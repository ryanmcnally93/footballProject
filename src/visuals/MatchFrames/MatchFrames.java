package visuals.MatchFrames;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Box;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import entities.UsersMatch;
import visuals.CustomizedElements.CustomizedButton;
import visuals.CustomizedElements.GamePanel;
import visuals.CustomizedElements.SlidingPanel;

public class MatchFrames extends GamePanel {
	private static final long serialVersionUID = -7779250965738495855L;
	private static final String RIGHT = "Next Page";
	private static final String LEFT = "Previous Page";
	private static final String PLAY = "Play Game";
	private CardLayout layout;
	private JPanel pages;
	private SlidingPanel slidingPanel;
	private UsersMatch match;
	private JLayeredPane layeredPane;
	private HeaderPanel headerPanel;
	private FooterPanel footerPanel;

	public MatchFrames(CardLayout cardLayout, JPanel mainPanel, UsersMatch match) {
    	super();
    	this.layout = cardLayout;
        this.pages = mainPanel;
        this.match = match;
        
        setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
        
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
             
        slidingPanel = new SlidingPanel();
        slidingPanel.setBounds(0, 0, 800, 600);
        
        headerPanel = new HeaderPanel();
        headerPanel.setBounds(0, 0, 800, 80);

        footerPanel = new FooterPanel();
        footerPanel.setBounds(0, 500, 800, 100);
        
        layeredPane.add(headerPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(footerPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(slidingPanel, JLayeredPane.MODAL_LAYER);
        
        add(layeredPane, BorderLayout.CENTER);
        setVisible(true);
    }
	
	public class HeaderPanel extends JPanel {
		
		private static final long serialVersionUID = -488591451686633686L;
		private JLabel liveScore;
		
		public HeaderPanel() {
			setLayout(new BorderLayout());
	        setBackground(Color.LIGHT_GRAY);
	        liveScore = new JLabel(match.getHome().getName() + " " + match.getHomeScore() + " - " + match.getAwayScore() + " " + match.getAway().getName(), SwingConstants.CENTER);
	        liveScore.setFont(new Font("Menlo", Font.BOLD, 30));
	        liveScore.setForeground(new Color(0, 51, 204));
	        add(liveScore, BorderLayout.CENTER);
		}
		
		public JLabel getLiveScore() {
			return liveScore;
		}

		public void setLiveScore(String score) {
			this.liveScore.setText(score);
		}

		public void updateScoreBoard(int home, int away) {
			setLiveScore(match.getHome().getName() + " " + home + " - " + away + " " + match.getAway().getName());
			repaint();
		}

		public void setLiveScore(JLabel liveScore) {
			this.liveScore = liveScore;
		}
		
	}
	
	public class FooterPanel extends JPanel {
		
		private static final long serialVersionUID = 2730359600520156788L;
		private CustomizedButton prevButton;
		private CustomizedButton playButton;
		private CustomizedButton nextButton;
		private JPanel buttonPanel;
		private ActionMap actionMap;
		private Box line;
		
		public FooterPanel() {

			setLayout(new BorderLayout());
	        setBackground(Color.LIGHT_GRAY);

			int day = match.getDateTime().getDayOfMonth();
			String dayWithSuffix = day + getDayOfMonthSuffix(day);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy HH:mm");
			String formattedDate = dayWithSuffix + " " + match.getDateTime().format(formatter);

			JLabel dateAndTime = new JLabel(formattedDate);
			dateAndTime.setPreferredSize(new Dimension(200,30));
			dateAndTime.setMaximumSize(new Dimension(200,30));
			dateAndTime.setMinimumSize(new Dimension(200,30));
			dateAndTime.setBorder(new EmptyBorder(0, 15, 0, 0));
			JLabel stadium = new JLabel(match.getStadium(), SwingConstants.CENTER);
			stadium.setPreferredSize(new Dimension(200,30));
			stadium.setMaximumSize(new Dimension(200,30));
			stadium.setMinimumSize(new Dimension(200,30));
			JLabel attendance = new JLabel("60000", SwingConstants.RIGHT);
			attendance.setPreferredSize(new Dimension(200,30));
			attendance.setMaximumSize(new Dimension(200,30));
			attendance.setMinimumSize(new Dimension(200,30));
			attendance.setBorder(new EmptyBorder(0, 0, 0, 20));

			line = Box.createHorizontalBox();
			line.add(dateAndTime);
			line.add(Box.createHorizontalStrut(100));
			line.add(stadium);
			line.add(Box.createHorizontalStrut(100));
			line.add(attendance);

			add(line, BorderLayout.NORTH);
			
			prevButton = new CustomizedButton("Prev");
	        nextButton = new CustomizedButton("Next");
	
	        prevButton.addActionListener(e -> {
	        	layout.previous(pages);
	        });
	
	        nextButton.addActionListener(e -> {
	            layout.next(pages);
	        });
	        
	        playButton = new CustomizedButton("Play");
	        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	        playButton.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	handleClick();
	            }
	        });

	        buttonPanel = new JPanel();
	        buttonPanel.add(prevButton);
	        buttonPanel.add(playButton);
	        buttonPanel.add(nextButton);
	        buttonPanel.setBackground(Color.LIGHT_GRAY);
	        buttonPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
	        add(buttonPanel, BorderLayout.CENTER);
		
	        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	        actionMap = getActionMap();
	        
	        inputMap.put(KeyStroke.getKeyStroke("LEFT"), LEFT);
	        actionMap.put(LEFT, new leftClick());
	        
	        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), RIGHT);
	        actionMap.put(RIGHT, new rightClick());
	        
	        inputMap.put(KeyStroke.getKeyStroke("ENTER"), PLAY);
	        actionMap.put(PLAY, new PlayGame());
	        
		}

		private static String getDayOfMonthSuffix(int day) {
			if (day >= 11 && day <= 13) {
				return "th";
			}
			switch (day % 10) {
				case 1:  return "st";
				case 2:  return "nd";
				case 3:  return "rd";
				default: return "th";
			}
		}

		public ActionMap getFooterActionMap() {
			return actionMap;
		}

		public Box getLine(){
			return this.line;
		}

		public void setFooterActionMap(ActionMap actionMap) {
			this.actionMap = actionMap;
		}
	}
	
	public void createContinueButton() {
		footerPanel.buttonPanel.remove(footerPanel.nextButton);
		footerPanel.buttonPanel.remove(footerPanel.playButton);
		CustomizedButton cont = new CustomizedButton("Continue");

		cont.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	match.getWindow().getContentPane().removeAll();
            	match.getScheduler().displayPage(match.getWindow());
				match.getScheduler().refreshMessages();
            	match.getWindow().revalidate();
            	match.getWindow().repaint();
            }
        });
		
		Action contAction = new AbstractAction() {
		    private static final long serialVersionUID = -7844475567826191445L;
			@Override
		    public void actionPerformed(ActionEvent e) {
		    	match.getWindow().getContentPane().removeAll();
            	match.getScheduler().displayPage(match.getWindow());
            	match.getWindow().revalidate();
            	match.getWindow().repaint();
		    }
		};
		footerPanel.getFooterActionMap().put(PLAY, contAction);
		
		footerPanel.buttonPanel.add(cont);
		footerPanel.buttonPanel.add(footerPanel.nextButton);
		footerPanel.buttonPanel.revalidate();
		footerPanel.buttonPanel.repaint();
	}

    public HeaderPanel getHeaderPanel() {
		return headerPanel;
	}

	public void setHeaderPanel(HeaderPanel headerPanel) {
		this.headerPanel = headerPanel;
	}

	public class leftClick extends AbstractAction {

		private static final long serialVersionUID = -5272199687623548598L;

		@Override
		public void actionPerformed(ActionEvent e) {
			layout.previous(pages);
		}
    }
    
    public class PlayGame extends AbstractAction {

		private static final long serialVersionUID = 7874836628287155601L;

		@Override
		public void actionPerformed(ActionEvent e) {
			handleClick();
		}
    }
    
    public class rightClick extends AbstractAction {

		private static final long serialVersionUID = 6606734713275450399L;

		@Override
		public void actionPerformed(ActionEvent e) {
			layout.next(pages);
		}
    }
    
	public void handleClick() {
		match.startMatch();
	}
	
	public void goalAlert(String name, int minute) {
    	getSlidingPanel().startSliding(name, minute);
    }

	public JPanel getPages() {
		return pages;
	}

	public void setPages(JPanel pages) {
		this.pages = pages;
	}

	public CardLayout getLayout() {
		return layout;
	}

	public void setLayout(CardLayout layout) {
		this.layout = layout;
	}

	public SlidingPanel getSlidingPanel() {
		return slidingPanel;
	}

	public void setSlidingPanel(SlidingPanel slidingPanel) {
		this.slidingPanel = slidingPanel;
	}

	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}

	public void setLayeredPane(JLayeredPane layeredPane) {
		this.layeredPane = layeredPane;
	}

	public FooterPanel getFooterPanel() {
		return footerPanel;
	}

	public void setFooterPanel(FooterPanel footerPanel) {
		this.footerPanel = footerPanel;
	}

	public UsersMatch getMatch() {
		return match;
	}

	public void setMatch(UsersMatch match) {
		this.match = match;
	}
	
}
