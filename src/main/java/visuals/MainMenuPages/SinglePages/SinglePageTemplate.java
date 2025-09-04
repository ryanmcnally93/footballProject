package visuals.MainMenuPages.SinglePages;

import visuals.CustomizedElements.MainPageTemplate;
import visuals.CustomizedElements.RoundedPanel;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class SinglePageTemplate extends MainPageTemplate {

    private JPanel mainPanel;
    private RoundedPanel leftBox, rightBox;
    private JScrollPane scroller;
    private Image backgroundImage;

    public SinglePageTemplate(Scheduler scheduler) {
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

        rightBox = new RoundedPanel(30);
        rightBox.setBorderColor(newColor);
        rightBox.setBackground(newColor);

        mainPanel.add(leftBox);
        mainPanel.add(Box.createRigidArea(new Dimension(12, 0)));

        scroller = makeScroller(rightBox);
        scroller.setFocusable(false);
        scroller.setOpaque(false);
        scroller.getViewport().setOpaque(false);
        setPermanentWidthAndHeight(scroller, 155, 265);
        mainPanel.add(scroller);

        mainPanel.setBounds(0, 90, 800, 420);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);

        getFooterPanel().addBackButton();
        updateBackButtonFunctionality();

        setVisible(true);
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

    public JScrollPane getScroller() {
        return scroller;
    }

    public void setScroller(JScrollPane scroller) {
        this.scroller = scroller;
    }
}
