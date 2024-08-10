package general;

public class TableLine {

	private Team team;
	private int position;
	private int wins;
	private int draws;
	private int losses;
	private int goalDifference;
	private int goalsScored;
	private int goalsConceded;
	private Integer points;
	private static int initialPos = 0;
	
	public TableLine(Team team) {
		this.team = team;
		this.wins = 0;
		this.draws = 0;
		this.losses = 0;
		this.goalDifference = 0;
		this.goalsScored = 0;
		this.goalsConceded = 0;
		this.points = 0;
		initialPos++;
		this.position = initialPos;
	}

	public Team getTeam() {
		return team;
	}
	
	public String getTeamName() {
		return team.getName();
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getWins() {
		return wins;
	}

	public void addWin() {
		this.wins += 1;
		this.points +=3;
	}

	public int getDraws() {
		return draws;
	}

	public void addDraw() {
		this.draws += 1;
		this.points +=1;
	}

	public int getLosses() {
		return losses;
	}

	public void addLoss() {
		this.losses += 1;
	}

	public int getGoalDifference() {
		return goalDifference;
	}

	public void setGoalDifference(int goalDifference) {
		this.goalDifference = goalDifference;
	}

	public int getGoalsScored() {
		return goalsScored;
	}

	public void setGoalsScored(int goalsScored) {
		this.goalsScored = goalsScored;
	}

	public int getGoalsConceded() {
		return goalsConceded;
	}

	public void setGoalsConceded(int goalsConceded) {
		this.goalsConceded = goalsConceded;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "TableLine [team=" + team.getName() + ", position=" + position + ", wins=" + wins + ", draws=" + draws
				+ ", losses=" + losses + ", goalDifference=" + goalDifference + ", goalsScored=" + goalsScored
				+ ", goalsConceded=" + goalsConceded + ", points=" + points + "]";
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
	
}
