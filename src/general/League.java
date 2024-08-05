package general;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class League {

	private String name;
	private String country;
	private int numOfTeams;
	private Map<String, Team> teams;
	private int tier;
	private Map<String, Match> fixtures;
	private static int season = 0;
	private ArrayList<Match> toLookThrough, temporary;
	private Map<Integer, Map<String, Match>> matchWeeks;
	
	public League(String name, String country, int numOfTeams, Map<String, Team> teams, int tier) {
		this.name = name;
		this.country = country;
		this.numOfTeams = numOfTeams;
		this.teams = teams;
		this.tier = tier;
		this.season++;
		this.fixtures = new HashMap<>();
		this.matchWeeks = new HashMap<>();
	}
	
	public void seasonSetup() {
		createFixtures();
		
		toLookThrough = new ArrayList<Match>(getFixtures().values());
		temporary = new ArrayList<>(toLookThrough);
		
		int weeks = (teams.size()-1)*2;

		for(int i=0; i<weeks; i++) {
			Map<String, Match> currentMW = createMatchWeek(toLookThrough);
			matchWeeks.put(i + 1, currentMW);
			for(Map.Entry<String, Match> each : currentMW.entrySet()) {
				int j = i + 1;
				System.out.println("Match Week " + j + " contains: " + each.getKey());
			}
		}
		
//		Example of how to retrieve a week's information
//		System.out.println(matchWeeks.get(2));
		
	}
	
	public Map<String, Match> createMatchWeek(ArrayList<Match> tolook){
		Map<String, Match> MW = new HashMap<>();
		
		int attempts = 0;
        int maxAttempts = 100;
        Boolean restart;
		do {
			restart = false;
			while(MW.size() < 4 && attempts < maxAttempts) {
				attempts++;
				
				if(temporary.size() == 0) {
					temporary = new ArrayList<>(toLookThrough);
					restart = true;
					continue;
				} else {
					int randomInt = (int) (Math.random() * temporary.size());
					Match chosen = temporary.get(randomInt);
					
					// Split this match into two teams by String
					String[] two = chosen.toString().split(" vs ");
					String team1 = two[0].trim();
					String team2 = two[1].trim();
					
					// Set checker
					Boolean checker = false;
					
					// Check to see if this team is already playing this week
					
					for(Map.Entry<String, Match> each : MW.entrySet()) {
						if(each.getKey().contains(team1) || each.getKey().contains(team2)) {
							temporary.remove(chosen);
							MW.clear();
							attempts = 0;
							checker = true;
							break;
						}	
					}
					
					if(!checker) {
						MW.put(chosen.toString(), chosen);
						temporary.remove(chosen);
					}
				}
			}
		} while (restart);
		
		// Removing from the temporary fixtures list
		Iterator<Match> iterator = tolook.iterator();
        while (iterator.hasNext()) {
            Match item = iterator.next();
            // Check if the item exists as a key in the Map
            if (MW.containsValue(item)) {
                iterator.remove(); // Remove the item from the list
            }
        }
		
		return MW;
	}
	
	public void createFixtures() {
		for(Map.Entry<String, Team> each : teams.entrySet()) {
			Team current = each.getValue();
			// Setup home games
			for(Map.Entry<String, Team> otherEach : teams.entrySet()) {
				Team opposition = otherEach.getValue();
				
				if(!current.getName().equals(opposition.getName())) {
					Match fixture = new Match(current, opposition);
					if(!fixtures.containsKey(current.getName() + " vs " + opposition.getName())) {
						fixtures.put(current.getName() + " vs " + opposition.getName(), fixture);
					}
				}
				
			}
			// Setup away games
			for(Map.Entry<String, Team> otherEach : teams.entrySet()) {
				Team opposition = otherEach.getValue();
				
				if(!current.getName().equals(opposition.getName())) {
					Match fixture = new Match(opposition, current);
					if(!fixtures.containsKey(opposition.getName() + " vs " + current.getName())) {
						fixtures.put(opposition.getName() + " vs " + current.getName(), fixture);
					}
				}
				
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getNumOfTeams() {
		return numOfTeams;
	}

	public void setNumOfTeams(int numOfTeams) {
		this.numOfTeams = numOfTeams;
	}

	public Map<String, Team> getTeams() {
		return teams;
	}

	public void setTeams(Map<String, Team> teams) {
		this.teams = teams;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}

	public void getFixturesToString() {
		for(Map.Entry<String, Match> each : fixtures.entrySet()) {
			Match value = each.getValue();
			System.out.println(value.toString());
		}
	}
	
	public void getTeamFixturesToString(Team team) {
		for(Map.Entry<String, Match> each : fixtures.entrySet()) {
			Match value = each.getValue();
			String key = each.getKey();
			if(key.contains(team.getName())) {
				System.out.println(value.toString());
			}
		}
	}

	public Map<String, Match> getFixtures() {
		return fixtures;
	}

	public void setFixtures(Map<String, Match> fixtures) {
		this.fixtures = fixtures;
	}

	public static int getSeason() {
		return season;
	}

	public static void setSeason(int season) {
		League.season = season;
	}
	
}
