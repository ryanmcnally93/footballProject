package visuals.MatchFrames;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import entities.Match;
import entities.UsersMatch;

public class MatchAllMatches extends MatchFrames {

    private Box topBox, centerBox, bottomBox;
    private Boolean earlyUpdated;
    private Boolean laterUpdated;

    public MatchAllMatches(CardLayout layout, JPanel pages, UsersMatch match) {
        super(layout, pages, match);

        earlyUpdated = false;
        laterUpdated = false;

        JLayeredPane layeredPane = getLayeredPane();
        JPanel mainPanel = new JPanel();
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

    public void updateMatches(Box box, ArrayList<Match> matches){
        box.removeAll();
        for (Match eachBackgroundMatch : matches) {
            // Match Title
            JLabel matchTitle = new JLabel("", SwingConstants.CENTER);
            matchTitle.setBorder(new CompoundBorder(
                    new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY),
                    new EmptyBorder(3, 5, 5, 5)));
            matchTitle.setText(eachBackgroundMatch.getScore());
            setPermanentWidth(matchTitle, 600);
            matchTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            box.add(matchTitle);

            if(eachBackgroundMatch.getDateTime().isAfter(getMatch().getDateTime())){
                int hour = eachBackgroundMatch.getDateTime().getHour();
                int minute = eachBackgroundMatch.getDateTime().getMinute();
                String home = eachBackgroundMatch.getHome().getName();
                String away = eachBackgroundMatch.getAway().getName();
                if(minute == 0){
                    matchTitle.setText(home + " " + hour + ":00 " + away);
                } else {
                    matchTitle.setText(home + " " + hour + ":" + minute + " " + away);
                }
            }

            // Box for scorers
            JPanel scorerPanel = new JPanel();
            scorerPanel.setBorder(new CompoundBorder(
                    new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY),
                    new EmptyBorder(3, 5, 5, 5)));
            setPermanentWidth(scorerPanel, box.getWidth());
            scorerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            scorerPanel.setLayout(new BorderLayout());
            box.add(scorerPanel);

            // Vertical inner boxes for each side
            Box homeVerticalBox = Box.createVerticalBox();
            Box awayVerticalBox = Box.createVerticalBox();
            scorerPanel.add(awayVerticalBox, BorderLayout.EAST);
            scorerPanel.add(homeVerticalBox, BorderLayout.WEST);

            int homeGoalCounter = 0;

            // Home Scorers
            for(String eachGoalscorer : eachBackgroundMatch.getHomeScorers()) {
                JLabel matchScorer = new JLabel("", SwingConstants.CENTER);
                matchScorer.setBorder(new EmptyBorder(0, 5, 0, 5));
                matchScorer.setText(eachGoalscorer);
                setPermanentWidth(matchScorer, box.getWidth()/2);
                homeVerticalBox.add(matchScorer);
                homeGoalCounter++;
            }

            int awayGoalCounter = 0;

            // Away Scorers
            for(String eachGoalscorer : eachBackgroundMatch.getAwayScorers()) {
                JLabel matchScorer = new JLabel("", SwingConstants.CENTER);
                matchScorer.setBorder(new EmptyBorder(0, 5, 0, 5));
                matchScorer.setText(eachGoalscorer);
                setPermanentWidth(matchScorer,  box.getWidth()/2);
                awayVerticalBox.add(matchScorer);
                awayGoalCounter++;
            }

            if(homeGoalCounter == 0 && awayGoalCounter == 0){
                setPermanentHeight(scorerPanel, 0);
            } else if(homeGoalCounter > awayGoalCounter){
                setPermanentHeight(scorerPanel, 10+(18*homeGoalCounter));
            } else {
                setPermanentHeight(scorerPanel, 10+(18*awayGoalCounter));
            }

            if(eachBackgroundMatch.getTimer().getTime().equals("90:00")){
                matchTitle.setBackground(Color.GREEN);
                matchTitle.setOpaque(true);
            }
        }

        box.revalidate();
        box.repaint();
    }

}
