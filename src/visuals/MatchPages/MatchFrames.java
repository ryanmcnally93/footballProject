package visuals.MatchPages;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import entities.Match;
import entities.Team;
import entities.UsersMatch;
import visuals.CustomizedElements.*;
import visuals.MainMenuPages.MainMenuPageTemplate;

public class MatchFrames extends CardmapMainPageTemplate {

	private static final String PLAY = "Play Game";
	private SlidingPanel slidingPanel;
	private UsersMatch match;
	private CustomizedButton playButton, resumeButton, pauseButton, tacticsButton, continueButton;
	private InputMap playButtonInputMap;
	private ActionMap playButtonActionMap;
	private JLabel time, dateAndTime, stadiumAndAttendance;
	private Speedometer speedometer;
	private Box speedometerBox;

	public MatchFrames(CardLayout cardLayout, JPanel pages, Speedometer speedometer, ArrayList<CustomizedButton> buttons) {
    	super(cardLayout, pages);
		this.speedometer = speedometer;
		this.pauseButton = buttons.getFirst();
		this.resumeButton = buttons.get(1);
		this.tacticsButton = buttons.get(2);
		continueButton = new CustomizedButton("Continue");

		dateAndTime = new JLabel();
		setPermanentWidthAndHeight(dateAndTime,200,30);
		dateAndTime.setBorder(new EmptyBorder(0, 15, 0, 0));

		stadiumAndAttendance = new JLabel("", SwingConstants.RIGHT);
		setPermanentWidthAndHeight(stadiumAndAttendance,200,30);
		stadiumAndAttendance.setBorder(new EmptyBorder(0, 0, 0, 20));

		Box line = Box.createHorizontalBox();
		line.add(dateAndTime);
		speedometerBox = Box.createHorizontalBox();
		line.add(speedometerBox);
		line.add(stadiumAndAttendance);
		getFooterPanel().add(line, BorderLayout.NORTH);

		time = new JLabel("", SwingConstants.CENTER);
		getHeaderPanel().add(time, BorderLayout.SOUTH);
		slidingPanel = new SlidingPanel();
		slidingPanel.setBounds(0, 0, 800, 600);

		getLayeredPane().add(slidingPanel, JLayeredPane.MODAL_LAYER);
	}

