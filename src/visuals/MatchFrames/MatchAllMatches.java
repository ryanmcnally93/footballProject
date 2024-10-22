package visuals.MatchFrames;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import entities.Match;
import entities.UsersMatch;

public class MatchAllMatches extends MatchFrames {

    private static final long serialVersionUID = 2216959922650578188L;
    private Box centerBox;

    public MatchAllMatches(CardLayout layout, JPanel pages, UsersMatch match) {
        super(layout, pages, match);

        JLayeredPane layeredPane = getLayeredPane();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        appendEastAndWest(mainPanel);

        centerBox = Box.createVerticalBox();
        mainPanel.add(centerBox, BorderLayout.CENTER);

        mainPanel.setBounds(0, 80, 800, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);

        setVisible(true);
    }

    public void addTodaysMatchesToPage() {
        if(getMatch().getSameDayMatches() != null) {
            centerBox.removeAll();
            for (Match eachBackgroundMatch : getMatch().getSameDayMatches()) {
                // Match Title
                JLabel matchTitle = new JLabel("", SwingConstants.CENTER);
                matchTitle.setBorder(new CompoundBorder(
                        new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY),
                        new EmptyBorder(3, 5, 5, 5)));
                matchTitle.setText(eachBackgroundMatch.getScore());
                setPermanentWidth(matchTitle, centerBox.getWidth());
                centerBox.add(matchTitle);

                // Box for scorers
                JPanel scorerPanel = new JPanel();
                scorerPanel.setBorder(new CompoundBorder(
                        new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY),
                        new EmptyBorder(3, 5, 5, 5)));
                setPermanentWidth(scorerPanel, centerBox.getWidth());
                scorerPanel.setLayout(new BorderLayout());
                centerBox.add(scorerPanel);

                // Home Scorers
                for(String eachGoalscorer : eachBackgroundMatch.getHomeScorers()) {
                    JLabel matchScorer = new JLabel("", SwingConstants.CENTER);
                    matchScorer.setBorder(new EmptyBorder(3, 5, 5, 5));
                    matchScorer.setText(eachGoalscorer);
                    setPermanentWidth(matchScorer, centerBox.getWidth()/2);
                    scorerPanel.add(matchScorer, BorderLayout.WEST);
                }

                // Away Scorers
                for(String eachGoalscorer : eachBackgroundMatch.getAwayScorers()) {
                    JLabel matchScorer = new JLabel("", SwingConstants.CENTER);
                    matchScorer.setBorder(new EmptyBorder(3, 5, 5, 5));
                    matchScorer.setText(eachGoalscorer);
                    setPermanentWidth(matchScorer, centerBox.getWidth()/2);
                    scorerPanel.add(matchScorer, BorderLayout.EAST);
                }
            }
            centerBox.revalidate();
            centerBox.repaint();
        }
    }

}
