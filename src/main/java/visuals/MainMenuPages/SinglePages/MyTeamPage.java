package visuals.MainMenuPages.SinglePages;

import visuals.ScheduleFrames.Scheduler;

public class MyTeamPage extends LeftContentRightScrollPagesTemplate {

    public MyTeamPage(Scheduler scheduler) {
        super(scheduler, false);
        getHeaderPanel().setTitle("My Team");

        setVisible(true);
    }

    @Override
    public boolean isFromScheduler() {
        return true;
    }
}
