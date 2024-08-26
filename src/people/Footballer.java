package people;

// Update Character like in vehicle speed
// Make startrun smaller using more methods
// Call more functions from other .java?

import entities.Team;

// Players are called Characters, because that's how we started
public class Footballer extends Individual {
	
	public int attack;
	public int defence;
	public int stamina; 
	public String likedPosition;
	private Team team;
	
	// Define Player Characteristics
	
	public Footballer(String name, int age) {
		super(name,age);
	}
	
	public Footballer(String name, int age, int attack, int defence, int stamina, String position) {
		super(name,age);
		this.attack = attack;
		this.defence = defence;
		this.stamina = stamina;
		this.likedPosition = position;

	}
	
	public int getStamina() {
		return this.stamina;
	}
	
	public void removeStamina(int number) {
		this.stamina -= number;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public String getLikedPosition() {
		return likedPosition;
	}

	public void setLikedPosition(String likedPosition) {
		this.likedPosition = likedPosition;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
