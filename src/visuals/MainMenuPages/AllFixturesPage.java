package visuals.MainMenuPages;
import javax.swing.*;
import java.awt.*;

public class AllFixturesPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public AllFixturesPage(CardLayout cardLayout, JPanel pages){
        super(cardLayout);
        mainPanel = pages;
        getHeaderPanel().setTitle("All Fixtures");
    }

}
