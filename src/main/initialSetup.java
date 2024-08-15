package main;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import general.League;
import general.Team;
import people.Footballer;
import people.Goalkeeper;
import people.Manager;
import visuals.MatchFrames.GameWindow;

public class initialSetup {
	
	private League league;
	private GameWindow window;
	
	public initialSetup() {
	Goalkeeper rava = new Goalkeeper("David Rava", 31, 150);
	Footballer jasus = new Footballer("Gabriel Jasus", 31, 180, 30, 100, "ST");
	Footballer trossart = new Footballer("Leandro Trossart", 31, 200, 40, 100, "LW");
	Footballer sake = new Footballer("Bukayo Sake", 31, 180, 65, 100, "RW");
	Footballer parley = new Footballer("Thomas Parley", 31, 90, 180, 100, "CM");
	Footballer ovegaard = new Footballer("Martin Ovegaard", 31, 180, 120, 100, "CAM");
	Footballer rire = new Footballer("Declan Rire", 31, 110, 190, 100, "CM");
	Footballer tomiyasa = new Footballer("Takehiro Tomiyasa", 31, 45, 215, 100, "LB");
	Footballer salima = new Footballer("William Salima", 31, 45, 260, 100, "CB");
	Footballer gabriel = new Footballer("Gabriel Magalhares", 31, 45, 245, 100, "CB");
	Footballer whites = new Footballer("Ben Whites", 31, 75, 215, 100, "RB");
	
	Goalkeeper vicarios = new Goalkeeper("Guglielmo Vicarios", 31, 130);
	Footballer johnsun = new Footballer("Brennan Jonnsun", 31, 180, 30, 100, "ST");
	Footballer sun = new Footballer("Heung-Min Sun", 31, 200, 40, 100, "RW");
	Footballer kulusevsi = new Footballer("Dejan Kulusevsi", 31, 180, 65, 100, "LW");
	Footballer maddisom = new Footballer("James Maddisom", 31, 90, 180, 100, "CAM");
	Footballer bissouna = new Footballer("Yves Bissouna", 31, 180, 120, 100, "CM");
	Footballer sara = new Footballer("Pape Matar Sara", 31, 110, 190, 100, "CM");
	Footballer udogia = new Footballer("Destiny Udogia", 31, 45, 215, 100, "LB");
	Footballer vanDeVem = new Footballer("Micky van de Vem", 31, 45, 260, 100, "CB");
	Footballer romaro = new Footballer("Cristian Romaro", 31, 45, 245, 100, "CB");
	Footballer poriro = new Footballer("Pedro Poriro", 31, 75, 215, 100, "RB");
	
	Goalkeeper ederrson = new Goalkeeper("Ederrson Moraes", 30, 140);
	Footballer haland = new Footballer("Erling Haland", 23, 210, 50, 100, "ST");
	Footballer Dsilvo = new Footballer("Bernardo Silvo", 29, 185, 75, 100, "RW");
	Footballer grealosh = new Footballer("Jack Grealosh", 28, 180, 70, 100, "LW");
	Footballer deBruyan = new Footballer("Kevin De Bruyan", 32, 200, 80, 100, "CAM");
	Footballer rodri = new Footballer("Rodri Hernandes", 27, 90, 200, 100, "CM");
	Footballer gundogam = new Footballer("Ilkay Gundogam", 33, 100, 180, 100, "CM");
	Footballer aka = new Footballer("Nathan Aka", 29, 55, 220, 100, "LB");
	Footballer diaz = new Footballer("Ruben Diaz", 26, 50, 250, 100, "CB");
	Footballer stonas = new Footballer("John Stonas", 29, 60, 230, 100, "CB");
	Footballer walkar = new Footballer("Kyle Walkar", 34, 80, 200, 100, "RB");
    
	Goalkeeper alisson = new Goalkeeper("Alisson Pecker", 31, 145);
	Footballer nunaz = new Footballer("Darwin Nunaz", 24, 190, 50, 100, "ST");
	Footballer salak = new Footballer("Mohamed Salak", 31, 200, 70, 100, "RW");
	Footballer diax = new Footballer("Luis Diax", 27, 180, 60, 100, "LW");
	Footballer szoboszlan = new Footballer("Dominik Szoboszlan", 23, 175, 65, 100, "CAM");
	Footballer macAllistir = new Footballer("Alexis Mac Allistir", 25, 150, 80, 100, "CM");
	Footballer jomes = new Footballer("Curtis Jomes", 23, 130, 85, 100, "CM");
	Footballer robertsen = new Footballer("Andrew Robertsen", 30, 65, 195, 100, "LB");
	Footballer vanDikl = new Footballer("Virgil van Dikl", 32, 50, 255, 100, "CB");
	Footballer konete = new Footballer("Ibrahima Konete", 25, 55, 240, 100, "CB");
	Footballer alexanderArneld = new Footballer("Trent Alexander-Arneld", 25, 95, 190, 100, "RB");
    
	Goalkeeper sanchaz = new Goalkeeper("Robert Sanchaz", 26, 135);
	Footballer jacksom = new Footballer("Nicolas Jacksom", 22, 185, 45, 100, "ST");
	Footballer sterliny = new Footballer("Raheem Sterliny", 29, 190, 55, 100, "RW");
	Footballer mudryi = new Footballer("Mykhailo Mudryi", 23, 180, 50, 100, "LW");
	Footballer enzo = new Footballer("Enzo Fernamdez", 23, 170, 80, 100, "CAM");
	Footballer caicede = new Footballer("Moises Caicede", 22, 140, 100, 100, "CM");
	Footballer gallaghar = new Footballer("Conor Gallaghar", 24, 125, 90, 100, "CM");
	Footballer chilwoll = new Footballer("Ben Chilwoll", 27, 75, 180, 100, "LB");
	Footballer silve = new Footballer("Thiago Silve", 39, 40, 250, 100, "CB");
	Footballer colwilk = new Footballer("Levi Colwilk", 21, 50, 210, 100, "CB");
	Footballer jamis = new Footballer("Reece Jamis", 24, 85, 200, 100, "RB");
    
	Goalkeeper onano = new Goalkeeper("Andre Onano", 28, 140);
	Footballer rashfard = new Footballer("Marcus Rashfard", 26, 195, 60, 100, "ST");
	Footballer anthony = new Footballer("Anthony Matheus", 24, 180, 55, 100, "RW");
	Footballer garnache = new Footballer("Alejandro Garnache", 20, 175, 50, 100, "LW");
	Footballer bruno = new Footballer("Bruno Fernandas", 29, 190, 75, 100, "CAM");
	Footballer casomiro = new Footballer("Casomiro", 32, 100, 190, 100, "CM");
	Footballer eriksem = new Footballer("Christian Eriksem", 32, 150, 80, 100, "CM");
	Footballer shas = new Footballer("Luke Shas", 28, 70, 185, 100, "LB");
	Footballer martinev = new Footballer("Lisandro Martinev", 26, 55, 240, 100, "CB");
	Footballer varanr = new Footballer("Raphael Varanr", 31, 50, 245, 100, "CB");
	Footballer dalog = new Footballer("Diogo Dalog", 25, 80, 195, 100, "RB");
    
	Goalkeeper popa = new Goalkeeper("Nick Popa", 32, 138);
	Footballer isaak = new Footballer("Alexander Isaak", 24, 180, 45, 100, "ST");
	Footballer almiran = new Footballer("Miguel Almiran", 29, 175, 55, 100, "RW");
	Footballer gordan = new Footballer("Anthony Gordan", 23, 160, 50, 100, "LW");
	Footballer tonalo = new Footballer("Sandro Tonalo", 24, 140, 90, 100, "CAM");
	Footballer guimaraez = new Footballer("Bruno Guimaraez", 26, 130, 100, 100, "CM");
	Footballer longstaffh = new Footballer("Sean Longstaffh", 26, 120, 85, 100, "CM");
	Footballer burm = new Footballer("Dan Burm", 31, 55, 220, 100, "LB");
	Footballer botmam = new Footballer("Sven Botmam", 24, 50, 235, 100, "CB");
	Footballer schas = new Footballer("Fabian Schas", 32, 60, 225, 100, "CB");
	Footballer trippiar = new Footballer("Kieran Trippiar", 33, 85, 200, 100, "RB");
    
	Goalkeeper Emartinev = new Goalkeeper("Emiliano Martinev", 31, 140);
	Footballer watkinz = new Footballer("Ollie Watkinz", 28, 185, 50, 100, "ST");
	Footballer traora = new Footballer("Bertrand Traora", 28, 175, 55, 100, "RW");
	Footballer diabi = new Footballer("Moussa Diabi", 24, 180, 50, 100, "LW");
	Footballer buendio = new Footballer("Emiliano Buendio", 27, 165, 60, 100, "CAM");
	Footballer douglasLuix = new Footballer("Douglas Luix", 26, 130, 85, 100, "CM");
	Footballer mcguinness = new Footballer("John McGuinness", 29, 125, 90, 100, "CM");
	Footballer digme = new Footballer("Lucas Digme", 30, 70, 180, 100, "LB");
	Footballer konsi = new Footballer("Ezri Konsi", 26, 55, 230, 100, "CB");
	Footballer torrex = new Footballer("Pau Torrex", 27, 50, 240, 100, "CB");
	Footballer casp = new Footballer("Matty Casp", 26, 80, 190, 100, "RB");

	Goalkeeper areole = new Goalkeeper("Alphonse Areole", 30, 135);
	Footballer antonia = new Footballer("Michail Antonia", 33, 190, 55, 100, "ST");
	Footballer bowem = new Footballer("Jarrod Bowem", 27, 185, 50, 100, "RW");
	Footballer benrahme = new Footballer("Said Benrahme", 28, 175, 55, 100, "LW");
	Footballer paquetn = new Footballer("Lucas Paquetn", 26, 180, 70, 100, "CAM");
	Footballer wardPrewse = new Footballer("James Ward-Prewse", 29, 150, 85, 100, "CM");
	Footballer soucik = new Footballer("Tomas Soucik", 28, 160, 90, 100, "CM");
	Footballer emerson = new Footballer("Emerson Palmari", 29, 70, 185, 100, "LB");
	Footballer zoumx = new Footballer("Kurt Zoumx", 28, 60, 240, 100, "CB");
	Footballer aguerb = new Footballer("Nayef Aguerb", 27, 55, 230, 100, "CB");
	Footballer coufalr = new Footballer("Vladimir Coufalr", 31, 80, 200, 100, "RB");

	// Leicester
	Goalkeeper hermansen = new Goalkeeper("Mads Hermansan", 23, 130);
	Footballer vardy = new Footballer("Jamie Vardy", 36, 180, 50, 100, "ST");
	Footballer iheanacho = new Footballer("Kelechi Iheanacno", 26, 175, 55, 100, "RW");
	Footballer madison = new Footballer("James Madisen", 27, 185, 60, 100, "LW");
	Footballer ndidi = new Footballer("Wilfred Ndidr", 26, 165, 70, 100, "CM");
	Footballer soumare = new Footballer("Boubakary Soumara", 24, 160, 80, 100, "CM");
	Footballer dewburyHall = new Footballer("Kiernan Dewsbury-Hall", 25, 150, 85, 100, "CAM");
	Footballer castagne = new Footballer("Timothy Castanre", 28, 70, 190, 100, "LB");
	Footballer faes = new Footballer("Wout Fas", 25, 55, 220, 100, "CB");
	Footballer souttar = new Footballer("Harry Souttqr", 24, 50, 230, 100, "CB");
	Footballer ricardo = new Footballer("Ricardo Pereirz", 29, 80, 195, 100, "RB");
	
	// Ipswich
	Goalkeeper walton = new Goalkeeper("Christian Waltom", 28, 140);
	Footballer broadhead = new Footballer("Ellis Broadhed", 25, 175, 50, 100, "ST");
	Footballer burns = new Footballer("Janoi Burnz", 26, 180, 55, 100, "RW");
	Footballer clarke = new Footballer("Conor Clark", 24, 170, 60, 100, "LW");
	Footballer evans = new Footballer("Lee Evens", 28, 160, 65, 100, "CM");
	Footballer morsey = new Footballer("Sam Morsy", 31, 150, 80, 100, "CM");
	Footballer luongo = new Footballer("Massimo Luongo", 30, 140, 85, 100, "CAM");
	Footballer kenlock = new Footballer("Myles Kenlocke", 25, 75, 185, 100, "LB");
	Footballer wolfenden = new Footballer("Luke Wolfenden", 24, 60, 220, 100, "CB");
	Footballer donacien = new Footballer("Toto Donacien", 29, 55, 225, 100, "CB");
	Footballer leigh = new Footballer("Greg Leigh", 30, 85, 195, 100, "RB");
	  
//    fulham
	Goalkeeper ramsdale = new Goalkeeper("Aaron Ramsdake", 26, 140);
	Footballer mitrovic = new Footballer("Aleksandar Mitrovich", 29, 190, 55, 100, "ST");
	Footballer wilson = new Footballer("Harry Wilson", 27, 175, 60, 100, "RW");
	Footballer perreira = new Footballer("Andreas Perreir", 28, 180, 65, 100, "LW");
	Footballer palhinha = new Footballer("Joao Palhinh", 28, 170, 80, 100, "CM");
	Footballer reed = new Footballer("Harrison Reen", 28, 155, 85, 100, "CM");
	Footballer lukic = new Footballer("Sasa Lukic", 27, 140, 90, 100, "CAM");
	Footballer roberts = new Footballer("Antonee Robertz", 25, 70, 185, 100, "LB");
	Footballer diop = new Footballer("Issa Diob", 26, 60, 220, 100, "CB");
	Footballer adarabioyo = new Footballer("Tosin Adarabioyu", 25, 55, 230, 100, "CB");
	Footballer tete = new Footballer("Sergi Tete", 26, 80, 195, 100, "RB");
	
//    everton
	Goalkeeper pickford = new Goalkeeper("Jordan Pickfard", 30, 145);
	Footballer calvertlewin = new Footballer("Dominic Calvert-Lewin", 26, 190, 55, 100, "ST");
	Footballer gray = new Footballer("Demarai Grary", 27, 180, 50, 100, "RW");
	Footballer mcneil = new Footballer("Dwight Mcneil", 24, 175, 55, 100, "LW");
	Footballer gueye = new Footballer("Idrissa Gueye", 34, 165, 75, 100, "CM");
	Footballer onana = new Footballer("Amadou Onan", 22, 160, 80, 100, "CM");
	Footballer wobi = new Footballer("Alex Iwobi", 27, 140, 85, 100, "CAM");
	Footballer mykolenko = new Footballer("Vitaliy Mykalenko", 24, 70, 180, 100, "LB");
	Footballer tarkowski = new Footballer("James Tarkowsky", 31, 65, 225, 100, "CB");
	Footballer coady = new Footballer("Conor Coad", 30, 55, 235, 100, "CB");
	Footballer patterson = new Footballer("Nathan Patterson", 22, 85, 200, 100, "RB");
	
//    brighton
	Goalkeeper steel = new Goalkeeper("Jason Steell", 27, 130);
	Footballer mitoma = new Footballer("Kaoru Mitom", 26, 180, 55, 100, "ST");
	Footballer march = new Footballer("Solly Marc", 29, 175, 60, 100, "RW");
	Footballer estupinan = new Footballer("Pervis Estupinaz", 25, 185, 65, 100, "LW");
	Footballer macallister = new Footballer("Alexis Macalliste", 25, 170, 80, 100, "CM");
	Footballer caicedo = new Footballer("Moises Caicedo", 22, 155, 85, 100, "CM");
	Footballer gilmore = new Footballer("Billy Gilmore", 22, 145, 90, 100, "CAM");
	Footballer veltman = new Footballer("Joel Veltma", 32, 75, 180, 100, "LB");
	Footballer dunk = new Footballer("Lewis Dunk", 31, 60, 230, 100, "CB");
	Footballer webster = new Footballer("Adam Webste", 24, 55, 220, 100, "CB");
	Footballer lamptey = new Footballer("Tariq Lamptey", 23, 85, 195, 100, "RB");
	
//    forest
	Goalkeeper hennessey = new Goalkeeper("Wayne Hennessex", 36, 140);
	Footballer awoniyi = new Footballer("Taiwo Awoniy", 25, 185, 55, 100, "ST");
	Footballer lingard = new Footballer("Jesse Lingrd", 31, 175, 60, 100, "RW");
	Footballer johnson = new Footballer("Brennan Johnsn", 22, 180, 65, 100, "LW");
	Footballer yates = new Footballer("Ryan Yates", 26, 170, 70, 100, "CM");
	Footballer freuler = new Footballer("Remo Freuler", 31, 155, 80, 100, "CM");
	Footballer danilo = new Footballer("Danilo Pereira", 22, 140, 85, 100, "CAM");
	Footballer lodi = new Footballer("Renan Lodi", 25, 75, 185, 100, "LB");
	Footballer mckenna = new Footballer("Scott Mckenna", 26, 60, 220, 100, "CB");
	Footballer worrall = new Footballer("Joe Worrll", 26, 55, 230, 100, "CB");
	Footballer nico = new Footballer("Neco Williams", 23, 80, 195, 100, "RB");
	
//    bournemouth
	Goalkeeper ramstale = new Goalkeeper("Aaron Ramstale", 25, 135);
	Footballer solanke = new Footballer("Dominic Solankc", 25, 185, 55, 100, "ST");
	Footballer antony = new Footballer("Ryan Antony", 23, 175, 60, 100, "RW");
	Footballer billing = new Footballer("David Billong", 22, 180, 65, 100, "LW");
	Footballer lerma = new Footballer("Jefferson Lerme", 28, 170, 80, 100, "CM");
	Footballer ramsay = new Footballer("Maxime Ramsay", 22, 155, 85, 100, "CM");
	Footballer cook = new Footballer("Lewis Cooke", 26, 140, 90, 100, "CAM");
	Footballer zamora = new Footballer("Jordan Zamor", 24, 75, 185, 100, "LB");
	Footballer mepham = new Footballer("Chris Mepham", 26, 60, 220, 100, "CB");
	Footballer smith = new Footballer("Adam Smiths", 32, 55, 230, 100, "CB");
	Footballer travers = new Footballer("Mark Travers", 23, 80, 195, 100, "RB");
	
//    brentford
	Goalkeeper rayz = new Goalkeeper("David Rayz", 31, 150);
	Footballer toney = new Footballer("Ivan Toney", 28, 190, 60, 100, "ST");
	Footballer bretton = new Footballer("Bryan Breetton", 26, 180, 55, 100, "RW");
	Footballer dasilva = new Footballer("Josh DaSilva", 24, 175, 60, 100, "LW");
	Footballer janelt = new Footballer("Vitaly Janelt", 25, 170, 70, 100, "CM");
	Footballer norgaard = new Footballer("Christian Norgaard", 29, 155, 80, 100, "CM");
	Footballer hickey = new Footballer("Aaron Hicksy", 22, 140, 85, 100, "CAM");
	Footballer henry = new Footballer("Rico Henrey", 26, 75, 180, 100, "LB");
	Footballer pinnock = new Footballer("Ethan Pinnok", 27, 60, 220, 100, "CB");
	Footballer mee = new Footballer("Ben Mee", 34, 55, 230, 100, "CB");
	Footballer canos = new Footballer("Sergi Canos", 26, 80, 195, 100, "RB");
	
//    crystal palace
	Goalkeeper johnstone = new Goalkeeper("Sam Johnstone", 30, 140);
	Footballer eze = new Footballer("Eberechi Eze", 25, 180, 55, 100, "ST");
	Footballer zaha = new Footballer("Wilfried Zah", 31, 175, 60, 100, "RW");
	Footballer olise = new Footballer("Michael Olise", 22, 185, 65, 100, "LW");
	Footballer doucoure = new Footballer("Cheick Doucoure", 24, 170, 70, 100, "CM");
	Footballer lurie = new Footballer("Jeffrey Lurie", 26, 155, 80, 100, "CM");
	Footballer hughes = new Footballer("Will Hughes", 28, 140, 85, 100, "CAM");
	Footballer mitchell = new Footballer("Tyrick Mitchell", 24, 75, 185, 100, "LB");
	Footballer guehi = new Footballer("Marc Gueh", 23, 60, 220, 100, "CB");
	Footballer andersen = new Footballer("Jannik Andersn", 27, 55, 230, 100, "CB");
	Footballer ward = new Footballer("Joel Wrad", 30, 80, 195, 100, "RB");
	
//    southampton
	Goalkeeper bazunu = new Goalkeeper("Gavin Bazunu", 22, 135);
	Footballer alcaraz = new Footballer("Carlos Alcarrz", 21, 175, 50, 100, "ST");
	Footballer wardprowse = new Footballer("James Wardprose", 29, 180, 60, 100, "RW");
	Footballer perraud = new Footballer("Romain Perraud", 26, 170, 65, 100, "LW");
	Footballer lavia = new Footballer("Romeo Lavia", 20, 160, 70, 100, "CM");
	Footballer arreola = new Footballer("Nicolas Arreola", 24, 150, 80, 100, "CM");
	Footballer jankewitz = new Footballer("James Jankewitz", 23, 140, 85, 100, "CAM");
	Footballer karlson = new Footballer("Lianco Karlson", 24, 75, 185, 100, "LB");
	Footballer salisu = new Footballer("Mohammed Salisu", 24, 60, 220, 100, "CB");
	Footballer bednarek = new Footballer("Jan Bednarek", 27, 55, 230, 100, "CB");
	Footballer walker = new Footballer("Kyle Walkr", 32, 80, 195, 100, "RB");
	
//    wolves
	Goalkeeper sarr = new Goalkeeper("José Sárr", 30, 140);
	Footballer cunha = new Footballer("Matheus Cuhna", 24, 185, 55, 100, "ST");
	Footballer podence = new Footballer("Daniel Podenc", 28, 175, 60, 100, "RW");
	Footballer netto = new Footballer("Pedro Nett", 23, 180, 65, 100, "LW");
	Footballer neves = new Footballer("Rúben Neves", 27, 170, 75, 100, "CM");
	Footballer lobato = new Footballer("Mateus Lobato", 20, 155, 80, 100, "CM");
	Footballer nunes = new Footballer("Matheus Nunes", 25, 140, 85, 100, "CAM");
	Footballer marcal = new Footballer("Marçal", 34, 75, 180, 100, "LB");
	Footballer kilman = new Footballer("Max Kilman", 26, 60, 220, 100, "CB");
	Footballer collins = new Footballer("Hugo Collins", 26, 55, 230, 100, "CB");
	Footballer jonny = new Footballer("Jonny Casto", 30, 80, 195, 100, "RB");
	
	// Fulham
//	Map<String, Footballer> eastHamFirst = new HashMap<>();
//	eastHamFirst.put("GK", areole);
//	eastHamFirst.put("ST", antonia);
//	eastHamFirst.put("RW", bowem);
//	eastHamFirst.put("LW", benrahme);
//	eastHamFirst.put("CAM", paquetn);
//	eastHamFirst.put("CM", wardPrewse);
//	eastHamFirst.put("CM", soucik);
//	eastHamFirst.put("LB", emerson);
//	eastHamFirst.put("CB", zoumx);
//	eastHamFirst.put("CB", aguerb);
//	eastHamFirst.put("RB", coufalr);
	
	// Ipswitch
//	Map<String, Footballer> eastHamFirst = new HashMap<>();
//	eastHamFirst.put("GK", areole);
//	eastHamFirst.put("ST", antonia);
//	eastHamFirst.put("RW", bowem);
//	eastHamFirst.put("LW", benrahme);
//	eastHamFirst.put("CAM", paquetn);
//	eastHamFirst.put("CM", wardPrewse);
//	eastHamFirst.put("CM", soucik);
//	eastHamFirst.put("LB", emerson);
//	eastHamFirst.put("CB", zoumx);
//	eastHamFirst.put("CB", aguerb);
//	eastHamFirst.put("RB", coufalr);
	
	// Everton
//	Map<String, Footballer> eastHamFirst = new HashMap<>();
//	eastHamFirst.put("GK", areole);
//	eastHamFirst.put("ST", antonia);
//	eastHamFirst.put("RW", bowem);
//	eastHamFirst.put("LW", benrahme);
//	eastHamFirst.put("CAM", paquetn);
//	eastHamFirst.put("CM", wardPrewse);
//	eastHamFirst.put("CM", soucik);
//	eastHamFirst.put("LB", emerson);
//	eastHamFirst.put("CB", zoumx);
//	eastHamFirst.put("CB", aguerb);
//	eastHamFirst.put("RB", coufalr);
	
	// Brighton
//	Map<String, Footballer> eastHamFirst = new HashMap<>();
//	eastHamFirst.put("GK", areole);
//	eastHamFirst.put("ST", antonia);
//	eastHamFirst.put("RW", bowem);
//	eastHamFirst.put("LW", benrahme);
//	eastHamFirst.put("CAM", paquetn);
//	eastHamFirst.put("CM", wardPrewse);
//	eastHamFirst.put("CM", soucik);
//	eastHamFirst.put("LB", emerson);
//	eastHamFirst.put("CB", zoumx);
//	eastHamFirst.put("CB", aguerb);
//	eastHamFirst.put("RB", coufalr);
	
	// Forest
//	Map<String, Footballer> eastHamFirst = new HashMap<>();
//	eastHamFirst.put("GK", areole);
//	eastHamFirst.put("ST", antonia);
//	eastHamFirst.put("RW", bowem);
//	eastHamFirst.put("LW", benrahme);
//	eastHamFirst.put("CAM", paquetn);
//	eastHamFirst.put("CM", wardPrewse);
//	eastHamFirst.put("CM", soucik);
//	eastHamFirst.put("LB", emerson);
//	eastHamFirst.put("CB", zoumx);
//	eastHamFirst.put("CB", aguerb);
//	eastHamFirst.put("RB", coufalr);
	
	// Palace
//	Map<String, Footballer> eastHamFirst = new HashMap<>();
//	eastHamFirst.put("GK", areole);
//	eastHamFirst.put("ST", antonia);
//	eastHamFirst.put("RW", bowem);
//	eastHamFirst.put("LW", benrahme);
//	eastHamFirst.put("CAM", paquetn);
//	eastHamFirst.put("CM", wardPrewse);
//	eastHamFirst.put("CM", soucik);
//	eastHamFirst.put("LB", emerson);
//	eastHamFirst.put("CB", zoumx);
//	eastHamFirst.put("CB", aguerb);
//	eastHamFirst.put("RB", coufalr);
	
	// Southampton
//	Map<String, Footballer> eastHamFirst = new HashMap<>();
//	eastHamFirst.put("GK", areole);
//	eastHamFirst.put("ST", antonia);
//	eastHamFirst.put("RW", bowem);
//	eastHamFirst.put("LW", benrahme);
//	eastHamFirst.put("CAM", paquetn);
//	eastHamFirst.put("CM", wardPrewse);
//	eastHamFirst.put("CM", soucik);
//	eastHamFirst.put("LB", emerson);
//	eastHamFirst.put("CB", zoumx);
//	eastHamFirst.put("CB", aguerb);
//	eastHamFirst.put("RB", coufalr);
	
	// Bournemouth
//	Map<String, Footballer> eastHamFirst = new HashMap<>();
//	eastHamFirst.put("GK", areole);
//	eastHamFirst.put("ST", antonia);
//	eastHamFirst.put("RW", bowem);
//	eastHamFirst.put("LW", benrahme);
//	eastHamFirst.put("CAM", paquetn);
//	eastHamFirst.put("CM", wardPrewse);
//	eastHamFirst.put("CM", soucik);
//	eastHamFirst.put("LB", emerson);
//	eastHamFirst.put("CB", zoumx);
//	eastHamFirst.put("CB", aguerb);
//	eastHamFirst.put("RB", coufalr);
	
	// Brentford
//	Map<String, Footballer> eastHamFirst = new HashMap<>();
//	eastHamFirst.put("GK", areole);
//	eastHamFirst.put("ST", antonia);
//	eastHamFirst.put("RW", bowem);
//	eastHamFirst.put("LW", benrahme);
//	eastHamFirst.put("CAM", paquetn);
//	eastHamFirst.put("CM", wardPrewse);
//	eastHamFirst.put("CM", soucik);
//	eastHamFirst.put("LB", emerson);
//	eastHamFirst.put("CB", zoumx);
//	eastHamFirst.put("CB", aguerb);
//	eastHamFirst.put("RB", coufalr);
	
	// Wolves
//	Map<String, Footballer> eastHamFirst = new HashMap<>();
//	eastHamFirst.put("GK", areole);
//	eastHamFirst.put("ST", antonia);
//	eastHamFirst.put("RW", bowem);
//	eastHamFirst.put("LW", benrahme);
//	eastHamFirst.put("CAM", paquetn);
//	eastHamFirst.put("CM", wardPrewse);
//	eastHamFirst.put("CM", soucik);
//	eastHamFirst.put("LB", emerson);
//	eastHamFirst.put("CB", zoumx);
//	eastHamFirst.put("CB", aguerb);
//	eastHamFirst.put("RB", coufalr);
	
	// Leicester
//	Map<String, Footballer> eastHamFirst = new HashMap<>();
//	eastHamFirst.put("GK", areole);
//	eastHamFirst.put("ST", antonia);
//	eastHamFirst.put("RW", bowem);
//	eastHamFirst.put("LW", benrahme);
//	eastHamFirst.put("CAM", paquetn);
//	eastHamFirst.put("CM", wardPrewse);
//	eastHamFirst.put("CM", soucik);
//	eastHamFirst.put("LB", emerson);
//	eastHamFirst.put("CB", zoumx);
//	eastHamFirst.put("CB", aguerb);
//	eastHamFirst.put("RB", coufalr);
	
	Map<String, Footballer> eastHamFirst = new HashMap<>();
	eastHamFirst.put("GK", areole);
	eastHamFirst.put("ST", antonia);
	eastHamFirst.put("RW", bowem);
	eastHamFirst.put("LW", benrahme);
	eastHamFirst.put("CAM", paquetn);
	eastHamFirst.put("CM", wardPrewse);
	eastHamFirst.put("CM", soucik);
	eastHamFirst.put("LB", emerson);
	eastHamFirst.put("CB", zoumx);
	eastHamFirst.put("CB", aguerb);
	eastHamFirst.put("RB", coufalr);
    
	Map<String, Footballer> actonVillaFirst = new HashMap<>();
    actonVillaFirst.put("GK", Emartinev);
    actonVillaFirst.put("ST", watkinz);
    actonVillaFirst.put("RW", traora);
    actonVillaFirst.put("LW", diabi);
    actonVillaFirst.put("CAM", buendio);
    actonVillaFirst.put("CM", douglasLuix);
    actonVillaFirst.put("CM", mcguinness);
    actonVillaFirst.put("LB", digme);
    actonVillaFirst.put("CB", konsi);
    actonVillaFirst.put("CB", torrex);
    actonVillaFirst.put("RB", casp);

    Map<String, Footballer> newcostleFirst = new HashMap<>();
    newcostleFirst.put("GK", popa);
    newcostleFirst.put("ST", isaak);
    newcostleFirst.put("RW", almiran);
    newcostleFirst.put("LW", gordan);
    newcostleFirst.put("CAM", tonalo);
    newcostleFirst.put("CM", guimaraez);
    newcostleFirst.put("CM", longstaffh);
    newcostleFirst.put("LB", burm);
    newcostleFirst.put("CB", botmam);
    newcostleFirst.put("CB", schas);
    newcostleFirst.put("RB", trippiar);

    Map<String, Footballer> tanUtdFirst = new HashMap<>();
    tanUtdFirst.put("GK", onano);
    tanUtdFirst.put("ST", rashfard);
    tanUtdFirst.put("RW", anthony);
    tanUtdFirst.put("LW", garnache);
    tanUtdFirst.put("CAM", bruno);
    tanUtdFirst.put("CM", casomiro);
    tanUtdFirst.put("CM", eriksem);
    tanUtdFirst.put("LB", shas);
    tanUtdFirst.put("CB", martinev);
    tanUtdFirst.put("CB", varanr);
    tanUtdFirst.put("RB", dalog);

    Map<String, Footballer> chelseeFirst = new HashMap<>();
    chelseeFirst.put("GK", sanchaz);
    chelseeFirst.put("ST", jacksom);
    chelseeFirst.put("RW", sterliny);
    chelseeFirst.put("LW", mudryi);
    chelseeFirst.put("CAM", enzo);
    chelseeFirst.put("CM", caicede);
    chelseeFirst.put("CM", gallaghar);
    chelseeFirst.put("LB", chilwoll);
    chelseeFirst.put("CB", silve);
    chelseeFirst.put("CB", colwilk);
    chelseeFirst.put("RB", jamis);

    Map<String, Footballer> liverpuleFirst = new HashMap<>();
    liverpuleFirst.put("GK", alisson);
    liverpuleFirst.put("ST", nunaz);
    liverpuleFirst.put("RW", salak);
    liverpuleFirst.put("LW", diax);
    liverpuleFirst.put("CAM", szoboszlan);
    liverpuleFirst.put("CM", macAllistir);
    liverpuleFirst.put("CM", jomes);
    liverpuleFirst.put("LB", robertsen);
    liverpuleFirst.put("CB", vanDikl);
    liverpuleFirst.put("CB", konete);
    liverpuleFirst.put("RB", alexanderArneld);

    Map<String, Footballer> vanCityFirst = new HashMap<>();
    vanCityFirst.put("GK", ederrson);
    vanCityFirst.put("ST", haland);
    vanCityFirst.put("RW", Dsilvo);
    vanCityFirst.put("LW", grealosh);
    vanCityFirst.put("CAM", deBruyan);
    vanCityFirst.put("CM", rodri);
    vanCityFirst.put("CM", gundogam);
    vanCityFirst.put("LB", aka);
    vanCityFirst.put("CB", diaz);
    vanCityFirst.put("CB", stonas);
    vanCityFirst.put("RB", walkar);
	
	Map<String, Footballer> arsenolFirst = new HashMap<>();
	arsenolFirst.put("GK", rava);
	arsenolFirst.put("ST", jasus);
	arsenolFirst.put("LW", trossart);
	arsenolFirst.put("RW", sake);
	arsenolFirst.put("CAM", ovegaard);
	arsenolFirst.put("CM", parley);
	arsenolFirst.put("CM", rire);
	arsenolFirst.put("LB", tomiyasa);
	arsenolFirst.put("CB", gabriel);
	arsenolFirst.put("CB", salima);
	arsenolFirst.put("RB", whites);
	Map<String, Footballer> totenhamFirst = new HashMap<>();
	totenhamFirst.put("GK", vicarios);
	totenhamFirst.put("ST", johnsun);
	totenhamFirst.put("RW", sun);
	totenhamFirst.put("LW", kulusevsi);
	totenhamFirst.put("CAM", maddisom);
	totenhamFirst.put("CM", bissouna);
	totenhamFirst.put("CM", sara);
	totenhamFirst.put("LB", udogia);
	totenhamFirst.put("CB", vanDeVem);
	totenhamFirst.put("CB", romaro);
	totenhamFirst.put("RB", poriro);
	
	Manager Arteta = new Manager("Mikel Arteta", 31, 30000);
	Manager Mourinho = new Manager("Jose Mourinho", 56, 300000);
	Manager smithe = new Manager("Dean Smithe", 52, 300000);
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

	Team Arsenol = new Team("Arsenol", Arteta, arsenolFirst, 45000000, "Arsenol Stadium", Color.RED, Color.WHITE);
	Team Totenham = new Team("Totenham", Mourinho, totenhamFirst, 45000000, "Totenham Stadium", Color.WHITE, Color.BLUE);
	Team Newcostle = new Team("Newcostle", howe, newcostleFirst, 45000000, "Newcostle Stadium", Color.BLACK, Color.WHITE);
	Team VanCity = new Team("Van City", guardiola, vanCityFirst, 45000000, "Van City Stadium", Color.BLUE, Color.BLUE);
	Team Chelsee = new Team("Chelsee", lampard, chelseeFirst, 45000000, "Scum Stadium", Color.BLUE, Color.WHITE);
	Team ActonVilla = new Team("Acton Villa", emery, actonVillaFirst, 45000000, "Acton Park", Color.MAGENTA, Color.BLUE);
	Team TanUtd = new Team("Tan United", tenHag, tanUtdFirst, 45000000, "New Trafford Stadium", Color.RED, Color.WHITE);
	Team Liverpule = new Team("Liverpule", klopp, liverpuleFirst, 45000000, "Liverpule Stadium", Color.RED, Color.YELLOW);
	Team EastHam = new Team("EastHam", moyes, eastHamFirst, 45000000, "East Ham Stadium", Color.MAGENTA, Color.WHITE);
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
	
	Map<String, Team> preTeams = new HashMap<>();
	preTeams.put("Arsenol", Arsenol);
	preTeams.put("Totenham", Totenham);
	preTeams.put("Liverpule", Liverpule);
	preTeams.put("Newcostle", Newcostle);
	preTeams.put("Tan United", TanUtd);
	preTeams.put("Van City", VanCity);
	preTeams.put("Acton Villa", ActonVilla);
	preTeams.put("Chelsee", Chelsee);
	
	league = new League("Premia League", "England", 8, preTeams, 1);
    
	window = new GameWindow();
    window.setVisible(true);
	
	StartPage startPage = new StartPage(this);
	startPage.displayPage();
	
	}
	
	public void startSeason() {}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public GameWindow getWindow() {
		return window;
	}

	public void setWindow(GameWindow window) {
		this.window = window;
	}
}
