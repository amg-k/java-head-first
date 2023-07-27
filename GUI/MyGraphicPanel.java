package GUI;

import javax.swing.*;
import java.awt.*;

public class MyGraphicPanel extends JPanel {
    public void paintComponent(Graphics g) {
        g.setColor(Color.green);

        //g.fillRect(20, 50, 230, 150);

        Image myImage = new ImageIcon(getClass().getResource("boznanska.jpg")).getImage();

        g.drawImage(myImage, 15, 15, this);
    }
}
