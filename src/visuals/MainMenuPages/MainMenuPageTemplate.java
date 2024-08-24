package visuals.MainMenuPages;
import visuals.CustomizedElements.GamePanel;
import visuals.CustomizedElements.SlidingPanel;
import visuals.MatchFrames.MatchFrames;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class MainMenuPageTemplate extends GamePanel {

    private JLayeredPane layeredPane;
    private CardLayout layout;
    private JPanel mainPanel, footerPanel;
    private HeaderPanel headerPanel;

    public MainMenuPageTemplate(CardLayout cardLayout){
        setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        layout = cardLayout;

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
            title = new JLabel("");
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

    public JPanel getFooterPanel() {
        return footerPanel;
    }

    public void setFooterPanel(JPanel footerPanel) {
        this.footerPanel = footerPanel;
    }

}
