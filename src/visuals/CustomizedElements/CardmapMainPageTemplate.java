package visuals.CustomizedElements;
import visuals.MainMenuPages.MainMenuPageTemplate;
import visuals.MatchFrames.MatchFrames;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CardmapMainPageTemplate extends GamePanel {

    private static final String RIGHT = "Next Page";
    private static final String LEFT = "Previous Page";
    private JLayeredPane layeredPane;
    private CardLayout layout;
    private JPanel pages;
    private MainMenuPageTemplate.HeaderPanel headerPanel;
    private MainMenuPageTemplate.FooterPanel footerPanel;

    public CardmapMainPageTemplate(CardLayout cardLayout, JPanel pages){
        this.layout = cardLayout;
        this.pages = pages;

        setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        headerPanel = new CardmapMainPageTemplate.HeaderPanel();
        headerPanel.setBounds(0, 0, 800, 80);

        footerPanel = new CardmapMainPageTemplate.FooterPanel();
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

        private CustomizedButton prevButton, nextButton;
        private JPanel buttonBox, middleBox;
        private ActionMap actionMap;
        private Box line;

        public FooterPanel() {

            setLayout(new BorderLayout());
            setBackground(Color.LIGHT_GRAY);

            prevButton = new CustomizedButton("Prev");
            nextButton = new CustomizedButton("Next");

            prevButton.addActionListener(e -> {
                layout.previous(pages);
            });

            nextButton.addActionListener(e -> {
                layout.next(pages);
            });

            buttonBox = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonBox.add(prevButton);

            middleBox = new JPanel();
            middleBox.setBackground(Color.LIGHT_GRAY);
            buttonBox.add(middleBox);

            buttonBox.add(nextButton);
            buttonBox.setBackground(Color.LIGHT_GRAY);
            add(buttonBox, BorderLayout.CENTER);

            InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            actionMap = getActionMap();

            inputMap.put(KeyStroke.getKeyStroke("LEFT"), LEFT);
            actionMap.put(LEFT, new MatchFrames.leftClick());

            inputMap.put(KeyStroke.getKeyStroke("RIGHT"), RIGHT);
            actionMap.put(RIGHT, new MatchFrames.rightClick());

        }

        public JPanel getMiddleBox() {
            return middleBox;
        }

        public void setMiddleBox(JPanel middleBox) {
            this.middleBox = middleBox;
        }

        public ActionMap getFooterActionMap() {
            return actionMap;
        }

        public Box getLine(){
            return this.line;
        }

        public void setFooterActionMap(ActionMap actionMap) {
            this.actionMap = actionMap;
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

    public class rightClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            layout.next(pages);
        }
    }

    public class leftClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            layout.previous(pages);
        }
    }

}
