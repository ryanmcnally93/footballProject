package visuals.ScheduleFrames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import general.League;
import general.Match;
import general.Team;
import general.User;
import visuals.CustomizedElements.CustomizedButton;
import visuals.MatchFrames.GameWindow;

public class Scheduler extends JPanel {

	private static final long serialVersionUID = -949295084027854854L;
	private LocalDate date;
	private Box header;
	private JPanel eventsBox;
	private JButton advance;
	private Team team;
	private User user;
	private League league;
	private ArrayList<Events> events;
	private JLabel todaysDate;
	private JPanel mainPanel;
	private GameWindow window;
	private Box eventContainer;
	private Match match;
	
	// New Game Constructor
	public Scheduler(User user, Team team, League league) {
		this.date = LocalDate.of(2024,  6, 1);
		this.user = user;
		this.team = team;
		this.league = league;
		this.events = new ArrayList<Events>();
		
		setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
		
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        header = Box.createHorizontalBox();
        header.setPreferredSize(new Dimension(800, 80));
        JLabel title = new JLabel(team.getName() + " - " + user.getName() + " season " + league.getSeason(), SwingConstants.CENTER);
        title.setFont(new Font("Menlo", Font.BOLD, 30));
        header.add(title);
        mainPanel.add(header, BorderLayout.NORTH);
        
        Box east = Box.createHorizontalBox();
        east.setPreferredSize(new Dimension(20,200));
        mainPanel.add(east, BorderLayout.EAST); 
        
        Box west = Box.createHorizontalBox();
		west.setPreferredSize(new Dimension(20,200));
        mainPanel.add(west, BorderLayout.WEST);
        
		JPanel center = new JPanel();
		todaysDate = new JLabel("Today's date is: " + getDate());
		advance = new JButton("Advance");
		center.add(todaysDate);
		center.add(advance);
		mainPanel.add(center, BorderLayout.SOUTH);
		
		advance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addDay();
			}
		});
		
		add(mainPanel, BorderLayout.CENTER);
		
		revalidate();
		repaint();
        
	}
	
	public void displayPage(GameWindow window) {
		this.window= window;
		window.getContentPane().removeAll();
		window.getContentPane().add(this, BorderLayout.CENTER);
		window.revalidate();
		window.repaint();
		if(match != null) {
			if(match.getMinute() == 90) {
				eventContainer.removeAll();
			}
		}
	}
	
	public void addDay() {
		date = date.plusDays(1);
		todaysDate.setText("Today's date is: " + getDate());
		if(date.isEqual(LocalDate.of(2024, 06, 05))) {
			league.seasonSetup();
			setMatchdays();
		}
		for(Events each : events) {
			if(each.getDate().equals(getDate())) {
				showEvent(each);
				if(each.getType().equals("Match")) {
					this.match = each.getMatch();
				}
			}
		}
	}
	
	public void showEvent(Events event) {
		eventContainer = Box.createHorizontalBox();
		eventContainer.setPreferredSize(new Dimension(600,440));
		JLabel matchTitle = new JLabel(event.getMatch().getHome().getName() + " vs " + event.getMatch().getAway().getName());
		eventContainer.add(matchTitle);
		JButton playGame = new JButton("Play");
		Scheduler sch = this;
		playGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				event.getMatch().displayGame(window, sch);
			}
		});
		eventContainer.add(playGame);
		
		mainPanel.add(eventContainer, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	public void setMatchdays() {
		Map<String, Match> MW1 = league.getMatchWeeks().get(1);
		for(Map.Entry<String, Match> each : MW1.entrySet()) {
			if(each.getKey().contains(team.getName())) {
				System.out.println("Found a match: " + each.getValue().getHome().getName() + " vs " + each.getValue().getAway().getName());
				Match ourMatch = each.getValue();
				Events matchOne = new Events(ourMatch, LocalDate.of(2024, 6, 10));
				events.add(matchOne);
				break;
			}
		}
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Box getHeader() {
		return header;
	}

	public void setHeader(Box header) {
		this.header = header;
	}

	public JPanel getEventsBox() {
		return eventsBox;
	}

	public void setEventsBox(JPanel eventsBox) {
		this.eventsBox = eventsBox;
	}

	public JButton getAdvance() {
		return advance;
	}

	public void setAdvance(JButton advance) {
		this.advance = advance;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
