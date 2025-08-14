package visuals.CustomizedElements;

import java.awt.*;
import java.util.ArrayList;

public class PanelOfCircles extends GamePanel {
    private ArrayList<Circle> circles;
    int position = 0;

    public PanelOfCircles() {
        // Initialize the array of colors for four circles
        setPreferredSize(new Dimension(110, 100));  // Set preferred size of the panel
        setBackground(Color.LIGHT_GRAY);
        circles = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            circles.add(new Circle());
        }
    }

    public class Circle {
        private Color colour;
        public Circle(){};
        public void draw(Graphics2D g2d, int i) {
            // Remove any default insets/margins
            Insets insets = getInsets();  // This could be checked if you added any insets or padding.
            int xOffset = insets.left;
            int yOffset = insets.top;

            // Width and height of each circle
            int circleDiameter = 10;
            int spacing = 6; // Space between circles

            g2d.setColor(colour);
            g2d.fillOval(xOffset + (i * (circleDiameter + spacing)), yOffset, circleDiameter, circleDiameter);
        }

        public Color getColour() {
            return colour;
        }

        public void setColour(Color colour) {
            this.colour = colour;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Loop to draw circles without margins
        for (int i = 0; i < circles.size(); i++) {
            circles.get(i).draw(g2d, i);
        }
    }

    // Method to change the color of a specific circle
    public void changeCircleColor(String speed) {
        switch (speed) {
            case "slowest" -> position = 0;
            case "slow" -> position = 1;
            case "fast" -> position = 2;
            case "fastest" -> position = 3;
        }

        for(int i=0; i<circles.size(); i++){
            if(i != position){
                circles.get(i).setColour(Color.WHITE);
            } else {
                circles.get(i).setColour(Color.GREEN);
            }
        }

        revalidate();
        repaint();
    }
}
