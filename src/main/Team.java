package main;
import java.awt.Color;
import java.util.Map;
import people.Footballer;
import people.Manager;

public class Team {

	private String name;
	private Manager manager;
	// Is this the best way to access all players?
	private Map<String, Footballer> players;
	private long budget;
	// This will have to be a class eventually
	private String stadium;
	private Color primaryColour;
	private Color secondaryColour;
	
	public Team(String name, Manager manager, Map<String, Footballer> players, long budget, String stadium, Color primaryColour, Color secondaryColour) {
		this.name = name;
		this.manager = manager;
		this.players = players;
		this.budget = budget;
		this.stadium = stadium;
		this.primaryColour = primaryColour;
		this.secondaryColour = secondaryColour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Map<String, Footballer> getPlayers() {
		return players;
	}

	public void setPlayers(Map<String, Footballer> players) {
		this.players = players;
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public Color getPrimaryColour() {
		return primaryColour;
	}

	public void setPrimaryColour(Color primaryColour) {
		this.primaryColour = primaryColour;
	}

	public Color getSecondaryColour() {
		return secondaryColour;
	}

	public void setSecondaryColour(Color secondaryColour) {
		this.secondaryColour = secondaryColour;
	}
	
}
