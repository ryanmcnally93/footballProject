package visuals.MainMenuPages.SinglePages;

import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;

public class PlayerSearchPage extends LeftContentRightScrollPagesTemplate {

    public PlayerSearchPage(Scheduler scheduler) {
        super(scheduler);
        getHeaderPanel().setTitle("Player Search");

        ImageIcon buttonIcon = getIconWithSpecificSize("./src/main/java/visuals/Images/player_search_icon.png", "PlayerSearch", 16);
        getHeaderPanel().getPageIcon().setIcon(buttonIcon);
        
        setVisible(true);
    }

    @Override
    public boolean isFromScheduler() {
        return true;
    }
}
