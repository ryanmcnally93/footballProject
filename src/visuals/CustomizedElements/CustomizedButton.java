package visuals.CustomizedElements;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class CustomizedButton extends JButton {

	private static final long serialVersionUID = 2300144898005916497L;

	public CustomizedButton(String text) {
		super(text);
		applyNimbus();
	}
	
	public void applyNimbus() {
//		try {
//            // Save the current look and feel
//            LookAndFeel currentLookAndFeel = UIManager.getLookAndFeel();
//
//            // Set Nimbus Look and Feel
//            UIManager.setLookAndFeel(new NimbusLookAndFeel());
//            SwingUtilities.updateComponentTreeUI(this);
//
//            // Restore the previous look and feel
//            UIManager.setLookAndFeel(currentLookAndFeel);
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
	}
	
}
