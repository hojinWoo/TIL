/*
  Copyright 2014-2018 Kakao Corp.

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

import com.example.dnghwls7.kakaonavi.Logger.DeployPhase;

/**
 * Provides string constants necessary to constitute API requests, including server hosts, and
 * api endpoint paths.
 *
 * @author MJ
 */
public final class ServerProtocol {
    public static final DeployPhase DEPLOY_PHASE = DeployPhase.current();
    public static final String AUTH_AUTHORITY = initAuthAuthority();
    @Deprecated
    public static final String AGE_AUTH_AUTHORITY = initAgeAuthAuthority();
    public static final String ACCOUNT_AUTHORITY = AGE_AUTH_AUTHORITY;
    public static final String API_AUTHORITY = initAPIAuthority();
    public static final String NAVI_AUTHORITY = initNaviAuthority();

    //Authorization: Bearer
    public static final String AUTHORIZATION_HEADER_KEY ="Authorization";
    public static final String AUTHORIZATION_BEARER ="Bearer";
    public static final String KAKAO_AK_HEADER_KEY = "KakaoAK";
    public static final String AUTHORIZATION_HEADER_DELIMITER = " ";

    public static final String SCHEME = "https";
    // oauth url
    public static final String AUTHORIZE_CODE_PATH = "oauth/authorize";
    public static final String ACCESS_TOKEN_PATH = "oauth/token";
    public static final String ACCESS_AGE_AUTH_PATH = "ageauths/main.html";

    // api url
    private static final String API_VERSION = "v1";
    private static final String V2_API_VERSION = "v2";

    // usermgmt
    public static final String USER_ME_PATH = API_VERSION + "/user/me";
    public static final String USER_ME_V2_PATH = V2_API_VERSION + "/user/me";
    public static final String USER_LOGOUT_PATH = API_VERSION + "/user/logout";
    public static final String USER_SIGNUP_PATH = API_VERSION + "/user/signup";
    public static final String USER_UNLINK_PATH = API_VERSION + "/user/unlink";
    public static final String USER_UPDATE_PROFILE_PATH = API_VERSION + "/user/update_profile";
    public static final String USER_ACCESS_TOKEN_INFO_PATH = API_VERSION + "/user/access_token_info";
    public static final String USER_AGE_AUTH = API_VERSION + "/user/age_auth";
    public static final String ACCOUNT_LOGIN_PATH = "kakao_accounts/view/login";
    // push
    public static final String PUSH_REGISTER_PATH = API_VERSION + "/push/register";
    public static final String PUSH_TOKENS_PATH = API_VERSION + "/push/tokens";
    public static final String PUSH_DEREGISTER_PATH = API_VERSION + "/push/deregister";
    public static final String PUSH_SEND_PATH = API_VERSION + "/push/send";

    //POST
    public static final String STORY_MULTI_UPLOAD_PATH = API_VERSION + "/api/story/upload/multi";
    public static final String STORY_POST_NOTE_PATH = API_VERSION + "/api/story/post/note";
    public static final String STORY_POST_PHOTO_PATH = API_VERSION + "/api/story/post/photo";
    public static final String STORY_POST_LINK_PATH = API_VERSION + "/api/story/post/link";
    //GET
    public static final String STORY_PROFILE_PATH = API_VERSION + "/api/story/profile";
    public static final String STORY_ACTIVITIES_PATH = API_VERSION + "/api/story/mystories";
    public static final String STORY_ACTIVITY_PATH = API_VERSION + "/api/story/mystory";
    public static final String STORY_LINK_SCRAPPER_PATH = API_VERSION + "/api/story/linkinfo";
    public static final String IS_STORY_USER_PATH = API_VERSION + "/api/story/isstoryuser";
    //DELETE
    public static final String STORY_DELETE_ACTIVITY_PATH = API_VERSION + "/api/story/delete/mystory";

    //talk
    public static final String TALK_PROFILE_PATH = API_VERSION + "/api/talk/profile";
    public static final String TALK_MESSAGE_SEND = API_VERSION + "/api/talk/message/send";
    public static final String TALK_MEMO_SEND = API_VERSION + "/api/talk/memo/send";
    public static final String TALK_CHATROOM_LIST_PATH = API_VERSION + "/api/talk/chat/list";

