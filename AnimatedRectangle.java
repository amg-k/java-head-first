import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class AnimatedRectangle {
    int x = 1;
    int y = 1;

    public static void main(String[] args) {
        AnimatedRectangle myAnimation = new AnimatedRectangle();
        myAnimation.doIt();
    }

    private void doIt() {
        JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyAnimationPanel myPanel = new MyAnimationPanel();
        myFrame.getContentPane().add(myPanel);
        myFrame.setSize(520, 290);
        myFrame.setVisible(true);

        for (int i = 0; i < 124; i++, y++, x++) {
            x++;
            myPanel.repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (Exception ex) { }
        }
    }

    public class MyAnimationPanel extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(0, 0, 500, 250);
            g.setColor(Color.blue);
            g.fillRect(x, y, 500-x*2, 250-y*2);
        }
    }
}