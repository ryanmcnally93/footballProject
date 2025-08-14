package main.java.visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CustomizedOptionField extends JComponent {

	private List<String> options;
	private Color charcoal = new Color(0x36, 0x45, 0x4F);
	private int currentOption;
	private CustomizedButton left, right;

	public CustomizedOptionField(List<String> options, int width) {
		this.options = options;
		setLayout(null);
		currentOption = 0;

		// ∧∨<>
		left = new CustomizedButton("<");
		left.setBounds(10, (getHeight() + 20) /2, 30, 30);

		right = new CustomizedButton(">");
		right.setBounds(width - 40, (getHeight() + 20) /2, 30, 30);
		add(right);

		right.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				moveForward();
			}
		});

		left.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				moveBackward();
			}
		});

		revalidate();
		repaint();
	}

	public void moveForward() {
		if (currentOption < (options.size() - 1)) {
			currentOption++;
			if (currentOption < (options.size() - 1)) {
				if (!isAncestorOf(left)) {
					add(left);
				}
			} else {
				remove(right);
			}
			repaint();
		}
	}

	public void moveBackward() {
		if (currentOption != 0) {
			currentOption--;
			if (currentOption != 0) {
				if (!isAncestorOf(right)) {
					add(right);
				}
			} else {
				remove(left);
			}
			repaint();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Set Composite to 0.5 transparency, then draw background
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g2.setColor(Color.WHITE);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

		// Set the composite back to normal before drawing border and text
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

		// Draw border
		g2.setColor(GamePanel.getCharcoal());
		g2.setStroke(new BasicStroke(2));  // Set border thickness
		g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 10, 10);

		// Draw text
		g2.setFont(GamePanel.getBebasNeueFont());
		g2.setColor(GamePanel.getCharcoal());

		FontMetrics fm = g2.getFontMetrics();
		int textWidth = fm.stringWidth(options.get(currentOption));
		int x = (getWidth() - textWidth) /2;

		int textHeight = fm.getAscent();
		int y = (getHeight() - textHeight) + 13;

		g2.drawString(options.get(currentOption), x,y);

		g2.dispose();
		super.paintComponent(g);
	}

	public String getTeamName() {
		return options.get(currentOption);
	}
	
}
