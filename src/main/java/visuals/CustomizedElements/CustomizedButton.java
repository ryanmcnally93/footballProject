package visuals.CustomizedElements;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static visuals.CustomizedElements.GamePanel.*;

public class CustomizedButton extends JButton {

	private boolean hasImage, hasHoverEffect;
	private ImageIcon icon;
	private Color primaryColor = Color.WHITE;
	private Color secondaryColor = getCharcoal();
	private Color hoverColor;
    private int arcWidth = 10;
    private int arcHeight = 10;
    private boolean selected = false;

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

	public CustomizedButton(ImageIcon image) {
		super(image);
		hasImage = true;
		icon = image;
		setFont(GamePanel.getBebasNeueFont());
		setMargin(new Insets(7, 0, 0, 0));
		init();
	}

	public void init() {
		setForeground(secondaryColor); // Dark text color
		setBackground(primaryColor);
		hoverColor = secondaryColor;
		setFocusPainted(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));

		if (getText().equals("<") || getText().equals(">")) {
			setBorder(new EmptyBorder(4, 0, 0, 0));
			setContentAreaFilled(false);
		} else if (hasImage) {
			setBorder(new EmptyBorder(0, 0, 0, 0));
			setContentAreaFilled(false);
		} else {
			setBorderPainted(false);
		}

		if (!hasHoverEffect) {
			addHoverEffect();
		}
	}

	private void addHoverEffect() {
		hasHoverEffect = true;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
                mouseEnteredMethod();
			}

			@Override
			public void mouseExited(MouseEvent e) {
                hoverColor = secondaryColor;
                revalidate();
                repaint();
                if (isSelected()) {
                    return;
                }
				if (hasImage) {
					ImageIcon newIcon = getOppositeImage(icon);
					setIcon(newIcon);
					icon = newIcon;
				} else {
					setForeground(secondaryColor);
				}
				setBackground(primaryColor);
                childMouseExited();
				revalidate();
				repaint();
			}
		});
	}

    public void mouseEnteredMethod() {
        hoverColor = primaryColor;
        revalidate();
        repaint();
        if (isSelected()) {
            return;
        }
        if (hasImage) {
            ImageIcon newIcon = getOppositeImage(icon);
            setIcon(newIcon);
            icon = newIcon;
        } else {
            setForeground(primaryColor);
        }
        setBackground(secondaryColor);
        childMouseEntered();
        revalidate();
        repaint();
    }

    protected void childMouseExited() {}

    protected void childMouseEntered() {}

    @Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g); // first
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw background
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

		// Draw border
		g2.setColor(hoverColor);
		g2.setStroke(new BasicStroke(2));  // Set border thickness
		g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, arcWidth, arcHeight);

		g2.dispose();
		super.paintComponent(g);
	}

	public void setOtherIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public ImageIcon getOtherIcon() {
		return icon;
	}

	public void triggerColorReverse() {
		ImageIcon oppositeImage = getOppositeImage((ImageIcon) getIcon());
		icon = oppositeImage;
		setIcon(oppositeImage);
		this.init();
		this.revalidate();
		this.repaint();
	}

    public int getArcWidth() {
        return arcWidth;
    }

    public void setArcWidth(int arcWidth) {
        this.arcWidth = arcWidth;
    }

    public int getArcHeight() {
        return arcHeight;
    }

    public void setArcHeight(int arcHeight) {
        this.arcHeight = arcHeight;
    }

    public Color getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public void setSelected(boolean bool) {
        selected = bool;
    }

    public boolean isSelected() {
        return selected;
    }
}
