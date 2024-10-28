package visuals.MatchFrames;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

import entities.Match;
import entities.UsersMatch;
import people.Footballer;
import visuals.CustomizedElements.PlayerAchievementLine;
import visuals.CustomizedElements.PlayerMatchLine;

public class MatchRatings extends MatchFrames {

	private Box centerBox;
    private JPanel mainPanel;
    private Map<String, PlayerMatchLine> playerRatings;
    private ArrayList<PlayerMatchLine> homePlayers, awayPlayers;

	public MatchRatings(CardLayout layout, JPanel pages, UsersMatch match, Speedometer speedometer) {
		super(layout, pages, match, speedometer);

        homePlayers = new ArrayList<>();
        awayPlayers = new ArrayList<>();
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
        JPanel line = new JPanel();
        JLabel name = new JLabel("PLAYER NAME");
        setPermanentWidth(name, 200);
        JLabel saves = new JLabel("SAVES");
        setPermanentWidth(saves, 60);
        JLabel passingAccuracy = new JLabel("PASSING");
        setPermanentWidth(passingAccuracy, 60);
        JLabel shootingAccuracy = new JLabel("SHOOTING");
        setPermanentWidth(shootingAccuracy, 60);
        JLabel duelsWon = new JLabel("DUELS");
        setPermanentWidth(duelsWon, 60);
        JLabel fitness = new JLabel("FITNESS");
        setPermanentWidth(fitness, 60);
        JLabel rating = new JLabel("RATING");
        setPermanentWidth(rating, 60);
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
            PlayerMatchLine newLine = createRatingLine(eachPlayer, "Home");
            centerBox.add(newLine);
        }

        for(Map.Entry<String, Footballer> each : getMatch().getAwayTeam().entrySet()){
            Footballer eachPlayer = each.getValue();
            createRatingLine(eachPlayer, "Away");
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
            for(PlayerMatchLine eachLine : awayPlayers){
                centerBox.add(eachLine);
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
                for(PlayerMatchLine eachLine : homePlayers){
                    centerBox.add(eachLine);
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

    private PlayerMatchLine createRatingLine(Footballer player, String homeOrAway) {
        PlayerMatchLine newLine = new PlayerMatchLine(player);

        // Line will need to be a class in order for us to make changes to each section of the line
        playerRatings.put(player.getName(), newLine);

        if(homeOrAway.equals("Home")){
            homePlayers.add(newLine);
        } else {
            awayPlayers.add(newLine);
        }
        return newLine;
    }

    public void updateLine(Footballer player){
        PlayerMatchLine line = playerRatings.get(player.getName());

//        line.setSaves(String.valueOf(player.getSavesThisMatch()));
        line.setFitness(player.getStamina() + "%");

        player.updateDuelsPercentageThisMatch();
        line.setDuelsWon(player.getDuelsPercentageThisMatch() + "%");

        player.updatePassingAccuracyThisMatch();
        line.setPassingAccuracy(player.getPassingAccuracyThisMatch() + "%");

        player.updateShotAccuracyThisMatch();
        line.setShootingAccuracy(player.getShotAccuracyThisMatch() + "%");

        line.setRating(String.valueOf(10));
    }

}
