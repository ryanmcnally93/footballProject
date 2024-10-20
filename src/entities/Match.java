package entities;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;

import people.Footballer;
import people.Goalkeeper;
import visuals.CustomizedElements.PlayerAchievementLine;
import visuals.CustomizedElements.TeamAchievementLine;
import visuals.MatchFrames.MatchFrames;
import visuals.ScheduleFrames.Events;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;

public class Match {
	
	private Team home;
	private Team away;
	private String stadium;
	private int homeScore;
	private int awayScore;
	private Map<String, Footballer> homeTeam;
	private Map<String, Footballer> awayTeam;
	private Goalkeeper homegk;
	private Goalkeeper awaygk;
    private LocalDateTime dateTime;
    private League league;
	private Scheduler scheduler;
	private Boolean simulated = false;
	private Boolean backgroundGame = false;
	private MatchTimer timer;
	private String speed;
	private ArrayList<Match> laterMatches, sameDayMatches;
	
	public Match() {}
	
	public Match(Team home, Team away) {
		this.home = home;
		this.away = away;
		this.stadium = home.getStadium();
		this.homeTeam = home.getFirstTeam();
		this.awayTeam = away.getFirstTeam();
		this.homegk = home.getGoalkeeper();
		this.awaygk = away.getGoalkeeper();
		this.awayScore = 0;
		this.homeScore = 0;
		this.timer = new MatchTimer();
	}

	public Match(Team home, Team away, League league) {
		this.home = home;
		this.away = away;
		this.stadium = home.getStadium();
		this.homeTeam = home.getFirstTeam();
		this.awayTeam = away.getFirstTeam();
		this.homegk = home.getGoalkeeper();
		this.awaygk = away.getGoalkeeper();
		this.awayScore = 0;
		this.homeScore = 0;
		this.league = league;
		this.timer = new MatchTimer();
	}
	
	public Match(Team home, Team away, League league, LocalDateTime dateTime) {
		this.home = home;
		this.away = away;
		this.stadium = home.getStadium();
		this.homeTeam = home.getFirstTeam();
		this.awayTeam = away.getFirstTeam();
		this.homegk = home.getGoalkeeper();
		this.awaygk = away.getGoalkeeper();
		this.awayScore = 0;
		this.homeScore = 0;
		this.league = league;
		this.dateTime = dateTime;
		this.timer = new MatchTimer();
	}

