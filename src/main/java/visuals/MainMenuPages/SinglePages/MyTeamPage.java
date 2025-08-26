package visuals.MainMenuPages.SinglePages;

import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class MyTeamPage extends SinglePageTemplate {

    public MyTeamPage(Scheduler scheduler) {
        super(scheduler);
        getHeaderPanel().setTitle(scheduler.getTeam().getName());

        setVisible(true);
    }
}
