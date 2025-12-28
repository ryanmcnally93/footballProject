package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStatBar extends RoundedPanel {

    protected List<JLabel> columns = new ArrayList<>();
    private boolean showBorder;

    public AbstractStatBar(int cornerRadius, boolean showBorder) {
        super(cornerRadius);
        this.showBorder = showBorder;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(true);
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(600, 30));
        setBorderThickness(showBorder ? 1 : 0);
    }

    /** Subclasses implement this to add their JLabels in the desired order */
    protected abstract void buildColumns();

    /** Helper to create a JLabel with fixed width & center alignment */
    protected JLabel createColumn(String text, int width) {
        JLabel label = new JLabel(text.toUpperCase(), SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(width, 20));
        label.setMaximumSize(new Dimension(width, 20));
        label.setMinimumSize(new Dimension(width, 20));
        label.setFont(getBebasNeueFontWithSize(20));
        columns.add(label);
        add(label);
        return label;
    }

    protected JLabel createColumn(String text, int width, int swingConstant) {
        JLabel label = createColumn(text, width);
        label.setHorizontalAlignment(swingConstant);
        return label;
    }

    protected JLabel createColumn(String text, int width, boolean usersMatch) {
        JLabel label = createColumn(text, width);
        label.setForeground(usersMatch ? getGreenCharcoal() : getCharcoal());
        return label;
    }

    protected CustomizedButton createColumn(ImageIcon icon, int width) {
        CustomizedButton button = new CustomizedButton(icon);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(width, 20));
        button.setMaximumSize(new Dimension(width, 20));
        button.setMinimumSize(new Dimension(width, 20));
        add(button);
        return button;
    }

    /** Repaint + revalidate utility */
    protected void refresh() {
        revalidate();
        repaint();
    }

    public List<JLabel> getColumns() {
        return columns;
    }

    public void setColumns(List<JLabel> columns) {
        this.columns = columns;
    }
}