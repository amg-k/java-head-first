package GUI;

import javax.swing.*;

public class TesterGUI {
    private MyGraphicPanel myPanel;

    public static void main(String[] args) {
        TesterGUI myTester = new TesterGUI();
        myTester.doIt();
    }

    public void doIt() {
        JFrame myFrame = new JFrame();
        myPanel = new MyGraphicPanel();

        myFrame.getContentPane().add(myPanel);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(1500, 1000);
        myFrame.setVisible(true);
    }
}
