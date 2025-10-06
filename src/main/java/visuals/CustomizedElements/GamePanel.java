package visuals.CustomizedElements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class GamePanel extends JPanel {
    private static final long serialVersionUID = -8911764479146802449L;
    private Box east;
    private Box west;
    private static final String RIGHT = "Next Page";
    private static final String LEFT = "Previous Page";
    private static final String UP = "Up";
    private static final String DOWN = "Down";

    public GamePanel() {};

    protected void addGameMouseListener(MouseAdapter mouseAdapter) {
        addMouseListener(mouseAdapter);
    }

//    public static Color getGreenCharcoal() {
//        return new Color(0x36, 0x6A, 0x4F);
//    }

    public static Color getCharcoal() {
        return new Color(0x36, 0x45, 0x4F);
    }

    public static Font getBebasNeueFont() {
        try {
            File fontFile = new File("./src/main/java/visuals/Fonts/BebasNeue-Regular.ttf");
            return Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.BOLD, 30f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Font getBebasNeueFontWithSize(float size) {
        try {
            File fontFile = new File("./src/main/java/visuals/Fonts/BebasNeue-Regular.ttf");
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

    public void addLabelToTitleLine(String text, int width, JPanel titleLine) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Menlo", Font.BOLD, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        if (text.equals("RAT.")) {
            label.setToolTipText("RATING");
        }
        setPermanentWidth(label, width);
        titleLine.add(label);
    }

    public static boolean isTheCursorStillOverTheButton(JButton button) {
        if (!button.isShowing()) {
            return false;
        }
        Point mouseOnScreen = MouseInfo.getPointerInfo().getLocation();
        Rectangle buttonOnScreen = new Rectangle(
                button.getLocationOnScreen(),
                button.getSize()
        );

        return buttonOnScreen.contains(mouseOnScreen);
    }

    public static ImageIcon getOppositeImage(ImageIcon icon) {
        if (icon.getDescription().equals("Down")) {
            return new ImageIcon("./src/main/java/visuals/Images/down_arrow_darkbg.png", "DownDark");
        } else if (icon.getDescription().equals("DownDark")) {
            return new ImageIcon("./src/main/java/visuals/Images/down_arrow.png", "Down");
        } else if (icon.getDescription().equals("Up")) {
            return new ImageIcon("./src/main/java/visuals/Images/up_arrow_darkbg.png", "UpDark");
        } else if (icon.getDescription().equals("UpDark")) {
            return new ImageIcon("./src/main/java/visuals/Images/up_arrow.png", "Up");
        } else if (icon.getDescription().equals("Training")) {
            return new ImageIcon("./src/main/java/visuals/Images/training_icon_darkbg.png", "TrainingDark");
        } else if (icon.getDescription().equals("TrainingDark")) {
            return new ImageIcon("./src/main/java/visuals/Images/training_icon.png", "Training");
        } else if (icon.getDescription().equals("Team")) {
            return new ImageIcon("./src/main/java/visuals/Images/team_icon_darkbg.png", "TeamDark");
        } else if (icon.getDescription().equals("TeamDark")) {
            return new ImageIcon("./src/main/java/visuals/Images/team_icon.png", "Team");
        } else if (icon.getDescription().equals("Standings")) {
            return new ImageIcon("./src/main/java/visuals/Images/standings_icon_darkbg.png", "StandingsDark");
        } else if (icon.getDescription().equals("StandingsDark")) {
            return new ImageIcon("./src/main/java/visuals/Images/standings_icon.png", "Standings");
        } else if (icon.getDescription().equals("PlayerSearchDark")) {
            return new ImageIcon("./src/main/java/visuals/Images/player_search_icon.png", "PlayerSearch");
        } else if (icon.getDescription().equals("PlayerSearch")) {
            return new ImageIcon("./src/main/java/visuals/Images/player_search_icon_darkbg.png", "PlayerSearchDark");
        } else if (icon.getDescription().equals("MyClub")) {
            return new ImageIcon("./src/main/java/visuals/Images/my_club_icon_darkbg.png", "MyClubDark");
        } else if (icon.getDescription().equals("MyClubDark")) {
            return new ImageIcon("./src/main/java/visuals/Images/my_club_icon.png", "MyClub");
        } else if (icon.getDescription().equals("FixturesDark")) {
            return new ImageIcon("./src/main/java/visuals/Images/fixtures_icon.png", "Fixtures");
        } else if (icon.getDescription().equals("Fixtures")) {
            return new ImageIcon("./src/main/java/visuals/Images/fixtures_icon_darkbg.png", "FixturesDark");
        } else if (icon.getDescription().equals("MyProfile")) {
            return new ImageIcon("./src/main/java/visuals/Images/my_profile_icon_darkbg.png", "MyProfileDark");
        } else if (icon.getDescription().equals("MyProfileDark")) {
            return new ImageIcon("./src/main/java/visuals/Images/my_profile_icon.png", "MyProfile");
        } else if (icon.getDescription().equals("DownSmall")) {
            ImageIcon newIcon =  new ImageIcon("./src/main/java/visuals/Images/down_arrow_darkbg.png", "DownSmallDark");
            return alterImageSize(newIcon, 2);
        } else if (icon.getDescription().equals("DownSmallDark")) {
            ImageIcon newIcon =  new ImageIcon("./src/main/java/visuals/Images/down_arrow.png", "DownSmall");
            return alterImageSize(newIcon, 2);
        } else if (icon.getDescription().equals("UpSmall")) {
            ImageIcon newIcon =  new ImageIcon("./src/main/java/visuals/Images/up_arrow_darkbg.png", "UpSmallDark");
            return alterImageSize(newIcon, 2);
        } else if (icon.getDescription().equals("UpSmallDark")) {
            ImageIcon newIcon =  new ImageIcon("./src/main/java/visuals/Images/up_arrow.png", "UpSmall");
            return alterImageSize(newIcon, 2);
        } else {
            System.out.println("ERROR: getOppositeImage() is unable to determine which image should be shown! Icon description is: " + icon.getDescription() + ".");
            throw new RuntimeException();
        }
    }

    public JScrollPane makeScroller(Box container){
        JScrollPane scroller = new JScrollPane(container);
        scroller.getViewport().setBackground(Color.LIGHT_GRAY);
        scroller.setBorder(null);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        return scroller;
    }

    public JScrollPane makeScroller(RoundedPanel container){
        JScrollPane scroller = new JScrollPane(container);
        scroller.getViewport().setBackground(Color.LIGHT_GRAY);
        scroller.setBorder(null);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        return scroller;
    }

    public static ImageIcon alterImageSize(ImageIcon icon ,int size){
        int newWidth = icon.getIconWidth() / size;
        int newHeight = icon.getIconHeight() / size;

        Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage, icon.getDescription());
    }

    private static int findCustomisedTitleWidthBeforeDrawn(String text, Font font) {
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        // optional: make measuring match rendering quality
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        int width = fm.stringWidth(text);
        g2.dispose();
        return width;
    }

    public static ImageIcon getIconWithSpecificSize(String url, String description, int size) {
        BufferedImage bufferedImage = getIconWithSpecificSizeAsBufferedScaledImage(url, size);

        ImageIcon buttonIcon = new ImageIcon(bufferedImage);
        buttonIcon.setDescription(description);

        return buttonIcon;
    }

    public static BufferedImage getIconWithSpecificSizeAsBufferedScaledImage(String url, int size) {
        BufferedImage image = null;
        BufferedImage bufferedScaledImage = null;
        try {
            image = ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(image != null) {
            Image scaledImage = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            bufferedScaledImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedScaledImage.createGraphics();
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();
        }
        return bufferedScaledImage;
    }

    public static ImageIcon alterImageSizeWithTarget(ImageIcon icon, int targetSize) {
        Image scaledImage = icon.getImage().getScaledInstance(targetSize, targetSize, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage, icon.getDescription());
    }

    public static void setPermanentWidthAndHeight(JComponent box, int width, int height){
        box.setPreferredSize(new Dimension(width, height));
        box.setMinimumSize(new Dimension(width, height));
        box.setMaximumSize(new Dimension(width, height));
    }

    public static void setPermanentWidth(JComponent box, int width){
        box.setPreferredSize(new Dimension(width, box.getPreferredSize().height));
        box.setMinimumSize(new Dimension(width, box.getMinimumSize().height));
        box.setMaximumSize(new Dimension(width, box.getMaximumSize().height));
    }

    public static void setPermanentHeight(JComponent box, int height){
        box.setPreferredSize(new Dimension(box.getPreferredSize().width, height));
        box.setMinimumSize(new Dimension(box.getMinimumSize().width, height));
        box.setMaximumSize(new Dimension(box.getMaximumSize().width, height));
    }

    public void setPermanentExtendedHeight(JComponent box, int height){
        box.setPreferredSize(new Dimension(box.getPreferredSize().width, box.getPreferredSize().height + height));
        box.setMinimumSize(new Dimension(box.getMinimumSize().width, box.getMinimumSize().height + height));
        box.setMaximumSize(new Dimension(box.getMaximumSize().width, box.getMaximumSize().height + height));
    }

    public void setPermanentWidthAndHeight(Box box, int width, int height){
        box.setPreferredSize(new Dimension(width, height));
        box.setMinimumSize(new Dimension(width, height));
        box.setMaximumSize(new Dimension(width, height));
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

        inputMap.put(KeyStroke.getKeyStroke("UP"), UP);
        actionMap.put(UP, getUpClickAction());

        inputMap.put(KeyStroke.getKeyStroke("DOWN"), DOWN);
        actionMap.put(DOWN, getDownClickAction());
    }

    protected AbstractAction getRightClickAction() {
        return new RightClick();
    }

    public static class RightClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Default: Do nothing
        }
    }

    protected AbstractAction getLeftClickAction() {
        return new LeftClick();
    }

    public static class LeftClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Default: Do nothing
        }
    }

    protected AbstractAction getDownClickAction() {
        return new DownClick();
    }

    public static class DownClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Default: Do nothing
        }
    }

    protected AbstractAction getUpClickAction() {
        return new UpClick();
    }

    public static class UpClick extends AbstractAction {
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

        inputMap.remove(KeyStroke.getKeyStroke("UP"));
        actionMap.remove(UP);

        inputMap.remove(KeyStroke.getKeyStroke("DOWN"));
        actionMap.remove(DOWN);
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