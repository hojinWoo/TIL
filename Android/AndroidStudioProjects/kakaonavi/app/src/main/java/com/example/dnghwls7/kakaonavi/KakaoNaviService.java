package com.example.dnghwls7.kakaonavi;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;



import org.json.JSONException;
import org.json.JSONObject;

/**
 * 카카오내비 앱을 호출하여 목적지 공유/길찾기를 하기 위한 API.
 * @author kevin.kang
 * Created by kevin.kang on 2016. 8. 26..
 */

public class KakaoNaviService {

    static KakaoNaviService mInstance = new KakaoNaviService();

    public static KakaoNaviService getInstance() {
        return mInstance;
    }

    /**
     * 카카오내비 앱의 목적지 공유를 하기 위한 API.
     * @param context 현재 목적지 공유 API를 호출하는 컨텍스트
     * @param params 카카오내비 파라미터
     */
    public void shareDestination(final Context context, final KakaoNaviParams params) {
        shareDestination(context, params, 0);
    }

    /**
     * 카카오내비 앱의 목적지 공유를 하기 위한 API.
     * @param context 현재 목적지 공유 API를 호출하는 컨텍스트
     * @param params 카카오내비 파라미터
     * @param activityFlags custom activity flags such as {@link Intent#FLAG_ACTIVITY_NEW_TASK}
     */
    public void shareDestination(final Context context, final KakaoNaviParams params, final int activityFlags) {
        SystemInfo.initialize(context.getApplicationContext());
        Intent intent = buildKakaoNaviIntent(context, ServerProtocol.NAVI_SHARE_PATH, params, activityFlags);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 카카오내비 앱의 길 안내를 실행하기 위한 API.
     * @param context 현재 길 안내 API를 호출하는 컨텍스트
     * @param params 카카오내비 파라미터
     */
    public void navigate(final Context context, final KakaoNaviParams params) {
        navigate(context, params, 0);
    }

    /**
     * 카카오내비 앱의 길 안내를 실행하기 위한 API.
     * @param context 현재 길 안내 API를 호출하는 컨텍스트
     * @param params 카카오내비 파라미터
     * @param activityFlags custom activity flags such as {@link Intent#FLAG_ACTIVITY_NEW_TASK}
     */
    public void navigate(final Context context, final KakaoNaviParams params, final int activityFlags) {
        SystemInfo.initialize(context.getApplicationContext());
        Intent intent = buildKakaoNaviIntent(context.getApplicationContext(), ServerProtocol.NAVI_GUIDE_PATH, params, activityFlags);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 카카오내비 앱이 설치되어 있는지 확인해 주는 메소드.
     * @param context 앱의 컨텍스트
     * @return true if installed, false otherwise.
     */
    @SuppressWarnings("unused")
    public boolean isKakaoNaviInstalled(final Context context) {
        return Utility.isPackageInstalled(context, KakaoNaviProtocol.NAVI_PACKAGE);
    }


    Intent buildKakaoNaviIntent(final Context context, final String endpoint, final KakaoNaviParams params, final int activityFlags) {
        String appKey = getAppKey(context);
        if (appKey == null) {
            throw new IllegalStateException("Native app key is not defined in AndroidManifest.xml.");
        }
        if (params == null) {
            throw new NullPointerException("KakaoNaviParams is a required parameter and cannot be null.");
        }

        boolean isNative = isKakaoNaviInstalled(context);
        boolean isWebAllowed = isKakaoNaviWebViewAllowed(context);
        boolean needsParameter = isNative || isWebAllowed;

        Uri uri = buildKakaoNaviUri(context, endpoint, params);

        Intent intent;
        if (!needsParameter) {
            intent = new Intent(Intent.ACTION_VIEW, uri);
            if (activityFlags != 0) {
                intent.addFlags(activityFlags);
            }
            return intent;
        }

        if (isKakaoNaviInstalled(context.getApplicationContext()) ) {
            intent = new Intent(Intent.ACTION_VIEW, uri);
        } else {
            intent = new Intent(context, KakaoNaviWebViewActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(KakaoNaviProtocol.PROPERTY_WEB_URL, uri.toString());
        }
        if (activityFlags != 0) {
            intent.addFlags(activityFlags);
        }
        return intent;
    }

    Uri buildKakaoNaviUri(final Context context, final String endpoint, final KakaoNaviParams params) {
        Uri.Builder builder = new Uri.Builder();
        boolean isNative = isKakaoNaviInstalled(context);
        boolean isWebAllowed = isKakaoNaviWebViewAllowed(context);
        boolean needsParameter = isNative || isWebAllowed;

        if (isNative) {
            builder.scheme(KakaoNaviProtocol.NAVI_SCHEME);
            builder.authority(endpoint);
        } else {
            if (isWebAllowed) {
                builder.scheme(KakaoNaviProtocol.NAVI_WEB_SCHEME);
                builder.authority(ServerProtocol.NAVI_AUTHORITY);
                builder.path(ServerProtocol.NAVI_GUIDE_PATH + ".html");
            } else {
                if (isGooglePlayStoreInstalled(context)) {
                    builder.scheme(KakaoNaviProtocol.MARKET_SCHEME);
                    builder.authority(KakaoNaviProtocol.MARKET_AUTHORITY);
                    builder.appendQueryParameter(KakaoNaviProtocol.QUERY_ID, KakaoNaviProtocol.NAVI_PACKAGE);
                } else {
                    builder.scheme(KakaoNaviProtocol.NAVI_WEB_SCHEME);
                    builder.authority(KakaoNaviProtocol.MARKET_WEB_AUTHORITY);
                    builder.path(KakaoNaviProtocol.MARKET_WEB_PATH);
                    builder.appendQueryParameter(KakaoNaviProtocol.QUERY_ID, KakaoNaviProtocol.NAVI_PACKAGE);
                }
            }
        }

        if (needsParameter) {
            try {
                JSONObject paramJson = params.toJson();
                builder.appendQueryParameter("param", paramJson.toString());
            } catch (JSONException e) {
                throw new IllegalArgumentException("JSON parsing error. Malformed parameters were provided to KakaoNavi API. detailed error message: " + e.toString());
            }
            builder.appendQueryParameter("apiver", "1.0");
            builder.appendQueryParameter("appkey", getAppKey(context));
            builder.appendQueryParameter("extras", getExtras(context));
        }
        return builder.build();
    }


    String getExtras(final Context context) {
        JSONObject extras = new JSONObject();
        String keyHash = Utility.getKeyHash(context);

        if (keyHash == null)
            throw new IllegalStateException("Android key hash is a required parameter for KakaoNavi API.");

        try {
            extras.put("appPkg", context.getPackageName());
            extras.put(CommonProtocol.KA_HEADER_KEY, SystemInfo.getKAHeader());
            extras.put("keyHash", keyHash);
            return extras.toString();
        } catch (JSONException e) {
            throw new IllegalArgumentException("JSON parsing error. Malformed parameters wer provided to KakaoNavi API. Detailed error message: " + e.toString());
        }
    }

    String getAppKey(final Context context) {
        return Utility.getMetadata(context, CommonProtocol.APP_KEY_PROPERTY);
    }

    boolean isKakaoNaviWebViewAllowed(final Context context) {
        return Utility.getBooleanMetadata(context, KakaoNaviProtocol.NAVI_USE_WEB_VIEW, true);
    }

    boolean isGooglePlayStoreInstalled(final Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(KakaoNaviProtocol.NAVI_MARKET_URL));
        return Utility.resolveIntent(context, intent) != null;
    }

    private KakaoNaviService() {
    }
}
