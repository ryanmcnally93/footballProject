package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import leagueSetup.League;
import people.Footballer;
import people.Goalkeeper;
import people.Manager;
import visuals.MatchFrames.GameWindow;
import visuals.MatchFrames.MatchStats;

public class initialSetup {
	
	private League PremierLeague;
	private GameWindow window;
	
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
	
	Goalkeeper ederson = new Goalkeeper("Ederson Moraes", 30, 140);
    Footballer haaland = new Footballer("Erling Haaland", 23, 210, 50, 100, "ST");
    Footballer Dsilva = new Footballer("Bernardo Silva", 29, 185, 75, 100, "RW");
    Footballer grealish = new Footballer("Jack Grealish", 28, 180, 70, 100, "LW");
    Footballer deBruyne = new Footballer("Kevin De Bruyne", 32, 200, 80, 100, "CAM");
    Footballer rodri = new Footballer("Rodri Hernandez", 27, 90, 200, 100, "CM");
    Footballer gundogan = new Footballer("Ilkay Gundogan", 33, 100, 180, 100, "CM");
    Footballer ake = new Footballer("Nathan Ake", 29, 55, 220, 100, "LB");
    Footballer dias = new Footballer("Ruben Dias", 26, 50, 250, 100, "CB");
    Footballer stones = new Footballer("John Stones", 29, 60, 230, 100, "CB");
    Footballer walker = new Footballer("Kyle Walker", 34, 80, 200, 100, "RB");
    
    Goalkeeper alisson = new Goalkeeper("Alisson Becker", 31, 145);
    Footballer nunez = new Footballer("Darwin Nunez", 24, 190, 50, 100, "ST");
    Footballer salah = new Footballer("Mohamed Salah", 31, 200, 70, 100, "RW");
    Footballer diaz = new Footballer("Luis Diaz", 27, 180, 60, 100, "LW");
    Footballer szoboszlai = new Footballer("Dominik Szoboszlai", 23, 175, 65, 100, "CAM");
    Footballer macAllister = new Footballer("Alexis Mac Allister", 25, 150, 80, 100, "CM");
    Footballer jones = new Footballer("Curtis Jones", 23, 130, 85, 100, "CM");
    Footballer robertson = new Footballer("Andrew Robertson", 30, 65, 195, 100, "LB");
    Footballer vanDijk = new Footballer("Virgil van Dijk", 32, 50, 255, 100, "CB");
    Footballer konate = new Footballer("Ibrahima Konate", 25, 55, 240, 100, "CB");
    Footballer alexanderArnold = new Footballer("Trent Alexander-Arnold", 25, 95, 190, 100, "RB");
    
    Goalkeeper sanchez = new Goalkeeper("Robert Sanchez", 26, 135);
    Footballer jackson = new Footballer("Nicolas Jackson", 22, 185, 45, 100, "ST");
    Footballer sterling = new Footballer("Raheem Sterling", 29, 190, 55, 100, "RW");
    Footballer mudryk = new Footballer("Mykhailo Mudryk", 23, 180, 50, 100, "LW");
    Footballer enzo = new Footballer("Enzo Fernandez", 23, 170, 80, 100, "CAM");
    Footballer caicedo = new Footballer("Moises Caicedo", 22, 140, 100, 100, "CM");
    Footballer gallagher = new Footballer("Conor Gallagher", 24, 125, 90, 100, "CM");
    Footballer chilwell = new Footballer("Ben Chilwell", 27, 75, 180, 100, "LB");
    Footballer silva = new Footballer("Thiago Silva", 39, 40, 250, 100, "CB");
    Footballer colwill = new Footballer("Levi Colwill", 21, 50, 210, 100, "CB");
    Footballer james = new Footballer("Reece James", 24, 85, 200, 100, "RB");
    
