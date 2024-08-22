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
    private Scheduler schedule;
	
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
		
        watchPanel = new MatchWatch(layout, matchPages, cardMap, this);
        scorerPanel = new MatchScorers(layout, matchPages, cardMap, this);
        statsPanel = new MatchStats(layout, matchPages, cardMap, this);
        eventsPanel = new MatchEvents(layout, matchPages, cardMap, this);
        allMatchesPanel = new MatchAllMatches(layout, matchPages, cardMap, this);
        tablePanel = new MatchTable(layout, matchPages, cardMap, this);
        ratingsPanel = new MatchRatings(layout, matchPages, cardMap, this);

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

        // Initialize with the main page, this will change multiple times
	}
	
	public void displayGame(GameWindow window, Scheduler schedule) {
		this.window = window;
		this.schedule = schedule;
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
		for (JPanel page : cardMap.values()) {
			if (page instanceof MatchFrames) {
            	((MatchFrames) page).goalAlert(player.getName(), this.getMinute());
            }
        }
	}
						
	@Override
	public void displayHomeGoalOnScreen(Footballer player) {
		((MatchEvents) cardMap.get("Events")).addHomeEvents(getMinute(), player, "goal");
		((MatchScorers) cardMap.get("Scorers")).displayGoalScorers(player, getMinute(), "Home");
	}
	
	@Override
	public void displayAwayGoalOnScreen(Footballer player) {
		((MatchEvents) cardMap.get("Events")).addAwayEvents(getMinute(), player, "goal");
		((MatchScorers) cardMap.get("Scorers")).displayGoalScorers(player, getMinute(), "Away");
	}
	
	@Override
	public void updateScoreOnScreen() {
		for (JPanel page : cardMap.values()) {
            if (page instanceof MatchFrames) {
            	((MatchFrames) page).getHeaderPanel().updateScoreBoard(getHomeScore(), getAwayScore());
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
		if(findTeam(player) == "Away") {
			((MatchEvents) cardMap.get("Events")).addAwayEvents(getMinute(), player, "save");
		} else {
			((MatchEvents) cardMap.get("Events")).addHomeEvents(getMinute(), player, "save");
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

	public Scheduler getSchedule() {
		return schedule;
	}

	public void setSchedule(Scheduler schedule) {
		this.schedule = schedule;
	}
}
