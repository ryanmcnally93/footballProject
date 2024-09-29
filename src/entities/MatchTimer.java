package entities;
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

    public MatchTimer(){
       this.gameMinutes = 0;
       this.gameSeconds = 0;
    }

    // This method will run the timed event in two different modes: "normal" or "fast"
    public void runEvent(String mode) {
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
//            System.out.println(getGameMinutes() + ":" + getGameSeconds());

            // Stop the scheduler when in-app time reaches 90 minutes (1200 seconds)
            if (inAppTime[0] >= MatchSeconds) {
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

    public int getGameMinutes() {
        return gameMinutes;
    }

    public void setGameMinutes(int gameMinutes) {
        this.gameMinutes = gameMinutes;
    }

    public int getGameSeconds() {
        return gameSeconds;
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

//            System.out.println("In-app time: " + gameMinutes + " minutes and " + gameSeconds + " seconds");
        }
    }

    public int getRealWorldDurationInSeconds() {
        return realWorldDurationInSeconds;
    }

    public void setRealWorldDurationInSeconds(int realWorldDurationInSeconds) {
        this.realWorldDurationInSeconds = realWorldDurationInSeconds;
    }

}

// Need to better understand exactly what inAppTime is doing, can we debug it to understand it?