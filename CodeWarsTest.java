import java.util.*;

class CodeWarsTest {
    public static void main(String[] args) {
        String a = "8768879868";
        String b = "7799087";

        var aBuff = new StringBuffer(a);
        var bBuff = new StringBuffer(b);

        StringBuffer[] buffArr = fillZeros(aBuff, bBuff);

        String sumResult = stringSum("5", "8");
        String sumResUnity = sumResult.length() > 1 ? String.valueOf(sumResult.charAt(1)) : sumResult;

        System.out.println(stringSum(buffArr[0].substring(buffArr[0].length() - 1), buffArr[1].substring(buffArr[1].length() - 1)));
    } 
    
    static private StringBuffer[] fillZeros(StringBuffer a, StringBuffer b) {
        var shortBuff = a.length() >= b.length() ? b : a;
        var longBuff = shortBuff.equals(a) ? b : a;

        shortBuff.reverse();
        while(shortBuff.length() != longBuff.length()) {
            shortBuff.append("0");
        }
        shortBuff.reverse();
        StringBuffer[] result = new StringBuffer[2];
        result[0] = longBuff;
        result[1] = shortBuff;
        
        return result;
    }

    static private String stringSum(String a, String b) {
        //if (a.length() > 1 || a.length() > 1) { return "input format incorrect"; }
        return String.valueOf(Integer.parseInt(a) + Integer.parseInt(b));    
    }
}