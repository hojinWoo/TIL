package com.example.dnghwls7.kakaonavi;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 목적지/경유지를 나타내기 위한 class.
 *
 * @author kevin.kang
 * Created by kevin.kang on 2016. 8. 27..
 */
public class Location {
    /**
     * 필수 파라미터.
     */
    private final String name; // 목적지명
    private Double x; // 목적지 x (경도)
    private Double y; // 목적지 y (위도)

    /**
     * 아래 두개의 필드는 kakao service only.
     */
    private String rpflag; // 도착링크 옵션. default G.
    private String cid; // confirm id (poi id).

    /**
     * 실제 리퀘스트에 들어갈 필드 이름.
     */
    private static final String NAME = "name";
    private static final String X = "x";
    private static final String Y = "y";
    private static final String RP_FLAG = "rpflag";
    private static final String CID = "cid";

    Location(Builder builder) {
        this.name = builder.name;
        if (this.name == null) {
            throw new NullPointerException("Location's name is a required field.");
        }
        this.x = builder.x;
        this.y = builder.y;
        this.rpflag = builder.rpflag;
        this.cid = builder.cid;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @SuppressWarnings("unused")
    public String getRpflag() {
        return rpflag;
    }

    @SuppressWarnings("unused")
    public String getCid() {
        return cid;
    }

    /**
     * 필수 파라미터를 포함하는 Builder를 생성하는 메소드.
     * @param name 장소의 이름
     * @param x 좌표의 x값
     * @param y 좌표의 y값
     * @return 필수 파라미터들이 채워져 있는 Location.Builder 객체
     */
    public static Builder newBuilder(final String name, final double x, final double y) {
        return new Builder(name, x, y);
    }

    protected JSONObject toJson() {
        JSONObject location = new JSONObject();
        try {
            location.put(NAME, name);
            location.put(X, x);
            location.put(Y, y);
            location.put(RP_FLAG, rpflag);
            location.put(CID, cid);
            return location;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Destination을 간편하게 build하기 위한 Builder class.
     */
    public static class Builder<T extends Builder<T>> {
        private final String name;
        private final Double x;
        private final Double y;
        private String rpflag;
        private String cid;


        Builder(final String name, final double x, final double y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        @SuppressWarnings("unchecked, unused")
        public T setRpflag(final String rpflag) {
            this.rpflag = rpflag;
            return (T) this;
        }

        /**
         * set confirm id (poi id).
         * @param cid confirm id
         * @return self
         */
        @SuppressWarnings("unchecked, unused")
        public T setCid(final String cid) {
            this.cid = cid;
            return (T) this;
        }

        public Location build() {
            return new Location(this);
        }
    }
}
