package gameSetup;

import entities.Season;
import entities.Team;
import people.Footballer;
import people.Goalkeeper;
import people.Manager;

import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("TextBlockMigration")
public class initialSetup {
	
	private Season season;
	private GameWindow window;
	
	public initialSetup() {
        Map<String, Team> preTeams = new HashMap<>();
        preTeams.put("Arsenol", createArsenal());
        preTeams.put("Totenham", createTottenham());
        preTeams.put("Liverpule", createLiverpool());
        preTeams.put("Newcostle", createNewcastle());
        preTeams.put("Tan United", createManUtd());
        preTeams.put("Van City", createManCity());
        preTeams.put("Acton Villa", createAstonVilla());
        preTeams.put("Chelsee", createChelsea());
        preTeams.put("East Ham", createWestHam());
        preTeams.put("Leicestor", createLeicester());
        preTeams.put("Wolves", createWolves());
        preTeams.put("Upswitch", createIpswich());
        preTeams.put("Fullham", createFulham());
        preTeams.put("Evertun", createEverton());
        preTeams.put("Brighten", createBrighton());
        preTeams.put("Nottingham Woods", createNottinghamForest());
        preTeams.put("Palace", createCrystalPalace());
        preTeams.put("Bornmouth", createBournemouth());
        preTeams.put("Burntford", createBrentford());
        preTeams.put("Southamton", createSouthampton());

        season = new Season(preTeams);

        window = new GameWindow();
        window.setVisible(true);

        StartPage startPage = new StartPage(this);
        startPage.displayPage();
    }

    private Team createSouthampton() {
        Goalkeeper bazunu = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 76, 78,  "Gavin Bazunu", LocalDate.of(1993, 6, 10));
        Footballer alcarrz = FootballerFactory.createPlayer("ST", "Technically Gifted", 78, 80, "Carlos Alcarrz", LocalDate.of(1993, 6, 10));
        Footballer wardprose = FootballerFactory.createPlayer("RW", "Physically Strong", 79, 79, "James Wardprose", LocalDate.of(1993, 6, 10));
        Footballer peraud = FootballerFactory.createPlayer("LW", "Technically Gifted", 77, 78, "Romain Peraud", LocalDate.of(1993, 6, 10));
        Footballer lavie = FootballerFactory.createPlayer("CAM", "Technically Gifted", 78, 80, "Romeo Lavie", LocalDate.of(1993, 6, 10));
        Footballer arreole = FootballerFactory.createPlayer("CM1", "Playmaker", 75, 77, "Nicolas Arreole", LocalDate.of(1993, 6, 10));
        Footballer jankwitz = FootballerFactory.createPlayer("CM2", "Playmaker", 76, 79, "James Jankwitz", LocalDate.of(1993, 6, 10));
        Footballer karlsen = FootballerFactory.createPlayer("LB", "Physically Strong", 77, 79, "Lianco Karlsen", LocalDate.of(1993, 6, 10));
        Footballer salisu = FootballerFactory.createPlayer("CB1", "Physically Strong", 79, 79, "Mohammed Salisu", LocalDate.of(1993, 6, 10));
        Footballer bednarek = FootballerFactory.createPlayer("CB2", "Physically Strong", 80, 80, "Jan Bednarek", LocalDate.of(1993, 6, 10));
        Footballer walkr = FootballerFactory.createPlayer("RB", "Technically Gifted", 82, 84, "Kyle Walkr", LocalDate.of(1993, 6, 10));

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

