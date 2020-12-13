package day.thirteen;

import AOCUtil.Timer;

import java.util.ArrayList;

public class Part2 {
    public static void main(String[] args) {
        String input = "17,x,x,x,x,x,x,x,x,x,x,37,x,x,x,x,x,571,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,13,x,x,x,x,23,x,x,x,x,x,29,x,401,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,19";
        String[] schedule = input.split(",");

        Timer t = new Timer();
        t.start();

        int[] busIDs = toIntArray(input.split("[x,]+"));
        ArrayList<ArrayList<Long>> fitReqTogether = new ArrayList<>();

        long timestamp = 0;
        long increment = busIDs[0];
        boolean timestampFound = false;
        while (!timestampFound) {
            timestamp += increment;
            // a list to keep track of how many busIDs departed
            // at the correct time based on this timestamp
            ArrayList<Long> fitReq = new ArrayList<>();
            for (int i = 1; i < busIDs.length; i++) {
                int afterT = indexOf(busIDs[i] + "", schedule);
                if ((timestamp + afterT) % busIDs[i] == 0) {
                    fitReq.add((long) busIDs[i]);
                    timestampFound = true;
                } else {
                    timestampFound = false;
                    break;
                }
            }
            // if more than one bus departed at the correct time
            // based on this timestamp, add them and the current timestamp
            // to a list of buses that departed at the correct time
            if (fitReq.size() > 1) {
                fitReqTogether.add(fitReq);
                ArrayList<Long> timestampList = new ArrayList<>();
                // this is super dumb but it works
                // I couldn't think of a better solution at the time
                // so the timestamp where these buses departed correctly
                // is stored in the same list as the buses
                // could probably be done better with a Map or a class
                timestampList.add(timestamp);
                fitReqTogether.add(timestampList);
                // if this pair of buses that departed correctly have departed
                // correctly earlier, start incrementing by the difference between
                // the timestamp they first departed correctly together and the
                // next timestamp they departed together
                if (seenBefore(fitReq, fitReqTogether)) {
                    ArrayList<Integer> seenAt = indexesOf(fitReq, fitReqTogether);
                    increment = fitReqTogether.get(seenAt.get(1) + 1).get(0) - fitReqTogether.get(seenAt.get(0) + 1).get(0);
                }
            }
        }

        System.out.println(timestamp + ", took " + t.nextMillis() + " ms");
    }

    private static ArrayList<Integer> indexesOf(ArrayList<Long> group, ArrayList<ArrayList<Long>> seenTogether) {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < seenTogether.size(); i++) {
            if (group.equals(seenTogether.get(i))) {
                indexes.add(i);
                if (indexes.size() >= 2) {
                    break;
                }
            }
        }
        return indexes;
    }

    private static boolean seenBefore(ArrayList<Long> seen, ArrayList<ArrayList<Long>> seenTogether) {
        int timesSeen = 0;
        for (ArrayList<Long> group : seenTogether) {
            if (seen.equals(group)) {
                timesSeen++;
            }
        }
        return timesSeen >= 2;
    }

    private static int indexOf(String s, String[] a) {
        int index = -1;
        for (int i = 0; i < a.length; i++) {
            if (s.equals(a[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static int[] toIntArray (String[] a) {
        int[] out = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = Integer.parseInt(a[i]);
        }
        return out;
    }
}
