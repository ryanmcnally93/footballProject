import java.util.ArrayList;
import java.util.List;

public class Match {

	public static void main(String[] args) {
		Footballer ryan = new Footballer("Ryan", 200, 20, 100, "Arsenal", "Striker");
		Footballer majid = new Footballer("Majid", 180, 100, 100, "Tottenham", "Striker");
		Footballer josh = new Footballer("Josh", 20, 205, 100, "Arsenal", "Defender");
		Footballer steven = new Footballer("Steven", 40, 210, 100, "Tottenham", "Defender");
		Footballer michael = new Footballer("Michael", 160, 180, 100, "Arsenal", "Midfielder");
		Footballer chester = new Footballer("Chester", 180, 170, 100, "Tottenham", "Midfielder");
		
		Footballer allPlayers[] = {ryan, majid, michael, chester, josh, steven};
		int gameTurn = 0;
		int arsenal = 0; 
		int tottenham = 0;
		ArrayList<String> arsenalScorers = new ArrayList<>();
		ArrayList<String> tottenhamScorers = new ArrayList<>();
		
		ryan.startRun(allPlayers, gameTurn, arsenal, tottenham, arsenalScorers, tottenhamScorers);
	}

}
