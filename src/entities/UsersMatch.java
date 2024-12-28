package entities;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.CompletableFuture;
import javax.swing.*;

import people.Footballer;
import people.Goalkeeper;
import main.GameWindow;
import visuals.MatchPages.*;
import visuals.ScheduleFrames.Scheduler;

public class UsersMatch extends Match {
	
	private int homeAllShots;
	private int homeShotsOn;
	private int awayAllShots;
	private int awayShotsOn;
    private GameWindow window;
	private final Timer delayTimer = new Timer();
	private String currentPageName;
	
	public UsersMatch() {};
	
	public UsersMatch(Team home, Team away) {
		super(home, away);
		this.homeAllShots = 0;
		this.homeShotsOn = 0;
		this.awayAllShots = 0;
		this.awayShotsOn = 0;
	}

	public UsersMatch(Team home, Team away, League league, LocalDateTime dateTime) {
		super(home, away, league, dateTime);
		this.homeAllShots = 0;
		this.homeShotsOn = 0;
		this.awayAllShots = 0;
		this.awayShotsOn = 0;
		setSpeed("slowest");
	}

	@Override
	public void updateAllMatchesPage(){
		getScheduler().getAllMatchesPanel().addTodaysMatchesToPage();
	}

	@Override
	public void callUpdateTableVisually() {
		getScheduler().getTablePanel().updateTableVisually();
	};

	@Override
	public void displayTacticsButton() {
		getScheduler().getStatsPanel().displayTacticsButton();
	}

	@Override
	public void updateShotsOnScreen(Footballer player) {
		if(findTeam(player).equals("Away")) {
			this.awayShotsOn++;
			this.awayAllShots++;
		} else {
			this.homeShotsOn++;
			this.homeAllShots++;
		}
		getScheduler().getStatsPanel().updateShotsOnBar(getHomeShotsOn(), getAwayShotsOn());
		getScheduler().getStatsPanel().updateAllShotsBar(getHomeAllShots(), getAwayAllShots());
	}

