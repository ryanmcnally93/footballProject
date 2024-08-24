package visuals.MainMenuPages;
import javax.swing.*;
import java.awt.*;

public class MatchRolesPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public MatchRolesPage(CardLayout cardLayout, JPanel pages){
        super(cardLayout);
        mainPanel = pages;
        getHeaderPanel().setTitle("Match Roles");
    }

}
