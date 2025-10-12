package visuals.CustomizedElements;

import entities.Team;

import javax.swing.*;

public class TeamAchievementLine extends AbstractStatBar {

    private JLabel position, clubName, played, wins, draws, losses, gf, ga, gd, points;
    private Team team;
	private static int initialPos = 0;

    public TeamAchievementLine(Team team, boolean showBorder) {
        super(20, showBorder);
        initialPos++;
        this.team = team;
        buildColumns();
    }

    @Override
    protected void buildColumns() {
        position = createColumn(String.valueOf(initialPos), 30);
        clubName = createColumn("", 150);
        played = createColumn("0", 40);
        wins = createColumn("0", 40);
        draws = createColumn("0", 40);
        losses = createColumn("0", 40);
        gf = createColumn("0", 40);
        ga = createColumn("0", 40);
        gd = createColumn("0", 40);
        points = createColumn("0", 50);
        // points.setFont(points.getFont().deriveFont(Font.BOLD));
    }

    public void updateFromAchievement(TeamAchievementLine line) {
        position.setText(String.valueOf(line.getPosition()));
        clubName.setText(line.getTeamName());
        played.setText(String.valueOf(line.getPlayed()));
        wins.setText(String.valueOf(line.getWins()));
        draws.setText(String.valueOf(line.getDraws()));
        losses.setText(String.valueOf(line.getLosses()));
        gf.setText(String.valueOf(line.getGoalsScored()));
        ga.setText(String.valueOf(line.getGoalsConceded()));
        gd.setText(String.valueOf(line.getGoalDifference()));
        points.setText(String.valueOf(line.getPoints()));
        refresh();
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
		return Integer.parseInt(position.getText());
	}

	public void setPosition(int position) {
		this.position.setText(String.valueOf(position));
	}

	public int getWins() {
		return Integer.parseInt(wins.getText());
	}

	public void addWin() {
        this.wins.setText(String.valueOf(Integer.parseInt(this.wins.getText()) + 1));
        this.points.setText(String.valueOf(Integer.parseInt(this.points.getText()) + 3));
	}

	public void addMatchPlayed(){
        this.played.setText(String.valueOf(Integer.parseInt(this.played.getText()) + 1));
	}

	public int getDraws() {
		return Integer.parseInt(draws.getText());
	}

	public void addDraw() {
        this.draws.setText(String.valueOf(Integer.parseInt(this.draws.getText()) + 1));
        this.points.setText(String.valueOf(Integer.parseInt(this.points.getText()) + 1));;
	}

	public int getLosses() {
		return Integer.parseInt(losses.getText());
	}

	public void addLoss() {
        this.losses.setText(String.valueOf(Integer.parseInt(this.losses.getText()) + 1));
	}

	public void removeLoss() {
        this.losses.setText(String.valueOf(Integer.parseInt(this.losses.getText()) - 1));
	}

	public void removeWin() {
        this.wins.setText(String.valueOf(Integer.parseInt(this.wins.getText()) - 1));
        this.points.setText(String.valueOf(Integer.parseInt(this.points.getText()) - 3));
	}

	public void removeDraw() {
        this.draws.setText(String.valueOf(Integer.parseInt(this.draws.getText()) - 1));
        this.points.setText(String.valueOf(Integer.parseInt(this.points.getText()) - 1));
	}

	public Integer getGoalDifference() {
		return Integer.parseInt(gd.getText());
	}

	public void setGoalDifference(Integer goalDifference) {
		this.gd.setText(String.valueOf(goalDifference));
	}

	public int getGoalsScored() {
		return Integer.parseInt(gf.getText());
	}

	public void setGoalsScored(int goalsScored) {
		this.gf.setText(String.valueOf(goalsScored));
	}
	
	public void addGoalsScored() {
        this.gf.setText(String.valueOf(Integer.parseInt(this.gf.getText()) + 1));
        this.gd.setText(String.valueOf(Integer.parseInt(this.gd.getText()) + 1));
	}
	
	public void addGoalsConceded() {
        this.ga.setText(String.valueOf(Integer.parseInt(this.ga.getText()) + 1));
        this.gd.setText(String.valueOf(Integer.parseInt(this.gd.getText()) - 1));
	}

	public int getGoalsConceded() {
		return Integer.parseInt(ga.getText());
	}

	public void setGoalsConceded(int goalsConceded) {
		this.ga.setText(String.valueOf(goalsConceded));
	}

	public Integer getPoints() {
		return Integer.parseInt(points.getText());
	}

	public void setPoints(int points) {
		this.points.setText(String.valueOf(points));
	}

	@Override
	public String toString() {
		return "TableLine [team=" + team.getName() + ", position=" + position.getText() + ", wins=" + wins.getText() + ", draws=" + draws.getText()
				+ ", losses=" + losses.getText() + ", goalDifference=" + gd.getText() + ", goalsScored=" + gf.getText()
				+ ", goalsConceded=" + ga.getText() + ", points=" + points.getText() + "]";
	}

	public void setWins(int wins) {
		this.wins.setText(String.valueOf(wins));
	}

	public void setDraws(int draws) {
		this.draws.setText(String.valueOf(draws));
	}

	public void setLosses(int losses) {
		this.losses.setText(String.valueOf(losses));
	}

	public void setPoints(Integer points) {
		this.points.setText(String.valueOf(points));
	}

	public int getPlayed() {
		return Integer.parseInt(played.getText());
	}

	public void setPlayed(int gamesPlayed) {
		this.played.setText(String.valueOf(gamesPlayed));
	}

}
