import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

// Update Character like in vehicle speed
// Make startrun smaller using more methods
// Call more functions from other .java?

// Players are called Characters, because that's how we started
public class Footballer {
	private String name;
	public int attack;
	public int defence;
	private String team;
	public int stamina; 
	public String position;
	
	// Define Player Characteristics
	public Footballer(String name, int attack, int defence, int stamina, String team, String position) {
		this.name = name;
		this.attack = attack;
		this.defence = defence;
		this.team = team;
		this.stamina = stamina;
		this.position = position;
	}
	
	/* This method will determine whether or not a player
	has successfully sprinted past his opponent, we are
	going to use math random against the likelihood out of 100 */
	public boolean getPastPlayer(Footballer otherPlayer) {
		
		// If the opponent is out of juice, we run past them
		if (otherPlayer.stamina <= 0) {
			return true;
		} else {
			
			/* This works out the likelihood percentage of
			the player getting past the defence. */
			int overall = this.attack + otherPlayer.defence;
			float odds = overall / 100.0F;
			float perc = this.attack / odds;
			
			// Here we have a random number between 1-100
			double randomNumber = Math.floor(Math.random()*100);
			
			/* If 30 percent, and the random number resides within
			the first 30 numbers, we will run past. */
			if (randomNumber <= perc) {
				// After we make a successful run, we have less energy
				this.stamina -= 10;
				// Check to see we haven't run out of stamina
				if (this.stamina <= 0) {
					outOfStamina(this);
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
		System.out.println(player.name + " has run out of stamina");
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
	
	public boolean fullTimeCheck(int gameTurn,int arsenal, List<String> arsenalScorers,int tottenham,List<String> tottenhamScorers) {
		if (gameTurn >= 90) {
	        System.out.println("\nFull time!");
	        scoreUpdate(arsenal, arsenalScorers, tottenham, tottenhamScorers);
	        return true;
	    } else {
	    	return false;
	    }
	}
	
	/* This is effectively a start game method,
	first time round is run with Ryan
	but whoever wins the ball continues a run of their own */
	public void startRun(Footballer[] players, int gameTurn, int arsenal, int tottenham, List<String> arsenalScorers, List<String> tottenhamScorers) {
		
		if(fullTimeCheck(gameTurn,arsenal,arsenalScorers,tottenham,tottenhamScorers)) {return;};
		
		// Making sure the game is under 90 minutes.
		// Inserted game time was 0
		for (Footballer player : players) {
				
			if (player.getName() != this.name && player.getTeam() != this.team) {
				if (this.getPastPlayer(player) == true) {
					gameTurn++;
					if(fullTimeCheck(gameTurn,arsenal,arsenalScorers,tottenham,tottenhamScorers)) {return;};
					System.out.println(this.name + " sprinted past " + player.getName());
					if (player.position == "Defender") {
						gameTurn++;
						if(fullTimeCheck(gameTurn,arsenal,arsenalScorers,tottenham,tottenhamScorers)) {return;};
						System.out.println(this.name + " scores for " + this.team + " in the " + gameTurn + "th minute!");
						
						if (this.team == "Arsenal") {
							arsenal++;
							boolean aFound = false;
							for (int i = 0;i<arsenalScorers.size();i++) {
								String scorer = arsenalScorers.get(i);
								
								if (scorer.contains(this.name) ) {
									aFound = true;
									// Update scorer
									scorer = scorer.substring(0, scorer.length() - 1);
									scorer = scorer + ", " + gameTurn + ")";
									arsenalScorers.set(i, scorer);
									break;
								}
								
							}
							if (!aFound) {
								arsenalScorers.add(this.name + "(" + gameTurn + ")");
							}
						} else {
							tottenham++;
							boolean tFound = false;
							for (int i = 0;i<tottenhamScorers.size();i++) {
								String scorer = tottenhamScorers.get(i);
								
								if (scorer.contains(this.name) ) {
									tFound = true;
									// Update scorer
									scorer = scorer.substring(0, scorer.length() - 1);
									scorer = scorer + ", " + gameTurn + ")";
									tottenhamScorers.set(i, scorer);
									break;
								}
								
							}
							if (!tFound) {
								tottenhamScorers.add(this.name + "(" + gameTurn + ")");
							}
						}
						
						scoreUpdate(arsenal, arsenalScorers, tottenham, tottenhamScorers);
						
						this.startRun(players, gameTurn, arsenal, tottenham, arsenalScorers, tottenhamScorers);
						return;
					}
				} else {
					gameTurn++;
					if(fullTimeCheck(gameTurn,arsenal,arsenalScorers,tottenham,tottenhamScorers)) {return;};
				    
					System.out.println(this.name + " has conceded posession to " + player.getName());
					player.startRun(players, gameTurn, arsenal, tottenham, arsenalScorers, tottenhamScorers);
					return;
				}
			}				
		}
		
		// Ensure that the method always returns.
	    return;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getTeam() {
		return this.team;
	}
	
}
