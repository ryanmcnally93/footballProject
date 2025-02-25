package visuals.CustomizedElements;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;

public abstract class GamePanel extends JPanel {
    private static final long serialVersionUID = -8911764479146802449L;
    private Box east;
    private Box west;

    public GamePanel() {};

    protected void addGameMouseListener(MouseAdapter mouseAdapter) {
        addMouseListener(mouseAdapter);
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