package Network;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class DailyAdviceClient {
    public void doIt() {
        InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 5000);
        try (SocketChannel channelSC = SocketChannel.open(serverAddress)) {
            Reader channelReader = Channels.newReader(channelSC, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(channelReader);

            String advice = reader.readLine();
            System.out.println("Porada na dziś: " + advice);
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DailyAdviceClient().doIt();
    }
}
