package visuals.MatchFrames;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Box;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import entities.UsersMatch;
import visuals.CustomizedElements.CardmapMainPageTemplate;
import visuals.CustomizedElements.CustomizedButton;
import visuals.CustomizedElements.SlidingPanel;

public class MatchFrames extends CardmapMainPageTemplate {

	private static final String PLAY = "Play Game";
	private SlidingPanel slidingPanel;
	private UsersMatch match;
	private CustomizedButton playButton;
	private InputMap playButtonInputMap;
	private ActionMap playButtonActionMap;

	public MatchFrames(CardLayout cardLayout, JPanel pages, UsersMatch match) {
    	super(cardLayout, pages);
        this.match = match;

		// Adding match event sliding panel
        slidingPanel = new SlidingPanel();
        slidingPanel.setBounds(0, 0, 800, 600);

        getLayeredPane().add(slidingPanel, JLayeredPane.MODAL_LAYER);
		getHeaderPanel().setTitle(match.getHome().getName() + " " + match.getHomeScore() + " - " + match.getAwayScore() + " " + match.getAway().getName());

		// Adding stadium, date and attendance data to footer element
		int day = match.getDateTime().getDayOfMonth();
		String dayWithSuffix = day + getDayOfMonthSuffix(day);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy HH:mm");
		String formattedDate = dayWithSuffix + " " + match.getDateTime().format(formatter);

		JLabel dateAndTime = new JLabel(formattedDate);
		setPermanentWidthAndHeight(dateAndTime,200,30);
		dateAndTime.setBorder(new EmptyBorder(0, 15, 0, 0));

		JLabel stadium = new JLabel(match.getStadium(), SwingConstants.CENTER);
		setPermanentWidthAndHeight(stadium,200,30);

		JLabel attendance = new JLabel("60000", SwingConstants.RIGHT);
		setPermanentWidthAndHeight(attendance,200,30);
		attendance.setBorder(new EmptyBorder(0, 0, 0, 20));

		Box line = Box.createHorizontalBox();
		line.add(dateAndTime);
		line.add(Box.createHorizontalStrut(100));
		line.add(stadium);
		line.add(Box.createHorizontalStrut(100));
		line.add(attendance);

		getFooterPanel().add(line, BorderLayout.NORTH);

		// Adding play button
		playButton = new CustomizedButton("Play");
		playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (match.getMinute() == 0) {
					handleClick();
				}
			}
		});

		playButtonInputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		playButtonActionMap = getActionMap();

		playButtonInputMap.put(KeyStroke.getKeyStroke("ENTER"), PLAY);
		playButtonActionMap.put(PLAY, new MatchFrames.PlayGame());

		getFooterPanel().getMiddleBox().add(playButton);
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
	
	public void createContinueButton() {
		// Adding the continue button
		CustomizedButton cont = new CustomizedButton("Continue");
		cont.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	match.getWindow().getContentPane().removeAll();
            	match.getScheduler().displayPage(match.getWindow());
				match.getScheduler().refreshMessages();
            	match.getWindow().revalidate();
            	match.getWindow().repaint();
            }
        });

		Action contAction = new AbstractAction() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		    	match.getWindow().getContentPane().removeAll();
            	match.getScheduler().displayPage(match.getWindow());
				match.getScheduler().refreshMessages();
            	match.getWindow().revalidate();
            	match.getWindow().repaint();
		    }
		};

		getFooterPanel().getFooterActionMap().put(PLAY, contAction);

		getFooterPanel().getMiddleBox().add(cont);
		getFooterPanel().getButtonBox().revalidate();
		getFooterPanel().getButtonBox().repaint();
	}
    
    public class PlayGame extends AbstractAction {

		private static final long serialVersionUID = 7874836628287155601L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(match.getMinute() == 0){
				handleClick();
			}
		}
    }

	public void removePlayButton(){
		// Removing the play button as soon as it's clicked
		getFooterPanel().getMiddleBox().remove(getPlayButton());
	}

	public void handleClick() {
		// Start the match
		match.startMatch();
	}
	
	public void goalAlert(String name, int minute) {
    	getSlidingPanel().startSliding(name, minute);
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

	public void setMatch(UsersMatch match) {
		this.match = match;
	}

	public CustomizedButton getPlayButton() {
		return playButton;
	}

	public void setPlayButton(CustomizedButton playButton) {
		this.playButton = playButton;
	}
}
