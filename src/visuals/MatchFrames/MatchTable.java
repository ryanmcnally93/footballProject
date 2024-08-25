package visuals.MatchFrames;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.Map;

import javax.swing.*;

import visuals.CustomizedElements.LeagueTable;
import entities.UsersMatch;

public class MatchTable extends MatchFrames {

	private static final long serialVersionUID = -37261786755290081L;
	private Box centerBox;
	private LeagueTable table;
	private JPanel mainPanel;
	private JScrollPane scroller;

	public MatchTable(CardLayout layout, JPanel pages, UsersMatch match) {
		super(layout, pages, match);
		
		JLayeredPane layeredPane = getLayeredPane();
		mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        appendEastAndWest(mainPanel);
		
        centerBox = Box.createVerticalBox();
		centerBox.setBackground(Color.LIGHT_GRAY);

		scroller = makeScroller(centerBox);

		mainPanel.add(scroller, BorderLayout.CENTER);

        mainPanel.setBounds(0, 80, 800, 420);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
        
        setVisible(true);
	}
	
	public void updateTableVisually() {
		centerBox.removeAll();
		table = getMatch().getLeague().getLeagueTable();
        centerBox.add(table);
		Box padding = Box.createVerticalBox();
		padding.add(Box.createVerticalStrut(20));  // 20 pixels of padding, adjust as needed
		centerBox.add(padding);
		centerBox.revalidate();
		centerBox.repaint();
	}

	public Box getCenterBox() {
		return centerBox;
	}

	public void setCenterBox(Box centerBox) {
		this.centerBox = centerBox;
	}

	public LeagueTable getTable() {
		return table;
	}

	public void setTable(LeagueTable table) {
		this.table = table;
	}
	
}
