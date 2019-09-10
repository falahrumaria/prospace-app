package ltd.prospace.app.lib;

import java.util.HashMap;
import java.util.Map;

/**
 * written by rumaria
 */
public class RomanNumeral {

    /**
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        final Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int allSum = 0;
        int num;
        int i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            num = map.get(ch);
            switch (ch) {
                case 'I':
                    i++;
                    if (i < s.length() && (s.charAt(i) == 'V' || s.charAt(i) == 'X')) {
                        allSum += map.get(s.charAt(i)) - num;
                        i++;
                    } else {
                        allSum += num;
                    }
                    break;
                case 'X':
                    i++;
                    if (i < s.length() && (s.charAt(i) == 'L' || s.charAt(i) == 'C')) {
                        allSum += map.get(s.charAt(i)) - num;
                        i++;
                    } else {
                        allSum += num;
                    }
                    break;
                case 'C':
                    i++;
                    if (i < s.length() && (s.charAt(i) == 'D' || s.charAt(i) == 'M')) {
                        allSum += map.get(s.charAt(i)) - num;
                        i++;
                    } else {
                        allSum += num;
                    }
                    break;
                default:
                    allSum += num;
                    i++;
                    break;
            }
        }
        return allSum;
    }

    /**
     * @param num in the range 0 < num < 4000
     * @return
     */
    public static String intToRoman(int num) {
        if (num < 1 || num > 3999) {
            throw new IllegalArgumentException("input argument is beyond the scope of roman number");
        }
        final Map<Integer, char[]> map = new HashMap<>();
        map.put(0, new char[]{'I', 'V', 'X'});
        map.put(1, new char[]{'X', 'L', 'C'});
        map.put(2, new char[]{'C', 'D', 'M'});
        map.put(3, new char[]{'M', 'M', 'M'});

        String numAsStr = String.valueOf(num);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numAsStr.length(); i++) {
            char ch = numAsStr.charAt(i);
            int key = numAsStr.length() - 1 - i;
            switch (ch) {
                case '1':
                    sb.append(map.get(key)[0]);
                    break;
                case '2':
                    sb.append(map.get(key)[0]).append(map.get(key)[0]);
                    break;
                case '3':
                    sb.append(map.get(key)[0]).append(map.get(key)[0]).append(map.get(key)[0]);
                    break;
                case '4':
                    sb.append(map.get(key)[0]);
                case '5':
                    sb.append(map.get(key)[1]);
                    break;
                case '6':
                    sb.append(map.get(key)[1]).append(map.get(key)[0]);
                    break;
                case '7':
                    sb.append(map.get(key)[1]).append(map.get(key)[0]).append(map.get(key)[0]);
                    break;
                case '8':
                    sb.append(map.get(key)[1]).append(map.get(key)[0]).append(map.get(key)[0]).append(map.get(key)[0]);
                    break;
                case '9':
                    sb.append(map.get(key)[0]).append(map.get(key)[2]);
                    break;
                default:
                    break;
            }
        }
        return sb.toString();
    }
}
