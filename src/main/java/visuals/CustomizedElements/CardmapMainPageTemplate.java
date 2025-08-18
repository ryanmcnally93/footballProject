package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CardmapMainPageTemplate extends MainPageTemplate {

    private CardLayout layout;
    private JPanel pages;

    public CardmapMainPageTemplate(CardLayout cardLayout, JPanel pages){
        super();
        this.layout = cardLayout;
        this.pages = pages;

        getFooterPanel().addLeftAndRightButtons();
        addLeftAndRightActionListeners();
        addKeyListeners();
    }

    public void addLeftAndRightActionListeners() {
        getFooterPanel().getPrevButton().addActionListener(e -> {
            layout.previous(pages);
            moveButtonsWithUser_Backwards();
        });

        getFooterPanel().getNextButton().addActionListener(e -> {
            layout.next(pages);
            moveButtonsWithUser_Forwards();
        });
    }

    @Override
    protected AbstractAction getRightClickAction() {
        return new CustomRightClick();
    }

    public class CustomRightClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            layout.next(pages);
            moveButtonsWithUser_Forwards();
        }
    }

    public void addLabelToTitleLine(String text, int width, JPanel titleLine) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Menlo", Font.BOLD, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        if (text.equals("RAT.")) {
            label.setToolTipText("RATING");
        }
        setPermanentWidth(label, width);
        titleLine.add(label);
    }

    public void moveButtonsWithUser_Forwards() {}

    public void moveButtonsWithUser_Backwards() {}

    @Override
    protected AbstractAction getLeftClickAction() {
        return new CustomLeftClick();
    }

    public class CustomLeftClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            layout.previous(pages);
            moveButtonsWithUser_Backwards();
        }
    }

    public void setPages(JPanel pages) {
        this.pages = pages;
    }
}
