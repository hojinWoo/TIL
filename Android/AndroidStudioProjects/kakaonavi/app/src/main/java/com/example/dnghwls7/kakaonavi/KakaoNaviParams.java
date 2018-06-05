package com.example.dnghwls7.kakaonavi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 카카오내비에 사용되는 파라미터들을 위한 class.
 * @author kevin.kang
 * Created by kevin.kang on 2016. 9. 21..
 */

public class KakaoNaviParams {
    private Location destination;
    private  NaviOptions naviOptions;
    private List<Location> stops = new ArrayList<Location>();

    private static final String DESTINATION = "destination";
    private static final String OPTION = "option";
    private static final String VIA_LIST = "via_list";

    private static final int MAX_VIA = 3;

    private KakaoNaviParams(final Location destination, final NaviOptions naviOptions, final List<Location> stops) {
        if (destination == null)
            throw new NullPointerException("Destination is a required parameter for KakaoNavi API.");
        this.destination = destination;
        this.naviOptions = naviOptions;
        if (stops != null) {
            if (stops.size() > MAX_VIA)
                throw new IllegalArgumentException("More than three locations were provided as via lists. Maximum number allowed is three.");
            this.stops = stops;
        }
    }

    @SuppressWarnings("unused")
    public Location getDestination() {
        return destination;
    }
    @SuppressWarnings("unused")
    public NaviOptions getNaviOptions() {
        return naviOptions;
    }
    @SuppressWarnings("unused")
    public List<Location> getStops() {
        return stops;
    }

    /**
     * 필수 파라미터들을 포함하는 Builder를 생성하는 메소드.
     * @param destination 목적지 객체.
     * @return Builder for KakaoNaviParams
     */
    public static Builder newBuilder(final Location destination) {
        return new Builder(destination);
    }

    JSONObject toJson() throws JSONException {
        JSONObject params = new JSONObject();
        params.put(DESTINATION, destination.toJson());
        if (naviOptions != null) {
            params.put(OPTION, naviOptions.toJson());
        }

        if (stops.size() == 0) {
            return params;
        }

        JSONArray viaLists = new JSONArray();
        for (Location stop : stops) {
            viaLists.put(stop.toJson());
        }
        params.put(VIA_LIST, viaLists);
        return params;
    }

    /**
     * KakaoNaviParams를 간편하게 build하기 위한 Builder class.
     */
    public static class Builder {
        private final Location destination;
        private NaviOptions naviOptions;
        private List<Location> stops;

        private Builder(final Location destination) {
            this.destination = destination;
        }

        public Builder setNaviOptions(final NaviOptions naviOptions) {
            this.naviOptions = naviOptions;
            return this;
        }

        public Builder setViaList(final List<Location> stops) {
            this.stops = stops;
            return this;
        }

        public KakaoNaviParams build() {
            return new KakaoNaviParams(destination, naviOptions, stops);
        }
    }
}
