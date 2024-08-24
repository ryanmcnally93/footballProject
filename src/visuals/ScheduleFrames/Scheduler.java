package visuals.ScheduleFrames;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entities.League;
import entities.Match;
import entities.UsersMatch;
import entities.Team;
import entities.User;
import visuals.CustomizedElements.GamePanel;
import main.GameWindow;
import visuals.CustomizedElements.MainMenu;

public class Scheduler extends GamePanel {

	private static final long serialVersionUID = -949295084027854854L;
	private LocalDateTime date;
	private JPanel eventsBox, southMiddle, menuBox, header;
	private JButton advance, playGame, advanceToGame, simGame, menu, closeButton;
	private Team team;
	private User user;
	private League league;
	private ArrayList<Events> events;
	private JLabel todaysDate;
	private JPanel mainPanel;
	private GameWindow window;
	private Box eventContainer;
	private UsersMatch match;
	private JLayeredPane layeredPane;
	private MainMenu main;
	
	// New Game Constructor
	public Scheduler(User user, Team team, League league) {
		this.date = LocalDateTime.of(2024,  6, 1, 0, 0);
		this.user = user;
		this.team = team;
		this.league = league;
		this.events = new ArrayList<Events>();

		layeredPane = new JLayeredPane();
		setPermanentWidthAndHeight(layeredPane, 800, 600);
		
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
		mainPanel.setBounds(0, 0, 800, 600);

		main = new MainMenu();
		main.setBounds(600, 70, 200,400); // Set bounds of MainMenu
		main.setVisible(true);

        header = new JPanel();
        header.setPreferredSize(new Dimension(800, 80));
        JLabel title = new JLabel(team.getName() + " - " + user.getName() + " season " + league.getSeason(), SwingConstants.CENTER);
        title.setFont(new Font("Menlo", Font.BOLD, 30));
        header.add(title);
        mainPanel.add(header, BorderLayout.NORTH);
        
        appendEastAndWest(mainPanel);

		JPanel south = new JPanel(new BorderLayout());
		south.setPreferredSize(new Dimension(800, 80));
		southMiddle = new JPanel(new FlowLayout(FlowLayout.CENTER));

		todaysDate = new JLabel("Today's date is: " + getDate());
		advance = new JButton("Advance");
		southMiddle.add(todaysDate);
		southMiddle.add(advance);

		menuBox = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		setPermanentWidth(menuBox, 115);
		// Makes the southMiddle central
		JPanel leftBlankBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		setPermanentWidth(leftBlankBox, 115);
		menu = new JButton("Main Menu");
		menuBox.add(menu);

		south.add(menuBox, BorderLayout.EAST);
		south.add(southMiddle, BorderLayout.CENTER);
		south.add(leftBlankBox, BorderLayout.WEST);
		mainPanel.add(south, BorderLayout.SOUTH);
		
		advance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addDay();
			}
		});

		// JLAYEREDPANE IS NOT ADDED TO ANYTHING!
		layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
		add(layeredPane, BorderLayout.CENTER);

		menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				triggerMenu();
			}
		});

		Events chairmanMessage = new Events("Chairman", "Welcome to " + team.getName() + " FC! We've been preparing for your arrival for some time and wish you the best of luck for the season ahead.", getDate());
		events.add(chairmanMessage);
		Events youthCoachMessage = new Events("Youth Coach", "Welcome boss, I've put together a list of the top players in the academy for you to take a look at.", getDate());
		events.add(youthCoachMessage);

		refreshMessages();

		revalidate();
		repaint();
	}

	public void triggerMenu(){
		menuBox.remove(menu);
		closeButton = new JButton("Close Menu");
		menuBox.add(closeButton);
		setPermanentWidthAndHeight(main, layeredPane.getWidth(), layeredPane.getHeight());
		layeredPane.add(main, JLayeredPane.PALETTE_LAYER);
		layeredPane.revalidate();
		layeredPane.repaint();

		layeredPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!main.getBounds().contains(e.getPoint())) {
					closeMenu();
				}
			}
		});

		closeButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				closeMenu();
			}
		});

	}

	public void closeMenu(){
		if(layeredPane.isAncestorOf(main)){
			layeredPane.remove(main);
			menuBox.remove(closeButton);
			menuBox.add(menu);
			layeredPane.repaint();
		} else{
			System.out.println("Menu isn't open?");
		}
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
				southMiddle.remove(playGame);
				southMiddle.remove(simGame);
				southMiddle.add(advance);
				closeMenu();
				if(league.getLeagueTable().getLine(team).getPosition() == 1){
					Events chairmanMessage = new Events("Chairman", "This is absolutely incredible! We are top of the league! From all of the staff and players, we thank you for your hard work!", getDate());
					events.add(chairmanMessage);
					refreshMessages();
				}
			}
		}
	}

	public void refreshMessages(){
		System.out.println("Match has ended, lets refresh messages");
		ArrayList<Events> todaysEvents  = new ArrayList<>();
		for(Events each : events) {
			if(each.getDate().toLocalDate().equals(getDate().toLocalDate())) {
				todaysEvents.add(each);
			}
		}
		System.out.println("Weve gotten this far 2nd");
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
		System.out.println("We are running showtodaysevents, which means event hasn't successfully been added to todays events if that message doesn't show after this");
		showTodaysEvents(todaysEvents);
	}

	public void showTodaysEvents(ArrayList<Events> todaysEvents){
		// And now we will show todays events
		for(Events event : todaysEvents){
			showEventsDescription(event);
			// Remove advance button
			southMiddle.remove(advance);
			System.out.println("Checking if this is a match or not");
			if (event.getType().equals("Match")){
				// This is a match event
				// This is the play button and its functionality
				playGame = new JButton("Play");
				Scheduler sch = this;
				playGame.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						event.getMatch().displayGame(window, sch);
					}
				});
				southMiddle.add(playGame);
				// This is the simulate match button and its functionality
				simGame = new JButton("Simulate");
				Scheduler thissch = this;
				simGame.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						southMiddle.remove(simGame);
						southMiddle.remove(playGame);
						eventContainer.removeAll();
						// Run as normal match method
						UsersMatch todaysMatch = ((UsersMatch) league.getFixtures().get(event.getMatch().toString()));
						if(todaysMatch != null){
							Match child = new Match(todaysMatch.getHome(), todaysMatch.getAway(), league, todaysMatch.getDateTime());
							league.getFixtures().put(todaysMatch.toString(), child);
							// This is the only time a scheduler is passed to a match
							// So on at fulltime check, will run some tasks on this scheduler
							child.startMatch(thissch);
						}
						events.remove(event);
					}
				});
				southMiddle.add(simGame);
			} else {
				System.out.println("This is not a match event");
				JButton dismiss = new JButton("Dismiss");
				dismiss.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						eventContainer.removeAll();
						southMiddle.remove(dismiss);
						southMiddle.add(advance);
						todaysEvents.remove(event);
						events.remove(event);
						showTodaysEvents(todaysEvents);
						eventContainer.repaint();
						southMiddle.revalidate();
						southMiddle.repaint();
					}
				});
				southMiddle.add(dismiss);
				break;
			}
		}
	}

	public void showEventsDescription(Events event) {
		System.out.println("This is that event's stuff");
		eventContainer = Box.createVerticalBox();
		eventContainer.setPreferredSize(new Dimension(600,40));

		// Add margin bottom
		eventContainer.setBorder(new EmptyBorder(0,0,20,0));

		// This pushes everything to the bottom
		eventContainer.add(Box.createVerticalGlue());

		Box titleBox = Box.createHorizontalBox();
		titleBox.add(Box.createHorizontalGlue());
		JLabel title = event.getTitle();
		titleBox.add(title);
		titleBox.add(Box.createHorizontalGlue());
		titleBox.setBorder(new EmptyBorder(0,0,10,0));
		eventContainer.add(titleBox);

		Box descriptionBox = Box.createHorizontalBox();
		descriptionBox.add(Box.createHorizontalGlue());
		JLabel description = event.getDescription();
		descriptionBox.add(description);
		descriptionBox.add(Box.createHorizontalGlue());
		eventContainer.add(descriptionBox);

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
					southMiddle.remove(advanceToGame);
				}
			});
			southMiddle.add(advanceToGame);
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

	public Box getEventContainer() {
		return eventContainer;
	}

	public void setEventContainer(Box eventContainer) {
		this.eventContainer = eventContainer;
	}

	public ArrayList<Events> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Events> events) {
		this.events = events;
	}

	public JPanel getSouth() {
		return southMiddle;
	}

	public void setSouth(JPanel south) {
		this.southMiddle = south;
	}
}
