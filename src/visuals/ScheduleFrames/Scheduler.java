package visuals.ScheduleFrames;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.swing.*;
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
import visuals.MainMenuPages.TacticsPages.FirstTeamPage;
import visuals.MainMenuPages.TacticsPages.FormationPage;
import visuals.MainMenuPages.TacticsPages.MatchRolesPage;
import visuals.MatchPages.*;

public class Scheduler extends GamePanel {

	private LocalDateTime date;
	private JPanel eventsBox, southMiddle, menuBox, header;
	private JButton advance, playGame, advanceToGame, simGame, menuButton, closeButton;
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
	
	// New Game Constructor
	public Scheduler(User user, Team team, League league) {
		this.date = LocalDateTime.of(2024,  6, 1, 0, 0);
		this.user = user;
		this.team = team;
		this.league = league;
		this.events = new ArrayList<Events>();
		this.season = league.getSeason();

		layeredPane = new JLayeredPane();
		setPermanentWidthAndHeight(layeredPane, 800, 600);
		
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
		mainPanel.setBounds(0, 0, 800, 600);

        header = new JPanel();
        header.setPreferredSize(new Dimension(800, 80));
        JLabel title = new JLabel(team.getName() + " - " + user.getName() + " " + season.getYearFrom() + "/" + season.getYearTo(), SwingConstants.CENTER);
        title.setFont(new Font("Menlo", Font.BOLD, 30));
        header.add(title);
        mainPanel.add(header, BorderLayout.NORTH);
        
        appendEastAndWest(mainPanel);

		JPanel south = new JPanel(new BorderLayout());
		south.setPreferredSize(new Dimension(800, 80));
		southMiddle = new JPanel(new FlowLayout(FlowLayout.CENTER));

		String todaysDateFormatted = getTodaysDateWithGoodFormat();
		todaysDate = new JLabel(todaysDateFormatted);
		advance = new JButton("Advance");
		southMiddle.add(todaysDate);
		southMiddle.add(advance);

		menuBox = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		setPermanentWidth(menuBox, 115);

		// Makes the southMiddle central
		JPanel leftBlankBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		setPermanentWidth(leftBlankBox, 115);
		menuButton = new JButton("Main Menu");
		menuBox.add(menuButton);

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

		layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
		add(layeredPane, BorderLayout.CENTER);

		menuButton.addMouseListener(new MouseAdapter() {
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

		createMainMenu();

		revalidate();
		repaint();
	}

	public void createMainMenu() {
		mainMenu = new MainMenu(window, this);

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

		ArrayList<JButton> firstButtons = new ArrayList<>();
		firstButtons.add(getMainMenu().getLeagueTableButton());
		firstButtons.add(getMainMenu().getGoalscorersButton());
		firstButtons.add(getMainMenu().getAssistsButton());

		addListeners(firstButtons, leaderboardsPages, leaderboardsLayout);

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

		ArrayList<JButton> secondButtons = new ArrayList<>();
		secondButtons.add(getMainMenu().getAllFixturesButton());
		secondButtons.add(getMainMenu().getMyFixturesButton());
		secondButtons.add(getMainMenu().getResultsButton());

		addListeners(secondButtons, fixturesPages, fixturesLayout);

		speedometer = new Speedometer();
		ArrayList<CustomizedButton> buttons = new ArrayList<CustomizedButton>();

		// Add Pause and Resume Buttons here
		pauseButton = new CustomizedButton("Pause");
		pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttons.add(pauseButton);

		resumeButton = new CustomizedButton("Resume");
		resumeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttons.add(resumeButton);

		tacticsButton = new CustomizedButton("Tactics");
		tacticsButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		buttons.add(tacticsButton);

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

		ArrayList<JButton> thirdButtons = new ArrayList<>();
		thirdButtons.add(getMainMenu().getFirstTeamButton());
		thirdButtons.add(getMainMenu().getFormationButton());
		thirdButtons.add(getMainMenu().getMatchRolesButton());

		addListeners(thirdButtons, tacticsPages, tacticsLayout);
	}

	public void addListeners(ArrayList<JButton> buttons, JPanel pages, CardLayout thisLayout){
		for(JButton button : buttons){
			button.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					window.getContentPane().removeAll();
					window.getContentPane().add(pages, BorderLayout.CENTER);
					thisLayout.show(pages, button.getText());
					window.revalidate();
					window.repaint();
				}
			});
		}
	}

	public void triggerMenu(){
		menuBox.remove(menuButton);
		closeButton = new JButton("Close Menu");
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
				southMiddle.remove(playGame);
				southMiddle.remove(simGame);
				// We don't want the advance button when messages are present
				// Let's check there isn't already an advance button
				if(!southMiddle.isAncestorOf(advance)){
					southMiddle.add(advance);
				}
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
			showEventsDescription(event);
			southMiddle.remove(advance);

			// This is a match event
			if (event.getType().equals("Match")) {
				playGame = new JButton("Play");
				Scheduler sch = this;
				playGame.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						for (Map.Entry<String, JPanel> page : getMatchFramesMap().entrySet()) {
							MatchFrames frame = (MatchFrames) page.getValue();
							event.getMatch().setScheduler(sch);
							frame.setMatch(event.getMatch());
						}
						sch.displayMatchFrames(event.getMatch());
						event.setRemoveEvent(true);
					}
				});
				System.out.println("Adding a new playgame button");
				southMiddle.add(playGame);

				// This is the simulate match button and its functionality
				simGame = new JButton("Simulate");
				Scheduler thissch = this;
				simGame.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						southMiddle.remove(simGame);
						southMiddle.remove(playGame);
						if (event.getMatch() != null) {
							// Change back to a normal match as this is not being shown to user
							Match convertedMatch = new Match(event.getMatch());
							convertedMatch.startMatch(thissch, "instant");

							for(Match eachMatch : event.getMatch().getSameDayMatches()){
								CompletableFuture.runAsync(() -> eachMatch.startMatch("instant"));
							}
							myFixtures.getLine(event.getMatch()).gameComplete();
						}

						event.setRemoveEvent(true);
						showTodaysEvents(todaysEvents);
					}
				});
				System.out.println("Adding a simgame button");
				southMiddle.add(simGame);
			} else {
				// This is not a match event
				JButton dismiss = new JButton("Dismiss");
				dismiss.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						eventContainer.removeAll();
						southMiddle.remove(dismiss);
						southMiddle.add(advance);
						event.setRemoveEvent(true);
						showTodaysEvents(todaysEvents);
						eventContainer.repaint();
						southMiddle.revalidate();
						southMiddle.repaint();
					}
				});
				// Only need 1 dismiss button
				if (!southMiddle.isAncestorOf(dismiss)) {
					System.out.println("Adding a dismiss button");
					southMiddle.add(dismiss);
				}
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
		ratingsPanel.refreshLines();
		currentPage.getFooterPanel().getMiddleBox().add(currentPage.getResumeButton());
		currentPage.displayTacticsButton();
		addSpeedometer(currentPage.getSpeedometerBox());
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
		emptySpeedometer.setBackground(Color.LIGHT_GRAY);
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
		tablePanel.getFooterPanel().removeKeyListeners();
	}

	public void showEventsDescription(Events event) {
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
		this.laterMatches = new ArrayList<>();
		this.sameDayMatches = new ArrayList<>();

		System.out.println("All Events: " + events);
		System.out.println("Events size: " + events.size());
		// Need ones that aren't finished
//		System.out.println("All Fixtures: " + league.getFixtures());
//		System.out.println("Fixtures Size: " + league.getFixtures().size());
		eventContainer.removeAll();
		mainPanel.repaint();
		date = date.plusDays(1);

		String todaysDateFormatted = getTodaysDateWithGoodFormat();
		todaysDate.setText(todaysDateFormatted);

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

	public JButton getPlayGame() {
		return playGame;
	}

	public void setPlayGame(JButton playGame) {
		this.playGame = playGame;
	}

	public JButton getAdvanceToGame() {
		return advanceToGame;
	}

	public void setAdvanceToGame(JButton advanceToGame) {
		this.advanceToGame = advanceToGame;
	}

	public JButton getSimGame() {
		return simGame;
	}

	public void setSimGame(JButton simGame) {
		this.simGame = simGame;
	}

	public JButton getMenuButton() {
		return menuButton;
	}

	public void setMenuButton(JButton menuButton) {
		this.menuButton = menuButton;
	}

	public JButton getCloseButton() {
		return closeButton;
	}

	public void setCloseButton(JButton closeButton) {
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

	public JLabel getTodaysDate() {
		return todaysDate;
	}

	public void setTodaysDate(JLabel todaysDate) {
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
}