	public void setMatch(UsersMatch match) {
		this.match = match;
		// Add the time to the header
		time.setText(match.getTimer().getTime());
		time.setFont(new Font(time.getFont().getName(), Font.BOLD, 18));
		time.setBorder(new EmptyBorder(0, 0, 5, 0));
		if (!getMatch().getTimer().getTime().equals("90:00") && getFooterPanel().getMiddleBox().isAncestorOf(getContinueButton())) {
			getFooterPanel().getMiddleBox().remove(getContinueButton());
		}
		getHeaderPanel().setTitle(match.getHome().getName() + " " + match.getHomeScore() + " - " + match.getAwayScore() + " " + match.getAway().getName());

		// Adding stadium, date and attendance data to footer element
		int day = match.getDateTime().getDayOfMonth();
		String dayWithSuffix = day + getDayOfMonthSuffix(day);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy HH:mm");
		String formattedDate = dayWithSuffix + " " + match.getDateTime().format(formatter);

		dateAndTime.setText(formattedDate);
		this.speedometer.setMatch(getMatch());
		stadiumAndAttendance.setText(match.getStadium() + ": 60000");

		// Adding play button
		playButton = new CustomizedButton("Play");
		playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (match.getTimer().getTime().equals("00:00")) {
					handleClick();
				}
			}
		});

		getPauseButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getFooterPanel().getMiddleBox().remove(getPauseButton());
				// Lets pause all the matches in the array
				for (Match each : getMatch().getSameDayMatches()) {
					if (each.getTimer().isRunning()) {
						each.getTimer().pauseTimer();
					}
				}
				// Sometimes the array doesn't contain the main match
				if (!getMatch().getSameDayMatches().contains(match)) {
					getMatch().getTimer().pauseTimer();
				}

				MatchFrames current = getCurrentPage();
				current.getFooterPanel().getMiddleBox().add(getResumeButton());
				current.getFooterPanel().getMiddleBox().revalidate();
				current.getFooterPanel().getMiddleBox().repaint();
			}
		});

		getTacticsButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewTacticsDuringMatch();
			}
		});

		getResumeButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getFooterPanel().getMiddleBox().remove(getResumeButton());
				getFooterPanel().getMiddleBox().remove(getTacticsButton());
				// Lets resume all the matches in the array
				for (Match each : getMatch().getSameDayMatches()) {
					each.getTimer().resumeTimer();
				}
				// Sometimes the array doesn't contain the main match
				if (!getMatch().getSameDayMatches().contains(match)) {
					getMatch().getTimer().resumeTimer();
				}

				MatchFrames current = getCurrentPage();
				current.getFooterPanel().getMiddleBox().add(getPauseButton());
				current.getFooterPanel().getMiddleBox().revalidate();
				current.getFooterPanel().getMiddleBox().repaint();
			}
		});

		playButtonInputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		playButtonActionMap = getActionMap();

		playButtonInputMap.put(KeyStroke.getKeyStroke("ENTER"), PLAY);
		playButtonActionMap.put(PLAY, new MatchFrames.PlayGame());

		getFooterPanel().getMiddleBox().add(playButton);
		addContentForChildClass();
	}

	public void addContentForChildClass() {}

	public void removeContentForChildClass() {}

	@Override
	public void moveSpeedometerForward(){
		String nextPageName = getMatch().getNextPageName();
		MatchFrames page = (MatchFrames) getMatch().getScheduler().getMatchFramesMap().get(nextPageName);
		page.getSpeedometerBox().add(this.speedometer);
		if(getMatch().getTimer().isPaused()) {
			page.getFooterPanel().getMiddleBox().add(getResumeButton());
			page.getFooterPanel().getMiddleBox().add(getTacticsButton());
		} else if (!getMatch().getTimer().isPaused() && getMatch().isInMiddleOfMatch()){
			page.getFooterPanel().getMiddleBox().add(getPauseButton());
		}
		getMatch().setCurrentPageName(nextPageName);
	}

	@Override
	public void moveSpeedometerBack(){
		String prevPageName = getMatch().getPrevPageName();
		MatchFrames page = (MatchFrames) getMatch().getScheduler().getMatchFramesMap().get(prevPageName);
		page.getSpeedometerBox().add(this.speedometer);
		if(getMatch().getTimer().isPaused()) {
			page.getFooterPanel().getMiddleBox().add(getResumeButton());
			page.getFooterPanel().getMiddleBox().add(getTacticsButton());
		} else if (!getMatch().getTimer().isPaused() && getMatch().isInMiddleOfMatch()){
			page.getFooterPanel().getMiddleBox().add(getPauseButton());
		}
		getMatch().setCurrentPageName(prevPageName);
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

	public void updateScoreBoard(int home, int away) {
		getHeaderPanel().setTitle(match.getHome().getName() + " " + home + " - " + away + " " + match.getAway().getName());
		repaint();
	}

	public int findRoundedInt(String time){
		int roundedUp = 0;

		// This sets gives us the time, rounded up to the next minute
		try {
			if(time.startsWith("0:")){
				roundedUp = 1;
			} else {
				int newTime = Integer.parseInt(time.substring(0, 2));
				roundedUp = newTime + 1;
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid number format: " + time.substring(0, 2));
		}
		return roundedUp;
	}

	public void createContinueButton() {
		// Adding the continue button
		if (continueButton.getMouseListeners().length == 1) {
			continueButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					continueToScheduler();
				}
			});
		}

		// This needs to override play button
		Action contAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				continueToScheduler();
			}
		};

		getFooterPanel().getMiddleBox().remove(getPauseButton());
		getFooterPanel().getMiddleBox().add(continueButton);
		getFooterPanel().getButtonBox().revalidate();
		getFooterPanel().getButtonBox().repaint();
	}

	public void viewTacticsDuringMatch() {
		match.getScheduler().getWindow().getContentPane().removeAll();
		for (Map.Entry<String, JPanel> eachTacticsPage : match.getScheduler().getTacticsMap().entrySet()) {
			((MainMenuPageTemplate) eachTacticsPage.getValue()).setFromScheduler(false);
			((MainMenuPageTemplate) eachTacticsPage.getValue()).updateBackButtonFunctionality(match);
		}
		match.getScheduler().getWindow().getContentPane().add(match.getScheduler().getTacticsPages(), BorderLayout.CENTER);
		match.getScheduler().getTacticsLayout().show(match.getScheduler().getTacticsPages(), "First Team");
		match.getScheduler().getWindow().revalidate();
		match.getScheduler().getWindow().repaint();

	}

	public void continueToScheduler(){
		for (Map.Entry<String, JPanel> eachPage : match.getScheduler().getMatchFramesMap().entrySet()) {
			((MatchFrames) eachPage.getValue()).removeContentForChildClass();
		}
		match.getScheduler().getWindow().getContentPane().removeAll();
		match.getScheduler().displayPage(match.getScheduler().getWindow());
		match.getScheduler().refreshMessages();

		match.getLeague().getPlayerLeaderboard().updateLinesInTableLogic("Goals");

		match.getScheduler().getWindow().revalidate();
		match.getScheduler().getWindow().repaint();
		// Play whatever later matches we have
		if(!match.getLaterMatches().isEmpty()){
			for(Match eachMatch : match.getLaterMatches()){
				CompletableFuture.runAsync(() -> eachMatch.startMatch("instant"));
			}
		}
		// Set the back button on tactics cardmap to normal
		for (Map.Entry<String, JPanel> eachTacticsPage : match.getScheduler().getTacticsMap().entrySet()) {
			((MainMenuPageTemplate) eachTacticsPage.getValue()).setFromScheduler(true);
			((MainMenuPageTemplate) eachTacticsPage.getValue()).updateBackButtonFunctionality(match);
		}
	}

    public class PlayGame extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(match.getTimer().getTime().equals("00:00")){
				handleClick();
			}
		}
    }

	public void replacePlayButtonWithPauseButton(){
		// Removing the play button as soon as it's clicked
		getFooterPanel().getMiddleBox().remove(getPlayButton());
		MatchFrames current = getCurrentPage();
		current.getFooterPanel().getMiddleBox().add(getPauseButton());
		current.getFooterPanel().getMiddleBox().revalidate();
		current.getFooterPanel().getMiddleBox().repaint();
	}

	public void displayTacticsButton() {
		MatchFrames current = getCurrentPage();
		if (!getTacticsButton().isAncestorOf(current.getFooterPanel().getMiddleBox())) {
			current.getFooterPanel().getMiddleBox().add(getTacticsButton());
			current.getFooterPanel().getMiddleBox().revalidate();
			current.getFooterPanel().getMiddleBox().repaint();
		}
	}

	public MatchFrames getCurrentPage(){
		String currentPageString = getMatch().getCurrentPageName();
		return (MatchFrames) getMatch().getScheduler().getMatchFramesMap().get(currentPageString);
	}

	public void handleClick() {
		// Start the match
		match.startMatch(match.getSpeed());
	}
	
	public void goalAlert(String name, String time) {
    	getSlidingPanel().startSliding(name, time);
    }

	public SlidingPanel getSlidingPanel() {
		return slidingPanel;
	}

	public void setSlidingPanel(SlidingPanel slidingPanel) {
		this.slidingPanel = slidingPanel;
	}

	public UsersMatch getMatch() {
		return match;
	}

	public CustomizedButton getPlayButton() {
		return playButton;
	}

	public void setPlayButton(CustomizedButton playButton) {
		this.playButton = playButton;
	}

	public JLabel getTime() {
		return time;
	}

	public void setTime(JLabel time) {
		this.time = time;
	}

	public Box getSpeedometerBox() {
		return speedometerBox;
	}

	public void setSpeedometerBox(Box speedometerBox) {
		this.speedometerBox = speedometerBox;
	}

	public CustomizedButton getResumeButton() {
		return resumeButton;
	}

	public void setResumeButton(CustomizedButton resumeButton) {
		this.resumeButton = resumeButton;
	}

	public CustomizedButton getPauseButton() {
		return pauseButton;
	}

	public void setPauseButton(CustomizedButton pauseButton) {
		this.pauseButton = pauseButton;
	}

	public CustomizedButton getTacticsButton() {
		return tacticsButton;
	}

	public void setTacticsButton(CustomizedButton tacticsButton) {
		this.tacticsButton = tacticsButton;
	}

	public CustomizedButton getContinueButton() {
		return continueButton;
	}

	public void setContinueButton(CustomizedButton continueButton) {
		this.continueButton = continueButton;
	}
}
