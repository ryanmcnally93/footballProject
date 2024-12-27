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
import visuals.CustomizedElements.CustomizedButton;
import visuals.MatchPages.*;
import visuals.ScheduleFrames.Scheduler;

public class UsersMatch extends Match {
	
	private int homeAllShots;
	private int homeShotsOn;
	private int awayAllShots;
	private int awayShotsOn;
	private CardLayout layout;
    private JPanel matchPages;
    private Map<String, JPanel> cardMap;
    private MatchWatch watchPanel;
    private MatchScorers scorerPanel;
    private MatchStats statsPanel;
    private MatchEvents eventsPanel;
    private MatchAllMatches allMatchesPanel;
    private MatchTable tablePanel;
    private MatchRatings ratingsPanel;
    private GameWindow window;
	private final Timer delayTimer = new Timer();
	private Speedometer speedometer;
	private String currentPageName;
	private CustomizedButton pauseButton, resumeButton, tacticsButton;
	
	public UsersMatch() {};
	
	public UsersMatch(Team home, Team away) {
		super(home, away);
		this.homeAllShots = 0;
		this.homeShotsOn = 0;
		this.awayAllShots = 0;
		this.awayShotsOn = 0;
		
		initialize();
	}

	public UsersMatch(Team home, Team away, League league, LocalDateTime dateTime) {
		super(home, away, league, dateTime);
		this.homeAllShots = 0;
		this.homeShotsOn = 0;
		this.awayAllShots = 0;
		this.awayShotsOn = 0;
		setSpeed("slowest");
		initialize();
	}
	
	public void initialize() {
        cardMap = new HashMap<>();
        layout = new CardLayout();
        matchPages = new JPanel(layout);

		speedometer = new Speedometer();
		ArrayList<CustomizedButton> buttons = new ArrayList<CustomizedButton>();

		// Add Pause and Resume Buttons here
		pauseButton = new CustomizedButton("Pause");
		pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttons.add(pauseButton);

		resumeButton = new CustomizedButton("Resume");
		resumeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttons.add(resumeButton);

		tacticsButton = new CustomizedButton("Tactics");
		tacticsButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		buttons.add(tacticsButton);

        watchPanel = new MatchWatch(layout, matchPages, this, speedometer, buttons);
        scorerPanel = new MatchScorers(layout, matchPages, this, speedometer, buttons);
        statsPanel = new MatchStats(layout, matchPages, this, speedometer, buttons);
        eventsPanel = new MatchEvents(layout, matchPages, this, speedometer, buttons);
        allMatchesPanel = new MatchAllMatches(layout, matchPages, this, speedometer, buttons);
        tablePanel = new MatchTable(layout, matchPages, this, speedometer, buttons);
        ratingsPanel = new MatchRatings(layout, matchPages, this, speedometer, buttons);

        // Add MatchFrame instances to the MatchFrames main panel
        
        matchPages.add(watchPanel, "Watch");
        cardMap.put("Watch", watchPanel);
        matchPages.add(scorerPanel, "Scorers"); // Updated
        cardMap.put("Scorers", scorerPanel);
        matchPages.add(statsPanel, "Stats"); // Updated
        cardMap.put("Stats", statsPanel);
        matchPages.add(eventsPanel, "Events"); // Updated
        cardMap.put("Events", eventsPanel);
        matchPages.add(allMatchesPanel, "All Matches"); // Updated
        cardMap.put("All Matches", allMatchesPanel);
        matchPages.add(tablePanel, "Table"); // Updated
        cardMap.put("Table", tablePanel);
        matchPages.add(ratingsPanel, "Ratings");
        cardMap.put("Ratings", ratingsPanel);

	}
	
	public void displayGame(GameWindow window, Scheduler schedule) {
		this.window = window;
		setScheduler(schedule);
		window.getContentPane().removeAll();
		window.getContentPane().add(matchPages, BorderLayout.CENTER);
		statsPanel.getSpeedometerBox().add(speedometer);
        layout.show(matchPages, "Stats");
		currentPageName = "Stats";

		if(!getSameDayMatches().contains(this)){
			getSameDayMatches().add(this);
		}

		if(getTimer().isPaused()) {
			statsPanel.getFooterPanel().getMiddleBox().add(statsPanel.getResumeButton());
			statsPanel.displayTacticsButton();
		}

		updateAllMatchesPage();
		updateDomesticLeagueTable();

		window.revalidate();
		window.repaint();
	}

