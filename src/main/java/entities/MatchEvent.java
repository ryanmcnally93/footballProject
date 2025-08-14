package main.java.entities;

import main.java.people.Footballer;

public class MatchEvent {

    private String homeOrAway;
    private String eventType;
    private Footballer player;
    private int minute;

    public MatchEvent(String homeOrAway, String eventType, Footballer player, int minute) {
        this.homeOrAway = homeOrAway;
        this.eventType = eventType;
        this.player = player;
        this.minute = minute;
    }

    public String getHomeOrAway() {
        return homeOrAway;
    }

    public void setHomeOrAway(String homeOrAway) {
        this.homeOrAway = homeOrAway;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Footballer getPlayer() {
        return player;
    }

    public void setPlayer(Footballer player) {
        this.player = player;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
