package day.two;
import AOCUtil.*;

public class Main {
    public static void main(String[] args) {
        String inputString = FileReader.readFile("two/input.txt");
        String[] input = inputString.split("\n");

        int validPasswords = 0;

        for (int i = 0; i < input.length; i++) {
            String current = input[i];

            int minAppearances = Integer.parseInt(current.substring(0, current.indexOf("-")));
            int maxAppearances = Integer.parseInt(current.substring(current.indexOf("-") + 1, current.indexOf(" ")));
            char currentLetter = current.charAt(current.indexOf(" ") + 1);
            String currentPassword = current.substring(current.indexOf(":") + 2);

            int occurences = 0;

            for (int j = 0; j < currentPassword.length(); j++) {
                if (currentPassword.charAt(j) == currentLetter) {
                    occurences++;
                }
            }

            if (occurences >= minAppearances && occurences <= maxAppearances) {
                validPasswords++;
            }
        }

        System.out.println(validPasswords);
    }
}