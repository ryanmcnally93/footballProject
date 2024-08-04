package leagueSetup;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import main.Match;
import main.Team;

public class League {

	private String name;
	private String country;
	private int numOfTeams;
	private Map<String, Team> teams;
	private int tier;
	private Map<String, Match> fixtures;
	private static int season = 0;
	
	public League(String name, String country, int numOfTeams, Map<String, Team> teams, int tier) {
		this.name = name;
		this.country = country;
		this.numOfTeams = numOfTeams;
		this.teams = teams;
		this.tier = tier;
		this.season++;
		this.fixtures = new HashMap<>();
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
