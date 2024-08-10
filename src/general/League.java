package general;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
	private Map<Integer, Map<String, Match>> matchWeeksMatches;
	private Map<Integer, Map<Integer, LocalDateTime>> matchWeeksSlots;
	private boolean restartWholeProcess = false;
	private LeagueTable leagueTable;
	
	public League(String name, String country, int numOfTeams, Map<String, Team> teams, int tier) {
		this.name = name;
		this.country = country;
		this.numOfTeams = numOfTeams;
		this.teams = teams;
		this.tier = tier;
		this.fixtures = new HashMap<>();
		this.matchWeeksMatches = new HashMap<>();
		this.matchWeeksSlots = new HashMap<>();
		this.leagueTable = new LeagueTable(this);
		
		List<Team> teamNamesInOrder = new ArrayList<>();
		for(Map.Entry<String, Team> eachTeam : teams.entrySet()) {
			Team team = eachTeam.getValue();
			teamNamesInOrder.add(team);
		}
		Collections.sort(teamNamesInOrder, new Comparator<Team>() {
            @Override
            public int compare(Team t1, Team t2) {
                return t1.getName().compareTo(t2.getName());
            }
        });
		for(int i=1;i<teamNamesInOrder.size()+1;i++){
			TableLine line = new TableLine(teamNamesInOrder.get(i-1));
			getLeagueTable().getAllLines().add(line);
		}
		
		getLeagueTable().updateLinesInTableLogic();
	}
	
	public void assignFixturesToWeekNumber() {
		createFixtures();
		season++;
		
		toLookThrough = new ArrayList<Match>(getFixtures().values());
		temporary = new ArrayList<>(toLookThrough);
		
		int weeks = (teams.size()-1)*2;

		for(int i = 0; i<weeks; i++) {
			Map<String, Match> currentMW = createMatchWeek(toLookThrough);
			if(restartWholeProcess  ==  true) {
				
				System.out.println("We are acting on the restart whole process!");
				
				for (Map<String, Match> eachWeek : matchWeeksMatches.values()) {
		            toLookThrough.addAll(eachWeek.values());
		        }
				
				matchWeeksMatches.clear();
				i = -1;
				restartWholeProcess = false;
				
			} else {
				matchWeeksMatches.put(i + 1, currentMW);
				for(Map.Entry<String, Match> each : currentMW.entrySet()) {
					int j = i + 1;
					System.out.println("Match Week " + j + " contains: " + each.getKey());
				}
			}
		}
	}
	
	public Map<String, Match> createMatchWeek(ArrayList<Match> tolook){
		Map<String, Match> MW = new HashMap<>();
		temporary  = new ArrayList<>(toLookThrough);
		
		int attempts = 0;
        Boolean restart;
		do {
			restart = false;
			while(MW.size() < 4 && restartWholeProcess  == false) {
				
				System.out.println("Running While again");
				System.out.println("To Look Through Size:  "+toLookThrough.size());
				System.out.println("Temporary Size:  "+temporary.size());
				if(temporary.size() == 0) {
					
					System.out.println("Temporary is 0 again");
					
					temporary = new ArrayList<>(toLookThrough);
					MW.clear();
					
					if(attempts == 10) {
						System.out.println("Restarting Whole Process!");
						restartWholeProcess  = true;
						attempts = 0;
						break;
					} else {
						attempts++;
					}
					System.out.println(attempts);
					restart = true;
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
						System.out.println("Match added: " + chosen.toString());
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
					Match fixture = new Match(current, opposition, this);
					if(!fixtures.containsKey(current.getName() + " vs " + opposition.getName())) {
						fixtures.put(current.getName() + " vs " + opposition.getName(), fixture);
					}
				}
				
			}
			// Setup away games
			for(Map.Entry<String, Team> otherEach : teams.entrySet()) {
				Team opposition = otherEach.getValue();
				
				if(!current.getName().equals(opposition.getName())) {
					Match fixture = new Match(opposition, current, this);
					if(!fixtures.containsKey(opposition.getName() + " vs " + current.getName())) {
						fixtures.put(opposition.getName() + " vs " + current.getName(), fixture);
					}
				}
				
			}
		}
	}

	public void assignSlotsToMatches() {
		// STEP 1
		for(int i = 1; i<=matchWeeksMatches.size() ; i++ ) {
			
			Map<String, Match> thisWeeksMatches = matchWeeksMatches.get(i);
			Map<Integer, LocalDateTime> thisWeeksSlots = matchWeeksSlots.get(i);
			Match match;
			for(Map.Entry<String, Match> matches : thisWeeksMatches.entrySet()) {
				match = matches.getValue();
				int randomInt = 0;
				LocalDateTime slot = null;
				
				while(slot == null) {
					randomInt = 1 + (int) (Math.random() * (thisWeeksSlots.size() - 1));
					slot = thisWeeksSlots.get(randomInt);
				}
				
				match.setDateTime(slot);
				
				thisWeeksSlots.remove(randomInt);
				
				System.out.println(match.toString() + ": " + match.getDateTime());
			}
		}
		
		// For each of the 14 matchWeeks
		
		
		
		
		// Look through matches
		// Look through available slots
		// Clear two Maps and create a third that contains 
		// Map<Integer*(MW)*, Map<Match, DateTime>>
		
		// STEP 2
		
		// Create a class called absent matches that does the same thing as
		// Match class but without talking to matchframes or responding to user.
		// This class must be created and ran when the user leaves the day
		// that that match is on, print to console, must excluse users game.
	}
	
	public void assignDatetimesToWeekNumber() {
		int startYear = 2023 + season;
		LocalDateTime saturday = findFirstSaturday(startYear);
		normalWeekend(1, saturday);
		normalWeekend(2, saturday.plusWeeks(1));
		normalWeekend(3, saturday.plusWeeks(2));
		normalWeekend(4, saturday.plusWeeks(4));
		normalWeekend(5, saturday.plusWeeks(5));
		normalWeekend(6, saturday.plusWeeks(6));
		normalWeekend(7, saturday.plusWeeks(7));
		normalWeekend(8, saturday.plusWeeks(9));
		normalWeekend(9, saturday.plusWeeks(10));
		normalWeekend(10, saturday.plusWeeks(11));
		normalWeekend(11, saturday.plusWeeks(12));
		normalWeekend(12, saturday.plusWeeks(14));
		normalWeekend(13, saturday.plusWeeks(15));
		// This needs to be changed
		normalWeekend(14, saturday.plusWeeks(16)); // First Tuesday Fixtures
		System.out.println("Match Week 1 slots: " + matchWeeksSlots.get(1));
		System.out.println("Match Weel 2 slots: " + matchWeeksSlots.get(2));
		System.out.println("Match Weel 3 slots: " + matchWeeksSlots.get(3));
		System.out.println("Match Weel 4 slots: " + matchWeeksSlots.get(4));
		System.out.println("Match Weel 5 slots: " + matchWeeksSlots.get(5));
		System.out.println("Match Weel 6 slots: " + matchWeeksSlots.get(6));
		System.out.println("Match Weel 7 slots: " + matchWeeksSlots.get(7));
		System.out.println("Match Weel 8 slots: " + matchWeeksSlots.get(8));
		System.out.println("Match Weel 9 slots: " + matchWeeksSlots.get(9));
		System.out.println("Match Weel 10 slots: " + matchWeeksSlots.get(10));
		System.out.println("Match Weel 11 slots: " + matchWeeksSlots.get(11));
		System.out.println("Match Weel 12 slots: " + matchWeeksSlots.get(12));
		System.out.println("Match Weel 13 slots: " + matchWeeksSlots.get(13));
		System.out.println("Match Weel 14 slots: " + matchWeeksSlots.get(14));
	}
	
	public void normalWeekend(int matchWeek, LocalDateTime saturday){
		// This needs to be a separate method, somewhere else preferrably
		// We will use this to enter a matchweek and this will generate
		// times for a normal weekend
		Map<Integer, LocalDateTime> thisWeek = new HashMap<>();
		
		LocalDateTime eight = saturday.minusDays(1).withHour(20).withMinute(0).withSecond(0).withNano(0);
		
		thisWeek.put(1, eight);
		
		LocalDateTime twelveThirty = saturday.withHour(12).withMinute(30).withSecond(0).withNano(0);
		LocalDateTime three = saturday.withHour(15).withMinute(0).withSecond(0).withNano(0);
		LocalDateTime fiveThirty = saturday.withHour(17).withMinute(30).withSecond(0).withNano(0);
		
		thisWeek.put(2, twelveThirty);
		thisWeek.put(3, three);
		thisWeek.put(4, three);
		thisWeek.put(5, three);
		thisWeek.put(6, three);
		thisWeek.put(7, fiveThirty);
		
		LocalDateTime two = saturday.plusDays(1).withHour(14).withMinute(0).withSecond(0).withNano(0);
		LocalDateTime fourThirty = saturday.plusDays(1).withHour(16).withMinute(30).withSecond(0).withNano(0);
		LocalDateTime sundayEight = saturday.plusDays(1).withHour(20).withMinute(0).withSecond(0).withNano(0);
		
		thisWeek.put(8, two);
		thisWeek.put(9, fourThirty);
		thisWeek.put(10, sundayEight);
		
		matchWeeksSlots.put(matchWeek, thisWeek);
	}
	
	public LocalDateTime findFirstSaturday(int year) {
		LocalDateTime firstGameDay = LocalDateTime.of(year, 8, 15, 0, 0);
        DayOfWeek dayOfWeek = firstGameDay.getDayOfWeek();
        // Find the closest saturday to that
        int daysUntilSaturday = DayOfWeek.SATURDAY.getValue() - dayOfWeek.getValue();
		LocalDateTime closestSaturday;
		if (daysUntilSaturday == 0) {
            // It's already Saturday
            closestSaturday = firstGameDay;
        } else if (daysUntilSaturday > 0) {
            // Saturday is within the same week
            closestSaturday = firstGameDay.plusDays(daysUntilSaturday);
        } else {
            // Saturday was in the previous week
            closestSaturday = firstGameDay.minusDays(Math.abs(daysUntilSaturday));
        }
		
		System.out.println("Our first date is: " + closestSaturday);
		return closestSaturday;
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

	public Map<Integer, Map<String, Match>> getMatchWeeksMatches() {
		return matchWeeksMatches;
	}

	public void setMatchWeeksMatches(Map<Integer, Map<String, Match>> matchWeeksMatches) {
		this.matchWeeksMatches = matchWeeksMatches;
	}

	public LeagueTable getLeagueTable() {
		return leagueTable;
	}

	public void setLeagueTable(LeagueTable leagueTable) {
		this.leagueTable = leagueTable;
	}
	
}
