package visuals;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MatchWatch extends MatchFrames {
    private static final long serialVersionUID = 5937268249853937276L;
	private static final int SQUARE_X = 100;
    private static final int SQUARE_Y = 100;
    private static final int SQUARE_SIZE = 50;
    private CardLayout layout;
    private JPanel pages;

    public MatchWatch(CardLayout layout, JPanel pages) {
    	super(layout, pages);
        addGameMouseListener(new MouseAdapter() {
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.fillRect(SQUARE_X, SQUARE_Y, SQUARE_SIZE, SQUARE_SIZE);
    }

	public void handleClick() {
		match.startMatch(getGraphics(), new MatchEvents(layout, pages));
	}
}