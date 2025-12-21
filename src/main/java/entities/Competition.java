package entities;

import java.util.*;

public class Competition {

	private String name, country;
	private Map<String, Team> teams;
	private Map<String, Match> fixtures;
	private Season season;
    private Map<Integer, Map<String, Match>> matchWeeksMatches;

    public Competition() {};

	protected Competition(String name, String country, Map<String, Team> teams, Season season) {
		this.name = name;
		this.country = country;
        this.matchWeeksMatches = new HashMap<>();
        if (teams.isEmpty()) {
            throw new IllegalArgumentException("You cannot start a Competition with no teams");
        }
		this.teams = teams;
		this.fixtures = new HashMap<>();
		this.season = season;
	}

    void getFixturesToString() {
        // Returns all league matches this season
        for(Map.Entry<String, Match> each : fixtures.entrySet()) {
            Match value = each.getValue();
            System.out.println(value.toString());
        }
    }

    void getTeamFixturesToString(Team team) {
        // Returns all fixtures for specific team
        for(Map.Entry<String, Match> each : fixtures.entrySet()) {
            Team homeTeam = each.getValue().getHome();
            Team awayTeam = each.getValue().getAway();
            if(homeTeam.equals(team) || awayTeam.equals(team)) {
                System.out.println(each.getValue().toString());
            }
        }
    }

    public List<String> getRoundNames() {
        return null;
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

	public Map<String, Team> getTeams() {
		return teams;
	}

	public void setTeams(Map<String, Team> teams) {
		this.teams = teams;
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

    public Map<Integer, Map<String, Match>> getMatchWeeksMatches() {
        return matchWeeksMatches;
    }

    public void setMatchWeeksMatches(Map<Integer, Map<String, Match>> matchWeeksMatches) {
        this.matchWeeksMatches = matchWeeksMatches;
    }
}
