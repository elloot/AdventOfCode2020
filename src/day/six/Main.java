package day.six;
import AOCUtil.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String inputString = FileReader.readFile("six/input.txt");
        ArrayList<String> groups = new ArrayList<>();
        populateSeparatedPassportArray(inputString, groups);

        int numYesAnswers = 0;

        for (String group : groups) {
            String[] currentAnswers = group.split("\n");
            Set<Character> yesAnswers = new HashSet<>();
            for (String answers : currentAnswers) {
                for (int j = 0; j < answers.length(); j++) {
                    yesAnswers.add(answers.charAt(j));
                }
            }
            //numYesAnswers += yesAnswers.size();
            for (Character question : yesAnswers) {
                boolean answeredByEveryone = true;
                for (String answers : currentAnswers) {
                    if (!answers.contains(question.toString())) {
                        answeredByEveryone = false;
                        break;
                    }
                }
                if (answeredByEveryone) {
                    numYesAnswers++;
                }
            }
        }

        System.out.println(numYesAnswers);
    }

    private static void populateSeparatedPassportArray(String clumpedPassports, ArrayList<String> separatedPassports) {
        char currentChar;
        char nextChar = clumpedPassports.charAt(1);
        int passportStartingIndex = 0;
        int passportEndingIndex = 0;

        for (int i = 0; i < clumpedPassports.length(); i++) {
            currentChar = clumpedPassports.charAt(i);
            if (i + 1 < clumpedPassports.length()) nextChar = clumpedPassports.charAt(i + 1);
            passportEndingIndex = i;
            if (passportHasBeenFound(currentChar, nextChar)) {
                String passport = getPassport(passportStartingIndex, passportEndingIndex, clumpedPassports);
                addPassport(separatedPassports, passport);

                if (hasNextPassport(clumpedPassports, passportEndingIndex)) {
                    passportStartingIndex = nextPassportStartingIndex(passportEndingIndex);
                }
            }
        }
    }

    private static boolean passportHasBeenFound(char currentChar, char nextChar) {
        return currentChar == '\n' && nextChar == '\n';
    }

    private static String getPassport(int passportStartingIndex, int passportEndingIndex, String clumpedPassports) {
        return clumpedPassports.substring(passportStartingIndex, passportEndingIndex);
    }

    private static int nextPassportStartingIndex(int passportEndingIndex) {
        int passportStartingIndex;
        passportStartingIndex = passportEndingIndex + 2;
        return passportStartingIndex;
    }

    private static boolean hasNextPassport(String clumpedPassports, int passportEndingIndex) {
        return passportEndingIndex + 2 < clumpedPassports.length();
    }

    private static void addPassport(ArrayList<String> separatedPassports, String passport) {
        separatedPassports.add(passport);
    }
}
