package Network;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ElementaryChatServer {
    private final List<PrintWriter> clientsWriters = new ArrayList<>();
    
    public static void main(String[] args) {
        new ElementaryChatServer().run();
    }

    public void run() {
        ExecutorService threadsPool = Executors.newCachedThreadPool();
        try {
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(5000));

            while (serverChannel.isOpen()) {
                SocketChannel clientChannel = serverChannel.accept();
                PrintWriter writer = new PrintWriter(Channels.newWriter(clientChannel, UTF_8));
                clientsWriters.add(writer);
                threadsPool.submit(new ClientService(clientChannel));
                System.out.println("connection established");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void sendToAll(String message) {
        for (PrintWriter writer : clientsWriters) {
            writer.println(message);
            writer.flush();
        }
    }

    public class ClientService implements Runnable {
        BufferedReader reader;
        SocketChannel channel;

        public ClientService(SocketChannel clientChannel) {
            channel = clientChannel;
            reader = new BufferedReader(Channels.newReader(channel, UTF_8));
        }

        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("Readed: " + message);
                    sendToAll(message);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
