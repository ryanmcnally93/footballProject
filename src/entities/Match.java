package entities;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import people.Footballer;
import people.Goalkeeper;
import visuals.CustomizedElements.PlayerAchievementLine;
import visuals.CustomizedElements.PlayerStatsBoxOnRatingsPage;
import visuals.CustomizedElements.PlayerStatsLineOnRatingsPage;
import visuals.CustomizedElements.TeamAchievementLine;
import visuals.MainMenuPages.MainMenuPageTemplate;
import visuals.MatchPages.MatchFrames;
import visuals.ScheduleFrames.Events;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;

public class Match {

	protected int homeAllShots;
	protected int homeShotsOn;
	protected int awayAllShots;
	protected int awayShotsOn;
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
	private ArrayList<Match> laterMatches, sameDayMatches, earlierMatches;
	private ArrayList<String> homeScorers, awayScorers;
	private boolean matchHasPlayed = false;
	private ArrayList<MatchEvent> matchEvents;
	private ArrayList<PlayerStatsLineOnRatingsPage> firstTeamsPlayersLines, secondTeamsPlayersLines;
	private ArrayList<PlayerStatsBoxOnRatingsPage> firstTeamsPlayersBoxes, secondTeamsPlayersBoxes;
	private UsersMatch simulatedMatch;
	private Map<Footballer, Integer> homeRatings, awayRatings;

	public Match() {}

	public Match(UsersMatch simulatedMatch) {
		this.simulatedMatch = simulatedMatch;
		this.home = simulatedMatch.getHome();
		this.away = simulatedMatch.getAway();
		this.stadium = simulatedMatch.getHome().getStadium();
		this.homeTeam = new HashMap<>(simulatedMatch.getHome().getFirstTeam());
		this.awayTeam = new HashMap<>(simulatedMatch.getAway().getFirstTeam());
		this.homegk = simulatedMatch.getHome().getGoalkeeper();
		this.awaygk = simulatedMatch.getAway().getGoalkeeper();
		this.awayScore = 0;
		this.homeScore = 0;
		this.league = simulatedMatch.getLeague();
		this.dateTime = simulatedMatch.getDateTime();
		this.timer = new MatchTimer();
		this.homeScorers = new ArrayList<>();
		this.awayScorers = new ArrayList<>();
		this.earlierMatches = simulatedMatch.getEarlierMatches();
		this.sameDayMatches = simulatedMatch.getSameDayMatches();
		this.laterMatches = simulatedMatch.getLaterMatches();
		this.homeAllShots = 0;
		this.homeShotsOn = 0;
		this.awayAllShots = 0;
		this.awayShotsOn = 0;
		simulated = true;
		this.matchEvents = new ArrayList<>();
		this.homeRatings = new HashMap<>();
		this.awayRatings = new HashMap<>();
	}

	public Match(Team home, Team away) {
		this.home = home;
		this.away = away;
		this.stadium = home.getStadium();
		this.homeTeam = new HashMap<>(home.getFirstTeam());
		this.awayTeam = new HashMap<>(away.getFirstTeam());
		this.homegk = home.getGoalkeeper();
		this.awaygk = away.getGoalkeeper();
		this.awayScore = 0;
		this.homeScore = 0;
		this.timer = new MatchTimer();
		this.homeScorers = new ArrayList<>();
		this.awayScorers = new ArrayList<>();
		this.earlierMatches = new ArrayList<>();
		this.sameDayMatches = new ArrayList<>();
		this.laterMatches = new ArrayList<>();
		this.homeAllShots = 0;
		this.homeShotsOn = 0;
		this.awayAllShots = 0;
		this.awayShotsOn = 0;
		this.homeRatings = new HashMap<>();
		this.awayRatings = new HashMap<>();
	}

