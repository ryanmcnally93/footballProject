package visuals.MainMenuPages.TacticsPages;
import people.Footballer;
import visuals.CustomizedElements.PlayerStatsLineOnRatingsPage;
import visuals.MainMenuPages.MainMenuPageTemplate;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FirstTeamPage extends MainMenuPageTemplate {

    private JPanel mainPanel;
    private Box centerBox;
    private JPanel titleLine;
    private ArrayList<PlayerStatsLineOnRatingsPage> listOfLines;
    private PlayerStatsLineOnRatingsPage firstClickedLine = null;
    private PlayerStatsLineOnRatingsPage secondClickedLine = null;

    public FirstTeamPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages, scheduler, fromScheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("First Team");
        listOfLines = new ArrayList<>();

        JLayeredPane layeredPane = getLayeredPane();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        centerBox = Box.createVerticalBox();
        mainPanel.add(centerBox);

        // Create title line
        createTitleLine();

        chooseLinesToView(scheduler.getTeam().getFormation().getPositionOrder());

        mainPanel.setBounds(35, 70, 730, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);

        setVisible(true);
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

    public void chooseLinesToView(List<String> sortedPositions) {
        // Remove any previous lines
        for (Component comp : centerBox.getComponents()) {
            if (comp instanceof PlayerStatsLineOnRatingsPage) {
                centerBox.remove(comp);
            }
        }

        if (getMatch() == null) {
            for (String position : sortedPositions) {
                Footballer player = getScheduler().getTeam().getFirstTeam().get(position);
                PlayerStatsLineOnRatingsPage newLine = createRatingLine(player);
                centerBox.add(newLine);
            }
        } else if (getMatch().getHome() == getScheduler().getTeam()) {
            for (String position : sortedPositions) {
                Footballer player = getMatch().getHomeTeam().get(position);
                PlayerStatsLineOnRatingsPage newLine = createRatingLine(player);
                centerBox.add(newLine);
            }
        } else if (getMatch().getAway() == getScheduler().getTeam()) {
            for (String position : sortedPositions) {
                Footballer player = getMatch().getAwayTeam().get(position);
                PlayerStatsLineOnRatingsPage newLine = createRatingLine(player);
                centerBox.add(newLine);
            }
        }
    }

    private void handleLineClick(PlayerStatsLineOnRatingsPage clickedLine) {
        if (firstClickedLine == null) {
            // First click: highlight the line
            firstClickedLine = clickedLine;
            firstClickedLine.setBackground(Color.YELLOW); // Highlight color
            firstClickedLine.setOpaque(true);
            firstClickedLine.repaint();
        } else if (secondClickedLine == null && clickedLine != firstClickedLine) {
            // Second click: set the second line and start the animation
            secondClickedLine = clickedLine;
            swapPlayerPositionsVisually(firstClickedLine, secondClickedLine);
            swapPlayerPositionsLogically(firstClickedLine, secondClickedLine);
        }
    }

    public void swapPlayerPositionsLogically(PlayerStatsLineOnRatingsPage line1, PlayerStatsLineOnRatingsPage line2) {
        // If we are outside a match, this should effect our first team
        if (isFromScheduler()) {
            line1.getPlayer().setPositionPlaced(line2.getPosLabel().getText());
            getScheduler().getTeam().getFirstTeam().put(line2.getPosLabel().getText(), line1.getPlayer());
            line2.getPlayer().setPositionPlaced(line1.getPosLabel().getText());
            getScheduler().getTeam().getFirstTeam().put(line1.getPosLabel().getText(), line2.getPlayer());
        } else {
            // Need to update the match's teams rather than the actual first team
            // UPDATE VISUALLY!
            if (getScheduler().getRatingsPanel().getMatch().getHomeTeam() == getScheduler().getTeam().getFirstTeam()) {
                line1.getPlayer().setPositionPlaced(line2.getPosLabel().getText());
                getScheduler().getRatingsPanel().getMatch().getHomeTeam().put(line2.getPosLabel().getText(), line1.getPlayer());
                line2.getPlayer().setPositionPlaced(line1.getPosLabel().getText());
                getScheduler().getRatingsPanel().getMatch().getHomeTeam().put(line1.getPosLabel().getText(), line2.getPlayer());
            } else {
                line1.getPlayer().setPositionPlaced(line2.getPosLabel().getText());
                getScheduler().getRatingsPanel().getMatch().getAwayTeam().put(line2.getPosLabel().getText(), line1.getPlayer());
                line2.getPlayer().setPositionPlaced(line1.getPosLabel().getText());
                getScheduler().getRatingsPanel().getMatch().getAwayTeam().put(line1.getPosLabel().getText(), line2.getPlayer());
            }
        }

        // Updates the position label on our line now that it has moved
        String firstLinePosition = line1.getPosLabel().getText();
        line1.getPosLabel().setText(line2.getPosLabel().getText());
        line2.getPosLabel().setText(firstLinePosition);
    }

    public void swapPlayerPositionsVisually(PlayerStatsLineOnRatingsPage line1, PlayerStatsLineOnRatingsPage line2) {
        Point start1 = line1.getLocation();
        Point start2 = line2.getLocation();

        int totalSteps = 50; // Number of animation steps
        int delay = 10;      // Delay between steps (ms)

        Timer timer = new Timer(delay, new ActionListener() {
            int step = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (step > totalSteps) {
                    ((Timer) e.getSource()).stop();

                    // Update the order in the container
                    int index1 = centerBox.getComponentZOrder(line1);
                    int index2 = centerBox.getComponentZOrder(line2);


                    // Swap positions in the container
                    centerBox.remove(line1);
                    centerBox.remove(line2);

                    if (index1 < index2) {
                        centerBox.add(line2, index1);
                        centerBox.add(line1, index2);
                    } else {
                        centerBox.add(line1, index2);
                        centerBox.add(line2, index1);
                    }

                    centerBox.revalidate();
                    centerBox.repaint();

                    // Reset the state
                    firstClickedLine.setBackground(Color.LIGHT_GRAY);
                    firstClickedLine = null;
                    secondClickedLine = null;
                    return;
                }

                // Interpolate positions
                double t = (double) step / totalSteps;
                int newX1 = (int) (start1.x + t * (start2.x - start1.x));
                int newY1 = (int) (start1.y + t * (start2.y - start1.y));
                int newX2 = (int) (start2.x + t * (start1.x - start2.x));
                int newY2 = (int) (start2.y + t * (start1.y - start2.y));

                line1.setLocation(newX1, newY1);
                line2.setLocation(newX2, newY2);

                centerBox.repaint();
                step++;
            }
        });

        timer.start();
    }

    // Could this be in adult class? repeated code
    public PlayerStatsLineOnRatingsPage createRatingLine(Footballer player) {
        PlayerStatsLineOnRatingsPage newLine = new PlayerStatsLineOnRatingsPage();
        newLine.updateLine(player);
        listOfLines.add(newLine);
        newLine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleLineClick(newLine);
            }
        });
        return newLine;
    }

}
