package visuals.MatchFrames;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entities.UsersMatch;
import people.Footballer;
import visuals.CustomizedElements.PlayerMatchLine;

public class MatchRatings extends MatchFrames {

	private Box centerBox, rightBox;
    private JPanel mainPanel;
    private Map<String, PlayerMatchLine> playerRatings;
    private ArrayList<PlayerMatchLine> homePlayers, awayPlayers, teamInView;
    private int lineInView;
    private InputMap inputMap;
    private ActionMap actionMap;

	public MatchRatings(CardLayout layout, JPanel pages, UsersMatch match, Speedometer speedometer) {
		super(layout, pages, match, speedometer);

        lineInView = 0;

        homePlayers = new ArrayList<>();
        awayPlayers = new ArrayList<>();
        playerRatings = new HashMap<>();

		JLayeredPane layeredPane = getLayeredPane();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
        centerBox = Box.createVerticalBox();
        JLabel title = new JLabel(getMatch().getHome().getName().toUpperCase());
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerBox.add(title);
        mainPanel.add(centerBox);

        // Create title line
        JPanel line = new JPanel();
        line.setBackground(Color.LIGHT_GRAY);
        line.setOpaque(true);

        JLabel pos = new JLabel("POS");
        pos.setFont(new Font("Menlo", Font.BOLD, 12));
        pos.setHorizontalAlignment(SwingConstants.CENTER);
        setPermanentWidth(pos, 30);

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
            PlayerMatchLine newLine = createRatingLine(player, homePlayers);
            centerBox.add(newLine);
        }

        teamInView = homePlayers;

        for (String position : sortedPositions) {
            Footballer player = getMatch().getAwayTeam().get(position);
            createRatingLine(player, awayPlayers);
        }

        String otherTeamsName = getMatch().getAway().getName();
        JButton switchTeamInView = new JButton(otherTeamsName);
        switchTeamInView.setHorizontalAlignment(SwingConstants.CENTER);
        switchTeamInView.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerBox.add(switchTeamInView);

        switchTeamInView.addActionListener(e -> {
            centerBox.removeAll();
            teamInView = awayPlayers;
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
            setPlayerLineFocus(awayPlayers, lineInView);

            centerBox.revalidate();
            centerBox.repaint();

            switchTeamBack.addActionListener(f -> {
                centerBox.removeAll();
                teamInView = homePlayers;
                title.setText(getMatch().getHome().getName().toUpperCase());
                centerBox.add(title);
                centerBox.add(line);
                for(PlayerMatchLine eachLine : homePlayers){
                    centerBox.add(eachLine);
                }
                centerBox.add(switchTeamInView);
                setPlayerLineFocus(homePlayers, lineInView);

                centerBox.revalidate();
                centerBox.repaint();
            });

        });

        Box emptyGap= Box.createVerticalBox();
        setPermanentWidthAndHeight(emptyGap, 20, 440);
        mainPanel.add(emptyGap);

        rightBox = Box.createVerticalBox();
        setPermanentWidthAndHeight(rightBox, 250, 440);
        rightBox.setBackground(Color.RED);
        rightBox.setOpaque(true);
        mainPanel.add(rightBox);

        setPlayerLineFocus(homePlayers, 0);

        mainPanel.setBounds(35, 80, 730, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);

        Action upAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lineInView > 0) {
                    lineInView--;
                    updateFocus("UP", teamInView);
                }
            }
        };

        Action downAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lineInView < teamInView.size() - 1) {
                    lineInView++;
                    updateFocus("DOWN", teamInView);
                }
            }
        };

        inputMap = centerBox.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = centerBox.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
        actionMap.put("UP", upAction);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");
        actionMap.put("DOWN", downAction);

        setVisible(true);
		
	}

    private void setPlayerLineFocus(ArrayList<PlayerMatchLine> players, int index) {
        for(PlayerMatchLine eachLine : players){
            if(eachLine == players.get(index)){
                eachLine.setVisible(true);
                eachLine.requestFocusInWindow();
                eachLine.setBackground(Color.YELLOW);
            } else {
                eachLine.setBackground(Color.LIGHT_GRAY);
            }
        }
    }

    private void updateFocus(String direction, ArrayList<PlayerMatchLine> players) {
        if(direction.equals("UP")) {
            PlayerMatchLine current = players.get(lineInView + 1);
            current.setBackground(Color.LIGHT_GRAY);
        } else if (direction.equals("DOWN")) {
            PlayerMatchLine current = players.get(lineInView - 1);
            current.setBackground(Color.LIGHT_GRAY);
        }
        PlayerMatchLine current = players.get(lineInView);
        current.setBackground(Color.YELLOW);
    }

    private PlayerMatchLine createRatingLine(Footballer player, ArrayList<PlayerMatchLine> players) {
        PlayerMatchLine newLine = new PlayerMatchLine(player);
        playerRatings.put(player.getName(), newLine);
        players.add(newLine);
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