        Manager gibbs = new Manager("Mark Gibbs", 33, 300000);
        Team Southamton = new Team("Southamton", gibbs, southamtonFirst, 45000000, "Southamton Stadium", Color.RED, Color.WHITE);
        Southamton.setCaptain(bednarek);
        return Southamton;
    }

    private Team createBrentford() {
        Goalkeeper rayz = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 76, 78, "David Rayz", LocalDate.of(1993, 6, 10));
        Footballer toney = FootballerFactory.createPlayer("ST", "Physically Strong", 78, 80, "Ivan Toney", LocalDate.of(1993, 6, 10));
        Footballer breetton = FootballerFactory.createPlayer("RW", "Technically Gifted", 79, 79, "Bryan Breetton", LocalDate.of(1993, 6, 10));
        Footballer dasilva = FootballerFactory.createPlayer("LW", "Technically Gifted", 77, 78, "Josh DaSilva", LocalDate.of(1993, 6, 10));
        Footballer janelt = FootballerFactory.createPlayer("CM1", "Playmaker", 75, 77, "Vitaly Janelt", LocalDate.of(1993, 6, 10));
        Footballer norgaard = FootballerFactory.createPlayer("CM2", "Playmaker", 76, 79, "Christian Norgaard", LocalDate.of(1993, 6, 10));
        Footballer hicksy = FootballerFactory.createPlayer("CAM", "Technically Gifted", 78, 80, "Aaron Hicksy", LocalDate.of(1993, 6, 10));
        Footballer henrey = FootballerFactory.createPlayer("LB", "Physically Strong", 77, 79, "Rico Henrey", LocalDate.of(1993, 6, 10));
        Footballer pinnok = FootballerFactory.createPlayer("CB1", "Physically Strong", 79, 79, "Ethan Pinnok", LocalDate.of(1993, 6, 10));
        Footballer mee = FootballerFactory.createPlayer("CB2", "Physically Strong", 80, 80, "Ben Mee", LocalDate.of(1993, 6, 10));
        Footballer canos = FootballerFactory.createPlayer("RB", "Technically Gifted", 82, 84, "Sergi Canos", LocalDate.of(1993, 6, 10));

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

        Manager mcnally = new Manager("Ryan McNally", 31, 300000);
        Team Burntford = new Team("Burntford", mcnally, burntfordFirst, 45000000, "Burntford Stadium", Color.RED, Color.WHITE);
        Burntford.setCaptain(norgaard);
        return Burntford;
    }

    private Team createBournemouth() {
        Goalkeeper ramstale = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 74, 77, "Aaron Ramstale", LocalDate.of(1993, 6, 10));
        Footballer solankc = FootballerFactory.createPlayer("ST", "Physically Strong", 76, 79, "Dominic Solankc", LocalDate.of(1993, 6, 10));
        Footballer antony = FootballerFactory.createPlayer("RW", "Technically Gifted", 75, 78, "Ryan Antony", LocalDate.of(1993, 6, 10));
        Footballer billong = FootballerFactory.createPlayer("LW", "Technically Gifted", 74, 77, "David Billong", LocalDate.of(1993, 6, 10));
        Footballer lerme = FootballerFactory.createPlayer("CM1", "Playmaker", 78, 81, "Jefferson Lerme", LocalDate.of(1993, 6, 10));
        Footballer ramsay = FootballerFactory.createPlayer("CM2", "Playmaker", 77, 80, "Maxime Ramsay", LocalDate.of(1993, 6, 10));
        Footballer cooke = FootballerFactory.createPlayer("CAM", "Technically Gifted", 79, 82, "Lewis Cooke", LocalDate.of(1993, 6, 10));
        Footballer zamor = FootballerFactory.createPlayer("LB", "Physically Strong", 73, 76, "Jordan Zamor", LocalDate.of(1993, 6, 10));
        Footballer mepham = FootballerFactory.createPlayer("CB1", "Physically Strong", 76, 79, "Chris Mepham", LocalDate.of(1993, 6, 10));
        Footballer smiths = FootballerFactory.createPlayer("CB2", "Physically Strong", 78, 80, "Adam Smiths", LocalDate.of(1993, 6, 10));
        Footballer travers = FootballerFactory.createPlayer("RB", "Technically Gifted", 77, 79, "Mark Travers", LocalDate.of(1993, 6, 10));

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

        Manager oneil = new Manager("Gary O'Neil", 41, 300000);
        Team Bornmouth = new Team("Bornmouth", oneil, bornmouthFirst, 45000000, "Bornmouth Stadium", Color.RED, Color.BLACK);
        Bornmouth.setCaptain(lerme);
        return Bornmouth;
    }

    private Team createNottinghamForest() {
        Goalkeeper hennessex = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 72, 75, "Wayne Hennessex", LocalDate.of(1993, 6, 10));
        Footballer awoniy = FootballerFactory.createPlayer("ST", "Physically Strong", 75, 78, "Taiwo Awoniy", LocalDate.of(1993, 6, 10));
        Footballer lingrd = FootballerFactory.createPlayer("RW", "Technically Gifted", 74, 77, "Jesse Lingrd", LocalDate.of(1993, 6, 10));
        Footballer johnsn = FootballerFactory.createPlayer("LW", "Technically Gifted", 73, 76, "Brennan Johnsn", LocalDate.of(1993, 6, 10));
        Footballer yates = FootballerFactory.createPlayer("CM1", "Playmaker", 77, 80, "Ryan Yates", LocalDate.of(1993, 6, 10));
        Footballer freuler = FootballerFactory.createPlayer("CM2", "Playmaker", 76, 79, "Remo Freuler", LocalDate.of(1993, 6, 10));
        Footballer pereira = FootballerFactory.createPlayer("CAM", "Technically Gifted", 78, 81, "Danilo Pereira", LocalDate.of(1993, 6, 10));
        Footballer lodi = FootballerFactory.createPlayer("LB", "Physically Strong", 72, 75, "Renan Lodi", LocalDate.of(1993, 6, 10));
        Footballer mckenna = FootballerFactory.createPlayer("CB1", "Physically Strong", 75, 78, "Scott Mckenna", LocalDate.of(1993, 6, 10));
        Footballer worrll = FootballerFactory.createPlayer("CB2", "Physically Strong", 76, 79, "Joe Worrll", LocalDate.of(1993, 6, 10));
        Footballer nico = FootballerFactory.createPlayer("RB", "Technically Gifted", 77, 79, "Neco Williams", LocalDate.of(1993, 6, 10));

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

        Manager cooper = new Manager("Steve Cooper", 44, 300000);
        Team NottinghamWoods = new Team("Nottingham Woods", cooper, nottinghamWoodsFirst, 45000000, "Woods Stadium", Color.RED, Color.WHITE);
        NottinghamWoods.setCaptain(pereira);
        return NottinghamWoods;
    }

    private Team createCrystalPalace() {
        Goalkeeper johnstone = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 73, 76, "Sam Johnstone", LocalDate.of(1993, 6, 10));
        Footballer eze = FootballerFactory.createPlayer("ST", "Physically Strong", 75, 78, "Eberechi Eze", LocalDate.of(1993, 6, 10));
        Footballer zah = FootballerFactory.createPlayer("RW", "Technically Gifted", 74, 77, "Wilfried Zah", LocalDate.of(1993, 6, 10));
        Footballer olise = FootballerFactory.createPlayer("LW", "Technically Gifted", 76, 79, "Michael Olise", LocalDate.of(1993, 6, 10));
        Footballer doucoure = FootballerFactory.createPlayer("CM1", "Playmaker", 77, 80, "Cheick Doucoure", LocalDate.of(1993, 6, 10));
        Footballer lurie = FootballerFactory.createPlayer("CM2", "Playmaker", 76, 79, "Jeffrey Lurie", LocalDate.of(1993, 6, 10));
        Footballer hughes = FootballerFactory.createPlayer("CAM", "Technically Gifted", 78, 81, "Will Hughes", LocalDate.of(1993, 6, 10));
        Footballer mitchell = FootballerFactory.createPlayer("LB", "Physically Strong", 72, 75, "Tyrick Mitchell", LocalDate.of(1993, 6, 10));
        Footballer gueh = FootballerFactory.createPlayer("CB1", "Physically Strong", 75, 78, "Marc Gueh", LocalDate.of(1993, 6, 10));
        Footballer andersn = FootballerFactory.createPlayer("CB2", "Physically Strong", 76, 79, "Jannik Andersn", LocalDate.of(1993, 6, 10));
        Footballer wrad = FootballerFactory.createPlayer("RB", "Technically Gifted", 77, 79, "Joel Wrad", LocalDate.of(1993, 6, 10));

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

        Manager edwards = new Manager("Rob Edwards", 40, 300000);
        Team Palace = new Team("Palace", edwards, palaceFirst, 45000000, "Palace Stadium", Color.BLUE, Color.RED);
        Palace.setCaptain(hughes);
        return Palace;
    }

    private Team createBrighton() {
        Goalkeeper steell = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 70, 73, "Jason Steell", LocalDate.of(1993, 6, 10));
        Footballer mitom = FootballerFactory.createPlayer("ST", "Physically Strong", 75, 78, "Kaoru Mitom", LocalDate.of(1993, 6, 10));
        Footballer marc = FootballerFactory.createPlayer("RW", "Technically Gifted", 74, 77, "Solly Marc", LocalDate.of(1993, 6, 10));
        Footballer estupinaz = FootballerFactory.createPlayer("LW", "Technically Gifted", 76, 79, "Pervis Estupinaz", LocalDate.of(1993, 6, 10));
        Footballer macalliste = FootballerFactory.createPlayer("CM1", "Playmaker", 77, 80, "Alexis Macalliste", LocalDate.of(1993, 6, 10));
        Footballer caicedo = FootballerFactory.createPlayer("CM2", "Playmaker", 76, 79, "Moises Caicedo", LocalDate.of(1993, 6, 10));
        Footballer gilmore = FootballerFactory.createPlayer("CAM", "Technically Gifted", 78, 81, "Billy Gilmore", LocalDate.of(1993, 6, 10));
        Footballer veltma = FootballerFactory.createPlayer("LB", "Physically Strong", 72, 75, "Joel Veltma", LocalDate.of(1993, 6, 10));
        Footballer dunk = FootballerFactory.createPlayer("CB1", "Physically Strong", 75, 78, "Lewis Dunk", LocalDate.of(1993, 6, 10));
        Footballer webste = FootballerFactory.createPlayer("CB2", "Physically Strong", 74, 77, "Adam Webste", LocalDate.of(1993, 6, 10));
        Footballer lamptey = FootballerFactory.createPlayer("RB", "Technically Gifted", 79, 81, "Tariq Lamptey", LocalDate.of(1993, 6, 10));

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

        Manager Ehowe = new Manager("Eddie Howe", 46, 300000);
        Team Brighten = new Team("Brighten", Ehowe, brightenFirst, 45000000, "Brighten Stadium", Color.BLUE, Color.BLACK);
        Brighten.setCaptain(lamptey);
        return Brighten;
    }

    private Team createEverton() {
        Goalkeeper pickfard = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 72, 75, "Jordan Pickfard", LocalDate.of(1993, 6, 10));
        Footballer calvertlewin = FootballerFactory.createPlayer("ST", "Physically Strong", 78, 80, "Dominic Calvert-Lewin", LocalDate.of(1993, 6, 10));
        Footballer grary = FootballerFactory.createPlayer("RW", "Technically Gifted", 75, 77, "Demarai Grary", LocalDate.of(1993, 6, 10));
        Footballer mcneil = FootballerFactory.createPlayer("LW", "Technically Gifted", 74, 76, "Dwight Mcneil", LocalDate.of(1993, 6, 10));
        Footballer gueye = FootballerFactory.createPlayer("CM1", "Playmaker", 73, 75, "Idrissa Gueye", LocalDate.of(1993, 6, 10));
        Footballer onan = FootballerFactory.createPlayer("CM2", "Playmaker", 72, 74, "Amadou Onan", LocalDate.of(1993, 6, 10));
        Footballer iwobi = FootballerFactory.createPlayer("CAM", "Technically Gifted", 76, 78, "Alex Iwobi", LocalDate.of(1993, 6, 10));
        Footballer mykalenko = FootballerFactory.createPlayer("LB", "Physically Strong", 70, 72, "Vitaliy Mykalenko", LocalDate.of(1993, 6, 10));
        Footballer tarkowsky = FootballerFactory.createPlayer("CB1", "Physically Strong", 73, 75, "James Tarkowsky", LocalDate.of(1993, 6, 10));
        Footballer coad = FootballerFactory.createPlayer("CB2", "Physically Strong", 72, 74, "Conor Coad", LocalDate.of(1993, 6, 10));
        Footballer patterson = FootballerFactory.createPlayer("RB", "Technically Gifted", 77, 79, "Nathan Patterson", LocalDate.of(1993, 6, 10));

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

        Manager pochettino = new Manager("Mauricio Pochettino", 52, 300000);
        Team Evertun = new Team("Evertun", pochettino, evertunFirst, 45000000, "Evertun Stadium", Color.BLUE, Color.BLUE);
        Evertun.setCaptain(tarkowsky);
        return Evertun;
    }

    private Team createFulham() {
        Goalkeeper ramsdake = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 76, 78, "Aaron Ramsdake", LocalDate.of(1993, 6, 10));
        Footballer mitrovich = FootballerFactory.createPlayer("ST", "Physically Strong", 82, 84, "Aleksandar Mitrovich", LocalDate.of(1993, 6, 10));
        Footballer wilson = FootballerFactory.createPlayer("RW", "Technically Gifted", 77, 79, "Harry Wilson", LocalDate.of(1993, 6, 10));
        Footballer perreir = FootballerFactory.createPlayer("LW", "Technically Gifted", 79, 81, "Andreas Perreir", LocalDate.of(1993, 6, 10));
        Footballer palhinh = FootballerFactory.createPlayer("CM1", "Playmaker", 80, 82, "Joao Palhinh", LocalDate.of(1993, 6, 10));
        Footballer reen = FootballerFactory.createPlayer("CM2", "Playmaker", 78, 80, "Harrison Reen", LocalDate.of(1993, 6, 10));
        Footballer lukic = FootballerFactory.createPlayer("CAM", "Technically Gifted", 81, 83, "Sasa Lukic", LocalDate.of(1993, 6, 10));
        Footballer robertz = FootballerFactory.createPlayer("LB", "Physically Strong", 72, 74, "Antonee Robertz", LocalDate.of(1993, 6, 10));
        Footballer diob = FootballerFactory.createPlayer("CB1", "Physically Strong", 75, 77, "Issa Diob", LocalDate.of(1993, 6, 10));
        Footballer adarabioyu = FootballerFactory.createPlayer("CB2", "Physically Strong", 74, 76, "Tosin Adarabioyu", LocalDate.of(1993, 6, 10));
        Footballer tete = FootballerFactory.createPlayer("RB", "Technically Gifted", 76, 78, "Sergi Tete", LocalDate.of(1993, 6, 10));

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

        Manager dyche = new Manager("Sean Dyche", 52, 300000);
        Team Fullham = new Team("Fullham", dyche, fullhamFirst, 45000000, "Fullham Stadium", Color.WHITE, Color.BLACK);
        Fullham.setCaptain(palhinh);
        return Fullham;
    }

    private Team createIpswich() {
        Goalkeeper waltom = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 75, 77, "Christian Waltom", LocalDate.of(1993, 6, 10));
        Footballer broadhed = FootballerFactory.createPlayer("ST", "Physically Strong", 72, 74, "Ellis Broadhed", LocalDate.of(1993, 6, 10));
        Footballer burnz = FootballerFactory.createPlayer("RW", "Technically Gifted", 74, 76, "Janoi Burnz", LocalDate.of(1993, 6, 10));
        Footballer clark = FootballerFactory.createPlayer("LW", "Technically Gifted", 73, 75, "Conor Clark", LocalDate.of(1993, 6, 10));
        Footballer evens = FootballerFactory.createPlayer("CM1", "Playmaker", 71, 73, "Lee Evens", LocalDate.of(1993, 6, 10));
        Footballer morsy = FootballerFactory.createPlayer("CM2", "Playmaker", 74, 76, "Sam Morsy", LocalDate.of(1993, 6, 10));
        Footballer luongo = FootballerFactory.createPlayer("CAM", "Technically Gifted", 75, 77, "Massimo Luongo", LocalDate.of(1993, 6, 10));
        Footballer kenlocke = FootballerFactory.createPlayer("LB", "Physically Strong", 70, 72, "Myles Kenlocke", LocalDate.of(1993, 6, 10));
        Footballer wolfenden = FootballerFactory.createPlayer("CB1", "Physically Strong", 69, 71, "Luke Wolfenden", LocalDate.of(1993, 6, 10));
        Footballer donacien = FootballerFactory.createPlayer("CB2", "Physically Strong", 68, 70, "Toto Donacien", LocalDate.of(1993, 6, 10));
        Footballer leigh = FootballerFactory.createPlayer("RB", "Technically Gifted", 76, 78, "Greg Leigh", LocalDate.of(1993, 6, 10));

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

        Manager smithe = new Manager("Dean Smithe", 52, 300000);
        Team Upswitch = new Team("Upswitch", smithe, upswitchFirst, 45000000, "Upswitch Stadium", Color.BLUE, Color.WHITE);
        Upswitch.setCaptain(evens);
        return Upswitch;
    }

    private Team createWolves() {
        Goalkeeper sarr = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 75, 77, "José Sárr", LocalDate.of(1993, 6, 10));
        Footballer cuhna = FootballerFactory.createPlayer("ST", "Physically Strong", 78, 80, "Matheus Cuhna", LocalDate.of(1993, 6, 10));
        Footballer podenc = FootballerFactory.createPlayer("RW", "Technically Gifted", 74, 76, "Daniel Podenc", LocalDate.of(1993, 6, 10));
        Footballer nett = FootballerFactory.createPlayer("LW", "Technically Gifted", 75, 77, "Pedro Nett", LocalDate.of(1993, 6, 10));
        Footballer neves = FootballerFactory.createPlayer("CM1", "Playmaker", 77, 79, "Rúben Neves", LocalDate.of(1993, 6, 10));
        Footballer lobato = FootballerFactory.createPlayer("CM2", "Playmaker", 72, 74, "Mateus Lobato", LocalDate.of(1993, 6, 10));
        Footballer nunes = FootballerFactory.createPlayer("CAM", "Technically Gifted", 76, 78, "Matheus Nunes", LocalDate.of(1993, 6, 10));
        Footballer marcal = FootballerFactory.createPlayer("LB", "Physically Strong", 70, 72, "Marçal", LocalDate.of(1993, 6, 10));
        Footballer kilman = FootballerFactory.createPlayer("CB1", "Physically Strong", 69, 71, "Max Kilman", LocalDate.of(1993, 6, 10));
        Footballer collins = FootballerFactory.createPlayer("CB2", "Physically Strong", 68, 70, "Hugo Collins", LocalDate.of(1993, 6, 10));
        Footballer casto = FootballerFactory.createPlayer("RB", "Technically Gifted", 76, 78, "Jonny Casto", LocalDate.of(1993, 6, 10));

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

        Manager hodgson = new Manager("Roy Hodgson", 76, 300000);
        Team Wolves = new Team("Wolves", hodgson, wolvesFirst, 45000000, "Wolves Stadium", Color.ORANGE, Color.WHITE);
        Wolves.setCaptain(neves);
        return Wolves;
    }

    private Team createLeicester() {
        Goalkeeper hermanson = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 70, 73, "Mads Hermansan", LocalDate.of(2000, 1, 1));
        Footballer vardi = FootballerFactory.createPlayer("ST", "Physically Strong", 75, 78, "Jamie Vardy", LocalDate.of(1987, 1, 1));
        Footballer iheanachos = FootballerFactory.createPlayer("RW", "Technically Gifted", 74, 77, "Kelechi Iheanacno", LocalDate.of(1997, 1, 1));
        Footballer madisonn = FootballerFactory.createPlayer("LW", "Technically Gifted", 73, 76, "James Madisen", LocalDate.of(1996, 1, 1));
        Footballer ndidy = FootballerFactory.createPlayer("CM1", "Playmaker", 72, 75, "Wilfred Ndidr", LocalDate.of(1997, 1, 1));
        Footballer soumara = FootballerFactory.createPlayer("CM2", "Playmaker", 70, 73, "Boubakary Soumara", LocalDate.of(1999, 1, 1));
        Footballer dewburyHull = FootballerFactory.createPlayer("CAM", "Technically Gifted", 74, 77, "Kiernan Dewsbury-Hall", LocalDate.of(1998, 1, 1));
        Footballer castagna = FootballerFactory.createPlayer("LB", "Physically Strong", 69, 71, "Timothy Castanre", LocalDate.of(1995, 1, 1));
        Footballer fase = FootballerFactory.createPlayer("CB1", "Physically Strong", 68, 70, "Wout Fas", LocalDate.of(1998, 1, 1));
        Footballer soutar = FootballerFactory.createPlayer("CB2", "Physically Strong", 67, 69, "Harry Souttqr", LocalDate.of(1999, 1, 1));
        Footballer riccardo = FootballerFactory.createPlayer("RB", "Technically Gifted", 75, 78, "Ricardo Pereirz", LocalDate.of(1994, 1, 1));

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

        Manager viera = new Manager("Patrick Vieira", 48, 300000);
        Team Leicestor = new Team("Leicestor", viera, leicestorFirst, 45000000, "Leicestor Stadium", Color.BLUE, Color.WHITE);
        Leicestor.setCaptain(castagna);
        return Leicestor;
    }

    private Team createWestHam() {
        Goalkeeper areole = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 70, 73, "Alphonse Areole", LocalDate.of(1993, 6, 10));
        Footballer antonia = FootballerFactory.createPlayer("ST", "Physically Strong", 75, 78, "Michail Antonia", LocalDate.of(1990, 6, 10));
        Footballer bowem = FootballerFactory.createPlayer("RW", "Technically Gifted", 74, 77, "Jarrod Bowem", LocalDate.of(1996, 6, 10));
        Footballer benrahme = FootballerFactory.createPlayer("LW", "Technically Gifted", 73, 76, "Said Benrahme", LocalDate.of(1995, 6, 10));
        Footballer paquetn = FootballerFactory.createPlayer("CAM", "Playmaker", 74, 77, "Lucas Paquetn", LocalDate.of(1997, 6, 10));
        Footballer wardPrewse = FootballerFactory.createPlayer("CM1", "Playmaker", 75, 78, "James Ward-Prewse", LocalDate.of(1994, 6, 10));
        Footballer soucik = FootballerFactory.createPlayer("CM2", "Physically Strong", 76, 79, "Tomas Soucik", LocalDate.of(1995, 6, 10));
        Footballer emerson = FootballerFactory.createPlayer("LB", "Physically Strong", 70, 73, "Emerson Palmari", LocalDate.of(1994, 6, 10));
        Footballer zoumx = FootballerFactory.createPlayer("CB1", "Physically Strong", 72, 75, "Kurt Zoumx", LocalDate.of(1995, 6, 10));
        Footballer aguerb = FootballerFactory.createPlayer("CB2", "Physically Strong", 71, 74, "Nayef Aguerb", LocalDate.of(1996, 6, 10));
        Footballer coufalr = FootballerFactory.createPlayer("RB", "Technically Gifted", 74, 77, "Vladimir Coufalr", LocalDate.of(1992, 6, 10));

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

        Manager moyes = new Manager("David Moyes", 61, 300000);
        Team EastHam = new Team("East Ham", moyes, eastHamFirst, 45000000, "East Ham Stadium", getClaret(), Color.WHITE);
        EastHam.setCaptain(wardPrewse);
        return EastHam;
    }

    private Team createManUtd() {
        Goalkeeper onano = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 70, 73, "Andre Onano", LocalDate.of(1995, 6, 10));
        Footballer rashfard = FootballerFactory.createPlayer("ST", "Physically Strong", 77, 80, "Marcus Rashfard", LocalDate.of(1997, 6, 10));
        Footballer anthony = FootballerFactory.createPlayer("RW", "Technically Gifted", 74, 77, "Anthony Matheus", LocalDate.of(1999, 6, 10));
        Footballer garnache = FootballerFactory.createPlayer("LW", "Technically Gifted", 72, 75, "Alejandro Garnache", LocalDate.of(2003, 6, 10));
        Footballer bruno = FootballerFactory.createPlayer("CAM", "Playmaker", 79, 82, "Bruno Fernandas", LocalDate.of(1992, 6, 10));
        Footballer casomiro = FootballerFactory.createPlayer("CM1", "Physically Strong", 78, 81, "Casomiro", LocalDate.of(1989, 6, 10));
        Footballer eriksem = FootballerFactory.createPlayer("CM2", "Playmaker", 75, 78, "Christian Eriksem", LocalDate.of(1989, 6, 10));
        Footballer shas = FootballerFactory.createPlayer("LB", "Physically Strong", 70, 73, "Luke Shas", LocalDate.of(1995, 6, 10));
        Footballer martinev = FootballerFactory.createPlayer("CB1", "Physically Strong", 72, 75, "Lisandro Martinev", LocalDate.of(1997, 6, 10));
        Footballer varanr = FootballerFactory.createPlayer("CB2", "Physically Strong", 71, 74, "Raphael Varanr", LocalDate.of(1992, 6, 10));
        Footballer dalog = FootballerFactory.createPlayer("RB", "Technically Gifted", 74, 77, "Diogo Dalog", LocalDate.of(1998, 6, 10));

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

        Manager tenHag = new Manager("Erik ten Hag", 54, 300000);
        Team TanUtd = new Team("Tan United", tenHag, tanUtdFirst, 45000000, "New Trafford Stadium", Color.RED, Color.WHITE);
        TanUtd.setCaptain(bruno);
        return TanUtd;
    }

    private Team createNewcastle() {
        Goalkeeper popa = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 70, 72, "Nick Popa", LocalDate.of(1991, 6, 10));
        Footballer isaak = FootballerFactory.createPlayer("ST", "Physically Strong", 75, 78, "Alexander Isaak", LocalDate.of(1999, 6, 10));
        Footballer almiran = FootballerFactory.createPlayer("RW", "Technically Gifted", 73, 75, "Miguel Almiran", LocalDate.of(1994, 6, 10));
        Footballer gordan = FootballerFactory.createPlayer("LW", "Technically Gifted", 70, 72, "Anthony Gordan", LocalDate.of(2000, 6, 10));
        Footballer tonalo = FootballerFactory.createPlayer("CAM", "Playmaker", 78, 80, "Sandro Tonalo", LocalDate.of(1999, 6, 10));
        Footballer guimaraez = FootballerFactory.createPlayer("CM1", "Playmaker", 76, 79, "Bruno Guimaraez", LocalDate.of(1997, 6, 10));
        Footballer longstaffh = FootballerFactory.createPlayer("CM2", "Playmaker", 74, 77, "Sean Longstaffh", LocalDate.of(1997, 6, 10));
        Footballer burm = FootballerFactory.createPlayer("LB", "Physically Strong", 69, 71, "Dan Burm", LocalDate.of(1992, 6, 10));
        Footballer botmam = FootballerFactory.createPlayer("CB1", "Physically Strong", 68, 70, "Sven Botmam", LocalDate.of(1999, 6, 10));
        Footballer schas = FootballerFactory.createPlayer("CB2", "Physically Strong", 67, 69, "Fabian Schas", LocalDate.of(1991, 6, 10));
        Footballer trippiar = FootballerFactory.createPlayer("RB", "Technically Gifted", 75, 77, "Kieran Trippiar", LocalDate.of(1990, 6, 10));

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

        Manager howe = new Manager("Eddie Howe", 46, 300000);
        Team Newcostle = new Team("Newcostle", howe, newcostleFirst, 45000000, "Newcostle Stadium", Color.BLACK, Color.WHITE);
        Newcostle.setCaptain(tonalo);
        return Newcostle;
    }

    private Team createLiverpool() {
        Goalkeeper alisson = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 75, 77, "Alisson Pecker", LocalDate.of(1992, 6, 10));
        Footballer nunaz = FootballerFactory.createPlayer("ST", "Physically Strong", 78, 80, "Darwin Nunaz", LocalDate.of(1999, 6, 10));
        Footballer salak = FootballerFactory.createPlayer("RW", "Technically Gifted", 79, 81, "Mohamed Salak", LocalDate.of(1992, 6, 10));
        Footballer diax = FootballerFactory.createPlayer("LW", "Technically Gifted", 74, 76, "Luis Diax", LocalDate.of(1996, 6, 10));
        Footballer szoboszlan = FootballerFactory.createPlayer("CAM", "Playmaker", 73, 75, "Dominik Szoboszlan", LocalDate.of(2000, 6, 10));
        Footballer macAllistir = FootballerFactory.createPlayer("CM1", "Playmaker", 75, 77, "Alexis Mac Allistir", LocalDate.of(1998, 6, 10));
        Footballer jomes = FootballerFactory.createPlayer("CM2", "Playmaker", 72, 74, "Curtis Jomes", LocalDate.of(2000, 6, 10));
        Footballer robertsen = FootballerFactory.createPlayer("LB", "Physically Strong", 70, 72, "Andrew Robertsen", LocalDate.of(1993, 6, 10));
        Footballer vanDikl = FootballerFactory.createPlayer("CB1", "Physically Strong", 74, 76, "Virgil van Dikl", LocalDate.of(1990, 6, 10));
        Footballer konete = FootballerFactory.createPlayer("CB2", "Physically Strong", 72, 74, "Ibrahima Konete", LocalDate.of(1997, 6, 10));
        Footballer alexanderArneld = FootballerFactory.createPlayer("RB", "Technically Gifted", 76, 78, "Trent Alexander-Arneld", LocalDate.of(1998, 6, 10));

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

        Manager klopp = new Manager("Jurgen Klopp", 56, 300000);
        Team Liverpule = new Team("Liverpule", klopp, liverpuleFirst, 45000000, "Liverpule Stadium", Color.RED, Color.YELLOW);
        Liverpule.setCaptain(vanDikl);
        return Liverpule;
    }

    private Team createChelsea() {
        Goalkeeper sanchaz = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 72, 74, "Robert Sanchaz", LocalDate.of(1997, 6, 10));
        Footballer jacksom = FootballerFactory.createPlayer("ST", "Physically Strong", 75, 77, "Nicolas Jacksom", LocalDate.of(2001, 6, 10));
        Footballer sterliny = FootballerFactory.createPlayer("RW", "Technically Gifted", 78, 80, "Raheem Sterliny", LocalDate.of(1994, 6, 10));
        Footballer mudryi = FootballerFactory.createPlayer("LW", "Technically Gifted", 74, 76, "Mykhailo Mudryi", LocalDate.of(1999, 6, 10));
        Footballer enzo = FootballerFactory.createPlayer("CAM", "Playmaker", 73, 75, "Enzo Fernamdez", LocalDate.of(1999, 6, 10));
        Footballer caicede = FootballerFactory.createPlayer("CM1", "Playmaker", 76, 78, "Moises Caicede", LocalDate.of(2000, 6, 10));
        Footballer gallaghar = FootballerFactory.createPlayer("CM2", "Playmaker", 74, 76, "Conor Gallaghar", LocalDate.of(1998, 6, 10));
        Footballer chilwoll = FootballerFactory.createPlayer("LB", "Physically Strong", 70, 72, "Ben Chilwoll", LocalDate.of(1996, 6, 10));
        Footballer silve = FootballerFactory.createPlayer("CB1", "Physically Strong", 68, 70, "Thiago Silve", LocalDate.of(1984, 6, 10));
        Footballer colwilk = FootballerFactory.createPlayer("CB2", "Physically Strong", 69, 71, "Levi Colwilk", LocalDate.of(2002, 6, 10));
        Footballer jamis = FootballerFactory.createPlayer("RB", "Technically Gifted", 75, 77, "Reece Jamis", LocalDate.of(1998, 6, 10));

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

        Manager lampard = new Manager("Frank Lampard", 45, 300000);
        Team Chelsee = new Team("Chelsee", lampard, chelseeFirst, 45000000, "Chelsee Stadium", Color.BLUE, Color.WHITE);
        Chelsee.setCaptain(caicede);
        return Chelsee;
    }

    private Team createManCity() {
        Goalkeeper ederrson = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 86, 88, "Ederrson Moraes", LocalDate.of(1993, 6, 10));
        Footballer haland = FootballerFactory.createPlayer("ST", "Physically Strong", 92, 95, "Erling Haland", LocalDate.of(1993, 6, 10));
        Footballer Dsilvo = FootballerFactory.createPlayer("RW", "Technically Gifted", 88, 89, "Bernardo Silvo", LocalDate.of(1993, 6, 10));
        Footballer grealosh = FootballerFactory.createPlayer("LW", "Technically Gifted", 86, 86, "Jack Grealosh", LocalDate.of(1993, 6, 10));
        Footballer deBruyan = FootballerFactory.createPlayer("CAM", "Technically Gifted", 91, 91, "Kevin De Bruyan", LocalDate.of(1993, 6, 10));
        Footballer rodri = FootballerFactory.createPlayer("CM1", "Physically Strong", 90, 90, "Rodri Hernandes", LocalDate.of(1993, 6, 10));
        Footballer gundogam = FootballerFactory.createPlayer("CM2", "Playmaker", 87, 87, "Ilkay Gundogam", LocalDate.of(1993, 6, 10));
        Footballer aka = FootballerFactory.createPlayer("LB", "Physically Strong", 85, 85, "Nathan Aka", LocalDate.of(1993, 6, 10));
        Footballer diaz = FootballerFactory.createPlayer("CB1", "Physically Strong", 84, 84, "Ruben Diaz", LocalDate.of(1993, 6, 10));
        Footballer stonas = FootballerFactory.createPlayer("CB2", "Physically Strong", 83, 83, "John Stonas", LocalDate.of(1993, 6, 10));
        Footballer walkar = FootballerFactory.createPlayer("RB", "Technically Gifted", 88, 88, "Kyle Walkar", LocalDate.of(1993, 6, 10));

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

        Manager guardiola = new Manager("Pep Guardiola", 53, 300000);
        Team VanCity = new Team("Van City", guardiola, vanCityFirst, 45000000, "Van City Stadium", getLightBlue(), Color.WHITE);
        VanCity.setCaptain(deBruyan);
        return VanCity;
    }

    private Team createTottenham() {
        Goalkeeper vicarios = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 80, 80, "Guglielmo Vicarios", LocalDate.of(1993, 6, 10));
        Footballer johnsun = FootballerFactory.createPlayer("ST", "Technically Gifted", 82, 83, "Brennan Jonnsun", LocalDate.of(1993, 6, 10));
        Footballer sun = FootballerFactory.createPlayer("RW", "Technically Gifted", 90, 90, "Heung-Min Sun", LocalDate.of(1993, 6, 10));
        Footballer kulusevsi = FootballerFactory.createPlayer("LW","Physically Strong", 84, 84, "Dejan Kulusevsi", LocalDate.of(1993, 6, 10));
        Footballer maddisom = FootballerFactory.createPlayer("CAM", "Playmaker", 83, 84, "James Maddisom", LocalDate.of(1993, 6, 10));
        Footballer bissouna = FootballerFactory.createPlayer("CM1", "Playmaker", 80, 82, "Yves Bissouna", LocalDate.of(1993, 6, 10));
        Footballer sara = FootballerFactory.createPlayer("CM2", "Technically Gifted", 81, 82, "Pape Matar Sara", LocalDate.of(1993, 6, 10));
        Footballer udogia = FootballerFactory.createPlayer("LB", "Physically Strong", 79, 80, "Destiny Udogia", LocalDate.of(1993, 6, 10));
        Footballer vanDeVem = FootballerFactory.createPlayer("CB1", "Physically Strong", 81, 81, "Micky van de Vem", LocalDate.of(1993, 6, 10));
        Footballer romaro = FootballerFactory.createPlayer("CB2", "Physically Strong", 80, 80, "Cristian Romaro", LocalDate.of(1993, 6, 10));
        Footballer poriro = FootballerFactory.createPlayer("RB", "Physically Strong", 78, 79, "Pedro Poriro", LocalDate.of(1993, 6, 10));

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

        Manager Mourinho = new Manager("Jose Mourinho", 56, 300000);
        Team Totenham = new Team("Totenham", Mourinho, totenhamFirst, 45000000, "Totenham Stadium", Color.WHITE, Color.BLUE);
        Totenham.setCaptain(maddisom);
        return Totenham;
    }

    private Color getClaret() {
        return new Color(129, 19, 49);
    }

    private Color getLightBlue() {
        return new Color(173, 216, 230);
    }

    private Team createArsenal() {
        Goalkeeper rava = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 84, 88, "David Raya", LocalDate.of(1993, 6, 10));
        Footballer jasus = FootballerFactory.createPlayer("ST", "Technically Gifted", 84, 85, "Gabriel Jasus", LocalDate.of(1993, 6, 10));
        Footballer trossart = FootballerFactory.createPlayer("LW", "Technically Gifted", 81, 84, "Leandro Trossard", LocalDate.of(1993, 6, 10));
        Footballer saka = FootballerFactory.createPlayer("RW", "Technically Gifted", 86, 92, "Bukayo Saka", LocalDate.of(1993, 6, 10));
        Footballer parley = FootballerFactory.createPlayer("CM1", "Physically Strong", 86, 88, "Thomas Parley", LocalDate.of(1993, 6, 10));
        Footballer ovegaard = FootballerFactory.createPlayer("CAM", "Technically Gifted", 85, 88, "Martin Ovegaard", LocalDate.of(1993, 6, 10));
        Footballer rire = FootballerFactory.createPlayer("CM2", "Leader", 89, 91, "Declan Rire", LocalDate.of(1993, 6, 10));
        Footballer tomiyasa = FootballerFactory.createPlayer("LB", "Technically Gifted", 79, 81, "Takehiro Tomiyasa", LocalDate.of(1993, 6, 10));
        Footballer salima = FootballerFactory.createPlayer("CB1", "Physically Strong", 86, 93, "William Saliba", LocalDate.of(1993, 6, 10));
        Footballer gabriel = FootballerFactory.createPlayer("CB2", "Physically Strong", 85, 89, "Gabriel Magalhares", LocalDate.of(1993, 6, 10));
        Footballer whites = FootballerFactory.createPlayer("RB", "Technically Gifted", 82,85, "Ben Whites", LocalDate.of(1993, 6, 10));

        Map<String, Footballer> arsenolFirst = new HashMap<>();
        arsenolFirst.put(rava.getPositionPlaced(), rava);
        arsenolFirst.put(jasus.getPositionPlaced(), jasus);
        arsenolFirst.put(trossart.getPositionPlaced(), trossart);
        arsenolFirst.put(saka.getPositionPlaced(), saka);
        arsenolFirst.put(ovegaard.getPositionPlaced(), ovegaard);
        arsenolFirst.put(parley.getPositionPlaced(), parley);
        arsenolFirst.put(rire.getPositionPlaced(), rire);
        arsenolFirst.put(tomiyasa.getPositionPlaced(), tomiyasa);
        arsenolFirst.put(gabriel.getPositionPlaced(), gabriel);
        arsenolFirst.put(salima.getPositionPlaced(), salima);
        arsenolFirst.put(whites.getPositionPlaced(), whites);

        Manager Arteta = new Manager("Mikel Arteta", 31, 30000);
        Team Arsenol = new Team("Arsenol", Arteta, arsenolFirst, 45000000, "Arsenol Stadium", Color.RED, Color.WHITE);
        Arsenol.setCaptain(ovegaard);
        return Arsenol;
    }

    private Team createAstonVilla() {
        Goalkeeper Emartinev = (Goalkeeper) FootballerFactory.createPlayer("GK", "Goalkeeper", 84, 84, "Emiliano Martinev", LocalDate.of(1993, 6, 10));
        Footballer watkinz = FootballerFactory.createPlayer("ST", "Technically Gifted", 83, 85, "Ollie Watkinz", LocalDate.of(1993, 6, 10));
        Footballer traora = FootballerFactory.createPlayer("RW", "Technically Gifted", 82, 84, "Bertrand Traora", LocalDate.of(1993, 6, 10));
        Footballer diabi = FootballerFactory.createPlayer("LW", "Technically Gifted", 78, 81, "Moussa Diabi", LocalDate.of(1993, 6, 10));
        Footballer buendio = FootballerFactory.createPlayer("CAM", "Technically Gifted", 79, 80, "Emiliano Buendio", LocalDate.of(1993, 6, 10));
        Footballer douglasLuix = FootballerFactory.createPlayer("CM1", "Playmaker", 78, 80, "Douglas Luix", LocalDate.of(1993, 6, 10));
        Footballer mcguinness = FootballerFactory.createPlayer("CM2", "Physically Strong", 80, 80, "John McGuinness", LocalDate.of(1993, 6, 10));
        Footballer digme = FootballerFactory.createPlayer("LB", "Physically Strong", 77, 79, "Lucas Digme", LocalDate.of(1993, 6, 10));
        Footballer konsi = FootballerFactory.createPlayer("CB1", "Physically Strong", 78, 80, "Ezri Konsi", LocalDate.of(1993, 6, 10));
        Footballer torrex = FootballerFactory.createPlayer("CB2", "Physically Strong", 75, 79, "Pau Torrex", LocalDate.of(1993, 6, 10));
        Footballer casp = FootballerFactory.createPlayer("RB", "Physically Strong", 78, 81,"Matty Casp", LocalDate.of(1993, 6, 10));

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

        Manager emery = new Manager("Unai Emery", 76, 300000);
        Team ActonVilla = new Team("Acton Villa", emery, actonVillaFirst, 45000000, "Acton Park", getClaret(), Color.BLUE);
        ActonVilla.setCaptain(watkinz);
        return ActonVilla;
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
