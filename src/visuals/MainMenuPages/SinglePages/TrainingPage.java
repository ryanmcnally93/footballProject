package visuals.MainMenuPages.SinglePages;

import visuals.CustomizedElements.MainPageTemplate;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class TrainingPage extends MainPageTemplate {

    private JPanel mainPanel;
    private Box centerBox;
    private JScrollPane scroller;

    public TrainingPage(Scheduler scheduler) {
        super(scheduler);
        getHeaderPanel().setTitle("Training");

        JLayeredPane layeredPane = getLayeredPane();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        centerBox = Box.createVerticalBox();
        mainPanel.add(centerBox);

        scroller = makeScroller(centerBox);
        mainPanel.add(scroller);

        mainPanel.setBounds(35, 90, 730, 420);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);

        getFooterPanel().addBackButton();
        updateBackButtonFunctionality();

        setVisible(true);
    }

    @Override
    public boolean isFromScheduler() {
        return true;
    }
}
