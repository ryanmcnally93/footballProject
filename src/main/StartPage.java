package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.*;

import entities.Team;
import entities.User;
import visuals.CustomizedElements.CustomizedButton;
import visuals.CustomizedElements.CustomizedTextField;
import visuals.CustomizedElements.CustomizedTitle;
import visuals.CustomizedElements.GamePanel;
import visuals.ScheduleFrames.Scheduler;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartPage extends GamePanel {

    private static final long serialVersionUID = 4432562427445474607L;
    private JPanel centerBox, teamsBox, south;
    private CustomizedTitle title;
    private initialSetup setup;
    private CustomizedButton country, submitButton;
    private String name;
	private Image backgroundImage;
	private CustomizedTextField nameField;
	private Box mainContentBox, buttonBox, leftFooterBox, middleFooterBox, rightFooterBox;
	private CustomizedTitle chosenCountry, chosenLeague;

    public StartPage(initialSetup setup) {
    	this.setup = setup;
		backgroundImage = new ImageIcon("./src/visuals/Images/start_page_main.jpg").getImage();
    	setLayout(new BorderLayout());
    	
    	// Header
    	JPanel north = new JPanel();
    	north.setPreferredSize(new Dimension(800,170));
    	north.setLayout(new BorderLayout());
        north.setOpaque(false);

		JPanel titleWrapper = new JPanel();
		titleWrapper.setOpaque(false); // Keep it transparent
		titleWrapper.setPreferredSize(new Dimension(300, 80)); // Enforce size
		titleWrapper.setLayout(new GridBagLayout()); // Center label inside

        title = new CustomizedTitle("Type your name", SwingConstants.CENTER);

		titleWrapper.add(title); // Add label inside wrapper
		north.add(titleWrapper, BorderLayout.CENTER);
        add(north, BorderLayout.NORTH);

        // Center Box
        centerBox = new JPanel();
        centerBox.setOpaque(false);
        
        mainContentBox = Box.createVerticalBox();
        mainContentBox.setPreferredSize(new Dimension(450, 120));
		mainContentBox.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		mainContentBox.add(Box.createVerticalGlue());

        nameField = new CustomizedTextField(20, 15);
		mainContentBox.add(nameField);

		submitButton = new CustomizedButton("Submit");
		buttonBox = Box.createHorizontalBox();
		buttonBox.add(Box.createHorizontalGlue()); // Pushes button to center
		buttonBox.add(submitButton);
		buttonBox.add(Box.createHorizontalGlue()); // Keeps it centered
		mainContentBox.add(buttonBox);
        mainContentBox.add(Box.createVerticalGlue());
        centerBox.add(mainContentBox);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	name=nameField.getText();
            	submitName(nameField);
            }
        });
        
        add(centerBox, BorderLayout.CENTER);

		south = new JPanel();
		south.setPreferredSize(new Dimension(800,80));
		south.setLayout(new BoxLayout(south, BoxLayout.X_AXIS));
		south.setOpaque(false);

		leftFooterBox = Box.createHorizontalBox();
		leftFooterBox.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		setPermanentWidthAndHeight(leftFooterBox, 266, 80);
		middleFooterBox = Box.createHorizontalBox();
		middleFooterBox.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		setPermanentWidthAndHeight(middleFooterBox, 268, 80);
		rightFooterBox = Box.createHorizontalBox();
		rightFooterBox.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		setPermanentWidthAndHeight(rightFooterBox, 266, 80);

		south.add(leftFooterBox);
		south.add(middleFooterBox);
		south.add(rightFooterBox);

		add(south, BorderLayout.SOUTH);
        
        setBackground(new Color(0, 0, 0, 140));
		setOpaque(false);
        setVisible(true);
    }

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.45f)); // 55% transparency
		g2d.setColor(new Color(255, 255, 255)); // Change color if needed (white here)
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.dispose();
	}
    
    public void editName() {
    	// Remove country pick options
		for (int i = centerBox.getComponentCount() - 1; i > 0; i--) {
            centerBox.remove(i);
        }
		// Remove any components that may have been added to nameBox
    	mainContentBox.removeAll();
		leftFooterBox.removeAll();
		leftFooterBox.revalidate();
		leftFooterBox.repaint();

		viewNamePickPage();

		centerBox.revalidate();
		centerBox.repaint();
        title.setText("Edit your name");
		title.addOpaqueBackground();
    }

	private void viewNamePickPage() {
		mainContentBox.add(Box.createVerticalGlue());
		nameField.setText(name);
		mainContentBox.add(nameField);
		mainContentBox.add(buttonBox);
		mainContentBox.add(Box.createVerticalGlue());
	}

	public void submitName(CustomizedTextField nameField) {
    	// Save the name then remove the namebox and submit button
		String userInput = nameField.getText();
        mainContentBox.removeAll();

        CustomizedTitle chosenName = new CustomizedTitle(userInput, SwingConstants.LEFT);
        leftFooterBox.add(Box.createHorizontalGlue());
		leftFooterBox.add(chosenName);
		CustomizedButton firstEditButton = new CustomizedButton("Edit");
		leftFooterBox.add(firstEditButton);
		leftFooterBox.add(Box.createHorizontalGlue());

		firstEditButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editName();
        	}
        });

		if (chosenCountry == null) {
			viewCountryPickPage();
		} else if (chosenLeague == null) {
			viewLeaguePickPage();
		} else {
			viewTeamPickPage();
		}
    }

	private void viewCountryPickPage() {
		mainContentBox.add(Box.createVerticalGlue());

		Box firstRowOfCountries = Box.createHorizontalBox();
		Box secondRowOfCountries = Box.createHorizontalBox();

		country = new CustomizedButton("England");
		CustomizedButton Spain = new CustomizedButton("Spain");
		CustomizedButton Germany = new CustomizedButton("Germany");
		CustomizedButton USA = new CustomizedButton("USA");
		CustomizedButton France = new CustomizedButton("France");
		CustomizedButton Italy = new CustomizedButton("Italy");

		country.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				countryPick(country.getText());
			}
		});

		firstRowOfCountries.add(Spain);
		firstRowOfCountries.add(country);
		firstRowOfCountries.add(Germany);
		mainContentBox.add(firstRowOfCountries);

		secondRowOfCountries.add(USA);
		secondRowOfCountries.add(France);
		secondRowOfCountries.add(Italy);
		mainContentBox.add(secondRowOfCountries);

		mainContentBox.add(Box.createVerticalGlue());
		mainContentBox.revalidate();
		mainContentBox.repaint();

		title.setText("Pick a country");
		title.addOpaqueBackground();
	}

	public void countryPick(String country) {
		mainContentBox.removeAll();

		chosenCountry = new CustomizedTitle(country, SwingConstants.CENTER);
		middleFooterBox.add(Box.createHorizontalGlue());
		middleFooterBox.add(chosenCountry);
		CustomizedButton secondEditButton = new CustomizedButton("Edit");

		secondEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editCountry();
			}
		});

		middleFooterBox.add(secondEditButton);
		middleFooterBox.add(Box.createHorizontalGlue());

		viewLeaguePickPage();
    }

	private void editCountry() {
		chosenCountry = null;
		chosenLeague = null;
		// Remove league pick options
		for (int i = centerBox.getComponentCount() - 1; i > 0; i--) {
			centerBox.remove(i);
		}
		// Remove any components that may have been added to nameBox
		mainContentBox.removeAll();
		middleFooterBox.removeAll();
		middleFooterBox.revalidate();
		middleFooterBox.repaint();

		rightFooterBox.removeAll();
		rightFooterBox.revalidate();
		rightFooterBox.repaint();

		viewCountryPickPage();

		centerBox.revalidate();
		centerBox.repaint();
		title.setText("Edit your country");
		title.addOpaqueBackground();
	}

	private void viewLeaguePickPage() {
		mainContentBox.add(Box.createVerticalGlue());
		Box firstRowOfLeagues = Box.createHorizontalBox();
		Box secondRowOfLeagues = Box.createHorizontalBox();
		JButton prem = new JButton("Premier League");
		JButton championship = new JButton("Championship");
		JButton leagueOne = new JButton("League One");
		JButton leagueTwo = new JButton("League Two");
		firstRowOfLeagues.add(prem);
		firstRowOfLeagues.add(championship);
		secondRowOfLeagues.add(leagueOne);
		secondRowOfLeagues.add(leagueTwo);
		mainContentBox.add(firstRowOfLeagues);
		mainContentBox.add(secondRowOfLeagues);
		mainContentBox.add(Box.createVerticalGlue());
		mainContentBox.revalidate();
		mainContentBox.repaint();

		prem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				leaguePick(prem.getText());
			}
		});

		title.setText("Pick a League");
		title.addOpaqueBackground();
	}

	public void leaguePick(String league) {
    	mainContentBox.removeAll();

		chosenLeague = new CustomizedTitle(league, SwingConstants.RIGHT);
		rightFooterBox.add(Box.createHorizontalGlue());
		rightFooterBox.add(chosenLeague);
		CustomizedButton thirdEditButton = new CustomizedButton("Edit");

		thirdEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editLeague();
			}
		});

		rightFooterBox.add(thirdEditButton);
		rightFooterBox.add(Box.createHorizontalGlue());

		viewTeamPickPage();
    }

	private void editLeague() {
		chosenLeague = null;
		// Remove team pick options
		for (int i = centerBox.getComponentCount() - 1; i > 0; i--) {
			centerBox.remove(i);
		}
		// Remove any components that may have been added to nameBox
		mainContentBox.removeAll();
		rightFooterBox.removeAll();
		rightFooterBox.revalidate();
		rightFooterBox.repaint();

		viewLeaguePickPage();

		centerBox.revalidate();
		centerBox.repaint();
		title.setText("Edit your league");
		title.addOpaqueBackground();
	}

	private void viewTeamPickPage() {
		// Assuming setup.getPremierLeague().getTeams() returns a Map
		Map<String, Team> teams = setup.getSeason().getPremLeague().getTeams();
		for (Map.Entry<String, Team> each : teams.entrySet()) {
			Team current = each.getValue();
			JButton teamButton = new JButton(current.getName());
			mainContentBox.add(teamButton);

			teamButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					teamPick(current);
				}
			});
		}
		mainContentBox.revalidate();
		mainContentBox.repaint();

		title.setText("Pick a Team");
		title.addOpaqueBackground();
	}

	public void teamPick(Team team) {
    	User user = new User(name, 18, 40000);
    	Map<String, Team> cardMap = setup.getSeason().getPremLeague().getTeams();
    	((Team) cardMap.get(team.getName())).setManager(user);
    	System.out.println(user.getName() + " is the new manager of " + team.getName());
    	
    	// Create Schedule
    	Scheduler schedule = new Scheduler(user, team, setup.getSeason().getPremLeague());
    	setup.getWindow().getContentPane().remove(this);
    	setup.getWindow().getContentPane().revalidate();
    	setup.getWindow().getContentPane().repaint();
    	schedule.displayPage(setup.getWindow());
    }
    
    public void displayPage() {
    	setup.getWindow().getContentPane().add(this, BorderLayout.CENTER);
    }

	public JPanel getCenterBox() {
		return centerBox;
	}

	public void setCenterBox(JPanel centerBox) {
		this.centerBox = centerBox;
	}

	public JPanel getTeamsBox() {
		return teamsBox;
	}

	public void setTeamsBox(JPanel teamsBox) {
		this.teamsBox = teamsBox;
	}

	public CustomizedTitle getTitle() {
		return title;
	}

	public void setTitle(CustomizedTitle title) {
		this.title = title;
	}

	public initialSetup getSetup() {
		return setup;
	}

	public void setSetup(initialSetup setup) {
		this.setup = setup;
	}

	public Box getMainContentBox() {
		return mainContentBox;
	}

	public void setMainContentBox(Box mainContentBox) {
		this.mainContentBox = mainContentBox;
	}

	public CustomizedButton getCountry() {
		return country;
	}

	public void setCountry(CustomizedButton country) {
		this.country = country;
	}

	public CustomizedButton getSubmitButton() {
		return submitButton;
	}

	public void setSubmitButton(CustomizedButton submitButton) {
		this.submitButton = submitButton;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
