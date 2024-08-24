package visuals.MainMenuPages;
import javax.swing.*;
import java.awt.*;

public class TopGoalscorersPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public TopGoalscorersPage(CardLayout cardLayout, JPanel pages){
        super(cardLayout);
        mainPanel = pages;
        getHeaderPanel().setTitle("Top Goalscorers");
    }

}
