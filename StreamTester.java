import java.util.*;
import java.util.stream.*;

public class StreamTester {
    public static void main(String[] args) {
        List<String> text = List.of("Ja", "jestem", "wzorcową", "Listą", "łańcuchów", "znaków");
        
/*         Stream<String> stream = text.stream();
        Stream<String> limitStream = stream.limit(4);
        List<String> resultList = limitStream.collect(Collectors.toList()); */

        List<String> resultList = text.stream().sorted((s1, s2) -> s1.compareToIgnoreCase(s2)).skip(2).limit(5).collect(Collectors.toList());

        System.out.println("Stream = " + resultList);
        System.out.println("List = " + text);
    }

}
