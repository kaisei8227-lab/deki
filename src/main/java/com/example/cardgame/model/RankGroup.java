package com.example.cardgame.model;

public enum RankGroup {
    BEGINNER_0("Beginner 0", 0, 149, "🟢"),
    BEGINNER_1("Beginner 1", 150, 499, "🟢"),
    BEGINNER_2("Beginner 2", 500, 1199, "🟢"),
    BEGINNER_3("Beginner 3", 1200, 2199, "🟢"),

    D0("D0", 2200, 3599, "🟠"),
    D1("D1", 3600, 5199, "🟠"),
    D2("D2", 5200, 6999, "🟠"),
    D3("D3", 7000, 8999, "🟠"),

    C0("C0", 9000, 11399, "🔴"),
    C1("C1", 11400, 14099, "🔴"),
    C2("C2", 14100, 17099, "🔴"),
    C3("C3", 17100, 20399, "🔴"),

    B0("B0", 20400, 24199, "💎"),
    B1("B1", 24200, 28399, "💎"),
    B2("B2", 28400, 32999, "💎"),
    B3("B3", 33000, 37999, "💎"),

    A0("A0", 38000, 43499, "🟣"),
    A1("A1", 43500, 49499, "🟣"),
    A2("A2", 49500, 55999, "🟣"),
    A3("A3", 56000, 62999, "🟣"),

    AA0("AA0", 63000, 70999, "🔵"),
    AA1("AA1", 71000, 79999, "🔵"),
    AA2("AA2", 80000, 89999, "🔵"),
    AA3("AA3", 90000, 99999, "🔵"),

    MASTER_0("Master 0", 0, 4999, "🏆"),
    MASTER_1("Master 1", 5000, 9999, "🏆"),
    MASTER_2("Master 2", 10000, 14999, "🏆"),
    MASTER_3("Master 3", 15000, 19999, "🏆"),

    GRAND_MASTER("Grand Master", 20000, 100000, "👑");

    private final String displayName;
    private final int minMp;
    private final int maxMp;
    private final String icon;

    RankGroup(String displayName, int minMp, int maxMp, String icon) {
        this.displayName = displayName;
        this.minMp = minMp;
        this.maxMp = maxMp;
        this.icon = icon;
    }

    public String getDisplayName() { return displayName; }
    public int getMinMp() { return minMp; }
    public int getMaxMp() { return maxMp; }
    public String getIcon() { return icon; }

    /**
     * MP数値から対応するランクを取得
     */
    public static RankGroup getRankByMp(int mp) {
        for (RankGroup rank : values()) {
            if (mp >= rank.minMp && mp <= rank.maxMp) {
                return rank;
            }
        }
        return GRAND_MASTER; // 100,000以上はGrand Master
    }
}