package visuals.ScheduleFrames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import general.League;
import general.Match;
import general.UsersMatch;
import general.Team;
import general.User;
import visuals.CustomizedElements.CustomizedButton;
import visuals.MatchFrames.GameWindow;
import visuals.MatchFrames.MatchFrames;

public class Scheduler extends JPanel {

	private static final long serialVersionUID = -949295084027854854L;
	private LocalDateTime date;
	private Box header;
	private JPanel eventsBox, south;
	private JButton advance, playGame;
	private Team team;
	private User user;
	private League league;
	private ArrayList<Events> events;
	private JLabel todaysDate;
	private JPanel mainPanel;
	private GameWindow window;
	private Box eventContainer;
	private UsersMatch match;
	
	// New Game Constructor
	public Scheduler(User user, Team team, League league) {
		this.date = LocalDateTime.of(2024,  6, 1, 0, 0);
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
        east.setPreferredSize(new Dimension(100,200));
        mainPanel.add(east, BorderLayout.EAST); 
        
        Box west = Box.createHorizontalBox();
		west.setPreferredSize(new Dimension(100,200));
        mainPanel.add(west, BorderLayout.WEST);
        
		south = new JPanel();
		todaysDate = new JLabel("Today's date is: " + getDate());
		advance = new JButton("Advance");
		south.add(todaysDate);
		south.add(advance);
		mainPanel.add(south, BorderLayout.SOUTH);
		
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
				south.remove(playGame);
				south.add(advance);
			}
		}
	}
	
	public void addDay() {
		date = date.plusDays(1);
		todaysDate.setText("Today's date is: " + getDate());
		
		// WE NEED TO GIVE THIS YEAR THE VALUE OF SEASON
		
		// This will decide which matches are played on which week
		if(date.toLocalDate().isEqual(LocalDate.of(2024, 06, 05))) {
			league.assignFixturesToWeekNumber();
		}
		// This will set the dates for each match
		if(date.toLocalDate().isEqual(LocalDate.of(2024, 06, 06))) {
			league.assignDatetimesToWeekNumber();
		}
		if(date.toLocalDate().isEqual(LocalDate.of(2024, 06, 07))) {
			league.assignSlotsToMatches();
		}
		if(date.toLocalDate().isEqual(LocalDate.of(2024, 06, 8))) {
			
			setMatchdays();
			
		}
		
		// Here we are looking through the events
		// Looking for todays events
		// Displaying them on the front screen
		// WHY DO WE NEED TO HAVE A MATCH BELONGING TO SCHEDULE?
		for(Events each : events) {
			if(each.getDate().toLocalDate().equals(getDate().toLocalDate())) {
				showEvent(each);
				if(each.getType().equals("Match")) {
					this.match = each.getMatch();
				}
			}
		}
		
		// For each fixture (after populated)
		// If they're not my usermatch
		// And if they have the same date as today
		// the match plays (Or doesn't)
		if(getDate().toLocalDate().isAfter(LocalDate.of(2024, 06, 8))) {
			for(Map.Entry<String, Match> each : league.getFixtures().entrySet()) {
				Match match = each.getValue();
				if (!(match instanceof UsersMatch)) {
					
					// This will eventually need to look at match time
					// And only play the matches before this fixture
					
					if(match.getDateTime().toLocalDate().equals(getDate().toLocalDate())) {
						
						System.out.println("A match should play now *************************");
						
						match.startMatch();
						
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					}
				}
			}
		}
	}
		
	public void showEvent(Events event) {
		eventContainer = Box.createHorizontalBox();
		eventContainer.setPreferredSize(new Dimension(600,440));
		JLabel matchTitle = new JLabel(event.getMatch().getHome().getName() + " vs " + event.getMatch().getAway().getName());
		
		// THIS POSITIONS THE MATCH TITLE AT TOP
//		matchTitle.setAlignmentY(Component.TOP_ALIGNMENT);
		
		eventContainer.add(Box.createHorizontalGlue());
		eventContainer.add(matchTitle);
		eventContainer.add(Box.createHorizontalGlue());
		
		south.remove(advance);
		playGame = new JButton("Play");
		Scheduler sch = this;
		playGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				event.getMatch().displayGame(window, sch);
			}
		});
		south.add(playGame);
		
		mainPanel.add(eventContainer, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	public void setMatchdays() {

		List<String> keysToReplace = new ArrayList<>();
		
		// This makes a note of which matches need to be changed, and puts to array
		for(Map.Entry<String, Match> each : league.getFixtures().entrySet()) {
			if(each.getKey().contains(team.getName())) {
				keysToReplace.add(each.getKey());
			}
		}
		
		// This overwrites each entry with a child UsersMatch
		for (String key : keysToReplace) {
            Match adult = league.getFixtures().get(key);
            if (adult != null) {
                UsersMatch child = new UsersMatch(adult.getHome(), adult.getAway(), league); // Create ChildClass instance
                child.setDateTime(adult.getDateTime());
                league.getFixtures().put(key, child); // Replace the entry in the map
            }
        }
		
		// This looks for UsersMatches and creates an event from it
		for(Map.Entry<String, Match> each : league.getFixtures().entrySet()) {
			Match eachMatch = each.getValue();
			if (eachMatch instanceof UsersMatch) {
				Events matchEvent = new Events((UsersMatch) eachMatch);
				events.add(matchEvent);
			}
		}

		System.out.println(events);
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
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
