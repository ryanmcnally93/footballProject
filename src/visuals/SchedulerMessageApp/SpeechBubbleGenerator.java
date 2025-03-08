package visuals.SchedulerMessageApp;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpeechBubbleGenerator {
    private static final int MAX_WIDTH = 400;
    private static boolean received;

    public static JLabel createSpeechBubbleReceived(String message) {
        return createSpeechBubble(message, true);
    }
    public static JLabel createSpeechBubbleSent(String message) {
        return createSpeechBubble(message, false);
    }

    public static JLabel createSpeechBubble(String message, boolean isReceivedMessage) {
        received = isReceivedMessage;
        int padding = 20;
        int fontSize = 16;
        Font font = new Font("Arial", Font.PLAIN, fontSize);

        // Create a temporary image to measure text size
        BufferedImage tempImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D tempG = tempImg.createGraphics();
        tempG.setFont(font);
        FontMetrics fm = tempG.getFontMetrics();

        // Wrap text to fit within MAX_WIDTH
        List<String> lines = wrapText(message, fm, MAX_WIDTH - 2 * padding);
        int textHeight = fm.getHeight() * lines.size();
        tempG.dispose();

        int bubbleWidth = MAX_WIDTH;
        int bubbleHeight = textHeight + 2 * padding + 20; // Additional space for the flick

        BufferedImage image = new BufferedImage(bubbleWidth, bubbleHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw speech bubble
        if (received) {
            g2d.setColor(new Color(204, 255, 204)); // Light green bubble
            g2d.fill(new RoundRectangle2D.Float(0, 0, bubbleWidth, bubbleHeight - 20, 20, 20));

            Path2D.Float flick = new Path2D.Float();
            flick.moveTo(bubbleWidth - 30, bubbleHeight - 20);
            flick.lineTo(bubbleWidth, bubbleHeight);
            flick.lineTo(bubbleWidth - 10, bubbleHeight - 20);
            flick.closePath();
            g2d.fill(flick);
        } else {
            g2d.setColor(new Color(173, 216, 230)); // Light blue bubble
            g2d.fill(new RoundRectangle2D.Float(0, 0, bubbleWidth, bubbleHeight - 20, 20, 20));

            Path2D.Float flick = new Path2D.Float();
            flick.moveTo(30, bubbleHeight - 20);
            flick.lineTo(0, bubbleHeight);
            flick.lineTo(10, bubbleHeight - 20);
            flick.closePath();
            g2d.fill(flick);
        }

        // Draw wrapped text
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        int y = padding + fm.getAscent();
        for (String line : lines) {
            g2d.drawString(line, padding, y);
            y += fm.getHeight();
        }

        g2d.dispose();

        ImageIcon icon = new ImageIcon(image);

        return new JLabel(icon, SwingConstants.CENTER);
    }

    private static List<String> wrapText(String text, FontMetrics fm, int maxWidth) {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            if (fm.stringWidth(currentLine + word) > maxWidth) {
                lines.add(currentLine.toString().trim());
                currentLine = new StringBuilder(word + " ");
            } else {
                currentLine.append(word).append(" ");
            }
        }
        if (!currentLine.isEmpty()) {
            lines.add(currentLine.toString().trim());
        }
        return lines;
    }
}