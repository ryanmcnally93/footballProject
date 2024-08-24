package visuals.MainMenuPages;
import javax.swing.*;
import java.awt.*;

public class TopAssistsPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public TopAssistsPage(CardLayout cardLayout, JPanel pages){
        super(cardLayout);
        mainPanel = pages;
        getHeaderPanel().setTitle("Top Assists");
    }

}
