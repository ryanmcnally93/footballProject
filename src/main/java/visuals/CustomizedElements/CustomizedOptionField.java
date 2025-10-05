package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CustomizedOptionField extends JComponent {

	private List<String> options;
	private Color charcoal = new Color(0x36, 0x45, 0x4F);
	private int currentOption;
	private CustomizedButton left, right;
    private int fontSize;
    private boolean borderRequired;
    private Runnable onClickDone;

	public CustomizedOptionField(List<String> options, int width, int offset, int fontSize, boolean borderRequired) {
		this.options = options;
        this.fontSize = fontSize;
        this.borderRequired = borderRequired;
		setLayout(null);
		currentOption = 0;

		// ∧∨<>
		left = new CustomizedButton("<");
        right = new CustomizedButton(">");

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                left.setBounds(offset, (getHeight() - fontSize) / 2 , fontSize, fontSize);
                right.setBounds(width - (fontSize + offset), (getHeight() - fontSize) / 2 , fontSize, fontSize);
            }
        });

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
            if (!isAncestorOf(left)) {
                add(left);
            }
			if (currentOption == (options.size() - 1)) {
				remove(right);
			}
			repaint();
		}
	}

	public void moveBackward() {
		if (currentOption != 0) {
			currentOption--;
            if (!isAncestorOf(right)) {
                add(right);
            }
			if (currentOption == 0) {
				remove(left);
			}
			repaint();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (borderRequired) {
            // Set Composite to 0.5 transparency, then draw background
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

            // Set the composite back to normal before drawing border and text
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

            // Draw border
            g2.setColor(getForeground());
            g2.setStroke(new BasicStroke(2));  // Set border thickness
            g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 10, 10);
        }

		// Draw text
		g2.setFont(GamePanel.getBebasNeueFontWithSize(fontSize));
		g2.setColor(getForeground());

		FontMetrics fm = g2.getFontMetrics();
		int textWidth = fm.stringWidth(options.get(currentOption));
		int x = (getWidth() - textWidth) / 2;

		int textHeight = fm.getAscent();
		int gapAbove = (getHeight() - textHeight) / 2;
        int y = gapAbove + textHeight - 1;

		g2.drawString(options.get(currentOption), x, y);

		g2.dispose();
		super.paintComponent(g);
	}

	public String getTeamName() {
		return options.get(currentOption);
	}

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void removeButtons() {
        removeAll();
        revalidate();
        repaint();
    }

    public void addButtons() {
        if (currentOption < options.size() - 1) {
            add(right);
        }
        if (currentOption != 0) {
            add(left);
        }
        revalidate();
        repaint();
    }
}