    Goalkeeper onana = new Goalkeeper("Andre Onana", 28, 140);
    Footballer rashford = new Footballer("Marcus Rashford", 26, 195, 60, 100, "ST");
    Footballer antony = new Footballer("Antony Matheus", 24, 180, 55, 100, "RW");
    Footballer garnacho = new Footballer("Alejandro Garnacho", 20, 175, 50, 100, "LW");
    Footballer bruno = new Footballer("Bruno Fernandes", 29, 190, 75, 100, "CAM");
    Footballer casemiro = new Footballer("Casemiro", 32, 100, 190, 100, "CM");
    Footballer eriksen = new Footballer("Christian Eriksen", 32, 150, 80, 100, "CM");
    Footballer shaw = new Footballer("Luke Shaw", 28, 70, 185, 100, "LB");
    Footballer martinez = new Footballer("Lisandro Martinez", 26, 55, 240, 100, "CB");
    Footballer varane = new Footballer("Raphael Varane", 31, 50, 245, 100, "CB");
    Footballer dalot = new Footballer("Diogo Dalot", 25, 80, 195, 100, "RB");
    
    Goalkeeper pope = new Goalkeeper("Nick Pope", 32, 138);
    Footballer isak = new Footballer("Alexander Isak", 24, 180, 45, 100, "ST");
    Footballer almiron = new Footballer("Miguel Almiron", 29, 175, 55, 100, "RW");
    Footballer gordon = new Footballer("Anthony Gordon", 23, 160, 50, 100, "LW");
    Footballer tonali = new Footballer("Sandro Tonali", 24, 140, 90, 100, "CAM");
    Footballer guimaraes = new Footballer("Bruno Guimaraes", 26, 130, 100, 100, "CM");
    Footballer longstaff = new Footballer("Sean Longstaff", 26, 120, 85, 100, "CM");
    Footballer burn = new Footballer("Dan Burn", 31, 55, 220, 100, "LB");
    Footballer botman = new Footballer("Sven Botman", 24, 50, 235, 100, "CB");
    Footballer schar = new Footballer("Fabian Schar", 32, 60, 225, 100, "CB");
    Footballer trippier = new Footballer("Kieran Trippier", 33, 85, 200, 100, "RB");
    
    Goalkeeper Emartinez = new Goalkeeper("Emiliano Martinez", 31, 140);
    Footballer watkins = new Footballer("Ollie Watkins", 28, 185, 50, 100, "ST");
    Footballer traore = new Footballer("Bertrand Traore", 28, 175, 55, 100, "RW");
    Footballer diaby = new Footballer("Moussa Diaby", 24, 180, 50, 100, "LW");
    Footballer buendia = new Footballer("Emiliano Buendia", 27, 165, 60, 100, "CAM");
    Footballer douglasLuiz = new Footballer("Douglas Luiz", 26, 130, 85, 100, "CM");
    Footballer mcginn = new Footballer("John McGinn", 29, 125, 90, 100, "CM");
    Footballer digne = new Footballer("Lucas Digne", 30, 70, 180, 100, "LB");
    Footballer konsa = new Footballer("Ezri Konsa", 26, 55, 230, 100, "CB");
    Footballer torres = new Footballer("Pau Torres", 27, 50, 240, 100, "CB");
    Footballer cash = new Footballer("Matty Cash", 26, 80, 190, 100, "RB");

    Map<String, Footballer> astonVillaFirst = new HashMap<>();
    astonVillaFirst.put("GK", Emartinez);
    astonVillaFirst.put("ST", watkins);
    astonVillaFirst.put("RW", traore);
    astonVillaFirst.put("LW", diaby);
    astonVillaFirst.put("CAM", buendia);
    astonVillaFirst.put("CM", douglasLuiz);
    astonVillaFirst.put("CM", mcginn);
    astonVillaFirst.put("LB", digne);
    astonVillaFirst.put("CB", konsa);
    astonVillaFirst.put("CB", torres);
    astonVillaFirst.put("RB", cash);

    Map<String, Footballer> newcastleFirst = new HashMap<>();
    newcastleFirst.put("GK", pope);
    newcastleFirst.put("ST", isak);
    newcastleFirst.put("RW", almiron);
    newcastleFirst.put("LW", gordon);
    newcastleFirst.put("CAM", tonali);
    newcastleFirst.put("CM", guimaraes);
    newcastleFirst.put("CM", longstaff);
    newcastleFirst.put("LB", burn);
    newcastleFirst.put("CB", botman);
    newcastleFirst.put("CB", schar);
    newcastleFirst.put("RB", trippier);

