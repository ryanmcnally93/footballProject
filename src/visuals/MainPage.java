package visuals;
import javax.swing.JFrame;
import javax.swing.JPanel;
import people.Footballer;
import people.Goalkeeper;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainPage extends JPanel {

	private static final long serialVersionUID = -4173725585901723623L;
	private static final int SQUARE_X = 50;
	private static final int SQUARE_Y = 50;
	private static final int SQUARE_SIZE = 50;
	private static int messageIncOne = 250;
	private static int messageIncTwo = 30;
	private Match match;

	public MainPage(Match match) {
		this.match = match;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY();
				if (mouseX >= SQUARE_X && mouseX <= SQUARE_X + SQUARE_SIZE &&
						mouseY >= SQUARE_Y && mouseY <= SQUARE_Y + SQUARE_SIZE) {
					handleClick();
					System.out.println("Yellow square clicked!");
				}
			}
		});
        setPreferredSize(new Dimension(800, 600)); // Set preferred size
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		// Clear the screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// Draw a yellow border
		g.setColor(Color.YELLOW);
		// Top border
		g.fillRect(0, 0, WIDTH, 2);
		// Bottom border
		g.fillRect(0, HEIGHT - 2, WIDTH, 2);
		// Left border
		g.fillRect(0, 0, 2, HEIGHT);
		// Right border (Starting point is 2px from the full width
		// 0 is top of page, 2 is thickness, height is measurement
		// travelling downwards.
		g.fillRect(WIDTH - 2, 0, 2, HEIGHT);

		// Draw a simple shape inside the border (optional)
		g2d.fillRect(SQUARE_X, SQUARE_Y, SQUARE_SIZE, SQUARE_SIZE);
	}

	public void handleClick() {
		this.match.startMatch(getGraphics(), this);
	}

	public void displayGoal(String message) {
		Graphics g = getGraphics();
		g.setColor(Color.YELLOW);
		g.drawString(message, messageIncOne, messageIncTwo);
		messageIncTwo += 25;
	}

	    public static void main(String[] args) {
	    	Match match = new Match();
	        JFrame window = new JFrame("Ryan's Football Game");
	        GamePanel gamePanel = new GamePanel("main", match);
	        window.add(gamePanel);
	        window.pack();
	        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        window.setLocationRelativeTo(null);
	        window.setVisible(true);
	    }
}