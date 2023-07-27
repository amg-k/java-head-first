package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MyGraphicPanel extends JPanel {
    public void paintComponent(Graphics g) {
        //Graphics2D g2d = (Graphics2D) g;
        //g2d.setColor(Color.green);

        //Image myImage = new ImageIcon(getClass().getResource("boznanska.jpg")).getImage();

        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //g2d.drawImage(myImage, 15, 15, this);

        g.setColor(getRandomColor());

        //g2d.drawLine(0, 0, 600, 500);

        //GradientPaint myGradient = new GradientPaint(0, 0, getRandomColor(), 
        //                                            100, 100, getRandomColor());

        //g2d.setPaint(myGradient);
        g.fillRect(getRandomInt(300), getRandomInt(300), 100, 100);
        
    }

    private Color getRandomColor() {
        Random myRandom = new Random();
        int myRed = myRandom.nextInt(256);
        int myGreen = myRandom.nextInt(256);
        int myBlue = myRandom.nextInt(256);

        Color myRandomColor = new Color(myRed, myGreen, myBlue);
        return myRandomColor;  
    }

    private int getRandomInt(int max) {
        Random myRandom = new Random();
        int myRandomInt = myRandom.nextInt(max);
        return myRandomInt;
    }

}
