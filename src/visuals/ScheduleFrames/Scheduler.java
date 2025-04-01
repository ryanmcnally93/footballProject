package visuals.ScheduleFrames;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import entities.*;
import visuals.CustomizedElements.*;
import main.GameWindow;
import visuals.MainMenuPages.FixturesPages.AllFixturesPage;
import visuals.MainMenuPages.FixturesPages.MyFixturesPage;
import visuals.MainMenuPages.FixturesPages.ResultsPage;
import visuals.MainMenuPages.LeaderboardPages.LeagueTablePage;
import visuals.MainMenuPages.LeaderboardPages.TopAssistsPage;
import visuals.MainMenuPages.LeaderboardPages.TopGoalscorersPage;
import visuals.MainMenuPages.MainMenuPageTemplate;
import visuals.MainMenuPages.TacticsPages.FirstTeamPage;
import visuals.MainMenuPages.TacticsPages.FormationPage;
import visuals.MainMenuPages.TacticsPages.MatchRolesPage;
import visuals.MatchPages.*;
import visuals.SchedulerMessageApp.MessageViewer;

public class Scheduler extends GamePanel {

	private LocalDateTime date;
	private JPanel eventsBox, southMiddle, menuBox, header, backgroundMoverPanel, datePanel;
	private TacticsPanel tacticsPanel;
	private CustomizedButton menuButton, closeButton, backgroundMover, trainingButton, playerSearchButton, standingsButton, teamButton, myClubButton, fixturesButton, myProfileButton;
	private CustomizedTitle todaysDate, teamName, userName;
	private Team team;
	private User user;
	private League league;
	private ArrayList<Events> events;
	private JPanel mainPanel;
	private GameWindow window;
	private UsersMatch match;
	private JLayeredPane layeredPane;
	private MainMenu mainMenu;
	private Season season;
	private ArrayList<Match> laterMatches, sameDayMatches;
	// Main Menu & MatchFrames Layouts, CardMap & Pages
	private CardLayout leaderboardsLayout, fixturesLayout, tacticsLayout, matchFramesLayout;
	private Map<String, JPanel> leaderboardsMap, tacticsMap, fixturesMap, matchFramesMap;
	private JPanel leaderboardsPages, tacticsPages, fixturesPages, matchFramesPages;
	// Main Menu Pages
	private LeagueTablePage leaguePage;
	private TopGoalscorersPage goals;
	private TopAssistsPage assists;
	private AllFixturesPage allFixtures;
	private MyFixturesPage myFixtures;
	private ResultsPage results;
	private FirstTeamPage firstTeam;
	private FormationPage formation;
	private MatchRolesPage matchRoles;
	// Match Frames Pages
	private MatchWatch watchPanel;
	private MatchScorers scorerPanel;
	private MatchStats statsPanel;
	private MatchEvents eventsPanel;
	private MatchAllMatches allMatchesPanel;
	private MatchTable tablePanel;
	private MatchRatings ratingsPanel;
	// Match Components
	private Speedometer speedometer;
	private CustomizedButton pauseButton, resumeButton, tacticsButton;
	// Visuals
	private Image backgroundImage;
	private int backgroundImageHeight = -205;
	private List<Component> movingComponents = new ArrayList<>();
	private MessageViewer messageViewer;
	
	// New Game Constructor
	public Scheduler(User user, Team team, League league) {
		this.date = LocalDateTime.of(2024,  6, 1, 0, 0);
		this.user = user;
		this.team = team;
		this.league = league;
		this.events = new ArrayList<Events>();
		this.season = league.getSeason();
		this.setLayout(new BorderLayout());

		ImageIcon image = new ImageIcon("./src/visuals/Images/main_scheduler.jpeg");
		backgroundImage = image.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);

		layeredPane = new JLayeredPane();
		setPermanentWidthAndHeight(layeredPane, 800, 600);
		
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
		mainPanel.setBounds(0, 0, 800, 600);
		mainPanel.setOpaque(false);

		header = new JPanel(new BorderLayout());
		header.setPreferredSize(new Dimension(800, 80));
		header.setOpaque(false);

		// Left-side panel
		JPanel teamNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		teamNamePanel.setOpaque(false);
		teamName = new CustomizedTitle(team.getName());
		teamName.setFontSize(20);
		teamNamePanel.add(teamName);

		// Right-side panel
		JPanel userNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		userNamePanel.setOpaque(false);
		userName = new CustomizedTitle(user.getName());
		userName.setFontSize(20);
		userNamePanel.add(userName);

		// Add to header
		header.add(teamNamePanel, BorderLayout.WEST);
		header.add(userNamePanel, BorderLayout.EAST);

		mainPanel.add(header, BorderLayout.NORTH);
        
        appendEastAndWest(mainPanel);

		JPanel south = new JPanel(new BorderLayout());
		south.setOpaque(false);
		movingComponents.add(south);
		southMiddle = new JPanel(new BorderLayout());
		southMiddle.setOpaque(false);

