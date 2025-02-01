package main;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import entities.League;
import entities.Season;
import entities.Team;
import people.Footballer;
import people.Goalkeeper;
import people.Manager;

@SuppressWarnings("TextBlockMigration")
public class initialSetup {
	
	private Season season;
	private GameWindow window;
	
	public initialSetup() {
	Goalkeeper rava = new Goalkeeper("David Rava", 31, 150);
	Footballer jasus = new Footballer("Gabriel Jasus", 31, 180, 30, 100, "ST");
	Footballer trossart = new Footballer("Leandro Trossart", 31, 200, 40, 100, "LW");
	Footballer sake = new Footballer("Bukayo Sake", 31, 180, 65, 100, "RW");
	Footballer parley = new Footballer("Thomas Parley", 31, 90, 180, 100, "CM", "CM1");
	Footballer ovegaard = new Footballer("Martin Ovegaard", 31, 180, 120, 100, "CAM");
	Footballer rire = new Footballer("Declan Rire", 31, 110, 190, 100, "CM", "CM2");
	Footballer tomiyasa = new Footballer("Takehiro Tomiyasa", 31, 45, 215, 100, "LB");
	Footballer salima = new Footballer("William Salima", 31, 45, 260, 100, "CB", "CB1");
	Footballer gabriel = new Footballer("Gabriel Magalhares", 31, 45, 245, 100, "CB", "CB2");
	Footballer whites = new Footballer("Ben Whites", 31, 75, 215, 100, "RB");
	
	Goalkeeper vicarios = new Goalkeeper("Guglielmo Vicarios", 31, 130);
	Footballer johnsun = new Footballer("Brennan Jonnsun", 31, 180, 30, 100, "ST");
	Footballer sun = new Footballer("Heung-Min Sun", 31, 200, 40, 100, "RW");
	Footballer kulusevsi = new Footballer("Dejan Kulusevsi", 31, 180, 65, 100, "LW");
	Footballer maddisom = new Footballer("James Maddisom", 31, 90, 180, 100, "CAM");
	Footballer bissouna = new Footballer("Yves Bissouna", 31, 180, 120, 100, "CM", "CM1");
	Footballer sara = new Footballer("Pape Matar Sara", 31, 110, 190, 100, "CM", "CM2");
	Footballer udogia = new Footballer("Destiny Udogia", 31, 45, 215, 100, "LB");
	Footballer vanDeVem = new Footballer("Micky van de Vem", 31, 45, 260, 100, "CB", "CB1");
	Footballer romaro = new Footballer("Cristian Romaro", 31, 45, 245, 100, "CB", "CB2");
	Footballer poriro = new Footballer("Pedro Poriro", 31, 75, 215, 100, "RB");
	
	Goalkeeper ederrson = new Goalkeeper("Ederrson Moraes", 30, 140);
	Footballer haland = new Footballer("Erling Haland", 23, 210, 50, 100, "ST");
	Footballer Dsilvo = new Footballer("Bernardo Silvo", 29, 185, 75, 100, "RW");
	Footballer grealosh = new Footballer("Jack Grealosh", 28, 180, 70, 100, "LW");
	Footballer deBruyan = new Footballer("Kevin De Bruyan", 32, 200, 80, 100, "CAM");
	Footballer rodri = new Footballer("Rodri Hernandes", 27, 90, 200, 100, "CM", "CM1");
	Footballer gundogam = new Footballer("Ilkay Gundogam", 33, 100, 180, 100, "CM", "CM2");
	Footballer aka = new Footballer("Nathan Aka", 29, 55, 220, 100, "LB");
	Footballer diaz = new Footballer("Ruben Diaz", 26, 50, 250, 100, "CB", "CB1");
	Footballer stonas = new Footballer("John Stonas", 29, 60, 230, 100, "CB", "CB2");
	Footballer walkar = new Footballer("Kyle Walkar", 34, 80, 200, 100, "RB");
    
	Goalkeeper alisson = new Goalkeeper("Alisson Pecker", 31, 145);
	Footballer nunaz = new Footballer("Darwin Nunaz", 24, 190, 50, 100, "ST");
	Footballer salak = new Footballer("Mohamed Salak", 31, 200, 70, 100, "RW");
	Footballer diax = new Footballer("Luis Diax", 27, 180, 60, 100, "LW");
	Footballer szoboszlan = new Footballer("Dominik Szoboszlan", 23, 175, 65, 100, "CAM");
	Footballer macAllistir = new Footballer("Alexis Mac Allistir", 25, 150, 80, 100, "CM", "CM1");
	Footballer jomes = new Footballer("Curtis Jomes", 23, 130, 85, 100, "CM", "CM2");
	Footballer robertsen = new Footballer("Andrew Robertsen", 30, 65, 195, 100, "LB");
	Footballer vanDikl = new Footballer("Virgil van Dikl", 32, 50, 255, 100, "CB", "CB1");
	Footballer konete = new Footballer("Ibrahima Konete", 25, 55, 240, 100, "CB", "CB2");
	Footballer alexanderArneld = new Footballer("Trent Alexander-Arneld", 25, 95, 190, 100, "RB");
    
	Goalkeeper sanchaz = new Goalkeeper("Robert Sanchaz", 26, 135);
	Footballer jacksom = new Footballer("Nicolas Jacksom", 22, 185, 45, 100, "ST");
	Footballer sterliny = new Footballer("Raheem Sterliny", 29, 190, 55, 100, "RW");
	Footballer mudryi = new Footballer("Mykhailo Mudryi", 23, 180, 50, 100, "LW");
	Footballer enzo = new Footballer("Enzo Fernamdez", 23, 170, 80, 100, "CAM");
	Footballer caicede = new Footballer("Moises Caicede", 22, 140, 100, 100, "CM", "CM1");
	Footballer gallaghar = new Footballer("Conor Gallaghar", 24, 125, 90, 100, "CM", "CM2");
	Footballer chilwoll = new Footballer("Ben Chilwoll", 27, 75, 180, 100, "LB");
	Footballer silve = new Footballer("Thiago Silve", 39, 40, 250, 100, "CB", "CB1");
	Footballer colwilk = new Footballer("Levi Colwilk", 21, 50, 210, 100, "CB", "CB2");
	Footballer jamis = new Footballer("Reece Jamis", 24, 85, 200, 100, "RB");
    
	Goalkeeper onano = new Goalkeeper("Andre Onano", 28, 140);
	Footballer rashfard = new Footballer("Marcus Rashfard", 26, 195, 60, 100, "ST");
	Footballer anthony = new Footballer("Anthony Matheus", 24, 180, 55, 100, "RW");
	Footballer garnache = new Footballer("Alejandro Garnache", 20, 175, 50, 100, "LW");
	Footballer bruno = new Footballer("Bruno Fernandas", 29, 190, 75, 100, "CAM");
	Footballer casomiro = new Footballer("Casomiro", 32, 100, 190, 100, "CM", "CM1");
	Footballer eriksem = new Footballer("Christian Eriksem", 32, 150, 80, 100, "CM", "CM2");
	Footballer shas = new Footballer("Luke Shas", 28, 70, 185, 100, "LB");
	Footballer martinev = new Footballer("Lisandro Martinev", 26, 55, 240, 100, "CB", "CB1");
	Footballer varanr = new Footballer("Raphael Varanr", 31, 50, 245, 100, "CB", "CB2");
	Footballer dalog = new Footballer("Diogo Dalog", 25, 80, 195, 100, "RB");
    
	Goalkeeper popa = new Goalkeeper("Nick Popa", 32, 138);
	Footballer isaak = new Footballer("Alexander Isaak", 24, 180, 45, 100, "ST");
	Footballer almiran = new Footballer("Miguel Almiran", 29, 175, 55, 100, "RW");
	Footballer gordan = new Footballer("Anthony Gordan", 23, 160, 50, 100, "LW");
	Footballer tonalo = new Footballer("Sandro Tonalo", 24, 140, 90, 100, "CAM");
	Footballer guimaraez = new Footballer("Bruno Guimaraez", 26, 130, 100, 100, "CM", "CM1");
	Footballer longstaffh = new Footballer("Sean Longstaffh", 26, 120, 85, 100, "CM", "CM2");
	Footballer burm = new Footballer("Dan Burm", 31, 55, 220, 100, "LB");
	Footballer botmam = new Footballer("Sven Botmam", 24, 50, 235, 100, "CB", "CB1");
	Footballer schas = new Footballer("Fabian Schas", 32, 60, 225, 100, "CB", "CB2");
	Footballer trippiar = new Footballer("Kieran Trippiar", 33, 85, 200, 100, "RB");
    
	Goalkeeper Emartinev = new Goalkeeper("Emiliano Martinev", 31, 140);
	Footballer watkinz = new Footballer("Ollie Watkinz", 28, 185, 50, 100, "ST");
	Footballer traora = new Footballer("Bertrand Traora", 28, 175, 55, 100, "RW");
	Footballer diabi = new Footballer("Moussa Diabi", 24, 180, 50, 100, "LW");
	Footballer buendio = new Footballer("Emiliano Buendio", 27, 165, 60, 100, "CAM");
	Footballer douglasLuix = new Footballer("Douglas Luix", 26, 130, 85, 100, "CM", "CM1");
	Footballer mcguinness = new Footballer("John McGuinness", 29, 125, 90, 100, "CM", "CM2");
	Footballer digme = new Footballer("Lucas Digme", 30, 70, 180, 100, "LB");
	Footballer konsi = new Footballer("Ezri Konsi", 26, 55, 230, 100, "CB", "CB1");
	Footballer torrex = new Footballer("Pau Torrex", 27, 50, 240, 100, "CB", "CB2");
	Footballer casp = new Footballer("Matty Casp", 26, 80, 190, 100, "RB");

	Goalkeeper areole = new Goalkeeper("Alphonse Areole", 30, 135);
	Footballer antonia = new Footballer("Michail Antonia", 33, 190, 55, 100, "ST");
	Footballer bowem = new Footballer("Jarrod Bowem", 27, 185, 50, 100, "RW");
	Footballer benrahme = new Footballer("Said Benrahme", 28, 175, 55, 100, "LW");
	Footballer paquetn = new Footballer("Lucas Paquetn", 26, 180, 70, 100, "CAM");
	Footballer wardPrewse = new Footballer("James Ward-Prewse", 29, 150, 85, 100, "CM", "CM1");
	Footballer soucik = new Footballer("Tomas Soucik", 28, 160, 90, 100, "CM", "CM2");
	Footballer emerson = new Footballer("Emerson Palmari", 29, 70, 185, 100, "LB");
	Footballer zoumx = new Footballer("Kurt Zoumx", 28, 60, 240, 100, "CB", "CB1");
	Footballer aguerb = new Footballer("Nayef Aguerb", 27, 55, 230, 100, "CB", "CB2");
	Footballer coufalr = new Footballer("Vladimir Coufalr", 31, 80, 200, 100, "RB");

	Goalkeeper hermanson = new Goalkeeper("Mads Hermansan", 23, 130);
	Footballer vardi = new Footballer("Jamie Vardy", 36, 180, 50, 100, "ST");
	Footballer iheanachos = new Footballer("Kelechi Iheanacno", 26, 175, 55, 100, "RW");
	Footballer madisonn = new Footballer("James Madisen", 27, 185, 60, 100, "LW");
	Footballer ndidy = new Footballer("Wilfred Ndidr", 26, 165, 70, 100, "CM", "CM1");
	Footballer soumara = new Footballer("Boubakary Soumara", 24, 160, 80, 100, "CM", "CM2");
	Footballer dewburyHull = new Footballer("Kiernan Dewsbury-Hall", 25, 150, 85, 100, "CAM");
	Footballer castagna = new Footballer("Timothy Castanre", 28, 70, 190, 100, "LB");
	Footballer fase = new Footballer("Wout Fas", 25, 55, 220, 100, "CB", "CB1");
	Footballer soutar = new Footballer("Harry Souttqr", 24, 50, 230, 100, "CB", "CB2");
	Footballer riccardo = new Footballer("Ricardo Pereirz", 29, 80, 195, 100, "RB");

	Goalkeeper waltom = new Goalkeeper("Christian Waltom", 28, 140);
	Footballer broadhed = new Footballer("Ellis Broadhed", 25, 175, 50, 100, "ST");
	Footballer burnz = new Footballer("Janoi Burnz", 26, 180, 55, 100, "RW");
	Footballer clark = new Footballer("Conor Clark", 24, 170, 60, 100, "LW");
	Footballer evens = new Footballer("Lee Evens", 28, 160, 65, 100, "CM", "CM1");
	Footballer morsy = new Footballer("Sam Morsy", 31, 150, 80, 100, "CM", "CM2");
	Footballer luongo = new Footballer("Massimo Luongo", 30, 140, 85, 100, "CAM");
	Footballer kenlocke = new Footballer("Myles Kenlocke", 25, 75, 185, 100, "LB");
	Footballer wolfenden = new Footballer("Luke Wolfenden", 24, 60, 220, 100, "CB", "CB1");
	Footballer donacien = new Footballer("Toto Donacien", 29, 55, 225, 100, "CB", "CB2");
	Footballer leigh = new Footballer("Greg Leigh", 30, 85, 195, 100, "RB");

	Goalkeeper ramsdake = new Goalkeeper("Aaron Ramsdake", 26, 140);
	Footballer mitrovich = new Footballer("Aleksandar Mitrovich", 29, 190, 55, 100, "ST");
	Footballer wilson = new Footballer("Harry Wilson", 27, 175, 60, 100, "RW");
	Footballer perreir = new Footballer("Andreas Perreir", 28, 180, 65, 100, "LW");
	Footballer palhinh = new Footballer("Joao Palhinh", 28, 170, 80, 100, "CM", "CM1");
	Footballer reen = new Footballer("Harrison Reen", 28, 155, 85, 100, "CM", "CM2");
	Footballer lukic = new Footballer("Sasa Lukic", 27, 140, 90, 100, "CAM");
	Footballer robertz = new Footballer("Antonee Robertz", 25, 70, 185, 100, "LB");
	Footballer diob = new Footballer("Issa Diob", 26, 60, 220, 100, "CB", "CB1");
	Footballer adarabioyu = new Footballer("Tosin Adarabioyu", 25, 55, 230, 100, "CB", "CB2");
	Footballer tete = new Footballer("Sergi Tete", 26, 80, 195, 100, "RB");

	Goalkeeper pickfard = new Goalkeeper("Jordan Pickfard", 30, 145);
	Footballer calvertlewin = new Footballer("Dominic Calvert-Lewin", 26, 190, 55, 100, "ST");
	Footballer grary = new Footballer("Demarai Grary", 27, 180, 50, 100, "RW");
	Footballer mcneil = new Footballer("Dwight Mcneil", 24, 175, 55, 100, "LW");
	Footballer gueye = new Footballer("Idrissa Gueye", 34, 165, 75, 100, "CM", "CM1");
	Footballer onan = new Footballer("Amadou Onan", 22, 160, 80, 100, "CM", "CM2");
	Footballer iwobi = new Footballer("Alex Iwobi", 27, 140, 85, 100, "CAM");
	Footballer mykalenko = new Footballer("Vitaliy Mykalenko", 24, 70, 180, 100, "LB");
	Footballer tarkowsky = new Footballer("James Tarkowsky", 31, 65, 225, 100, "CB", "CB1");
	Footballer coad = new Footballer("Conor Coad", 30, 55, 235, 100, "CB", "CB2");
	Footballer patterson = new Footballer("Nathan Patterson", 22, 85, 200, 100, "RB");

	Goalkeeper steell = new Goalkeeper("Jason Steell", 27, 130);
	Footballer mitom = new Footballer("Kaoru Mitom", 26, 180, 55, 100, "ST");
	Footballer marc = new Footballer("Solly Marc", 29, 175, 60, 100, "RW");
	Footballer estupinaz = new Footballer("Pervis Estupinaz", 25, 185, 65, 100, "LW");
	Footballer macalliste = new Footballer("Alexis Macalliste", 25, 170, 80, 100, "CM", "CM1");
	Footballer caicedo = new Footballer("Moises Caicedo", 22, 155, 85, 100, "CM", "CM2");
	Footballer gilmore = new Footballer("Billy Gilmore", 22, 145, 90, 100, "CAM");
	Footballer veltma = new Footballer("Joel Veltma", 32, 75, 180, 100, "LB");
	Footballer dunk = new Footballer("Lewis Dunk", 31, 60, 230, 100, "CB", "CB1");
	Footballer webste = new Footballer("Adam Webste", 24, 55, 220, 100, "CB", "CB2");
	Footballer lamptey = new Footballer("Tariq Lamptey", 23, 85, 195, 100, "RB");

	Goalkeeper hennessex = new Goalkeeper("Wayne Hennessex", 36, 140);
	Footballer awoniy = new Footballer("Taiwo Awoniy", 25, 185, 55, 100, "ST");
	Footballer lingrd = new Footballer("Jesse Lingrd", 31, 175, 60, 100, "RW");
	Footballer johnsn = new Footballer("Brennan Johnsn", 22, 180, 65, 100, "LW");
	Footballer yates = new Footballer("Ryan Yates", 26, 170, 70, 100, "CM", "CM1");
	Footballer freuler = new Footballer("Remo Freuler", 31, 155, 80, 100, "CM", "CM2");
	Footballer pereira = new Footballer("Danilo Pereira", 22, 140, 85, 100, "CAM");
	Footballer lodi = new Footballer("Renan Lodi", 25, 75, 185, 100, "LB");
	Footballer mckenna = new Footballer("Scott Mckenna", 26, 60, 220, 100, "CB", "CB1");
	Footballer worrll = new Footballer("Joe Worrll", 26, 55, 230, 100, "CB", "CB2");
	Footballer nico = new Footballer("Neco Williams", 23, 80, 195, 100, "RB");

	Goalkeeper ramstale = new Goalkeeper("Aaron Ramstale", 25, 135);
	Footballer solankc = new Footballer("Dominic Solankc", 25, 185, 55, 100, "ST");
	Footballer antony = new Footballer("Ryan Antony", 23, 175, 60, 100, "RW");
	Footballer billong = new Footballer("David Billong", 22, 180, 65, 100, "LW");
	Footballer lerme = new Footballer("Jefferson Lerme", 28, 170, 80, 100, "CM", "CM1");
	Footballer ramsay = new Footballer("Maxime Ramsay", 22, 155, 85, 100, "CM", "CM2");
	Footballer cooke = new Footballer("Lewis Cooke", 26, 140, 90, 100, "CAM");
	Footballer zamor = new Footballer("Jordan Zamor", 24, 75, 185, 100, "LB");
	Footballer mepham = new Footballer("Chris Mepham", 26, 60, 220, 100, "CB", "CB1");
	Footballer smiths = new Footballer("Adam Smiths", 32, 55, 230, 100, "CB", "CB2");
	Footballer travers = new Footballer("Mark Travers", 23, 80, 195, 100, "RB");

	Goalkeeper rayz = new Goalkeeper("David Rayz", 31, 150);
	Footballer toney = new Footballer("Ivan Toney", 28, 190, 60, 100, "ST");
	Footballer breetton = new Footballer("Bryan Breetton", 26, 180, 55, 100, "RW");
	Footballer dasilva = new Footballer("Josh DaSilva", 24, 175, 60, 100, "LW");
	Footballer janelt = new Footballer("Vitaly Janelt", 25, 170, 70, 100, "CM", "CM1");
	Footballer norgaard = new Footballer("Christian Norgaard", 29, 155, 80, 100, "CM", "CM2");
	Footballer hicksy = new Footballer("Aaron Hicksy", 22, 140, 85, 100, "CAM");
	Footballer henrey = new Footballer("Rico Henrey", 26, 75, 180, 100, "LB");
	Footballer pinnok = new Footballer("Ethan Pinnok", 27, 60, 220, 100, "CB", "CB1");
	Footballer mee = new Footballer("Ben Mee", 34, 55, 230, 100, "CB", "CB2");
	Footballer canos = new Footballer("Sergi Canos", 26, 80, 195, 100, "RB");

	Goalkeeper johnstone = new Goalkeeper("Sam Johnstone", 30, 140);
	Footballer eze = new Footballer("Eberechi Eze", 25, 180, 55, 100, "ST");
	Footballer zah = new Footballer("Wilfried Zah", 31, 175, 60, 100, "RW");
	Footballer olise = new Footballer("Michael Olise", 22, 185, 65, 100, "LW");
	Footballer doucoure = new Footballer("Cheick Doucoure", 24, 170, 70, 100, "CM", "CM1");
	Footballer lurie = new Footballer("Jeffrey Lurie", 26, 155, 80, 100, "CM", "CM2");
	Footballer hughes = new Footballer("Will Hughes", 28, 140, 85, 100, "CAM");
	Footballer mitchell = new Footballer("Tyrick Mitchell", 24, 75, 185, 100, "LB");
	Footballer gueh = new Footballer("Marc Gueh", 23, 60, 220, 100, "CB", "CB1");
	Footballer andersn = new Footballer("Jannik Andersn", 27, 55, 230, 100, "CB", "CB2");
	Footballer wrad = new Footballer("Joel Wrad", 30, 80, 195, 100, "RB");

	Goalkeeper bazunu = new Goalkeeper("Gavin Bazunu", 22, 135);
	Footballer alcarrz = new Footballer("Carlos Alcarrz", 21, 175, 50, 100, "ST");
	Footballer wardprose = new Footballer("James Wardprose", 29, 180, 60, 100, "RW");
	Footballer peraud = new Footballer("Romain Peraud", 26, 170, 65, 100, "LW");
	Footballer lavie = new Footballer("Romeo Lavie", 20, 160, 70, 100, "CM", "CM2");
	Footballer arreole = new Footballer("Nicolas Arreole", 24, 150, 80, 100, "CM", "CM1");
	Footballer jankwitz = new Footballer("James Jankwitz", 23, 140, 85, 100, "CAM");
	Footballer karlsen = new Footballer("Lianco Karlsen", 24, 75, 185, 100, "LB");
	Footballer salisu = new Footballer("Mohammed Salisu", 24, 60, 220, 100, "CB", "CB1");
	Footballer bednarek = new Footballer("Jan Bednarek", 27, 55, 230, 100, "CB", "CB2");
	Footballer walkr = new Footballer("Kyle Walkr", 32, 80, 195, 100, "RB");

	Goalkeeper sarr = new Goalkeeper("José Sárr", 30, 140);
	Footballer cuhna = new Footballer("Matheus Cuhna", 24, 185, 55, 100, "ST");
	Footballer podenc = new Footballer("Daniel Podenc", 28, 175, 60, 100, "RW");
	Footballer nett = new Footballer("Pedro Nett", 23, 180, 65, 100, "LW");
	Footballer neves = new Footballer("Rúben Neves", 27, 170, 75, 100, "CM", "CM1");
	Footballer lobato = new Footballer("Mateus Lobato", 20, 155, 80, 100, "CM", "CM2");
	Footballer nunes = new Footballer("Matheus Nunes", 25, 140, 85, 100, "CAM");
	Footballer marcal = new Footballer("Marçal", 34, 75, 180, 100, "LB");
	Footballer kilman = new Footballer("Max Kilman", 26, 60, 220, 100, "CB", "CB1");
	Footballer collins = new Footballer("Hugo Collins", 26, 55, 230, 100, "CB", "CB2");
	Footballer casto = new Footballer("Jonny Casto", 30, 80, 195, 100, "RB");

	Map<String, Footballer> fullhamFirst = new HashMap<>();
	fullhamFirst.put(ramsdake.getPositionPlaced(), ramsdake);
	fullhamFirst.put(mitrovich.getPositionPlaced(), mitrovich);
	fullhamFirst.put(wilson.getPositionPlaced(), wilson);
	fullhamFirst.put(perreir.getPositionPlaced(), perreir);
	fullhamFirst.put(lukic.getPositionPlaced(), lukic);
	fullhamFirst.put(reen.getPositionPlaced(), reen);
	fullhamFirst.put(palhinh.getPositionPlaced(), palhinh);
	fullhamFirst.put(robertz.getPositionPlaced(), robertz);
	fullhamFirst.put(diob.getPositionPlaced(), diob);
	fullhamFirst.put(adarabioyu.getPositionPlaced(), adarabioyu);
	fullhamFirst.put(tete.getPositionPlaced(), tete);

	Map<String, Footballer> upswitchFirst = new HashMap<>();
	upswitchFirst.put(waltom.getPositionPlaced(), waltom);
	upswitchFirst.put(broadhed.getPositionPlaced(), broadhed);
	upswitchFirst.put(burnz.getPositionPlaced(), burnz);
	upswitchFirst.put(clark.getPositionPlaced(), clark);
	upswitchFirst.put(luongo.getPositionPlaced(), luongo);
	upswitchFirst.put(morsy.getPositionPlaced(), morsy);
	upswitchFirst.put(evens.getPositionPlaced(), evens);
	upswitchFirst.put(kenlocke.getPositionPlaced(), kenlocke);
	upswitchFirst.put(wolfenden.getPositionPlaced(), wolfenden);
	upswitchFirst.put(donacien.getPositionPlaced(), donacien);
	upswitchFirst.put(leigh.getPositionPlaced(), leigh);

	Map<String, Footballer> evertunFirst = new HashMap<>();
	evertunFirst.put(pickfard.getPositionPlaced(), pickfard);
	evertunFirst.put(calvertlewin.getPositionPlaced(), calvertlewin);
	evertunFirst.put(grary.getPositionPlaced(), grary);
	evertunFirst.put(mcneil.getPositionPlaced(), mcneil);
	evertunFirst.put(iwobi.getPositionPlaced(), iwobi);
	evertunFirst.put(onan.getPositionPlaced(), onan);
	evertunFirst.put(gueye.getPositionPlaced(), gueye);
	evertunFirst.put(mykalenko.getPositionPlaced(), mykalenko);
	evertunFirst.put(tarkowsky.getPositionPlaced(), tarkowsky);
	evertunFirst.put(coad.getPositionPlaced(), coad);
	evertunFirst.put(patterson.getPositionPlaced(), patterson);

	Map<String, Footballer> brightenFirst = new HashMap<>();
	brightenFirst.put(steell.getPositionPlaced(), steell);
	brightenFirst.put(mitom.getPositionPlaced(), mitom);
	brightenFirst.put(marc.getPositionPlaced(), marc);
	brightenFirst.put(estupinaz.getPositionPlaced(), estupinaz);
	brightenFirst.put(gilmore.getPositionPlaced(), gilmore);
	brightenFirst.put(caicedo.getPositionPlaced(), caicedo);
	brightenFirst.put(macalliste.getPositionPlaced(), macalliste);
	brightenFirst.put(veltma.getPositionPlaced(), veltma);
	brightenFirst.put(dunk.getPositionPlaced(), dunk);
	brightenFirst.put(webste.getPositionPlaced(), webste);
	brightenFirst.put(lamptey.getPositionPlaced(), lamptey);

	Map<String, Footballer> nottinghamWoodsFirst = new HashMap<>();
	nottinghamWoodsFirst.put(hennessex.getPositionPlaced(), hennessex);
	nottinghamWoodsFirst.put(awoniy.getPositionPlaced(), awoniy);
	nottinghamWoodsFirst.put(lingrd.getPositionPlaced(), lingrd);
	nottinghamWoodsFirst.put(johnsn.getPositionPlaced(), johnsn);
	nottinghamWoodsFirst.put(pereira.getPositionPlaced(), pereira);
	nottinghamWoodsFirst.put(freuler.getPositionPlaced(), freuler);
	nottinghamWoodsFirst.put(yates.getPositionPlaced(), yates);
	nottinghamWoodsFirst.put(lodi.getPositionPlaced(), lodi);
	nottinghamWoodsFirst.put(mckenna.getPositionPlaced(), mckenna);
	nottinghamWoodsFirst.put(worrll.getPositionPlaced(), worrll);
	nottinghamWoodsFirst.put(nico.getPositionPlaced(), nico);

	Map<String, Footballer> palaceFirst = new HashMap<>();
	palaceFirst.put(johnstone.getPositionPlaced(), johnstone);
	palaceFirst.put(eze.getPositionPlaced(), eze);
	palaceFirst.put(zah.getPositionPlaced(), zah);
	palaceFirst.put(olise.getPositionPlaced(), olise);
	palaceFirst.put(doucoure.getPositionPlaced(), doucoure);
	palaceFirst.put(lurie.getPositionPlaced(), lurie);
	palaceFirst.put(hughes.getPositionPlaced(), hughes);
	palaceFirst.put(mitchell.getPositionPlaced(), mitchell);
	palaceFirst.put(gueh.getPositionPlaced(), gueh);
	palaceFirst.put(andersn.getPositionPlaced(), andersn);
	palaceFirst.put(wrad.getPositionPlaced(), wrad);

	Map<String, Footballer> southamtonFirst = new HashMap<>();
	southamtonFirst.put(bazunu.getPositionPlaced(), bazunu);
	southamtonFirst.put(alcarrz.getPositionPlaced(), alcarrz);
	southamtonFirst.put(wardprose.getPositionPlaced(), wardprose);
	southamtonFirst.put(peraud.getPositionPlaced(), peraud);
	southamtonFirst.put(jankwitz.getPositionPlaced(), jankwitz);
	southamtonFirst.put(arreole.getPositionPlaced(), arreole);
	southamtonFirst.put(lavie.getPositionPlaced(), lavie);
	southamtonFirst.put(karlsen.getPositionPlaced(), karlsen);
	southamtonFirst.put(salisu.getPositionPlaced(), salisu);
	southamtonFirst.put(bednarek.getPositionPlaced(), bednarek);
	southamtonFirst.put(walkr.getPositionPlaced(), walkr);

	Map<String, Footballer> bornmouthFirst = new HashMap<>();
	bornmouthFirst.put(ramstale.getPositionPlaced(), ramstale);
	bornmouthFirst.put(solankc.getPositionPlaced(), solankc);
	bornmouthFirst.put(antony.getPositionPlaced(), antony);
	bornmouthFirst.put(billong.getPositionPlaced(), billong);
	bornmouthFirst.put(cooke.getPositionPlaced(), cooke);
	bornmouthFirst.put(ramsay.getPositionPlaced(), ramsay);
	bornmouthFirst.put(lerme.getPositionPlaced(), lerme);
	bornmouthFirst.put(zamor.getPositionPlaced(), zamor);
	bornmouthFirst.put(mepham.getPositionPlaced(), mepham);
	bornmouthFirst.put(smiths.getPositionPlaced(), smiths);
	bornmouthFirst.put(travers.getPositionPlaced(), travers);

	Map<String, Footballer> burntfordFirst = new HashMap<>();
	burntfordFirst.put(rayz.getPositionPlaced(), rayz);
	burntfordFirst.put(toney.getPositionPlaced(), toney);
	burntfordFirst.put(breetton.getPositionPlaced(), breetton);
	burntfordFirst.put(dasilva.getPositionPlaced(), dasilva);
	burntfordFirst.put(hicksy.getPositionPlaced(), hicksy);
	burntfordFirst.put(norgaard.getPositionPlaced(), norgaard);
	burntfordFirst.put(janelt.getPositionPlaced(), janelt);
	burntfordFirst.put(henrey.getPositionPlaced(), henrey);
	burntfordFirst.put(pinnok.getPositionPlaced(), pinnok);
	burntfordFirst.put(mee.getPositionPlaced(), mee);
	burntfordFirst.put(canos.getPositionPlaced(), canos);

	Map<String, Footballer> wolvesFirst = new HashMap<>();
	wolvesFirst.put(sarr.getPositionPlaced(), sarr);
	wolvesFirst.put(cuhna.getPositionPlaced(), cuhna);
	wolvesFirst.put(podenc.getPositionPlaced(), podenc);
	wolvesFirst.put(nett.getPositionPlaced(), nett);
	wolvesFirst.put(nunes.getPositionPlaced(), nunes);
	wolvesFirst.put(lobato.getPositionPlaced(), lobato);
	wolvesFirst.put(neves.getPositionPlaced(), neves);
	wolvesFirst.put(marcal.getPositionPlaced(), marcal);
	wolvesFirst.put(kilman.getPositionPlaced(), kilman);
	wolvesFirst.put(collins.getPositionPlaced(), collins);
	wolvesFirst.put(casto.getPositionPlaced(), casto);

	Map<String, Footballer> leicestorFirst = new HashMap<>();
	leicestorFirst.put(hermanson.getPositionPlaced(), hermanson);
	leicestorFirst.put(vardi.getPositionPlaced(), vardi);
	leicestorFirst.put(iheanachos.getPositionPlaced(), iheanachos);
	leicestorFirst.put(madisonn.getPositionPlaced(), madisonn);
	leicestorFirst.put(dewburyHull.getPositionPlaced(), dewburyHull);
	leicestorFirst.put(soumara.getPositionPlaced(), soumara);
	leicestorFirst.put(ndidy.getPositionPlaced(), ndidy);
	leicestorFirst.put(castagna.getPositionPlaced(), castagna);
	leicestorFirst.put(fase.getPositionPlaced(), fase);
	leicestorFirst.put(soutar.getPositionPlaced(), soutar);
	leicestorFirst.put(riccardo.getPositionPlaced(), riccardo);
	
	Map<String, Footballer> eastHamFirst = new HashMap<>();
	eastHamFirst.put(areole.getPositionPlaced(), areole);
	eastHamFirst.put(antonia.getPositionPlaced(), antonia);
	eastHamFirst.put(bowem.getPositionPlaced(), bowem);
	eastHamFirst.put(benrahme.getPositionPlaced(), benrahme);
	eastHamFirst.put(paquetn.getPositionPlaced(), paquetn);
	eastHamFirst.put(wardPrewse.getPositionPlaced(), wardPrewse);
	eastHamFirst.put(soucik.getPositionPlaced(), soucik);
	eastHamFirst.put(emerson.getPositionPlaced(), emerson);
	eastHamFirst.put(zoumx.getPositionPlaced(), zoumx);
	eastHamFirst.put(aguerb.getPositionPlaced(), aguerb);
	eastHamFirst.put(coufalr.getPositionPlaced(), coufalr);
    
	Map<String, Footballer> actonVillaFirst = new HashMap<>();
	actonVillaFirst.put(Emartinev.getPositionPlaced(), Emartinev);
	actonVillaFirst.put(watkinz.getPositionPlaced(), watkinz);
	actonVillaFirst.put(traora.getPositionPlaced(), traora);
	actonVillaFirst.put(diabi.getPositionPlaced(), diabi);
	actonVillaFirst.put(buendio.getPositionPlaced(), buendio);
	actonVillaFirst.put(douglasLuix.getPositionPlaced(), douglasLuix);
	actonVillaFirst.put(mcguinness.getPositionPlaced(), mcguinness);
	actonVillaFirst.put(digme.getPositionPlaced(), digme);
	actonVillaFirst.put(konsi.getPositionPlaced(), konsi);
	actonVillaFirst.put(torrex.getPositionPlaced(), torrex);
	actonVillaFirst.put(casp.getPositionPlaced(), casp);

    Map<String, Footballer> newcostleFirst = new HashMap<>();
	newcostleFirst.put(popa.getPositionPlaced(), popa);
	newcostleFirst.put(isaak.getPositionPlaced(), isaak);
	newcostleFirst.put(almiran.getPositionPlaced(), almiran);
	newcostleFirst.put(gordan.getPositionPlaced(), gordan);
	newcostleFirst.put(tonalo.getPositionPlaced(), tonalo);
	newcostleFirst.put(guimaraez.getPositionPlaced(), guimaraez);
	newcostleFirst.put(longstaffh.getPositionPlaced(), longstaffh);
	newcostleFirst.put(burm.getPositionPlaced(), burm);
	newcostleFirst.put(botmam.getPositionPlaced(), botmam);
	newcostleFirst.put(schas.getPositionPlaced(), schas);
	newcostleFirst.put(trippiar.getPositionPlaced(), trippiar);

    Map<String, Footballer> tanUtdFirst = new HashMap<>();
	tanUtdFirst.put(onano.getPositionPlaced(), onano);
	tanUtdFirst.put(rashfard.getPositionPlaced(), rashfard);
	tanUtdFirst.put(anthony.getPositionPlaced(), anthony);
	tanUtdFirst.put(garnache.getPositionPlaced(), garnache);
	tanUtdFirst.put(bruno.getPositionPlaced(), bruno);
	tanUtdFirst.put(casomiro.getPositionPlaced(), casomiro);
	tanUtdFirst.put(eriksem.getPositionPlaced(), eriksem);
	tanUtdFirst.put(shas.getPositionPlaced(), shas);
	tanUtdFirst.put(martinev.getPositionPlaced(), martinev);
	tanUtdFirst.put(varanr.getPositionPlaced(), varanr);
	tanUtdFirst.put(dalog.getPositionPlaced(), dalog);

    Map<String, Footballer> chelseeFirst = new HashMap<>();
	chelseeFirst.put(sanchaz.getPositionPlaced(), sanchaz);
	chelseeFirst.put(jacksom.getPositionPlaced(), jacksom);
	chelseeFirst.put(sterliny.getPositionPlaced(), sterliny);
	chelseeFirst.put(mudryi.getPositionPlaced(), mudryi);
	chelseeFirst.put(enzo.getPositionPlaced(), enzo);
	chelseeFirst.put(caicede.getPositionPlaced(), caicede);
	chelseeFirst.put(gallaghar.getPositionPlaced(), gallaghar);
	chelseeFirst.put(chilwoll.getPositionPlaced(), chilwoll);
	chelseeFirst.put(silve.getPositionPlaced(), silve);
	chelseeFirst.put(colwilk.getPositionPlaced(), colwilk);
	chelseeFirst.put(jamis.getPositionPlaced(), jamis);

    Map<String, Footballer> liverpuleFirst = new HashMap<>();
	liverpuleFirst.put(alisson.getPositionPlaced(), alisson);
	liverpuleFirst.put(nunaz.getPositionPlaced(), nunaz);
	liverpuleFirst.put(salak.getPositionPlaced(), salak);
	liverpuleFirst.put(diax.getPositionPlaced(), diax);
	liverpuleFirst.put(szoboszlan.getPositionPlaced(), szoboszlan);
	liverpuleFirst.put(macAllistir.getPositionPlaced(), macAllistir);
	liverpuleFirst.put(jomes.getPositionPlaced(), jomes);
	liverpuleFirst.put(robertsen.getPositionPlaced(), robertsen);
	liverpuleFirst.put(vanDikl.getPositionPlaced(), vanDikl);
	liverpuleFirst.put(konete.getPositionPlaced(), konete);
	liverpuleFirst.put(alexanderArneld.getPositionPlaced(), alexanderArneld);

    Map<String, Footballer> vanCityFirst = new HashMap<>();
	vanCityFirst.put(ederrson.getPositionPlaced(), ederrson);
	vanCityFirst.put(haland.getPositionPlaced(), haland);
	vanCityFirst.put(Dsilvo.getPositionPlaced(), Dsilvo);
	vanCityFirst.put(grealosh.getPositionPlaced(), grealosh);
	vanCityFirst.put(deBruyan.getPositionPlaced(), deBruyan);
	vanCityFirst.put(rodri.getPositionPlaced(), rodri);
	vanCityFirst.put(gundogam.getPositionPlaced(), gundogam);
	vanCityFirst.put(aka.getPositionPlaced(), aka);
	vanCityFirst.put(diaz.getPositionPlaced(), diaz);
	vanCityFirst.put(stonas.getPositionPlaced(), stonas);
	vanCityFirst.put(walkar.getPositionPlaced(), walkar);
	
	Map<String, Footballer> arsenolFirst = new HashMap<>();
	arsenolFirst.put(rava.getPositionPlaced(), rava);
	arsenolFirst.put(jasus.getPositionPlaced(), jasus);
	arsenolFirst.put(trossart.getPositionPlaced(), trossart);
	arsenolFirst.put(sake.getPositionPlaced(), sake);
	arsenolFirst.put(ovegaard.getPositionPlaced(), ovegaard);
	arsenolFirst.put(parley.getPositionPlaced(), parley);
	arsenolFirst.put(rire.getPositionPlaced(), rire);
	arsenolFirst.put(tomiyasa.getPositionPlaced(), tomiyasa);
	arsenolFirst.put(gabriel.getPositionPlaced(), gabriel);
	arsenolFirst.put(salima.getPositionPlaced(), salima);
	arsenolFirst.put(whites.getPositionPlaced(), whites);

	Map<String, Footballer> totenhamFirst = new HashMap<>();
	totenhamFirst.put(vicarios.getPositionPlaced(), vicarios);
	totenhamFirst.put(johnsun.getPositionPlaced(), johnsun);
	totenhamFirst.put(sun.getPositionPlaced(), sun);
	totenhamFirst.put(kulusevsi.getPositionPlaced(), kulusevsi);
	totenhamFirst.put(maddisom.getPositionPlaced(), maddisom);
	totenhamFirst.put(bissouna.getPositionPlaced(), bissouna);
	totenhamFirst.put(sara.getPositionPlaced(), sara);
	totenhamFirst.put(udogia.getPositionPlaced(), udogia);
	totenhamFirst.put(vanDeVem.getPositionPlaced(), vanDeVem);
	totenhamFirst.put(romaro.getPositionPlaced(), romaro);
	totenhamFirst.put(poriro.getPositionPlaced(), poriro);
	
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
	Manager mcnally = new Manager("Ryan McNally", 31, 300000);
	Manager hodgson = new Manager("Roy Hodgson", 76, 300000);
	Manager gibbs = new Manager("Mark Gibbs", 33, 300000);
	Manager emery = new Manager("Unai Emery", 76, 300000);

	Color CLARET = new Color(129, 19, 49);
	Color LIGHT_BLUE = new Color(173, 216, 230);

	Team Arsenol = new Team("Arsenol", Arteta, arsenolFirst, 45000000, "Arsenol Stadium", Color.RED, Color.WHITE);
	Team Totenham = new Team("Totenham", Mourinho, totenhamFirst, 45000000, "Totenham Stadium", Color.WHITE, Color.BLUE);
	Team Newcostle = new Team("Newcostle", howe, newcostleFirst, 45000000, "Newcostle Stadium", Color.BLACK, Color.WHITE);
	Team VanCity = new Team("Van City", guardiola, vanCityFirst, 45000000, "Van City Stadium", LIGHT_BLUE, Color.WHITE);
	Team Chelsee = new Team("Chelsee", lampard, chelseeFirst, 45000000, "Chelsee Stadium", Color.BLUE, Color.WHITE);
	Team ActonVilla = new Team("Acton Villa", emery, actonVillaFirst, 45000000, "Acton Park", CLARET, Color.BLUE);
	Team TanUtd = new Team("Tan United", tenHag, tanUtdFirst, 45000000, "New Trafford Stadium", Color.RED, Color.WHITE);
	Team Liverpule = new Team("Liverpule", klopp, liverpuleFirst, 45000000, "Liverpule Stadium", Color.RED, Color.YELLOW);
	Team EastHam = new Team("East Ham", moyes, eastHamFirst, 45000000, "East Ham Stadium", CLARET, Color.WHITE);
	Team Leicestor = new Team("Leicestor", viera, leicestorFirst, 45000000, "Leicestor Stadium", Color.BLUE, Color.WHITE);
	Team Wolves = new Team("Wolves", hodgson, wolvesFirst, 45000000, "Wolves Stadium", Color.ORANGE, Color.WHITE);
	Team Upswitch = new Team("Upswitch", smithe, upswitchFirst, 45000000, "Upswitch Stadium", Color.BLUE, Color.WHITE);
	Team Fullham = new Team("Fullham", dyche, fullhamFirst, 45000000, "Fullham Stadium", Color.WHITE, Color.BLACK);
	Team Evertun = new Team("Evertun", pochettino, evertunFirst, 45000000, "Evertun Stadium", Color.BLUE, Color.BLUE);
	Team Brighten = new Team("Brighten", Ehowe, brightenFirst, 45000000, "Brighten Stadium", Color.BLUE, Color.BLACK);
	Team NottinghamWoods = new Team("Nottingham Woods", cooper, nottinghamWoodsFirst, 45000000, "Woods Stadium", Color.RED, Color.WHITE);
	Team Palace = new Team("Palace", edwards, palaceFirst, 45000000, "Palace Stadium", Color.BLUE, Color.RED);
	Team Bornmouth = new Team("Bornmouth", oneil, bornmouthFirst, 45000000, "Bornmouth Stadium", Color.RED, Color.BLACK);
	Team Burntford = new Team("Burntford", mcnally, burntfordFirst, 45000000, "Burntford Stadium", Color.RED, Color.WHITE);
	Team Southamton = new Team("Southamton", gibbs, southamtonFirst, 45000000, "Southamton Stadium", Color.RED, Color.WHITE);
	
	Map<String, Team> preTeams = new HashMap<>();
	preTeams.put("Arsenol", Arsenol);
	preTeams.put("Totenham", Totenham);
	preTeams.put("Liverpule", Liverpule);
	preTeams.put("Newcostle", Newcostle);
	preTeams.put("Tan United", TanUtd);
	preTeams.put("Van City", VanCity);
	preTeams.put("Acton Villa", ActonVilla);
	preTeams.put("Chelsee", Chelsee);
	preTeams.put("East Ham", EastHam);
	preTeams.put("Leicestor", Leicestor);
	preTeams.put("Wolves", Wolves);
	preTeams.put("Upswitch", Upswitch);
	preTeams.put("Fullham", Fullham);
	preTeams.put("Evertun", Evertun);
	preTeams.put("Brighten", Brighten);
	preTeams.put("Nottingham Woods", NottinghamWoods);
	preTeams.put("Palace", Palace);
	preTeams.put("Bornmouth", Bornmouth);
	preTeams.put("Burntford", Burntford);
	preTeams.put("Southamton", Southamton);
	
	season = new Season(preTeams);
    
	window = new GameWindow();
    window.setVisible(true);
	
	StartPage startPage = new StartPage(this);
	startPage.displayPage();
	
	}
	
	public void startSeason() {}

	public GameWindow getWindow() {
		return window;
	}

	public void setWindow(GameWindow window) {
		this.window = window;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}
}