	public void startRun(Footballer player) {
		// Inserted game time was 0
		System.out.println("Started run with " + player.getName());
		
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
		
		System.out.println("Get's to the enemies team");
		
		for (Map.Entry<String, Footballer> each : thisPlayersEnemy.entrySet()) {
			// Run the function to see if the dribble was successful
			Footballer enemy = each.getValue();
			
			System.out.println("Trying to run past player");
			
			if (getPastPlayer(player, enemy)) {
				enemyCounter++;
				// Increment minute and do a full time check

				if(fullTimeCheck()) {return;}

				System.out.println("Checking number of player ran past");
				
				if (enemyCounter == 3) {
					updateShotsOnScreen(player);
					
					System.out.println("About to take shot");
					
					if(takeShot(player, thisFoeGk)) {
						
						// SCORED
						System.out.println("There's been a goal");

						// This triggers the slider
						goalAlertOnScreen(player);

						int oldHomeScore = getHomeScore();
						int oldAwayScore = getAwayScore();

						// Create the score card and print
						if (findTeam(player).equals("Home")) {
							System.out.println("HOME GOAL");
							this.homeScore++;
							displayHomeGoalOnScreen(player);
							league.getLeagueTable().getLine(getHome()).addGoalsScored();
							for(PlayerAchievementLine line : league.getPlayerLeaderboard().getPlayerAchievements()){
								if(line.getPlayer() == player){
									line.addToGoals();
									break;
								};
							}
							league.getLeagueTable().getLine(getAway()).addGoalsConceded();
							// This stops sameday matches scoring 1000 goals!
							if(backgroundGame) {
								try {
									Thread.sleep(5000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						} else {
							System.out.println("AWAY GOAL");
							this.awayScore++;
							displayAwayGoalOnScreen(player);
							league.getLeagueTable().getLine(getAway()).addGoalsScored();
							for(PlayerAchievementLine line : league.getPlayerLeaderboard().getPlayerAchievements()){
								if(line.getPlayer() == player){
									line.addToGoals();
									break;
								};
							}
							league.getLeagueTable().getLine(getHome()).addGoalsConceded();
							// This stops sameday matches scoring 1000 goals!
							if(backgroundGame) {
								try {
									Thread.sleep(5000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}

						// We only want live goal alerts on non instant matches
						if(!getSpeed().equals("instant")) {
							goalUpdatesTable(oldHomeScore, oldAwayScore);
						}

						updateScoreOnScreen();

						if (findTeam(player).equals("Home")) {
							Footballer awayStriker = awayTeam.get("ST");
							addTimerForScreen(awayStriker);
						} else {
							Footballer homeStriker = homeTeam.get("ST");
							addTimerForScreen(homeStriker);
						}
						
						
						return;
					} else {
						
						System.out.println("Keeper saved the shot");
						
						displaySavesToScreen(player, thisFoeGk);
						if (findTeam(player).equals("Home")) {
							Footballer awayStriker = awayTeam.get("ST");
							startRun(awayStriker);
						} else {
							Footballer homeStriker = homeTeam.get("ST");
							startRun(homeStriker);
						}
						return;
					}
				}
			} else {
				
				System.out.println("Defender won the ball");

				if(fullTimeCheck()) {return;};
				startRun(enemy);
				return;
			}			
		}

    }

	public void goalUpdatesTable(int oldHome, int oldAway) {
		System.out.println("RUNNING goalUpdatesTable METHOD");
		TeamAchievementLine home = league.getLeagueTable().getLine(getHome());
		TeamAchievementLine away = league.getLeagueTable().getLine(getAway());

		if(oldHome == 0 && oldAway == 0 && getHomeScore() > getAwayScore()){
			home.addWin();
			away.addLoss();
		} else if (oldHome == 0 && oldAway == 0 && getHomeScore() < getAwayScore()){
			home.addLoss();
			away.addWin();
		} else {
			if (oldHome == oldAway && getHomeScore() > getAwayScore()) {
				home.addWin();
				home.removeDraw();
				away.addLoss();
				away.removeDraw();
			} else if (oldHome == oldAway && getHomeScore() < getAwayScore()) {
				home.addLoss();
				home.removeDraw();
				away.addWin();
				away.removeDraw();
			} else if (oldHome > oldAway && getAwayScore() == getHomeScore()) {
				home.addDraw();
				home.removeWin();
				away.addDraw();
				away.removeLoss();
			} else if (oldHome < oldAway && getAwayScore() == getHomeScore()) {
				home.addDraw();
				home.removeLoss();
				away.addDraw();
				away.removeWin();
			}
		}
		updateDomesticLeagueTable();
	}

	public void updateDomesticLeagueTable(){
		System.out.println("RUNNING updateDomesticLeagueTable METHOD");
		getLeague().getLeagueTable().updateLinesInTableLogic();
		callUpdateTableVisually();
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
//				player.removeStamina(10);
				// Check to see we haven't run out of stamina
                return player.stamina > 0;
				// Successful run past
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
        return randomNumber <= perc;
	}
	
	public String findTeam(Footballer player) {
		if(getAwayTeam().containsValue(player)) {
			return "Away";
		} else {
			return "Home";
		}
	}
	
	public boolean fullTimeCheck() {
		System.out.println("RUNNING fullTimeCheck METHOD");
		if (getTimer().getTime().equals("90:00")) {
			System.out.println("It's fulltime");

			System.out.println("*****" + getHome().getName() + " " + getHomeScore() + " - " + getAwayScore() + " " + getAway().getName() + "*****");

			if(scheduler != null){
				scheduler.getEventContainer().removeAll();
				// We only want this on a simulated match
				if(simulated) {
					Events simulatedResult = new Events("Result", getScore(), getDateTime().plusHours(2));
					System.out.println("Adding an event in match class (for simulated matches)");
					scheduler.getEvents().add(simulatedResult);
					// Give 1st place message if user is now 1st
					if(league.getLeagueTable().getLine(scheduler.getTeam()).getPosition() == 1) {
						scheduler.addFirstPositionMessage();
					}
					if(getLaterMatches() != null){
						System.out.println("Playing todays Later Matches");
						for(Match eachMatch : getLaterMatches()){
							CompletableFuture.runAsync(() -> eachMatch.startMatch("instant"));
						}
					}
					scheduler.refreshMessages();
				}
				// Refresh messages once match is played
				scheduler.getEventContainer().repaint();
				scheduler.getSouth().revalidate();
				scheduler.getSouth().repaint();
			}
			
			continueButtonOnScreen();
			return true;
	    } else {
			System.out.println("Full time checked, but it is " + getTimer().getTime());
	    	return false;
	    }
	}

	public void callUpdateTableVisually() {};
	
	public void continueButtonOnScreen() {};

	public void startMatch(String speed) {
		this.speed = speed;
		removePlayButton();
		addMatchPlayed();
		initialSetup();
    }

	public void startMatch(String speed, Boolean backgroundGame) {
		this.speed = speed;
		this.backgroundGame = backgroundGame;
		removePlayButton();
		addMatchPlayed();
		initialSetup();
	}

	public void removePlayButton() {};

	// For simulated matches
	public void startMatch(Scheduler scheduler, Boolean bool, String speed, ArrayList<Match> laterMatches) {
		this.scheduler = scheduler;
		this.simulated = true;
		this.speed = speed;
		this.laterMatches = laterMatches;
		addMatchPlayed();
		initialSetup();
	}

	public void addMatchPlayed() {
		TeamAchievementLine home = league.getLeagueTable().getLine(getHome());
		TeamAchievementLine away = league.getLeagueTable().getLine(getAway());
		home.addMatchPlayed();
		away.addMatchPlayed();
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

		Footballer homeStriker = homeTeam.get("ST");
		startTimer();
		startRun(homeStriker);
	}

	public void startTimer(){
		getTimer().runEvent(getSpeed(), this);
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

	public void setHomeTeam(Map<String, Footballer> homeTeam) {
		this.homeTeam = homeTeam;
	}

	public void setAwayTeam(Map<String, Footballer> awayTeam) {
		this.awayTeam = awayTeam;
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

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public Boolean getSimulated() {
		return simulated;
	}

	public void setSimulated(Boolean simulated) {
		this.simulated = simulated;
	}

	public MatchTimer getTimer() {
		return timer;
	}

	public void setTimer(MatchTimer timer) {
		this.timer = timer;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public ArrayList<Match> getLaterMatches() {
		return laterMatches;
	}

	public void setLaterMatches(ArrayList<Match> laterMatches) {
		this.laterMatches = laterMatches;
	}

	public void updateWinsDrawsAndLossesForInstantMatches() {

		TeamAchievementLine home = league.getLeagueTable().getLine(getHome());
		TeamAchievementLine away = league.getLeagueTable().getLine(getAway());

		if(getHomeScore() > getAwayScore()){
			home.addWin();
			away.addLoss();
		} else if (getHomeScore() < getAwayScore()){
			home.addLoss();
			away.addWin();
		} else {
			home.addDraw();
			away.addDraw();
		}

	}

	public ArrayList<Match> getSameDayMatches() {
		return sameDayMatches;
	}

	public void setSameDayMatches(ArrayList<Match> sameDayMatches) {
		this.sameDayMatches = sameDayMatches;
	}
}
