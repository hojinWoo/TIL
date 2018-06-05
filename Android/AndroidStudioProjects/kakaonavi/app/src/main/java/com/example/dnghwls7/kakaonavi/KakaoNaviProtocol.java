package com.example.dnghwls7.kakaonavi;

/**
 * @author kevin.kang
 * Created by kevin.kang on 2016. 11. 15..
 */

class KakaoNaviProtocol {
    /**
     * 카카오내비 패키지 이름. 앱이 설치되어 있는지 확인할 때 사용한다.
     */
    static final String NAVI_PACKAGE = "com.locnall.KimGiSa";

    public static final String MARKET_URL_PREFIX = "market://details?id=";
    public static final String MARKET_WEB_URL_PREFIX = "https://play.google.com/store/apps/details?id=";

    public static final String MARKET_SCHEME = "market";
    public static final String MARKET_AUTHORITY = "details";
    public static final String QUERY_ID = "id";

    public static final String MARKET_WEB_AUTHORITY = "play.google.com";
    public static final String MARKET_WEB_PATH = "store/apps/details";


    public static final String NAVI_SCHEME = "kakaonavi-sdk";
    public static final String NAVI_WEB_SCHEME = "https";

    static final String NAVI_MARKET_URL = MARKET_URL_PREFIX + NAVI_PACKAGE;
    static final String PROPERTY_WEB_URL = "com.kakao.sdk.kakaonavi.web_url";
    static final String NAVI_USE_WEB_VIEW = "com.kakao.sdk.kakaonavi.useWebView";
}
