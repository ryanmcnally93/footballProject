package entities;
import java.time.LocalDateTime;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import javax.swing.JPanel;
import java.util.Timer;
import people.Footballer;
import people.Goalkeeper;
import main.GameWindow;
import visuals.MatchFrames.MatchAllMatches;
import visuals.MatchFrames.MatchEvents;
import visuals.MatchFrames.MatchFrames;
import visuals.MatchFrames.MatchRatings;
import visuals.MatchFrames.MatchScorers;
import visuals.MatchFrames.MatchStats;
import visuals.MatchFrames.MatchTable;
import visuals.MatchFrames.MatchWatch;
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
		
		initialize();
	}
	
	public void initialize() {
        cardMap = new HashMap<>();
        layout = new CardLayout();
        matchPages = new JPanel(layout);
		
        watchPanel = new MatchWatch(layout, matchPages, this);
        scorerPanel = new MatchScorers(layout, matchPages, this);
        statsPanel = new MatchStats(layout, matchPages, this);
        eventsPanel = new MatchEvents(layout, matchPages, this);
        allMatchesPanel = new MatchAllMatches(layout, matchPages, this);
        tablePanel = new MatchTable(layout, matchPages, this);
        ratingsPanel = new MatchRatings(layout, matchPages, this);

        // Add MatchFrame instances to the MatchFrames main panel
        
        matchPages.add(watchPanel, "Watch");
        cardMap.put("Watch", watchPanel);
        matchPages.add(scorerPanel, "Scorers");
        cardMap.put("Scorers", scorerPanel);
        matchPages.add(statsPanel, "Stats");
        cardMap.put("Stats", statsPanel);
        matchPages.add(eventsPanel, "Events");
        cardMap.put("Events", eventsPanel);
        matchPages.add(allMatchesPanel, "All Matches");
        cardMap.put("All Matches", allMatchesPanel);
        matchPages.add(tablePanel, "Table");
        cardMap.put("Table", tablePanel);
        matchPages.add(ratingsPanel, "Ratings");
        cardMap.put("Ratings", ratingsPanel);

	}
	
	public void displayGame(GameWindow window, Scheduler schedule) {
		this.window = window;
		setScheduler(schedule);
		window.getContentPane().removeAll();
		window.getContentPane().add(matchPages, BorderLayout.CENTER);
        layout.show(matchPages, "Stats");
        tablePanel.updateTableVisually();
		window.revalidate();
		window.repaint();
	}
	
	@Override
	public void callUpdateTableVisually() {
		tablePanel.updateTableVisually();
	};

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
			System.out.println("The integer value is: " + roundedUp);
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
	public void displayHomeGoalOnScreen(Footballer player) {
		((MatchEvents) cardMap.get("Events")).addHomeEvents(getTimer().getTime(), player, "goal");
		((MatchScorers) cardMap.get("Scorers")).displayGoalScorers(player, getTimer().getTime(), "Home");
	}
	
	@Override
	public void displayAwayGoalOnScreen(Footballer player) {
		((MatchEvents) cardMap.get("Events")).addAwayEvents(getTimer().getTime(), player, "goal");
		((MatchScorers) cardMap.get("Scorers")).displayGoalScorers(player, getTimer().getTime(), "Away");
	}

	@Override
	public void startTimer(){
		getTimer().runEvent("slowest", this);
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
	public void addTimerForScreen(Footballer enemy) {
		Timer timer = new Timer();
		int delay = 7000;
		UsersMatch match = this;
		timer.schedule(new TimerTask() {
		    @Override
		    public void run() {
		    	match.startRun(enemy);
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
		for (JPanel page : cardMap.values()) {
            if (page instanceof MatchFrames) {
            	((MatchFrames) page).createContinueButton();
            }
        }
	}

	@Override
	public void removePlayButton() {
		for (JPanel page : cardMap.values()) {
			if (page instanceof MatchFrames) {
				((MatchFrames) page).removePlayButton();
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

}
