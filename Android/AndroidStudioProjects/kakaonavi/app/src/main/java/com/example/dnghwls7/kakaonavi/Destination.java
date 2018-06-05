package com.example.dnghwls7.kakaonavi;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 목적지 파라미터를 위한 class.
 *
 * @author kevin.kang
 * Created by kevin.kang on 2016. 11. 11..
 */
public class Destination extends Location {

    private final Integer guideId; // 안내좌표 ID. default -1.
    private static final String GUIDE_ID = "guide_id";

    private Destination(Builder builder) {
        super(builder);
        this.guideId = builder.guideId;
    }

    public static Builder newBuilder(final String name, final double x, final double y) {
        return new Builder(name, x, y);
    }

    @SuppressWarnings("unused")
    public int getGuideId() {
        return guideId;
    }

    @Override
    protected JSONObject toJson() {
        JSONObject destination = super.toJson();
        try {
            destination.put(GUIDE_ID, guideId);
            return destination;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Builder for Destination parameter.
     */
    public static class Builder extends Location.Builder<Builder> {
        private Integer guideId;

        Builder(final String name, final double x, final double y) {
            super(name, x, y);
        }

        @SuppressWarnings("unused")
        public Builder setGuideId(final Integer guideId) {
            this.guideId = guideId;
            return this;
        }

        public Destination build() {
            return new Destination(this);
        }
    }
}
