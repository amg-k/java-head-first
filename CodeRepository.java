import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.*;

public class CodeRepository {
    
    public static void main(String[] args) {
       
        System.out.println(Arrays.toString(CodeRepository.removeEveryOther(new Object[] { "Hello", "Goodbye", "Hello Again" })));

    }
    
    /* OPERACJE WEJŚCIA I WYJŚCIA */

    //odczyt z pliku oraz wyświetlenie zawartości:
    static void readFile() {
        try {
            Scanner fileIn = new Scanner(Path.of("C:\\Users\\przem\\Desktop\\plik.txt"), 
                                    StandardCharsets.UTF_8);
        String text;
        while (fileIn.hasNext()) {
            text = fileIn.nextLine();
            System.out.println(text);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //zapis tekstu do pliku:
    static void saveFile() {
        try {
            PrintWriter fileOut = new PrintWriter("C:\\Users\\przem\\Desktop\\plik.txt");
            fileOut.println("lorem ipsum");
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* PRZETWARZANIE ŁAŃCUCHÓW */

    //usunięcie z łańcucha podanych znaków (np. samogłosek) za pomocą regex
    static String deleteVowels(String str) {
        return str.replaceAll("[aAeEiIoOuU]", "");
    }

    //usunięcie pierwszego i ostatniego znaku
    static String deleteFirstLast(String str) {
        return str.substring(1, str.length() - 1);
    }

    //łańcuch zawierający cyfry oddzielone spacjami - przetworzenie na int 
    //i zwrócenie wartości max i min w postaci łańcucha
    static String stringMinMax(String str) {
        var stats = Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).summaryStatistics();
        //var stats = Arrays.stream(str.split(" ")).mapToInt(e -> Integer.valueOf(e)).summaryStatistics();
        //Integer.parseInt() zwraca int; Integer.valueOf() zwraca Integer
        String min = String.valueOf(stats.getMin());
        String max = String.valueOf(stats.getMax());
        return String.join(" ", min, max);
    }

    //połączenie łańcuchów, eliminacja powtórzeń liter, uszeregowanie alfabetyczne
    static String twoStringsOrder(String str1, String str2) {
        String str = str1 + str2;
        return str.chars()
                    .distinct()
                    .sorted()
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

        //lub
/*         StringBuilder strB = new StringBuilder();
        (str1 + str2).chars()
                        .distinct()
                        .sorted()
                        .forEach(e -> strB.appendCodePoint(e));
        return strB.toString(); */
    }

    /* PRZETWARZANIE KOLEKCJI, TABLIC */

    //usunięcie z listy elementów nie będących typu Integer
    static List<Object> filterInteger(List<Object> list) {
        return list.stream().filter(e -> (e instanceof Integer)).toList();
    }

    //kolekcja dwuelementowych tablic - operacja na elementach i podanie sumy
    static int sumArraysList(ArrayList<int[]> list) {
        return list.stream().mapToInt(e -> (e[0] - e[1])).sum();
    }

    //tablicę Object[] zredukować aby pozostały tylko co drugie elementy
    //schemat działania - wyznaczyć co drugi indeks i za jego pomocą wskazać elementy do pozostawienia
    static Object[] removeEveryOther(Object[] arr) {
        return IntStream.range(0, arr.length).filter(n -> n % 2 == 0).mapToObj(x -> arr[x]).toArray();
    }
}