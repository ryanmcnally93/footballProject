package visuals.CustomizedElements;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class CustomizedTextField extends JTextField {

    public CustomizedTextField(int columns, int charLimit) {
        super(columns);
        setOpaque(false); // Allows custom painting of background
        setFont(GamePanel.getBebasNeueFont());
        setForeground(new Color(0x36, 0x45, 0x4F)); // Dark text color
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 5, 15, 5));
        fixCaretHeight();
        setDocument(new LimitedDocument(charLimit)); // Set custom document
    }

    private void fixCaretHeight() {
        DefaultCaret caret = new DefaultCaret() {
            @Override
            public void paint(Graphics g) {
                if (isVisible()) {
                    try {
                        JTextField textField = (JTextField) getComponent();
                        Graphics2D g2d = (Graphics2D) g.create();

                        // Get caret position
                        Rectangle caretRect = textField.modelToView(getDot());

                        // Get font metrics for correct text height
                        FontMetrics fm = textField.getFontMetrics(textField.getFont());
                        int textHeight = fm.getAscent(); // Get actual text height

                        // Set caret properties
                        g2d.setColor(getComponent().getCaretColor());
                        g2d.fillRect(caretRect.x, (textField.getHeight() - textHeight) / 2, 2, textHeight);

                        g2d.dispose();
                    } catch (Exception ignored) {}
                }
            }
        };
        caret.setBlinkRate(getCaret().getBlinkRate()); // Maintain blinking speed
        setCaret(caret);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Set transparency for the background (40% opacity white)
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.dispose();
        super.paintComponent(g);
    }

    private static class LimitedDocument extends PlainDocument {
        private final int limit;

        public LimitedDocument(int limit) {
            this.limit = limit;
        }

        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException, BadLocationException {
            if (str != null && (getLength() + str.length() <= limit)) {
                super.insertString(offset, str, attr);
            }
        }
    }

}