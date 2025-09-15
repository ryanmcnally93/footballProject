package visuals.MainMenuPages;

import entities.UsersMatch;
import visuals.CustomizedElements.CustomizedButton;
import visuals.CustomizedElements.HeaderFooterAndCardMapTemplate;
import visuals.MatchPages.MatchPageTemplate;
import visuals.ScheduleFrames.Events;
import visuals.ScheduleFrames.Scheduler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Map;

public class TacticsPageTemplate extends HeaderFooterAndCardMapTemplate {

    private JPanel mainPanel;
    private UsersMatch match;
    private Events event;
    private MouseAdapter playButtonListener;

    public TacticsPageTemplate(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
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
            if (Arrays.stream(back.getMouseListeners()).anyMatch(listener -> listener == playButtonListener)) {
                removePlayFunctionalityToBackButton(back);
            }
            getFooterPanel().addBackButtonListener(back);
        } else {
            if (Arrays.stream(back.getMouseListeners()).anyMatch(listener -> listener == getFooterPanel().getBackButtonListener())) {
                getFooterPanel().removeBackButtonListener(back);
            }
            addPlayFunctionalityToBackButton(back);
        }
    }

    public void addPlayFunctionalityToBackButton(CustomizedButton back) {
        if (playButtonListener == null) {
            playButtonListener = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (Map.Entry<String, JPanel> page : getScheduler().getMatchFramesMap().entrySet()) {
                        MatchPageTemplate frame = (MatchPageTemplate) page.getValue();
                        event.getMatch().setScheduler(getScheduler());
                        frame.setMatch(event.getMatch());
                    }
                    getScheduler().displayMatchFrames(event.getMatch());
                    setEvent(null);
                    back.init();
                }
            };
        }
        back.addMouseListener(playButtonListener);
    }

    public void removePlayFunctionalityToBackButton(CustomizedButton back) {
        back.removeMouseListener(playButtonListener);
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
