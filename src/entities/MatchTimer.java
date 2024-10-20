package entities;
import visuals.MatchFrames.MatchFrames;
import visuals.MatchFrames.MatchStats;

import javax.swing.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MatchTimer {

    // Changing this changes the speed of timer
    private int realWorldDurationInSeconds = 60;

    final int MatchMinutes = 90;
    final int MatchSeconds = MatchMinutes * 60;
    final int gameSecondsPerRealSecond = MatchSeconds / realWorldDurationInSeconds;
    private int gameMinutes;
    private int gameSeconds;
    private Match match;

    public MatchTimer(){
       this.gameMinutes = 0;
       this.gameSeconds = 0;
    }

    // This method will run the timed event in two different modes: "normal" or "fast"
    public void runEvent(String mode, Match match) {
        this.match = match;
        switch (mode) {
            case "slowest" -> runSlowestMode();
            case "slow" -> runSlowMode();
            case "fast" -> runfastMode();
            case "fastest" -> runFastestMode();
            case "instant" -> runInstantMode();
            default -> System.out.println("You chose the inavlid option: " + mode);
        }
    }

    public void runSlowestMode(){
        CompletableFuture.runAsync(this::runTimer);
    }

    public void runSlowMode(){
        setRealWorldDurationInSeconds(45);
        CompletableFuture.runAsync(this::runTimer);
    }

    public void runfastMode(){
        setRealWorldDurationInSeconds(30);
        CompletableFuture.runAsync(this::runTimer);
    }

    public void runFastestMode(){
        setRealWorldDurationInSeconds(15);
        CompletableFuture.runAsync(this::runTimer);
    }

    public void runTimer() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final int[] inAppTime = {0};

        Runnable task = () -> {
            inAppTime[0]++;

            setGameMinutes(inAppTime[0] / 60);
            setGameSeconds(inAppTime[0] % 60);

            // Prints the current in-app time
            if(this.match instanceof UsersMatch newMatch){
                for (JPanel page : newMatch.getCardMap().values()) {
                    if (page instanceof MatchFrames) {
                        ((MatchFrames) page).getTime().setText(getGameMinutes() + ":" + getGameSeconds());
                    }
                }
            }

            // Stop the scheduler when in-app time reaches 90 minutes (1200 seconds)
            if (inAppTime[0] >= MatchSeconds) {
                if(match instanceof UsersMatch usersMatch){
                    System.out.println("RUNNING methods inside runTimer finished");
                    usersMatch.getDelayTimer().cancel();
                    usersMatch.getDelayTimer().purge();
                    usersMatch.fullTimeCheck();
                }
                scheduler.shutdown();
            }
        };
        scheduler.scheduleAtFixedRate(task, 0, 1000 / gameSecondsPerRealSecond, TimeUnit.MILLISECONDS);
    }

    public String getTime(){
        return getGameMinutes() + ":" + getGameSeconds();
    }

    public int getMatchMinutes() {
        return MatchMinutes;
    }

    public String getGameMinutes() {
        String newValue = "";
        if(gameMinutes<10){
            newValue = "0" + gameMinutes;
        } else {
            newValue = String.valueOf(gameMinutes);
        }
        return newValue;
    }

    public void setGameMinutes(int gameMinutes) {
        this.gameMinutes = gameMinutes;
    }

    public String getGameSeconds() {
        String newValue = "";
        if(gameSeconds<10){
            newValue = "0" + gameSeconds;
        } else {
            newValue = String.valueOf(gameSeconds);
        }
        return newValue;
    }

    public void setGameSeconds(int gameSeconds) {
        this.gameSeconds = gameSeconds;
    }

    public int getMatchSeconds() {
        return MatchSeconds;
    }

    public int getGameSecondsPerRealSecond() {
        return gameSecondsPerRealSecond;
    }

    public void runInstantMode() {
        CompletableFuture.runAsync(this::runInstantly);
    }

    public void runInstantly(){
        final int[] inAppTime = {0};

        while (inAppTime[0] < MatchSeconds) {
            inAppTime[0] ++;

            setGameMinutes(inAppTime[0] / 60);
            setGameSeconds(inAppTime[0] % 60);
        }
        System.out.println("AN INSTANTLY PLAYED MATCH JUST FINISHED");
        match.updateWinsDrawsAndLossesForInstantMatches();
        match.updateDomesticLeagueTable();
        match.getLeague().getPlayerLeaderboard().updateLinesInTableLogic("Goals");
    }

    public int getRealWorldDurationInSeconds() {
        return realWorldDurationInSeconds;
    }

    public void setRealWorldDurationInSeconds(int realWorldDurationInSeconds) {
        this.realWorldDurationInSeconds = realWorldDurationInSeconds;
    }

}

// Need to better understand exactly what inAppTime is doing, can we debug it to understand it?