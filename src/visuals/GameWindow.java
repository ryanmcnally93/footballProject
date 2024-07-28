package visuals;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Dimension;

public class GameWindow extends JFrame {
    private static final long serialVersionUID = 157462818597402652L;
	private CardLayout layout;
    private JPanel pages;
    private Map<String, JPanel> cardMap; // Map to store card names and their panels
    private JLabel liveScore;
    private CustomProgressBar shotsOnBar;
    private CustomProgressBar allShotsBar;
        private JPanel headerPanel, centerPanel;
        private Box headerBox, centerBox;

        public GameWindow() {
            setTitle("My Game");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null); // Center the window

            cardMap = new HashMap<>();
            layout = new CardLayout();
            pages = new JPanel(layout);

            // Create instances of your panels
            MatchEvents eventsPanel = new MatchEvents(layout, pages, cardMap);
            MatchWatch watchPanel = new MatchWatch(layout, pages, cardMap);

            // Add panels to the main panel
            pages.add(watchPanel, "Watch");
            cardMap.put("Watch", watchPanel);
            pages.add(eventsPanel, "Events");
            cardMap.put("Events", eventsPanel);

            // Initialize with the main page
            getContentPane().add(pages, BorderLayout.CENTER);
            layout.show(pages, "Watch");

            JPanel otherPanel = new JPanel();  // Another panel without buttons
            otherPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));

            // Set up otherPanel
            otherPanel.setBackground(Color.LIGHT_GRAY);
            otherPanel.setLayout(new BorderLayout(5, 5));
            pages.add(otherPanel, "OtherPanel");
            cardMap.put("Other Panel", otherPanel);

            // Header setup
            headerPanel = new JPanel(new BorderLayout());
            headerBox = Box.createVerticalBox();
            liveScore = new JLabel("Arsenal 0 : 0 Tottenham");
            liveScore.setAlignmentX(Component.CENTER_ALIGNMENT);
            liveScore.setHorizontalAlignment(SwingConstants.CENTER);
            headerBox.add(liveScore);
            headerPanel.add(headerBox, BorderLayout.CENTER);
            getContentPane().add(headerPanel, BorderLayout.NORTH);

            // Center setup
            centerPanel = new JPanel(new BorderLayout());
            centerBox = Box.createVerticalBox();
            Box newBox = Box.createHorizontalBox();

            Box leftBox = Box.createVerticalBox();
            JLabel shotsOnLeft = new JLabel("0");
            shotsOnLeft.setAlignmentX(Component.LEFT_ALIGNMENT);
            shotsOnLeft.setHorizontalAlignment(SwingConstants.LEFT);
            leftBox.add(shotsOnLeft);
            leftBox.add(Box.createHorizontalGlue());

            Box middleBox = Box.createVerticalBox();
            JLabel shotsOn = new JLabel("Shots on Target");
            middleBox.add(shotsOn);

            Box rightBox = Box.createVerticalBox();
            rightBox.add(Box.createHorizontalGlue());
            JLabel shotsOnRight = new JLabel("0");
            shotsOnRight.setAlignmentX(Component.RIGHT_ALIGNMENT);
            shotsOnRight.setHorizontalAlignment(SwingConstants.RIGHT);
            rightBox.add(shotsOnRight);

            newBox.add(leftBox);
            newBox.add(middleBox);
            newBox.add(rightBox);
            centerBox.add(newBox);

            //Study how the progress bars are able to take a percentage of page.
            
            Box west = Box.createHorizontalBox();
            west.setPreferredSize(new Dimension(200,200));
            add(west, BorderLayout.WEST);
            
            
            // Shots on target progress bar
            shotsOnBar = new CustomProgressBar();
            shotsOnBar.setBorder(new EmptyBorder(20, 20, 10, 20));
            shotsOnBar.setValue(50);
            centerBox.add(shotsOnBar);

            // Label for all shots
            JLabel allShots = new JLabel("Shots");
            allShots.setAlignmentX(Component.CENTER_ALIGNMENT);
            allShots.setHorizontalAlignment(SwingConstants.CENTER);
            centerBox.add(allShots);

            // All shots progress bar
            allShotsBar = new CustomProgressBar();
            allShotsBar.setBorder(new EmptyBorder(10, 20, 10, 20));
            allShotsBar.setValue(50);
            centerBox.add(allShotsBar);

            CustomProgressBar progressBar_2 = new CustomProgressBar();
            progressBar_2.setBorder(new EmptyBorder(10, 20, 20, 20));
            progressBar_2.setValue(50);
            centerBox.add(progressBar_2);

            JButton playButton = new JButton("Play");
            playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            playButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Play button clicked!");
                }
            });
            centerBox.add(playButton);
            centerPanel.add(centerBox, BorderLayout.CENTER);
            getContentPane().add(centerPanel, BorderLayout.CENTER);

            // Component listener to adjust sizes
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    adjustPanelSize(headerPanel);
                    adjustPanelSize(centerPanel);
                }
            });

            // Set the initial size for the frame
            setSize(800, 600);

            // Set the frame to be visible after setting the size
            setVisible(true);
        }
    
        private void adjustPanelSize(JPanel panel) {
            Dimension screenSize = getSize();
            int width = screenSize.width;
            if (panel == headerPanel) {
                // Fixed height for Header panel
                panel.setPreferredSize(new Dimension(width, 150));
                panel.setMinimumSize(new Dimension(width, 150));
                panel.setMaximumSize(new Dimension(width, 150));
            } else if (panel == centerPanel) {
                // Dynamic width for Center panel
                double seventyFivePercentWidth = width * 0.75;
                panel.setPreferredSize(new Dimension((int) seventyFivePercentWidth, panel.getPreferredSize().height));
                panel.setMinimumSize(new Dimension((int) seventyFivePercentWidth, panel.getMinimumSize().height));
                panel.setMaximumSize(new Dimension((int) seventyFivePercentWidth, panel.getMaximumSize().height));
            }
            // Revalidate and repaint to apply changes
            panel.revalidate();
            panel.repaint();
        }

    public void switchToPanel(GamePanel panel) {
        if (pages != null) {
            remove(pages);
        }
        pages = panel;
        getContentPane().add(pages);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow window = new GameWindow();
            window.setVisible(true);
        });
    }

	public CardLayout getLayout() {
		return layout;
	}

	public void setLayout(CardLayout layout) {
		this.layout = layout;
	}

	public JPanel getPages() {
		return pages;
	}

	public void setPages(JPanel pages) {
		this.pages = pages;
	}

	public Map<String, JPanel> getCardMap() {
		return cardMap;
	}

	public void setCardMap(Map<String, JPanel> cardMap) {
		this.cardMap = cardMap;
	}
}