package people;

import java.util.Map;

public class Goalkeeper extends Footballer {

	private int keeping;

    public Goalkeeper() {};

    public Goalkeeper(String name, Map<String, Integer> attributes) {
        super(name, "GK", "Goalkeeper", attributes);
    }

	public int getKeeping() {
		return keeping;
	}

	public void setKeeping(int keeping) {
		this.keeping = keeping;
	}
	
}
