package visuals.CustomizedElements;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class CustomizedButton extends JButton {

	private static final long serialVersionUID = 2300144898005916497L;

	public CustomizedButton(String text) {
		super(text);
		
	    setContentAreaFilled(false);
	    setOpaque(false);
	    setFocusPainted(false);
	    setMargin(new InsetsUIResource(-3, 14, -3, 14));
    	
    	addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(Color.BLACK);
            }
        });
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        if (getModel().isRollover()) {
            g.setColor(Color.LIGHT_GRAY.darker());
        } else if (getModel().isRollover()) {
            g.setColor(Color.LIGHT_GRAY.brighter());
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(g);
    }
	
}
