import java.util.ArrayList;
import java.util.List;

public class Match {
	
	private int homeScore;
	private int awayScore;
	private ArrayList<Footballer> homeTeam;
	private ArrayList<Footballer> awayTeam;
	private int minute;
	public ArrayList<String> homeScorers;
	public ArrayList<String> awayScorers;
	
	public Match(ArrayList<Footballer> homeTeam, ArrayList<Footballer> awayTeam) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.minute = 0;
		this.homeScorers = new ArrayList<String>();
		this.awayScorers = new ArrayList<String>();
		this.homeScore = 0;
		this.awayScore = 0;
	}

	public static void main(String[] args) {
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
		
		Match match = new Match(arsenal, tottenham);
		match.startRun(jesus);
		
	}
	
	/* This is effectively a start game method,
	first time round is run with Ryan
	but whoever wins the ball continues a run of their own */
	public void startRun(Footballer player) {
		
		if(fullTimeCheck()) {return;};
		int enemyCounter = 0;
		
		
		// Making sure the game is under 90 minutes.
		// Inserted game time was 0
		for (Footballer enemy : getAwayTeam()) {
				
			// Run the function to see if the dribble was successful
			if (getPastPlayer(player, enemy) == true) {
				enemyCounter++;
				// Increment minute and do a full time check
				addMinute();
				if(fullTimeCheck()) {return;};
				// Print the successful dribble if not full time
				System.out.println(player.getName() + " sprinted past " + enemy.getName());
				
				// Check to see if this is the last defender
				// This is where the issue persists
				if (enemyCounter == 3) {
					// Confirm goal
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
					
					this.startRun(player);
					return;
				}
			} else {
				addMinute();
				if(fullTimeCheck()) {return;};
			    
				System.out.println(player.getName() + " has conceded posession to " + enemy.getName());
				startRun(enemy);
				return;
			}			
		}
		
		// Ensure that the method always returns.
	    return;
	}

	public boolean getPastPlayer(Footballer player, Footballer otherPlayer) {
		
		// If the opponent is out of juice, we run past them
		if (otherPlayer.stamina <= 0) {
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
				player.stamina -= 10;
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
	
	public boolean fullTimeCheck() {
		if (this.getMinute() >= 90) {
	        System.out.println("\nFull time!");
	        scoreUpdate();
	        return true;
	    } else {
	    	return false;
	    }
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
		Footballer striker;
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
