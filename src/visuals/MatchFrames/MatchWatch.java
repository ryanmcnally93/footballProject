package visuals.MatchFrames;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import entities.UsersMatch;

public class MatchWatch extends MatchFrames {

	private static final long serialVersionUID = -3730364980616724000L;
	private Box centerBox;

	public MatchWatch(CardLayout layout, JPanel pages, Map<String, JPanel> cardMap, UsersMatch match) {
		super(layout, pages, cardMap, match);
		
		JLayeredPane layeredPane = getLayeredPane();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        appendEastAndWest(mainPanel);
		
        centerBox = Box.createVerticalBox();
        JLabel title = new JLabel("Watch Match Page");
        centerBox.add(title);
        mainPanel.add(centerBox, BorderLayout.CENTER);
        
        mainPanel.setBounds(0, 80, 800, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
        
        setVisible(true);
        
	}
	
}
