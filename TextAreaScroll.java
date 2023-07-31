import java.awt.*;
import javax.swing.*;

public class TextAreaScroll {
    public static void main(String[] args) {
        TextAreaScroll myTester = new TextAreaScroll();
        myTester.doIt();
    }

    public void doIt() {
        JFrame myFrame = new JFrame();
        JPanel myPanel = new JPanel();

        JButton myButton = new JButton("Click me!");
        
        JTextArea myText = new JTextArea(10, 20);
        myText.setLineWrap(true);
        myButton.addActionListener(ev -> myText.append("Button clicked!\n"));

        JScrollPane myScroll = new JScrollPane(myText);
        myScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        myScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        myPanel.add(myScroll);

        myFrame.getContentPane().add(BorderLayout.CENTER, myPanel);
        myFrame.getContentPane().add(BorderLayout.SOUTH, myButton);

        myFrame.setSize(300, 300);
        myFrame.setVisible(true);
    }
}
