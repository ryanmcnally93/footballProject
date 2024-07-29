package visuals;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MatchEvents extends MatchFrames {
    private static final long serialVersionUID = 5937268249853937276L;

    public MatchEvents(CardLayout layout, JPanel pages, Map<String, JPanel> cardMap) {
    	super(layout, pages, cardMap);
    }

}