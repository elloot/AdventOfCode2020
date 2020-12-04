package day.four;

import AOCUtil.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String clumpedPassports = FileReader.readFile("four/input.txt");
        ArrayList<String> separatedPassports = new ArrayList<>();

        populateSeparatedPassportArray(clumpedPassports, separatedPassports);

        HashMap[] passports = new HashMap[separatedPassports.size()];
        for (int i = 0; i < separatedPassports.size(); i++) {
            passports[i] = new HashMap<String, String>();
        }

        mapPassports(separatedPassports, passports);

        System.out.println(numValidPassports(passports));
    }

    private static int numValidPassports(HashMap[] passports) {
        int numValidPassports = 0;
        for (HashMap currentPassport : passports) {
            if (currentPassport.containsKey("byr")) {
                if (currentPassport.containsKey("iyr")) {
                    if (currentPassport.containsKey("eyr")) {
                        if (currentPassport.containsKey("hgt")) {
                            if (currentPassport.containsKey("hcl")) {
                                if (currentPassport.containsKey("ecl")) {
                                    if (currentPassport.containsKey("pid")) {
                                        numValidPassports++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return numValidPassports;
    }

    private static void mapPassports(ArrayList<String> separatedPassports, HashMap[] passports) {
        int currentColonIndex;

        String currentField;
        String currentPassport;

        int nextLineBreakIndex;
        int previousLineBreakIndex;
        int nextSpaceIndex;
        int previousSpaceIndex;

        String currentFieldValue;

        boolean stopSearchLineBreak;
        boolean stopSearchSpace;

        for (int i = 0; i < separatedPassports.size(); i++) {
            currentPassport = separatedPassports.get(i);
            currentColonIndex = currentPassport.indexOf(':');
            nextLineBreakIndex = 0;
            previousLineBreakIndex = 0;
            nextSpaceIndex = 0;
            previousSpaceIndex = 0;
            stopSearchLineBreak = false;
            stopSearchSpace = false;

            while (true) {
                currentField = currentPassport.substring(currentColonIndex - 3, currentColonIndex);

                if (!stopSearchLineBreak) {
                    nextLineBreakIndex = currentPassport.indexOf('\n', previousLineBreakIndex + 1);
                }
                if (!stopSearchSpace) {
                    nextSpaceIndex = currentPassport.indexOf(' ', previousSpaceIndex + 1);
                }

                if (nextLineBreakIndex == -1) {
                    nextLineBreakIndex = currentPassport.length();
                    stopSearchLineBreak = true;
                }

                if (nextSpaceIndex == -1) {
                    nextSpaceIndex = currentPassport.length();
                    stopSearchSpace = true;
                }

                int closestBreakChIndex = Math.min(nextSpaceIndex, nextLineBreakIndex);

                currentFieldValue = currentPassport.substring(currentColonIndex + 1, closestBreakChIndex);

                passports[i].put(currentField, currentFieldValue);

                currentColonIndex = currentPassport.indexOf(':', currentColonIndex + 1);

                if (currentColonIndex == -1) {
                    break;
                }

                if (!(nextLineBreakIndex > nextSpaceIndex)) {
                    previousLineBreakIndex = nextLineBreakIndex;
                }
                if (!(nextSpaceIndex > nextLineBreakIndex)) {
                    previousSpaceIndex = nextSpaceIndex;
                }
            }
        }
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
