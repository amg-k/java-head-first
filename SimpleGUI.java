import javax.swing.*;
import java.awt.event.*;

public class SimpleGUI implements ActionListener {
    private JButton button;
    public static void main(String[] args) {
        SimpleGUI sGUI = new SimpleGUI();
        sGUI.doIt();
    }

    public void doIt() {
        JFrame frame = new JFrame();
        button = new JButton("Click me!");

        button.addActionListener(this);
        
        frame.getContentPane().add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        button.setText("I'm clicked!");
    }
}
