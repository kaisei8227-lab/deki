package com.example.cardgame.model;

public class MatchResult {
    private String username;
    private String result; // "WIN" または "LOSE"
    private String opponent; // "AI"
    private String timestamp;

    public MatchResult() {}
    public MatchResult(String username, String result, String opponent, String timestamp) {
        this.username = username;
        this.result = result;
        this.opponent = opponent;
        this.timestamp = timestamp;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    public String getOpponent() { return opponent; }
    public void setOpponent(String opponent) { this.opponent = opponent; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}