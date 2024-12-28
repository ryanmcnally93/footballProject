package visuals.MatchPages;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private Map<String, PlayerStatsLineOnRatingsPage> playerRatings;
    private Map<String, PlayerStatsBoxOnRatingsPage> playerBoxes;
    private ArrayList<PlayerStatsLineOnRatingsPage> homePlayersLines, awayPlayersLines, teamInViewLines;
    private ArrayList<PlayerStatsBoxOnRatingsPage> homePlayersBoxes, awayPlayersBoxes, teamInViewBoxes;
    private int lineInView;
    private InputMap inputMap;
    private ActionMap actionMap;
    private Box rightBox;
    private ImageIcon icon;
    private BufferedImage bufferedScaledImage;
    private JLabel title;
    private JButton switchTeamInView;

	public MatchRatings(CardLayout layout, JPanel pages, Speedometer speedometer, ArrayList<CustomizedButton> buttons) {
		super(layout, pages, speedometer, buttons);

        lineInView = 0;

        homePlayersLines = new ArrayList<>();
        awayPlayersLines = new ArrayList<>();
        homePlayersBoxes = new ArrayList<>();
        awayPlayersBoxes = new ArrayList<>();
        playerRatings = new HashMap<>();
        playerBoxes = new HashMap<>();

		JLayeredPane layeredPane = getLayeredPane();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
        centerBox = Box.createVerticalBox();
        title = new JLabel();
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerBox.add(title);
        mainPanel.add(centerBox);

        // Create title line
        titleLine = new JPanel();
        titleLine.setBackground(Color.LIGHT_GRAY);
        titleLine.setOpaque(true);

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

        titleLine.add(pos);
        titleLine.add(name);
        titleLine.add(duelsWon);
        titleLine.add(passingAccuracy);
        titleLine.add(shootingAccuracy);
        titleLine.add(fitness);
        titleLine.add(rating);
        setPermanentHeight(titleLine, 30);
        centerBox.add(titleLine);

        switchTeamInView = new JButton();
        switchTeamInView.setHorizontalAlignment(SwingConstants.CENTER);
        switchTeamInView.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerBox.add(switchTeamInView);

        Box emptyGap= Box.createVerticalBox();
        setPermanentWidthAndHeight(emptyGap, 20, 440);
        mainPanel.add(emptyGap);

        rightBox = Box.createVerticalBox();
        setPermanentWidthAndHeight(rightBox, 250, 405);

        mainPanel.add(rightBox);

        mainPanel.setBounds(35, 70, 730, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);

        Action upAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lineInView > 0) {
                    lineInView--;
                    updateFocus("UP", teamInViewLines, teamInViewBoxes);
                }
            }
        };

        Action downAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lineInView < teamInViewLines.size() - 1) {
                    lineInView++;
                    updateFocus("DOWN", teamInViewLines, teamInViewBoxes);
                }
            }
        };

        inputMap = centerBox.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = centerBox.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
        actionMap.put("UP", upAction);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");
        actionMap.put("DOWN", downAction);

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

    @Override
    public void addContentForChildClass() {
        List<String> positionOrder = Arrays.asList(
                "GK", "RB", "CB1", "CB2",
                "LB", "CM1", "CAM", "CM2", "RW", "ST", "LW");

        // Sort keys based on custom order
        List<String> sortedPositions = new ArrayList<>(getMatch().getHomeTeam().keySet());
        sortedPositions.sort(Comparator.comparingInt(positionOrder::indexOf));

        Map<String, Footballer> usersPlayers;
        Map<String, Footballer> oppositionsPlayers;
        Team usersTeam;
        Team oppositionsTeam;

        if (getMatch().getScheduler().getTeam() == getMatch().getHome()) {
            usersPlayers = getMatch().getHomeTeam();
            oppositionsPlayers = getMatch().getAwayTeam();
            usersTeam = getMatch().getHome();
            oppositionsTeam = getMatch().getAway();
        } else {
            usersPlayers = getMatch().getAwayTeam();
            oppositionsPlayers = getMatch().getHomeTeam();
            usersTeam = getMatch().getAway();
            oppositionsTeam = getMatch().getHome();
        }

        title.setText(usersTeam.getName().toUpperCase());

        // Execute commands in sorted order
        for (String position : sortedPositions) {
            Footballer player = usersPlayers.get(position);
            PlayerStatsLineOnRatingsPage newLine = createRatingLine(player, homePlayersLines);
            centerBox.add(newLine);
        }

        // Home Players Mouse Event Listeners
        teamInViewLines = homePlayersLines;
        teamInViewBoxes = homePlayersBoxes;
        for (int i = 0; i < homePlayersLines.size(); i++) {
            final int index = i;
            homePlayersLines.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    updateFocus(homePlayersLines, homePlayersLines.get(index), homePlayersBoxes.get(index));
                }
            });
        }

        // Let's create the away players lines, to be added later
        for (String position : sortedPositions) {
            Footballer player = oppositionsPlayers.get(position);
            createRatingLine(player, awayPlayersLines);
        }

        // Away Players Mouse Event Listeners
        for(int i = 0; i < awayPlayersLines.size(); i++){
            final int index = i;
            awayPlayersLines.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    updateFocus(awayPlayersLines, awayPlayersLines.get(index), awayPlayersBoxes.get(index));
                }
            });
        }

        switchTeamInView.setText(oppositionsTeam.getName());
        switchTeamInView.addActionListener(e -> {
            centerBox.removeAll();
            teamInViewLines = awayPlayersLines;
            teamInViewBoxes = awayPlayersBoxes;
            title.setText(oppositionsTeam.getName().toUpperCase());
            centerBox.add(title);
            centerBox.add(titleLine);
            for(PlayerStatsLineOnRatingsPage eachLine : awayPlayersLines){
                centerBox.add(eachLine);
            }
            String firstTeamsName = usersTeam.getName();
            JButton switchTeamBack = new JButton(firstTeamsName);
            switchTeamBack.setHorizontalAlignment(SwingConstants.CENTER);
            switchTeamBack.setAlignmentX(Component.CENTER_ALIGNMENT);
            centerBox.add(switchTeamBack);
            setPlayerLineFocus(awayPlayersLines, lineInView, awayPlayersBoxes.get(lineInView));

            centerBox.revalidate();
            centerBox.repaint();

            switchTeamBack.addActionListener(f -> {
                centerBox.removeAll();
                teamInViewLines = homePlayersLines;
                teamInViewBoxes = homePlayersBoxes;
                title.setText(usersTeam.getName().toUpperCase());
                centerBox.add(title);
                centerBox.add(titleLine);
                for(PlayerStatsLineOnRatingsPage eachLine : homePlayersLines){
                    centerBox.add(eachLine);
                }
                centerBox.add(switchTeamInView);
                setPlayerLineFocus(homePlayersLines, lineInView, homePlayersBoxes.get(lineInView));

                centerBox.revalidate();
                centerBox.repaint();
            });

        });

        // Place back at bottom
        centerBox.remove(switchTeamInView);
        centerBox.add(switchTeamInView);

        PlayerStatsBoxOnRatingsPage playerBox = new PlayerStatsBoxOnRatingsPage(usersPlayers.get("GK"));
        rightBox.add(playerBox);

        // Create Boxes for each Team
        for (String position : sortedPositions) {
            Footballer player = usersPlayers.get(position);
            createRatingBox(player, homePlayersBoxes);
            rightBox.add(homePlayersBoxes.getFirst());
        }

        for (String position : sortedPositions) {
            Footballer player = oppositionsPlayers.get(position);
            createRatingBox(player, awayPlayersBoxes);
        }

        setPlayerLineFocus(homePlayersLines, 0, homePlayersBoxes.get(lineInView));
    }

    @Override
    public void removeContentForChildClass() {
        centerBox.removeAll();
        playerRatings.clear();
        playerBoxes.clear();
    }

    private void setPlayerLineFocus(ArrayList<PlayerStatsLineOnRatingsPage> players, int index, PlayerStatsBoxOnRatingsPage box) {
        for(PlayerStatsLineOnRatingsPage eachLine : players){
            if(eachLine == players.get(index)){
                eachLine.setVisible(true);
                eachLine.requestFocusInWindow();
                eachLine.setBackground(Color.YELLOW);
            } else {
                eachLine.setBackground(Color.LIGHT_GRAY);
            }
        }

        rightBox.removeAll();
        rightBox.add(box);
        rightBox.revalidate();
        rightBox.repaint();
    }

    private void updateFocus(String direction, ArrayList<PlayerStatsLineOnRatingsPage> players, ArrayList<PlayerStatsBoxOnRatingsPage> boxes) {
        if(direction.equals("UP")) {
            PlayerStatsLineOnRatingsPage current = players.get(lineInView + 1);
            current.setBackground(Color.LIGHT_GRAY);
        } else if (direction.equals("DOWN")) {
            PlayerStatsLineOnRatingsPage current = players.get(lineInView - 1);
            current.setBackground(Color.LIGHT_GRAY);
        }
        PlayerStatsLineOnRatingsPage current = players.get(lineInView);
        current.setBackground(Color.YELLOW);
        rightBox.removeAll();
        rightBox.add(boxes.get(lineInView));
        rightBox.revalidate();
        rightBox.repaint();
    }

    // Stats need labels

    private void updateFocus(ArrayList<PlayerStatsLineOnRatingsPage> players, PlayerStatsLineOnRatingsPage thisLine, PlayerStatsBoxOnRatingsPage thisBox) {
        for(int i=0;i <players.size(); i++){
            if(players.get(i) != thisLine){
                players.get(i).setBackground(Color.LIGHT_GRAY);
            } else {
                lineInView = i;
            }
        }
        thisLine.setBackground(Color.YELLOW);
        rightBox.removeAll();
        rightBox.add(thisBox);
        rightBox.revalidate();
        rightBox.repaint();
    }

    public PlayerStatsLineOnRatingsPage createRatingLine(Footballer player, ArrayList<PlayerStatsLineOnRatingsPage> players) {
        PlayerStatsLineOnRatingsPage newLine = new PlayerStatsLineOnRatingsPage(player);
        playerRatings.put(player.getName(), newLine);
        players.add(newLine);
        return newLine;
    }

    public void createRatingBox(Footballer player, ArrayList<PlayerStatsBoxOnRatingsPage> players){
        PlayerStatsBoxOnRatingsPage newBox = new PlayerStatsBoxOnRatingsPage(player);
        playerBoxes.put(player.getName(), newBox);
        players.add(newBox);
    }

    public void updateLine(Footballer player){
        PlayerStatsLineOnRatingsPage line = playerRatings.get(player.getName());

        // This is for the football image
        if(player.getGoalsThisMatch() == 1) {
            line.setName(player.getName());
            line.getNameAsJLabel().setIcon(icon);
            line.getNameAsJLabel().setHorizontalTextPosition(SwingConstants.LEFT);
        } else if (player.getGoalsThisMatch() > 1) {
            ImageWithText multipleGoals = new ImageWithText(bufferedScaledImage, String.valueOf(player.getGoalsThisMatch()), 0.4f);
            line.setName(player.getName());
            line.getNameAsJLabel().setIcon(multipleGoals);
            line.getNameAsJLabel().setHorizontalTextPosition(SwingConstants.LEFT);
        }

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

    public void updateBox(Footballer player){
        PlayerStatsBoxOnRatingsPage box = playerBoxes.get(player.getName());

//        line.setSaves(String.valueOf(player.getSavesThisMatch()));
        box.setShotsOn(String.valueOf(player.getShotsOnTargetThisMatch()));
        box.setShotsOff(String.valueOf(player.getShotsOffTargetThisMatch()));

        box.setDuelsWon(String.valueOf(player.getDuelsWonThisMatch()));
        box.setDuelsLost(String.valueOf(player.getDuelsLostThisMatch()));

        box.setSuccessfulPasses(String.valueOf(player.getSuccessfulPassesThisMatch()));
        box.setFailedPasses(String.valueOf(player.getFailedPassesThisMatch()));

        box.setYellowCard(String.valueOf(player.getYellowCardThisMatch()));
        box.setRedCard(String.valueOf(player.getRedCardThisMatch()));

        box.setGoals(String.valueOf(player.getGoalsThisMatch()));
        box.setAssists(String.valueOf(player.getAssistsThisMatch()));

        box.setOffsides(String.valueOf(player.getOffsidesThisMatch()));
        box.setFouls(String.valueOf(player.getFoulsThisMatch()));
        box.setSubstituted(String.valueOf(player.getSubstitutedThisMatch()));
        box.setInjury(String.valueOf(player.getInjuryTimeThisMatch()));

    }

}
