package visuals.CustomizedElements;
import visuals.MatchPages.MatchFrames;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;

public abstract class GamePanel extends JPanel {
    private static final long serialVersionUID = -8911764479146802449L;
    private Box east;
    private Box west;
    private static final String RIGHT = "Next Page";
    private static final String LEFT = "Previous Page";

    public GamePanel() {
        addKeyListeners();
    };

    protected void addGameMouseListener(MouseAdapter mouseAdapter) {
        addMouseListener(mouseAdapter);
    }

    public static Color getCharcoal() {
        return new Color(0x36, 0x45, 0x4F);
    }

    public static Font getBebasNeueFont() {
        try {
            File fontFile = new File("./src/visuals/Fonts/BebasNeue-Regular.ttf");
            return Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.BOLD, 30f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Font getBebasNeueFontWithSize(float size) {
        try {
            File fontFile = new File("./src/visuals/Fonts/BebasNeue-Regular.ttf");
            return Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.BOLD, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void appendEastAndWest(JPanel mainPanel){
        appendEastAndWest(mainPanel, 100);
    }

    public void appendEastAndWest(JPanel mainPanel, int width){
        west = Box.createHorizontalBox();
        west.setPreferredSize(new Dimension(width,200));
        mainPanel.add(west, BorderLayout.WEST);
        east = Box.createHorizontalBox();
        east.setPreferredSize(new Dimension(width,200));
        mainPanel.add(east, BorderLayout.EAST);
    }

    public JScrollPane makeScroller(Box container){
        JScrollPane scroller = new JScrollPane(container);
        scroller.getViewport().setBackground(Color.LIGHT_GRAY);
        scroller.setBorder(null);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        return scroller;
    }

    public void setPermanentWidthAndHeight(JPanel box, int width, int height){
        box.setPreferredSize(new Dimension(width, height));
        box.setMinimumSize(new Dimension(width, height));
        box.setMaximumSize(new Dimension(width, height));
    }

    public void setPermanentWidthAndHeight(JButton box, int width, int height){
        box.setPreferredSize(new Dimension(width, height));
        box.setMinimumSize(new Dimension(width, height));
        box.setMaximumSize(new Dimension(width, height));
    }

    public void setPermanentWidthAndHeight(Box box, int width, int height){
        box.setPreferredSize(new Dimension(width, height));
        box.setMinimumSize(new Dimension(width, height));
        box.setMaximumSize(new Dimension(width, height));
    }

    public void setPermanentWidthAndHeight(JLabel box, int width, int height){
        box.setPreferredSize(new Dimension(width, height));
        box.setMinimumSize(new Dimension(width, height));
        box.setMaximumSize(new Dimension(width, height));
    }

    public void setPermanentWidthAndHeight(JLayeredPane box, int width, int height){
        box.setPreferredSize(new Dimension(width, height));
        box.setMinimumSize(new Dimension(width, height));
        box.setMaximumSize(new Dimension(width, height));
    }

    public void setPermanentWidth(JButton box, int width){
        box.setPreferredSize(new Dimension(width, box.getPreferredSize().height));
        box.setMinimumSize(new Dimension(width, box.getMinimumSize().height));
        box.setMaximumSize(new Dimension(width, box.getMaximumSize().height));
    }

    public void setPermanentWidthAndHeight(JComponent box, int width, int height){
        box.setPreferredSize(new Dimension(width, height));
        box.setMinimumSize(new Dimension(width, height));
        box.setMaximumSize(new Dimension(width, height));
    }

    public void setPermanentWidth(JPanel box, int width){
        box.setPreferredSize(new Dimension(width, box.getPreferredSize().height));
        box.setMinimumSize(new Dimension(width, box.getMinimumSize().height));
        box.setMaximumSize(new Dimension(width, box.getMaximumSize().height));
    }

    public void setPermanentWidth(JLabel box, int width){
        box.setPreferredSize(new Dimension(width, box.getPreferredSize().height));
        box.setMinimumSize(new Dimension(width, box.getMinimumSize().height));
        box.setMaximumSize(new Dimension(width, box.getMaximumSize().height));
    }

    public void setPermanentExtendedHeight(JPanel box, int height){
        box.setPreferredSize(new Dimension(box.getPreferredSize().width, box.getPreferredSize().height + height));
        box.setMinimumSize(new Dimension(box.getMinimumSize().width, box.getMinimumSize().height + height));
        box.setMaximumSize(new Dimension(box.getMaximumSize().width, box.getMaximumSize().height + height));
    }

    public void setPermanentHeight(JPanel box, int height){
        box.setPreferredSize(new Dimension(box.getPreferredSize().width, height));
        box.setMinimumSize(new Dimension(box.getMinimumSize().width, height));
        box.setMaximumSize(new Dimension(box.getMaximumSize().width, height));
    }

    public void setPermanentHeight(JLabel box, int height){
        box.setPreferredSize(new Dimension(box.getPreferredSize().width, height));
        box.setMinimumSize(new Dimension(box.getMinimumSize().width, height));
        box.setMaximumSize(new Dimension(box.getMaximumSize().width, height));
    }

    public void setPermanentHeight(Box box, int height){
        box.setPreferredSize(new Dimension(box.getPreferredSize().width, height));
        box.setMinimumSize(new Dimension(box.getMinimumSize().width, height));
        box.setMaximumSize(new Dimension(box.getMaximumSize().width, height));
    }

    public void setPermanentExtendedHeight(Box box, int height){
        box.setPreferredSize(new Dimension(box.getPreferredSize().width, box.getPreferredSize().height + height));
        box.setMinimumSize(new Dimension(box.getMinimumSize().width, box.getMinimumSize().height + height));
        box.setMaximumSize(new Dimension(box.getMaximumSize().width, box.getMaximumSize().height + height));
    }

    public void setPermanentWidth(Box box, int width){
        box.setPreferredSize(new Dimension(width, box.getPreferredSize().height));
        box.setMinimumSize(new Dimension(width, box.getMinimumSize().height));
        box.setMaximumSize(new Dimension(width, box.getMaximumSize().height));
    }

    public void addKeyListeners() {
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("LEFT"), LEFT);
        actionMap.put(LEFT, getLeftClickAction());

        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), RIGHT);
        actionMap.put(RIGHT, getRightClickAction());
    }

    protected AbstractAction getRightClickAction() {
        return new RightClick();
    }

    public class RightClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Default: Do nothing
        }
    }

    protected AbstractAction getLeftClickAction() {
        return new LeftClick();
    }

    public class LeftClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Default: Do nothing
        }
    }

    public void removeKeyListeners() {
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.remove(KeyStroke.getKeyStroke("LEFT"));
        actionMap.remove(LEFT);

        inputMap.remove(KeyStroke.getKeyStroke("RIGHT"));
        actionMap.remove(RIGHT);
    }

    public Box getWest() {
        return west;
    }

    public void setWest(Box west) {
        this.west = west;
    }

    public Box getEast() {
        return east;
    }

    public void setEast(Box east) {
        this.east = east;
    }
}