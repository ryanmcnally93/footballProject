package general;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.util.Timer;
import people.Footballer;
import people.Goalkeeper;
import visuals.MatchFrames.GameWindow;
import visuals.MatchFrames.MatchAllMatches;
import visuals.MatchFrames.MatchEvents;
import visuals.MatchFrames.MatchFrames;
import visuals.MatchFrames.MatchRatings;
import visuals.MatchFrames.MatchScorers;
import visuals.MatchFrames.MatchStats;
import visuals.MatchFrames.MatchTable;
import visuals.MatchFrames.MatchWatch;
import visuals.ScheduleFrames.Scheduler;

public class Match {
	
	private Team home;
	private Team away;
	private String stadium;
	private static int homeScore = 0;
	private static int awayScore = 0;
	private Map<String, Footballer> homeTeam;
	private Map<String, Footballer> awayTeam;
	private int minute;
	private Goalkeeper homegk;
	private Goalkeeper awaygk;
	private static int homeAllShots = 0;
	private static int homeShotsOn = 0;
	private static int awayAllShots = 0;
	private static int awayShotsOn = 0;
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
    private LocalDateTime dateTime;
	
	public Match() {};
	
	public Match(Team home, Team away) {
		this.home = home;
		this.away = away;
		this.stadium = home.getStadium();
		this.homeTeam = home.getFirstTeam();
		this.awayTeam = away.getFirstTeam();
		this.minute = 0;
		this.homegk = home.getGoalkeeper();
		this.awaygk = away.getGoalkeeper();
		
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
		window.revalidate();
		window.repaint();
	}

	public Goalkeeper getHomegk() {
		return homegk;
	}

	public Goalkeeper getAwaygk() {
		return awaygk;
	}

	public static void main(String[] args) {
		
	}
	
	public void setDateTime(LocalDateTime datetime) {
		this.dateTime = datetime;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}


	/* This is effectively a start game method,
	first time round is run with Ryan
	but whoever wins the ball continues a run of their own */
	public void startRun(Footballer player, Graphics g, Map<String, JPanel> cardMap) {
		// Making sure the game is under 90 minutes.
		// Inserted game time was 0
		if(fullTimeCheck(cardMap)) {return;};
		int enemyCounter = 0;
		
		Map<String, Footballer> thisPlayersEnemy;
		Goalkeeper thisFoeGk;
		
		if(getAwayTeam().containsValue(player)) {
			thisPlayersEnemy = getHomeTeam();
			thisFoeGk = getHomegk();
		} else {
			thisPlayersEnemy = getAwayTeam();
			thisFoeGk = getAwaygk();
		}
		
		for (Map.Entry<String, Footballer> each : thisPlayersEnemy.entrySet()) {
			// Run the function to see if the dribble was successful
			Footballer enemy = each.getValue();
			if (getPastPlayer(player, enemy) == true) {
				enemyCounter++;
				// Increment minute and do a full time check
				addMinute();
				if(fullTimeCheck(cardMap)) {return;};
				// Print the successful dribble if not full time
				System.out.println(player.getName() + " sprinted past " + enemy.getName());
				
				// Check to see if this is the last defender
				// This is where the issue persists
				if (enemyCounter == 3) {
					
					if(findTeam(player) == "Away") {
						awayShotsOn++;
						awayAllShots++;
					} else {
						homeShotsOn++;
						homeAllShots++;
					}
					((MatchStats) cardMap.get("Stats")).updateShotsOnBar(getHomeShotsOn(), getAwayShotsOn());
					((MatchStats) cardMap.get("Stats")).updateAllShotsBar(getHomeAllShots(), getAwayAllShots());
					if(takeShot(player, thisFoeGk) == true) {
						
						// SCORED
						
						// Confirm goal
						for (JPanel page : cardMap.values()) {
				            if (page instanceof MatchFrames) {
				            	((MatchFrames) page).goalAlert(player.getName(), this.getMinute());
				            }
				        }
						// Create the score card and print
						if (findTeam(player) == "Home") {
							homeScore++;
							((MatchEvents) cardMap.get("Events")).addHomeEvents(getMinute(), player, "goal");
							((MatchScorers) cardMap.get("Scorers")).displayLeftGoalScorers(player, getMinute());
						} else {
							awayScore++;
							((MatchEvents) cardMap.get("Events")).addAwayEvents(getMinute(), player, "goal");
							((MatchScorers) cardMap.get("Scorers")).displayRightGoalScorers(player, getMinute());
						}
						
						for (JPanel page : cardMap.values()) {
				            if (page instanceof MatchFrames) {
				            	((MatchFrames) page).getHeaderPanel().updateScoreBoard(getHomeScore(), getAwayScore());
				            }
				        }
						
						// ******
						
						Timer timer = new Timer();
						int delay = 7000;
						Match match = this;
						timer.schedule(new TimerTask() {
						    @Override
						    public void run() {
						    	match.startRun(enemy, g, cardMap);
						    }
						}, delay);
						
						// ******

						return;
					} else {
						System.out.println("Brilliant save by " + thisFoeGk.getName() + " to deny " + player.getName());
						if(findTeam(player) == "Away") {
							((MatchEvents) cardMap.get("Events")).addAwayEvents(getMinute(), player, "save");
						} else {
							((MatchEvents) cardMap.get("Events")).addHomeEvents(getMinute(), player, "save");
						}
					}
				}
			} else {
				addMinute();
				if(fullTimeCheck(cardMap)) {return;};
			    
				System.out.println(player.getName() + " has conceded posession to " + enemy.getName());
				startRun(enemy, g, cardMap);
				return;
			}			
		}
		
		// Ensure that the method always returns.
	    return;
	}

