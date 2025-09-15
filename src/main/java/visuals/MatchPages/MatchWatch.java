package visuals.MatchPages;

import visuals.CustomizedElements.CustomizedButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MatchWatch extends MatchPageTemplate {

	private static final long serialVersionUID = -3730364980616724000L;
	private Box centerBox;

	public MatchWatch(CardLayout layout, JPanel pages, Speedometer speedometer, ArrayList<CustomizedButton> buttons) {
		super(layout, pages, speedometer, buttons);
		
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

    @Override
    public String getMatchFrameName() {
        return "Watch";
    }

}
