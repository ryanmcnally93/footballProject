package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class OptionBar extends RightBoxBar {

    private CustomizedOptionField optionField;
    private OptionBar dependant;

    private List<String> options;
    private HashMap<String, List<String>> optionsMap = new HashMap<>();

    private int currentIndex = 0;

    public OptionBar(List<String> options, int size) {
        super(20);
        this.options = new ArrayList<>(options);

        GamePanel.setPermanentHeight(this, size);
        optionField = new CustomizedOptionField(options, 133, 0, 18, false);
        GamePanel.setPermanentHeight(optionField, 20);

        optionField.setOnClickLeft(this::updateOptionsBackward);
        optionField.setOnClickRight(this::updateOptionsForward);
    }

    public void setOptionsMap(Map<String, List<String>> map) {
        this.optionsMap = new HashMap<>(map);
    }

    @Override
    public void createBar() {
        add(optionField, BorderLayout.CENTER);
        setArcHeight(20);
        setArcWidth(20);
        revalidate();
        repaint();
    }

    public void setOptions(List<String> newOptions) {
        this.options = new ArrayList<>(newOptions);
        currentIndex = 0;
        optionField.setOptions(newOptions);
        optionField.setCurrentOption(0);
    }

    public void updateOptionsForward() {
        if (currentIndex < options.size() - 1) {
            currentIndex++;
            optionField.setCurrentOption(currentIndex);
            onSelectionChanged();
        }
    }

    public void updateOptionsBackward() {
        if (currentIndex > 0) {
            currentIndex--;
            optionField.setCurrentOption(currentIndex);
            onSelectionChanged();
        }
    }

    public String getCurrentValue() {
        return options.get(currentIndex);
    }

    // --- Selection Change Handler ---
    private void onSelectionChanged() {
        // if there is a dependant, update its options based on current value
        if (dependant != null && !optionsMap.isEmpty()) {
            String selectedValue = getCurrentValue();
            List<String> newDependantOptions = optionsMap.get(selectedValue);

            if (newDependantOptions != null) {
                dependant.setOptions(newDependantOptions);
                dependant.revalidate();
                dependant.repaint();
                dependant.onSelectionChanged();
            }
        }
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
    public void setAsSelected(boolean bool) {
        setSelected(bool);
        // This should be done ideally within the CustomisedButton class
        if (bool) {
            optionField.setForeground(getPrimaryColor());
            setBackground(getThirdColor());
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
