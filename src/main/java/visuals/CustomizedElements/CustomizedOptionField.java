package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.function.Consumer;

public class CustomizedOptionField extends JComponent {

	private List<String> options;
	private Color charcoal = new Color(0x36, 0x45, 0x4F);
	private int currentOption;
	private CustomizedButton left, right;
    private int fontSize;
    private boolean borderRequired;
    private Runnable onClickLeft, onClickRight;
    private Consumer<String> onSelectionChange;

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
            if (onClickRight != null) {
                onClickRight.run();
            }
            if (onSelectionChange != null) {
                triggerUpdate();
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
            if (onClickLeft != null) {
                onClickLeft.run();
            }
            if (onSelectionChange != null) {
                triggerUpdate();
            }
            repaint();
		}
	}

    public void triggerUpdate() {
        if (onSelectionChange != null && (currentOption + 1) <= options.size()) {
            onSelectionChange.accept(options.get(currentOption));
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
		g2.setFont(GamePanel.getBebasNeueFontWithSizeBold(fontSize));
		g2.setColor(getForeground());

        if (currentOption + 1 <= options.size()) {
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(options.get(currentOption));
            int x = (getWidth() - textWidth) / 2;

            int textHeight = fm.getAscent();
            int gapAbove = (getHeight() - textHeight) / 2;
            int y = gapAbove + textHeight - 1;

            g2.drawString(options.get(currentOption), x, y);
        }
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

    public void setOnSelectionChange(Consumer<String> listener) {
        this.onSelectionChange = listener;
    }

    public Consumer<String> getOnSelectionChange() {
        return this.onSelectionChange;
    }

    public Runnable getOnClickLeft() {
        return onClickLeft;
    }

    public void setOnClickLeft(Runnable onClickLeft) {
        this.onClickLeft = onClickLeft;
    }

    public Runnable getOnClickRight() {
        return onClickRight;
    }

    public void setOnClickRight(Runnable onClickRight) {
        this.onClickRight = onClickRight;
    }

    public int getCurrentOption() {
        return currentOption;
    }

    public void setCurrentOption(int currentOption) {
        this.currentOption = currentOption;
    }
}
