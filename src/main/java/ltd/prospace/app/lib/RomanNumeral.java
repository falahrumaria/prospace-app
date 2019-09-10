package ltd.prospace.app.lib;

import java.util.HashMap;
import java.util.Map;

public class RomanNumeral {

    /**
     * @param romanNum
     * @return
     */
    public static int romanToInt(String romanNum) {
        final String message = "invalid roman number : " + romanNum;
        int num;
        int result = doConvert(romanNum, message);
        boolean reverseConvert;
        try {
            reverseConvert = romanNum.equals(intToRoman(result));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(message);
        }
        if (!reverseConvert) {
            throw new IllegalArgumentException(message);
        }
        return result;
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

    private static int doConvert(String romanNum, String message) {
        final Map<Character, Integer> romanCharValueMap = getRomanCharValueMap();
        int result = 0;
        int num;
        int i = 0;
        while (i < romanNum.length()) {
            char ch = romanNum.charAt(i);
            try {
                num = romanCharValueMap.get(ch);
            } catch (NullPointerException e) {
                throw new IllegalArgumentException(message);
            }
            switch (ch) {
                case 'I':
                    i++;
                    if (i < romanNum.length() && (romanNum.charAt(i) == 'V' || romanNum.charAt(i) == 'X')) {
                        result += romanCharValueMap.get(romanNum.charAt(i)) - num;
                        i++;
                    } else {
                        result += num;
                    }
                    break;
                case 'X':
                    i++;
                    if (i < romanNum.length() && (romanNum.charAt(i) == 'L' || romanNum.charAt(i) == 'C')) {
                        result += romanCharValueMap.get(romanNum.charAt(i)) - num;
                        i++;
                    } else {
                        result += num;
                    }
                    break;
                case 'C':
                    i++;
                    if (i < romanNum.length() && (romanNum.charAt(i) == 'D' || romanNum.charAt(i) == 'M')) {
                        result += romanCharValueMap.get(romanNum.charAt(i)) - num;
                        i++;
                    } else {
                        result += num;
                    }
                    break;
                default:
                    result += num;
                    i++;
                    break;
            }
        }
        return result;
    }

    private static Map<Character, Integer> getRomanCharValueMap() {
        final Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        return map;
    }
}
