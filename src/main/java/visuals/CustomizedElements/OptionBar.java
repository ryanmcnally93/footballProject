package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OptionBar extends RightBoxBar {

    private CustomizedOptionField optionField;
    private OptionBar dependant;

    public OptionBar(List<String> options, int size) {
        super(20);
        GamePanel.setPermanentHeight(this, size);
        optionField = new CustomizedOptionField(options, 133, 0, 18, false);
        GamePanel.setPermanentHeight(optionField, 20);
    }

    @Override
    public void createBar() {
        add(optionField, BorderLayout.CENTER);
        setArcHeight(20);
        setArcWidth(20);
        revalidate();
        repaint();
    }

    public void updateOptions(List<String> options) {
        optionField.setOptions(options);
        optionField.revalidate();
        optionField.repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        // keep your text-based width/height here; do NOT read parent/getWidth()
        return new Dimension(d.width-3, d.height);
    }

    @Override
    public Dimension getMaximumSize() {
        Dimension d = super.getPreferredSize();
        // allow horizontal stretch, keep natural height
        return new Dimension(Integer.MAX_VALUE, d.height);
    }

    @Override
    protected void childMouseExited() {
        optionField.setForeground(getSecondaryColor());
    }

    @Override
    protected void childMouseEntered() {
        optionField.setForeground(getPrimaryColor());
    }

    @Override
    public void setAsSelected(boolean bool) {
        setSelected(bool);
        // This should be done ideally within the CustomisedButton class
        if (bool) {
            optionField.setForeground(getPrimaryColor());
            setBackground(getSecondaryColor());
            optionField.addButtons();
        } else {
            optionField.setForeground(getSecondaryColor());
            setBackground(getPrimaryColor());
            optionField.removeButtons();
        }
    }

    public CustomizedOptionField getOptionField() {
        return optionField;
    }

    public void setOptionField(CustomizedOptionField optionField) {
        this.optionField = optionField;
    }

    public OptionBar getDependant() {
        return dependant;
    }

    public void setDependant(OptionBar dependant) {
        this.dependant = dependant;
    }
}
