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
    private int speed;
    private ScheduledExecutorService scheduler;
    private boolean isRunning = false;
    private int inAppTime = 0;
    private Match match;

    public MatchTimer(){
       this.gameMinutes = 0;
       this.gameSeconds = 0;
    }

    // This method will run the timed event in two different modes: "normal" or "fast"
    public void runEvent(String mode, Match match) {
        this.match = match;
        switch (mode) {
            case "slowest" -> run(1);
            case "slow" -> run(2);
            case "fast" -> run(3);
            case "fastest" -> run(4);
            case "instant" -> runInstantMode();
            default -> System.out.println("You chose the inavlid option: " + mode);
        }
    }

    public void run(int speed){
        changeSpeed(speed);
        CompletableFuture.runAsync(this::runTimer);
    }

    public void runTimer() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }

        scheduler = Executors.newScheduledThreadPool(1);
        isRunning = true;

        Runnable task = () -> {
            inAppTime++;

            setGameMinutes(inAppTime / 60);
            setGameSeconds(inAppTime % 60);

            // Prints the current in-app time
            if(this.match instanceof UsersMatch newMatch){
                for (JPanel page : newMatch.getCardMap().values()) {
                    if (page instanceof MatchFrames) {
                        ((MatchFrames) page).getTime().setText(getGameMinutes() + ":" + getGameSeconds());
                    }
                }
            }

            // Stop the scheduler when in-app time reaches 90 minutes (1200 seconds)
            if (inAppTime >= MatchSeconds) {
                if(match instanceof UsersMatch usersMatch){
                    System.out.println("RUNNING methods inside runTimer finished");
                    usersMatch.getDelayTimer().cancel();
                    usersMatch.getDelayTimer().purge();
                    usersMatch.fullTimeCheck();
                }
                scheduler.shutdown();
                isRunning = false;
            }
        };
        scheduler.scheduleAtFixedRate(task, 0, speed, TimeUnit.MILLISECONDS);
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

    public void changeSpeed(int value) {
        speed = (1000 / gameSecondsPerRealSecond) / value;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}

// Need to better understand exactly what inAppTime is doing, can we debug it to understand it?