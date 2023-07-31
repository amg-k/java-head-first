import java.awt.*;
import javax.swing.*;

public class LayoutTester {
    public static void main(String[] args) {
        LayoutTester myTester = new LayoutTester();
        myTester.doIt();
    }

    public void doIt() {
        JFrame myFrame = new JFrame();
        JPanel myPanel = new JPanel();
        JButton myButton1 = new JButton("Alfa-Alfa-Alfa");
        JButton myButton2 = new JButton("Bravo-Bravo-Bravo");
        JButton myButton3 = new JButton("Charlie-Charlie");

        myPanel.setBackground(Color.darkGray);
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

        myPanel.add(myButton1);
        myPanel.add(myButton2);
        myPanel.add(myButton3);

        myFrame.getContentPane().add(BorderLayout.EAST, myPanel);
        myFrame.setSize(300, 300);
        myFrame.setVisible(true);
    }
}
