package visuals;
import java.util.List;
import java.util.Map;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class MatchWatch extends MatchFrames {
    private static final long serialVersionUID = 5937268249853937276L;
    private JLabel label;

    public MatchWatch(CardLayout layout, JPanel pages, Map<String, JPanel> cardMap) {
        super(layout, pages, cardMap);
        
        setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        setBackground(Color.LIGHT_GRAY);

        // Header Box
        Box Header = Box.createVerticalBox();
        add(Header, BorderLayout.NORTH);
        label = new JLabel("Arsenal 0 : 0 Tottenham");
        Header.add(label);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        Header.setPreferredSize(new Dimension(500, 150));

        // Center Box
        Box Center = Box.createVerticalBox();
        Center.setPreferredSize(new Dimension(500, 250));

        CustomProgressBar progressBar_1 = new CustomProgressBar();
        progressBar_1.setBorder(new EmptyBorder(20, 20, 10, 20));
        progressBar_1.setValue(50);
        Center.add(progressBar_1);

        CustomProgressBar progressBar = new CustomProgressBar();
        progressBar.setBorder(new EmptyBorder(10, 20, 10, 20));
        progressBar.setValue(50);
        Center.add(progressBar);

        CustomProgressBar progressBar_2 = new CustomProgressBar();
        progressBar_2.setBorder(new EmptyBorder(10, 20, 20, 20));
        progressBar_2.setValue(50);
        Center.add(progressBar_2);

        JButton PlayButton = new JButton("Play");
        PlayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        PlayButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick();
                System.out.println("Play button clicked!");
            }
        });
        Center.add(PlayButton);
        add(Center, BorderLayout.CENTER);

        SlidingPanel slidingPanel = super.getSlidingPanel();
        slidingPanel.setBounds(0, 0, 800, 600);
    }
    
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        
//    }

	public void handleClick() {
		match.startMatch(getGraphics(), super.getCardMap());
	}
	
	public void updateScoreBoard(int home, int away) {
		System.out.println();
		setLabel("Arsenal " + home + " : " + away + " Tottenham");
		repaint();
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(String string) {
		this.label.setText(string);
	}
	
	

}