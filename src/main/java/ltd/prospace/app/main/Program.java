package ltd.prospace.app.main;

import ltd.prospace.app.lib.RomanNumeral;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Program {

    private static Map<String, Character> intergalacticNumberMap = new HashMap<>();
    private static Map<String, Double> metalPrices = new HashMap<>();
    private static List<String> answers = new ArrayList<>();

    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir") + "\\test-input.txt";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            for (int i = 0; i < 3; i++) {
                while (true) {
                    if (i == 0) {
                        input1(line);
                    } else if (i == 1) {
                        input2(line);
                    } else {
                        input3(line);
                    }
                    line = reader.readLine();
                    if (line == null || line.equals("~")) {
                        line = reader.readLine();
                        break;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        printAnswers(answers);
    }

    private static void input1(String line) {
        System.out.println(line);
        String[] split = line.split(" is ");
        String split0 = split[0];
        if (split0.contains(" ")) {
            throw new IllegalArgumentException("no space allowed for intergalactic numeric digit!");
        }
        String split1 = split[1];
        if (split1.length() > 1 || (!split1.equals("I") && !split1.equals("V") && !split1.equals("X") && !split1.equals("L")
                && !split1.equals("C") && !split1.equals("D") && !split1.equals("M"))) {
            throw new IllegalArgumentException(split1 + " is invalid Roman Numeric Digit!");
        }
        intergalacticNumberMap.put(split0, split1.charAt(0));
    }

    private static void input2(String line) {
        System.out.println(line);
        final String[] split = line.split(" is ");
        String[] split0 = split[0].split(" ");
        String metal = split0[split0.length - 1];
        int num = Integer.parseInt(split[1].split(" ")[0]);
        inputMetalPrices(metal, num, intergalacticToRoman(split0, false));
    }

    private static void input3(String line) {
        System.out.println(line);
        try {
            String intergalacticWords = line.split(" is ")[1].split("\\?")[0];
            String[] intergalacticWordsArr = intergalacticWords.split(" ");
            if (line.toUpperCase().contains("HOW MUCH")) {
                int romanToInt = RomanNumeral.romanToInt(intergalacticToRoman(intergalacticWordsArr, true));
                answers.add(intergalacticWords + " is " + romanToInt);
            } else if (line.toUpperCase().contains("HOW MANY CREDITS")) {
                String metal = intergalacticWordsArr[intergalacticWordsArr.length - 1];
                int romanToInt = RomanNumeral.romanToInt(intergalacticToRoman(intergalacticWordsArr, false));
                double price = romanToInt * metalPrices.get(metal);
                answers.add(intergalacticWords + " is " + price + " credits");
            } else {
                answers.add("I have no idea what you are talking about");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            answers.add("I have no idea what you are talking about");
        } catch (IllegalArgumentException e) {
            answers.add("invalid roman number");
        }
    }

    /**
     * put metal and its price to the static member metalPrices
     *
     * @param metal
     * @param num
     */
    private static void inputMetalPrices(String metal, int num, String romanNumber) {
        int romanToInt = RomanNumeral.romanToInt(romanNumber);
        metalPrices.put(metal, (num * 1.0) / romanToInt);
        System.out.println("1 " + metal + " is equal to " + metalPrices.get(metal) + " credits");
    }

    /**
     * @param intergalacticWords
     * @param includeLastIndex,  false if the last word of intergalacticWords is "credits"
     * @return roman number
     */
    private static String intergalacticToRoman(String[] intergalacticWords, boolean includeLastIndex) {
        String romanNumber = "";
        int limit = includeLastIndex ? intergalacticWords.length : intergalacticWords.length - 1;
        for (int i = 0; i < limit; i++) {
            romanNumber += intergalacticNumberMap.get(intergalacticWords[i]);
        }
        return romanNumber;
    }

    private static void printAnswers(List<String> list) {
        System.out.println("\nanswers");
        for (String str : list) {
            System.out.println(str);
        }
    }
}
