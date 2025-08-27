package visuals.MainMenuPages.SinglePages;

import visuals.ScheduleFrames.Scheduler;

public class TrainingPage extends SinglePageTemplate {

    public TrainingPage(Scheduler scheduler) {
        super(scheduler);
        getHeaderPanel().setTitle("Training");

        setVisible(true);
    }

    @Override
    public boolean isFromScheduler() {
        return true;
    }
}
