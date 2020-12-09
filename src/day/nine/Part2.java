package day.nine;

import AOCUtil.FileReader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Part2 {
    public static void main(String[] args) {
        String[] input = FileReader.readFile("nine/input.txt").split("\n");
        long[] xmasNumbers = parseLongs(input);
        long incorrectNumber = 41682220;

        long sum = 0;
        Set<Long> addends = new HashSet<>();

        int beginIndex = 0;

        for (int i = 0; i < xmasNumbers.length; i++) {
            addends.add(xmasNumbers[i]);
            Object[] addendsArray =  addends.toArray();
            for (Object addend : addendsArray) {
                sum += (long) addend;
            }
            if (sum == incorrectNumber) {
                break;
            } else if (sum > incorrectNumber) {
                beginIndex++;
                i = beginIndex;
                addends = new HashSet<>();
            }
            sum = 0;
        }
        Object[] addendsArray = addends.toArray();
        long[] actualAddendsArray = new long[addendsArray.length];
        for (int i = 0; i < addendsArray.length; i++) {
            actualAddendsArray[i] = (long) addendsArray[i];
        }
        Arrays.sort(actualAddendsArray);
        long encyptionWeakness = actualAddendsArray[0] + actualAddendsArray[actualAddendsArray.length - 1];
        System.out.println(encyptionWeakness);
    }

    public static long[] parseLongs(String[] numbersAsStrings) {
        long[] longs = new long[numbersAsStrings.length];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = Long.parseLong(numbersAsStrings[i]);
        }
        return longs;
    }
}
