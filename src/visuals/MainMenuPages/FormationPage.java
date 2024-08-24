package visuals.MainMenuPages;
import javax.swing.*;
import java.awt.*;

public class FormationPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public FormationPage(CardLayout cardLayout, JPanel pages){
        super(cardLayout);
        mainPanel = pages;
        getHeaderPanel().setTitle("Formation");
    }

}
