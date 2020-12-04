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

        int numValidPassports = 0;

        for (HashMap passport : passports) {
            if (passesInitialValidation(passport) && isValid(passport)) {
                numValidPassports++;
            }
        }

        System.out.println(numValidPassports);
    }

    private static boolean isValid(HashMap passport) {
        String byr = passport.get("byr").toString(), iyr = passport.get("iyr").toString(), eyr = passport.get("eyr").toString(), hgt = passport.get("hgt").toString(), hcl = passport.get("hcl").toString(), ecl = passport.get("ecl").toString(), pid = passport.get("pid").toString();

        return hasValidByr(byr) && hasValidIyr(iyr) && hasValidEyr(eyr) && hasValidHgt(hgt) && hasValidHcl(hcl) && hasValidEcl(ecl) && hasValidPid(pid);
    }

    private static boolean hasValidPid(String pid) {
        for (int i = 0; i < pid.length(); i++) {
            if (!Character.isDigit(pid.charAt(i))) {
                return false;
            }
        }

        return pid.length() == 9;
    }

    private static boolean hasValidEcl(String ecl) {
        String[] allowedValues = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};

        for (String allowedValue : allowedValues) {
            if (ecl.equals(allowedValue)) {
                return true;
            }
        }

        return false;
    }

    private static boolean hasValidHcl(String hcl) {
        char firstChar = hcl.charAt(0);

        if (firstChar != '#') {
            return false;
        }

        try {
            Integer.parseInt(hcl.substring(1), 16);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean hasValidHgt(String hgt) {
        String unit = hgt.substring(hgt.length() - 2);
        int height;

        try {
            height = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
        } catch (NumberFormatException e) {
            return false;
        }

        if (unit.equals("cm")) {
            return height >= 150 && height <= 193;
        } else if (unit.equals("in")) {
            return height >= 59 && height <= 76;
        } else {
            return false;
        }
    }

    private static boolean hasValidEyr(String eyr) {
        int expirationYear;

        try {
            expirationYear = Integer.parseInt(eyr);
        } catch (NumberFormatException e) {
            return false;
        }
        return expirationYear >= 2020 && expirationYear <= 2030;
    }

    private static boolean hasValidIyr(String iyr) {
        int issueYear;
        try {
            issueYear = Integer.parseInt(iyr);
        } catch (NumberFormatException e) {
            return false;
        }
        return issueYear >= 2010 && issueYear <= 2020;
    }

    private static boolean hasValidByr(String byr) {
        int birthYear;
        try {
            birthYear = Integer.parseInt(byr);
        } catch (NumberFormatException e) {
            return false;
        }
        return birthYear >= 1920 && birthYear <= 2002;
    }

    private static boolean passesInitialValidation(HashMap passport) {
        if (passport.containsKey("byr")) {
            if (passport.containsKey("iyr")) {
                if (passport.containsKey("eyr")) {
                    if (passport.containsKey("hgt")) {
                        if (passport.containsKey("hcl")) {
                            if (passport.containsKey("ecl")) {
                                return passport.containsKey("pid");
                            }
                        }
                    }
                }
            }
        }
        return false;
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
