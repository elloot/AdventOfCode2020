package day.four;
import AOCUtil.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String inputString = FileReader.readFile("four/input.txt");
        ArrayList<String> inputArray = new ArrayList<>();

        //char previousChar = inputString.charAt(0);
        char currentChar;
        char nextChar = inputString.charAt(1);
        int paragraphStartingIndex = 0;
        int paragraphEndingIndex = 0;

        for (int i = 0; i < inputString.length(); i++) {
            currentChar = inputString.charAt(i);
            if (i + 1 < (inputString.length() - 1)) nextChar = inputString.charAt(i + 1);
            paragraphEndingIndex = i;
            if (currentChar == '\n' && nextChar == '\n') {
                inputArray.add(inputString.substring(paragraphStartingIndex, paragraphEndingIndex));
                if (i + 2 < (inputString.length() - 1)) paragraphStartingIndex = i + 2;
            }
        }

        for (int i = 0; i < inputArray.size(); i++) {
            System.out.println(inputArray.get(i));
            System.out.println();
        }
    }
}
