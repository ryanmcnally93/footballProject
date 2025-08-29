package visuals.MainMenuPages.SinglePages;

import people.Footballer;
import visuals.CustomizedElements.PlayerMenuBar;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class TrainingPage extends SinglePageTemplate {

    public TrainingPage(Scheduler scheduler) {
        super(scheduler);
        getHeaderPanel().setTitle("Training");

        getRightBox().setLayout(new BoxLayout(getRightBox(), BoxLayout.Y_AXIS));
        getRightBox().setBorder(new EmptyBorder(5, 5, 5, 5));

        for (Map.Entry<String, Footballer> eachPlayer : scheduler.getTeam().getPlayers().entrySet()) {
            PlayerMenuBar playerMenuBar = new PlayerMenuBar(eachPlayer.getValue());
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

        setVisible(true);
    }

    @Override
    public boolean isFromScheduler() {
        return true;
    }
}
