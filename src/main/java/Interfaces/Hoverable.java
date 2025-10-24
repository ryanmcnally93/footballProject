package Interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static visuals.CustomizedElements.GamePanel.*;

public interface Hoverable {

    // Default color palette and geometry
    default Color getPrimaryColor() {
        return new Color(245, 245, 245);
    }

    default Color getSecondaryColor() {
        return getCharcoal();
    }

    // Selection handling (optional to override)
    default boolean isSelected() {
        return false;
    }

    default void setSelected(boolean selected) {
    }

    // Called when hover enters/exits — can be overridden
    default void onHoverEnter(JComponent c) {
    }

    default void onHoverExit(JComponent c) {
    }

    // Draw rounded background and border — reusable across buttons/panels
    default void paintHoverableBackground(Graphics g, JComponent c, Color background, Color border, int arcWidth, int arcHeight) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(background);
        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), arcWidth, arcHeight);
        g2.setColor(border);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(1, 1, c.getWidth() - 2, c.getHeight() - 2, arcWidth, arcHeight);
        g2.dispose();
    }

    // Core hover behavior
    default void addHoverEffect(JComponent component, Runnable repaintTrigger) {
        Color primary = getPrimaryColor();
        Color secondary = getSecondaryColor();

        component.addMouseListener(new MouseAdapter() {
            Color hoverColor = secondary;

            @Override
            public void mouseEntered(MouseEvent e) {
                if (isSelected()) return;
                onHoverEnter(component);
                component.setBackground(secondary);
                component.setForeground(primary);
                hoverColor = primary;
                component.revalidate();
                component.repaint();
                if (repaintTrigger != null) repaintTrigger.run();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (isSelected()) return;
                onHoverExit(component);
                component.setBackground(primary);
                component.setForeground(secondary);
                hoverColor = secondary;
                component.revalidate();
                component.repaint();
                if (repaintTrigger != null) repaintTrigger.run();
            }
        });
    }

    // Optional helpers for image-based hover swaps
    default void addHoverEffectWithImageSwap(AbstractButton button, Runnable repaintTrigger) {
        Color primary = getPrimaryColor();
        Color secondary = getSecondaryColor();
        ImageIcon originalIcon = (ImageIcon) button.getIcon();

        button.addMouseListener(new MouseAdapter() {
            ImageIcon currentIcon = originalIcon;

            @Override
            public void mouseEntered(MouseEvent e) {
                if (isSelected()) return;
                onHoverEnter(button);
                currentIcon = getOppositeImage(currentIcon);
                button.setIcon(currentIcon);
                button.setForeground(primary);
                button.setBackground(secondary);
                button.revalidate();
                button.repaint();
                if (repaintTrigger != null) repaintTrigger.run();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (isSelected()) return;
                onHoverExit(button);
                currentIcon = getOppositeImage(currentIcon);
                button.setIcon(currentIcon);
                button.setForeground(secondary);
                button.setBackground(primary);
                button.revalidate();
                button.repaint();
                if (repaintTrigger != null) repaintTrigger.run();
            }
        });
    }
}