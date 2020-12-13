package day.thirteen;

import AOCUtil.*;
import AOCUtil.Timer;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static void main(String[] args) {
        String input = FileReader.readFile("/thirteen/input.txt");
        Timer t = new Timer();
        t.start();
        int earliestTimestamp = Integer.parseInt(input.substring(0, input.indexOf('\n')));
        String busIDString = input.substring(input.indexOf('\n') + 1);

        // finds all the bus IDs
        Pattern pattern = Pattern.compile("([^x,])([0-9])+");
        Matcher matcher = pattern.matcher(busIDString);
        ArrayList<Integer> busIDs = new ArrayList<>();
        while (matcher.find()) {
            busIDs.add(Integer.parseInt(matcher.group()));
        }

        Map<Integer, Integer> departureTimes = new HashMap<>();
        for (int busID : busIDs) {
            int departure = busID;
            while (departure < earliestTimestamp) {
                departure += busID;
            }
            departureTimes.put(departure, busID);
        }

        Map<Integer, Integer> sortedDepartures = new TreeMap<>(departureTimes);
        int earliestDeparture = Collections.min(departureTimes.keySet());
        int waitTime = earliestDeparture - earliestTimestamp;

        System.out.println(waitTime * sortedDepartures.get(earliestDeparture) + ", took " + t.nextMillis() + " ms");
    }
}
