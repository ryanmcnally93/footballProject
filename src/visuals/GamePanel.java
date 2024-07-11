package visuals;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = -3967158582430585376L;
	private String pageTitle;
	private MainPage main;

	// A panel is a page of data, so main and events
	// Each 'panel' is added to the window
    public GamePanel(String title, Match match) {
        this.pageTitle = title;
        
        setPreferredSize(new Dimension(800, 600));
        if (title.equals("main")) {
            this.main = new MainPage(match);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pageTitle.equals("main")) {
        	main.draw(g);
        } else if (pageTitle.equals("events")) {
            g.setColor(Color.GREEN);
            g.fillOval(150, 100, 100, 100);
            g.drawString("Other Page Content", 150, 250);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pageTitle.equals("Page 1")) {
            // Update the game state periodically
        }
        repaint(); // Repaint the panel to update the visuals
    }

}