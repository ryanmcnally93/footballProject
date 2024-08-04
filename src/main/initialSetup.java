package main;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import leagueSetup.League;
import people.Footballer;
import people.Goalkeeper;
import people.Manager;
import visuals.MatchFrames.GameWindow;
import visuals.MatchFrames.MatchStats;

public class initialSetup {
	
	public initialSetup() {
	Goalkeeper raya = new Goalkeeper("David Raya", 31, 150);
	Footballer jesus = new Footballer("Gabriel Jesus", 31, 180, 30, 100, "ST");
	Footballer trossard = new Footballer("Leandro Trossard", 31, 200, 40, 100, "LW");
	Footballer saka = new Footballer("Bukayo Saka", 31, 180, 65, 100, "RW");
	Footballer partey = new Footballer("Thomas Partey", 31, 90, 180, 100, "CM");
	Footballer odegaard = new Footballer("Martin Odegaard", 31, 180, 120, 100, "CAM");
	Footballer rice = new Footballer("Declan Rice", 31, 110, 190, 100, "CM");
	Footballer tomiyasu = new Footballer("Takehiro Tomiyasu", 31, 45, 215, 100, "LB");
	Footballer saliba = new Footballer("William Saliba", 31, 45, 260, 100, "CB");
	Footballer gabriel = new Footballer("Gabriel Magalhaes", 31, 45, 245, 100, "CB");
	Footballer white = new Footballer("Ben White", 31, 75, 215, 100, "RB");
	
	Goalkeeper vicario = new Goalkeeper("Guglielmo Vicario", 31, 130);
	Footballer johnson = new Footballer("Brennan Johnson", 31, 180, 30, 100, "ST");
	Footballer son = new Footballer("Heung-Min Son", 31, 200, 40, 100, "RW");
	Footballer kulusevski = new Footballer("Dejan Kulusevski", 31, 180, 65, 100, "LW");
	Footballer maddison = new Footballer("James Maddison", 31, 90, 180, 100, "CAM");
	Footballer bissouma = new Footballer("Yves Bissouma", 31, 180, 120, 100, "CM");
	Footballer sarr = new Footballer("Pape Matar Sarr", 31, 110, 190, 100, "CM");
	Footballer udogie = new Footballer("Destiny Udogie", 31, 45, 215, 100, "LB");
	Footballer vanDeVen = new Footballer("Micky van de Ven", 31, 45, 260, 100, "CB");
	Footballer romero = new Footballer("Cristian Romero", 31, 45, 245, 100, "CB");
	Footballer porro = new Footballer("Guglielmo Vicario", 31, 75, 215, 100, "RB");
	
	Map<String, Footballer> arsenalFirst = new HashMap<>();
	arsenalFirst.put("GK", raya);
	arsenalFirst.put("ST", jesus);
	arsenalFirst.put("LW", trossard);
	arsenalFirst.put("RW", saka);
	arsenalFirst.put("CAM", odegaard);
	arsenalFirst.put("CM", partey);
	arsenalFirst.put("CM", rice);
	arsenalFirst.put("LB", tomiyasu);
	arsenalFirst.put("CB", gabriel);
	arsenalFirst.put("CB", saliba);
	arsenalFirst.put("RB", white);
	Map<String, Footballer> tottenhamFirst = new HashMap<>();
	tottenhamFirst.put("GK", vicario);
	tottenhamFirst.put("ST", johnson);
	tottenhamFirst.put("RW", son);
	tottenhamFirst.put("LW", kulusevski);
	tottenhamFirst.put("CAM", maddison);
	tottenhamFirst.put("CM", bissouma);
	tottenhamFirst.put("CM", sarr);
	tottenhamFirst.put("LB", udogie);
	tottenhamFirst.put("CB", vanDeVen);
	tottenhamFirst.put("CB", romero);
	tottenhamFirst.put("RB", porro);
	
	Manager Arteta = new Manager("Mikel Arteta", 31, 30000);
	Manager Mourinho = new Manager("Jose Mourinho", 56, 300000);

	Team Arsenal = new Team("Arsenal", Arteta, arsenalFirst, 45000000, "Emirates Stadium", Color.RED, Color.WHITE);
	Team Tottenham = new Team("Tottenham", Mourinho, tottenhamFirst, 45000000, "Scummy Stadium", Color.WHITE, Color.BLUE);
	
	Map<String, Team> premTeams = new HashMap<>();
	premTeams.put("Arsenal", Arsenal);
	premTeams.put("Tottenham", Tottenham);
	
	League PremierLeague = new League("Premier League", "England", 2, premTeams, 1);
	PremierLeague.getFixturesToString();
    
	GameWindow window = new GameWindow();
    window.setVisible(true);
	
    Match match = ((Match) PremierLeague.getFixtures().get("Tottenham vs Arsenal"));
	match.displayGame(window);
	
	}
	
	public void startSeason() {
		
	}
}
