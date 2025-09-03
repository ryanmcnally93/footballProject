package visuals.MainMenuPages.SinglePages;

import people.Footballer;
import visuals.CustomizedElements.PlayerAttributeLine;
import visuals.CustomizedElements.PlayerMenuBar;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class TrainingPage extends SinglePageTemplate {

    private Scheduler scheduler;
    private Footballer selectedPlayer;
    private PlayerAttributeLine firstLine, secondLine, thirdLine, fourthLine;

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

        addKeyListeners();
        setVisible(true);
    }

    private void setupAttributesOnLeft() {
        getLeftBox().setLayout(new BoxLayout(getLeftBox(), BoxLayout.X_AXIS));
        firstLine = new PlayerAttributeLine();
        secondLine = new PlayerAttributeLine();
        thirdLine = new PlayerAttributeLine();
        fourthLine = new PlayerAttributeLine();

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
                    throw new IllegalArgumentException("Invalid direction");
                }

                PlayerMenuBar nextBar = (PlayerMenuBar) rightBox.getComponent(nextIndex);
                nextBar.setAsSelected(true);
                nextBar.revalidate();
                nextBar.repaint();

                // Update reference
                selectedPlayer = nextBar.getPlayer();
                populatePlayerAttributes();
                break;
            }
        }
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
        firstLine.changeContent(selectedPlayer.getGkAttributes());
    }

    private void addAttackerAttributes() {
        firstLine.changeContent(selectedPlayer.getMovementAttributes());
    }

    private void addMidfielderAttributes() {
        firstLine.changeContent(selectedPlayer.getMovementAttributes());
    }

    private void addDefenderAttributes() {
        firstLine.changeContent(selectedPlayer.getMovementAttributes());
    }

    private void setupPlayerListOnRight() {
        getRightBox().setLayout(new BoxLayout(getRightBox(), BoxLayout.Y_AXIS));
        getRightBox().setBorder(new EmptyBorder(5, 5, 5, 5));

        for (Map.Entry<String, Footballer> eachPlayer : scheduler.getTeam().getPlayers().entrySet()) {
            Footballer player = eachPlayer.getValue();
            String title = player.getName().charAt(0) + " " + player.getName().substring(player.getName().lastIndexOf(' ') + 1);
            PlayerMenuBar playerMenuBar = new PlayerMenuBar(player, title);
            playerMenuBar.setAlignmentX(Component.LEFT_ALIGNMENT);
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

    @Override
    public Scheduler getScheduler() {
        return scheduler;
    }

    @Override
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
