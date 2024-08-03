package leagueSetup;

import java.util.Map;

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
		for(int i=0;i<numOfTeams;i++) {
			Team current = teams.get(i);
			// Setup home games
			for(int j=0;j<numOfTeams;j++) {
				Team opposition = teams.get(j);
				
				if(current.getName().equals(opposition.getName())) {
					System.out.println("Cannot play yourself");
				} else {
					System.out.println("Match created: " + current.getName() + " vs " + opposition.getName());
				}
				
			}
			// Setup away games
			for(int k=0;k<numOfTeams;k++) {
				Team opposition = teams.get(k);
				
				if(current.getName().equals(opposition.getName())) {
					System.out.println("Cannot play yourself");
				} else {
					System.out.println("Match created: " + opposition.getName() + " vs " + current.getName());
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
	
}
