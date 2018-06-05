/*
  Copyright 2014-2017 Kakao Corp.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package com.example.dnghwls7.kakaonavi;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.example.dnghwls7.kakaonavi.BuildConfig;

import java.util.Locale;

public class SystemInfo {
    private static final int OS_VERSION = Build.VERSION.SDK_INT;
    private static final String DEVICE_MODEL = Build.MODEL.replaceAll("\\s", "-").toUpperCase();
    private static final String LANGUAGE = Locale.getDefault().getLanguage().toLowerCase();
    private static final String COUNTRY_CODE = Locale.getDefault().getCountry().toUpperCase();
    private static String KA_HEADER;

    public static void initialize(Context context) {
        if(KA_HEADER == null) {
            KA_HEADER = CommonProtocol.KA_SDK_KEY + "1.11.0-SNAPSHOT" + " " +
                    CommonProtocol.KA_OS_KEY + CommonProtocol.OS_ANDROID + "-" + OS_VERSION + " " +
                    CommonProtocol.KA_LANG_KEY + LANGUAGE + "-" + COUNTRY_CODE + " " +
                    CommonProtocol.KA_KEY_HASH + Utility.getKeyHash(context) + " " +
                    CommonProtocol.KA_DEVICE_KEY + DEVICE_MODEL + " " +
                    CommonProtocol.KA_PACKAGE_NAME + context.getPackageName();

            try {
                PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                KA_HEADER += (" " + CommonProtocol.KA_APP_VERSION + info.versionName);
            } catch (PackageManager.NameNotFoundException ignored) {
            }
        }
    }

    public static String getKAHeader() {
        return KA_HEADER;
    }
}
