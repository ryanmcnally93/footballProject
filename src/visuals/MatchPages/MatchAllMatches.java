package visuals.MatchPages;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import entities.Match;
import entities.UsersMatch;
import visuals.CustomizedElements.CustomizedButton;

public class MatchAllMatches extends MatchFrames {

    private Box topBox, centerBox, bottomBox;
    private Boolean earlyUpdated;
    private Boolean laterUpdated;
    private JPanel mainPanel;

    public MatchAllMatches(CardLayout layout, JPanel pages, UsersMatch match, Speedometer speedometer, ArrayList<CustomizedButton> buttons) {
        super(layout, pages, match, speedometer, buttons);

        earlyUpdated = false;
        laterUpdated = false;

        JLayeredPane layeredPane = getLayeredPane();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        topBox = Box.createVerticalBox();
        mainPanel.add(topBox);

        centerBox = Box.createVerticalBox();
        mainPanel.add(centerBox);

        bottomBox = Box.createVerticalBox();
        mainPanel.add(bottomBox);

        mainPanel.setBounds(0, 80, 800, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);

        setVisible(true);
    }

    public void addTodaysMatchesToPage() {

        if(!earlyUpdated){
            updateMatches(topBox, getMatch().getEarlierMatches());
            earlyUpdated = true;
        }

        if(getMatch().getSameDayMatches() != null) {
            updateMatches(centerBox, getMatch().getSameDayMatches());
        }

        if(!laterUpdated){
            updateMatches(bottomBox, getMatch().getLaterMatches());
            laterUpdated = true;
        }
    }

    public void updateMatches(Box box, ArrayList<Match> matches) {
        box.removeAll();  // Clear the existing components

        for (Match eachBackgroundMatch : matches) {
            // Match Title
            JLabel matchTitle = new JLabel("", SwingConstants.CENTER) {
                @Override
                public Dimension getPreferredSize() {
                    // Enforce fixed width and height for the title
                    return new Dimension(600, 28);  // Width: 600, Height: 28
                }
            };
            matchTitle.setBorder(new CompoundBorder(
                    new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY),
                    new EmptyBorder(3, 5, 5, 5)));
            matchTitle.setText(eachBackgroundMatch.getScore());
            matchTitle.setMaximumSize(new Dimension(600, 28));  // Ensure it doesn't expand
            matchTitle.setMinimumSize(new Dimension(600, 28));  // Ensure it doesn't shrink
            matchTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            box.add(matchTitle);  // Add the match title to the box

            // Format time for future matches
            if (eachBackgroundMatch.getDateTime().isAfter(getMatch().getDateTime())) {
                int hour = eachBackgroundMatch.getDateTime().getHour();
                int minute = eachBackgroundMatch.getDateTime().getMinute();
                String home = eachBackgroundMatch.getHome().getName();
                String away = eachBackgroundMatch.getAway().getName();
                if (minute == 0) {
                    matchTitle.setText(home + " " + hour + ":00 " + away);
                } else {
                    matchTitle.setText(home + " " + hour + ":" + minute + " " + away);
                }
            }

            // Scorer Panel
            JPanel scorerPanel = new JPanel();
            scorerPanel.setBorder(new CompoundBorder(
                    new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY),
                    new EmptyBorder(3, 5, 5, 5)));
            scorerPanel.setLayout(new BorderLayout());  // Layout for the scorer panel
            setPermanentWidthAndHeight(scorerPanel, 600, 100);
            scorerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center it in the box
            box.add(scorerPanel);  // Add scorer panel to the box

            // Vertical boxes for home and away scorers
            Box homeVerticalBox = Box.createVerticalBox();
            Box awayVerticalBox = Box.createVerticalBox();
            scorerPanel.add(awayVerticalBox, BorderLayout.EAST);
            scorerPanel.add(homeVerticalBox, BorderLayout.WEST);

            int homeGoalCounter = 0;
            for (String eachGoalscorer : eachBackgroundMatch.getHomeScorers()) {
                JLabel matchScorer = new JLabel("", SwingConstants.CENTER);
                matchScorer.setBorder(new EmptyBorder(0, 5, 0, 5));
                matchScorer.setText(eachGoalscorer);
                matchScorer.setPreferredSize(new Dimension(300, 18));  // Example size
                matchScorer.setMaximumSize(new Dimension(300, 18));  // Ensure no stretching
                homeVerticalBox.add(matchScorer);
                homeGoalCounter++;
            }

            int awayGoalCounter = 0;
            for (String eachGoalscorer : eachBackgroundMatch.getAwayScorers()) {
                JLabel matchScorer = new JLabel("", SwingConstants.CENTER);
                matchScorer.setBorder(new EmptyBorder(0, 5, 0, 5));
                matchScorer.setText(eachGoalscorer);
                matchScorer.setPreferredSize(new Dimension(300, 18));  // Example size
                matchScorer.setMaximumSize(new Dimension(300, 18));  // Ensure no stretching
                awayVerticalBox.add(matchScorer);
                awayGoalCounter++;
            }

            int calculatedHeight = 0;
            if(homeGoalCounter != 0 || awayGoalCounter != 0){
                // Calculate height for scorerPanel based on number of goals
                calculatedHeight = Math.max(10 + (18 * homeGoalCounter), 10 + (18 * awayGoalCounter));
            }

            setPermanentWidthAndHeight(scorerPanel, 600, calculatedHeight);

            if(eachBackgroundMatch.getTimer().getTime().equals("90:00")){
                matchTitle.setBackground(Color.GREEN);
                matchTitle.setOpaque(true);
            }
        }

        // No need to add a rigid area unless for additional spacing at the end
        mainPanel.revalidate();
        mainPanel.repaint();
    }

}