	public boolean getPastPlayer(Footballer player, Footballer otherPlayer) {
		
		if (player.stamina <= 0) {
			outOfStamina(player);
			return false;
		}
		
		// If the opponent is out of juice, we run past them
		if (otherPlayer.stamina <= 0) {
			// After we make a successful run, we have less energy
			player.removeStamina(10);
			// Check to see we haven't run out of stamina
			if (player.stamina <= 0) {
				outOfStamina(player);
				return false;
			}
			// Successful run past
			return true;
		} else {
			
			/* This works out the likelihood percentage of
			the player getting past the defence. */
			int overall = player.attack + otherPlayer.defence;
			float odds = overall / 100.0F;
			float perc = player.attack / odds;
			
			// Here we have a random number between 1-100
			double randomNumber = Math.floor(Math.random()*100);
			
			/* If 30 percent, and the random number resides within
			the first 30 numbers, we will run past. */
			if (randomNumber <= perc) {
				// After we make a successful run, we have less energy
				player.removeStamina(10);
				// Check to see we haven't run out of stamina
				if (player.stamina <= 0) {
					outOfStamina(player);
					return false;
				}
				// Successful run past
				return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean takeShot(Footballer player, Goalkeeper gk) {
		int overall = player.attack + gk.getKeeping();
		float odds = overall / 100.0F;
		float perc = player.attack / odds;
		
		// Here we have a random number between 1-100
		double randomNumber = Math.floor(Math.random()*100);
		
		/* If 30 percent, and the random number resides within
		the first 30 numbers, we will run past. */
		if (randomNumber <= perc) {
			return true;
		} else {
			return false;
		}
	}
	
	public String findTeam(Footballer player) {
		if(getAwayTeam().containsValue(player)) {
			return "Away";
		} else {
			return "Home";
		}
	}
	
	// Ran out of stamina message
	public void outOfStamina(Footballer player) {
		System.out.println(player.getName() + " has run out of stamina");
	}
	
	public boolean fullTimeCheck(Map<String, JPanel> cardMap) {
		if (this.getMinute() >= 90) {
	        System.out.println("\nFull time!");
	        for (JPanel page : cardMap.values()) {
	            if (page instanceof MatchFrames) {
	            	((MatchFrames) page).createContinueButton();
	            }
	        }
	        return true;
	    } else {
	    	return false;
	    }
	}

	public int getHomeScore() {
		return homeScore;
	}
	
	public int getAwayScore() {
		return awayScore;
	}

	public Map<String, Footballer> getHomeTeam() {
		return this.homeTeam;
	}
	
	public Map<String, Footballer> getAwayTeam() {
		return this.awayTeam;
	}
	
	public int getMinute() {
		return minute;
	}

	public void addMinute() {
		this.minute += 1;
	}
	
	public void startMatch(Graphics g, Map<String, JPanel> cardMap) {
    	System.out.println("You are starting the match");
    	Footballer homeStriker = ((Footballer) homeTeam.get("ST"));
		startRun(homeStriker, g, cardMap);
    }

	public void setHomeTeam(Map<String, Footballer> homeTeam) {
		this.homeTeam = homeTeam;
	}

	public void setAwayTeam(Map<String, Footballer> awayTeam) {
		this.awayTeam = awayTeam;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public void setHomegk(Goalkeeper homegk) {
		this.homegk = homegk;
	}

	public void setAwaygk(Goalkeeper awaygk) {
		this.awaygk = awaygk;
	}

	public static int getHomeAllShots() {
		return homeAllShots;
	}

	public static void setHomeAllShots(int homeAllShots) {
		Match.homeAllShots = homeAllShots;
	}

	public static int getHomeShotsOn() {
		return homeShotsOn;
	}

	public static void setHomeShotsOn(int homeShotsOn) {
		Match.homeShotsOn = homeShotsOn;
	}

	public static int getAwayAllShots() {
		return awayAllShots;
	}

	public static void setAwayAllShots(int awayAllShots) {
		Match.awayAllShots = awayAllShots;
	}

	public static int getAwayShotsOn() {
		return awayShotsOn;
	}

	public static void setAwayShotsOn(int awayShotsOn) {
		Match.awayShotsOn = awayShotsOn;
	}

	public static void setHomeScore(int homeScore) {
		Match.homeScore = homeScore;
	}

	public static void setAwayScore(int awayScore) {
		Match.awayScore = awayScore;
	}

	public Team getHome() {
		return home;
	}

	public void setHome(Team home) {
		this.home = home;
	}

	public Team getAway() {
		return away;
	}

	public void setAway(Team away) {
		this.away = away;
	}
	
	public String toString() {
		return home.getName() + " vs " + away.getName();
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
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
