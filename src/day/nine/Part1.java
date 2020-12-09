package day.nine;

import AOCUtil.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Part1 {
    public static void main(String[] args) {
        String[] input = FileReader.readFile("nine/input.txt").split("\n");
        long[] xmasNumbers = parseLongs(input);

        Timer t = new Timer();
        t.start();

        boolean incorrectNumberFound = false;

        int preambleLength = 25;
        long incorrectNumber = 0;
        int startingIndex = 0;
        int endingIndex = preambleLength;

        while (!incorrectNumberFound && endingIndex < xmasNumbers.length) {
            long[] preceeding25 = Arrays.copyOfRange(xmasNumbers, startingIndex, endingIndex);
            long currentNumber = xmasNumbers[endingIndex];
            boolean isCorrect = false;
            for (long preceedingNumber : preceeding25) {
                Map<Long, Long> difference = new HashMap<>();
                difference.put(preceedingNumber, currentNumber - preceedingNumber);
                for (long term : preceeding25) {
                    if (difference.get(preceedingNumber) == term) {
                        isCorrect = true;
                        break;
                    }
                }
                if (isCorrect) {
                    break;
                }
            }
            if (!isCorrect) {
                incorrectNumberFound = true;
                incorrectNumber = currentNumber;
            }
            startingIndex++;
            endingIndex++;
        }
        System.out.println(incorrectNumber + " " + t.nextMillis() + " ms");
    }

    public static long[] parseLongs(String[] numbersAsStrings) {
        long[] longs = new long[numbersAsStrings.length];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = Long.parseLong(numbersAsStrings[i]);
        }
        return longs;
    }
}