	public Match(Team home, Team away, League league) {
		this.home = home;
		this.away = away;
		this.stadium = home.getStadium();
		this.homeTeam = new HashMap<>(home.getFirstTeam());
		this.awayTeam = new HashMap<>(away.getFirstTeam());
		this.homegk = home.getGoalkeeper();
		this.awaygk = away.getGoalkeeper();
		this.awayScore = 0;
		this.homeScore = 0;
		this.league = league;
		this.timer = new MatchTimer();
		this.homeScorers = new ArrayList<>();
		this.awayScorers = new ArrayList<>();
		this.earlierMatches = new ArrayList<>();
		this.sameDayMatches = new ArrayList<>();
		this.laterMatches = new ArrayList<>();
		this.homeRatings = new HashMap<>();
		this.awayRatings = new HashMap<>();
	}

	public Match(Team home, Team away, League league, LocalDateTime dateTime) {
		this.home = home;
		this.away = away;
		this.stadium = home.getStadium();
		this.homeTeam = new HashMap<>(home.getFirstTeam());
		this.awayTeam = new HashMap<>(away.getFirstTeam());
		this.homegk = home.getGoalkeeper();
		this.awaygk = away.getGoalkeeper();
		this.awayScore = 0;
		this.homeScore = 0;
		this.league = league;
		this.dateTime = dateTime;
		this.timer = new MatchTimer();
		this.homeScorers = new ArrayList<>();
		this.awayScorers = new ArrayList<>();
		this.earlierMatches = new ArrayList<>();
		this.sameDayMatches = new ArrayList<>();
		this.laterMatches = new ArrayList<>();
		this.homeAllShots = 0;
		this.homeShotsOn = 0;
		this.awayAllShots = 0;
		this.awayShotsOn = 0;
		this.homeRatings = new HashMap<>();
		this.awayRatings = new HashMap<>();
	}

	public void updateAllMatchesPage(){
		for(Match eachMatch : getSameDayMatches()){
			if(eachMatch instanceof UsersMatch usersMatch){
				eachMatch.getScheduler().getAllMatchesPanel().addTodaysMatchesToPage();
			}
		}
	}

	public void updateRatingsPage(Footballer player){}

