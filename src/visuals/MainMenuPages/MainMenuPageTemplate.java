package visuals.MainMenuPages;
import visuals.CustomizedElements.CustomizedButton;
import visuals.CustomizedElements.GamePanel;
import visuals.CustomizedElements.SlidingPanel;
import visuals.MatchFrames.MatchFrames;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuPageTemplate extends GamePanel {

    private Scheduler scheduler;
    private JLayeredPane layeredPane;
    private CardLayout layout;
    private JPanel pages, mainPanel;
    private HeaderPanel headerPanel;
    private FooterPanel footerPanel;

    public MainMenuPageTemplate(CardLayout cardLayout, JPanel pages, Scheduler scheduler){
        setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        this.scheduler = scheduler;
        this.layout = cardLayout;
        this.pages = pages;

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        headerPanel = new MainMenuPageTemplate.HeaderPanel();
        headerPanel.setBounds(0, 0, 800, 80);

        footerPanel = new MainMenuPageTemplate.FooterPanel();
        footerPanel.setBounds(0, 500, 800, 100);

        layeredPane.add(headerPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(footerPanel, JLayeredPane.DEFAULT_LAYER);

        add(layeredPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public class HeaderPanel extends JPanel {

        private JLabel title;

        public HeaderPanel() {
            setLayout(new BorderLayout());
            setBackground(Color.LIGHT_GRAY);
            title = new JLabel("", SwingConstants.CENTER);
            title.setFont(new Font("Menlo", Font.BOLD, 30));
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

        private JButton prevButton, nextButton, back;

        public FooterPanel() {
            setLayout(new BorderLayout());
            JPanel middleBox = new JPanel(new FlowLayout(FlowLayout.CENTER));
            prevButton = new CustomizedButton("Prev");
            nextButton = new CustomizedButton("Next");

            prevButton.addActionListener(e -> {
                layout.previous(pages);
            });

            nextButton.addActionListener(e -> {
                layout.next(pages);
            });
            middleBox.add(prevButton);
            middleBox.add(nextButton);
            middleBox.setBackground(Color.LIGHT_GRAY);
            add(middleBox);

            JPanel backButtonBox = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            backButtonBox.setBackground(Color.LIGHT_GRAY);
            setPermanentWidth(backButtonBox, 115);

            JPanel leftBlankBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
            leftBlankBox.setBackground(Color.LIGHT_GRAY);
            setPermanentWidth(leftBlankBox, 115);
            back = new JButton("Back");
            backButtonBox.add(back);

            back.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    scheduler.displayPage(scheduler.getWindow());
                }
            });

            add(backButtonBox, BorderLayout.EAST);
            add(middleBox, BorderLayout.CENTER);
            add(leftBlankBox, BorderLayout.WEST);
        }

        public JButton getPrevButton() {
            return prevButton;
        }

        public void setPrevButton(JButton prevButton) {
            this.prevButton = prevButton;
        }

        public JButton getNextButton() {
            return nextButton;
        }

        public void setNextButton(JButton nextButton) {
            this.nextButton = nextButton;
        }

        public JButton getBack() {
            return back;
        }

        public void setBack(JButton back) {
            this.back = back;
        }
    }

    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    public void setLayeredPane(JLayeredPane layeredPane) {
        this.layeredPane = layeredPane;
    }

    @Override
    public CardLayout getLayout() {
        return layout;
    }

    public void setLayout(CardLayout layout) {
        this.layout = layout;
    }

    public HeaderPanel getHeaderPanel() {
        return headerPanel;
    }

    public void setHeaderPanel(HeaderPanel headerPanel) {
        this.headerPanel = headerPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public FooterPanel getFooterPanel() {
        return footerPanel;
    }

    public void setFooterPanel(FooterPanel footerPanel) {
        this.footerPanel = footerPanel;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public JPanel getPages() {
        return pages;
    }

    public void setPages(JPanel pages) {
        this.pages = pages;
    }
}
