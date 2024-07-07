package people;

public class Goalkeeper extends Footballer {

	private int keeping;
	
	public Goalkeeper(String name, int age, int wealth, int keeping, String team, String position) {
		super(name, age, wealth, team, position);
		this.keeping = keeping;
	}

	public int getKeeping() {
		return keeping;
	}
	
}
