package visuals.MainMenuPages.SinglePages;

import visuals.CustomizedElements.*;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class LeftContentRightScrollPagesTemplate extends HeaderFooterAndCardMapTemplate {

    private JPanel mainPanel;
    private RoundedPanel leftBox, rightBox;
    private JScrollPane leftScroller, rightScroller;
    private Image backgroundImage;
    private Timer timer;
    private boolean leftFocused;
    private final Map<String, Rectangle> boundsByDirection = Map.ofEntries(
            Map.entry("down", new Rectangle(0, 0, 155, 20)),
            Map.entry("up",   new Rectangle(0, 0, 155, 20)),
            Map.entry("left", new Rectangle(0, 0, 50, 50)),
            Map.entry("right", new Rectangle(0, 0, 50, 50)),
            Map.entry("leftDown" , new Rectangle(0, 0, 218, 25)),
            Map.entry("leftUp" , new Rectangle(0, 0, 218, 25)),
            Map.entry("downHover", new Rectangle(633, 413, 155, 20)),
            Map.entry("upHover",   new Rectangle(633, 168, 155, 20)),
            Map.entry("leftHover", new Rectangle(5, 272, 50, 50)),
            Map.entry("rightHover", new Rectangle(565, 272, 50, 50)),
            Map.entry("leftDownHover", new Rectangle(201, 440, 218, 25)),
            Map.entry("leftUpHover", new Rectangle(201, 135, 218, 25))
    );

    public LeftContentRightScrollPagesTemplate(Scheduler scheduler) {
        super(scheduler);
        getHeaderPanel().setOpaque(false);
        getHeaderPanel().setBounds(0, 0, 800, 120);
        getFooterPanel().setOpaque(false);
        getFooterPanel().getBackButtonBox().setOpaque(false);
        getFooterPanel().getLeftBlankBox().setOpaque(false);
        backgroundImage = new ImageIcon("./src/main/java/visuals/Images/start_page_main.jpg").getImage();

        JLayeredPane layeredPane = getLayeredPane();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setOpaque(false);

        leftBox = new RoundedPanel(30);
        Color newColor = new Color(217, 217, 217, (int)(0.9 * 255));
        leftBox.setBorderColor(newColor);
        leftBox.setBackground(newColor);
        setPermanentWidthAndHeight(leftBox, 621, 340);

        leftScroller = makeScroller(leftBox);
        leftScroller.setFocusable(false);
        leftScroller.setOpaque(false);
        leftScroller.getViewport().setOpaque(false);
        setPermanentWidthAndHeight(leftScroller, 621, 340);

        rightBox = new RoundedPanel(30);
        rightBox.setBorderColor(newColor);
        rightBox.setBackground(newColor);

        mainPanel.add(leftScroller);
        mainPanel.add(Box.createRigidArea(new Dimension(12, 0)));

        rightScroller = makeScroller(rightBox);
        rightScroller.setFocusable(false);
        rightScroller.setOpaque(false);
        rightScroller.getViewport().setOpaque(false);
        setPermanentWidthAndHeight(rightScroller, 155, 265);
        mainPanel.add(rightScroller);

        mainPanel.setBounds(0, 90, 800, 420);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);

        getFooterPanel().addBackButton();
        updateBackButtonFunctionality();

        setVisible(true);
    }

    protected void addScrollButton(String direction) {
        CustomizedButton scrollButton = getCustomizedButton(direction);

        scrollButton.setBounds(boundsByDirection.get(direction));
        scrollButton.setVisible(false);

        JPanel hoverArea = new JPanel(null);
        hoverArea.setOpaque(false);
        hoverArea.setBounds(boundsByDirection.get(direction + "Hover"));
        hoverArea.add(scrollButton);
        getLayeredPane().add(hoverArea, JLayeredPane.PALETTE_LAYER);

        scrollButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                scrollButton.setVisible(false);
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
            }
        });

        hoverArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!directionEqualsPage(direction)) {
                    JScrollBar vBar;
                    if (direction.equals("leftDown") || direction.equals("leftUp")) {
                        vBar = getLeftScroller().getVerticalScrollBar();
                    } else {
                        vBar = getRightScroller().getVerticalScrollBar();
                    }
                    int max = vBar.getMaximum() - vBar.getVisibleAmount();
                    int value = vBar.getValue();
                    boolean edgeOfPosition = false;
                    if (direction.equals("down") || direction.equals("leftDown")) {
                        edgeOfPosition = value >= max;
                    } else if (direction.equals("up") || direction.equals("leftUp")) {
                        edgeOfPosition = value == 0;
                    }

                    scrollButton.setVisible(!edgeOfPosition);
                }
            }
        });

        if (direction.equals("down") || direction.equals("up") || direction.equals("leftDown") || direction.equals("leftUp")) {
            scrollButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    scrollUpOrDownOnHover(direction, scrollButton);
                }
            });
        } else {
            scrollButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    moveLeftOrRight(direction);
                    scrollButton.setVisible(false);
                }
            });
        }
    }

    protected void scrollUpOrDownOnHover(String direction, CustomizedButton scrollButton) {
        if (timer != null && timer.isRunning()) timer.stop();
        JScrollBar vBar = (direction.contains("left") ? getLeftScroller() : getRightScroller()).getVerticalScrollBar();
        boolean isDown = direction.toLowerCase().contains("down");
        int delay = direction.startsWith("left") ? 5 : 20;

        timer = new Timer(delay, evt -> {
            int newVal = isDown ? Math.min(vBar.getValue() + 2, vBar.getMaximum()) : Math.max(vBar.getValue() - 2, 0);
            vBar.setValue(newVal);

            int max = vBar.getMaximum() - vBar.getVisibleAmount();
            boolean atEdge = isDown ? newVal >= max : newVal == 0;

            if (atEdge) {
                if (scrollButton != null) scrollButton.setVisible(false);
                timer.stop();
            }
        });

        timer.start();
    }

    protected void moveLeftOrRight(String direction) {}

    protected boolean directionEqualsPage(String direction) {
        return true;
    }

    private static CustomizedButton getCustomizedButton(String direction) {
        CustomizedButton button;
        switch (direction) {
            case "down", "up" -> {
                ImageIcon buttonIcon = new ImageIcon("./src/main/java/visuals/Images/" + direction + "_arrow.png", direction.substring(0, 1).toUpperCase() + direction.substring(1) + "Small");
                button = new CustomizedButton(buttonIcon);
            }
            case "leftDown", "leftUp" -> {
                String upOrDown = direction.substring(4);
                ImageIcon buttonIcon = new ImageIcon("./src/main/java/visuals/Images/" + upOrDown + "_arrow.png", upOrDown.substring(0, 1).toUpperCase() + upOrDown.substring(1) + "Small");
                button = new CustomizedButton(buttonIcon);
            }
            case "left" -> button = new CustomizedButton("<");
            case "right" -> button = new CustomizedButton(">");
            default -> throw new IllegalArgumentException("Invalid direction");
        }
        return button;
    }

    public void scrollToButton(JButton button) {
        Rectangle bounds = button.getBounds();
        Point location = SwingUtilities.convertPoint(button.getParent(), bounds.getLocation(), getRightScroller().getViewport());
        bounds.setLocation(location);

        getRightScroller().getViewport().scrollRectToVisible(bounds);
    }

    public void scrollToButton(AbstractStatBar statBar) {
        Rectangle bounds = statBar.getBounds();
        Point location = SwingUtilities.convertPoint(statBar.getParent(), bounds.getLocation(), getRightScroller().getViewport());
        bounds.setLocation(location);

        getLeftScroller().getViewport().scrollRectToVisible(bounds);
    }

    public RoundedPanel getLeftBox() {
        return leftBox;
    }

    public void setLeftBox(RoundedPanel leftBox) {
        this.leftBox = leftBox;
    }

    public RoundedPanel getRightBox() {
        return rightBox;
    }

    public void setRightBox(RoundedPanel rightBox) {
        this.rightBox = rightBox;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.43f)); // 57% transparency
        g2d.setColor(new Color(255, 255, 255)); // Change color if needed (white here)
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }

    @Override
    public boolean isFromScheduler() {
        return true;
    }

    public JScrollPane getLeftScroller() {
        return leftScroller;
    }

    public void setLeftScroller(JScrollPane leftScroller) {
        this.leftScroller = leftScroller;
    }

    public JScrollPane getRightScroller() {
        return rightScroller;
    }

    public void setRightScroller(JScrollPane rightScroller) {
        this.rightScroller = rightScroller;
    }

    protected int getBarHeights(HashMap<String, List<String>> options) {
        int numberOfElements = options.size();
        if (numberOfElements == 0) return 0;

        int totalHeight = 265;
        int padding = 10;
        int gapSize = 5;
        int totalGapHeight = (numberOfElements - 1) * gapSize;

        int availableHeight = totalHeight - padding - totalGapHeight;
        return availableHeight / numberOfElements;
    }

    protected <T> void setupPlayerListOnRight(
            Map<?, T> hashMap,
            BiFunction<T, Integer, String> titleProvider,
            BiFunction<T, String, RightBoxBar> menuBarFactory) {

        getRightBox().setLayout(new BoxLayout(getRightBox(), BoxLayout.Y_AXIS));
        getRightBox().setBorder(new EmptyBorder(5, 5, 5, 5));

        int index = 0;
        for (Map.Entry<?, T> entry : hashMap.entrySet()) {
            T value = entry.getValue();
            String title = titleProvider.apply(value, index);

            RightBoxBar rightBoxBar = menuBarFactory.apply(value, title);
            if (index == 0 && rightBoxBar instanceof OptionBar) {
                ((OptionBar) rightBoxBar).getOptionField().addButtons();
            }
            rightBoxBar.createBar();
            rightBoxBar.setAlignmentX(Component.LEFT_ALIGNMENT);
            rightBoxBar.setFocusable(false);

            rightBoxBar.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    getButtonClickedAction(rightBoxBar);
                }
            });

            getRightBox().add(rightBoxBar);
            getRightBox().add(Box.createVerticalStrut(5));

            index++;
        }

        // Remove that last 5px
        int count = getRightBox().getComponentCount();
        if (count > 0) {
            getRightBox().remove(count - 1);
            getRightBox().revalidate();
            getRightBox().repaint();
        }

        if (getRightBox().getComponentCount() > 0 && getRightBox().getComponent(0) instanceof RightBoxBar firstBar) {
            firstBar.setAsSelected(true);
        }

    }

    private void getButtonClickedAction(RightBoxBar rightBoxBar) {
        // Supply the retrieved index of this bar to 'move'
        moveRightScroller(String.valueOf(Arrays.asList(getRightBox().getComponents()).indexOf(rightBoxBar)));
    }

    protected void moveRightScroller(String direction) {
        Container rightBox = getRightBox();
        int count = rightBox.getComponentCount();

        for (int i = 0; i < count; i++) {
            Component comp = rightBox.getComponent(i);

            if ((direction.equals("left") || direction.equals("right")) && comp instanceof OptionBar optionBar && optionBar.isSelected()) {
                if (direction.equals("right")) {
                    optionBar.getOptionField().moveForward();
                } else {
                    optionBar.getOptionField().moveBackward();
                }
                optionBar.revalidate();
                optionBar.repaint();
                break;
            }

            if (comp instanceof RightBoxBar playerBar && playerBar.isSelected()) {
                // Deselect current
                playerBar.setAsSelected(false);
                playerBar.revalidate();
                playerBar.repaint();

                int nextIndex;
                if (direction.equals("down")) {
                    nextIndex = (i + 2 < count) ? i + 2 : i; // stay at end if no next
                } else if (direction.equals("up")) {
                    nextIndex = (i - 2 >= 0) ? i - 2 : i;
                } else {
                    nextIndex = Integer.parseInt(direction);
                }

                RightBoxBar nextBar = (RightBoxBar) rightBox.getComponent(nextIndex);
                nextBar.setAsSelected(true);
                scrollToButton(nextBar);
                nextBar.revalidate();
                nextBar.repaint();

                // Update reference
                rightBarSelected(nextBar);
                break;
            }
        }
    }

    protected void addFocusListeners(JPanel panel, boolean left) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                leftFocused = left;
                getPage().requestFocusInWindow();
            }
        });

        for (Component comp : panel.getComponents()) {
            if (comp instanceof JPanel childPanel) {
                addFocusListeners(childPanel, left);
            } else {
                comp.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        leftFocused = left;
                        getPage().requestFocusInWindow();
                    }
                });
            }
        }
    }

    protected void rightBarSelected(RightBoxBar nextBar) {}

    protected void leftBarSelected(AbstractStatBar statBar) {}

    public boolean isLeftFocused() {
        return leftFocused;
    }

    public void setLeftFocused(boolean leftFocused) {
        this.leftFocused = leftFocused;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public LeftContentRightScrollPagesTemplate getPage() {
        return this;
    }

}
