package visuals.MatchFrames;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

import entities.UsersMatch;
import people.Footballer;

public class MatchRatings extends MatchFrames {

	private static final long serialVersionUID = -8877342968514201485L;
	private Box centerBox;
    private JPanel mainPanel;
    private Map<String, Box> playerRatings;

	public MatchRatings(CardLayout layout, JPanel pages, UsersMatch match, Speedometer speedometer) {
		super(layout, pages, match, speedometer);

        playerRatings = new HashMap<>();

		JLayeredPane layeredPane = getLayeredPane();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        appendEastAndWest(mainPanel);
		
        centerBox = Box.createVerticalBox();
        JLabel title = new JLabel(getMatch().getHome().getName().toUpperCase());
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerBox.add(title);
        mainPanel.add(centerBox, BorderLayout.CENTER);

        // Create title line
        Box line = Box.createHorizontalBox();
        JLabel name = new JLabel("PLAYER NAME");
        setPermanentWidth(name, 200);
        JLabel saves = new JLabel("SAVES");
        setPermanentWidth(saves, 50);
        JLabel passingAccuracy = new JLabel("PASSING");
        setPermanentWidth(passingAccuracy, 50);
        JLabel shootingAccuracy = new JLabel("SHOOTING");
        setPermanentWidth(shootingAccuracy, 50);
        JLabel duelsWon = new JLabel("DUELS");
        setPermanentWidth(duelsWon, 50);
        JLabel fitness = new JLabel("FITNESS");
        setPermanentWidth(fitness, 50);
        JLabel rating = new JLabel("RATING");
        setPermanentWidth(rating, 50);
        line.add(name);
        line.add(duelsWon);
        line.add(passingAccuracy);
        line.add(shootingAccuracy);
        line.add(fitness);
        line.add(rating);
        setPermanentHeight(line, 30);
        centerBox.add(line);

        for(Map.Entry<String, Footballer> each : getMatch().getHomeTeam().entrySet()){
            Footballer eachPlayer = each.getValue();
            createRatingLine(eachPlayer);
        }

        String otherTeamsName = getMatch().getAway().getName();
        JButton switchTeamInView = new JButton(otherTeamsName);
        switchTeamInView.setHorizontalAlignment(SwingConstants.CENTER);
        switchTeamInView.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerBox.add(switchTeamInView);

        switchTeamInView.addActionListener(e -> {
            centerBox.removeAll();
            title.setText(getMatch().getAway().getName().toUpperCase());
            centerBox.add(title);
            centerBox.add(line);
            for(Map.Entry<String, Footballer> each : getMatch().getAwayTeam().entrySet()){
                Footballer eachPlayer = each.getValue();
                createRatingLine(eachPlayer);
            }
            String firstTeamsName = getMatch().getHome().getName();
            JButton switchTeamBack = new JButton(firstTeamsName);
            switchTeamBack.setHorizontalAlignment(SwingConstants.CENTER);
            switchTeamBack.setAlignmentX(Component.CENTER_ALIGNMENT);
            centerBox.add(switchTeamBack);
            centerBox.revalidate();
            centerBox.repaint();

            switchTeamBack.addActionListener(f -> {
                centerBox.removeAll();
                title.setText(getMatch().getHome().getName().toUpperCase());
                centerBox.add(title);
                centerBox.add(line);
                for(Map.Entry<String, Footballer> each : getMatch().getHomeTeam().entrySet()){
                    Footballer eachPlayer = each.getValue();
                    createRatingLine(eachPlayer);
                }
                centerBox.add(switchTeamInView);
                centerBox.revalidate();
                centerBox.repaint();
            });

        });
        
        mainPanel.setBounds(0, 80, 800, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
        
        setVisible(true);
		
	}

    private void createRatingLine(Footballer player) {
        Box line = Box.createHorizontalBox();
        // Add football emoji to end of name when scored?
        // Red when O.G? Different emoji for assists
        // Number on top when more than one, prevents spilling out of bounds
        JLabel name = new JLabel(player.getName());
        setPermanentWidth(name, 200);
        // For GK
        JLabel saves = new JLabel(player.getSavesThisMatch() + "%");
        setPermanentWidth(saves, 40);

        JLabel passingAccuracy = new JLabel(player.getPassingAccuracyThisMatch() + "%");
        setPermanentWidth(passingAccuracy, 40);
        JLabel shootingAccuracy = new JLabel(player.getShotAccuracyThisMatch() + "%");
        setPermanentWidth(shootingAccuracy, 40);
        JLabel duelsWon = new JLabel(player.getDuelsWonThisMatch() + "%");
        setPermanentWidth(duelsWon, 40);

        JLabel fitness = new JLabel(player.getStamina() + "%");
        setPermanentWidth(fitness, 40);
        JLabel rating = new JLabel(player.getRatingThisMatch() + "%");
        setPermanentWidth(rating, 40);

        line.add(name);
        line.add(duelsWon);
        line.add(passingAccuracy);
        line.add(shootingAccuracy);
        line.add(fitness);
        line.add(rating);
        setPermanentHeight(line, 30);
        centerBox.add(line);

        // Line will need to be a class in order for us to make changes to each section of the line
        playerRatings.put(player.getName(), line);
    }

}
