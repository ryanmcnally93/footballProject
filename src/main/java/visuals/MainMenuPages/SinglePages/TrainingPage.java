package visuals.MainMenuPages.SinglePages;

import people.Footballer;
import visuals.CustomizedElements.PlayerAttributeBox;
import visuals.CustomizedElements.PlayerMenuBar;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class TrainingPage extends SinglePageTemplate {

    private Scheduler scheduler;
    private Footballer selectedPlayer;

    public TrainingPage(Scheduler scheduler) {
        super(scheduler);
        this.scheduler = scheduler;
        getHeaderPanel().setTitle("Training");

        setupPlayerListOnRight();
        PlayerMenuBar firstPlayerBar = (PlayerMenuBar) getRightBox().getComponent(0);
        firstPlayerBar.setAsSelected();
        selectedPlayer = firstPlayerBar.getPlayer();
        setupMiddleContent();

        setVisible(true);
    }

    private void setupMiddleContent() {
        getLeftBox().setLayout(new BoxLayout(getLeftBox(), BoxLayout.Y_AXIS));
        // Grab players position first, this needs to be in attributes instead
        String position = selectedPlayer.getPositionPlaced();
        if (position.equals("RB") || position.equals("CB") || position.equals("LB")) {
            addDefenderAttributes();
        }
    }

    private void addDefenderAttributes() {
        PlayerAttributeBox playerAttribute = new PlayerAttributeBox("Standing Tackle", selectedPlayer.getStandingTackle());
        getLeftBox().add(playerAttribute);
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
