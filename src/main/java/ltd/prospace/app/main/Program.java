package ltd.prospace.app.main;

import ltd.prospace.app.lib.RomanNumeral;

import java.util.*;

public class Program {

    private static Map<String, Character> intergalacticNumberMap = new HashMap<>();
    private static Map<String, Double> metalPrices = new HashMap<>();
    private static List<String> answers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        input1(in);
        input2(in);
        input3(in);

        printAnswers(answers);
    }

    private static void input1(Scanner in) {
        char[] romanDigits = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        System.out.println("Input 1:");
        for (int i = 0; i < romanDigits.length; i++) {
            System.out.print(romanDigits[i] + " = ");
            String input = in.nextLine();
            while (input.contains(" ")) {
                System.out.print("forbidden, " + romanDigits[i] + " = ");
                input = in.nextLine();
            }
            intergalacticNumberMap.put(input, romanDigits[i]);
        }
    }

    private static void input2(Scanner in) {
        System.out.println("Input 2:");
        while (true) {
            String input = in.nextLine();
            final String[] splitStrings = input.split(" is ");
            String[] arrayOfStringBeforeIs = splitStrings[0].split(" ");
            String metal = arrayOfStringBeforeIs[arrayOfStringBeforeIs.length - 1];
            int num = Integer.parseInt(splitStrings[1].split(" ")[0]);
            inputMetalPrices(metal, num, intergalacticToRoman(arrayOfStringBeforeIs, false));
            askContinue();
            if (!in.nextLine().equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

    private static void input3(Scanner in) {
        System.out.println("Input 3:");
        while (true) {
            String input = in.nextLine();
            try {
                String intergalacticWords = input.split(" is ")[1].split("\\?")[0];
                String[] intergalacticWordsArr = intergalacticWords.split(" ");
                if (input.toUpperCase().contains("HOW MUCH")) {
                    int romanToInt = RomanNumeral.romanToInt(intergalacticToRoman(intergalacticWordsArr, true));
                    answers.add(intergalacticWords + " is " + romanToInt);
                } else if (input.toUpperCase().contains("HOW MANY CREDITS")) {
                    String metal = intergalacticWordsArr[intergalacticWordsArr.length - 1];
                    int romanToInt = RomanNumeral.romanToInt(intergalacticToRoman(intergalacticWordsArr, false));
                    double price = romanToInt * metalPrices.get(metal);
                    answers.add(intergalacticWords + " is " + price + " credits");
                } else {
                    answers.add("I have no idea what you are talking about");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                answers.add("I have no idea what you are talking about");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                answers.add("I have no idea what you are talking about");
            }
            askContinue();
            if (!in.nextLine().equalsIgnoreCase("Y")) {
                break;
            }
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

    private static void askContinue() {
        System.out.println("Continue (Y/N)? ");
    }

    private static void printAnswers(List<String> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }
    }
}
