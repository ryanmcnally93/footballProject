package visuals.MainMenuPages;
import javax.swing.*;
import java.awt.*;

public class FirstTeamPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public FirstTeamPage(CardLayout cardLayout, JPanel pages){
        super(cardLayout);
        mainPanel = pages;
        getHeaderPanel().setTitle("First Team");
    }

}
