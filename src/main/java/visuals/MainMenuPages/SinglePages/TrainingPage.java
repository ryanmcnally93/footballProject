package visuals.MainMenuPages.SinglePages;

import people.Footballer;
import visuals.CustomizedElements.CustomizedButton;
import visuals.CustomizedElements.PlayerAttributeBox;
import visuals.CustomizedElements.PlayerAttributeLine;
import visuals.CustomizedElements.PlayerMenuBar;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Arrays;
import java.util.Map;

public class TrainingPage extends SinglePageTemplate {

    private Scheduler scheduler;
    private Footballer selectedPlayer;
    private PlayerAttributeLine firstLine, secondLine, thirdLine, fourthLine;
    private Timer downTimer, upTimer;

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

        addScrollDownButton();
        addScrollUpButton();

        addKeyListeners();
        setVisible(true);
    }

    private void addScrollDownButton() {
        ImageIcon buttonIcon = new ImageIcon("./src/main/java/visuals/Images/down_arrow.png", "DownSmall");
        CustomizedButton scrollDownButton = new CustomizedButton(buttonIcon);
        scrollDownButton.setBounds(0, 0, 155, 20);
        scrollDownButton.setVisible(false);

        JPanel hoverArea = new JPanel(null);
        hoverArea.setOpaque(false);
        hoverArea.setBounds(633, 413, 155, 20);
        hoverArea.add(scrollDownButton);
        getLayeredPane().add(hoverArea, JLayeredPane.PALETTE_LAYER);

        hoverArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JScrollBar vBar = getScroller().getVerticalScrollBar();
                int max = vBar.getMaximum() - vBar.getVisibleAmount();
                int value = vBar.getValue();
                boolean atBottom = value >= max;

                scrollDownButton.setVisible(!atBottom);
            }
        });

        scrollDownButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                scrollDownButton.setVisible(false);
                if (downTimer != null && downTimer.isRunning()) {
                    downTimer.stop();
                }
            }
        });

        scrollDownButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JScrollBar vBar = getScroller().getVerticalScrollBar();
                downTimer = new Timer(20, evt -> {
                    int newVal = Math.min(vBar.getValue() + 2, vBar.getMaximum());
                    vBar.setValue(newVal);

                    int max = vBar.getMaximum() - vBar.getVisibleAmount();
                    boolean atBottom = vBar.getValue() >= max;

                    if (atBottom) {
                        scrollDownButton.setVisible(false);
                        downTimer.stop();
                    }
                });

                downTimer.start();
            }
        });
    }

    private void addScrollUpButton() {
        ImageIcon buttonIcon = new ImageIcon("./src/main/java/visuals/Images/up_arrow.png", "UpSmall");
        CustomizedButton scrollUpButton = new CustomizedButton(buttonIcon);
        scrollUpButton.setBounds(0, 0, 155, 20);
        scrollUpButton.setVisible(false);

        JPanel hoverArea = new JPanel(null);
        hoverArea.setOpaque(false);
        hoverArea.setBounds(633, 168, 155, 20);
        hoverArea.add(scrollUpButton);
        getLayeredPane().add(hoverArea, JLayeredPane.PALETTE_LAYER);

        hoverArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JScrollBar vBar = getScroller().getVerticalScrollBar();
                int value = vBar.getValue();
                boolean atTop = value == 0;

                scrollUpButton.setVisible(!atTop);
            }
        });

        scrollUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                scrollUpButton.setVisible(false);
                if (upTimer != null && upTimer.isRunning()) {
                    upTimer.stop();
                }
            }
        });

        scrollUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JScrollBar vBar = getScroller().getVerticalScrollBar();
                upTimer = new Timer(20, evt -> {
                    int newVal = Math.min(vBar.getValue() - 2, vBar.getMaximum());
                    vBar.setValue(newVal);

                    boolean atTop = vBar.getValue() == 0;

                    if (atTop) {
                        scrollUpButton.setVisible(false);
                        upTimer.stop();
                    }
                });

                upTimer.start();
            }
        });
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
    }

    @Override
    protected AbstractAction getDownClickAction() {
        return new TrainingPage.CustomDownClick();
    }

    public class CustomDownClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            move("down");
        }
    }

    @Override
    protected AbstractAction getUpClickAction() {
        return new TrainingPage.CustomUpClick();
    }

    public class CustomUpClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            move("up");
        }
    }

    private void move(String direction) {
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
        if (position.equals("RB") || position.equals("CB1") || position.equals("CB2") || position.equals("LB")) {
            addDefenderAttributes();
        } else if (position.equals("CM1") || position.equals("CM2") || position.equals("CAM") || position.equals("LM") || position.equals("RM") || position.equals("CDM")) {
            addMidfielderAttributes();
        } else if (position.equals("RW") || position.equals("ST") || position.equals("LW")) {
            addAttackerAttributes();
        } else if (position.equals("GK")) {
            addGoalkeeperAttributes();
        } else {
            throw new IllegalArgumentException("Invalid position: " + position);
        }
        getLeftBox().revalidate();
        getLeftBox().repaint();
    }

    private void addGoalkeeperAttributes() {
        firstLine.changeContent(selectedPlayer.getGkAttributes(), "Goalkeeper");
        secondLine.changeContent(selectedPlayer.getPassingAttributes(), "Passing");
        thirdLine.changeContent(selectedPlayer.getGeneralAttributes(), "General");
        fourthLine.changeContent(selectedPlayer.getLastAttributes(), String.valueOf(selectedPlayer.getOVR()));
    }

    private void addAttackerAttributes() {
        firstLine.changeContent(selectedPlayer.getAttackingAttributes(), "Attack");
        ((PlayerAttributeBox) firstLine.getComponent(3)).addTripleIncrease();
        secondLine.changeContent(selectedPlayer.getMovementAttributes(), "Movement");
        thirdLine.changeContent(selectedPlayer.getGeneralAttributes(), "General");
        fourthLine.changeContent(selectedPlayer.getLastAttributes(), String.valueOf(selectedPlayer.getOVR()));
    }

    private void addMidfielderAttributes() {
        firstLine.changeContent(selectedPlayer.getMovementAttributes(), "Movement");
        secondLine.changeContent(selectedPlayer.getPassingAttributes(), "Passing");
        thirdLine.changeContent(selectedPlayer.getGeneralAttributes(), "General");
        fourthLine.changeContent(selectedPlayer.getLastAttributes(), String.valueOf(selectedPlayer.getOVR()));
    }

    private void addDefenderAttributes() {
        firstLine.changeContent(selectedPlayer.getDefendingAttributes(), "Defence");
        secondLine.changeContent(selectedPlayer.getPassingAttributes(), "Passing");
        thirdLine.changeContent(selectedPlayer.getGeneralAttributes(), "General");
        fourthLine.changeContent(selectedPlayer.getLastAttributes(), String.valueOf(selectedPlayer.getOVR()));
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
        move(String.valueOf(Arrays.asList(getRightBox().getComponents()).indexOf(playerMenuBar)));
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
