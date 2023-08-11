package Network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Random;

public class DailyAdviceServer {
    final private String[] adviceList = {
        "Advice 1",
        "Advice 2",
        "Advice 3",
        "Advice 4",
        "Advice 5"};
    private final Random myRandom = new Random();

    public void doIt() {
        try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            serverChannel.bind(new InetSocketAddress(5000));

            while (serverChannel.isOpen()) {
                SocketChannel clientChannel = serverChannel.accept();
                PrintWriter writer = new PrintWriter(Channels.newOutputStream(clientChannel));

                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getAdvice() {
        int adviceNum = myRandom.nextInt(adviceList.length);
        return adviceList[adviceNum];
    }

    public static void main (String[] args) {
        new DailyAdviceServer().doIt();
    }
}
