package visuals.MatchPages;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
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
    private JLabel title;
    private JButton switchTeamInView;

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

    public void createTitleLine() {
        titleLine = new JPanel();
        titleLine.setBackground(Color.LIGHT_GRAY);
        titleLine.setOpaque(true);

        addLabelToTitleLine("POS", 30, titleLine);
        addLabelToTitleLine("PLAYER NAME", 130, titleLine);
        addLabelToTitleLine("DUELS", 50, titleLine);
        addLabelToTitleLine("PASSES", 50, titleLine);
        addLabelToTitleLine("SHOTS", 50, titleLine);
        addLabelToTitleLine("FITNESS", 50, titleLine);
        addLabelToTitleLine("RATING", 50, titleLine);

        setPermanentHeight(titleLine, 30);
        centerBox.add(titleLine);
    }

    @Override
    public void populateMatchFramesContentForNewMatch() {
        lineInView = 0;
        super.populateMatchFramesContentForNewMatch();

        Team usersTeam = getTeam("User");
        Team oppositionsTeam = getTeam("Opposition");

        title.setText(usersTeam.getName().toUpperCase());

        updateLinesAndBox(usersTeam.getFirstTeam(), usersTeam);

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

    public void updateLinesAndBox(Map<String, Footballer> players, Team team) {
        if (getMatch().isMatchHasPlayed()) {
            for (int i = 0; i < team.getFormation().getPositionOrder().size(); i++) {
                Footballer player = players.get(team.getFormation().getPositionOrder().get(i));
                playerStatsLines.get(i).updateLineWhenMatchFinished(player, players, getMatch());
            }
        } else {
            // Creating users lines and boxes
            for (int i = 0; i < team.getFormation().getPositionOrder().size(); i++) {
                Footballer player = players.get(team.getFormation().getPositionOrder().get(i));
                playerStatsLines.get(i).updateLine(player);
            }
            updateBox(players.get("GK"));
        }

        // Users Players Mouse Event Listeners
        for (int i = 0; i < playerStatsLines.size(); i++) {
            final int index = i;
            // Remove last mouse listener before adding a new one
            if (playerStatsLines.get(i).getMouseListeners().length == 1) {
                MouseListener[] listeners = playerStatsLines.get(i).getMouseListeners();
                playerStatsLines.get(i).removeMouseListener(listeners[listeners.length - 1]);
            }
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

        boolean away;
        away = firstTeam == getMatch().getAway();

        switchTeamInView.addActionListener(e -> {
            if (showingFirstTeam[0]) {
                // Show the second team
                title.setText(secondTeam.getName().toUpperCase());
                if (away) {
                    updateLinesAndBox(getMatch().getHomeTeam(), secondTeam);
                } else {
                    updateLinesAndBox(getMatch().getAwayTeam(), secondTeam);
                }
                switchTeamInView.setText(firstTeam.getName()); // Button now toggles back to first team
            } else {
                // Show the first team
                title.setText(firstTeam.getName().toUpperCase());
                if (away) {
                    updateLinesAndBox(getMatch().getAwayTeam(), firstTeam);
                } else {
                    updateLinesAndBox(getMatch().getHomeTeam(), firstTeam);
                }
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

    public void updateLine(Footballer player) {
        for (PlayerStatsLineOnRatingsPage line : playerStatsLines) {
            if (line.getPlayerName().equals(player.getName())) {
                line.updateLine(player);
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

    public void refreshLines() {
        if (playerStatsLines.getFirst().getPlayer() == getMatch().getHomegk()) {
            updateLinesAndBox(getMatch().getHomeTeam(), getMatch().getHome());
        } else {
            updateLinesAndBox(getMatch().getAwayTeam(), getMatch().getAway());
        }
    }
}
