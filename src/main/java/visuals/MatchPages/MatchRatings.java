package visuals.MatchPages;

import entities.Team;
import people.Footballer;
import visuals.CustomizedElements.CustomizedButton;
import visuals.CustomizedElements.PlayerStatsBoxOnRatingsPage;
import visuals.CustomizedElements.MatchRatingsStatLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Map;

public class MatchRatings extends MatchPageTemplate {

    private Box centerBox;
    private JPanel mainPanel, titleLine;
    private ArrayList<MatchRatingsStatLine> playerStatsLines;
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

        Box emptyGap = Box.createVerticalBox();
        setPermanentWidthAndHeight(emptyGap, 20, 440);
        mainPanel.add(emptyGap);

        playerStatsBox = new PlayerStatsBoxOnRatingsPage();
        for (int i = 0; i < 11; i++) {
            MatchRatingsStatLine newLine = new MatchRatingsStatLine(false);
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
                    turnClickedLineYellow();
                }
            }
        };

        Action downAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lineInView < playerStatsLines.size() - 1) {
                    lineInView++;
                    turnClickedLineYellow();
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

        addLabelToTitleLine("", 18, titleLine);
        addLabelToTitleLine("POS", 30, titleLine);
        addLabelToTitleLine("PLAYER NAME", 127, titleLine);
        addLabelToTitleLine("DUELS", 50, titleLine);
        addLabelToTitleLine("PASSES", 50, titleLine);
        addLabelToTitleLine("SHOTS", 50, titleLine);
        addLabelToTitleLine("FITNESS", 50, titleLine);
        addLabelToTitleLine("RAT.", 35, titleLine);

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

        removeOldListenerAndAddNew(usersTeam, oppositionsTeam);

        centerBox.remove(switchTeamInView);
        centerBox.add(switchTeamInView);

        turnClickedLineYellow();
        centerBox.revalidate();
        centerBox.repaint();
    }

    private void removeOldListenerAndAddNew(Team usersTeam, Team oppositionsTeam) {
        for (ActionListener listener : switchTeamInView.getActionListeners()) {
            switchTeamInView.removeActionListener(listener);
        }

        addNewSwitchTeamInViewListener(usersTeam, oppositionsTeam);
    }

    public Team getTeam(String team) {
        if (getMatch().getScheduler().getTeam() == getMatch().getHome()) {
            return team.equals("User") ? getMatch().getHome() : getMatch().getAway();
        } else {
            return team.equals("User") ? getMatch().getAway() : getMatch().getHome();
        }
    }

    public void updateLinesAndBox(Map<String, Footballer> players, Team team) {
        if (getMatch().isMatchHasPlayed()) {
            for (int i = 0; i < team.getFormation().getPositionOrder().size(); i++) {
                Footballer player = players.get(team.getFormation().getPositionOrder().get(i));
                playerStatsLines.get(i).updateLineWhenMatchFinished(player, players, getMatch());
            }
        } else {
            for (int i = 0; i < team.getFormation().getPositionOrder().size(); i++) {
                Footballer player = players.get(team.getFormation().getPositionOrder().get(i));
                playerStatsLines.get(i).updateLine(player);
            }
            updateBox(players.get("GK"));
        }

        for (int i = 0; i < playerStatsLines.size(); i++) {
            final int index = i;
            if (playerStatsLines.get(i).getMouseListeners().length == 1) {
                MouseListener[] listeners = playerStatsLines.get(i).getMouseListeners();
                playerStatsLines.get(i).removeMouseListener(listeners[listeners.length - 1]);
            }
            playerStatsLines.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    lineInView = index;
                    turnClickedLineYellow();
                }
            });
        }
    }

    public void addNewSwitchTeamInViewListener(Team firstTeam, Team secondTeam) {
        final boolean[] showingFirstTeam = {true};
        boolean away = firstTeam == getMatch().getAway();

        switchTeamInView.addActionListener(e -> {
            String newTitle = showingFirstTeam[0] ? secondTeam.getName() : firstTeam.getName();
            title.setText(newTitle.toUpperCase());

            updateLinesAndBox(
                    away ? (showingFirstTeam[0] ? getMatch().getHomeTeam() : getMatch().getAwayTeam()) : (showingFirstTeam[0] ? getMatch().getAwayTeam() : getMatch().getHomeTeam()),
                    showingFirstTeam[0] ? secondTeam : firstTeam
            );

            switchTeamInView.setText(showingFirstTeam[0] ? firstTeam.getName() : secondTeam.getName());

            showingFirstTeam[0] = !showingFirstTeam[0];

            turnClickedLineYellow(lineInView);
            centerBox.revalidate();
            centerBox.repaint();
        });
    }

    private void turnClickedLineYellow() {
        playerStatsLines.forEach(line -> turnClickedLineYellow(playerStatsLines.indexOf(line)));
    }

    public void turnClickedLineYellow(int i) {
        MatchRatingsStatLine line = playerStatsLines.get(i);
        line.setBackground(i == lineInView ? Color.YELLOW : Color.LIGHT_GRAY);
        if (i == lineInView) {
            line.requestFocusInWindow();
            updateBox(line.getPlayer());
        }
    }

    public void updateLineAfterMatchEvent(Footballer player) {
        playerStatsLines.stream()
                .filter(line -> line.getPlayerName().equals(player.getName()))
                .forEach(line -> line.updateLine(player));
    }

    public void updateBox(Footballer player) {
        playerStatsBox.setPlayerStats(player);
    }

    @Override
    public String getMatchFrameName() {
        return "Ratings";
    }

    public void updateLinesAfterTacticsChange() {
        if (playerStatsLines.get(0).getPlayer() == getMatch().getHomegk()) {
            updateLinesAndBox(getMatch().getHomeTeam(), getMatch().getHome());
        } else {
            updateLinesAndBox(getMatch().getAwayTeam(), getMatch().getAway());
        }
    }
}