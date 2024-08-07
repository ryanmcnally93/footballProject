package visuals.MatchFrames;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import general.UsersMatch;

public class MatchTable extends MatchFrames {

	private static final long serialVersionUID = -37261786755290081L;
	private Box centerBox;

	public MatchTable(CardLayout layout, JPanel pages, Map<String, JPanel> cardMap, UsersMatch match) {
		super(layout, pages, cardMap, match);
		
		JLayeredPane layeredPane = getLayeredPane();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        Box west = Box.createHorizontalBox();
        west.setPreferredSize(new Dimension(100,200));
        mainPanel.add(west, BorderLayout.WEST);
        Box east = Box.createHorizontalBox();
        east.setPreferredSize(new Dimension(100,200));
        mainPanel.add(east, BorderLayout.EAST); 
		
        centerBox = Box.createVerticalBox();
        JLabel title = new JLabel("Live Table Page");
        centerBox.add(title);
        mainPanel.add(centerBox, BorderLayout.CENTER);
        
        mainPanel.setBounds(0, 80, 800, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
        
        setVisible(true);
		
	}
	
}
