package visuals.MatchPages;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import entities.UsersMatch;
import visuals.CustomizedElements.CustomProgressBar;
import visuals.CustomizedElements.CustomizedButton;

public class MatchStats extends MatchFrames {
    private static final long serialVersionUID = 5937268249853937276L;
    private JLabel homeShotsOn, awayShotsOn, homeAllShots, awayAllShots, homeCorners, awayCorners, homeOffsides, awayOffsides, homeFouls, awayFouls;
    private CustomProgressBar shotsOnBar, allShotsBar, cornerBar, offsideBar, foulsBar;
    private Box centerBox;
    private Color homeColor, awayColor;
    private ArrayList<Box> boxes;

    public MatchStats(CardLayout layout, JPanel pages, UsersMatch match, Speedometer speedometer, CustomizedButton pauseButton, CustomizedButton resumeButton) {
        super(layout, pages, match, speedometer, pauseButton, resumeButton);

        // Set colours for the progress bars to match team colours
        homeColor = getMatch().getHome().getPrimaryColour();
        awayColor = getMatch().getAway().getPrimaryColour();
        if(homeColor == awayColor){
            awayColor = getMatch().getAway().getSecondaryColour();
        }
        // To help control title margins on different sized screens
        boxes = new ArrayList<>();
        
        JLayeredPane layeredPane = getLayeredPane();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        centerBox = Box.createVerticalBox();

        appendEastAndWest(mainPanel);

        // Make the five bars and titles
        shotsOnBar = new CustomProgressBar(homeColor, awayColor);
        homeShotsOn = new JLabel("0");
        awayShotsOn = new JLabel("0");
        makeBarWithTitle("Shots on Target", homeShotsOn, awayShotsOn, shotsOnBar);

        allShotsBar = new CustomProgressBar(homeColor, awayColor);
        homeAllShots = new JLabel("0");
        awayAllShots = new JLabel("0");
        makeBarWithTitle("All Shots", homeAllShots, awayAllShots, allShotsBar);

        cornerBar = new CustomProgressBar(homeColor, awayColor);
        homeCorners = new JLabel("0");
        awayCorners = new JLabel("0");
        makeBarWithTitle("Corners", homeCorners, awayCorners, cornerBar);

        offsideBar = new CustomProgressBar(homeColor, awayColor);
        homeOffsides = new JLabel("0");
        awayOffsides = new JLabel("0");
        makeBarWithTitle("Offside", homeOffsides, awayOffsides, offsideBar);

        foulsBar = new CustomProgressBar(homeColor, awayColor);
        homeFouls = new JLabel("0");
        awayFouls = new JLabel("0");
        makeBarWithTitle("Fouls", homeFouls, awayFouls, foulsBar);
        
        mainPanel.add(centerBox, BorderLayout.CENTER);
        
        mainPanel.setBounds(0, 80, 800, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);

        // Component listener to adjust marginss on different screen sizes
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustPanelSize(getWest());
                adjustPanelSize(getEast());
                for(Box box : boxes){
                    marginChange(box);
                }
            }
        });
        
        setVisible(true);
    }

    public void makeBarWithTitle(String title, JLabel home, JLabel away, CustomProgressBar bar){
        // Create titlebox
        Box titleBox = Box.createHorizontalBox();
        // Left digit above bar
        Box leftBox = Box.createVerticalBox();
        home.setAlignmentX(Component.LEFT_ALIGNMENT);
        home.setHorizontalAlignment(SwingConstants.LEFT);
        leftBox.add(home);
        leftBox.add(Box.createHorizontalGlue());
        leftBox.setBorder(new EmptyBorder(0, 20, 0, 20));
        // Bar Title
        Box middleBox = Box.createVerticalBox();
        JLabel shotsOn = new JLabel(title);
        middleBox.add(shotsOn);
        // Right digit above bar
        Box rightBox = Box.createVerticalBox();
        rightBox.add(Box.createHorizontalGlue());
        away.setAlignmentX(Component.RIGHT_ALIGNMENT);
        away.setHorizontalAlignment(SwingConstants.RIGHT);
        rightBox.add(away);
        rightBox.setBorder(new EmptyBorder(0, 20, 0, 20));
        // Add titles to the titlebox
        titleBox.add(leftBox);
        titleBox.add(middleBox);
        titleBox.add(rightBox);
        centerBox.add(titleBox);
        // Create bar underneath titles
        bar.setBorder(new EmptyBorder(20, 20, 10, 20));
        bar.setValue(50);
        centerBox.add(bar);
        boxes.add(leftBox);
        boxes.add(rightBox);

    }
    
    private void adjustPanelSize(Box box) {
    	Dimension screenSize = getSize();
        int width = screenSize.width;
        int eighth = width/8;
        setPermanentWidth(box, eighth);
        box.revalidate();
        box.repaint();
    }
    
    public void marginChange(Box box) {
    	Dimension screenSize = getSize();
        int width = screenSize.width;
        int eighth = width/8;
        int margin = eighth/5;
        box.setBorder(new EmptyBorder(0, margin, 0, margin));
        box.revalidate();
        box.repaint();
    }

    public void updateStatBar(CustomProgressBar bar, JLabel homeLabel, JLabel awayLabel, int home, int away){
        int total = home + away;
        int percentage = (total == 0) ? 50 : (100 * home / total);
        bar.setValue(percentage);
        homeLabel.setText(Integer.toString(home));
        awayLabel.setText(Integer.toString(away));
        bar.repaint();
    }
	
	public void updateShotsOnBar(int home, int away) {
		updateStatBar(getShotsOnBar(), getHomeShotsOn(), getAwayShotsOn(), home, away);
	}
	
	public void updateAllShotsBar(int home, int away) {
        updateStatBar(getAllShotsBar(), getHomeAllShots(), getAwayAllShots(), home, away);
	}

	public CustomProgressBar getShotsOnBar() {
		return shotsOnBar;
	}

	public void setShotsOnBar(int percentage) {
		shotsOnBar.setValue(percentage);
	}

	public CustomProgressBar getAllShotsBar() {
		return allShotsBar;
	}

	public void setAllShotsBar(int percentage) {
		allShotsBar.setValue(percentage);
	}

	
	public JLabel getHomeShotsOn() {
		return homeShotsOn;
	}

	
	public void setHomeShotsOn(int num) {
		this.homeShotsOn.setText(Integer.toString(num));
	}

	
	public JLabel getAwayShotsOn() {
		return awayShotsOn;
	}

	
	public void setAwayShotsOn(int num) {
		this.awayShotsOn.setText(Integer.toString(num));
	}

	
	public JLabel getHomeAllShots() {
		return homeAllShots;
	}

	
	public void setHomeAllShots(int num) {
		this.homeAllShots.setText(Integer.toString(num));
	}

	
	public JLabel getAwayAllShots() {
		return awayAllShots;
	}

	
	public void setAwayAllShots(int num) {
		this.awayAllShots.setText(Integer.toString(num));
	}


}