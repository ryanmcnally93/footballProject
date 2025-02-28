package entities;
import visuals.MatchPages.MatchFrames;

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
    private boolean isPaused = false;
    private int inAppTime = 0;
    private Match match;
    private Runnable task;

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
        if (!isRunning) {
            CompletableFuture.runAsync(this::runTimer);
        }
    }

    public void runTimer() {
        if (scheduler == null || scheduler.isShutdown()) {
            scheduler = Executors.newScheduledThreadPool(1);
        }
        isRunning = true;
        isPaused = false;

        task = () -> {
            if (!isPaused) {
                inAppTime++;
                setGameMinutes(inAppTime / 60);
                setGameSeconds(inAppTime % 60);

                // Prints the current in-app time
                if (this.match instanceof UsersMatch newMatch) {
                    for (JPanel page : newMatch.getScheduler().getMatchFramesMap().values()) {
                        if (page instanceof MatchFrames) {
                            ((MatchFrames) page).getTime().setText(getGameMinutes() + ":" + getGameSeconds());
                        }
                    }
                }

                // Stop the scheduler when in-app time reaches 90 minutes (1200 seconds)
                if (inAppTime >= MatchSeconds) {
                    endMatch();
                    isRunning = false;
                }
            }
        };
        scheduler.scheduleAtFixedRate(task, 0, speed, TimeUnit.MILLISECONDS);
    }

    public void changeSpeedDuringMatch(int newSpeed) {
        if (isRunning) {
            scheduler.shutdown();
            scheduler = Executors.newScheduledThreadPool(1);
            changeSpeed(newSpeed);
            scheduler.scheduleAtFixedRate(task, 0, speed, TimeUnit.MILLISECONDS);
        } else if (isPaused) {
            changeSpeed(newSpeed);
        }
    }

    public void pauseTimer() {
        isPaused = true;
        isRunning = false;
        scheduler.shutdown();
    }

    public void resumeTimer(){
        if (isPaused) {
            isPaused = false;
            isRunning = true;
            scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(task, 0, speed, TimeUnit.MILLISECONDS);
        }
    }

    public void endMatch(){
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
        if (match instanceof UsersMatch usersMatch) {
            usersMatch.getDelayTimer().cancel();
            usersMatch.getDelayTimer().purge();
            usersMatch.fullTimeCheck();
        }
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

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public Runnable getTask() {
        return task;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }
}
