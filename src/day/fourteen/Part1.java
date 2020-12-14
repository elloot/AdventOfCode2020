package day.fourteen;

import AOCUtil.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static void main(String[] args) {
        String input = FileReader.readFile("fourteen/input.txt");
        Timer t = new Timer();
        t.start();
        Pattern p = Pattern.compile("(mask)");
        Matcher m = p.matcher(input);
        int numMasks = 0;
        while (m.find()) {
            numMasks++;
        }

        Map<Integer, Long> memLocations = new HashMap<>();
        int currentMaskIndex = 0;
        for (int i = 0; i < numMasks; i++) {
            int nextMaskIndex = input.indexOf("mask", currentMaskIndex + 1);
            String currentMask = getMask(currentMaskIndex, input);
            String memoryBlock = getMemoryBlock(nextMaskIndex, currentMaskIndex, input);

            ArrayList<Integer> memAddress = getMemAddress(memoryBlock);
            ArrayList<Long> memValues = getMemValues(memoryBlock);

            for (int j = 0; j < memAddress.size(); j++) {
                Integer currentMemAddress = memAddress.get(j);
                Long currentMemValue = memValues.get(j);
                Long finalMemValue = applyBitmask(currentMemValue, currentMask);
                memLocations.put(currentMemAddress, finalMemValue);
            }
            currentMaskIndex = nextMaskIndex;
        }

        long sumValues = 0;
        for (Integer key : memLocations.keySet()) {
            sumValues += memLocations.get(key);
        }
        System.out.println(sumValues + ", took " + t.nextMillis() + " ms");
    }

    private static Long applyBitmask(Long currentMemValue, String currentMask) {
        String binaryMemValue = padWithLeadingZeros(Long.toBinaryString(currentMemValue));
        StringBuilder finalMemValue = new StringBuilder(binaryMemValue);
        for (int i = 0; i < currentMask.length(); i++) {
            if (currentMask.charAt(i) != 'X') {
                finalMemValue.replace(i, i + 1, String.valueOf(currentMask.charAt(i)));
            }
        }
        return Long.parseLong(finalMemValue.toString(), 2);
    }

    private static String getMemoryBlock(int nextMaskIndex, int currentMaskIndex, String input) {
        return input.substring(input.indexOf("mem", currentMaskIndex), nextMaskIndex != -1 ? nextMaskIndex - 1 : input.length() - 1);
    }

    private static String getMask(int i, String s) {
        return s.substring(i, s.indexOf('\n', i)).substring(7);
    }

    private static ArrayList<Integer> getMemAddress(String memoryBlock) {
        Pattern memAddressPattern = Pattern.compile("(?!\\[)[0-9]+(?=])");
        Matcher memAddressMatcher = memAddressPattern.matcher(memoryBlock);
        ArrayList<Integer> memAddress = new ArrayList<>();
        while (memAddressMatcher.find()) {
            memAddress.add(Integer.parseInt(memAddressMatcher.group()));
        }
        return memAddress;
    }

    private static ArrayList<Long> getMemValues(String memoryBlock) {
        Pattern memValuePattern = Pattern.compile("[^ =]+(?=\\n|$)");
        Matcher memValueMatcher = memValuePattern.matcher(memoryBlock);
        ArrayList<Long> memValues = new ArrayList<>();
        while (memValueMatcher.find()) {
            memValues.add(Long.parseLong(memValueMatcher.group()));
        }
        return memValues;
    }

    private static String padWithLeadingZeros(String binaryMemValue) {
        StringBuilder padded = new StringBuilder(binaryMemValue);
        while (padded.length() < 36) {
            padded.insert(0, '0');
        }
        return padded.toString();
    }
}
