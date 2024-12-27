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
        // This is repeated code from Match Ratings, could it be in adult class?
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

        createPlayerBoxes();

        mainPanel.setBounds(35, 70, 730, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);

        setVisible(true);
    }

    public void createPlayerBoxes() {
        java.util.List<String> positionOrder = Arrays.asList(
                "GK", "RB", "CB1", "CB2",
                "LB", "CM1", "CAM", "CM2", "RW", "ST", "LW");

        // Sort keys based on custom order
        List<String> sortedPositions = new ArrayList<>(getScheduler().getTeam().getFirstTeam().keySet());
        sortedPositions.sort(Comparator.comparingInt(positionOrder::indexOf));

        chooseLinesToView(sortedPositions);

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
        String firstLinePosition = line1.getPos().getText();
        line1.getPos().setText(line2.getPos().getText());
        line2.getPos().setText(firstLinePosition);

        // Need to update MatchRatings page
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
        PlayerStatsLineOnRatingsPage newLine = new PlayerStatsLineOnRatingsPage(player);
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
