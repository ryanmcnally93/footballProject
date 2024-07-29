package visuals;
import java.util.List;
import java.util.Map;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class MatchWatch extends MatchFrames {
    private static final long serialVersionUID = 5937268249853937276L;
    private JLabel liveScore;
    private JLabel homeShotsOn;
    private JLabel awayShotsOn;
    private JLabel homeAllShots;
    private JLabel awayAllShots;
    private CustomProgressBar shotsOnBar;
    private CustomProgressBar allShotsBar;
    private Box headerBox, centerBox;

    public MatchWatch(CardLayout layout, JPanel pages, Map<String, JPanel> cardMap) {
        super(layout, pages, cardMap);
        
        setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        setBackground(Color.LIGHT_GRAY);

     // MATCHWATCH STARTS
        // Header setup
        headerBox = Box.createVerticalBox();
        headerBox.setPreferredSize(new Dimension(headerBox.getWidth(), 150));
        liveScore = new JLabel("Arsenal 0 : 0 Tottenham");
        liveScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        liveScore.setHorizontalAlignment(SwingConstants.CENTER);
        headerBox.add(liveScore);
        add(headerBox, BorderLayout.NORTH);

        // Center setup
        centerBox = Box.createVerticalBox();
        Box firstTitleBox = Box.createHorizontalBox();

        Box firstLeftBox = Box.createVerticalBox();
        homeShotsOn = new JLabel("0");
        homeShotsOn.setAlignmentX(Component.LEFT_ALIGNMENT);
        homeShotsOn.setHorizontalAlignment(SwingConstants.LEFT);
        firstLeftBox.add(homeShotsOn);
        firstLeftBox.add(Box.createHorizontalGlue());
        firstLeftBox.setBorder(new EmptyBorder(0, 20, 0, 20));

        Box firstMiddleBox = Box.createVerticalBox();
        JLabel shotsOn = new JLabel("Shots on Target");
        firstMiddleBox.add(shotsOn);

        Box firstRightBox = Box.createVerticalBox();
        firstRightBox.add(Box.createHorizontalGlue());
        awayShotsOn = new JLabel("0");
        awayShotsOn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        awayShotsOn.setHorizontalAlignment(SwingConstants.RIGHT);
        firstRightBox.add(awayShotsOn);
        firstRightBox.setBorder(new EmptyBorder(0, 20, 0, 20));

        firstTitleBox.add(firstLeftBox);
        firstTitleBox.add(firstMiddleBox);
        firstTitleBox.add(firstRightBox);
        centerBox.add(firstTitleBox);
        
        Box west = Box.createHorizontalBox();
        west.setPreferredSize(new Dimension(100,200));
        add(west, BorderLayout.WEST);
        
        Box east = Box.createHorizontalBox();
        east.setPreferredSize(new Dimension(100,200));
        add(east, BorderLayout.EAST);       
        
        // Shots on target progress bar
        shotsOnBar = new CustomProgressBar();
        shotsOnBar.setBorder(new EmptyBorder(20, 20, 10, 20));
        shotsOnBar.setValue(50);
        centerBox.add(shotsOnBar);

        
        Box secondTitleBox = Box.createHorizontalBox();

        Box secondLeftBox = Box.createVerticalBox();
        homeAllShots = new JLabel("0");
        homeAllShots.setAlignmentX(Component.LEFT_ALIGNMENT);
        homeAllShots.setHorizontalAlignment(SwingConstants.LEFT);
        secondLeftBox.add(homeAllShots);
        secondLeftBox.add(Box.createHorizontalGlue());
        secondLeftBox.setBorder(new EmptyBorder(0, 20, 0, 20));

        Box secondMiddleBox = Box.createVerticalBox();
        JLabel allShots = new JLabel("Shots");
        secondMiddleBox.add(allShots);

        Box secondRightBox = Box.createVerticalBox();
        awayAllShots = new JLabel("0");
        awayAllShots.setAlignmentX(Component.RIGHT_ALIGNMENT);
        awayAllShots.setHorizontalAlignment(SwingConstants.RIGHT);
        secondRightBox.add(awayAllShots);
        secondRightBox.add(Box.createHorizontalGlue());
        secondRightBox.setBorder(new EmptyBorder(0, 20, 0, 20));
        
        secondTitleBox.add(secondLeftBox);
        secondTitleBox.add(secondMiddleBox);
        secondTitleBox.add(secondRightBox);
        centerBox.add(secondTitleBox);

        // All shots progress bar
        allShotsBar = new CustomProgressBar();
        allShotsBar.setBorder(new EmptyBorder(10, 20, 10, 20));
        allShotsBar.setValue(50);
        centerBox.add(allShotsBar);

        CustomProgressBar progressBar_2 = new CustomProgressBar();
        progressBar_2.setBorder(new EmptyBorder(10, 20, 20, 20));
        progressBar_2.setValue(50);
        centerBox.add(progressBar_2);

        JButton playButton = new JButton("Play");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	handleClick();
                System.out.println("Play button clicked!");
            }
        });
        centerBox.add(playButton);
        add(centerBox, BorderLayout.CENTER);

        // Component listener to adjust sizes
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustPanelSize(west);
                adjustPanelSize(east);
                marginChange(firstLeftBox);
                marginChange(firstRightBox);
                marginChange(secondLeftBox);
                marginChange(secondRightBox);
            }
        });
        
//      SlidingPanel slidingPanel = super.getSlidingPanel();
//      slidingPanel.setBounds(0, 0, 800, 600);
    }
    
    private void adjustPanelSize(Box box) {
    	Dimension screenSize = getSize();
        int width = screenSize.width;
        int eighth = width/8;
        box.setPreferredSize(new Dimension(eighth, box.getPreferredSize().height));
        box.setMinimumSize(new Dimension(eighth, box.getMinimumSize().height));
        box.setMaximumSize(new Dimension(eighth, box.getMaximumSize().height));
        // Revalidate and repaint to apply changes
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
    
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        
//    }

	public void handleClick() {
		match.startMatch(getGraphics(), super.getCardMap());
	}
	
	public void updateScoreBoard(int home, int away) {
		System.out.println();
		setLiveScore("Arsenal " + home + " : " + away + " Tottenham");
		repaint();
	}
	
	public void updateShotsOnBar(int home, int away) {
		int total = home + away;
		if(total != 0) {
			if(away == 0) {
				setShotsOnBar(100);
			} else {
				int segments = 100/total;
				setShotsOnBar(segments*home);
			}
			
		}
		setHomeShotsOn(home);
		setAwayShotsOn(away);
		repaint();
	}
	
	public void updateAllShotsBar(int home, int away) {
		int total = home + away;
		if(total != 0) {
			if(away == 0) {
				setAllShotsBar(100);
			} else {
				int segments = 100/total;
				setAllShotsBar(segments*home);
			}
		}
		setHomeAllShots(home);
		setAwayAllShots(away);
		repaint();
	}

	public JLabel getLiveScore() {
		return liveScore;
	}

	public void setLiveScore(String score) {
		this.liveScore.setText(score);
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

	
	public void setLiveScore(JLabel liveScore) {
		this.liveScore = liveScore;
	}

}