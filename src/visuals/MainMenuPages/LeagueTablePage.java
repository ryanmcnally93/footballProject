package visuals.MainMenuPages;
import javax.swing.*;
import java.awt.*;

public class LeagueTablePage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public LeagueTablePage(CardLayout cardLayout, JPanel pages){
        super(cardLayout);
        mainPanel = pages;
        getHeaderPanel().setTitle("League Table");
    }

}
