package visuals.MatchFrames;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.*;

import general.LeagueTable;
import general.UsersMatch;

public class MatchTable extends MatchFrames {

	private static final long serialVersionUID = -37261786755290081L;
	private Box centerBox;
	private LeagueTable table;
	private JPanel mainPanel;

	public MatchTable(CardLayout layout, JPanel pages, Map<String, JPanel> cardMap, UsersMatch match) {
		super(layout, pages, cardMap, match);
		
		JLayeredPane layeredPane = getLayeredPane();
		mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        Box west = Box.createHorizontalBox();
        west.setPreferredSize(new Dimension(100,200));
        mainPanel.add(west, BorderLayout.WEST);
        Box east = Box.createHorizontalBox();
        east.setPreferredSize(new Dimension(100,200));
        mainPanel.add(east, BorderLayout.EAST); 
		
        centerBox = Box.createVerticalBox();

		JScrollPane scroller = new JScrollPane(centerBox);
		scroller.getViewport().setBackground(Color.LIGHT_GRAY);
		scroller.setBorder(null);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        mainPanel.add(scroller, BorderLayout.CENTER);
        
        mainPanel.setBounds(0, 80, 800, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
        
        setVisible(true);
		
	}
	
	public void updateTableVisually() {
		centerBox.removeAll();
		table = getMatch().getLeague().getLeagueTable();
        centerBox.add(table);
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
