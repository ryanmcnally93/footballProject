package visuals;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import javax.sql.rowset.serial.SQLOutputImpl;
import javax.swing.JPanel;

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
	
	public Match() {};
	
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
	public void startRun(Footballer player, Graphics g, Map<String, JPanel> cardMap) {
		// Making sure the game is under 90 minutes.
		// Inserted game time was 0
		if(fullTimeCheck(cardMap)) {return;};
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
				if(fullTimeCheck(cardMap)) {return;};
				// Print the successful dribble if not full time
				System.out.println(player.getName() + " sprinted past " + enemy.getName());
				
				// Check to see if this is the last defender
				// This is where the issue persists
				if (enemyCounter == 3) {
					
					if(takeShot(player, thisFoeGk) == true) {
						// Confirm goal
						for (JPanel page : cardMap.values()) {
				            if (page instanceof MatchFrames) {
				                ((MatchFrames) page).goalAlert(player.getName(), this.getMinute());
				            }
				        }
						((MatchEvents) cardMap.get("Events")).displayGoal(player.getName() + " scores for " + player.getTeam() + " in the " + this.getMinute() + "th minute!");
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
						
						scoreUpdate();
						
						// ******
						
						Timer timer = new Timer();
						int delay = 5000;
						Match match = this;
						timer.schedule(new TimerTask() {
						    @Override
						    public void run() {
						    	match.startRun(player, g, cardMap);
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
	
	// Ran out of stamina message
	public void outOfStamina(Footballer player) {
		System.out.println(player.getName() + " has run out of stamina");
	}
	
	public void scoreUpdate() {
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
	
	public boolean fullTimeCheck(Map<String, JPanel> cardMap) {
		if (this.getMinute() >= 90) {
	        System.out.println("\nFull time!");
	        lastScoreUpdate(cardMap);
	        return true;
	    } else {
	    	return false;
	    }
	}
	
	public void lastScoreUpdate(Map<String, JPanel> cardMap) {
		// Score Update
		System.out.print("The score is\nArsenal: " + getHomeScore() + " ");
		((MatchEvents) cardMap.get("Events")).displayGoal("The score is\nArsenal: " + getHomeScore() + " ");
		for (String score : getHomeScorers()) {
			System.out.print(score + " ");
			((MatchEvents) cardMap.get("Events")).displayGoal(score + " ");
		}
		System.out.print("\nTottenham: " + getAwayScore() + " ");
		((MatchEvents) cardMap.get("Events")).displayGoal("\nTottenham: " + getAwayScore() + " ");
		for (String score : getAwayScorers()) {
			System.out.print(score + " ");
			((MatchEvents) cardMap.get("Events")).displayGoal(score + "");
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
	
	public void startMatch(Graphics g, Map<String, JPanel> cardMap) {
    	System.out.println("You are starting the match");
    	Goalkeeper raya = new Goalkeeper("David Raya", 31, 30000, 150, "Arsenal", "Goalkeeper");
    	Footballer jesus = new Footballer("Gabriel Jesus", 31, 30000, 180, 30, 100, "Arsenal", "Striker");
		Footballer trossard = new Footballer("Leandro Trossard", 31, 30000, 200, 40, 100, "Arsenal", "Winger");
		Footballer saka = new Footballer("Bukayo Saka", 31, 30000, 180, 65, 100, "Arsenal", "Winger");
		Footballer partey = new Footballer("Thomas Partey", 31, 30000, 90, 180, 100, "Arsenal", "Midfielder");
		Footballer odegaard = new Footballer("Martin Odegaard", 31, 30000, 180, 120, 100, "Arsenal", "Midfielder");
		Footballer rice = new Footballer("Declan Rice", 31, 30000, 110, 190, 100, "Arsenal", "Midfielder");
		Footballer tomiyasu = new Footballer("Takehiro Tomiyasu", 31, 30000, 45, 215, 100, "Arsenal", "Defender");
		Footballer saliba = new Footballer("William Saliba", 31, 30000, 45, 260, 100, "Arsenal", "Defender");
		Footballer gabriel = new Footballer("Gabriel Magalhaes", 31, 30000, 45, 245, 100, "Arsenal", "Defender");
		Footballer white = new Footballer("Ben White", 31, 30000, 75, 215, 100, "Arsenal", "Defender");
		
    	Goalkeeper vicario = new Goalkeeper("Guglielmo Vicario", 31, 30000, 130, "Tottenham", "Goalkeeper");
		Footballer johnson = new Footballer("Brennan Johnson", 31, 30000, 180, 30, 100, "Tottenham", "Striker");
		Footballer son = new Footballer("Heung-Min Son", 31, 30000, 200, 40, 100, "Tottenham", "Winger");
		Footballer kulusevski = new Footballer("Dejan Kulusevski", 31, 30000, 180, 65, 100, "Tottenham", "Winger");
		Footballer maddison = new Footballer("James Maddison", 31, 30000, 90, 180, 100, "Tottenham", "Midfielder");
		Footballer bissouma = new Footballer("Yves Bissouma", 31, 30000, 180, 120, 100, "Tottenham", "Midfielder");
		Footballer sarr = new Footballer("Pape Matar Sarr", 31, 30000, 110, 190, 100, "Tottenham", "Midfielder");
		Footballer udogie = new Footballer("Destiny Udogie", 31, 30000, 45, 215, 100, "Tottenham", "Defender");
		Footballer vanDeVen = new Footballer("Micky van de Ven", 31, 30000, 45, 260, 100, "Tottenham", "Defender");
		Footballer romero = new Footballer("Cristian Romero", 31, 30000, 45, 245, 100, "Tottenham", "Defender");
		Footballer porro = new Footballer("Guglielmo Vicario", 31, 30000, 75, 215, 100, "Tottenham", "Defender");
		
		ArrayList<Footballer> arsenal = new ArrayList<Footballer>();
		arsenal.add(jesus);
		arsenal.add(trossard);
		arsenal.add(saka);
		arsenal.add(odegaard);
		arsenal.add(partey);
		arsenal.add(rice);
		arsenal.add(tomiyasu);
		arsenal.add(gabriel);
		arsenal.add(saliba);
		arsenal.add(white);
		ArrayList<Footballer> tottenham = new ArrayList<Footballer>();
		tottenham.add(johnson);
		tottenham.add(son);
		tottenham.add(kulusevski);
		tottenham.add(maddison);
		tottenham.add(bissouma);
		tottenham.add(sarr);
		tottenham.add(udogie);
		tottenham.add(vanDeVen);
		tottenham.add(romero);
		tottenham.add(porro);
		
		Match match = new Match(arsenal, tottenham, raya, vicario);
		
		match.startRun(jesus, g, cardMap);
    }

	public void setHomeTeam(ArrayList<Footballer> homeTeam) {
		this.homeTeam = homeTeam;
	}

	public void setAwayTeam(ArrayList<Footballer> awayTeam) {
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
	
}
