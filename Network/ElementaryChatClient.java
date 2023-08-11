package Network;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ElementaryChatClient {
    private JTextField messageField;
    private PrintWriter writer;

    public void run() {
        communicationConfigure();

        messageField = new JTextField(20);

        JButton sendButton = new JButton("Wyślij");
        sendButton.addActionListener(ev -> sendMessage());

        JPanel mainPanel = new JPanel();
        mainPanel.add(messageField);
        mainPanel.add(sendButton);
        JFrame myFrame = new JFrame("Elementary Chat Client");
        myFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        myFrame.setSize(400, 100);
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void communicationConfigure() {
        try {
            InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 5000);

            SocketChannel channelSC = SocketChannel.open(serverAddress);
            writer = new PrintWriter(Channels.newWriter(channelSC, UTF_8));
            System.out.println(String.format("Network connection ready. Client address: %s", channelSC.getLocalAddress()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void sendMessage() {
        writer.println(messageField.getText());
        writer.flush();
        messageField.setText("");
        messageField.requestFocus();
    }

    public static void main(String[] args) {
        new ElementaryChatClient().run();
    }
}