    Map<String, Footballer> manUtdFirst = new HashMap<>();
    manUtdFirst.put("GK", onana);
    manUtdFirst.put("ST", rashford);
    manUtdFirst.put("RW", antony);
    manUtdFirst.put("LW", garnacho);
    manUtdFirst.put("CAM", bruno);
    manUtdFirst.put("CM", casemiro);
    manUtdFirst.put("CM", eriksen);
    manUtdFirst.put("LB", shaw);
    manUtdFirst.put("CB", martinez);
    manUtdFirst.put("CB", varane);
    manUtdFirst.put("RB", dalot);

    Map<String, Footballer> chelseaFirst = new HashMap<>();
    chelseaFirst.put("GK", sanchez);
    chelseaFirst.put("ST", jackson);
    chelseaFirst.put("RW", sterling);
    chelseaFirst.put("LW", mudryk);
    chelseaFirst.put("CAM", enzo);
    chelseaFirst.put("CM", caicedo);
    chelseaFirst.put("CM", gallagher);
    chelseaFirst.put("LB", chilwell);
    chelseaFirst.put("CB", silva);
    chelseaFirst.put("CB", colwill);
    chelseaFirst.put("RB", james);

    Map<String, Footballer> liverpoolFirst = new HashMap<>();
    liverpoolFirst.put("GK", alisson);
    liverpoolFirst.put("ST", nunez);
    liverpoolFirst.put("RW", salah);
    liverpoolFirst.put("LW", diaz);
    liverpoolFirst.put("CAM", szoboszlai);
    liverpoolFirst.put("CM", macAllister);
    liverpoolFirst.put("CM", jones);
    liverpoolFirst.put("LB", robertson);
    liverpoolFirst.put("CB", vanDijk);
    liverpoolFirst.put("CB", konate);
    liverpoolFirst.put("RB", alexanderArnold);

    Map<String, Footballer> manCityFirst = new HashMap<>();
    manCityFirst.put("GK", ederson);
    manCityFirst.put("ST", haaland);
    manCityFirst.put("RW", Dsilva);
    manCityFirst.put("LW", grealish);
    manCityFirst.put("CAM", deBruyne);
    manCityFirst.put("CM", rodri);
    manCityFirst.put("CM", gundogan);
    manCityFirst.put("LB", ake);
    manCityFirst.put("CB", dias);
    manCityFirst.put("CB", stones);
    manCityFirst.put("RB", walker);
	
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
	Manager smith = new Manager("Dean Smith", 52, 300000);
	Manager howe = new Manager("Eddie Howe", 46, 300000);
	Manager dyche = new Manager("Sean Dyche", 52, 300000);
	Manager pochettino = new Manager("Mauricio Pochettino", 52, 300000);
	Manager viera = new Manager("Patrick Vieira", 48, 300000);
	Manager lampard = new Manager("Frank Lampard", 45, 300000);
	Manager guardiola = new Manager("Pep Guardiola", 53, 300000);
	Manager tenHag = new Manager("Erik ten Hag", 54, 300000);
	Manager Ehowe = new Manager("Eddie Howe", 46, 300000);
	Manager cooper = new Manager("Steve Cooper", 44, 300000);
	Manager klopp = new Manager("Jurgen Klopp", 56, 300000);
	Manager edwards = new Manager("Rob Edwards", 40, 300000);
	Manager oneil = new Manager("Gary O'Neil", 41, 300000);
	Manager moyes = new Manager("David Moyes", 61, 300000);
	Manager Msilva = new Manager("Marco Silva", 46, 300000);
	Manager hodgson = new Manager("Roy Hodgson", 76, 300000);
	Manager other1 = new Manager("Marco Silva", 46, 300000);
	Manager emery = new Manager("Unai Emery", 76, 300000);

