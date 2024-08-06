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
	private boolean anotherTry = false;
	
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

		for(int i = 0; i<weeks; i++) {
			Map<String, Match> currentMW = createMatchWeek(toLookThrough);
			matchWeeks.put(i + 1, currentMW);
			for(Map.Entry<String, Match> each : currentMW.entrySet()) {
				int j = i + 1;
				System.out.println("Match Week " + j + " contains: " + each.getKey());
				if(anotherTry==true) {
					System.out.println("Decreasing 'I'");
					i--;
				}
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
				System.out.println("Running While again");
				if(temporary.size() == 0) {
					System.out.println("Temporary is 0 again");
					temporary = new ArrayList<>(toLookThrough);
					MW.clear();
					
					if(attempts == 10) {
						//toLookThrough doesn't contain MW12 matches
						// They need to be readded
						System.out.println("Fixtures = " + tolook);
						System.out.println("MW12 = " + matchWeeks.get(12));
						System.out.println("Temporary = " + temporary);
						
						matchWeeks.remove(12);
						anotherTry = true;
					}
					
					attempts++;
					restart = true;
					continue;
				} else {
					int randomInt = (int) (Math.random() * temporary.size());
					Match chosen = temporary.get(randomInt);
					
					// Split this match into two teams by String
					String[] two = chosen.toString().split(" vs ");
					String team1 = two[0].trim();
					String team2 = two[1].trim();
					
					Boolean conflicts = false;
					
					// Check to see if this team is already playing this week
					for(Map.Entry<String, Match> each : MW.entrySet()) {
						if(each.getKey().contains(team1) || each.getKey().contains(team2)) {
							// We are removing this match from temporary
							// As we don't want to land on this match twice
							temporary.remove(chosen);
							conflicts = true;
							break;
						}	
					}
					
					// If the match doesn't contain conflicts, we can add
					if(!conflicts) {
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

	public ArrayList<Match> getToLookThrough() {
		return toLookThrough;
	}

	public void setToLookThrough(ArrayList<Match> toLookThrough) {
		this.toLookThrough = toLookThrough;
	}

	public ArrayList<Match> getTemporary() {
		return temporary;
	}

	public void setTemporary(ArrayList<Match> temporary) {
		this.temporary = temporary;
	}

	public Map<Integer, Map<String, Match>> getMatchWeeks() {
		return matchWeeks;
	}

	public void setMatchWeeks(Map<Integer, Map<String, Match>> matchWeeks) {
		this.matchWeeks = matchWeeks;
	}
	
}
