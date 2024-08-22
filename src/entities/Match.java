package entities;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import people.Footballer;
import people.Goalkeeper;
import visuals.ScheduleFrames.Events;
import visuals.ScheduleFrames.Scheduler;

public class Match {
	
	private Team home;
	private Team away;
	private String stadium;
	private int homeScore;
	private int awayScore;
	private Map<String, Footballer> homeTeam;
	private Map<String, Footballer> awayTeam;
	private int minute;
	private Goalkeeper homegk;
	private Goalkeeper awaygk;
    private LocalDateTime dateTime;
    private League league;
	private Scheduler scheduler;
	
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
		this.awayScore = 0;
		this.homeScore = 0;
	}

	public Match(Team home, Team away, League league) {
		this.home = home;
		this.away = away;
		this.stadium = home.getStadium();
		this.homeTeam = home.getFirstTeam();
		this.awayTeam = away.getFirstTeam();
		this.minute = 0;
		this.homegk = home.getGoalkeeper();
		this.awaygk = away.getGoalkeeper();
		this.awayScore = 0;
		this.homeScore = 0;
		this.league = league;
	}
	
	public Match(Team home, Team away, League league, LocalDateTime dateTime) {
		this.home = home;
		this.away = away;
		this.stadium = home.getStadium();
		this.homeTeam = home.getFirstTeam();
		this.awayTeam = away.getFirstTeam();
		this.minute = 0;
		this.homegk = home.getGoalkeeper();
		this.awaygk = away.getGoalkeeper();
		this.awayScore = 0;
		this.homeScore = 0;
		this.league = league;
		this.dateTime = dateTime;
	}

	/* This is effectively a start game method,
	first time round is run with Ryan
	but whoever wins the ball continues a run of their own */
	public void startRun(Footballer player) {
		// Making sure the game is under 90 minutes.
		// Inserted game time was 0
		System.out.println("Started run with " + player.getName());
		
		int enemyCounter = 0;
		
		Map<String, Footballer> thisPlayersEnemy;
		Goalkeeper thisFoeGk;
		
		System.out.println("Get's to team setters");
		
		if(getAwayTeam().containsValue(player)) {
			thisPlayersEnemy = getHomeTeam();
			thisFoeGk = getHomegk();
		} else {
			thisPlayersEnemy = getAwayTeam();
			thisFoeGk = getAwaygk();
		}
		
		System.out.println("Get's to the enemies team");
		
		for (Map.Entry<String, Footballer> each : thisPlayersEnemy.entrySet()) {
			// Run the function to see if the dribble was successful
			Footballer enemy = each.getValue();
			
			System.out.println("Trying to run past player");
			
			if (getPastPlayer(player, enemy) == true) {
				enemyCounter++;
				// Increment minute and do a full time check
				
				System.out.println(enemyCounter);
				
				addMinute();
				System.out.println("First Fulltime check");
				if(fullTimeCheck()) {return;};

				System.out.println("Checking number of player ran past");
				
				if (enemyCounter == 3) {
					enemyCounter = 0;
					updateShotsOnScreen(player);
					
					System.out.println("About to take shot");
					
					if(takeShot(player, thisFoeGk) == true) {
						
						// SCORED
						System.out.println("There's been a goal");
						goalAlertOnScreen(player);
						// Create the score card and print
						if (findTeam(player) == "Home") {
							this.homeScore++;
							displayHomeGoalOnScreen(player);
							league.getLeagueTable().getLine(getHome()).addGoalsScored();
							league.getLeagueTable().getLine(getAway()).addGoalsConceded();
						} else {
							this.awayScore++;
							displayAwayGoalOnScreen(player);
							league.getLeagueTable().getLine(getAway()).addGoalsScored();
							league.getLeagueTable().getLine(getHome()).addGoalsConceded();
						}
						
						updateScoreOnScreen();

						if (findTeam(player) == "Home") {
							Footballer awayStriker = ((Footballer) awayTeam.get("ST"));
							addTimerForScreen(awayStriker);
						} else {
							Footballer homeStriker = ((Footballer) homeTeam.get("ST"));
							addTimerForScreen(homeStriker);
						}
						
						
						return;
					} else {
						
						System.out.println("Keeper saved the shot");
						
						displaySavesToScreen(player, thisFoeGk);
						if (findTeam(player) == "Home") {
							Footballer awayStriker = ((Footballer) awayTeam.get("ST"));
							startRun(awayStriker);
						} else {
							Footballer homeStriker = ((Footballer) homeTeam.get("ST"));
							startRun(homeStriker);
						}
						return;
					}
				}
			} else {
				
				System.out.println("Defender won the ball");
				
				addMinute();
				System.out.println("Second Fulltime check");
				if(fullTimeCheck()) {return;};
				startRun(enemy);
				return;
			}			
		}
		
		// Ensure that the method always returns.
	    return;
	}

	public void displaySavesToScreen(Footballer player, Goalkeeper thisFoeGk) {}

	public void addTimerForScreen(Footballer enemy) {
		Timer timer = new Timer();
		int delay = 0;
		Match match = this;
		timer.schedule(new TimerTask() {
		    @Override
		    public void run() {
		    	match.startRun(enemy);
		    }
		}, delay);
	}

	public void updateScoreOnScreen() {}

	public void displayAwayGoalOnScreen(Footballer player) {}

	public void displayHomeGoalOnScreen(Footballer player) {}

	public void goalAlertOnScreen(Footballer player) {}

	public void updateShotsOnScreen(Footballer player) {}

	public boolean getPastPlayer(Footballer player, Footballer otherPlayer) {
		
		if (player.stamina <= 0) {
			return false;
		}
		
		// If the opponent is out of juice, we run past them
		if (otherPlayer.stamina <= 0) {
			// After we make a successful run, we have less energy
			player.removeStamina(10);
			// Check to see we haven't run out of stamina
			if (player.stamina <= 0) {
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
	
	public boolean fullTimeCheck() {
		System.out.println("Checking full time, current minute = " + this.getMinute());
		if (this.getMinute() >= 90) {
			System.out.println("*****" + getHome().getName() + " " + getHomeScore() + " - " + getAwayScore() + " " + getAway().getName() + "*****");
			if(league != null) {
				if(getHomeScore() > getAwayScore()) {
					league.getLeagueTable().getLine(getHome()).addWin();
					league.getLeagueTable().getLine(getAway()).addLoss();
				} else if (getAwayScore() > getHomeScore()) {
					league.getLeagueTable().getLine(getHome()).addLoss();
					league.getLeagueTable().getLine(getAway()).addWin();
				} else {
					league.getLeagueTable().getLine(getHome()).addDraw();
					league.getLeagueTable().getLine(getAway()).addDraw();
				}
				league.getLeagueTable().updateLinesInTableLogic();
				callUpdateTableVisually();
			}
			System.out.println("Running Check");
			if(scheduler != null){
				scheduler.getEventContainer().removeAll();
				scheduler.getEvents().remove(this);
				Events simulatedResult = new Events("Result", getScore(), getDateTime().plusHours(2));
				scheduler.getEvents().add(simulatedResult);
				scheduler.refreshMessages();
				scheduler.getEventContainer().repaint();
				scheduler.getSouth().revalidate();
				scheduler.getSouth().repaint();
				System.out.println("I've tried!");
			}
			
			continueButtonOnScreen();
			return true;
	    } else {
	    	return false;
	    }
	}
	
	public void callUpdateTableVisually() {};
	
	public void continueButtonOnScreen() {};

	public void startMatch() {
		initialSetup();
    }

	public void startMatch(Scheduler scheduler) {
		this.scheduler = scheduler;
		initialSetup();
	}

	public void initialSetup(){
		for(Map.Entry<String, Footballer> each : getHomeTeam().entrySet()) {
			Footballer player = each.getValue();
			player.setStamina(100);
			getHomegk().setStamina(100);
		}

		for(Map.Entry<String, Footballer> each : getAwayTeam().entrySet()) {
			Footballer player = each.getValue();
			player.setStamina(100);
			getAwaygk().setStamina(100);
		}

		Footballer homeStriker = ((Footballer) homeTeam.get("ST"));
		startRun(homeStriker);
	}
	
	// Getters & Setters
	
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

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public  void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
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
	
	public Goalkeeper getHomegk() {
		return homegk;
	}

	public Goalkeeper getAwaygk() {
		return awaygk;
	}

	public static void main(String[] args) {
		
	}

	public String getScore(){
		return getHome() + " " + getHomeScore() + " - " + getAwayScore() + " " + getAway();
	}
	
	public void setDateTime(LocalDateTime datetime) {
		this.dateTime = datetime;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}
	
}