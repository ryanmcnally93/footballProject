package entities;
import java.awt.Color;
import java.util.Map;
import people.Footballer;
import people.Goalkeeper;
import people.Manager;

public class Team {

	private String name;
	private Manager manager;
	// Is this the best way to access all players?
	private Map<String, Footballer> players;
	private Map<String, Footballer> firstTeam;
	private long budget;
	// This will have to be a class eventually
	private String stadium;
	private Color primaryColour;
	private Color secondaryColour;
	private Formation formation;
	private Footballer captain;
	
	public Team(String name, Manager manager, Map<String, Footballer> players, long budget, String stadium, Color primaryColour, Color secondaryColour) {
		this.name = name;
		this.manager = manager;
		this.firstTeam = players;
		this.budget = budget;
		this.stadium = stadium;
		this.primaryColour = primaryColour;
		this.secondaryColour = secondaryColour;
		this.players = players;
		this.formation = new Formation(4, 3, 3);

		for(Map.Entry<String, Footballer> each : players.entrySet()){
			Footballer thisPlayer = each.getValue();
			thisPlayer.setTeam(this);
		}

	}
	
	public Goalkeeper getGoalkeeper() {
		return ((Goalkeeper) firstTeam.get("GK"));
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

	public Map<String, Footballer> getFirstTeam() {
		return firstTeam;
	}

	public void setFirstTeam(Map<String, Footballer> firstTeam) {
		this.firstTeam = firstTeam;
	}

	@Override
	public String toString() {
		return getName();
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Footballer getCaptain() {
		return captain;
	}

	public void setCaptain(Footballer captain) {
		this.captain = captain;
	}
}
