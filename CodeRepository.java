import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.*;

public class CodeRepository {
    
    public static void main(String[] args) {
       
        System.out.println(CodeRepository.toRoman(1482));

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

    //użycie cyfr z przekazaniej liczby int tak aby stworzyć najwięszą możliwą liczbę int
    //uszeregowanie w kolejności malejącej
    static int sortDescending(int num) {
        String[] strArr = String.valueOf(num).split("");
        Arrays.sort(strArr, Collections.reverseOrder());
        return Integer.parseInt(String.join("", strArr));
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

    //przedstawia tablicę 10 cyfr w formie numeru tel. jako łańcuch znaków
    //jako drugi parametr metody .format podana jest Integer[] (opakowanie w Integr poprzez .boxed())
    static String createPhoneNumber(int[] numbers) {
        return String.format("(%d%d%d) %d%d%d-%d%d%d%d", Arrays.stream(numbers).boxed().toArray());
    }
    
    //sprawdź czy liczba jest min 100 i jeśli tak czy spełania min jedno kryterium: (1) cyfra z samymi zerami, (2) taka sama czytając z lewej i z prawej
    //(3) rosnąca ze skokiem o 1, (4) malejąca ze skokiem o 1, (5) jest równa któejś z liczb przekazanych w tablicy
    static boolean checkCriteria(int number, int[] awesomePhrases) {
        var strNum = String.valueOf(number);
        if (!(number > 99)) return false;
        return strNum.matches("\\d0+") ||
               new StringBuilder(strNum).reverse().toString().equals(strNum) ||
               "1234567890".contains(strNum) ||
               "9876543210".contains(strNum) ||
               IntStream.of(awesomePhrases).anyMatch(x -> x == number);
    }

    //zwraca inną wartość liczbową po spełnieniu kryteriów przez kolejne trzy liczby
    static int isInteresting(int number, int[] awesomePhrases) {
        return checkCriteria(number, awesomePhrases) ? 2 :
               checkCriteria(number + 1, awesomePhrases) ? 1 :
               checkCriteria(number + 2, awesomePhrases) ? 1 : 0;
    }

    //startWith zamiast replaceFirst - i wtedy stała w kolejności malejącej

    //zmienia liczbę arabską na liczbę rzymską
    static String toRoman(int n) {
        var romanB = new StringBuilder();
        var ROMAN_DECIMAL = new LinkedHashMap<String, Integer>() {{
          put("M", 1000);
          put("CM", 900);
          put("D", 500);
          put("CD", 400);
          put("C", 100);
          put("XC", 90);
          put("L", 50);
          put("XL", 40);
          put("X", 10);
          put("IX", 9);
          put("V", 5);
          put("IV", 4);
          put("I", 1);
        }};
        
        for (String key : ROMAN_DECIMAL.keySet()) {
          while (n >= ROMAN_DECIMAL.get(key)) {
            romanB.append(key);
            n = n - ROMAN_DECIMAL.get(key);
          }
        }
        return romanB.toString();
    }
    
    //zmienia wartość liczby rzymskiej na liczbę arabską
    static int fromRoman(String romanNumeral) {
        int romanNum = 0;
        var ROMAN_DECIMAL = new LinkedHashMap<String, Integer>() {{
          put("CM", 900);
          put("M", 1000);
          put("CD", 400);
          put("D", 500);
          put("XC", 90);
          put("C", 100);
          put("XL", 40);
          put("L", 50);
          put("IX", 9);
          put("X", 10);
          put("IV", 4);
          put("V", 5);
          put("I", 1);
        }};
        
        for (String key : ROMAN_DECIMAL.keySet()) {
          while (romanNumeral.contains(key)) {
            romanNumeral = romanNumeral.replaceFirst(key, "");
            romanNum = romanNum + ROMAN_DECIMAL.get(key);
          }
        }    
        return romanNum;
    }
}