		messageViewer = new MessageViewer(this);
		messageViewer.addAdvanceButton();
		southMiddle.add(messageViewer, BorderLayout.CENTER);

		menuBox = new JPanel();
		setPermanentWidth(menuBox, 155);

		datePanel = new JPanel();
		setPermanentWidth(datePanel, 154);
		datePanel.setOpaque(false);

		String todaysDateFormatted = getTodaysDateWithGoodFormat();
		todaysDate = new CustomizedTitle(todaysDateFormatted);
		todaysDate.setFontSize(16);
		datePanel.add(Box.createVerticalStrut(320));
		datePanel.add(Box.createHorizontalGlue());
		datePanel.add(todaysDate);
		datePanel.add(Box.createHorizontalGlue());
		datePanel.setBorder(new EmptyBorder(0,-10,0,0));

		menuButton = new CustomizedButton("Main Menu", 16);
		menuBox.add(Box.createVerticalStrut(320));
		menuBox.add(Box.createHorizontalGlue());
		menuBox.add(menuButton);
		menuBox.add(Box.createHorizontalGlue());
		menuBox.setOpaque(false);
		menuBox.setBorder(new EmptyBorder(0,0,0,15));

		south.add(menuBox, BorderLayout.EAST);
		south.add(southMiddle, BorderLayout.CENTER);
		south.add(datePanel, BorderLayout.WEST);
		south.setBounds(0, 372, 800, 200);
		layeredPane.add(south, JLayeredPane.PALETTE_LAYER);

		layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
		add(layeredPane, BorderLayout.CENTER);

		Events chairmanMessage = new Events("Chairman", "Welcome to " + team.getName() + " FC! We've been preparing for your arrival for some time and wish you the best of luck for the season ahead.", getDate());
		events.add(chairmanMessage);
		Events youthCoachMessage = new Events("Youth Coach", "Welcome boss, I've put together a list of the top players in the academy for you to take a look at.", getDate());
		events.add(youthCoachMessage);

		createLayoutsMapsAndPages();
		createMovingButtons();
		addMouseListeners();
		createMainMenu();
		refreshMessages();