	public void startRun(Footballer player) {
		// Inserted game time was 0

		while(getTimer().isPaused()){
			System.out.println("This match is paused...");
			displayTacticsButton();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

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

							// This may be accessed by multiple games at once, this stops concurrent modification errors
							List<PlayerAchievementLine> achievements = new CopyOnWriteArrayList<>(league.getPlayerLeaderboard().getPlayerAchievements());
							for (PlayerAchievementLine line : achievements) {
								if (line.getPlayer() == player) {
									line.addToGoals();
									break;
								}
							}

							player.addGoalToThisMatch();
							player.setRatingThisMatch(10);
							System.out.println(player.getName() + "'s rating has been set to 10");
							league.getLeagueTable().getLine(getAway()).addGoalsConceded();
							// This stops sameday matches scoring 1000 goals!
							if (backgroundGame) {
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
							player.addGoalToThisMatch();
							player.setRatingThisMatch(10);
							System.out.println(player.getName() + "'s rating has been set to 10");
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
							updateAllMatchesPage();
							updateRatingsPage(player);
						}

						updateScoreOnScreen();

						if (findTeam(player).equals("Home")) {
							Footballer awayStriker = awayTeam.get("ST");
							delayKickoffAfterGoal(awayStriker);
						} else {
							Footballer homeStriker = homeTeam.get("ST");
							delayKickoffAfterGoal(homeStriker);
						}
						
						
						return;
					} else {
						
						System.out.println("Keeper saved the shot");
						
						createGoalkeeperSaveEvent(player, thisFoeGk);
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

	public void displayTacticsButton() {}

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

	public void createGoalkeeperSaveEvent(Footballer player, Goalkeeper thisFoeGk) {
		if(findTeam(player).equals("Away")) {
			getMatchEvents().add(new MatchEvent("Away", "save", player, findRoundedInt(getTimer().getTime())));
		} else {
			getMatchEvents().add(new MatchEvent("Home", "save", player, findRoundedInt(getTimer().getTime())));
		}
	}

	public void delayKickoffAfterGoal(Footballer enemy) {
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

	public void displayAwayGoalOnScreen(Footballer player) {
		appendToTeamScorers(player, getTimer().getTime(), "Away");
		getMatchEvents().add(new MatchEvent("Away", "goal", player, findRoundedInt(getTimer().getTime())));
	}

	public void displayHomeGoalOnScreen(Footballer player) {
		appendToTeamScorers(player, getTimer().getTime(), "Home");
		getMatchEvents().add(new MatchEvent("Home", "goal", player, findRoundedInt(getTimer().getTime())));
	}

	public void appendToTeamScorers(Footballer player, String time, String side){
		ArrayList<String> scorers;
		if(side.equals("Home")){
			scorers = getHomeScorers();
		} else{
			scorers = getAwayScorers();
		}

		boolean bFound = false;
		String minutes;
		String updatedMinutes = "";

		int roundedUp = findRoundedInt(time);

		// Let's go through the right teams scorers
		for (int i = 0;i<scorers.size();i++) {
			String scorer = scorers.get(i);

			// Has this player already scored? Let's add the time to their String
			if (scorer.contains(player.getName()) ) {
				bFound = true;
				minutes = scorer.substring(0, scorer.length() - 1);
				updatedMinutes = minutes + ", " + roundedUp + ")";
				scorers.set(i, updatedMinutes);
				break;
			}
		}

		// This is the players first goal, so let's add their name and time
		if (!bFound) {
			scorers.add(player.getName() + "(" + roundedUp + ")");
		}

		if(side.equals("Home")){
			setHomeScorers(scorers);
		} else{
			setAwayScorers(scorers);
		}

		if(this instanceof UsersMatch) {
			getScheduler().getScorerPanel().displayGoalScorers(side, scorers);
		}
	}

	public int findRoundedInt(String time){
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
		return roundedUp;
	}

	public void goalAlertOnScreen(Footballer player) {}

	public void updateShotsOnScreen(Footballer player) {
		if(findTeam(player).equals("Away")) {
			this.awayShotsOn++;
			this.awayAllShots++;
		} else {
			this.homeShotsOn++;
			this.homeAllShots++;
		}
	}

	public boolean isInMiddleOfMatch(){
		return !getTimer().getTime().equals("90:00") && !getTimer().getTime().equals("00:00");
	}

	public boolean getPastPlayer(Footballer player, Footballer otherPlayer) {
		
		if (player.stamina <= 0) {
			otherPlayer.addToDuelsWon();
			return false;
		}
		
		// If the opponent is out of juice, we run past them
		if (otherPlayer.stamina <= 0) {
			otherPlayer.addToDuelsLost();
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
				otherPlayer.addToDuelsLost();
                return true;
				// Successful run past
            } else {
				otherPlayer.addToDuelsWon();
				return false;
			}
		}
	}
	
	public boolean takeShot(Footballer player, Goalkeeper gk) {
		System.out.println(player.getName() + " is having a crack!");
		int overall = player.attack + gk.getKeeping();
		float odds = overall / 100.0F;
		float perc = player.attack / odds;
		
		// Here we have a random number between 1-100
		double randomNumber = Math.floor(Math.random()*100);
		
		/* If 30 percent, and the random number resides within
		the first 30 numbers, we will run past. */
		player.addToShotOnTarget();
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

			// Should make the bar green
			if(!getSpeed().equals("instant")) {
				updateAllMatchesPage();
			}

			System.out.println("*****" + getHome().getName() + " " + getHomeScore() + " - " + getAwayScore() + " " + getAway().getName() + "*****");
			if (!(this instanceof UsersMatch)) {
				matchHasPlayed = true;
				saveRatingsAndRefreshPlayerData();
			}
			if(scheduler != null){
				scheduler.getEventContainer().removeAll();
				// We only want this on a simulated match
				if(simulated) {
					Events simulatedResult = new Events("Result", getScore(), getDateTime().plusHours(2));
					System.out.println("Adding an event in match class (for simulated matches)");
					scheduler.getEvents().add(simulatedResult);

					continueToScheduler();
					simulated = false;
				}
			}
			
			continueButtonOnScreen();
			return true;
	    } else {
			System.out.println("Full time checked, but it is " + getTimer().getTime());
	    	return false;
	    }
	}

	public void continueToScheduler() {
		if (!simulated) {
			for (Map.Entry<String, JPanel> eachPage : getScheduler().getMatchFramesMap().entrySet()) {
				MatchFrames matchPage = (MatchFrames) eachPage.getValue();
				matchPage.removeMatchFramesContentWhenLeavingMatch();
				matchPage.getFooterPanel().getMiddleBox().remove(matchPage.getContinueButton());
			}
			getScheduler().getStatsPanel().getFooterPanel().getMiddleBox().add(getScheduler().getStatsPanel().getPlayButton());
			saveRatingsAndRefreshPlayerData();
			for (Map.Entry<String, Footballer> eachPlayer : scheduler.getTeam().getFirstTeam().entrySet()) {
				// this player was switched during game, need to set his position placed back to normal
				if (!eachPlayer.getKey().equals(eachPlayer.getValue().getPositionPlaced())) {
					eachPlayer.getValue().setPositionPlaced(eachPlayer.getKey());
				}
			}
			scheduler.getFirstTeam().chooseLinesToView(scheduler.getTeam().getFormation().getPositionOrder());
		}
		// Give 1st place message if user is now 1st
		if(getLeague().getLeagueTable().getLine(getScheduler().getTeam()).getPosition() == 1) {
			getScheduler().addFirstPositionMessage();
		}

		getScheduler().getMyFixtures().getLine(this).gameComplete();
		getScheduler().getWindow().getContentPane().removeAll();
		getScheduler().displayPage(getScheduler().getWindow());
		getScheduler().refreshMessages();

		getLeague().getPlayerLeaderboard().updateLinesInTableLogic("Goals");

		getScheduler().getWindow().revalidate();
		getScheduler().getWindow().repaint();
		// Play whatever later matches we have
		if(getLaterMatches() != null && !getLaterMatches().isEmpty()){
			System.out.println("Playing todays Later Matches");
			for(Match eachMatch : getLaterMatches()){
				CompletableFuture.runAsync(() -> eachMatch.startMatch("instant"));
			}
		}

		// Set the back button on tactics cardmap to normal
		for (Map.Entry<String, JPanel> eachTacticsPage : getScheduler().getTacticsMap().entrySet()) {
			((MainMenuPageTemplate) eachTacticsPage.getValue()).setFromScheduler(true);
		}

		if (simulatedMatch != null) {
			giveSimulatedMatchAttributesOfThis();
		}
		matchHasPlayed = true;
		if (this instanceof UsersMatch) {
			callUpdateTableVisually();
		}
	}

	public void saveRatingsAndRefreshPlayerData() {
		// Set all the player ratings for this match, so we can view later
		for (Map.Entry<String, Footballer> eachPlayer : getHomeTeam().entrySet()) {
			Footballer player = eachPlayer.getValue();
			System.out.println(player.getName() + "'s rating on checking is " + player.getRatingThisMatch());
			homeRatings.put(player, player.getRatingThisMatch());
		}
		for (Map.Entry<String, Footballer> eachPlayer : getAwayTeam().entrySet()) {
			Footballer player = eachPlayer.getValue();
			System.out.println(player.getName() + "'s rating on checking is " + player.getRatingThisMatch());
			awayRatings.put(player, player.getRatingThisMatch());
		}

		for(Map.Entry<String, Footballer> each : getHomeTeam().entrySet()) {
			Footballer player = each.getValue();
			player.newMatchReset();
		}
		getHomegk().newMatchReset();

		for(Map.Entry<String, Footballer> each : getAwayTeam().entrySet()) {
			Footballer player = each.getValue();
			player.newMatchReset();
		}
		getAwaygk().newMatchReset();
	}

	public void giveSimulatedMatchAttributesOfThis() {
		simulatedMatch.setHomeScore(homeScore);
		simulatedMatch.setAwayScore(awayScore);
		simulatedMatch.setHomeShotsOn(homeShotsOn);
		simulatedMatch.setAwayShotsOn(awayShotsOn);
		simulatedMatch.setHomeAllShots(homeAllShots);
		simulatedMatch.setAwayAllShots(awayAllShots);
		simulatedMatch.setMatchEvents(matchEvents);
		simulatedMatch.setHomeScorers(homeScorers);
		simulatedMatch.setAwayScorers(awayScorers);
		simulatedMatch.setHomeRatings(homeRatings);
		simulatedMatch.setAwayRatings(awayRatings);
		simulatedMatch.setMatchHasPlayed(true);
		simulatedMatch.setTimer(getTimer());
	}

	public void callUpdateTableVisually() {};
	
	public void continueButtonOnScreen() {};

	public void startMatch(String speed) {
		this.speed = speed;
		replacePlayButtonWithPauseButton();
		addMatchPlayed();
		initialSetup();
    }

	public void startMatch(String speed, Boolean backgroundGame) {
		this.speed = speed;
		this.backgroundGame = backgroundGame;
		replacePlayButtonWithPauseButton();
		addMatchPlayed();
		initialSetup();
	}

	public void replacePlayButtonWithPauseButton() {};

	// For simulated matches
	public void startMatch(Scheduler scheduler, String speed) {
		this.scheduler = scheduler;
		this.speed = speed;
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

	public void appendLaterMatches(Match newMatch) {
		this.laterMatches.add(newMatch);
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

	public Boolean getBackgroundGame() {
		return backgroundGame;
	}

	public void setBackgroundGame(Boolean backgroundGame) {
		this.backgroundGame = backgroundGame;
	}

	public ArrayList<String> getHomeScorers() {
		return homeScorers;
	}

	public void setHomeScorers(ArrayList<String> homeScorers) {
		this.homeScorers = homeScorers;
	}

	public ArrayList<String> getAwayScorers() {
		return awayScorers;
	}

	public void setAwayScorers(ArrayList<String> awayScorers) {
		this.awayScorers = awayScorers;
	}

	public void appendSameDayMatches(Match newMatch) {
		this.sameDayMatches.add(newMatch);
	}

	public ArrayList<Match> getEarlierMatches() {
		return earlierMatches;
	}

	public void appendEarlierMatches(Match newMatch) {
		this.earlierMatches.add(newMatch);
	}

	public boolean isMatchHasPlayed() {
		return matchHasPlayed;
	}

	public ArrayList<MatchEvent> getMatchEvents() {
		return matchEvents;
	}

	public void setMatchEvents(ArrayList<MatchEvent> matchEvents) {
		this.matchEvents = matchEvents;
	}

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

	public void setMatchHasPlayed(boolean matchHasPlayed) {
		this.matchHasPlayed = matchHasPlayed;
	}

	public void setEarlierMatches(ArrayList<Match> earlierMatches) {
		this.earlierMatches = earlierMatches;
	}

	public void setSameDayMatches(ArrayList<Match> sameDayMatches) {
		this.sameDayMatches = sameDayMatches;
	}

	public void setLaterMatches(ArrayList<Match> laterMatches) {
		this.laterMatches = laterMatches;
	}

	public UsersMatch getSimulatedMatch() {
		return simulatedMatch;
	}

	public void setSimulatedMatch(UsersMatch simulatedMatch) {
		this.simulatedMatch = simulatedMatch;
	}

	public Map<Footballer, Integer> getHomeRatings() {
		return homeRatings;
	}

	public void setHomeRatings(Map<Footballer, Integer> homeRatings) {
		this.homeRatings = homeRatings;
	}

	public Map<Footballer, Integer> getAwayRatings() {
		return awayRatings;
	}

	public void setAwayRatings(Map<Footballer, Integer> awayRatings) {
		this.awayRatings = awayRatings;
	}
}
