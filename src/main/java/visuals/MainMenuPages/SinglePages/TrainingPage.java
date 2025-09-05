package visuals.MainMenuPages.SinglePages;

import people.Footballer;
import visuals.CustomizedElements.*;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class TrainingPage extends SinglePageTemplate {

    private Scheduler scheduler;
    private Footballer selectedPlayer;
    private PlayerAttributeLine firstLine, secondLine, thirdLine, fourthLine;
    private Timer timer;
    private String activePage = "left";
    private PanelOfCircles circles;
    private final Map<String, Rectangle> boundsByDirection = Map.of(
            "down", new Rectangle(0, 0, 155, 20),
            "up",   new Rectangle(0, 0, 155, 20),
            "left", new Rectangle(0, 0, 50, 50),
            "right", new Rectangle(0, 0, 50, 50),
            "downHover", new Rectangle(633, 413, 155, 20),
            "upHover",   new Rectangle(633, 168, 155, 20),
            "leftHover", new Rectangle(5, 272, 50, 50),
            "rightHover", new Rectangle(565, 272, 50, 50)
    );

    public TrainingPage(Scheduler scheduler) {
        super(scheduler);
        this.scheduler = scheduler;
        getHeaderPanel().setTitle("Training");

        setupPlayerListOnRight();
        PlayerMenuBar firstPlayerBar = (PlayerMenuBar) getRightBox().getComponent(0);
        firstPlayerBar.setAsSelected(true);
        selectedPlayer = firstPlayerBar.getPlayer();

        setupAttributesOnLeft();
        populatePlayerAttributes();

        addScrollButton("down");
        addScrollButton("up");
        addScrollButton("left");
        addScrollButton("right");

        addKeyListeners();
        setVisible(true);
    }

    private void addScrollButton(String direction) {
        CustomizedButton button;
        if (direction.equals("down") || direction.equals("up")) {
            ImageIcon buttonIcon = new ImageIcon("./src/main/java/visuals/Images/" + direction + "_arrow.png", direction.substring(0, 1).toUpperCase() + direction.substring(1) + "Small");
            button = new CustomizedButton(buttonIcon);
        } else if (direction.equals("left")) {
            button = new CustomizedButton("<");
        } else if (direction.equals("right")) {
            button = new CustomizedButton(">");
        } else {
            throw new IllegalArgumentException("Invalid direction");
        }

        button.setBounds(boundsByDirection.get(direction));
        button.setVisible(false);

        JPanel hoverArea = new JPanel(null);
        hoverArea.setOpaque(false);
        hoverArea.setBounds(boundsByDirection.get(direction + "Hover"));
        hoverArea.add(button);
        getLayeredPane().add(hoverArea, JLayeredPane.PALETTE_LAYER);

        hoverArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!direction.equals(activePage)) {
                    JScrollBar vBar = getScroller().getVerticalScrollBar();
                    int max = vBar.getMaximum() - vBar.getVisibleAmount();
                    int value = vBar.getValue();
                    boolean edgeOfPosition = false;
                    if (direction.equals("down")) {
                        edgeOfPosition = value >= max;
                    } else if (direction.equals("up")) {
                        edgeOfPosition = value == 0;
                    }

                    button.setVisible(!edgeOfPosition);
                }
            }
        });

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                button.setVisible(false);
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
            }
        });

        if (direction.equals("down") || direction.equals("up")) {
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    JScrollBar vBar = getScroller().getVerticalScrollBar();
                    timer = new Timer(20, evt -> {
                        int newVal = 0;
                        if (direction.equals("down")) {
                            newVal = Math.min(vBar.getValue() + 2, vBar.getMaximum());
                        } else {
                            newVal = Math.min(vBar.getValue() - 2, vBar.getMaximum());
                        }
                        vBar.setValue(newVal);

                        int max = vBar.getMaximum() - vBar.getVisibleAmount();
                        boolean edgeOfPosition = false;
                        if (direction.equals("down")) {
                            edgeOfPosition = vBar.getValue() >= max;
                        } else {
                            edgeOfPosition = vBar.getValue() == 0;
                        }

                        if (edgeOfPosition) {
                            button.setVisible(false);
                            timer.stop();
                        }
                    });

                    timer.start();
                }
            });
        } else {
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    changeStatBars(direction);
                    button.setVisible(false);
                }
            });
        }
    }

    private void setupAttributesOnLeft() {
        getLeftBox().setLayout(new BoxLayout(getLeftBox(), BoxLayout.X_AXIS));
        firstLine = new PlayerAttributeLine();
        secondLine = new PlayerAttributeLine();
        thirdLine = new PlayerAttributeLine();
        fourthLine = new PlayerAttributeLine(true);

        getLeftBox().add(Box.createHorizontalStrut(50));
        getLeftBox().add(firstLine);
        getLeftBox().add(Box.createHorizontalStrut(20));
        getLeftBox().add(secondLine);
        getLeftBox().add(Box.createHorizontalStrut(20));
        getLeftBox().add(thirdLine);
        getLeftBox().add(Box.createHorizontalStrut(20));
        getLeftBox().add(fourthLine);
        getLeftBox().add(Box.createHorizontalStrut(50));

        Box bottomHorizontalBox = Box.createHorizontalBox();
        bottomHorizontalBox.setBounds(0, 419, 621, 51);

        Box leftBox = Box.createVerticalBox();
        setPermanentWidth(leftBox, 70);
        leftBox.setAlignmentY(Component.TOP_ALIGNMENT);

        CustomizedButton custom = new CustomizedButton("Custom", 16);
        custom.setAlignmentY(Component.TOP_ALIGNMENT);

        Box rightBox = Box.createVerticalBox();
        rightBox.setAlignmentY(Component.TOP_ALIGNMENT);
        setPermanentWidthAndHeight(rightBox, 70, 51);

        circles = new PanelOfCircles(2);
        circles.changeCircleColor(activePage);
        rightBox.add(Box.createVerticalStrut(10));
        rightBox.add(circles);

        bottomHorizontalBox.add(leftBox);
        bottomHorizontalBox.add(Box.createHorizontalGlue());
        bottomHorizontalBox.add(custom);
        bottomHorizontalBox.add(Box.createHorizontalGlue());
        bottomHorizontalBox.add(rightBox);

        getLayeredPane().add(bottomHorizontalBox, JLayeredPane.PALETTE_LAYER);
    }

    @Override
    protected AbstractAction getDownClickAction() {
        return new TrainingPage.CustomDownClick();
    }

    public class CustomDownClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            moveScroller("down");
        }
    }

    @Override
    protected AbstractAction getUpClickAction() {
        return new TrainingPage.CustomUpClick();
    }

    public class CustomUpClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            moveScroller("up");
        }
    }

    @Override
    protected AbstractAction getLeftClickAction() {
        return new TrainingPage.CustomLeftClick();
    }

    public class CustomLeftClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            changeStatBars("left");
        }
    }

    @Override
    protected AbstractAction getRightClickAction() {
        return new TrainingPage.CustomRightClick();
    }

    public class CustomRightClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            changeStatBars("right");
        }
    }

    private void changeStatBars(String direction) {
        if (!activePage.equals(direction)) {
            activePage = direction;
            populatePlayerAttributes();
            circles.changeCircleColor(activePage);
        }
    }

    private void moveScroller(String direction) {
        Container rightBox = getRightBox();
        int count = rightBox.getComponentCount();

        for (int i = 0; i < count; i++) {
            Component comp = rightBox.getComponent(i);

            if (comp instanceof PlayerMenuBar playerBar && playerBar.isSelected()) {
                // Deselect current
                playerBar.setAsSelected(false);
                playerBar.revalidate();
                playerBar.repaint();

                int nextIndex;
                if (direction.equals("down")) {
                    nextIndex = (i + 2 < count) ? i + 2 : i; // stay at end if no next
                } else if (direction.equals("up")) {
                    nextIndex = (i - 2 >= 0) ? i - 2 : i;
                } else {
                    nextIndex = Integer.parseInt(direction);
                }

                PlayerMenuBar nextBar = (PlayerMenuBar) rightBox.getComponent(nextIndex);
                nextBar.setAsSelected(true);
                scrollToButton(nextBar);
                nextBar.revalidate();
                nextBar.repaint();

                // Update reference
                selectedPlayer = nextBar.getPlayer();
                populatePlayerAttributes();
                break;
            }
        }
    }

    public void scrollToButton(JButton button) {
        Rectangle bounds = button.getBounds();
        Point location = SwingUtilities.convertPoint(button.getParent(), bounds.getLocation(), getScroller().getViewport());
        bounds.setLocation(location);

        getScroller().getViewport().scrollRectToVisible(bounds);
    }

    private void populatePlayerAttributes() {
        String position = selectedPlayer.getPositionPlaced();
        switch (position) {
            case "RB", "CB1", "CB2", "LB" -> addDefenderAttributes();
            case "CM1", "CM2", "CAM", "LM", "RM", "CDM" -> addMidfielderAttributes();
            case "RW", "ST", "LW" -> addAttackerAttributes();
            case "GK" -> addGoalkeeperAttributes();
            default -> throw new IllegalArgumentException("Invalid position: " + position);
        }
        getLeftBox().revalidate();
        getLeftBox().repaint();
    }

    private void addGoalkeeperAttributes() {
        if (activePage.equals("left")) {
            firstLine.changeContent(selectedPlayer.getGkAttributes(), "Goalkeeping");
            secondLine.changeContent(selectedPlayer.getPassingAttributes(), "Passing");
            thirdLine.changeContent(selectedPlayer.getGeneralAttributes(), "General");
            fourthLine.changeContent(selectedPlayer.getLastAttributes(), String.valueOf(selectedPlayer.getOVR()));
        } else {
            firstLine.changeContent(selectedPlayer.getMovementAttributes(), "Movement");
            secondLine.changeContent(selectedPlayer.getDefendingAttributes(), "Defence");
            thirdLine.changeContent(selectedPlayer.getAttackingAttributes(), "Attack");
            fourthLine.changeContent(selectedPlayer.getSetPieceAttributes(), String.valueOf(selectedPlayer.getOVR()));
        }
    }

    private void addAttackerAttributes() {
        if (activePage.equals("left")) {
            firstLine.changeContent(selectedPlayer.getAttackingAttributes(), "Attack");
            secondLine.changeContent(selectedPlayer.getMovementAttributes(), "Movement");
            thirdLine.changeContent(selectedPlayer.getGeneralAttributes(), "General");
            fourthLine.changeContent(selectedPlayer.getSetPieceAttributes(), String.valueOf(selectedPlayer.getOVR()));
        } else {
            firstLine.changeContent(selectedPlayer.getPassingAttributes(), "Passing");
            secondLine.changeContent(selectedPlayer.getDefendingAttributes(), "Defence");
            thirdLine.changeContent(selectedPlayer.getGkAttributes(), "Goalkeeping");
            fourthLine.changeContent(selectedPlayer.getLastAttributes(), String.valueOf(selectedPlayer.getOVR()));
        }
    }

    private void addMidfielderAttributes() {
        if (activePage.equals("left")) {
            firstLine.changeContent(selectedPlayer.getMovementAttributes(), "Movement");
            secondLine.changeContent(selectedPlayer.getPassingAttributes(), "Passing");
            thirdLine.changeContent(selectedPlayer.getGeneralAttributes(), "General");
            fourthLine.changeContent(selectedPlayer.getSetPieceAttributes(), String.valueOf(selectedPlayer.getOVR()));
        } else {
            firstLine.changeContent(selectedPlayer.getDefendingAttributes(), "Defence");
            secondLine.changeContent(selectedPlayer.getAttackingAttributes(), "Attack");
            thirdLine.changeContent(selectedPlayer.getGkAttributes(), "Goalkeeping");
            fourthLine.changeContent(selectedPlayer.getLastAttributes(), String.valueOf(selectedPlayer.getOVR()));
        }
    }

    private void addDefenderAttributes() {
        if (activePage.equals("left")) {
            firstLine.changeContent(selectedPlayer.getDefendingAttributes(), "Defence");
            secondLine.changeContent(selectedPlayer.getPassingAttributes(), "Passing");
            thirdLine.changeContent(selectedPlayer.getGeneralAttributes(), "General");
            fourthLine.changeContent(selectedPlayer.getLastAttributes(), String.valueOf(selectedPlayer.getOVR()));
        } else {
            firstLine.changeContent(selectedPlayer.getMovementAttributes(), "Movement");
            secondLine.changeContent(selectedPlayer.getAttackingAttributes(), "Attack");
            thirdLine.changeContent(selectedPlayer.getGkAttributes(), "Goalkeeping");
            fourthLine.changeContent(selectedPlayer.getSetPieceAttributes(), String.valueOf(selectedPlayer.getOVR()));
        }
    }

    private void setupPlayerListOnRight() {
        getRightBox().setLayout(new BoxLayout(getRightBox(), BoxLayout.Y_AXIS));
        getRightBox().setBorder(new EmptyBorder(5, 5, 5, 5));

        for (Map.Entry<String, Footballer> eachPlayer : scheduler.getTeam().getPlayers().entrySet()) {
            Footballer player = eachPlayer.getValue();
            String title = player.getName().charAt(0) + " " + player.getName().substring(player.getName().lastIndexOf(' ') + 1);
            PlayerMenuBar playerMenuBar = new PlayerMenuBar(player, title);
            playerMenuBar.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerMenuBar.setFocusable(false);
            playerMenuBar.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    getButtonClickedAction(playerMenuBar);
                }
            });
            getRightBox().add(playerMenuBar);
            getRightBox().add(Box.createVerticalStrut(5));
        }

        // Remove that last 5px
        int count = getRightBox().getComponentCount();
        if (count > 0) {
            getRightBox().remove(count - 1);
            getRightBox().revalidate();
            getRightBox().repaint();
        }
    }

    private void getButtonClickedAction(PlayerMenuBar playerMenuBar) {
        // Supply the retrieved index of this bar to 'move'
        moveScroller(String.valueOf(Arrays.asList(getRightBox().getComponents()).indexOf(playerMenuBar)));
    }

    @Override
    public Scheduler getScheduler() {
        return scheduler;
    }

    @Override
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
