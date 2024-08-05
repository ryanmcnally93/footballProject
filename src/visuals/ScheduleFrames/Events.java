package visuals.ScheduleFrames;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;

import general.Match;
import general.Team;
import people.Footballer;

public class Events {

	private LocalDate date;
	private String type;
	private Team team;
	private Footballer player;
	private JLabel title;
	private JLabel description;
	private Match match;
	
	// The user has a match
	private Events(Match match, LocalDate date) {
		this.date = date;
		this.type = "Match";
		this.title  = new JLabel();
		this.match = match;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Footballer getPlayer() {
		return player;
	}

	public void setPlayer(Footballer player) {
		this.player = player;
	}

	public JLabel getTitle() {
		return title;
	}

	public void setTitle(JLabel title) {
		this.title = title;
	}

	public JLabel getDescription() {
		return description;
	}

	public void setDescription(JLabel description) {
		this.description = description;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
	
}
