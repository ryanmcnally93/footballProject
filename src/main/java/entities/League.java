package entities;

import visuals.CustomizedElements.TeamAchievementLine;
import visuals.CustomizedElements.LeagueTable;
import visuals.CustomizedElements.PlayerLeaderboards;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class League {

	private String name, country;
	private int numOfTeams;
	private Map<String, Team> teams;
	private int tier;
	private Map<String, Match> fixtures;
	private Season season;
	private ArrayList<Match> mainFixtureList, temporaryFixtureList;
	private Map<Integer, Map<String, Match>> matchWeeksMatches;
	private Map<Integer, Map<Integer, LocalDateTime>> matchWeeksSlots;
	private boolean restartWholeProcess = false;
	private LeagueTable leagueTable;
	private PlayerLeaderboards leaderboard;
	
	public League(String name, String country, int numOfTeams, Map<String, Team> teams, int tier, Season season) {
		this.name = name;
		this.country = country;
		this.numOfTeams = numOfTeams;
		this.teams = teams;
		this.tier = tier;
		this.fixtures = new HashMap<>();
		this.matchWeeksMatches = new HashMap<>();
		this.matchWeeksSlots = new HashMap<>();
		this.leagueTable = new LeagueTable(this);
		this.season = season;
		this.leaderboard = new PlayerLeaderboards(this);

		// Creating visual League table
		// Adding all the teams to a list
		List<Team> teamNamesInOrder = new ArrayList<>();
		for(Map.Entry<String, Team> eachTeam : teams.entrySet()) {
			Team team = eachTeam.getValue();
			teamNamesInOrder.add(team);
		}
		// Sorting the teams by name, this is how the table will show when no games played
		Collections.sort(teamNamesInOrder, new Comparator<Team>() {
            @Override
            public int compare(Team t1, Team t2) {
                return t1.getName().compareTo(t2.getName());
            }
        });
		// This will create new lines for each team
		// They're called achievement lines because they will contain the teams statistics for the season
		for(int i=1;i<teamNamesInOrder.size()+1;i++){
			TeamAchievementLine line = new TeamAchievementLine(teamNamesInOrder.get(i-1));
			getLeagueTable().getAllLines().add(line);
		}
		// This will organise the teams, shouldn't really do much first time around
		// But if a table is constructed and teams have stats, this will re-sort the table by stats
		getLeagueTable().updateLinesInTableLogic();

	}
	
	public void assignFixturesToWeekNumber() {
		// This method creates all the fixtures for the season
		createFixtures();

		// Here we have two lists of fixtures, this will be useful when creating matchweeks
		mainFixtureList = new ArrayList<>(getFixtures().values());
		temporaryFixtureList = new ArrayList<>(mainFixtureList);

		// This finds out how many matchweeks will be played this season
		int weeks = (teams.size()-1)*2;

		for(int i = 0; i<weeks; i++) {
			// For each week, we create a matchweek
			Map<String, Match> currentMW = createMatchWeek(mainFixtureList);

			// If this boolean is true, there has been a clash of fixtures
			// when attempting to create this matchweek
			if(restartWholeProcess) {
				
				System.out.println("We are acting on the restart whole process!");

				// This will add the fixtures we took back to our main list
				for (Map<String, Match> eachWeek : matchWeeksMatches.values()) {
		            mainFixtureList.addAll(eachWeek.values());
		        }

				// Now we clear the matchweeks, and restart the process by re-setting 'i'
				matchWeeksMatches.clear();
				i = -1;
				restartWholeProcess = false;
				
			} else {
				// This is a successful matchweek creation
				// So we add it to the collection of matchweek maps and number it so we can call it later
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
		temporaryFixtureList  = new ArrayList<>(mainFixtureList);

		// Let's work out how many matches we need per week
		int matchesNeededPerWeek = getTeams().size()/2;
		System.out.println(matchesNeededPerWeek);

		// If we attempt this too many times, we want to look to reset rather than
		// Continue a potentially endless loop
		int attempts = 0;
        Boolean restart;
		do {
			restart = false;
			// As long as we haven't got enough fixtures and we haven't been asked to restart
			// Let's choose matches to add to the matchweek
			while(MW.size() < matchesNeededPerWeek && !restartWholeProcess) {

				System.out.println("Main Fixture List Matches:  "+ mainFixtureList.size());
				System.out.println("Temporary Fixture List Matches:  "+temporaryFixtureList.size());
				// This means we've run out of matches to try and add to the matchweek
				// None of the available matches left are suitable
				if(temporaryFixtureList.isEmpty()) {
					
					System.out.println("Temporary is 0 again");

					// Let's first just clear the matchweek, and try again
					// We may just have added an awkward collection of matches
					temporaryFixtureList = new ArrayList<>(mainFixtureList);
					MW.clear();

					// We've done this 10 times now, this issue probably isn't going to resolve itself
					// Or cannot, so we will trigger a restart
					if(attempts == 10) {
						System.out.println("Restarting Whole Process!");
						restartWholeProcess  = true;
						attempts = 0;
						break;
					} else {
						attempts++;
					}
					System.out.println("Attempts: " + attempts);
					restart = true;
				} else {
					// This will use math.random to choose a random fixture
					int randomInt = (int) (Math.random() * temporaryFixtureList.size());
					Match chosen = temporaryFixtureList.get(randomInt);
					
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
							temporaryFixtureList.remove(chosen);
							conflicts = true;
							break;
						}	
					}
					
					// If the match doesn't contain conflicts, we can add
					if(!conflicts) {
						System.out.println("Match added: " + chosen.toString());
						MW.put(chosen.toString(), chosen);
						temporaryFixtureList.remove(chosen);
					}
				}
			}
		} while (restart);
		
		// This removes fixtures from the mainFixtureList
		Iterator<Match> iterator = tolook.iterator();
        while (iterator.hasNext()) {
            Match item = iterator.next();
            // Check if the match exists as a key in the Map
            if (MW.containsValue(item)) {
				// If so, remove the match from the list
                iterator.remove();
            }
        }

		return MW;
	}
	
	public void createFixtures() {
		// For each team
		for(Map.Entry<String, Team> each : teams.entrySet()) {
			Team current = each.getValue();
			// For each team again
			for(Map.Entry<String, Team> otherEach : teams.entrySet()) {
				Team opposition = otherEach.getValue();

				// We're going to check that the team we got on the first loop
				// Isn't equal to the second, then create a home match
				if(!current.getName().equals(opposition.getName())) {
					Match fixture = new Match(current, opposition, this);
					// We're going to check here that the away team hasn't already created this fixture
					if(!fixtures.containsKey(current.getName() + " vs " + opposition.getName())) {
						fixtures.put(current.getName() + " vs " + opposition.getName(), fixture);
					}
				}
				
			}
			// Now we're going to do the same to create this teams away fixtures
			for(Map.Entry<String, Team> otherEach : teams.entrySet()) {
				Team opposition = otherEach.getValue();
				
				if(!current.getName().equals(opposition.getName())) {
					Match fixture = new Match(opposition, current, this);
					// Checking again that the match doesn't already exist
					if(!fixtures.containsKey(opposition.getName() + " vs " + current.getName())) {
						fixtures.put(opposition.getName() + " vs " + current.getName(), fixture);
					}
				}
				
			}
		}
	}

	public void assignSlotsToMatches() {

		// For each of our matchweeks
		for(int i = 1; i<=matchWeeksMatches.size() ; i++ ) {

			// This matchweek
			Map<String, Match> thisWeeksMatches = matchWeeksMatches.get(i);
			// This weeks available time slots
			Map<Integer, LocalDateTime> thisWeeksSlots = matchWeeksSlots.get(i);

			// Helpful local variables
			Match match;
			int originalSize = thisWeeksSlots.size();
			LocalDateTime slot;

			// For each match this week
			for(Map.Entry<String, Match> matches : thisWeeksMatches.entrySet()) {

				match = matches.getValue();
				int randomInt = 0;
				slot = null;

				// Randomly assign an available slot to a match
				while(slot == null) {
					randomInt = 1 + (int) (Math.random() * originalSize);
					slot = thisWeeksSlots.get(randomInt);
				}
				match.setDateTime(slot);

				// Remove the slot so it isn't used again
				thisWeeksSlots.remove(randomInt);

				System.out.println(match + ": " + match.getDateTime());
			}
		}
		
	}
	
	public void assignDatetimesToWeekNumber() {
		// Let's find what year we are in
		int startYear = 2023 + season.getNumber();
		System.out.println("Giving times to season " + season.getYearFrom() + " to " + season.getYearTo());

		// Let's find the first available Saturday, and add fixture slots relative to that
		// So many of these weeks will be normal Friday-Sunday weekends, this is called a 'normalWeekend'
		// We will also eventually add another method for midweek fixtures
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
		// This needs to be changed as it will be first midweek matchweek
		normalWeekend(14, saturday.plusWeeks(16));
		normalWeekend(15, saturday.plusWeeks(17));
		normalWeekend(16, saturday.plusWeeks(18));
		normalWeekend(17, saturday.plusWeeks(19));
		normalWeekend(18, saturday.plusWeeks(20));
		normalWeekend(19, saturday.plusWeeks(21));
		normalWeekend(20, saturday.plusWeeks(23));
		normalWeekend(21, saturday.plusWeeks(24));
		normalWeekend(22, saturday.plusWeeks(25));
		normalWeekend(23, saturday.plusWeeks(26));
		normalWeekend(24, saturday.plusWeeks(27));
		normalWeekend(25, saturday.plusWeeks(28));
		normalWeekend(26, saturday.plusWeeks(29));
		normalWeekend(27, saturday.plusWeeks(30));
		normalWeekend(28, saturday.plusWeeks(32));
		normalWeekend(29, saturday.plusWeeks(33));
		normalWeekend(30, saturday.plusWeeks(34));
		normalWeekend(31, saturday.plusWeeks(35));
		normalWeekend(32, saturday.plusWeeks(36));
		normalWeekend(33, saturday.plusWeeks(37));
		normalWeekend(34, saturday.plusWeeks(38));
		normalWeekend(35, saturday.plusWeeks(39));
		normalWeekend(36, saturday.plusWeeks(40));
		normalWeekend(37, saturday.plusWeeks(41));
		normalWeekend(38, saturday.plusWeeks(43));

		// This provides a list of matches and times back to the console
		for(Map.Entry<Integer,
				Map<Integer, LocalDateTime>> matches : matchWeeksSlots.entrySet()){
			Map<Integer, LocalDateTime> thisWeek = matches.getValue();
			Integer thisWeekNumber = matches.getKey();
			System.out.println("Match Week " + thisWeekNumber + " slots: " + thisWeek);
		}
	}
	
	public void normalWeekend(int matchWeek, LocalDateTime saturday){
		// This method enters generates times for a normal weekend
		Map<Integer, LocalDateTime> thisWeek = new HashMap<>();

		// This is our Friday evening fixture
		LocalDateTime eight = saturday.minusDays(1).withHour(20).withMinute(0).withSecond(0).withNano(0);
		thisWeek.put(1, eight);

		// These are our Saturday fixtures
		LocalDateTime twelveThirty = saturday.withHour(12).withMinute(30).withSecond(0).withNano(0);
		LocalDateTime three = saturday.withHour(15).withMinute(0).withSecond(0).withNano(0);
		LocalDateTime fiveThirty = saturday.withHour(17).withMinute(30).withSecond(0).withNano(0);

		// Here we add several 3pm fixtures, 10 weekly games maximum as largest league
		// At the moment only has 20 teams
		thisWeek.put(2, twelveThirty);
		thisWeek.put(3, three);
		thisWeek.put(4, three);
		thisWeek.put(5, three);
		thisWeek.put(6, three);
		thisWeek.put(7, fiveThirty);

		// These are our Sunday fixtures
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
        // Find the closest saturday to mid August
        int daysUntilSaturday = DayOfWeek.SATURDAY.getValue() - dayOfWeek.getValue();
		LocalDateTime firstSaturday;
		if (daysUntilSaturday == 0) {
            // It's already Saturday
			firstSaturday = firstGameDay;
        } else if (daysUntilSaturday > 0) {
            // Saturday is within the same week
			firstSaturday = firstGameDay.plusDays(daysUntilSaturday);
        } else {
            // Saturday was in the previous week
			firstSaturday = firstGameDay.minusDays(Math.abs(daysUntilSaturday));
        }
		
		System.out.println("Our first Saturday is: " + firstSaturday);
		return firstSaturday;
	}

	// Getters & Setters

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

	public PlayerLeaderboards getPlayerLeaderboard() {
		return leaderboard;
	}

	public void setPlayerLeaderboard(PlayerLeaderboards leaderboard) {
		this.leaderboard = leaderboard;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}

	public void getFixturesToString() {
		// Returns all league matches this season
		for(Map.Entry<String, Match> each : fixtures.entrySet()) {
			Match value = each.getValue();
			System.out.println(value.toString());
		}
	}
	
	public void getTeamFixturesToString(Team team) {
		// Returns all fixtures for specific team
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

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public ArrayList<Match> getMainFixtureList() {
		return mainFixtureList;
	}

	public void setMainFixtureList(ArrayList<Match> mainFixtureList) {
		this.mainFixtureList = mainFixtureList;
	}

	public ArrayList<Match> getTemporary() {
		return temporaryFixtureList;
	}

	public void setTemporary(ArrayList<Match> temporary) {
		this.temporaryFixtureList = temporary;
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

	public boolean isRestartWholeProcess() {
		return restartWholeProcess;
	}

	public void setRestartWholeProcess(boolean restartWholeProcess) {
		this.restartWholeProcess = restartWholeProcess;
	}

	public Map<Integer, Map<Integer, LocalDateTime>> getMatchWeeksSlots() {
		return matchWeeksSlots;
	}

	public void setMatchWeeksSlots(Map<Integer, Map<Integer, LocalDateTime>> matchWeeksSlots) {
		this.matchWeeksSlots = matchWeeksSlots;
	}

	public ArrayList<Match> getTemporaryFixtureList() {
		return temporaryFixtureList;
	}

	public void setTemporaryFixtureList(ArrayList<Match> temporaryFixtureList) {
		this.temporaryFixtureList = temporaryFixtureList;
	}

	public PlayerLeaderboards getLeaderboard() {
		return leaderboard;
	}

	public void setLeaderboard(PlayerLeaderboards leaderboard) {
		this.leaderboard = leaderboard;
	}
}
