package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;

public class NoResultsStatLine extends AbstractStatBar {

    public NoResultsStatLine(){
        super(20, false);
        buildColumns();
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void buildColumns() {
        createColumn("", 50);
        createColumn("No results found", 500, SwingConstants.LEFT);
    }

}