	@Override
	public void goalAlertOnScreen(Footballer player) {

		String time = getTimer().getTime();
		int roundedUp = 0;

		// This sets gives us the time, rounded up to the next minute
		try {
			if(time.startsWith("0:")){
				roundedUp = 1;
			} else {
				int newTime = Integer.parseInt(time.substring(0, 2));
				roundedUp = newTime + 1;
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid number format: " + time.substring(0, 2));
		}

		for (JPanel page : getScheduler().getMatchFramesMap().values()) {
			if (page instanceof MatchFrames) {
            	((MatchFrames) page).goalAlert(player.getName(), String.valueOf(roundedUp));
            }
        }
	}

	@Override
	public void startMatch(String speed) {
		replacePlayButtonWithPauseButton();
		addMatchPlayed();
		for(Match eachMatch : getSameDayMatches()){
			CompletableFuture.runAsync(() -> eachMatch.startMatch(speed, true));
		}
	}

	@Override
	public void startMatch(String speed, Boolean backgroundGame){
		initialSetup();
	}

	@Override
	public void displayHomeGoalOnScreen(Footballer player) {
		appendToTeamScorers(player, getTimer().getTime(), "Home");
		getScheduler().getEventsPanel().addHomeEvents(getTimer().getTime(), player, "goal");
	}
	
	@Override
	public void displayAwayGoalOnScreen(Footballer player) {
		appendToTeamScorers(player, getTimer().getTime(), "Away");
		getScheduler().getEventsPanel().addAwayEvents(getTimer().getTime(), player, "goal");
	}
	
	@Override
	public void updateScoreOnScreen() {
		for (JPanel page : getScheduler().getMatchFramesMap().values()) {
            if (page instanceof MatchFrames) {
            	((MatchFrames) page).updateScoreBoard(getHomeScore(), getAwayScore());
            }
        }
	}

	@Override
	public void updateRatingsPage(Footballer player){
		for (JPanel page : getScheduler().getMatchFramesMap().values()) {
			if (page instanceof MatchRatings) {
				((MatchRatings) page).updateLine(player);
				((MatchRatings) page).updateBox(player);
			}
		}
	}
	
	@Override
	public void addTimerForScreen(Footballer enemy) {
		int delay = 7000;
		UsersMatch match = this;
		this.delayTimer.schedule(new TimerTask() {
		    @Override
		    public void run() {
				if(!match.fullTimeCheck()){
					match.startRun(enemy);
				}
		    }
		}, delay);
	}
	
	@Override
	public void displaySavesToScreen(Footballer player, Goalkeeper thisFoeGk) {
		if(findTeam(player).equals("Away")) {
			getScheduler().getEventsPanel().addAwayEvents(getTimer().getTime(), player, "save");
		} else {
			getScheduler().getEventsPanel().addHomeEvents(getTimer().getTime(), player, "save");
		}
	}
		
	@Override
	public void continueButtonOnScreen() {
		int numberOfFinishedMatches = 0;
		int numberOfActualMatches = getSameDayMatches().size();
		while(numberOfFinishedMatches != numberOfActualMatches){
			numberOfFinishedMatches = 0;
			for(Match everyOtherMatch : getSameDayMatches()){
				if(everyOtherMatch.getTimer().getTime().equals("90:00")){
					numberOfFinishedMatches++;
				}
			}
		}

		for (JPanel page : getScheduler().getMatchFramesMap().values()) {
            if (page instanceof MatchFrames) {
            	((MatchFrames) page).createContinueButton();
            }
        }
	}

	@Override
	public void replacePlayButtonWithPauseButton() {
		for (JPanel page : getScheduler().getMatchFramesMap().values()) {
			if (page instanceof MatchFrames) {
				((MatchFrames) page).replacePlayButtonWithPauseButton();
			}
		}
	}

	// Getters & Setters

	public int getHomeAllShots() {
		return homeAllShots;
	}

	public void setHomeAllShots(int homeAllShots) {
		this.homeAllShots = homeAllShots;
	}

	public int getHomeShotsOn() {
		return homeShotsOn;
	}

	public void setHomeShotsOn(int homeShotsOn) {
		this.homeShotsOn = homeShotsOn;
	}

	public int getAwayAllShots() {
		return awayAllShots;
	}

	public void setAwayAllShots(int awayAllShots) {
		this.awayAllShots = awayAllShots;
	}

	public int getAwayShotsOn() {
		return awayShotsOn;
	}

	public void setAwayShotsOn(int awayShotsOn) {
		this.awayShotsOn = awayShotsOn;
	}

	public GameWindow getWindow() {
		return window;
	}

	public void setWindow(GameWindow window) {
		this.window = window;
	}

	public Timer getDelayTimer() {
		return delayTimer;
	}

	public String getCurrentPageName() {
		return currentPageName;
	}

	public void setCurrentPageName(String currentPageName) {
		this.currentPageName = currentPageName;
	}

	public String getPrevPageName(){
        switch (getCurrentPageName()) {
            case "Watch" -> {return "Ratings";}
            case "Scorers" -> {return "Watch";}
            case "Stats" -> {return "Scorers";}
            case "Events" -> {return "Stats";}
            case "All Matches" -> {return "Events";}
            case "Table" -> {return "All Matches";}
            case "Ratings" -> {return "Table";}
            default -> {
                System.out.println("You've entered a dodgy page name");
                return "Error 404";
            }
        }
	}

	public String getNextPageName(){
		switch (getCurrentPageName()) {
			case "Watch" -> {return "Scorers";}
			case "Scorers" -> {return "Stats";}
			case "Stats" -> {return "Events";}
			case "Events" -> {return "All Matches";}
			case "All Matches" -> {return "Table";}
			case "Table" -> {return "Ratings";}
			case "Ratings" -> {return "Watch";}
			default -> {
				System.out.println("You've entered a dodgy page name");
				return "Error 404";
			}
		}
	}
}
