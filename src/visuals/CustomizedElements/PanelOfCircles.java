package visuals.CustomizedElements;
import java.awt.*;

public class PanelOfCircles extends GamePanel {
    private Color[] circleColors;  // Array to hold the colors of the circles

    public PanelOfCircles() {
        // Initialize the array of colors for four circles
        circleColors = new Color[] {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
        setPreferredSize(new Dimension(110, 100));  // Set preferred size of the panel
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Remove any default insets/margins
        Insets insets = getInsets();  // This could be checked if you added any insets or padding.
        int xOffset = insets.left;
        int yOffset = insets.top;

        // Width and height of each circle
        int circleDiameter = 20;
        int spacing = 10; // Space between circles

        // Loop to draw circles without margins
        for (int i = 0; i < circleColors.length; i++) {
            g2d.setColor(circleColors[i]);
            g2d.fillOval(xOffset + (i * (circleDiameter + spacing)), yOffset, circleDiameter, circleDiameter);
        }
    }

    // Method to change the color of a specific circle
    public void changeCircleColor(String speed) {
        int index = 0;

        if(speed.equals("slow")){
            index = 1;
        } else if (speed.equals("fast")){
            index = 2;
        } else if (speed.equals("fastest")){
            index = 3;
        }

        for(int i=0; i<circleColors.length; i++){
            if(i != index){
                circleColors[i] = Color.WHITE;
            } else {
                circleColors[i] = Color.GREEN;
            }
        }
    }
}
