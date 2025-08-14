package main.java.visuals.MatchPages;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import main.java.entities.Match;
import main.java.entities.UsersMatch;
import main.java.visuals.CustomizedElements.CardmapMainPageTemplate;
import main.java.visuals.CustomizedElements.CustomizedButton;
import main.java.visuals.CustomizedElements.SlidingPanel;
import main.java.visuals.MainMenuPages.MainMenuPageTemplate;

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
	private JButton back;

	public MatchFrames(CardLayout cardLayout, JPanel pages, Speedometer speedometer, ArrayList<CustomizedButton> buttons) {
    	super(cardLayout, pages);
		this.speedometer = speedometer;
		this.pauseButton = buttons.getFirst();
		this.resumeButton = buttons.get(1);
		this.tacticsButton = buttons.get(2);
		continueButton = new CustomizedButton("Continue");

		// Adding play button
		playButton = new CustomizedButton("Play");
		playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				handleClick();
			}
		});

		back = new JButton("Back");
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// THIS BACK BUTTON IS ONLY VISIBLE WHEN WE ARE FROM SCHEDULER
				getMatch().getScheduler().getWindow().getContentPane().removeAll();
				getMatch().getScheduler().getWindow().getContentPane().add(getMatch().getScheduler().getFixturesPage(), BorderLayout.CENTER);
				if (!getMatch().isMatchHasPlayed()) {
					removeUnplayedMatchViewAttributes();
				} else {
					removePlayedMatchViewAttributes();
				}
				// Remove the back button in MatchFrames now that it has been clicked
				for (Map.Entry<String, JPanel> eachPage : match.getScheduler().getMatchFramesMap().entrySet()) {
					JPanel buttonBox = ((MatchFrames) eachPage.getValue()).getFooterPanel().getBackButtonBox();
					if (buttonBox.getComponents().length > 0) {
						buttonBox.remove(0);
					}
					((MatchFrames) eachPage.getValue()).setFromScheduler(false);
					((MatchFrames) eachPage.getValue()).removeMatchFramesContentWhenLeavingMatch();
				}
				getMatch().getScheduler().getWindow().revalidate();
				getMatch().getScheduler().getWindow().repaint();
				// We call this so that setMatch() is updated with correct Match
				getMatch().getScheduler().refreshMessages();
			}
		});

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
				match.getScheduler().viewTacticsPages(false, null);
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
	}

	public void removePlayedMatchViewAttributes() {
		removeOtherMatchFramesAttributes_WhenFromScheduler();
		getMatch().getScheduler().addPage("Watch", getMatch().getScheduler().getWatchPanel(), getMatch().getScheduler().getMatchFramesMap(), getMatch().getScheduler().getMatchFramesPages());
		getMatch().getScheduler().addPage("Table", getMatch().getScheduler().getTablePanel(), getMatch().getScheduler().getMatchFramesMap(), getMatch().getScheduler().getMatchFramesPages());
	}

	public void removeOtherMatchFramesAttributes_WhenFromScheduler() {
		for (Map.Entry<String, JPanel> eachPanel : getMatch().getScheduler().getMatchFramesMap().entrySet()) {
			MatchFrames currentPage = (MatchFrames) eachPanel.getValue();
			currentPage.getTime().setText(getMatch().getTimer().getTime());
		}
		MatchFrames tablePanel = getMatch().getScheduler().getTablePanel();
		CardmapMainPageTemplate.FooterPanel footer = tablePanel.getFooterPanel();

		footer.getButtonBox().remove(footer.getMiddleBox());
		footer.getButtonBox().add(footer.getPrevButton());
		footer.getButtonBox().add(footer.getMiddleBox());
		getMatch().getScheduler().getStatsPanel().getFooterPanel().getMiddleBox().add(getMatch().getScheduler().getStatsPanel().getPlayButton());
		footer.getButtonBox().add(footer.getNextButton());
	}

	public void removeUnplayedMatchViewAttributes() {
		MatchFrames tablePanel = getMatch().getScheduler().getTablePanel();
		CardmapMainPageTemplate.FooterPanel footer = tablePanel.getFooterPanel();

		// Set attendance back to default value 6000, change later
		tablePanel.getStadiumAndAttendance().setText(tablePanel.getStadiumAndAttendance() + " 6000");
		tablePanel.getHeaderPanel().setTitle(match.getHome().getName() + " " + match.getHomeScore() + " - " + match.getAwayScore() + " " + match.getAway().getName());
		tablePanel.addKeyListeners();
		tablePanel.getSpeedometerBox().removeAll();
		removeOtherMatchFramesAttributes_WhenFromScheduler();
	}

	public void setMatch(UsersMatch match) {
		this.match = match;
		populateMatchFramesContentForNewMatch();
	}

	public void populateMatchFramesContentForNewMatch() {
		// Add the time to the header
		time.setText(match.getTimer().getTime());
		time.setFont(new Font(time.getFont().getName(), Font.BOLD, 18));
		time.setBorder(new EmptyBorder(0, 0, 5, 0));
		getHeaderPanel().setTitle(match.getHome().getName() + " " + match.getHomeScore() + " - " + match.getAwayScore() + " " + match.getAway().getName());

		// Adding stadium, date and attendance data to footer element
		int day = match.getDateTime().getDayOfMonth();
		String dayWithSuffix = day + getDayOfMonthSuffix(day);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy HH:mm");
		String formattedDate = dayWithSuffix + " " + match.getDateTime().format(formatter);

		dateAndTime.setText(formattedDate);
		this.speedometer.setMatch(getMatch());
		stadiumAndAttendance.setText(match.getStadium() + ": 60000");
	}

	public void removeMatchFramesContentWhenLeavingMatch() {saveMatchFramesContentForMatch();}

	public void saveMatchFramesContentForMatch() {}

	@Override
	public void moveButtonsWithUser_Forwards(){
		moveButtons(getMatch().getNextPageName(), "forward");
	}

	public void moveButtons(String pageName, String direction) {
		MatchFrames page = (MatchFrames) getMatch().getScheduler().getMatchFramesMap().get(pageName);
		// Skip a page that has been removed from the cardmap
		if (page == null && direction.equals("forward")) {
			getMatch().setCurrentPageName(getMatch().getNextPageName());
			page = (MatchFrames) getMatch().getScheduler().getMatchFramesMap().get(getMatch().getNextPageName());
		} else if (page == null && direction.equals("backward")) {
			getMatch().setCurrentPageName(getMatch().getPrevPageName());
			page = (MatchFrames) getMatch().getScheduler().getMatchFramesMap().get(getMatch().getPrevPageName());
		}

		// Take components with us through the pages
		page.getSpeedometerBox().removeAll();
		if (isFromScheduler() && getMatch().getCurrentPageName().equals("Table") && direction.equals("forward")) {
			page.getSpeedometerBox().add(getMatch().getScheduler().getAllMatchesPanel().getSpeedometerBox().getComponent(0));
		} else if (isFromScheduler() && getMatch().getCurrentPageName().equals("Watch") && direction.equals("forward")) {
			page.getSpeedometerBox().add(getMatch().getScheduler().getRatingsPanel().getSpeedometerBox().getComponent(0));
		} else if (isFromScheduler() && getMatch().getCurrentPageName().equals("Table") && direction.equals("backward")) {
			page.getSpeedometerBox().add(getMatch().getScheduler().getRatingsPanel().getSpeedometerBox().getComponent(0));
		} else if (isFromScheduler() && getMatch().getCurrentPageName().equals("Watch") && direction.equals("backward")) {
			page.getSpeedometerBox().add(getMatch().getScheduler().getScorerPanel().getSpeedometerBox().getComponent(0));
		} else {
			page.getSpeedometerBox().add(getCurrentPage().getSpeedometerBox().getComponent(0));
		}

		if (getMatch().getTimer().isPaused()) {
			page.getFooterPanel().getMiddleBox().add(getResumeButton());
			page.getFooterPanel().getMiddleBox().add(getTacticsButton());
		} else if (!getMatch().getTimer().isPaused() && getMatch().isInMiddleOfMatch()) {
			page.getFooterPanel().getMiddleBox().add(getPauseButton());
		} else if (getMatch().getTimer().getTime().equals("00:00")) {
			if (page.getFooterPanel().getMiddleBox().getComponents().length == 0 ) {
				page.getFooterPanel().getMiddleBox().add(page.getPlayButton());
			}
		}

		// Back button moving
		if (page.isFromScheduler()) {
			if (!page.getFooterPanel().getBackButtonBox().isAncestorOf(page.getBackButton())) {
				page.getFooterPanel().getBackButtonBox().add(page.getBackButton());
				page.getFooterPanel().getBackButtonBox().revalidate();
				page.getFooterPanel().getBackButtonBox().repaint();
			}
		}
		getMatch().setCurrentPageName(page.getMatchFrameName());
	}

	@Override
	public void moveButtonsWithUser_Backwards(){
		moveButtons(getMatch().getPrevPageName(), "backward");
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

		// We are viewing an old event, time has already been rounded up and parsed once
		if (!time.contains(":")) {
			return Integer.parseInt(time);
		}

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
		if (continueButton.getMouseListeners().length == 2) {
			continueButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					getMatch().continueToScheduler();
				}
			});
		}

		// This needs to override play button
		Action contAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getMatch().continueToScheduler();
			}
		};

		getFooterPanel().getMiddleBox().remove(getPauseButton());
		getFooterPanel().getMiddleBox().add(continueButton);
		getFooterPanel().getButtonBox().revalidate();
		getFooterPanel().getButtonBox().repaint();
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
		if (match.getTimer().getTime().equals("00:00")) {
			match.startMatch(match.getSpeed());
		}
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

	public JButton getBackButton() {
		return back;
	}

	public JLabel getStadiumAndAttendance() {
		return stadiumAndAttendance;
	}

	public void setStadiumAndAttendance(JLabel stadiumAndAttendance) {
		this.stadiumAndAttendance = stadiumAndAttendance;
	}

	public String getMatchFrameName() {
		return "";
	}
}