	@Override
	public void updateAllMatchesPage(){
		allMatchesPanel.addTodaysMatchesToPage();
	}

	@Override
	public void callUpdateTableVisually() {
		tablePanel.updateTableVisually();
	};

	@Override
	public void displayTacticsButton() {
		((MatchFrames) cardMap.get("Stats")).displayTacticsButton();
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
		((MatchStats) cardMap.get("Stats")).updateShotsOnBar(getHomeShotsOn(), getAwayShotsOn());
		((MatchStats) cardMap.get("Stats")).updateAllShotsBar(getHomeAllShots(), getAwayAllShots());
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

		for (JPanel page : cardMap.values()) {
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
		((MatchEvents) cardMap.get("Events")).addHomeEvents(getTimer().getTime(), player, "goal");
	}
	
	@Override
	public void displayAwayGoalOnScreen(Footballer player) {
		appendToTeamScorers(player, getTimer().getTime(), "Away");
		((MatchEvents) cardMap.get("Events")).addAwayEvents(getTimer().getTime(), player, "goal");
	}
	
	@Override
	public void updateScoreOnScreen() {
		for (JPanel page : cardMap.values()) {
            if (page instanceof MatchFrames) {
            	((MatchFrames) page).updateScoreBoard(getHomeScore(), getAwayScore());
            }
        }
	}

	@Override
	public void updateRatingsPage(Footballer player){
		for (JPanel page : cardMap.values()) {
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
			((MatchEvents) cardMap.get("Events")).addAwayEvents(getTimer().getTime(), player, "save");
		} else {
			((MatchEvents) cardMap.get("Events")).addHomeEvents(getTimer().getTime(), player, "save");
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

		for (JPanel page : cardMap.values()) {
            if (page instanceof MatchFrames) {
            	((MatchFrames) page).createContinueButton();
            }
        }
	}

	@Override
	public void replacePlayButtonWithPauseButton() {
		for (JPanel page : cardMap.values()) {
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

	public CardLayout getLayout() {
		return layout;
	}

	public void setLayout(CardLayout layout) {
		this.layout = layout;
	}

	public JPanel getMatchPages() {
		return matchPages;
	}

	public void setMatchPages(JPanel matchPages) {
		this.matchPages = matchPages;
	}

	public Map<String, JPanel> getCardMap() {
		return cardMap;
	}

	public void setCardMap(Map<String, JPanel> cardMap) {
		this.cardMap = cardMap;
	}

	public MatchWatch getWatchPanel() {
		return watchPanel;
	}

	public void setWatchPanel(MatchWatch watchPanel) {
		this.watchPanel = watchPanel;
	}

	public MatchScorers getScorerPanel() {
		return scorerPanel;
	}

	public void setScorerPanel(MatchScorers scorerPanel) {
		this.scorerPanel = scorerPanel;
	}

	public MatchStats getStatsPanel() {
		return statsPanel;
	}

	public void setStatsPanel(MatchStats statsPanel) {
		this.statsPanel = statsPanel;
	}

	public MatchEvents getEventsPanel() {
		return eventsPanel;
	}

	public void setEventsPanel(MatchEvents eventsPanel) {
		this.eventsPanel = eventsPanel;
	}

	public MatchAllMatches getAllMatchesPanel() {
		return allMatchesPanel;
	}

	public void setAllMatchesPanel(MatchAllMatches allMatchesPanel) {
		this.allMatchesPanel = allMatchesPanel;
	}

	public MatchTable getTablePanel() {
		return tablePanel;
	}

	public void setTablePanel(MatchTable tablePanel) {
		this.tablePanel = tablePanel;
	}

	public MatchRatings getRatingsPanel() {
		return ratingsPanel;
	}

	public void setRatingsPanel(MatchRatings ratingsPanel) {
		this.ratingsPanel = ratingsPanel;
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
