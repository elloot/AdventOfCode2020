package day.fourteen;

import AOCUtil.FileReader;
import AOCUtil.Timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
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

        Map<Long, Long> memLocations = new HashMap<>();
        int currentMaskIndex = 0;
        for (int i = 0; i < numMasks; i++) {
            int nextMaskIndex = input.indexOf("mask", currentMaskIndex + 1);
            String currentMask = getMask(currentMaskIndex, input);
            String memoryBlock = getMemoryBlock(nextMaskIndex, currentMaskIndex, input);

            ArrayList<Long> memAddress = getMemAddress(memoryBlock);
            ArrayList<Long> memValues = getMemValues(memoryBlock);

            for (int j = 0; j < memAddress.size(); j++) {
                String maskedAddress = applyBitmask(memAddress.get(j), currentMask);
                ArrayList<Long> possibleNumbers = new ArrayList<>();
                getPossibleNumbers(maskedAddress, possibleNumbers);
                for (Long possibleAddress : possibleNumbers) {
                    memLocations.put(possibleAddress, memValues.get(j));
                }
            }
            currentMaskIndex = nextMaskIndex;
        }
        long sumValues = 0;
        for (Long key : memLocations.keySet()) {
            sumValues += memLocations.get(key);
        }
        System.out.println(sumValues + ", took " + t.nextMillis() + " ms");
    }

    private static void getPossibleNumbers(String floatyNumber, ArrayList<Long> possibleNumbers) {
        if (!floatyNumber.contains("X")) {
            possibleNumbers.add(Long.parseLong(floatyNumber, 2));
        } else {
            StringBuilder fN = new StringBuilder(floatyNumber);
            int xIndex = fN.indexOf("X");

            fN.replace(xIndex, xIndex + 1, "0");
            getPossibleNumbers(fN.toString(), possibleNumbers);

            fN.replace(xIndex, xIndex + 1, "1");
            getPossibleNumbers(fN.toString(), possibleNumbers);
        }
    }

    private static String applyBitmask(Long memAddress, String mask) {
        String binaryAddress = padWithLeadingZeros(Long.toBinaryString(memAddress));
        StringBuilder maskedAddress = new StringBuilder(binaryAddress);
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) != '0') {
                maskedAddress.replace(i, i + 1, String.valueOf(mask.charAt(i)));
            }
        }
        return maskedAddress.toString();
    }

    private static String getMemoryBlock(int nextMaskIndex, int currentMaskIndex, String input) {
        return input.substring(input.indexOf("mem", currentMaskIndex), nextMaskIndex != -1 ? nextMaskIndex - 1 : input.length() - 1);
    }

    private static String getMask(int i, String s) {
        return s.substring(i, s.indexOf('\n', i)).substring(7);
    }

    private static ArrayList<Long> getMemAddress(String memoryBlock) {
        Pattern memAddressPattern = Pattern.compile("(?!\\[)[0-9]+(?=])");
        Matcher memAddressMatcher = memAddressPattern.matcher(memoryBlock);
        ArrayList<Long> memAddress = new ArrayList<>();
        while (memAddressMatcher.find()) {
            memAddress.add(Long.parseLong(memAddressMatcher.group()));
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
