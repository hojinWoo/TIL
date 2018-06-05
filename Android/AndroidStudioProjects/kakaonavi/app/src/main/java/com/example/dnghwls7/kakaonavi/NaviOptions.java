package com.example.dnghwls7.kakaonavi;

import com.example.dnghwls7.kakaonavi.options.CoordType;
import com.example.dnghwls7.kakaonavi.options.RpOption;
import com.example.dnghwls7.kakaonavi.options.VehicleType;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 카카오내비 API에 사용되는 선택적인 파라미터들을 위한 class.
 * @author kevin.kang
 * Created by kevin.kang on 2016. 9. 21..
 */
public class NaviOptions {
    private CoordType coordType;
    private VehicleType vehicleType;
    private RpOption rpoption;
    private Boolean routeInfo; // 전체 경로정보 보기 여부
    private Double startX; // 시작좌표 x
    private Double startY; // 시작좌표 y
    private Integer startAngle; // 시작 앵글 (0~359). 앵글을 설정하지 않을 경우 : -1
    private String userId; // 길안내 유저 구분을 위한 USER ID (현재 택시에서 사용)
    private String returnUri; // 길안내 종료(전체 경로보기시 종료) 후 호출 될 URI

    private static final String COORD_TYPE = "coord_type";
    private static final String VEHICLE_TYPE = "vehicle_type";
    private static final String RP_OPTION = "rpoption";
    private static final String ROUTE_INFO = "route_info";
    private static final String S_X = "s_x";
    private static final String S_Y = "s_y";
    private static final String S_ANGLE = "s_angle";
    private static final String USER_ID = "user_id";
    private static final String RETURN_URI = "return_uri";

    public static Builder newBuilder() {
        return new Builder();
    }

    JSONObject toJson() {
        JSONObject options = new JSONObject();
        try {
            if (coordType != null) {
                options.put(COORD_TYPE, coordType.getType());
            }
            if (vehicleType != null) {
                options.put(VEHICLE_TYPE, vehicleType.getType());
            }
            if (rpoption != null) {
                options.put(RP_OPTION, rpoption.getOption());
            }
            options.put(ROUTE_INFO, routeInfo);
            options.put(S_X, startX);
            options.put(S_Y, startY);
            options.put(S_ANGLE, startAngle);
            options.put(USER_ID, userId);
            options.put(RETURN_URI, returnUri);
            return options;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * NaviOptions를 간편하게 build하기 위한 Builder class.
     */
    public static class Builder {
        private CoordType coordType;
        private VehicleType vehicleType;
        private RpOption rpOption;
        private Boolean routeInfo;
        private Double startX;
        private Double startY;
        private Integer startAngle;
        private String userId;
        private String returnUri;

        public Builder setCoordType(final CoordType coordType) {
            this.coordType = coordType;
            return this;
        }

        public Builder setVehicleType(final VehicleType vehicleType) {
            this.vehicleType = vehicleType;
            return this;
        }

        public Builder setRpOption(final RpOption rpOption) {
            this.rpOption = rpOption;
            return this;
        }

        /**
         * sets option whether to see whole route or not
         *
         * @param routeInfo whether to see whole route or not
         * @return self
         */
        public Builder setRouteInfo(final boolean routeInfo) {
            this.routeInfo = routeInfo;
            return this;
        }

        public Builder setStartX(final double startX) {
            this.startX = startX;
            return this;
        }

        public Builder setStartY(final double startY) {
            this.startY = startY;
            return this;
        }

        /**
         * Sets start angle.
         *
         * @param startAngle from 0 ~ 359. -1 if not set.
         * @return self
         */
        @SuppressWarnings("unused")
        public Builder setStartAngle(final int startAngle) {
            this.startAngle = startAngle;
            return this;
        }

        @SuppressWarnings("unused")
        public Builder setUserId(final String userId) {
            this.userId = userId;
            return this;
        }

        /**
         * sets a return URI
         *
         * @param returnUri URI to be called when the navigation ends
         * @return self
         */
        public Builder setReturnUri(final String returnUri) {
            this.returnUri = returnUri;
            return this;
        }

        public NaviOptions build() {
            NaviOptions options = new NaviOptions();
            options.coordType = this.coordType;
            options.vehicleType = this.vehicleType;
            options.rpoption = this.rpOption;
            options.routeInfo = this.routeInfo;
            options.startX = this.startX;
            options.startY = this.startY;
            options.startAngle = this.startAngle;
            options.userId = this.userId;
            options.returnUri = this.returnUri;
            return options;
        }
    }
}