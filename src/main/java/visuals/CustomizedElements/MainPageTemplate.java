package visuals.CustomizedElements;

import visuals.MainMenuPages.MainMenuPageTemplate;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainPageTemplate extends GamePanel {

    private Scheduler scheduler;
    private JLayeredPane layeredPane;
    private MainMenuPageTemplate.HeaderPanel headerPanel;
    private MainMenuPageTemplate.FooterPanel footerPanel;
    private boolean fromScheduler = false;

    public MainPageTemplate(Scheduler scheduler) {
        setScheduler(scheduler);
        init();
    }

    public MainPageTemplate() {
        init();
    }

    public void init() {
        setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        headerPanel = new MainMenuPageTemplate.HeaderPanel();
        headerPanel.setBounds(0, 0, 800, 80);

        footerPanel = new MainMenuPageTemplate.FooterPanel();
        footerPanel.setBounds(0, 500, 800, 100);

        layeredPane.add(headerPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(footerPanel, JLayeredPane.PALETTE_LAYER);

        add(layeredPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public class HeaderPanel extends JPanel {

        private JLabel title;

        public HeaderPanel() {
            setLayout(new BorderLayout());
            setBackground(Color.LIGHT_GRAY);
            title = new JLabel("", SwingConstants.CENTER);
            title.setFont(getBebasNeueFont());
            title.setForeground(new Color(0, 51, 204));
            add(title, BorderLayout.CENTER);
        }

        public JLabel getTitle() {
            return title;
        }

        public void setTitle(String title) {
            getTitle().setText(title);
        }

    }

    public class FooterPanel extends JPanel {

        private CustomizedButton prevButton, nextButton;
        private JPanel buttonBox, middleBox, backButtonBox;
        private JButton back;

        public FooterPanel() {
            setLayout(new BorderLayout());
            setBackground(Color.LIGHT_GRAY);

            backButtonBox = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            backButtonBox.setBackground(Color.LIGHT_GRAY);
            setPermanentWidth(backButtonBox, 115);

            JPanel leftBlankBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
            leftBlankBox.setBackground(Color.LIGHT_GRAY);
            setPermanentWidth(leftBlankBox, 115);

            add(backButtonBox, BorderLayout.EAST);
            add(leftBlankBox, BorderLayout.WEST);

            back = new JButton("Back");
        }

        public void addBackButton() {
            if (!backButtonBox.isAncestorOf(back)) {
                backButtonBox.add(back);
            }
        }

        public void addBackButtonListener(JButton back) {
            back.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (isFromScheduler()) {
                        getScheduler().displayPage(getScheduler().getWindow());
                    } else {
                        getScheduler().displayMatchFrames(getScheduler().getMatch());
                    }
                }
            });
        }

        public void addLeftAndRightButtons() {
            prevButton = new CustomizedButton("Prev");
            nextButton = new CustomizedButton("Next");

            buttonBox = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonBox.add(prevButton);

            middleBox = new JPanel();
            middleBox.setBackground(Color.LIGHT_GRAY);
            buttonBox.add(middleBox);

            buttonBox.add(nextButton);
            buttonBox.setBackground(Color.LIGHT_GRAY);
            add(buttonBox, BorderLayout.CENTER);

            addKeyListeners();
        }

        public JPanel getMiddleBox() {
            return middleBox;
        }

        public JPanel getBackButtonBox() {
            return backButtonBox;
        }

        public void setBackButtonBox(JPanel backButtonBox) {
            this.backButtonBox = backButtonBox;
        }

        public void setMiddleBox(JPanel middleBox) {
            this.middleBox = middleBox;
        }

        public JPanel getButtonBox() {
            return buttonBox;
        }

        public void setButtonBox(JPanel buttonBox) {
            this.buttonBox = buttonBox;
        }

        public CustomizedButton getNextButton() {
            return nextButton;
        }

        public void setNextButton(CustomizedButton nextButton) {
            this.nextButton = nextButton;
        }

        public CustomizedButton getPrevButton() {
            return prevButton;
        }

        public JButton getBackButton() {
            return back;
        }

        public void setBackButton(JButton back) {
            this.back = back;
        }

        public void setPrevButton(CustomizedButton prevButton) {
            this.prevButton = prevButton;
        }
    }

    public void updateBackButtonFunctionality() {
        JButton back = getFooterPanel().getBackButton();
        if (back.getMouseListeners().length == 1) {
            getFooterPanel().addBackButtonListener(back);
        } else {
            MouseListener[] listeners = back.getMouseListeners();
            back.removeMouseListener(listeners[listeners.length - 1]);
            getFooterPanel().addBackButtonListener(back);
        }
    }

    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    public void setLayeredPane(JLayeredPane layeredPane) {
        this.layeredPane = layeredPane;
    }

    public HeaderPanel getHeaderPanel() {
        return headerPanel;
    }

    public void setHeaderPanel(HeaderPanel headerPanel) {
        this.headerPanel = headerPanel;
    }

    public MainMenuPageTemplate.FooterPanel getFooterPanel() {
        return footerPanel;
    }

    public void setFooterPanel(MainMenuPageTemplate.FooterPanel footerPanel) {
        this.footerPanel = footerPanel;
    }

    public boolean isFromScheduler() {
        return fromScheduler;
    }

    public void setFromScheduler(boolean fromScheduler) {
        this.fromScheduler = fromScheduler;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
