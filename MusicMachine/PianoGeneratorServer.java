package MusicMachine;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class PianoGeneratorServer {
    private final List<ObjectOutputStream> exitClientStream = new ArrayList<>();

    public static void main(String[] args) {
        new PianoGeneratorServer().doIt();
    }

    public void doIt() {
        try {
            ServerSocket myServerSocket = new ServerSocket(4242);
            ExecutorService threadsPool = Executors.newCachedThreadPool();

            while (!myServerSocket.isClosed()) {
                Socket myClientSocket = myServerSocket.accept();
                ObjectOutputStream exit = new ObjectOutputStream(myClientSocket.getOutputStream());
                exitClientStream.add(exit);

                ClientService forClientService = new ClientService(myClientSocket);
                threadsPool.execute(forClientService);
                System.out.println("Nawiązano połączenie");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendToEveryone(Object nameAndMessage, Object beatSequence) {
        for (ObjectOutputStream clientStream : exitClientStream) {
            try {
                clientStream.writeObject(nameAndMessage);
                clientStream.writeObject(beatSequence);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public class ClientService implements Runnable {
        private ObjectInputStream enter;

        public ClientService(Socket socket) {
            try {
                enter = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        public void run() {
            Object nameAndMessage;
            Object beatSequence;
            try {
                while ((nameAndMessage = enter.readObject()) != null) {
                    beatSequence = enter.readObject();
                    
                    System.out.println("odczyt dwóch obiektów");
                    sendToEveryone(nameAndMessage, beatSequence);
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

}
