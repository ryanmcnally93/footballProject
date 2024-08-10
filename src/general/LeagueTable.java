package general;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import visuals.CustomizedElements.GamePanel;

public class LeagueTable extends GamePanel {

	private static final long serialVersionUID = -737891404507970413L;
	private ArrayList<TableLine> lines;
	private TableLine errorLine;
	private League league;
	private Box container;
	private JPanel teamBox;
	private JLabel title;
	private JPanel tableContainer;
	
	public LeagueTable(League league) {
		this.lines = new ArrayList<>();
		this.league = league;
		
		setBackground(Color.LIGHT_GRAY);
		
		JPanel titleBox = new JPanel();
		titleBox.setPreferredSize(new Dimension(600,30));
    	titleBox.setLayout(new BorderLayout());
    	titleBox.setBackground(Color.LIGHT_GRAY);
        title = new JLabel(league.getName(), SwingConstants.CENTER);
        title.setFont(new Font("Menlo", Font.BOLD, 16));
        title.setForeground(new Color(0, 51, 204));
        titleBox.add(title, BorderLayout.CENTER);
        add(titleBox);
        
        Box row = Box.createHorizontalBox();
    	row.setPreferredSize(new Dimension(600,20));
    	JLabel position = new JLabel("Pos.");
    	position.setPreferredSize(new Dimension(30,20));
        position.setMinimumSize(new Dimension(30,20));
        position.setMaximumSize(new Dimension(30,20));
    	JLabel clubName = new JLabel("Club");
    	clubName.setPreferredSize(new Dimension(450,20));
        clubName.setMinimumSize(new Dimension(450,20));
        clubName.setMaximumSize(new Dimension(450,20));
    	JLabel wins = new JLabel("W");
    	wins.setPreferredSize(new Dimension(30,20));
        wins.setMinimumSize(new Dimension(30,20));
        wins.setMaximumSize(new Dimension(30,20));
    	JLabel draws = new JLabel("D");
    	draws.setPreferredSize(new Dimension(30,20));
        draws.setMinimumSize(new Dimension(30,20));
        draws.setMaximumSize(new Dimension(30,20));
    	JLabel losses = new JLabel("L");
    	losses.setPreferredSize(new Dimension(30,20));
        losses.setMinimumSize(new Dimension(30,20));
        losses.setMaximumSize(new Dimension(30,20));
//    	JLabel position = new JLabel(String.valueOf(eachLine.getPosition()));
//    	JLabel position = new JLabel(String.valueOf(eachLine.getPosition()));
//    	JLabel position = new JLabel(String.valueOf(eachLine.getPosition()));
    	JLabel points = new JLabel("Pts");
    	points.setPreferredSize(new Dimension(30,20));
        points.setMinimumSize(new Dimension(30,20));
        points.setMaximumSize(new Dimension(30,20));
    	row.add(position);
    	row.add(clubName);
    	row.add(wins);
    	row.add(draws);
    	row.add(losses);
    	row.add(points);
    	add(row);
    	
        tableContainer = new JPanel();
        tableContainer.setPreferredSize(new Dimension(600,390));
        tableContainer.setBackground(Color.LIGHT_GRAY);
    	add(tableContainer);
        
		setVisible(true);
	}

	public TableLine getLine(Team team) {
		for(TableLine tableline : lines) {
			if(team.getName().equals(tableline.getTeamName())) {
				return tableline;
			}
		}
		return errorLine;
	}
	
	public ArrayList<TableLine> getAllLines(){
		return lines;
	}
	
	public void updateLinesInTableLogic() {

		// Implement logic to 'sort' your array list by points and GD
		Collections.sort(lines, new Comparator<TableLine>() {
            @Override
            public int compare(TableLine t1, TableLine t2) {
                return t2.getPoints().compareTo(t1.getPoints());
            }
        });
		
		for(int i=0;i<lines.size();i++) {
			lines.get(i).setPosition(i+1);
		}
		
		tableContainer.removeAll();
		for(TableLine eachLine : lines) {
			Box row = Box.createHorizontalBox();
			row.setPreferredSize(new Dimension(600,20));
        	JLabel position = new JLabel(String.valueOf(eachLine.getPosition()));
        	position.setPreferredSize(new Dimension(30,20));
            position.setMinimumSize(new Dimension(30,20));
            position.setMaximumSize(new Dimension(30,20));
        	JLabel clubName = new JLabel(eachLine.getTeamName());
        	clubName.setPreferredSize(new Dimension(450,20));
            clubName.setMinimumSize(new Dimension(450,20));
            clubName.setMaximumSize(new Dimension(450,20));
        	JLabel wins = new JLabel(String.valueOf(eachLine.getWins()));
        	wins.setPreferredSize(new Dimension(30,20));
            wins.setMinimumSize(new Dimension(30,20));
            wins.setMaximumSize(new Dimension(30,20));
        	JLabel draws = new JLabel(String.valueOf(eachLine.getDraws()));
        	draws.setPreferredSize(new Dimension(30,20));
            draws.setMinimumSize(new Dimension(30,20));
            draws.setMaximumSize(new Dimension(30,20));
        	JLabel losses = new JLabel(String.valueOf(eachLine.getLosses()));
        	losses.setPreferredSize(new Dimension(30,20));
            losses.setMinimumSize(new Dimension(30,20));
            losses.setMaximumSize(new Dimension(30,20));
//        	JLabel position = new JLabel(String.valueOf(eachLine.getPosition()));
//        	JLabel position = new JLabel(String.valueOf(eachLine.getPosition()));
//        	JLabel position = new JLabel(String.valueOf(eachLine.getPosition()));
        	JLabel points = new JLabel(String.valueOf(eachLine.getPoints()));
        	points.setPreferredSize(new Dimension(30,20));
            points.setMinimumSize(new Dimension(30,20));
            points.setMaximumSize(new Dimension(30,20));
        	row.add(position);
        	row.add(clubName);
        	row.add(wins);
        	row.add(draws);
        	row.add(losses);
        	row.add(points);
        	tableContainer.add(row);
        };
		
        tableContainer.revalidate();
        tableContainer.repaint();
	}

	public void setLine(ArrayList<TableLine> line) {
		this.lines = line;
	}

	@Override
	public String toString() {
		return "Definitely generating the right item";
	}
	
}
