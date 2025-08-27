package visuals.MainMenuPages.SinglePages;

import visuals.ScheduleFrames.Scheduler;

public class MyTeamPage extends SinglePageTemplate {

    public MyTeamPage(Scheduler scheduler) {
        super(scheduler);
        getHeaderPanel().setTitle("My Team");

        setVisible(true);
    }

    @Override
    public boolean isFromScheduler() {
        return true;
    }
}
