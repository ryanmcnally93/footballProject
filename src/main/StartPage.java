package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import visuals.MatchFrames.MatchStats;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartPage extends JPanel {

    private static final long serialVersionUID = 4432562427445474607L;
    private JPanel centerBox, teamsBox;
    private JLabel title;
    private initialSetup setup;
    private Box countryBox, leagueBox, nameBox;
    private JButton country, editButton, submitButton;
    private String name;

    public StartPage(initialSetup setup) {
    	this.setup = setup;
    	setLayout(new BorderLayout());
    	
    	// Header
    	
    	JPanel north = new JPanel();
    	north.setPreferredSize(new Dimension(800,80));
    	north.setLayout(new BorderLayout());
        north.setBackground(Color.LIGHT_GRAY);
        title = new JLabel("Type your name", SwingConstants.CENTER);
        title.setFont(new Font("Menlo", Font.BOLD, 30));
        title.setForeground(new Color(0, 51, 204));
        north.add(title, BorderLayout.CENTER);
        add(north, BorderLayout.NORTH);
    	
    	// Right Box
    	
    	Box east = Box.createHorizontalBox();
        east.setPreferredSize(new Dimension(100,200));
        add(east, BorderLayout.EAST); 
        
        // Footer
        
        
        
        // Left Box
        
        Box west = Box.createHorizontalBox();
        west.setPreferredSize(new Dimension(100,200));
        add(west, BorderLayout.WEST);
        
        // Center Box
        
        centerBox = new JPanel();
        centerBox.setBackground(Color.LIGHT_GRAY);
        
        nameBox = Box.createHorizontalBox();
        nameBox.setPreferredSize(new Dimension(600, 30));
        nameBox.add(Box.createHorizontalGlue());
        JTextField nameField = new JTextField(20);
        submitButton = new JButton("Submit");
        nameBox.add(nameField);
        nameBox.add(submitButton);
        nameBox.add(Box.createHorizontalGlue());
        centerBox.add(nameBox);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	name=nameField.getText();
            	handleSubmit(nameField);  
            }
        });
        
        add(centerBox, BorderLayout.CENTER);
        
        setBackground(Color.LIGHT_GRAY);
        setVisible(true);
    	
    }
    
    public void handleEdit() {
    	
    	for (int i = centerBox.getComponentCount() - 1; i > 0; i--) {
            centerBox.remove(i);
        }
    	centerBox.revalidate();
    	centerBox.repaint();
    	nameBox.removeAll();
        nameBox.add(Box.createHorizontalGlue());
        JTextField nameField = new JTextField(20);
        nameField.setText(name);
        submitButton = new JButton("Submit");
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	name=nameField.getText();
            	handleSubmit(nameField);  
            }
        });
        
        nameBox.add(nameField);
        nameBox.add(submitButton);
        nameBox.add(Box.createHorizontalGlue());
        nameBox.revalidate();
        nameBox.repaint();
        title.setText("Edit your name");
    }
    
    public void handleSubmit(JTextField nameField) {
    	String userInput = nameField.getText();
        nameBox.removeAll();
        nameBox.add(Box.createHorizontalGlue());
        JLabel result = new JLabel(userInput);
        nameBox.add(result);
        editButton = new JButton("Edit");
        nameBox.add(editButton);
        nameBox.add(Box.createHorizontalGlue());
        nameBox.revalidate();
        nameBox.repaint();
        
        editButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		handleEdit();
        	}
        });
        
        countryBox = Box.createHorizontalBox();
        countryBox.setPreferredSize(new Dimension(600, 30));
        countryBox.add(Box.createHorizontalGlue());
        country = new JButton("England");
        
        country.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		countryPick();
        	}
        });
        
        countryBox.add(country);
        countryBox.add(Box.createHorizontalGlue());
        centerBox.add(countryBox);
        
        title.setText("Pick a country");
    }
    
    public void countryPick() {
    	
    	leagueBox = Box.createHorizontalBox();
    	leagueBox.setPreferredSize(new Dimension(600, 30));
        leagueBox.add(Box.createHorizontalGlue());
    	JButton league = new JButton("Premier League");
    	leagueBox.add(league);
    	leagueBox.add(Box.createHorizontalGlue());
        centerBox.add(leagueBox);
        
        title.setText("Pick a League");
        
        league.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		leaguePick();
        	}
        });
        
        revalidate();
        repaint();
    }
    
    public void leaguePick() {
    	
    	teamsBox = new JPanel();
    	teamsBox.setBackground(Color.LIGHT_GRAY);
    	teamsBox.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        teamsBox.setPreferredSize(new Dimension(600, 90));

        // Assuming setup.getPremierLeague().getTeams() returns a Map
        Map<String, Team> teams = setup.getPremierLeague().getTeams();
        for (Map.Entry<String, Team> each : teams.entrySet()) {
            Team current = each.getValue();
            JButton teamButton = new JButton(current.getName());
            teamsBox.add(teamButton);
            
            teamButton.addMouseListener(new MouseAdapter() {
            	@Override
            	public void mouseClicked(MouseEvent e) {
            		teamPick(current);
            	}
            });
        }

        // Add the box to the frame
        centerBox.add(teamsBox, BorderLayout.CENTER);
    	
    	title.setText("Pick a Team");
    }

    public void teamPick(Team team) {
    	User user = new User(name, 18, 40000);
    	Map<String, Team> cardMap = setup.getPremierLeague().getTeams();
    	((Team) cardMap.get(team.getName())).setManager(user);
    	System.out.println(user.getName() + " is the new manager of " + team.getName());
    	setup.getPremierLeague().seasonSetup();
//    	setup.getPremierLeague().getTeamFixturesToString(team);
    }
    
    public void displayPage() {
		setup.getWindow().getContentPane().add(this, BorderLayout.CENTER);
    }
	
}
