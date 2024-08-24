package visuals.MainMenuPages;
import javax.swing.*;
import java.awt.*;

public class MyFixturesPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public MyFixturesPage(CardLayout cardLayout, JPanel pages){
        super(cardLayout);
        mainPanel = pages;
        getHeaderPanel().setTitle("My Fixtures");
    }

}
