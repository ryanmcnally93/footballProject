package visuals.MatchPages;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import entities.Team;
import people.Footballer;
import visuals.CustomizedElements.CustomizedButton;
import visuals.CustomizedElements.ImageWithText;
import visuals.CustomizedElements.PlayerStatsBoxOnRatingsPage;
import visuals.CustomizedElements.PlayerStatsLineOnRatingsPage;

public class MatchRatings extends MatchFrames {

	private Box centerBox;
    private JPanel mainPanel, titleLine;
    private ArrayList<PlayerStatsLineOnRatingsPage> playerStatsLines;
    private PlayerStatsBoxOnRatingsPage playerStatsBox;
    private int lineInView;
    private InputMap inputMap;
    private ActionMap actionMap;
    private ImageIcon icon;
    private BufferedImage bufferedScaledImage;
    private JLabel title;
    private JButton switchTeamInView;
    private List<String> positionOrder = Arrays.asList(
            "GK", "RB", "CB1", "CB2",
            "LB", "CM1", "CAM", "CM2", "RW", "ST", "LW");
    private List<String> sortedPositions;

	public MatchRatings(CardLayout layout, JPanel pages, Speedometer speedometer, ArrayList<CustomizedButton> buttons) {
		super(layout, pages, speedometer, buttons);

        lineInView = 0;
        playerStatsLines = new ArrayList<>();

		JLayeredPane layeredPane = getLayeredPane();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        centerBox = Box.createVerticalBox();
        title = new JLabel();
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerBox.add(title);
        mainPanel.add(centerBox);

        createTitleLine();

        switchTeamInView = new JButton();
        switchTeamInView.setHorizontalAlignment(SwingConstants.CENTER);
        switchTeamInView.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerBox.add(switchTeamInView);

        Box emptyGap= Box.createVerticalBox();
        setPermanentWidthAndHeight(emptyGap, 20, 440);
        mainPanel.add(emptyGap);

        playerStatsBox = new PlayerStatsBoxOnRatingsPage();
        for (int i = 0; i < 11; i++) {
            PlayerStatsLineOnRatingsPage newLine = new PlayerStatsLineOnRatingsPage();
            playerStatsLines.add(newLine);
            centerBox.add(newLine);
        }

        Box rightBox = Box.createVerticalBox();
        setPermanentWidthAndHeight(rightBox, 250, 405);
        rightBox.add(playerStatsBox);
        mainPanel.add(rightBox);

        mainPanel.setBounds(35, 70, 730, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);

        setupKeyBindings();

        // This is for the football image
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("/Users/admin/Desktop/footballProject/src/visuals/images/ratings_page_goal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(image != null) {
            Image scaledImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            bufferedScaledImage = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedScaledImage.createGraphics();
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();
            icon = new ImageIcon(bufferedScaledImage);
        }

        setVisible(true);
	}

