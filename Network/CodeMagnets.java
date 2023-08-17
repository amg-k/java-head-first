package Network;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.time.format.FormatStyle;
import java.util.concurrent.TimeUnit;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofLocalizedTime;

public class CodeMagnets {
    public static void main(String[] args) {
        InetSocketAddress server = new InetSocketAddress("127.0.0.1", 5000);
        try (SocketChannel channel = SocketChannel.open(server)) {
            PrintWriter writer = new PrintWriter(Channels.newWriter(channel, UTF_8));
            System.out.println("Obsługa sieci została uruchomiona");
            
            for (int i = 0; i < 10; i++) {
                String message = String.format("ping %s", i);
                writer.println(message);
                writer.flush();
                String currentTime = now().format(ofLocalizedTime(FormatStyle.MEDIUM));
                System.out.println(String.format("%s wysłano %s", currentTime, message));
                TimeUnit.SECONDS.sleep(1);
            } 
        } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
        }
    }
}
