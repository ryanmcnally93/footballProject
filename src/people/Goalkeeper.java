package people;

public class Goalkeeper extends Footballer {

	private int keeping;
	
	public Goalkeeper(String name, int age, int keeping) {
		super(name, age);
		this.keeping = keeping;
		this.likedPosition = "GK";
		this.positionPlaced = "GK";
	}

	public int getKeeping() {
		return keeping;
	}

	public void setKeeping(int keeping) {
		this.keeping = keeping;
	}
	
}
