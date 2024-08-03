package visuals.MatchFrames;
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

import visuals.CustomizedElements.CustomProgressBar;

public class MatchStats extends MatchFrames {
    private static final long serialVersionUID = 5937268249853937276L;
    private JLabel homeShotsOn, awayShotsOn, homeAllShots, awayAllShots, homeCorners, awayCorners, homeOffsides, awayOffsides, homeFouls, awayFouls;
    private CustomProgressBar shotsOnBar, allShotsBar, cornerBar, offsideBar, foulsBar;
    private Box centerBox;

    public MatchStats(CardLayout layout, JPanel pages, Map<String, JPanel> cardMap) {
        super(layout, pages, cardMap);
        
        // START OF MATCHWATCH
        
        JLayeredPane layeredPane = getLayeredPane();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        centerBox = Box.createVerticalBox();
        Box west = Box.createHorizontalBox();
        west.setPreferredSize(new Dimension(100,200));
        mainPanel.add(west, BorderLayout.WEST);
        Box east = Box.createHorizontalBox();
        east.setPreferredSize(new Dimension(100,200));
        mainPanel.add(east, BorderLayout.EAST); 
        
        // SHOTS ON TARGET
       
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
        
        shotsOnBar = new CustomProgressBar();
        shotsOnBar.setBorder(new EmptyBorder(20, 20, 10, 20));
        shotsOnBar.setValue(50);
        centerBox.add(shotsOnBar);

        // ALL SHOTS
        
        Box secondTitleBox = Box.createHorizontalBox();

        Box secondLeftBox = Box.createVerticalBox();
        homeAllShots = new JLabel("0");
        homeAllShots.setAlignmentX(Component.LEFT_ALIGNMENT);
        homeAllShots.setHorizontalAlignment(SwingConstants.LEFT);
        secondLeftBox.add(homeAllShots);
        secondLeftBox.add(Box.createHorizontalGlue());
        secondLeftBox.setBorder(new EmptyBorder(0, 20, 0, 20));

        Box secondMiddleBox = Box.createVerticalBox();
        JLabel allShots = new JLabel("All Shots");
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

        allShotsBar = new CustomProgressBar();
        allShotsBar.setBorder(new EmptyBorder(10, 20, 10, 20));
        allShotsBar.setValue(50);
        centerBox.add(allShotsBar);
        
        // CORNERS
        
        Box thirdTitleBox = Box.createHorizontalBox();

        Box thirdLeftBox = Box.createVerticalBox();
        homeCorners = new JLabel("0");
        homeCorners.setAlignmentX(Component.LEFT_ALIGNMENT);
        homeCorners.setHorizontalAlignment(SwingConstants.LEFT);
        thirdLeftBox.add(homeCorners);
        thirdLeftBox.add(Box.createHorizontalGlue());
        thirdLeftBox.setBorder(new EmptyBorder(0, 20, 0, 20));

        Box thirdMiddleBox = Box.createVerticalBox();
        JLabel corners = new JLabel("Corners");
        thirdMiddleBox.add(corners);

        Box thirdRightBox = Box.createVerticalBox();
        awayCorners = new JLabel("0");
        awayCorners.setAlignmentX(Component.RIGHT_ALIGNMENT);
        awayCorners.setHorizontalAlignment(SwingConstants.RIGHT);
        thirdRightBox.add(awayCorners);
        thirdRightBox.add(Box.createHorizontalGlue());
        thirdRightBox.setBorder(new EmptyBorder(0, 20, 0, 20));
        
        thirdTitleBox.add(thirdLeftBox);
        thirdTitleBox.add(thirdMiddleBox);
        thirdTitleBox.add(thirdRightBox);
        centerBox.add(thirdTitleBox);
        
        cornerBar = new CustomProgressBar();
        cornerBar.setBorder(new EmptyBorder(10, 20, 10, 20));
        cornerBar.setValue(50);
        centerBox.add(cornerBar);
        
        // OFFSIDES
        
        Box fourthTitleBox = Box.createHorizontalBox();

        Box fourthLeftBox = Box.createVerticalBox();
        homeOffsides = new JLabel("0");
        homeOffsides.setAlignmentX(Component.LEFT_ALIGNMENT);
        homeOffsides.setHorizontalAlignment(SwingConstants.LEFT);
        fourthLeftBox.add(homeOffsides);
        fourthLeftBox.add(Box.createHorizontalGlue());
        fourthLeftBox.setBorder(new EmptyBorder(0, 20, 0, 20));

        Box fourthMiddleBox = Box.createVerticalBox();
        JLabel offside = new JLabel("Offside");
        fourthMiddleBox.add(offside);

        Box fourthRightBox = Box.createVerticalBox();
        awayOffsides = new JLabel("0");
        awayOffsides.setAlignmentX(Component.RIGHT_ALIGNMENT);
        awayOffsides.setHorizontalAlignment(SwingConstants.RIGHT);
        fourthRightBox.add(awayOffsides);
        fourthRightBox.add(Box.createHorizontalGlue());
        fourthRightBox.setBorder(new EmptyBorder(0, 20, 0, 20));
        
        fourthTitleBox.add(fourthLeftBox);
        fourthTitleBox.add(fourthMiddleBox);
        fourthTitleBox.add(fourthRightBox);
        centerBox.add(fourthTitleBox);
        
        offsideBar = new CustomProgressBar();
        offsideBar.setBorder(new EmptyBorder(10, 20, 10, 20));
        offsideBar.setValue(50);
        centerBox.add(offsideBar);
        
        // FOULS
        
        Box fifthTitleBox = Box.createHorizontalBox();

        Box fifthLeftBox = Box.createVerticalBox();
        homeFouls = new JLabel("0");
        homeFouls.setAlignmentX(Component.LEFT_ALIGNMENT);
        homeFouls.setHorizontalAlignment(SwingConstants.LEFT);
        fifthLeftBox.add(homeFouls);
        fifthLeftBox.add(Box.createHorizontalGlue());
        fifthLeftBox.setBorder(new EmptyBorder(0, 20, 0, 20));

        Box fifthMiddleBox = Box.createVerticalBox();
        JLabel fouls = new JLabel("Fouls");
        fifthMiddleBox.add(fouls);

        Box fifthRightBox = Box.createVerticalBox();
        awayFouls = new JLabel("0");
        awayFouls.setAlignmentX(Component.RIGHT_ALIGNMENT);
        awayFouls.setHorizontalAlignment(SwingConstants.RIGHT);
        fifthRightBox.add(awayFouls);
        fifthRightBox.add(Box.createHorizontalGlue());
        fifthRightBox.setBorder(new EmptyBorder(0, 20, 0, 20));
        
        fifthTitleBox.add(fifthLeftBox);
        fifthTitleBox.add(fifthMiddleBox);
        fifthTitleBox.add(fifthRightBox);
        centerBox.add(fifthTitleBox);
        
        foulsBar = new CustomProgressBar();
        foulsBar.setBorder(new EmptyBorder(10, 20, 10, 20));
        foulsBar.setValue(50);
        centerBox.add(foulsBar);
        
mainPanel.add(centerBox, BorderLayout.CENTER);
        
        mainPanel.setBounds(0, 80, 800, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
        
        // END OF MATCHWATCH

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
                marginChange(thirdLeftBox);
                marginChange(thirdRightBox);
                marginChange(fourthLeftBox);
                marginChange(fourthRightBox);
                marginChange(fifthLeftBox);
                marginChange(fifthRightBox);
            }
        });
        
        setVisible(true);
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