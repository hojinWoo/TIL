package com.example.dnghwls7.kakaonavi.options;

/** 경로 옵션을 나타내는 enum class.
 * @author kevin.kang
 * Created by kevin.kang on 2016. 8. 30..
 */
public enum RpOption {
    /**
     * Fastest route
     */
    FAST(1), // 빠른길
    /**
     * Free route
     */
    FREE(2), // 무료도로
    /**
     * Shortest route
     */
    SHORTEST(3), // 최단거리
    /**
     * Exclude motorway
     */
    NO_AUTO(4), // 자동차전용제외
    /**
     * Wide road first
     */
    WIDE(5), // 큰길우선
    /**
     * Highway first
     */
    HIGHWAY(6), // 고속도로우선
    /**
     * Normal road first
     */
    NORMAL(8), // 일반도로우선
    /**
     * Recommended route (Current default option if not set)
     */
    RECOMMENDED(100); // 추천경로 (기본값)


    private final int option;

    RpOption(int option) {
        this.option = option;
    }

    public int getOption() {
        return this.option;
    }
}