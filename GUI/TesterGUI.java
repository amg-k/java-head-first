package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TesterGUI implements ActionListener {
    private JFrame myFrame;
    private MyGraphicPanel myPanel;
    private JButton myButton;

    public static void main(String[] args) {
        TesterGUI myTester = new TesterGUI();
        myTester.doIt();
    }

    public void doIt() {
        myFrame = new JFrame();
        myPanel = new MyGraphicPanel();
        myButton = new JButton("New Color!");

        myButton.addActionListener(this);

        myFrame.getContentPane().add(BorderLayout.CENTER, myPanel);
        myFrame.getContentPane().add(BorderLayout.SOUTH, myButton);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(600, 500);
        myFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent myEvent) {
        myFrame.repaint();
    }
}
