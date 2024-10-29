package visuals.MatchFrames;
import java.awt.*;
import java.util.List;
import java.util.*;
import javax.swing.*;

import entities.Match;
import entities.UsersMatch;
import people.Footballer;
import visuals.CustomizedElements.PlayerAchievementLine;
import visuals.CustomizedElements.PlayerMatchLine;

public class MatchRatings extends MatchFrames {

	private Box centerBox, rightBox;
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
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
//        mainPanel.setLayout(new BorderLayout());

//        appendEastAndWest(mainPanel);
		
        centerBox = Box.createVerticalBox();
        JLabel title = new JLabel(getMatch().getHome().getName().toUpperCase());
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerBox.add(title);
        mainPanel.add(centerBox);

        rightBox = Box.createVerticalBox();
        setPermanentWidthAndHeight(rightBox, 240, 440);
        rightBox.setBackground(Color.RED);
        rightBox.setOpaque(true);
        mainPanel.add(rightBox);

        // Create title line
        JPanel line = new JPanel();
        line.setBackground(Color.LIGHT_GRAY);
        line.setOpaque(true);

        JLabel pos = new JLabel("POS");
        pos.setFont(new Font("Menlo", Font.BOLD, 12));
        pos.setHorizontalAlignment(SwingConstants.CENTER);
        setPermanentWidth(pos, 40);

        JLabel name = new JLabel("PLAYER NAME");
        name.setFont(new Font("Menlo", Font.BOLD, 12));
        setPermanentWidth(name, 130);

        JLabel saves = new JLabel("SAVES");
        saves.setFont(new Font("Menlo", Font.BOLD, 12));
        saves.setHorizontalAlignment(SwingConstants.CENTER);
        setPermanentWidth(saves, 50);

        JLabel passingAccuracy = new JLabel("PASSES");
        passingAccuracy.setFont(new Font("Menlo", Font.BOLD, 12));
        passingAccuracy.setHorizontalAlignment(SwingConstants.CENTER);
        setPermanentWidth(passingAccuracy, 50);

        JLabel shootingAccuracy = new JLabel("SHOTS");
        shootingAccuracy.setFont(new Font("Menlo", Font.BOLD, 12));
        shootingAccuracy.setHorizontalAlignment(SwingConstants.CENTER);
        setPermanentWidth(shootingAccuracy, 50);

        JLabel duelsWon = new JLabel("DUELS");
        duelsWon.setFont(new Font("Menlo", Font.BOLD, 12));
        duelsWon.setHorizontalAlignment(SwingConstants.CENTER);
        setPermanentWidth(duelsWon, 50);

        JLabel fitness = new JLabel("FITNESS");
        fitness.setFont(new Font("Menlo", Font.BOLD, 12));
        fitness.setHorizontalAlignment(SwingConstants.CENTER);
        setPermanentWidth(fitness, 50);

        JLabel rating = new JLabel("RATING");
        rating.setFont(new Font("Menlo", Font.BOLD, 12));
        rating.setHorizontalAlignment(SwingConstants.CENTER);
        setPermanentWidth(rating, 50);

        line.add(pos);
        line.add(name);
        line.add(duelsWon);
        line.add(passingAccuracy);
        line.add(shootingAccuracy);
        line.add(fitness);
        line.add(rating);
        setPermanentHeight(line, 30);
        centerBox.add(line);

        List<String> positionOrder = Arrays.asList(
                "GK", "RB", "CB1", "CB2",
                "LB", "CM1", "CAM", "CM2", "RW", "ST", "LW");

        // Sort keys based on custom order
        List<String> sortedPositions = new ArrayList<>(getMatch().getHomeTeam().keySet());
        sortedPositions.sort(Comparator.comparingInt(positionOrder::indexOf));

        // Execute commands in sorted order
        for (String position : sortedPositions) {
            Footballer player = getMatch().getHomeTeam().get(position);
            PlayerMatchLine newLine = createRatingLine(player, "Home");
            centerBox.add(newLine);
        }

        for (String position : sortedPositions) {
            Footballer player = getMatch().getAwayTeam().get(position);
            createRatingLine(player, "Away");
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

        rightBox = Box.createVerticalBox();
        setPermanentWidthAndHeight(rightBox, 260, 440);
        rightBox.setBackground(Color.RED);
        rightBox.setOpaque(true);
        mainPanel.add(rightBox);
        
        mainPanel.setBounds(35, 80, 730, 440);
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
