package visuals.MainMenuPages;
import javax.swing.*;
import java.awt.*;

public class ResultsPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public ResultsPage(CardLayout cardLayout, JPanel pages){
        super(cardLayout);
        mainPanel = pages;
        getHeaderPanel().setTitle("Results");
    }

}
