package visuals.CustomizedElements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TacticsPanel extends JPanel {

    private Polygon shape;
    private JLabel footballLabel;
    private boolean isHovered;

    public TacticsPanel() {
        setLayout(null);
        BufferedImage image = null;
        ImageIcon football = null;
        try {
            image = ImageIO.read(new File("./src/main/java/visuals/images/ratings_page_goal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(image != null) {
            Image scaledImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            BufferedImage bufferedScaledImage = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedScaledImage.createGraphics();
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();
            football = new ImageIcon(bufferedScaledImage);
        }

        footballLabel = new JLabel(football);
        footballLabel.setBounds(121,85,30,30);

        setOpaque(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (shape.contains(e.getPoint())) {
                    isHovered = true;
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    addFootballIcon();
                } else {
                    isHovered = false;
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    removeFootballIcon();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                removeFootballIcon();
            }
        });

        // Update the cursor as we move around the shape
        // As we want to detect when we've travelled from the chair to the button
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (shape.contains(e.getPoint())) {
                    isHovered = true;
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    addFootballIcon();
                } else {
                    isHovered = false;
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    removeFootballIcon();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set up the center and radius for the circle
        int width = 272;
        int height = 188;

        // Calculate the 12 points
        int[] xPoints = new int[12];
        int[] yPoints = new int[12];

        int x = getWidth() / 2 - width / 2;
        int y = getHeight() / 2 - height / 2;

        xPoints[0] = x; yPoints[0] = y; // Top Left
        xPoints[1] = x + width; yPoints[1] = y; // Top right
        xPoints[2] = x + width; yPoints[2] = y + height; // Bottom Right
        xPoints[3] = (x + width) - 41; yPoints[3] = y + height; // Start of right side of chair
        xPoints[4] = (x + width) - 50; yPoints[4] = (y + height) - 40; // Up a bit
        xPoints[5] = (x + width) - 84; yPoints[5] = (y + height) - 40; // Inner Right Crease
        xPoints[6] = (x + width) - 84; yPoints[6] = (y + height) - 69; // Top Right of Chair
        xPoints[7] = (x + width) - 196; yPoints[7] = (y + height) - 69; // Top Left of Chair
        xPoints[8] = (x + width) - 198; yPoints[8] = (y + height) - 40; // Inner Left Crease
        xPoints[9] = (x + width) - 230; yPoints[9] = (y + height) - 40; // Left a bit
        xPoints[10] = (x + width) - 240; yPoints[10] = y + height; // Start of left side of chair
        xPoints[11] = x; yPoints[11] = y + height; // Bottom Left

        shape = new Polygon(xPoints, yPoints, 12);

        // Draw a border when hovered too
        if (isHovered) {
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawPolygon(xPoints, yPoints, 12);
        }
    }

    public Polygon getShape() {
        return shape;
    }

    public void setShape(Polygon shape) {
        this.shape = shape;
    }

    public void addFootballIcon() {
        if (!isAncestorOf(footballLabel)) {
            add(footballLabel);
            revalidate();
            repaint();
        }
    }

    public void removeFootballIcon() {
        if (isAncestorOf(footballLabel)) {
            remove(footballLabel);
            revalidate();
            repaint();
        }
    }

    public void setHovered(boolean hovered) {
        isHovered = hovered;
    }
}
