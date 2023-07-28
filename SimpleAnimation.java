import java.awt.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class SimpleAnimation {
    private JFrame myFrame;
    private AnimationPanel myPanel;
    private int xValue = 20;
    private int yValue = 50;
    private int animationStep = 1;

    public static void main(String[] args) {
        SimpleAnimation myAnimation = new SimpleAnimation();
        myAnimation.letsAnimate();
    }

    void letsAnimate() {
        myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myPanel = new AnimationPanel();

        myFrame.getContentPane().add(BorderLayout.CENTER, myPanel);
        myFrame.setSize(800, 600);
        myFrame.setVisible(true);

        while (xValue <= 400) {
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            xValue += animationStep;
            yValue += animationStep;
            myFrame.repaint();
        }
    }
    
    class AnimationPanel extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.green);
            g.fillOval(xValue, yValue, 100, 100);
        }
    }
}
