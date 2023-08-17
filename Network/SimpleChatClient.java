package Network;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.concurrent.*;
import javax.swing.*;
import java.awt.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SimpleChatClient {
    private JTextArea recievedMessages;
    private JTextField messageToSendField;
    private BufferedReader reader;
    private PrintWriter writer;

    public void doIt() {
        communicationConfigure();

        JScrollPane scrollableField = createScrollableTextArea();

        messageToSendField = new JTextField(20);

        JButton sendButton = new JButton("Wyślij");
        sendButton.addActionListener(e -> sendMessage());

        JPanel mainPanel = new JPanel();
        mainPanel.add(scrollableField);
        mainPanel.add(messageToSendField);
        mainPanel.add(sendButton);
        
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new MessageReciever());

        JFrame mainFrame = new JFrame("Prosty klient pogawędek");
        mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        mainFrame.setSize(400, 350);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private JScrollPane createScrollableTextArea() {
        recievedMessages = new JTextArea(15, 30);
        recievedMessages.setLineWrap(true);
        recievedMessages.setWrapStyleWord(true);
        recievedMessages.setEditable(false);
        JScrollPane scroll = new JScrollPane(recievedMessages);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroll;
    }

    private void communicationConfigure() {
        try {
            InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 5000);
            SocketChannel channelSC = SocketChannel.open(serverAddress);

            reader = new BufferedReader(Channels.newReader(channelSC, UTF_8));
            writer = new PrintWriter(Channels.newWriter(channelSC, UTF_8));

            System.out.println(String.format("Network connection configured. Client address and port: %s", 
                                                channelSC.getLocalAddress()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void sendMessage() {
        writer.println(messageToSendField.getText());
        writer.flush();
        messageToSendField.setText("");
        messageToSendField.requestFocus();
    }

    public class MessageReciever implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println(String.format("Odczyt %s", message));
                    recievedMessages.append(message + "\n");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new SimpleChatClient().doIt();
    }
}
