package visuals.ScheduleFrames;
import java.time.LocalDateTime;
import javax.swing.*;

import entities.UsersMatch;
import entities.Team;
import people.Footballer;

public class Events {

	private LocalDateTime date;
	private String type;
	private Team team;
	private Footballer player;
	private JLabel title;
	private JLabel description;
	private UsersMatch match;
	private Boolean removeEvent;

	public Events(){};

	// The user has a match
	public Events(UsersMatch match) {
		this.date = match.getDateTime();
		this.description = new JLabel(match.getHome().getName() + " vs " + match.getAway().getName(), SwingConstants.CENTER);
		this.type = "Match";
		this.title  = new JLabel();
		this.match = match;
		this.removeEvent = false;
	}

	public Events(String person, String message, LocalDateTime dateTime){
		this.date = dateTime;
		this.removeEvent = false;
		String text = "<html><body style='width: %1spx; text-align: center;'>" + message + "</body></html>";
		this.description = new JLabel(String.format(text, 400), SwingConstants.CENTER);
		if(person.equals("Chairman")){
			this.type = "Chairman Message";
			this.title = new JLabel("Chairman");
		} else if (person.equals("Youth Coach")){
			this.type = ("Youth Coach Message");
			this.title = new JLabel("Youth Coach");
		} else if (person.equals("Result")) {
			this.type = ("Result");
			this.title = new JLabel("Result");
		}
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
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

	public UsersMatch getMatch() {
		return match;
	}

	public void setMatch(UsersMatch match) {
		this.match = match;
	}

	@Override
	public String toString() {
		return "Events [date=" + date + ", type=" + type + ", title=" + ", match=" + match + "]";
	}

	public Boolean getRemoveEvent() {
		return removeEvent;
	}

	public void setRemoveEvent(Boolean removeEvent) {
		this.removeEvent = removeEvent;
	}
}
