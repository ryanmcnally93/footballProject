package entities;

import java.time.LocalDateTime;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.CompletableFuture;
import javax.swing.*;

import people.Footballer;
import people.Goalkeeper;
import gameSetup.GameWindow;
import visuals.MatchPages.MatchPageTemplate;

public class UsersMatch extends Match {

	private GameWindow window;
	private final Timer delayTimer = new Timer();
	private String currentPageName;

	public UsersMatch() {};

    // Used for Users Games from Scheduler when starting a game
	public UsersMatch(Team home, Team away, League league, LocalDateTime dateTime, int matchWeek) {
		super(home, away, league, dateTime, matchWeek);
		setSpeed("slowest");
	}

    // Used to map Matches to UsersMatches when viewing on Fixtures Page
    public UsersMatch(Match match) {
        super(match.getHome(), match.getAway(), match.getLeague(), match.getDateTime(), match.getMatchWeek());
        setSpeed(match.getSpeed());
        setAwaygk(match.getAwaygk());
        setHomegk(match.getHomegk());
        setAwayAllShots(match.getAwayAllShots());
        setHomeAllShots(match.getHomeAllShots());
        setAwayRatings(match.getAwayRatings());
        setHomeRatings(match.getHomeRatings());
        setAwayScore(match.getAwayScore());
        setHomeScore(match.getHomeScore());
        setAwayScorers(match.getAwayScorers());
        setHomeScorers(match.getHomeScorers());
        setAwayShotsOn(match.getAwayShotsOn());
        setHomeShotsOn(match.getHomeShotsOn());
        setHomeTeam(match.getHomeTeam());
        setAwayTeam(match.getAwayTeam());
        setBackgroundGame(match.getBackgroundGame());
        setDateTime(match.getDateTime());
        setEarlierMatches(match.getEarlierMatches());
        setLaterMatches(match.getLaterMatches());
        setLeague(match.getLeague());
        setMatchEvents(match.getMatchEvents());
        setSameDayMatches(match.getSameDayMatches());
        setScheduler(match.getScheduler());
        setSimulated(match.getSimulated());
        setSimulatedMatch(match.getSimulatedMatch());
        setStadium(match.getStadium());
        setTimer(match.getTimer());
        if (match.isMatchFinished()) {
           markFinished();
        }
    }

	@Override
	public void updateAllMatchesPage(){
		getScheduler().getAllMatchesPanel().addTodaysMatchesToPage();
	}

	@Override
	public void callUpdateTableVisually() {
		getScheduler().getTablePanel().updateTableVisually();
		if (isMatchFinished()) {
			getScheduler().getTeamStandings().updateTableVisually();
		}
	};

	@Override
	public void displayTacticsButton() {
		getScheduler().getStatsPanel().displayTacticsButton();
	}

	@Override
	public void updateShotsOnScreen(Footballer player) {
		super.updateShotsOnScreen(player);
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
			if (page instanceof MatchPageTemplate) {
            	((MatchPageTemplate) page).goalAlert(player.getName(), String.valueOf(roundedUp));
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
		super.displayHomeGoalOnScreen(player);
		getScheduler().getEventsPanel().addHomeEvents(getTimer().getTime(), player, "goal");
	}
	
	@Override
	public void displayAwayGoalOnScreen(Footballer player) {
		super.displayAwayGoalOnScreen(player);
		getScheduler().getEventsPanel().addAwayEvents(getTimer().getTime(), player, "goal");
	}
	
	@Override
	public void updateScoreOnScreen() {
		for (JPanel page : getScheduler().getMatchFramesMap().values()) {
            if (page instanceof MatchPageTemplate) {
            	((MatchPageTemplate) page).updateScoreBoard(getHomeScore(), getAwayScore());
            }
        }
	}

	@Override
	public void updateRatingsPage(Footballer player){
		getScheduler().getRatingsPanel().updateLineAfterMatchEvent(player);
		getScheduler().getRatingsPanel().updateBox(player);
	}
	
	@Override
	public void delayKickoffAfterGoal(Footballer enemy) {
		int delay = 7000;
		System.out.println("Delaying Kickoff after goal for UsersMatch");
		UsersMatch match = this;
		this.delayTimer.schedule(new TimerTask() {
		    @Override
		    public void run() {
				System.out.println("Delay has finished, going to start a run now");
				if(!match.fullTimeCheck()){
					match.startRun(enemy);
				}
		    }
		}, delay);
	}
	
	@Override
	public void createGoalkeeperSaveEvent(Footballer player, Goalkeeper thisFoeGk) {
		super.createGoalkeeperSaveEvent(player, thisFoeGk);
		if(findTeam(player).equals("Away")) {
			getScheduler().getEventsPanel().addAwayEvents(getTimer().getTime(), player, "save");
		} else {
			getScheduler().getEventsPanel().addHomeEvents(getTimer().getTime(), player, "save");
		}
	}
		
	@Override
	public void continueButtonOnScreen() {
		if (getSimulated()) {
			return;
		}
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
            if (page instanceof MatchPageTemplate) {
            	((MatchPageTemplate) page).createContinueButton();
            }
        }
	}

	@Override
	public void replacePlayButtonWithPauseButton() {
		for (JPanel page : getScheduler().getMatchFramesMap().values()) {
			if (page instanceof MatchPageTemplate) {
				((MatchPageTemplate) page).replacePlayButtonWithPauseButton();
			}
		}
	}

	// Getters & Setters

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
