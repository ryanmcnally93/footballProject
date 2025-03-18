package visuals.CustomizedElements;

import visuals.ScheduleFrames.Scheduler;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MessageViewer extends GamePanel {

    private CustomizedButton advance;
    private Scheduler scheduler;

    public MessageViewer(Scheduler scheduler) {
        setPermanentWidthAndHeight(this, 570, 180);
        setOpaque(false);
        setLayout(new GridLayout());

        this.scheduler = scheduler;
        advance = new CustomizedButton("Advance", 16);
        advance.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scheduler.addDay();
            }
        });

    }

    public void addAdvanceButton() {
        if (!isAncestorOf(advance)) {
            add(advance);
        }
    }

    public void removeAdvanceButton() {
        if (isAncestorOf(advance)) {
            remove(advance);
        }
    }

    public CustomizedButton getAdvanceButton() {
        return advance;
    }

    public void setAdvanceButton(CustomizedButton advance) {
        this.advance = advance;
    }
}
