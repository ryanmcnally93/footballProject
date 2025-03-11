package visuals.CustomizedElements;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomizedButton extends JButton {

	private static final long serialVersionUID = 2300144898005916497L;

	public CustomizedButton(String text) {
		super(text);
		setFont(GamePanel.getBebasNeueFont());
		setMargin(new Insets(7, 0, 0, 0));
		init();
	}

	public CustomizedButton(String text, int size) {
		super(text);
		setFont(GamePanel.getBebasNeueFontWithSize((float) size));
		setMargin(new Insets(size/4, 0, 0, 0));
		init();
	}

	public void init() {
		setForeground(GamePanel.getCharcoal()); // Dark text color
		setBackground(Color.WHITE);
		setFocusPainted(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));

		if (getText().equals("<") || getText().equals(">") || getText().equals("∧") || getText().equals("∨")) {
			setBorder(new EmptyBorder(4, 0,0,0));
			setContentAreaFilled(false);
		} else {
			setBorderPainted(false);
		}

		addHoverEffect();
	}

	private void addHoverEffect() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(GamePanel.getCharcoal());
				setForeground(Color.WHITE);
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(Color.WHITE);
				setForeground(GamePanel.getCharcoal());
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw background
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

		// Draw border
		g2.setColor(GamePanel.getCharcoal());
		g2.setStroke(new BasicStroke(2));  // Set border thickness
		g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 10, 10);

		g2.dispose();
		super.paintComponent(g);
	}
	
}