    public void setupKeyBindings() {
        Action upAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lineInView > 0) {
                    lineInView--;
                    updateFocus();
                }
            }
        };

        Action downAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lineInView < playerStatsLines.size() - 1) {
                    lineInView++;
                    updateFocus();
                }
            }
        };

        inputMap = centerBox.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = centerBox.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
        actionMap.put("UP", upAction);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");
        actionMap.put("DOWN", downAction);
    }

    private void createTitleLine() {
        titleLine = new JPanel();
        titleLine.setBackground(Color.LIGHT_GRAY);
        titleLine.setOpaque(true);

        addLabelToTitleLine("POS", 30);
        addLabelToTitleLine("PLAYER NAME", 130);
        addLabelToTitleLine("DUELS", 50);
        addLabelToTitleLine("PASSES", 50);
        addLabelToTitleLine("SHOTS", 50);
        addLabelToTitleLine("FITNESS", 50);
        addLabelToTitleLine("RATING", 50);

        setPermanentHeight(titleLine, 30);
        centerBox.add(titleLine);
    }

    private void addLabelToTitleLine(String text, int width) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Menlo", Font.BOLD, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        setPermanentWidth(label, width);
        titleLine.add(label);
    }

    @Override
    public void populateMatchFramesContentForNewMatch() {
        lineInView = 0;
        super.populateMatchFramesContentForNewMatch();

        Team usersTeam = getTeam("User");
        Team oppositionsTeam = getTeam("Opposition");

        title.setText(usersTeam.getName().toUpperCase());

        setSortedPositions();
        updateLinesAndBox(getPlayers("User"));

        switchTeamInView.setText(oppositionsTeam.getName());

        // Remove the old listener
        if (switchTeamInView.getActionListeners().length > 0) {
            ActionListener[] listeners = switchTeamInView.getActionListeners();
            switchTeamInView.removeActionListener(listeners[listeners.length - 1]);
        }
        // Add new listener
        addNewSwitchTeamInViewListener(usersTeam, oppositionsTeam);

        // Place back at bottom
        centerBox.remove(switchTeamInView);
        centerBox.add(switchTeamInView);

        updateFocus();
    }

    public void setSortedPositions() {
        if (sortedPositions == null) {
            sortedPositions = new ArrayList<>(getMatch().getHomeTeam().keySet());
        }
    }

    public Map<String, Footballer> getPlayers(String team) {
        if (getMatch().getScheduler().getTeam() == getMatch().getHome()) {
            if (team.equals("User")) {
                return getMatch().getHomeTeam();
            } else {
                return getMatch().getAwayTeam();
            }
        } else {
            if (team.equals("User")) {
                return getMatch().getAwayTeam();
            } else {
                return getMatch().getHomeTeam();
            }
        }
    }

    public Team getTeam(String team) {
        if (getMatch().getScheduler().getTeam() == getMatch().getHome()) {
            if (team.equals("User")) {
                return getMatch().getHome();
            } else {
                return getMatch().getAway();
            }
        } else {
            if (team.equals("User")) {
                return getMatch().getAway();
            } else {
                return getMatch().getHome();
            }
        }
    }

    public void updateLinesAndBox(Map<String, Footballer> team) {
        setSortedPositions();
        // Sort keys based on custom order
        sortedPositions.sort(Comparator.comparingInt(positionOrder::indexOf));

        // Creating users lines and boxes
        for (int i = 0; i < sortedPositions.size(); i++) {
            Footballer player = team.get(sortedPositions.get(i));
            updateLine(playerStatsLines.get(i), player);
        }
        updateBox(team.get("GK"));

        // Users Players Mouse Event Listeners
        for (int i = 0; i < playerStatsLines.size(); i++) {
            final int index = i;
            playerStatsLines.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    lineInView = index;
                    for (int j = 0; j < playerStatsLines.size(); j++) {
                        updateFocus(j);
                    }
                }
            });
        }
    }

    public void addNewSwitchTeamInViewListener(Team firstTeam, Team secondTeam) {
        // Create a flag to track the current state
        final boolean[] showingFirstTeam = {true};

        switchTeamInView.addActionListener(e -> {
            if (showingFirstTeam[0]) {
                // Show the second team
                title.setText(secondTeam.getName().toUpperCase());

                for (int i = 0; i < sortedPositions.size(); i++) {
                    Footballer player = secondTeam.getPlayers().get(sortedPositions.get(i));
                    updateLine(playerStatsLines.get(i), player);
                }
                updateBox(secondTeam.getPlayers().get("GK"));

                switchTeamInView.setText(firstTeam.getName()); // Button now toggles back to first team
            } else {
                // Show the first team
                title.setText(firstTeam.getName().toUpperCase());

                for (int i = 0; i < sortedPositions.size(); i++) {
                    Footballer player = firstTeam.getPlayers().get(sortedPositions.get(i));
                    updateLine(playerStatsLines.get(i), player);
                }
                updateBox(firstTeam.getPlayers().get("GK"));

                switchTeamInView.setText(secondTeam.getName()); // Button now toggles back to second team
            }

            // Toggle the flag
            showingFirstTeam[0] = !showingFirstTeam[0];

            // Refresh the UI
            updateFocus(lineInView);
            centerBox.revalidate();
            centerBox.repaint();
        });
    }

    private void updateFocus() {
        for (int i = 0; i < playerStatsLines.size(); i++) {
            updateFocus(i);
        }
    }

    public void updateFocus(int i) {
        PlayerStatsLineOnRatingsPage line = playerStatsLines.get(i);
        if (i == lineInView) {
            line.requestFocusInWindow();
            line.setBackground(Color.YELLOW);
            updateBox(line.getPlayer());
        } else {
            line.setBackground(Color.LIGHT_GRAY);
        }
    }

    public void updateLine(PlayerStatsLineOnRatingsPage line, Footballer player){
        line.setPlayer(player);
        line.getNameLabel().setText(player.getName());
        line.getPosLabel().setText(player.getLikedPosition());
        // This is for the football image
        if(player.getGoalsThisMatch() == 1) {
            line.getNameAsJLabel().setIcon(icon);
            line.getNameAsJLabel().setHorizontalTextPosition(SwingConstants.LEFT);
        } else if (player.getGoalsThisMatch() > 1) {
            ImageWithText multipleGoals = new ImageWithText(bufferedScaledImage, String.valueOf(player.getGoalsThisMatch()), 0.4f);
            line.getNameAsJLabel().setIcon(multipleGoals);
            line.getNameAsJLabel().setHorizontalTextPosition(SwingConstants.LEFT);
        } else {
            line.getNameAsJLabel().setIcon(null);
        }

//        line.setSaves(String.valueOf(player.getSavesThisMatch()));
        line.setFitnessLabel(player.getStamina() + "%");

        player.updateDuelsPercentageThisMatch();
        line.setDuelsWonLabel(player.getDuelsPercentageThisMatch() + "%");

        player.updatePassingAccuracyThisMatch();
        line.setPassingAccuracyLabel(player.getPassingAccuracyThisMatch() + "%");

        player.updateShotAccuracyThisMatch();
        line.setShootingAccuracyLabel(player.getShotAccuracyThisMatch() + "%");

        line.setRatingLabel(String.valueOf(10));
    }

    public void updateLine(Footballer player) {
        for (PlayerStatsLineOnRatingsPage line : playerStatsLines) {
            if (line.getPlayerName().equals(player.getName())) {
                updateLine(line, player);
            }
        }
    }

    public void updateBox(Footballer player){
        playerStatsBox.setName(player.getName());

        playerStatsBox.setShotsOn(String.valueOf(player.getShotsOnTargetThisMatch()));
        playerStatsBox.setShotsOff(String.valueOf(player.getShotsOffTargetThisMatch()));

        playerStatsBox.setDuelsWon(String.valueOf(player.getDuelsWonThisMatch()));
        playerStatsBox.setDuelsLost(String.valueOf(player.getDuelsLostThisMatch()));

        playerStatsBox.setSuccessfulPasses(String.valueOf(player.getSuccessfulPassesThisMatch()));
        playerStatsBox.setFailedPasses(String.valueOf(player.getFailedPassesThisMatch()));

        playerStatsBox.setYellowCard(String.valueOf(player.getYellowCardThisMatch()));
        playerStatsBox.setRedCard(String.valueOf(player.getRedCardThisMatch()));

        playerStatsBox.setGoals(String.valueOf(player.getGoalsThisMatch()));
        playerStatsBox.setAssists(String.valueOf(player.getAssistsThisMatch()));

        playerStatsBox.setOffsides(String.valueOf(player.getOffsidesThisMatch()));
        playerStatsBox.setFouls(String.valueOf(player.getFoulsThisMatch()));
        playerStatsBox.setSubstituted(String.valueOf(player.getSubstitutedThisMatch()));
        playerStatsBox.setInjury(String.valueOf(player.getInjuryTimeThisMatch()));

    }

    @Override
    public String getMatchFrameName() {
        return "Ratings";
    }

}
