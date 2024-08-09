package visuals.ScheduleFrames;
import java.time.LocalDateTime;
import javax.swing.JLabel;
import general.UsersMatch;
import general.Team;
import people.Footballer;

public class Events {

	private LocalDateTime date;
	private String type;
	private Team team;
	private Footballer player;
	private JLabel title;
	private JLabel description;
	private UsersMatch match;
	
	// The user has a match
	public Events(UsersMatch match) {
		this.date = match.getDateTime();
		this.type = "Match";
		this.title  = new JLabel();
		this.match = match;
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
	
}
