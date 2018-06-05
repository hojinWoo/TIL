package com.example.dnghwls7.kakaonavi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Window;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Activity that renders web version of KakaoNavi. This activity takes care of runtime location permission.
 */
public class KakaoNaviWebViewActivity extends Activity {
    private final int REQUEST_CODE_FINE_LOCATION = 1022;
    private final int REQUEST_CODE_LOCATION_SETTINGS = 1023; // 위치 권한 설정의 request code.
    private final int REQUEST_CODE_APP_LOCATION_SETTINGS = 1024; // 앱 권한 설정의 request code.
    private AlertDialog alertDialog;

    public class KakaoNaviWebViewClient extends WebViewClient {
        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null && KakaoNaviProtocol.NAVI_MARKET_URL.equals(url)) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                finish();
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Uri uri = request.getUrl();
            if (uri != null && KakaoNaviProtocol.NAVI_MARKET_URL.equals(request.getUrl().toString())) {
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
                finish();
                return true;
            }
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    public class KakaoNaviWebChromeClient extends WebChromeClient {
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        if (isLocationEnabled(this)) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_FINE_LOCATION);
                } else {
                    showSettingsAlertDialog(); // 유저가 거부하면서 다시 묻지 않기를 클릭..
                }
            } else {
                loadKakaoNaviWebView();
            }
        } else {
            showSettingsAlertDialog(); // 기기의 위치 권한이 꺼진 상태..
        }
    }

    @Override
    protected void onDestroy() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        super.onDestroy();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_FINE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadKakaoNaviWebView();
                } else {
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_LOCATION_SETTINGS: // 기기 위치 권한 세팅에서 돌아옴
                if (!isLocationEnabled(this)) {
                    finish();
                } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    showSettingsAlertDialog();
                } else {
                    loadKakaoNaviWebView();
                }
                break;
            case REQUEST_CODE_APP_LOCATION_SETTINGS: // 앱의 권한 세팅에서 돌아옴
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    finish();
                } else if (!isLocationEnabled(this)) {
                    showSettingsAlertDialog();
                } else {
                    loadKakaoNaviWebView();
                }
            default:
                break;
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void loadKakaoNaviWebView() {
        setContentView(R.layout.kakao_navi);
        Intent intent = getIntent();
        String url = intent.getStringExtra(KakaoNaviProtocol.PROPERTY_WEB_URL);
        if (url == null) {
            finish();
        }
        WebView webView = (WebView) findViewById(R.id.navi_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);

        webView.loadUrl(url);

        webView.setWebViewClient(new KakaoNaviWebViewClient());
        webView.setWebChromeClient(new KakaoNaviWebChromeClient());
    }

    private void showSettingsAlertDialog() {
        String alertMessage;
        final int requestCode;
        if (isLocationEnabled(this)) {
            alertMessage = getString(R.string.permission_error_app_location);
            requestCode = REQUEST_CODE_APP_LOCATION_SETTINGS;
        } else {
            alertMessage = getString(R.string.permission_error_location);
            requestCode = REQUEST_CODE_LOCATION_SETTINGS;
        }

        alertDialog = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert).setTitle(getResources().getString(R.string.permission_error_title))
                .setMessage(alertMessage)
                .setPositiveButton(getResources().getString(R.string.permission_error_okay), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivityForResult(getLocationSettingsIntent(), requestCode);
                    }
                }).setNegativeButton(getString(R.string.permission_error_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        finish();
                    }
                }).create();
        alertDialog.show();
    }

    /**
     * 기기의 위치 권한이 켜져 있는지 확인한다.
     * @param context 현재 컨텍스트
     * @return 위치권한이 켜져 있으면 true, 꺼져 있으면 false
     */
    @SuppressWarnings("deprecation")
    private boolean isLocationEnabled(final Context context) {
        int locationMode;
        String locationProviders;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    /**
     * 기기의 위치 권한 혹은 앱의 위치 권한을 획득하기 위한 설정 페이지 intent를 리턴한다.
     * @return 기기 위치 권한 혹은 앱의 권한 설정 페이지 intent
     */
    Intent getLocationSettingsIntent() {
        Intent intent;
        if (isLocationEnabled(this)) {
            intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("package:" + getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY| Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        } else {
            intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        }
        return intent;
    }
}
