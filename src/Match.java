import java.util.ArrayList;
import java.util.List;

public class Match {
	
	private int score;
	private Footballer[] homeTeam;
	private Footballer[] awayTeam;
	private int minute;
	private ArrayList<String> homeScorers;
	private ArrayList<String> awayScorers;
	
	public Match(Footballer[] homeTeam, Footballer[] awayTeam) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.minute = 0;
		this.homeScorers = new ArrayList<>();
		this.awayScorers = new ArrayList<>();
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
		
		Footballer arsenal[] = {jesus, trossard, saka, odegaard, partey, rice, tomiyasu, gabriel, saliba, white};
		Footballer tottenham[] = {johnson, son, kulusevski, maddison, bissouma, sarr, udogie, vanDeVen, romero, porro};
		
		Match match = new Match(arsenal, tottenham);
		match.startRun(jesus, 0, 0);
		
	}
	
	/* This is effectively a start game method,
	first time round is run with Ryan
	but whoever wins the ball continues a run of their own */
	public void startRun(Footballer player, Footballer[] enemies, int arsenal, int tottenham, List<String> arsenalScorers, List<String> tottenhamScorers) {
		
		if(fullTimeCheck(this.getMinute(),arsenal,arsenalScorers,tottenham,tottenhamScorers)) {return;};
		
		// Making sure the game is under 90 minutes.
		// Inserted game time was 0
		for (Footballer enemy : enemies) {
				
			// Make sure I only run past opposition players
			if (enemy.getTeam() != player.getTeam()) {
				// Run the function to see if the dribble was successful
				if (getPastPlayer(player, enemy) == true) {
					// Increment minute and do a full time check
					addMinute();
					if(fullTimeCheck(this.getMinute(),arsenal,arsenalScorers,tottenham,tottenhamScorers)) {return;};
					// Print the successful dribble if not full time
					System.out.println(player.getName() + " sprinted past " + enemy.getName());
					// Check to see if this is the last defender
					if (enemy.getPosition() == "Defender") {
						// Confirm goal
						System.out.println(player.getName() + " scores for " + player.getTeam() + " in the " + this.getMinute() + "th minute!");
						
						// Create the score card and print
						if (player.getTeam() == "Arsenal") {
							arsenal++;
							boolean aFound = false;
							for (int i = 0;i<arsenalScorers.size();i++) {
								String scorer = arsenalScorers.get(i);
								
								if (scorer.contains(player.getName()) ) {
									aFound = true;
									// Update scorer
									scorer = scorer.substring(0, scorer.length() - 1);
									scorer = scorer + ", " + this.getMinute() + ")";
									arsenalScorers.set(i, scorer);
									break;
								}
								
							}
							if (!aFound) {
								arsenalScorers.add(player.getName() + "(" + getMinute() + ")");
							}
						} else {
							tottenham++;
							boolean tFound = false;
							for (int i = 0;i<tottenhamScorers.size();i++) {
								String scorer = tottenhamScorers.get(i);
								
								if (scorer.contains(player.getName()) ) {
									tFound = true;
									// Update scorer
									scorer = scorer.substring(0, scorer.length() - 1);
									scorer = scorer + ", " + getMinute() + ")";
									tottenhamScorers.set(i, scorer);
									break;
								}
								
							}
							if (!tFound) {
								tottenhamScorers.add(player.getName() + "(" + getMinute() + ")");
							}
						}
						
						scoreUpdate(arsenal, arsenalScorers, tottenham, tottenhamScorers);
						
						this.startRun(player, enemies, arsenal, tottenham, arsenalScorers, tottenhamScorers);
						return;
					}
				} else {
					addMinute();
					if(fullTimeCheck(this.getMinute(),arsenal,arsenalScorers,tottenham,tottenhamScorers)) {return;};
				    
					System.out.println(player.getName() + " has conceded posession to " + enemy.getName());
					startRun(enemy, enemies, arsenal, tottenham, arsenalScorers, tottenhamScorers);
					return;
				}
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
	
	public void scoreUpdate(int arsenal, List<String> arsenalScorers, int tottenham, List<String> tottenhamScorers) {
		// Score Update
		System.out.print("The score is\nArsenal: " + arsenal + " ");
		for (String score : arsenalScorers) {
			System.out.print(score + " ");
		}
		System.out.print("\nTottenham: " + tottenham + " ");
				
		for (String score : tottenhamScorers) {
			System.out.print(score + " ");
		};
		System.out.println();
	}
	
	public boolean fullTimeCheck(int minute,int arsenal, List<String> arsenalScorers,int tottenham,List<String> tottenhamScorers) {
		if (minute >= 90) {
	        System.out.println("\nFull time!");
	        scoreUpdate(arsenal, arsenalScorers, tottenham, tottenhamScorers);
	        return true;
	    } else {
	    	return false;
	    }
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public int getMinute() {
		return minute;
	}

	public void addMinute() {
		this.minute += 1;
	}
	
}
