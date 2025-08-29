package visuals.MainMenuPages;

import entities.UsersMatch;
import visuals.CustomizedElements.CardmapMainPageTemplate;
import visuals.CustomizedElements.CustomizedButton;
import visuals.MatchPages.MatchFrames;
import visuals.ScheduleFrames.Events;
import visuals.ScheduleFrames.Scheduler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

public class MainMenuPageTemplate extends CardmapMainPageTemplate {

    private JPanel mainPanel;
    private UsersMatch match;
    private Events event;

    public MainMenuPageTemplate(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages);
        setScheduler(scheduler);
        setFromScheduler(fromScheduler);

        getFooterPanel().addBackButton();

        updateBackButtonFunctionality();
    }

    @Override
    public void updateBackButtonFunctionality(){
        CustomizedButton back = getFooterPanel().getBackButton();
        if (event == null) {
            if (back.getMouseListeners().length == 1) {
                getFooterPanel().addBackButtonListener(back);
            } else {
                MouseListener[] listeners = back.getMouseListeners();
                back.removeMouseListener(listeners[listeners.length - 1]);
                getFooterPanel().addBackButtonListener(back);
            }
        } else {
            if (back.getMouseListeners().length == 1) {
                addPlayFunctionalityToBackButton(back);
            } else {
                MouseListener[] listeners = back.getMouseListeners();
                back.removeMouseListener(listeners[listeners.length - 1]);
                addPlayFunctionalityToBackButton(back);
            }
        }
    }

    public void addPlayFunctionalityToBackButton(CustomizedButton back) {
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Map.Entry<String, JPanel> page : getScheduler().getMatchFramesMap().entrySet()) {
                    MatchFrames frame = (MatchFrames) page.getValue();
                    event.getMatch().setScheduler(getScheduler());
                    frame.setMatch(event.getMatch());
                }
                getScheduler().displayMatchFrames(event.getMatch());
                setEvent(null);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public UsersMatch getMatch() {
        return match;
    }

    public void setMatch(UsersMatch match) {
        this.match = match;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        CustomizedButton back = getFooterPanel().getBackButton();
        if (event != null) {
            this.event = event;
            back.setText("Kick Off");
            updateBackButtonFunctionality();
        } else {
            this.event = event;
            back.setText("Back");
            updateBackButtonFunctionality();
        }
    }
}
