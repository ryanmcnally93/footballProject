package visuals;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import javax.sql.rowset.serial.SQLOutputImpl;
import java.util.Timer;
import people.Footballer;
import people.Goalkeeper;

public class Match {
	
	private int homeScore;
	private int awayScore;
	private ArrayList<Footballer> homeTeam;
	private ArrayList<Footballer> awayTeam;
	private int minute;
	public ArrayList<String> homeScorers;
	public ArrayList<String> awayScorers;
	private Goalkeeper homegk;
	private Goalkeeper awaygk;
	
	public Match(ArrayList<Footballer> homeTeam, ArrayList<Footballer> awayTeam, Goalkeeper hgk, Goalkeeper agk) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.minute = 0;
		this.homeScorers = new ArrayList<String>();
		this.awayScorers = new ArrayList<String>();
		this.homeScore = 0;
		this.awayScore = 0;
		this.homegk = hgk;
		this.awaygk = agk;
	}

	public Goalkeeper getHomegk() {
		return homegk;
	}

	public Goalkeeper getAwaygk() {
		return awaygk;
	}

	public static void main(String[] args) {
		
	}
	
	/* This is effectively a start game method,
	first time round is run with Ryan
	but whoever wins the ball continues a run of their own */
	public void startRun(Footballer player, Graphics g, GameWindow gw) {
		// Making sure the game is under 90 minutes.
		// Inserted game time was 0
		if(fullTimeCheck(gw)) {return;};
		int enemyCounter = 0;
		
		ArrayList<Footballer> thisPlayersEnemy;
		Goalkeeper thisFoeGk;
		
		if(getAwayTeam().contains(player)) {
			thisPlayersEnemy = getHomeTeam();
			thisFoeGk = getHomegk();
		} else {
			thisPlayersEnemy = getAwayTeam();
			thisFoeGk = getAwaygk();
		}
		
		for (Footballer enemy : thisPlayersEnemy) {
			// Run the function to see if the dribble was successful
			if (getPastPlayer(player, enemy) == true) {
				enemyCounter++;
				// Increment minute and do a full time check
				addMinute();
				if(fullTimeCheck(gw)) {return;};
				// Print the successful dribble if not full time
				System.out.println(player.getName() + " sprinted past " + enemy.getName());
				
				// Check to see if this is the last defender
				// This is where the issue persists
				if (enemyCounter == 3) {
					
					if(takeShot(player, thisFoeGk) == true) {
						// Confirm goal
						gw.displayGoal(player.getName() + " scores for " + player.getTeam() + " in the " + this.getMinute() + "th minute!");
						System.out.println(player.getName() + " scores for " + player.getTeam() + " in the " + this.getMinute() + "th minute!");
						
						// Create the score card and print
						if (player.getTeam() == "Arsenal") {
							homeScore++;
							boolean aFound = false;
							for (int i = 0;i<getHomeScorers().size();i++) {
								String scorer = getHomeScorers().get(i);
								
								if (scorer.contains(player.getName()) ) {
									aFound = true;
									// Update scorer
									String minutes = scorer.substring(0, scorer.length() - 1);
									String updatedMinutes = minutes + ", " + this.getMinute() + ")";
									this.homeScorers.set(i, updatedMinutes);
									break;
								}
								
							}
							if (!aFound) {
								this.homeScorers.add(player.getName() + "(" + getMinute() + ")");
							}
						} else {
							setAwayScore(getAwayScore());
							boolean tFound = false;
							for (int i = 0;i<getAwayScorers().size();i++) {
								String scorer = getAwayScorers().get(i);
								
								if (scorer.contains(player.getName()) ) {
									tFound = true;
									// Update scorer
									String minutes = scorer.substring(0, scorer.length() - 1);
									String updatedMinutes = minutes + ", " + getMinute() + ")";
									this.awayScorers.set(i, updatedMinutes);
									break;
								}
								
							}
							if (!tFound) {
								this.awayScorers.add(player.getName() + "(" + getMinute() + ")");
							}
						}
						
						scoreUpdate(gw);
						
						// ******
						
						Timer timer = new Timer();
						int delay = 2000;
						Match match = this;
						timer.schedule(new TimerTask() {
						    @Override
						    public void run() {
						    	match.startRun(player, g, gw);
						    }
						}, delay);
						
						// ******

						return;
					} else {
						System.out.println("Brilliant save by " + thisFoeGk.getName() + " to deny " + player.getName());
					}
				}
			} else {
				addMinute();
				if(fullTimeCheck(gw)) {return;};
			    
				System.out.println(player.getName() + " has conceded posession to " + enemy.getName());
				startRun(enemy, g, gw);
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
	
	// Ran out of stamina message
	public void outOfStamina(Footballer player) {
		System.out.println(player.getName() + " has run out of stamina");
	}
	
	public void scoreUpdate(GameWindow gw) {
		// Score Update
		System.out.print("The score is\nArsenal: " + getHomeScore() + " ");
		for (String score : getHomeScorers()) {
			System.out.print(score + " ");
		}
		System.out.print("\nTottenham: " + getAwayScore() + " ");
				
		for (String score : getAwayScorers()) {
			System.out.print(score + " ");
		};
		System.out.println();
	}
	
	public boolean fullTimeCheck(GameWindow gw) {
		if (this.getMinute() >= 90) {
	        System.out.println("\nFull time!");
	        lastScoreUpdate(gw);
	        return true;
	    } else {
	    	return false;
	    }
	}
	
	public void lastScoreUpdate(GameWindow gw) {
		// Score Update
		System.out.print("The score is\nArsenal: " + getHomeScore() + " ");
		gw.displayGoal("The score is\nArsenal: " + getHomeScore() + " ");
		for (String score : getHomeScorers()) {
			System.out.print(score + " ");
			gw.displayGoal(score + " ");
		}
		System.out.print("\nTottenham: " + getAwayScore() + " ");
		gw.displayGoal("\nTottenham: " + getAwayScore() + " ");
		for (String score : getAwayScorers()) {
			System.out.print(score + " ");
			gw.displayGoal(score + "");
		};
		System.out.println();
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int score) {
		this.homeScore += 1;
	}
	
	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int score) {
		this.awayScore += 1;
	}

	public ArrayList<Footballer> getHomeTeam() {
		return this.homeTeam;
	}
	
	public ArrayList<Footballer> getAwayTeam() {
		return this.awayTeam;
	}

	// Work on a function to return a striker once team is given
	public ArrayList<Footballer> getRandomStriker(ArrayList<Footballer> team) {
		ArrayList<Footballer> strikers = new ArrayList<Footballer>();
		for (Footballer player : getAwayTeam()) {
			if (player.getPosition() == "Striker") {
				strikers.add(player);
			}
		}
		return strikers;
	}
	
	public ArrayList<Footballer> getAwayMidfielders() {
		ArrayList<Footballer> strikers = new ArrayList<Footballer>();
		for (Footballer player : getAwayTeam()) {
			if (player.getPosition() == "Striker") {
				strikers.add(player);
			}
		}
		return strikers;
	}
	
	public ArrayList<Footballer> getAwayDefenders() {
		ArrayList<Footballer> strikers = new ArrayList<Footballer>();
		for (Footballer player : getAwayTeam()) {
			if (player.getPosition() == "Striker") {
				strikers.add(player);
			}
		}
		return strikers;
	}
	
	public int getMinute() {
		return minute;
	}

	public void addMinute() {
		this.minute += 1;
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
	
}
