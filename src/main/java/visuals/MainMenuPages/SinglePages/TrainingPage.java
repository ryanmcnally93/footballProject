package visuals.MainMenuPages.SinglePages;

import people.Footballer;
import visuals.CustomizedElements.*;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TrainingPage extends LeftContentRightScrollPagesTemplate {

    private Footballer selectedPlayer;
    private PlayerAttributeLine firstLine, secondLine, thirdLine, fourthLine;
    private String activePage = "left";
    private PanelOfCircles circles;

    public TrainingPage(Scheduler scheduler) {
        super(scheduler);
        getHeaderPanel().setTitle("Training");

        ImageIcon buttonIcon = getIconWithSpecificSize("./src/main/java/visuals/Images/training_icon.png", "Training", 16);
        getHeaderPanel().getPageIcon().setIcon(buttonIcon);

        setupPlayerListOnRight(
                scheduler.getTeam().getPlayers(),
                (player, i) -> player.getName().charAt(0) + " " +
                        player.getName().substring(player.getName().lastIndexOf(' ') + 1),
                PlayerMenuBar::new
        );

        selectedPlayer = ((PlayerMenuBar) getRightBox().getComponent(0)).getPlayer();
        setupAttributesOnLeft();
        populatePlayerAttributes();

        addScrollButton("down");
        addScrollButton("up");
        addScrollButton("left");
        addScrollButton("right");

        addKeyListeners();
        setVisible(true);
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
            moveRightScroller("down");
        }
    }

    @Override
    protected AbstractAction getUpClickAction() {
        return new TrainingPage.CustomUpClick();
    }

    public class CustomUpClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            moveRightScroller("up");
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

    @Override
    protected void rightBarSelected(RightBoxBar nextBar) {
        selectedPlayer = ((PlayerMenuBar) nextBar).getPlayer();
        populatePlayerAttributes();
    }

    @Override
    protected boolean directionEqualsPage(String direction) {
        return direction.equals(activePage);
    }

    @Override
    protected void moveLeftOrRight(String direction) {
        changeStatBars(direction);
    }
}
