import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

// Update Character like in vehicle speed
// Make startrun smaller using more methods
// Call more functions from other .java?

// Players are called Characters, because that's how we started
public class Footballer  extends Individual {
	
	public int attack;
	public int defence;
	private String team;
	public int stamina; 
	private String position;
	
	// Define Player Characteristics
	
	public Footballer(String name, int age, int wealth) {
		super(name,age,wealth);
	}
	
	public Footballer(String name, int age, int wealth, int attack, int defence, int stamina, String team, String position) {
		super(name,age,wealth);
		this.attack = attack;
		this.defence = defence;
		this.team = team;
		this.stamina = stamina;
		this.position = position;
		super.setName(name);
	}
	
	/* This method will determine whether or not a player
	has successfully sprinted past his opponent, we are
	going to use math random against the likelihood out of 100 */

	public String getTeam() {
		return this.team;
	}
	
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
}
