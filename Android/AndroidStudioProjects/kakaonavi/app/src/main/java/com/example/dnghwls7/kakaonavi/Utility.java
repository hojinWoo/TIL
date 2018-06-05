/**
 * Copyright 2014-2016 Kakao Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.dnghwls7.kakaonavi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public final class Utility {
    private static final String TAG = Utility.class.getCanonicalName();

    public static boolean isNullOrEmpty(final String s) {
        return (s == null) || (s.length() == 0);
    }

    public static Uri buildUri(final String authority, final String path) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(CommonProtocol.URL_SCHEME);
        builder.authority(authority);
        builder.path(path);
        return builder.build();
    }

    public static Uri buildUri(final String authority, final String path, final Bundle parameters) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(CommonProtocol.URL_SCHEME);
        builder.authority(authority);
        builder.path(path);

        if (parameters != null) {
            for (String key : parameters.keySet()) {
                Object parameter = parameters.get(key);
                if (parameter instanceof String) {
                    builder.appendQueryParameter(key, (String) parameter);
                }
            }
        }
        return builder.build();
    }

    // URL 인코딩 되어 있지 않은 query string을 반환한다. KakaoNetworkImpl을 사용하는 경우 인코딩을 알아서 처리하기 때문에 전 단계에서 인코딩을 하면 안된다.
    public static String buildQueryString(Map<String, String> params) {
        if (params.size() == 0) return null;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> e : params.entrySet()) {
            if (sb.length() > 0) {
                sb.append('&');
            }
            sb.append(e.getKey()).append("=").append(e.getValue());
        }
        return sb.toString();
    }

    public static void putObjectInBundle(final Bundle bundle, final String key, final Object value) {
        if (value instanceof String) {
            bundle.putString(key, (String) value);
        } else if (value instanceof Parcelable) {
            bundle.putParcelable(key, (Parcelable) value);
        } else if (value instanceof byte[]) {
            bundle.putByteArray(key, (byte[]) value);
        } else {
            throw new KakaoException("attempted to add unsupported type to Bundle");
        }
    }

    public static void notNull(final Object arg, final String name) {
        if (arg == null) {
            throw new NullPointerException("Argument '" + name + "' cannot be null");
        }
    }

    public static String getMetadata(final Context context, final String key) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                context.getPackageName(), PackageManager.GET_META_DATA);
            if(ai == null)
                return null;
            else if(ai.metaData == null)
                return null;
            else
                return ai.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static boolean getBooleanMetadata(final Context context, final String key, final boolean defaultValue) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (ai == null)
                return defaultValue;
            else if (ai.metaData == null)
                return defaultValue;
            else
                return ai.metaData.getBoolean(key, defaultValue);
        } catch (PackageManager.NameNotFoundException e) {
            return defaultValue;
        }
    }

    public static ResolveInfo resolveIntent(final Context context, final Intent intent) {
        return context.getPackageManager().resolveActivity(intent, 0);
    }

    public static PackageInfo getPackageInfo(final Context context) {
        return getPackageInfo(context, 0);
    }


    public static PackageInfo getPackageInfo(final Context context, int flag) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), flag);
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, "Unable to get PackageInfo", e);
        }
        return null;
    }

    public static boolean isPackageInstalled(final Context context, final String packageName) {
        return context.getPackageManager().getLaunchIntentForPackage(packageName) != null;
    }

    public static int getAppVersion(final Context context) {
        return getPackageInfo(context).versionCode;
    }

    public static String getAppPackageName(final Context context) {
        return getPackageInfo(context).packageName;
    }

    public static String getKeyHash(final Context context) {
        PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Log.w(TAG, "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
        return null;
    }

    public static boolean isUsablePermission(Context context, String permission) {
        int res = context.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }
}

