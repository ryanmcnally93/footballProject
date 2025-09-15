package visuals.CustomizedElements;

import visuals.MainMenuPages.TacticsPageTemplate;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class HeaderFooterAndCardMapTemplate extends GamePanel {

    private Scheduler scheduler;
    private JLayeredPane layeredPane;
    private TacticsPageTemplate.HeaderPanel headerPanel;
    private TacticsPageTemplate.FooterPanel footerPanel;
    private boolean fromScheduler, cardmap;
    private CardLayout layout;
    private JPanel pages;

    public HeaderFooterAndCardMapTemplate(Scheduler scheduler) {
        setScheduler(scheduler);
        init();
    }

    public HeaderFooterAndCardMapTemplate(CardLayout cardLayout, JPanel pages){
        init();
        this.layout = cardLayout;
        this.pages = pages;
        this.cardmap = true;

        getFooterPanel().addLeftAndRightButtons();
        addLeftAndRightActionListeners();
        addKeyListeners();
    }

    public HeaderFooterAndCardMapTemplate() {
        init();
    }

    public void init() {
        setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        headerPanel = new TacticsPageTemplate.HeaderPanel();
        headerPanel.setBounds(0, 0, 800, 80);

        footerPanel = new TacticsPageTemplate.FooterPanel();
        footerPanel.setBounds(0, 500, 800, 100);

        layeredPane.add(headerPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(footerPanel, JLayeredPane.PALETTE_LAYER);

        add(layeredPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public void addLeftAndRightActionListeners() {
        getFooterPanel().getPrevButton().addActionListener(e -> {
            layout.previous(pages);
            moveButtonsWithUser_Backwards();
        });

        getFooterPanel().getNextButton().addActionListener(e -> {
            layout.next(pages);
            moveButtonsWithUser_Forwards();
        });
    }

    public void moveButtonsWithUser_Forwards() {}

    public void moveButtonsWithUser_Backwards() {}

    public class HeaderPanel extends JPanel {

        private CustomizedTitle title, pageIcon;

        public HeaderPanel() {
            setLayout(new BorderLayout());

            CustomizedTitle teamName = new CustomizedTitle("");
            teamName.setFontSize(20);

            if (getScheduler() != null && getScheduler().getTeam() != null) {
                teamName.setText(getScheduler().getTeam().getName());
            }

            JPanel leftPanel = new JPanel();
            leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            leftPanel.setOpaque(false);
            leftPanel.add(Box.createVerticalGlue());
            leftPanel.add(teamName);
            leftPanel.add(Box.createVerticalGlue());
            setPermanentWidthAndHeight(leftPanel, 200, 120);

            CustomizedTitle userName = new CustomizedTitle("");
            userName.setFontSize(20);

            if (getScheduler() != null && getScheduler().getUser() != null) {
                userName.setText(getScheduler().getUser().getName());
            }
            userName.setAlignmentX(Component.RIGHT_ALIGNMENT);

            pageIcon = new CustomizedTitle("");
            pageIcon.setAlignmentX(Component.RIGHT_ALIGNMENT);

            JPanel rightPanel = new JPanel();
            rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
            rightPanel.setOpaque(false);
            rightPanel.add(Box.createVerticalGlue());
            rightPanel.add(Box.createVerticalStrut(31));
            rightPanel.add(userName);
            rightPanel.add(Box.createVerticalStrut(5));
            rightPanel.add(pageIcon);
            rightPanel.add(Box.createVerticalGlue());
            setPermanentWidthAndHeight(rightPanel, 200, 120);

            title = new CustomizedTitle("", SwingConstants.CENTER);
            title.setFont(getBebasNeueFontWithSize(32f));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel titlePanel = new JPanel();
            titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
            titlePanel.setOpaque(false);
            titlePanel.add(Box.createVerticalGlue());
            titlePanel.add(title);
            titlePanel.add(Box.createVerticalGlue());
            setPermanentWidthAndHeight(titlePanel, 400, 120);

            add(leftPanel, BorderLayout.WEST);
            add(titlePanel, BorderLayout.CENTER);
            add(rightPanel, BorderLayout.EAST);
        }

        public CustomizedTitle getTitle() {
            return title;
        }

        public void setTitle(String title) {
            getTitle().setText(title);
            revalidate();
            repaint();
        }

        public CustomizedTitle getPageIcon() {
            return pageIcon;
        }

        public void setPageIcon(CustomizedTitle pageIcon) {
            this.pageIcon = pageIcon;
        }
    }

    public class FooterPanel extends JPanel {

        private CustomizedButton prevButton, nextButton;
        private JPanel buttonBox, middleBox, backButtonBox, leftBlankBox;
        private CustomizedButton back;
        private MouseAdapter backButtonListener;

        public FooterPanel() {
            setLayout(new BorderLayout());
            setBackground(Color.LIGHT_GRAY);

            backButtonBox = new JPanel(new FlowLayout(FlowLayout.CENTER));
            setPermanentWidth(backButtonBox, 115);

            leftBlankBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
            leftBlankBox.setBackground(Color.LIGHT_GRAY);
            setPermanentWidth(leftBlankBox, 115);

            add(backButtonBox, BorderLayout.EAST);
            add(leftBlankBox, BorderLayout.WEST);

            back = new CustomizedButton("Back", 16);
            back.setAlignmentX(Component.CENTER_ALIGNMENT);

            backButtonListener = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getSource() instanceof CustomizedButton button) button.init();
                    if (isFromScheduler()) {
                        getScheduler().displayPage(getScheduler().getWindow());
                    } else {
                        getScheduler().displayMatchFrames(getScheduler().getMatch());
                    }
                    back.init();
                }
            };
        }

        public MouseAdapter getBackButtonListener() {
            return backButtonListener;
        }

        public void setBackButtonListener(MouseAdapter backButtonListener) {
            this.backButtonListener = backButtonListener;
        }

        public void addBackButton() {
            if (!backButtonBox.isAncestorOf(back)) {
                backButtonBox.add(back);
            }
        }

        public void addBackButtonListener(CustomizedButton back) {
            back.addMouseListener(backButtonListener);
        }

        public void removeBackButtonListener(CustomizedButton back) {
            back.removeMouseListener(backButtonListener);
        }

        public void addLeftAndRightButtons() {
            prevButton = new CustomizedButton("Prev", 12);
            nextButton = new CustomizedButton("Next", 12);

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

        public JPanel getLeftBlankBox() {
            return leftBlankBox;
        }

        public void setLeftBlankBox(JPanel leftBlankBox) {
            this.leftBlankBox = leftBlankBox;
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

        public CustomizedButton getBackButton() {
            return back;
        }

        public void setBackButton(CustomizedButton back) {
            this.back = back;
        }

        public void setPrevButton(CustomizedButton prevButton) {
            this.prevButton = prevButton;
        }
    }

    public void updateBackButtonFunctionality() {
        CustomizedButton back = getFooterPanel().getBackButton();
        if (Arrays.stream(back.getMouseListeners()).noneMatch(listener -> listener == getFooterPanel().getBackButtonListener())) {
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

    public TacticsPageTemplate.FooterPanel getFooterPanel() {
        return footerPanel;
    }

    public void setFooterPanel(TacticsPageTemplate.FooterPanel footerPanel) {
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

    public void setPages(JPanel pages) {
        this.pages = pages;
    }

    @Override
    protected AbstractAction getRightClickAction() {
        if (cardmap) {
            return new CardMapRightClick();
        } else {
            return null;
        }
    }

    public class CardMapRightClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            layout.next(pages);
            moveButtonsWithUser_Forwards();
        }
    }

    @Override
    protected AbstractAction getLeftClickAction() {
        if (cardmap) {
            return new CardMapLeftClick();
        } else {
            return null;
        }
    }

    public class CardMapLeftClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            layout.previous(pages);
            moveButtonsWithUser_Backwards();
        }
    }
}
