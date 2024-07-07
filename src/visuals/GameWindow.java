package visuals;
import javax.swing.JFrame;
import javax.swing.JPanel;
import people.Footballer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameWindow extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int SQUARE_X = 50;
    private static final int SQUARE_Y = 50;
    private static final int SQUARE_SIZE = 100;
    private static int messageIncOne = 250;
    private static int messageIncTwo = 30;
    

    public GameWindow() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                if (mouseX >= SQUARE_X && mouseX <= SQUARE_X + SQUARE_SIZE &&
                    mouseY >= SQUARE_Y && mouseY <= SQUARE_Y + SQUARE_SIZE) {
//                    startMatch(getGraphics());
                	handleClick();
                    System.out.println("Red square clicked!");
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        initialPage(g);
    }

    public void initialPage(Graphics g) {
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
    	startMatch(getGraphics());
    }
    
    public void displayGoal(String message, Graphics g) {
    	g.setColor(Color.YELLOW);
		g.drawString(message, messageIncOne, messageIncTwo);
		messageIncTwo += 30;
    }
    
    private void startMatch(Graphics g) {
    	System.out.println("You are starting the match");
    	Footballer jesus = new Footballer("Gabriel Jesus", 31, 30000, 180, 30, 100, "Arsenal", "Striker");
		Footballer trossard = new Footballer("Leandro Trossard", 31, 30000, 200, 40, 100, "Arsenal", "Winger");
		Footballer saka = new Footballer("Bukayo Saka", 31, 30000, 180, 65, 100, "Arsenal", "Winger");
		Footballer partey = new Footballer("Thomas Partey", 31, 30000, 90, 180, 100, "Arsenal", "Midfielder");
		Footballer odegaard = new Footballer("Martin Odegaard", 31, 30000, 180, 120, 100, "Arsenal", "Midfielder");
		Footballer rice = new Footballer("Declan Rice", 31, 30000, 110, 190, 100, "Arsenal", "Midfielder");
		Footballer tomiyasu = new Footballer("Takehiro Tomiyasu", 31, 30000, 45, 215, 100, "Arsenal", "Defender");
		Footballer saliba = new Footballer("William Saliba", 31, 30000, 45, 260, 100, "Arsenal", "Defender");
		Footballer gabriel = new Footballer("Gabriel Magalhaes", 31, 30000, 45, 245, 100, "Arsenal", "Defender");
		Footballer white = new Footballer("Ben White", 31, 30000, 75, 215, 100, "Arsenal", "Defender");
		
		Footballer johnson = new Footballer("Brennan Johnson", 31, 30000, 180, 30, 100, "Tottenham", "Striker");
		Footballer son = new Footballer("Heung-Min Son", 31, 30000, 200, 40, 100, "Tottenham", "Winger");
		Footballer kulusevski = new Footballer("Dejan Kulusevski", 31, 30000, 180, 65, 100, "Tottenham", "Winger");
		Footballer maddison = new Footballer("James Maddison", 31, 30000, 90, 180, 100, "Tottenham", "Midfielder");
		Footballer bissouma = new Footballer("Yves Bissouma", 31, 30000, 180, 120, 100, "Tottenham", "Midfielder");
		Footballer sarr = new Footballer("Pape Matar Sarr", 31, 30000, 110, 190, 100, "Tottenham", "Midfielder");
		Footballer udogie = new Footballer("Destiny Udogie", 31, 30000, 45, 215, 100, "Tottenham", "Defender");
		Footballer vanDeVen = new Footballer("Micky van de Ven", 31, 30000, 45, 260, 100, "Tottenham", "Defender");
		Footballer romero = new Footballer("Cristian Romero", 31, 30000, 45, 245, 100, "Tottenham", "Defender");
		Footballer porro = new Footballer("Guglielmo Vicario", 31, 30000, 75, 215, 100, "Tottenham", "Defender");
		
		ArrayList<Footballer> arsenal = new ArrayList<Footballer>();
		arsenal.add(jesus);
		arsenal.add(trossard);
		arsenal.add(saka);
		arsenal.add(odegaard);
		arsenal.add(partey);
		arsenal.add(rice);
		arsenal.add(tomiyasu);
		arsenal.add(gabriel);
		arsenal.add(saliba);
		arsenal.add(white);
		ArrayList<Footballer> tottenham = new ArrayList<Footballer>();
		tottenham.add(johnson);
		tottenham.add(son);
		tottenham.add(kulusevski);
		tottenham.add(maddison);
		tottenham.add(bissouma);
		tottenham.add(sarr);
		tottenham.add(udogie);
		tottenham.add(vanDeVen);
		tottenham.add(romero);
		tottenham.add(porro);
		
		Match match = new Match(arsenal, tottenham);
		
		match.startRun(jesus, g, this);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Ryan's Football Game");
        GameWindow gamePanel = new GameWindow();
        window.add(gamePanel);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}