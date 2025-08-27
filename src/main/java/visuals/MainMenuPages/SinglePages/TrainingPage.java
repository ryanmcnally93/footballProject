package visuals.MainMenuPages.SinglePages;

import people.Footballer;
import visuals.CustomizedElements.PlayerMenuBar;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class TrainingPage extends SinglePageTemplate {

    public TrainingPage(Scheduler scheduler) {
        super(scheduler);
        getHeaderPanel().setTitle("Training");

        getRightBox().setLayout(new BoxLayout(getRightBox(), BoxLayout.Y_AXIS));

        for (Map.Entry<String, Footballer> eachPlayer : scheduler.getTeam().getPlayers().entrySet()) {
            PlayerMenuBar playerMenuBar = new PlayerMenuBar(eachPlayer.getValue());
            getRightBox().add(playerMenuBar);
        }

        setVisible(true);
    }

    @Override
    public boolean isFromScheduler() {
        return true;
    }
}