    public static final String TALK_MESSAGE_SEND_V2_PATH = V2_API_VERSION + "/api/talk/message/send";
    public static final String TALK_MESSAGE_SCRAP_V2_PATH = V2_API_VERSION + "/api/talk/message/scrap/send";
    public static final String TALK_MESSAGE_DEFAULT_V2_PATH = V2_API_VERSION + "/api/talk/message/default/send";
    public static final String TALK_MEMO_SEND_V2_PATH = V2_API_VERSION + "/api/talk/memo/send";
    public static final String TALK_MEMO_SCRAP_V2_PATH = V2_API_VERSION + "/api/talk/memo/scrap/send";
    public static final String TALK_MEMO_DEFAULT_V2_PATH = V2_API_VERSION + "/api/talk/memo/default/send";

    @Deprecated
    public static final String TALK_CHAT_LIST_PATH = API_VERSION + "/api/talk/chats";

    // link
    public static final String LINK_TEMPLATE_PATH = "/api/kakaolink/talk/template";
    public static final String LINK_TEMPLATE_VALIDATE_PATH = V2_API_VERSION + LINK_TEMPLATE_PATH + "/validate";
    public static final String LINK_TEMPLATE_DEFAULT_PATH = V2_API_VERSION + LINK_TEMPLATE_PATH + "/default";
    public static final String LINK_TEMPLATE_SCRAP_PATH = V2_API_VERSION + LINK_TEMPLATE_PATH + "/scrap";

    public static final String TALK_MESSAGE_IMAGE_PATH = "/api/talk/message/image";
    public static final String LINK_IMAGE_UPLOAD_PATH = V2_API_VERSION + TALK_MESSAGE_IMAGE_PATH + "/upload";
    public static final String LINK_IMAGE_SCRAP_PATH = V2_API_VERSION + TALK_MESSAGE_IMAGE_PATH + "/scrap";
    public static final String LINK_IMAGE_DELETE_PATH = V2_API_VERSION + TALK_MESSAGE_IMAGE_PATH + "/delete";

    // friends and operation
    public static final String GET_FRIENDS_PATH = API_VERSION + "/friends";
    public static final String GET_FRIENDS_OPERATION_PATH = API_VERSION + "/friends/operation";

    // storage
    public static final String STORAGE_UPLOAD_IMAGE = API_VERSION + "/storage/image/upload";

    // s2
    public static final String EVENTS_PUBLISH_PATH = API_VERSION + "/s2/publish";
    public static final String EVENTS_PUBLISH_ADID_PATH = API_VERSION + "/s2/publish/adid";

    /**
     * 카카오내비 앱을 통해 실행 가능한 기능들.
     */
    public static final String NAVI_SHARE_PATH = "sharePoi";
    public static final String NAVI_GUIDE_PATH = "navigate";

    private static String initAuthAuthority() {
        switch (DEPLOY_PHASE) {
            /*case Local:
                return "localhost:";
            case Alpha:
                return "alpha-kauth.kakao.com";
            case Sandbox:
                return "sandbox-kauth.kakao.com";
            case Beta:
                return "beta-kauth.kakao.com";
            case Real:
                return "kauth.kakao.com";*/
            default:
                return null;
        }
    }

    private static String initAPIAuthority() {
        switch (DEPLOY_PHASE) {
            case Local:
                return "localhost:";
            case Alpha:
                return "alpha-kapi.kakao.com";
            case Sandbox:
                return "sandbox-kapi.kakao.com";
            case Beta:
                return "beta-kapi.kakao.com";
            case Real:
                return "kapi.kakao.com";
            default:
                return null;
        }
    }

    private static String initAgeAuthAuthority() {
        switch (DEPLOY_PHASE) {
            case Local:
                return "localhost:";
            case Alpha:
                return "alpha-auth.kakao.com";
            case Sandbox:
                return "sandbox-auth.kakao.com";
            case Beta:
                return "beta-auth.kakao.com";
            case Real:
                return "auth.kakao.com";
            default:
                return null;
        }
    }

    private static String initNaviAuthority() {
        switch (DEPLOY_PHASE) {
            case Local:
                return "localhost:";
            case Alpha:
            case Sandbox:
                return "sandbox-kakaonavi-wguide.kakao.com";
            case Beta:
            case Real:
                return "kakaonavi-wguide.kakao.com";
            default:
                return null;
        }
    }
}
