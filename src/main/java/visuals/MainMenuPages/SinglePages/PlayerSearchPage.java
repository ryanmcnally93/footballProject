package visuals.MainMenuPages.SinglePages;

import visuals.ScheduleFrames.Scheduler;

public class PlayerSearchPage extends LeftContentRightScrollPagesTemplate {

    public PlayerSearchPage(Scheduler scheduler) {
        super(scheduler);
        getHeaderPanel().setTitle("Player Search");

        setVisible(true);
    }

    @Override
    public boolean isFromScheduler() {
        return true;
    }
}