	Team Arsenal = new Team("Arsenal", Arteta, arsenalFirst, 45000000, "Emirates Stadium", Color.RED, Color.WHITE);
	Team Tottenham = new Team("Tottenham", Mourinho, tottenhamFirst, 45000000, "Scummy Stadium", Color.WHITE, Color.BLUE);
	Team Newcastle = new Team("Newcastle", howe, newcastleFirst, 45000000, "Newcastle Stadium", Color.BLACK, Color.WHITE);
	Team ManCity = new Team("Manchester City", guardiola, manCityFirst, 45000000, "Etihad Stadium", Color.BLUE, Color.BLUE);
	Team Chelsea = new Team("Chelsea", lampard, chelseaFirst, 45000000, "Scum Stadium", Color.BLUE, Color.WHITE);
	Team AstonVilla = new Team("Aston Villa", emery, astonVillaFirst, 45000000, "Villa Park", Color.MAGENTA, Color.BLUE);
	Team ManUtd = new Team("Manchester United", tenHag, manUtdFirst, 45000000, "Old Trafford Stadium", Color.RED, Color.WHITE);
	Team Liverpool = new Team("Liverpool", klopp, liverpoolFirst, 45000000, "Anfield Stadium", Color.RED, Color.YELLOW);
//	Team Arsenal = new Team("Arsenal", Arteta, arsenalFirst, 45000000, "Emirates Stadium", Color.RED, Color.WHITE);
//	Team Tottenham = new Team("Tottenham", Mourinho, tottenhamFirst, 45000000, "Scummy Stadium", Color.WHITE, Color.BLUE);
//	Team Arsenal = new Team("Arsenal", Arteta, arsenalFirst, 45000000, "Emirates Stadium", Color.RED, Color.WHITE);
//	Team Tottenham = new Team("Tottenham", Mourinho, tottenhamFirst, 45000000, "Scummy Stadium", Color.WHITE, Color.BLUE);
//	Team Arsenal = new Team("Arsenal", Arteta, arsenalFirst, 45000000, "Emirates Stadium", Color.RED, Color.WHITE);
//	Team Tottenham = new Team("Tottenham", Mourinho, tottenhamFirst, 45000000, "Scummy Stadium", Color.WHITE, Color.BLUE);
//	Team Arsenal = new Team("Arsenal", Arteta, arsenalFirst, 45000000, "Emirates Stadium", Color.RED, Color.WHITE);
//	Team Tottenham = new Team("Tottenham", Mourinho, tottenhamFirst, 45000000, "Scummy Stadium", Color.WHITE, Color.BLUE);
//	Team Arsenal = new Team("Arsenal", Arteta, arsenalFirst, 45000000, "Emirates Stadium", Color.RED, Color.WHITE);
//	Team Tottenham = new Team("Tottenham", Mourinho, tottenhamFirst, 45000000, "Scummy Stadium", Color.WHITE, Color.BLUE);
//	Team Arsenal = new Team("Arsenal", Arteta, arsenalFirst, 45000000, "Emirates Stadium", Color.RED, Color.WHITE);
//	Team Tottenham = new Team("Tottenham", Mourinho, tottenhamFirst, 45000000, "Scummy Stadium", Color.WHITE, Color.BLUE);
	
	Map<String, Team> premTeams = new HashMap<>();
	premTeams.put("Arsenal", Arsenal);
	premTeams.put("Tottenham", Tottenham);
	premTeams.put("Liverpool", Liverpool);
	premTeams.put("Newcastle", Newcastle);
	premTeams.put("Manchester United", ManUtd);
	premTeams.put("Manchester City", ManCity);
	premTeams.put("Aston Villa", AstonVilla);
	premTeams.put("Chelsea", Chelsea);
	
	PremierLeague = new League("Premier League", "England", 8, premTeams, 1);
    
	window = new GameWindow();
    window.setVisible(true);
	
//    Match match = ((Match) PremierLeague.getFixtures().get("Chelsea vs Manchester City"));
//	match.displayGame(window);
	
	  StartPage startPage = new StartPage(this);
	  startPage.displayPage();
	}
	
	public void startSeason() {}

	public League getPremierLeague() {
		return PremierLeague;
	}

	public void setPremierLeague(League premierLeague) {
		PremierLeague = premierLeague;
	}

	public GameWindow getWindow() {
		return window;
	}

	public void setWindow(GameWindow window) {
		this.window = window;
	}
}
