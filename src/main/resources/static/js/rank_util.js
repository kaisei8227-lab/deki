// ===================================================
// 1. MPに応じたランクデータ定義（0 MPスタート対応）
// ===================================================
const RANK_TABLE = [
    { name: "Beginner 0", minMp: 0, maxMp: 199, icon: "🟢" },
    { name: "Beginner 1", minMp: 200, maxMp: 499, icon: "🟢" },
    { name: "Beginner 2", minMp: 500, maxMp: 999, icon: "🟢" },
    { name: "Beginner 3", minMp: 1000, maxMp: 1999, icon: "🟢" },
    { name: "D0", minMp: 2000, maxMp: 3499, icon: "🟠" },
    { name: "D1", minMp: 3500, maxMp: 4999, icon: "🟠" },
    { name: "D2", minMp: 5000, maxMp: 6999, icon: "🟠" },
    { name: "D3", minMp: 7000, maxMp: 8999, icon: "🟠" },
    { name: "C0", minMp: 9000, maxMp: 11499, icon: "🔴" },
    { name: "C1", minMp: 11500, maxMp: 13999, icon: "🔴" },
    { name: "C2", minMp: 14000, maxMp: 16999, icon: "🔴" },
    { name: "C3", minMp: 17000, maxMp: 19999, icon: "🔴" },
    { name: "B0", minMp: 20000, maxMp: 23999, icon: "💎" },
    { name: "B1", minMp: 24000, maxMp: 27999, icon: "💎" },
    { name: "B2", minMp: 28000, maxMp: 31999, icon: "💎" },
    { name: "B3", minMp: 32000, maxMp: 37999, icon: "💎" },
    { name: "A0", minMp: 38000, maxMp: 43499, icon: "🟣" },
    { name: "A1", minMp: 43500, maxMp: 49399, icon: "🟣" },
    { name: "A2", minMp: 49400, maxMp: 55999, icon: "🟣" },
    { name: "A3", minMp: 56000, maxMp: 62999, icon: "🟣" },
    { name: "AA0", minMp: 63000, maxMp: 70999, icon: "🔵" },
    { name: "AA1", minMp: 71000, maxMp: 79999, icon: "🔵" },
    { name: "AA2", minMp: 80000, maxMp: 89999, icon: "🔵" },
    { name: "AA3", minMp: 90000, maxMp: 99999, icon: "🔵" },
    { name: "Master", minMp: 100000, maxMp: 150000, icon: "🏆" },
    { name: "Grand Master", minMp: 150001, maxMp: 999999, icon: "👑" }
];

// MPから現在のランク情報を計算するユーティリティ関数
function getRankInfo(mp = 0) {
    let rank = RANK_TABLE.find(r => mp >= r.minMp && mp <= r.maxMp);
    if (!rank) {
        rank = RANK_TABLE[RANK_TABLE.length - 1]; // 最大値超過時はGrand Master
    }
    const nextMp = rank.maxMp + 1 - mp;
    return {
        name: rank.name,
        icon: rank.icon,
        currentMp: mp,
        nextMp: Math.max(0, nextMp)
    };
}


// ===================================================
// 2. グループ＆獲得MPボーナス定義
// ===================================================
const GROUP_TABLE = [
    { name: "エメラルド",   minWinStreak: 0,  mpBonus: 100, icon: "🟢", color: "#48bb78" },
    { name: "トパーズ",     minWinStreak: 3,  mpBonus: 110, icon: "🟠", color: "#ed8936" },
    { name: "ルビー",       minWinStreak: 5,  mpBonus: 120, icon: "🔴", color: "#e53e3e" },
    { name: "サファイア",   minWinStreak: 8,  mpBonus: 130, icon: "🔵", color: "#3182ce" },
    { name: "ダイヤモンド", minWinStreak: 10, mpBonus: 150, icon: "💎", color: "#00f0ff" }
];

// 連勝数からグループ情報と獲得MP数を取得するユーティリティ関数
function getGroupInfo(winStreak = 0) {
    let group = GROUP_TABLE[0];
    for (let i = GROUP_TABLE.length - 1; i >= 0; i--) {
        if (winStreak >= GROUP_TABLE[i].minWinStreak) {
            group = GROUP_TABLE[i];