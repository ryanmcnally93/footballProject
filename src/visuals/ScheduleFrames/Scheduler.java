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
	private JPanel header;
	private JPanel eventsBox, south;
	private JButton advance, playGame, advanceToGame;
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
        
        header = new JPanel();
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

		Events chairmanMessage = new Events("Chairman", "Welcome to " + team.getName() + " FC! We've been preparing for your arrival for some time and wish you the best of luck for the season ahead.", getDate());
		events.add(chairmanMessage);
		Events youthCoachMessage = new Events("Youth Coach", "Welcome boss, I've put together a list of the top players in the academy for you to take a look at.", getDate());
		events.add(youthCoachMessage);

		refreshMessages();

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
				if(league.getLeagueTable().getLine(team).getPosition() == 1){
					Events chairmanMessage = new Events("Chairman", "This is absolutely incredible! We are top of the league! From all of the staff and players, we thank you for your hard work!", getDate());
					events.add(chairmanMessage);
					refreshMessages();
				}
			}
		}
	}

	public void refreshMessages(){
		ArrayList<Events> todaysEvents  = new ArrayList<>();
		for(Events each : events) {
			if(each.getDate().toLocalDate().equals(getDate().toLocalDate())) {
				todaysEvents.add(each);
			}
		}

		// This ensures match is the last event today
		Events matchEvent = null;
		for(Events each : todaysEvents){
			if(each.getType().equals("Match")) {
				this.match = each.getMatch();
				matchEvent = each;
				todaysEvents.remove(each);
			}
		}
		if(matchEvent != null){
			todaysEvents.add(matchEvent);
		}

		showTodaysEvents(todaysEvents);
	}

	public void showTodaysEvents(ArrayList<Events> todaysEvents){
		// And now we will show todays events
		for(Events event : todaysEvents){
			showEventsDescription(event);
			south.remove(advance);
			if (event.getType().equals("Match")){
				playGame = new JButton("Play");
				Scheduler sch = this;
				playGame.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						event.getMatch().displayGame(window, sch);
					}
				});
				south.add(playGame);
			} else {
				JButton dismiss = new JButton("Dismiss");
				dismiss.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						eventContainer.removeAll();
						south.remove(dismiss);
						south.add(advance);
						todaysEvents.remove(event);
						events.remove(event);
						showTodaysEvents(todaysEvents);
						eventContainer.repaint();
						south.revalidate();
						south.repaint();
					}
				});
				south.add(dismiss);
				break;
			}
		}
	}

	public void showEventsDescription(Events event) {
		System.out.println("This is that event's stuff");
		eventContainer = Box.createHorizontalBox();
		eventContainer.setPreferredSize(new Dimension(600,40));
		JLabel description = event.getDescription();

		eventContainer.add(Box.createHorizontalGlue());
		eventContainer.add(description);
		eventContainer.add(Box.createHorizontalGlue());

		mainPanel.add(eventContainer, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	public void addDay() {
		eventContainer.removeAll();
		mainPanel.repaint();
		date = date.plusDays(1);
		todaysDate.setText("Today's date is: " + getDate());

		int year = 2023 + league.getSeason();

		// This will decide which matches are played on which week
		if(date.toLocalDate().isEqual(LocalDate.of(year, 06, 05))) {
			league.assignFixturesToWeekNumber();
		}

		// This will assign times to matchweeks
		if(date.toLocalDate().isEqual(LocalDate.of(year, 06, 06))) {
			league.assignDatetimesToWeekNumber();
		}

		// This will assign times to matches
		if(date.toLocalDate().isEqual(LocalDate.of(year, 06, 07))) {
			league.assignSlotsToMatches();
		}

		// This will create Events for each match, by turning this teams matches to UserMatch's
		if(date.toLocalDate().isEqual(LocalDate.of(year, 06, 8))) {
			setMatchdays();
		}

		// This will produce a skip to first match button when on 9th June
		if(date.toLocalDate().isEqual(LocalDate.of(year,06,9))){
			advanceToGame = new JButton("Skip to Matchday");
			advanceToGame.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int counter = 0;
					while(counter == 0){
						addDay();
						for(Events each : events) {
							if (each.getDate().toLocalDate().equals(getDate().toLocalDate())) {
								counter++;
							}
						}
					}
					south.remove(advanceToGame);
				}
			});
			south.add(advanceToGame);
		}

		refreshMessages();
		
		// This will 'play' the background matches
		if(getDate().toLocalDate().isAfter(LocalDate.of(year, 06, 8))) {
			for(Map.Entry<String, Match> each : league.getFixtures().entrySet()) {
				Match match = each.getValue();
				if (!(match instanceof UsersMatch)) {
					if(match.getDateTime().toLocalDate().equals(getDate().toLocalDate())) {
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
                UsersMatch child = new UsersMatch(adult.getHome(), adult.getAway(), league, adult.getDateTime()); // Create ChildClass instance
                league.getFixtures().put(key, child); // Replace the entry in the map
            }
        }
		
		// This looks for UsersMatches and creates an event from it
		for(Map.Entry<String, Match> each : league.getFixtures().entrySet()) {
			Match eachMatch = each.getValue();
			if (eachMatch instanceof UsersMatch) {
				Events matchEvent = new Events((UsersMatch) eachMatch);
				events.add(matchEvent);
				Team enemyTeam;
				String homeOrNot;
				if(eachMatch.getHome() == team){
					enemyTeam = eachMatch.getAway();
					homeOrNot = "home";
				} else {
					enemyTeam = eachMatch.getHome();
					homeOrNot = "away";
				}
				Events chairmanMessage = new Events("Chairman", "Good luck in your " + homeOrNot + " game today against " + enemyTeam.getName() + ".", eachMatch.getDateTime());
				events.add(chairmanMessage);
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

	public JPanel getHeader() {
		return header;
	}

	public void setHeader(JPanel header) {
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
