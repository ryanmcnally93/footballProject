package visuals;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MatchFrames extends GamePanel {
	private static final long serialVersionUID = -7779250965738495855L;
	private CardLayout layout;
	private JPanel pages;

    public MatchFrames(CardLayout cardLayout, JPanel mainPanel) {
        this.layout = cardLayout;
        this.pages = mainPanel;
        Initialize();
    }
    
	public void Initialize() {
		
		JButton prevButton = new JButton("Prev");
        JButton nextButton = new JButton("Next");

        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("You clicked previous");
                layout.previous(pages);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("You clicked next");
                layout.next(pages);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);
    }

}