		revalidate();
		repaint();
	}

	private void addMouseListeners() {
		menuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				triggerMenu();
			}
		});

		backgroundMover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String direction = backgroundMover.getOtherIcon().getDescription();
				if (direction.equals("Down") || direction.equals("DownDark")) {
					moveContentDown();
					ImageIcon upArrow = new ImageIcon("./src/visuals/Images/up_arrow_darkbg.png", "UpDark");
					backgroundMover.setIcon(upArrow);
					backgroundMover.setOtherIcon(upArrow);
				} else {
					moveContentUp();
					ImageIcon downArrow = new ImageIcon("./src/visuals/Images/down_arrow_darkbg.png", "DownDark");
					backgroundMover.setIcon(downArrow);
					backgroundMover.setOtherIcon(downArrow);
				}
				backgroundMover.revalidate();
				backgroundMover.repaint();
			}
		});

		addCardmapListener(tacticsPanel, tacticsPages, tacticsLayout);
		addCardmapListener(standingsButton, leaderboardsPages, leaderboardsLayout);
	}

	private void createMovingButtons() {
		backgroundMover = createMovingButton("./src/visuals/Images/down_arrow.png", "Down", 15, 305, 30, 30);
		trainingButton = createMovingButton("./src/visuals/Images/training_icon.png", "Training", 33, 110, 41, 45);
		teamButton = createMovingButton("./src/visuals/Images/team_icon.png", "Team", 84, 64, 41, 36);

		tacticsPanel = new TacticsPanel();
		tacticsPanel.setLayout(null);
		tacticsPanel.setBounds(280, -49, 275, 190);
		movingComponents.add(tacticsPanel);
		layeredPane.add(tacticsPanel, JLayeredPane.PALETTE_LAYER);

		standingsButton = createMovingButton("./src/visuals/Images/standings_icon.png", "Standings", 145, 100, 50, 58);
		playerSearchButton = createMovingButton("./src/visuals/Images/player_search_icon.png", "PlayerSearch", 211, 164, 45, 54);
		myClubButton = createMovingButton("./src/visuals/Images/my_club_icon.png", "MyClub", 577, 100, 48, 56);
		fixturesButton = createMovingButton("./src/visuals/Images/fixtures_icon.png", "Fixtures", 643, 51, 45, 54);
		myProfileButton = createMovingButton("./src/visuals/Images/my_profile_icon.png", "MyProfile", 707, 157, 41, 62);
	}

	private CustomizedButton createMovingButton(String url, String description, int x, int y, int width, int height) {
		ImageIcon buttonIcon = new ImageIcon(url, description);
		CustomizedButton button = new CustomizedButton(buttonIcon);
		setPermanentWidthAndHeight(button, 30, 30);

		JPanel thisPanel = new JPanel();
		thisPanel.setLayout(null);
		thisPanel.setBounds(x, y, width, height);
		thisPanel.setOpaque(false);
		thisPanel.add(button);
		movingComponents.add(thisPanel);
		button.setBounds(0,0,width, height);
		layeredPane.add(thisPanel, JLayeredPane.PALETTE_LAYER);
		return button;
	}

	public void moveContentUp() {
		moveContent(-205);
	}

	public void moveContent(int moveBy) {
		int totalSteps = 30;
		int delay = 20;
		int startBackgroundY = backgroundImageHeight;

		List<Integer> startPositions = new ArrayList<>();

		// Capture initial Y positions of all components
		for (Component comp : movingComponents) {
			startPositions.add(comp.getY());
		}

		Timer timer = new Timer(delay, new ActionListener() {
			int step = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (step > totalSteps) {
					((Timer) e.getSource()).stop();
					return;
				}

				// Interpolate positions
				double t = (double) step / totalSteps;

				backgroundImageHeight = (int) (startBackgroundY + t * moveBy);

				for (int i = 0; i < movingComponents.size(); i++) {
					Component comp = movingComponents.get(i);
					int startY = startPositions.get(i);
					int newY = (int) (startY + t * moveBy);
					comp.setBounds(comp.getX(), newY, comp.getWidth(), comp.getHeight());
					comp.revalidate();
					comp.repaint();
				}

				repaint();
				step++;
			}
		});

		timer.start();
	}

	public void moveContentDown() {
		moveContent(205);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, backgroundImageHeight, getWidth(), getHeight() + 205, this);
		}
	}

	public void createLayoutsMapsAndPages() {
		// Leaderboard Maps
		leaderboardsLayout = new CardLayout();
		leaderboardsPages = new JPanel(leaderboardsLayout);
		leaguePage = new LeagueTablePage(leaderboardsLayout, leaderboardsPages, this, true);
		goals = new TopGoalscorersPage(leaderboardsLayout, leaderboardsPages, this, true);
		assists = new TopAssistsPage(leaderboardsLayout, leaderboardsPages, this, true);
		leaderboardsPages.add(leaguePage, "League Table");
		leaderboardsPages.add(goals, "Top Goals");
		leaderboardsPages.add(assists, "Top Assists");

		leaderboardsMap = new HashMap<>();
		leaderboardsMap.put("League Table", leaguePage);
		leaderboardsMap.put("Top Goals", goals);
		leaderboardsMap.put("Top Assists", assists);

		// Fixtures Maps
		fixturesLayout = new CardLayout();
		fixturesPages = new JPanel(fixturesLayout);
		allFixtures = new AllFixturesPage(fixturesLayout, fixturesPages, this, true);
		myFixtures = new MyFixturesPage(fixturesLayout, fixturesPages, this, true);
		results = new ResultsPage(fixturesLayout, fixturesPages, this, true);
		fixturesPages.add(allFixtures, "All Fixtures");
		fixturesPages.add(myFixtures, "My Fixtures");
		fixturesPages.add(results, "Results");

		fixturesMap = new HashMap<>();
		fixturesMap.put("All Fixtures", allFixtures);
		fixturesMap.put("My Fixtures", myFixtures);
		fixturesMap.put("Results", results);

		// Create Components for Match Frames
		speedometer = new Speedometer();
		ArrayList<CustomizedButton> buttons = new ArrayList<CustomizedButton>();

		pauseButton = new CustomizedButton("Pause");
		pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttons.add(pauseButton);

		resumeButton = new CustomizedButton("Resume");
		resumeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttons.add(resumeButton);

		tacticsButton = new CustomizedButton("Tactics");
		tacticsButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		buttons.add(tacticsButton);

		// Match Frames Maps
		matchFramesLayout = new CardLayout();
		matchFramesPages = new JPanel(matchFramesLayout);
		watchPanel = new MatchWatch(matchFramesLayout, matchFramesPages, speedometer, buttons);
		scorerPanel = new MatchScorers(matchFramesLayout, matchFramesPages, speedometer, buttons);
		statsPanel = new MatchStats(matchFramesLayout, matchFramesPages, speedometer, buttons);
		eventsPanel = new MatchEvents(matchFramesLayout, matchFramesPages, speedometer, buttons);
		allMatchesPanel = new MatchAllMatches(matchFramesLayout, matchFramesPages, speedometer, buttons);
		tablePanel = new MatchTable(matchFramesLayout, matchFramesPages, speedometer, buttons);
		ratingsPanel = new MatchRatings(matchFramesLayout, matchFramesPages, speedometer, buttons);

		matchFramesPages.add(watchPanel, "Watch");
		matchFramesPages.add(scorerPanel, "Scorers"); // Updated
		matchFramesPages.add(statsPanel, "Stats"); // Updated
		matchFramesPages.add(eventsPanel, "Events"); // Updated
		matchFramesPages.add(allMatchesPanel, "All Matches"); // Updated
		matchFramesPages.add(tablePanel, "Table"); // Updated
		matchFramesPages.add(ratingsPanel, "Ratings");

		matchFramesMap = new HashMap<>();
		matchFramesMap.put("Watch", watchPanel);
		matchFramesMap.put("Scorers", scorerPanel);
		matchFramesMap.put("Stats", statsPanel);
		matchFramesMap.put("Events", eventsPanel);
		matchFramesMap.put("All Matches", allMatchesPanel);
		matchFramesMap.put("Table", tablePanel);
		matchFramesMap.put("Ratings", ratingsPanel);

		// Tactics Maps
		tacticsLayout = new CardLayout();
		tacticsPages = new JPanel(tacticsLayout);
		firstTeam = new FirstTeamPage(tacticsLayout, tacticsPages, this, true);
		formation = new FormationPage(tacticsLayout, tacticsPages, this, true);
		matchRoles = new MatchRolesPage(tacticsLayout, tacticsPages, this, true);
		tacticsPages.add(firstTeam, "First Team");
		tacticsPages.add(formation, "Formation");
		tacticsPages.add(matchRoles, "Match Roles");

		tacticsMap = new HashMap<>();
		tacticsMap.put("First Team", firstTeam);
		tacticsMap.put("Formation", formation);
		tacticsMap.put("Match Roles", matchRoles);
	}

	// Needs reviewing
	public void createMainMenu() {
		mainMenu = new MainMenu(window, this);
	}

	public void addCardmapListener(JComponent component, JPanel pages, CardLayout thisLayout){
		component.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				window.getContentPane().removeAll();
				window.getContentPane().add(pages, BorderLayout.CENTER);
				if (component instanceof JButton) {
					thisLayout.show(pages, ((JButton) component).getText());
					CustomizedButton thisButton = ((CustomizedButton) component);
					thisButton.triggerColorReverse();
				} else {
					// This is the Tactics Panel click
					if (tacticsPanel.getShape().contains(e.getPoint())) {
						thisLayout.show(pages, "First Team");
						tacticsPanel.setHovered(false);
						tacticsPanel.removeFootballIcon();
					}
				}
				window.revalidate();
				window.repaint();
			}
		});
	}

	public void triggerMenu(){
		menuBox.remove(menuButton);
		closeButton = new CustomizedButton("Close Menu", 16);
		menuBox.add(closeButton);
		setPermanentWidthAndHeight(mainMenu, layeredPane.getWidth(), layeredPane.getHeight());
		layeredPane.add(mainMenu, JLayeredPane.PALETTE_LAYER);
		layeredPane.revalidate();
		layeredPane.repaint();

		layeredPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!mainMenu.getBounds().contains(e.getPoint())) {
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
		if(layeredPane.isAncestorOf(mainMenu)){
			layeredPane.remove(mainMenu);
			menuBox.remove(closeButton);
			menuBox.add(menuButton);
			layeredPane.revalidate();
			layeredPane.repaint();
		}
	}
	
	public void displayPage(GameWindow window) {
		this.window= window;
		closeMenu();
		window.getContentPane().removeAll();
		window.getContentPane().add(this, BorderLayout.CENTER);
		window.revalidate();
		window.repaint();

		mainMenu.setBounds(600, 70, 200,400); // Set bounds of MainMenu
		mainMenu.setVisible(true);

		// When we come back from a match, we want to sort buttons out
		updateButtonsAfterUsersMatch();
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
			}
		}
		// If there isn't a match today, set 'match' to next weeks match
		if(matchEvent == null){
			Match nextWeeksMatch = events.stream()
					.filter(event -> event.getType().equals("Match"))
					.map(event -> (Match) event.getMatch())
					.min(Comparator.comparing(Match::getDateTime))
					.orElse(null);
			setMatch((UsersMatch) nextWeeksMatch);
		}
		todaysEvents.remove(matchEvent);
		if(matchEvent != null){
			todaysEvents.add(matchEvent);
		}
		showTodaysEvents(todaysEvents);
	}

	public void updateButtonsAfterUsersMatch(){
		// This only runs if there is a usersmatch
		if(match != null) {
			if(match.getTimer().getTime().equals("90:00")) {
				messageViewer.removePlayGameButton();
				messageViewer.removeSimGameButton();
				// We don't want the advance button when messages are present
				// Let's check there isn't already an advance button
				messageViewer.addAdvanceButton();
			}

		}
	}

	public void addFirstPositionMessage(){
		Events chairmanMessage = new Events("Chairman", "This is absolutely incredible! We are top of the league! From all of the staff and players, we thank you for your hard work!", getDate());
		events.add(chairmanMessage);
	}

	public void showTodaysEvents(ArrayList<Events> todaysEvents){
		System.out.println("Todays Events: " + todaysEvents);

		// Find events to remove
		ArrayList<Events> toRemove = new ArrayList<Events>();
		for(Events event : todaysEvents) {
			if(event.getRemoveEvent()){
				toRemove.add(event);
			}
		}
		// Remove them
		for(Events event : toRemove){
			todaysEvents.remove(event);
			events.remove(event);
		}

		// Let's look through todays events that are left
		for(Events event : todaysEvents){
			messageViewer.displayEvent(event);
			messageViewer.removeAdvanceButton();

			// This is a match event
			if (event.getType().equals("Match")) {
				System.out.println("Adding a new playgame button");
				messageViewer.addPlayGameButton(event);

				// This is the simulate match button and its functionality
				System.out.println("Adding a simgame button");
				messageViewer.addSimGameButton(event, todaysEvents);
			} else {
				// This is not a match event
				System.out.println("Adding a dismiss button");
				messageViewer.addDismissButton(event, todaysEvents);
				break;
			}
		}
	}

	public static String removeCharactersAfterColon(String input) {
		int colonIndex = input.indexOf(':');
		if (colonIndex != -1) {
			return input.substring(0, colonIndex);
		}
		return input; // No colon found, return the original string
	}

	public void displayMatchFrames(UsersMatch match) {
		window.getContentPane().removeAll();
		window.getContentPane().add(matchFramesPages, BorderLayout.CENTER);

		if (!match.isMatchHasPlayed() && tablePanel.isFromScheduler()) {
			viewMatchFramesWhenMatchNotPlayed();
		} else if (match.isMatchHasPlayed() && tablePanel.isFromScheduler()) {
			viewMatchFramesWhenMatchPlayed();
		} else if (match.getTimer().isPaused()) {
			viewMatchAfterTacticsPageViewed();
		} else {
			viewMatchStartOfGame();
		}

		if(!match.getSameDayMatches().contains(match)){
			match.getSameDayMatches().add(match);
		}

		allMatchesPanel.addTodaysMatchesToPage();
		match.updateDomesticLeagueTable();

		if (statsPanel.isFromScheduler()) {
			viewMatchFromScheduler();
		}

		window.revalidate();
		window.repaint();
	}

	public void viewMatchStartOfGame() {
		matchFramesLayout.show(matchFramesPages, "Stats");
		match.setCurrentPageName("Stats");
		addSpeedometer(statsPanel.getSpeedometerBox());
	}

	public void viewMatchAfterTacticsPageViewed() {
		MatchFrames currentPage = (MatchFrames) matchFramesMap.get(match.getCurrentPageName());
		ratingsPanel.updateLinesAfterTacticsChange();
		currentPage.getFooterPanel().getMiddleBox().add(currentPage.getResumeButton());
		currentPage.displayTacticsButton();
		addSpeedometer(currentPage.getSpeedometerBox());
	}

	public void viewTacticsPages(boolean beforeMatch, Events event) {
		getWindow().getContentPane().removeAll();
		for (Map.Entry<String, JPanel> eachTacticsPage : getTacticsMap().entrySet()) {
			((MainMenuPageTemplate) eachTacticsPage.getValue()).setFromScheduler(beforeMatch);
			((MainMenuPageTemplate) eachTacticsPage.getValue()).setEvent(event);
		}
		getWindow().getContentPane().add(getTacticsPages(), BorderLayout.CENTER);
		getTacticsLayout().show(getTacticsPages(), "First Team");
		getWindow().revalidate();
		getWindow().repaint();
	}

	public void viewMatchFramesWhenMatchPlayed() {
		removePage("Watch", getMatchFramesMap(), getMatchFramesPages());
		removePage("Table", getMatchFramesMap(), getMatchFramesPages());
		matchFramesLayout.show(matchFramesPages, "Stats");
		match.setCurrentPageName("Stats");
		addSpeedometer(getStatsPanel().getSpeedometerBox());
		if (getStatsPanel().getFooterPanel().getMiddleBox().isAncestorOf(getStatsPanel().getPlayButton())) {
			getStatsPanel().getFooterPanel().getMiddleBox().remove(getStatsPanel().getPlayButton());
		}
	}

	public void removePage(String pageName, Map<String, JPanel> cardMap, JPanel cardPanel) {
		// Ensure the page exists
		if (cardMap.containsKey(pageName)) {
			CardmapMainPageTemplate page = (CardmapMainPageTemplate) cardMap.get(pageName);

			// Remove from the card layout panel
			cardPanel.remove(page);
			if (cardMap == getMatchFramesMap()) {
				for (Map.Entry<String, JPanel> eachMatchPage : getMatchFramesMap().entrySet()) {
					((MatchFrames) eachMatchPage.getValue()).setPages(cardPanel);
				}
			}

			// Remove from the card map
			cardMap.remove(pageName);

			// Revalidate and repaint the panel to reflect changes
			cardPanel.revalidate();
			cardPanel.repaint();
		} else {
			System.out.println("Page \"" + pageName + "\" does not exist.");
		}
	}

	public void addPage(String pageName, JPanel page, Map<String, JPanel> cardMap, JPanel cardPanel) {
		String mapType = "";
		if (cardMap == getTacticsMap()) {
			mapType = "Tactics";
		} else if (cardMap == getMatchFramesMap()) {
			mapType = "Match Frames";
		}

		if (cardMap.containsKey(pageName)) {
			System.out.println("Page \"" + pageName + "\" already exists.");
			return;
		}

		// Insert the page into the map at the correct position
		LinkedHashMap<String, JPanel> newCardMap = new LinkedHashMap<>();
        newCardMap.putAll(cardMap);
		newCardMap.put(pageName, page);

		List<String> desiredOrder = Arrays.asList("Watch", "Scorers", "Stats", "Events", "All Matches", "Table", "Ratings");
		Map<String, JPanel> sortedMap = sortMapByCustomOrder(newCardMap, desiredOrder);

		// Replace the old cardMap with the new one
		if (mapType.equals("Tactics")) {
			getTacticsMap().clear();
			setTacticsMap(sortedMap);
			cardPanel.removeAll();
			for (Map.Entry<String, JPanel> entry : getTacticsMap().entrySet()) {
				cardPanel.add(entry.getValue(), entry.getKey());
			}
			for (Map.Entry<String, JPanel> entry : getTacticsMap().entrySet()) {
				CardmapMainPageTemplate cardPage = (CardmapMainPageTemplate) entry.getValue();
				cardPage.setPages(cardPanel);
			}
		} else if (mapType.equals("Match Frames")) {
			getMatchFramesMap().clear();
			setMatchFramesMap(sortedMap);
			cardPanel.removeAll();
			for (Map.Entry<String, JPanel> entry : getMatchFramesMap().entrySet()) {
				cardPanel.add(entry.getValue(), entry.getKey());
			}
			for (Map.Entry<String, JPanel> entry : getMatchFramesMap().entrySet()) {
				CardmapMainPageTemplate cardPage = (CardmapMainPageTemplate) entry.getValue();
				cardPage.setPages(cardPanel);
			}
		}

		// Revalidate and repaint to reflect changes
		cardPanel.revalidate();
		cardPanel.repaint();
	}

	public static Map<String, JPanel> sortMapByCustomOrder(Map<String, JPanel> unsortedMap, List<String> desiredOrder) {
		Map<String, JPanel> sortedMap = new LinkedHashMap<>();

		// Iterate through the desired order and add matching entries to the sorted map
		for (String key : desiredOrder) {
			if (unsortedMap.containsKey(key)) {
				sortedMap.put(key, unsortedMap.get(key));
			}
		}

		return sortedMap;
	}

	public void viewMatchFromScheduler() {
		for (Map.Entry<String, JPanel> eachPanel : getMatchFramesMap().entrySet()) {
			MatchFrames page = (MatchFrames) eachPanel.getValue();
			for (Component button : page.getFooterPanel().getMiddleBox().getComponents()) {
				if (button == page.getPauseButton() || button == page.getPlayButton() || button == page.getResumeButton() || button == page.getTacticsButton() || button == page.getContinueButton()) {
					page.getFooterPanel().getMiddleBox().remove(button);
				}
			}
			page.getTime().setText("");
		}

		addEmptySpeedometer(tablePanel.getSpeedometerBox());
		addEmptySpeedometer(statsPanel.getSpeedometerBox());
	}

	public void addEmptySpeedometer(Box speedometerBox) {
		JPanel emptySpeedometer = new JPanel();
		emptySpeedometer.setPreferredSize(new Dimension(200, 20));
		emptySpeedometer.setOpaque(false);
		emptySpeedometer.setBounds(400, 0, 200, 20);
		if (speedometerBox.getComponents().length == 0) {
			speedometerBox.add(emptySpeedometer);
		} else {
			speedometerBox.removeAll();
			speedometerBox.add(emptySpeedometer);
		}
	}

	public void addSpeedometer(Box speedometerBox) {
		if (speedometerBox.getComponents().length == 0) {
			speedometerBox.add(speedometer);
		} else {
			speedometerBox.removeAll();
			speedometerBox.add(speedometer);
		}
	}

	public void viewMatchFramesWhenMatchNotPlayed() {
		matchFramesLayout.show(matchFramesPages, "Table");
		match.setCurrentPageName("Table");
		tablePanel.getFooterPanel().getButtonBox().remove(tablePanel.getFooterPanel().getNextButton());
		if (tablePanel.getFooterPanel().getMiddleBox().getComponents().length > 0) {
			tablePanel.getFooterPanel().getMiddleBox().remove(tablePanel.getPlayButton());
		}
		tablePanel.getFooterPanel().getButtonBox().remove(tablePanel.getFooterPanel().getPrevButton());
		tablePanel.getStadiumAndAttendance().setText(removeCharactersAfterColon(tablePanel.getStadiumAndAttendance().getText()));
		tablePanel.getHeaderPanel().setTitle(match.getHome().getName() + " vs " + match.getAway().getName());
		tablePanel.removeKeyListeners();
	}
	
	public void addDay() {
		this.laterMatches = new ArrayList<>();
		this.sameDayMatches = new ArrayList<>();

		System.out.println("All Events: " + events);
		System.out.println("Events size: " + events.size());
		// Need ones that aren't finished
//		System.out.println("All Fixtures: " + league.getFixtures());
//		System.out.println("Fixtures Size: " + league.getFixtures().size());
		messageViewer.removeMessage();
		mainPanel.repaint();
		date = date.plusDays(1);

		String todaysDateFormatted = getTodaysDateWithGoodFormat();
		todaysDate.setText(todaysDateFormatted);
		refreshDateLabelSize();

		int year = 2023 + season.getNumber();

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
			messageViewer.addAdvanceToGameButton(events);
		}

		ArrayList<Match> todaysGames = getAllOfTodaysMatches();

		System.out.println("Today's Matches" + todaysGames);
		// This will 'play' the background matches
		for(Match backgroundMatch : todaysGames) {
			if (getMatch() != null && getMatch().getDateTime().toLocalDate().isEqual(date.toLocalDate())) {
				if (backgroundMatch.getDateTime().isBefore(getMatch().getDateTime())) {
					// We add this to the usersmatch earlier matches list
					getMatch().appendEarlierMatches(backgroundMatch);
					CompletableFuture.runAsync(() -> backgroundMatch.startMatch("instant"));
				} else if (backgroundMatch.getDateTime().isEqual(getMatch().getDateTime())) {
					// Make sure we add this to the usersmatch too
					getMatch().appendSameDayMatches(backgroundMatch);
					backgroundMatch.appendSameDayMatches(getMatch());
				} else {
					// We need to make sure that this plays when the UsersMatch finishes
					getMatch().appendLaterMatches(backgroundMatch);
				}
			} else {
				CompletableFuture.runAsync(() -> backgroundMatch.startMatch("instant"));
			}
		}

		if(getMatch() != null) {
			sortMatchesByTime(getMatch().getEarlierMatches());
			for(Match each : getMatch().getEarlierMatches()){
				System.out.println(each.getHome().getName() + "'s match is at: " + each.getDateTime() + " HERE");
			}
			sortMatchesByTime(getMatch().getLaterMatches());
		}

		refreshMessages();
	}

	private void refreshDateLabelSize() {
		todaysDate.addOpaqueBackground();
		datePanel.setBounds(10, 532, (int) todaysDate.getPreferredSize().getWidth(), (int) todaysDate.getPreferredSize().getHeight());
		todaysDate.setBounds(0,0,(int) todaysDate.getPreferredSize().getWidth(), (int) todaysDate.getPreferredSize().getHeight());

	}

	private void sortMatchesByTime(ArrayList<Match> matchArray) {
		matchArray.sort(new Comparator<Match>() {
			@Override
			public int compare(Match matchOne, Match matchTwo) {
				return matchOne.getDateTime().compareTo(matchTwo.getDateTime());
			}
		});
	}

	private String getTodaysDateWithGoodFormat(){
		int day = getDate().getDayOfMonth();
		String dayWithSuffix = day + getDayOfMonthSuffix(day);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
		return dayWithSuffix + " " + getDate().format(formatter);
	}

	public ArrayList<Match> getAllOfTodaysMatches(){
		ArrayList<Match> todaysGames = new ArrayList<>();
		if(getDate().toLocalDate().isAfter(LocalDate.of(2023 + season.getNumber(), 06, 8))) {
			for (Map.Entry<String, Match> each : league.getFixtures().entrySet()) {
				Match backgroundMatch = each.getValue();
				if (!(backgroundMatch instanceof UsersMatch)) {
					if (backgroundMatch.getDateTime().toLocalDate().equals(getDate().toLocalDate())) {
						todaysGames.add(backgroundMatch);
					}
				}
			}
		}
		return todaysGames;
	}

	private static String getDayOfMonthSuffix(int day) {
		if (day >= 11 && day <= 13) {
			return "th";
		}
		return switch (day % 10) {
			case 1 -> "st";
			case 2 -> "nd";
			case 3 -> "rd";
			default -> "th";
		};
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
		int k = 0;
		MyFixturesPage myFixturesPage = (MyFixturesPage) fixturesMap.get("My Fixtures");
		for (String key : keysToReplace) {
            Match adult = league.getFixtures().get(key);
            if (adult != null) {
                UsersMatch child = new UsersMatch(adult.getHome(), adult.getAway(), league, adult.getDateTime()); // Create ChildClass instance
                league.getFixtures().put(key, child); // Replace the entry in the map
				myFixturesPage.addFixtureLine(child);
            }
        }
		myFixturesPage.organiseMyFixtures();
		System.out.println("Created " + k + "UserMatches");
		
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

	public ArrayList<Events> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Events> events) {
		this.events = events;
	}

	public JPanel getSouthMiddle() {
		return southMiddle;
	}

	public void setSouthMiddle(JPanel southMiddle) {
		this.southMiddle = southMiddle;
	}

	public JPanel getMenuBox() {
		return menuBox;
	}

	public void setMenuBox(JPanel menuBox) {
		this.menuBox = menuBox;
	}

	public CustomizedButton getMenuButton() {
		return menuButton;
	}

	public void setMenuButton(CustomizedButton menuButton) {
		this.menuButton = menuButton;
	}

	public CustomizedButton getCloseButton() {
		return closeButton;
	}

	public void setCloseButton(CustomizedButton closeButton) {
		this.closeButton = closeButton;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public CustomizedTitle getTodaysDate() {
		return todaysDate;
	}

	public void setTodaysDate(CustomizedTitle todaysDate) {
		this.todaysDate = todaysDate;
	}

	public GameWindow getWindow() {
		return window;
	}

	public void setWindow(GameWindow window) {
		this.window = window;
	}

	public UsersMatch getMatch() {
		return match;
	}

	public void setMatch(UsersMatch match) {
		this.match = match;
	}

	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}

	public void setLayeredPane(JLayeredPane layeredPane) {
		this.layeredPane = layeredPane;
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

	public ArrayList<Match> getLaterMatches() {
		return laterMatches;
	}

	public void setLaterMatches(ArrayList<Match> laterMatches) {
		this.laterMatches = laterMatches;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public ArrayList<Match> getSameDayMatches() {
		return sameDayMatches;
	}

	public void setSameDayMatches(ArrayList<Match> sameDayMatches) {
		this.sameDayMatches = sameDayMatches;
	}

	public CardLayout getLeaderboardsLayout() {
		return leaderboardsLayout;
	}

	public void setLeaderboardsLayout(CardLayout leaderboardsLayout) {
		this.leaderboardsLayout = leaderboardsLayout;
	}

	public CardLayout getFixturesLayout() {
		return fixturesLayout;
	}

	public void setFixturesLayout(CardLayout fixturesLayout) {
		this.fixturesLayout = fixturesLayout;
	}

	public CardLayout getTacticsLayout() {
		return tacticsLayout;
	}

	public JPanel getFixturesPages() {
		return fixturesPages;
	}

	public void setTacticsLayout(CardLayout tacticsLayout) {
		this.tacticsLayout = tacticsLayout;
	}

	public Map<String, JPanel> getTacticsMap() {
		return tacticsMap;
	}

	public void setTacticsMap(Map<String, JPanel> tacticsMap) {
		this.tacticsMap = tacticsMap;
	}

	public JPanel getTacticsPages() {
		return tacticsPages;
	}

	public Map<String, JPanel> getMatchFramesMap() {
		return matchFramesMap;
	}

	public void setMatchFramesMap(Map<String, JPanel> matchFramesMap) {
		this.matchFramesMap = matchFramesMap;
	}

	public void setTacticsPages(JPanel tacticsPages) {
		this.tacticsPages = tacticsPages;
	}

	public LeagueTablePage getLeaguePage() {
		return leaguePage;
	}

	public TopGoalscorersPage getGoals() {
		return goals;
	}

	public TopAssistsPage getAssists() {
		return assists;
	}

	public AllFixturesPage getAllFixtures() {
		return allFixtures;
	}

	public MyFixturesPage getMyFixtures() {
		return myFixtures;
	}

	public ResultsPage getResults() {
		return results;
	}

	public FirstTeamPage getFirstTeam() {
		return firstTeam;
	}

	public FormationPage getFormation() {
		return formation;
	}

	public MatchRolesPage getMatchRoles() {
		return matchRoles;
	}

	public MatchWatch getWatchPanel() {
		return watchPanel;
	}

	public MatchScorers getScorerPanel() {
		return scorerPanel;
	}

	public MatchStats getStatsPanel() {
		return statsPanel;
	}

	public MatchEvents getEventsPanel() {
		return eventsPanel;
	}

	public MatchAllMatches getAllMatchesPanel() {
		return allMatchesPanel;
	}

	public MatchTable getTablePanel() {
		return tablePanel;
	}

	public MatchRatings getRatingsPanel() {
		return ratingsPanel;
	}

	public JPanel getMatchFramesPages() {
		return matchFramesPages;
	}

	public void setMatchFramesPages(JPanel matchFramesPages) {
		this.matchFramesPages = matchFramesPages;
	}

	public CardLayout getMatchFramesLayout() {
		return matchFramesLayout;
	}

	public void setMatchFramesLayout(CardLayout matchFramesLayout) {
		this.matchFramesLayout = matchFramesLayout;
	}

	public Map<String, JPanel> getLeaderboardsMap() {
		return leaderboardsMap;
	}

	public JPanel getLeaderboardsPages() {
		return leaderboardsPages;
	}

	public Map<String, JPanel> getFixturesMap() {
		return fixturesMap;
	}

	public MessageViewer getMessageViewer() {
		return messageViewer;
	}

	public void setMessageViewer(MessageViewer messageViewer) {
		this.messageViewer = messageViewer;
	}
}
