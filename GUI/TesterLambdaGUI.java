package GUI;

import java.awt.*;
import javax.swing.*;

public class TesterLambdaGUI {
    private JFrame myFrame;
    private MyGraphicPanel myPanel;
    private JButton myButtonRect;
    private JButton myButtonLabel;
    private JLabel myLabel;

    public static void main(String[] args) {
        TesterGUI myTester = new TesterGUI();
        myTester.doIt();
    }

    public void doIt() {
        myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myPanel = new MyGraphicPanel();
        myButtonRect = new JButton("New Color!");
        myButtonLabel = new JButton("Change label!");
        myLabel = new JLabel("Lorem ipsum...");

        myButtonRect.addActionListener(event -> myFrame.repaint());
        myButtonLabel.addActionListener(event -> myLabel.setText("...dolor sit amet..."));

        myFrame.getContentPane().add(BorderLayout.CENTER, myPanel);
        myFrame.getContentPane().add(BorderLayout.SOUTH, myButtonRect);
        myFrame.getContentPane().add(BorderLayout.WEST, myLabel);
        myFrame.getContentPane().add(BorderLayout.EAST, myButtonLabel);
        
        myFrame.setSize(600, 500);
        myFrame.setVisible(true);
    }
}
