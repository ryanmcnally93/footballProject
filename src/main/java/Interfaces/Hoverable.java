package Interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static visuals.CustomizedElements.GamePanel.*;

public interface Hoverable {

    // Default color palette and geometry
    default Color getPrimaryColor() {
        return getOffWhite();
    }

    default Color getSecondaryColor() {
        return getCharcoal();
    }

    default Color getThirdColor() {
        return getDarkGrey();
    }

    // Selection handling (optional to override)
    default boolean isSelected() {
        return false;
    }

    default void setSelected(boolean selected) {
    }

    // Called when hover enters/exits — can be overridden
    default void onHoverEnter(JComponent c) {}

    default void onHoverExit(JComponent c) {}

    // Draw rounded background and border — reusable across buttons/panels
    default void paintHoverableBackground(Graphics g, JComponent c, Color background, int arcWidth, int arcHeight, boolean borderWanted, boolean fillWanted) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (fillWanted) {
            g2.setColor(background);
            g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), arcWidth, arcHeight);
        }
        if (borderWanted) {
            Color border = (Color) c.getClientProperty("borderColor");
            g2.setColor(border != null ? border : getDarkGrey());
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(1, 1, c.getWidth() - 2, c.getHeight() - 2, arcWidth, arcHeight);
        }
        g2.dispose();
    }

    // Core hover behavior
    default void addHoverEffect(JComponent component, Runnable repaintTrigger) {
        Color primary = getPrimaryColor();
        Color secondary = getSecondaryColor();
        Color thirdly = getThirdColor();

        component.addMouseListener(new MouseAdapter() {
            Color hoverColor = secondary;

            @Override
            public void mouseEntered(MouseEvent e) {
                if (isSelected()) return;
                onHoverEnter(component);
                component.setBackground(thirdly);
                for (Component childComponent : component.getComponents()) {
                    childComponent.setForeground(primary);
                }
                component.setForeground(primary);
                for (Component child : component.getComponents()) {
                    if (child instanceof JLabel label) label.setForeground(primary);
                }
                component.putClientProperty("borderColor", getDarkGrey());
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
                for (Component childComponent : component.getComponents()) {
                    childComponent.setForeground(secondary);
                }
                component.setForeground(secondary);
                for (Component child : component.getComponents()) {
                    if (child instanceof JLabel label) label.setForeground(secondary);
                }
                component.putClientProperty("borderColor", getCharcoal());
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