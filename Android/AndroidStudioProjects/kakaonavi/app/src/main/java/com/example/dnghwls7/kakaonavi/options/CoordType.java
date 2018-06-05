package com.example.dnghwls7.kakaonavi.options;

/** 좌표 타입을 나타내는 enum class.
 * @author kevin.kang
 * Created by kevin.kang on 2016. 8. 30..
 */
public enum CoordType {
    /**
     * The World Geodetic System 84
     */
    WGS84("wgs84"),

    /**
     * Katech coordinate system
     */
    @SuppressWarnings("unused")
    KATEC("katec");

    private final String type;

    CoordType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}