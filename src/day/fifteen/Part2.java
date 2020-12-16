package day.fifteen;

import AOCUtil.Timer;

import java.util.HashMap;
import java.util.Map;

public class Part2 {
    public static void main(String[] args) {
        Timer t = new Timer();
        t.start();

        int[] startingNumbers = {1, 0, 16, 5, 17, 4};
        Map<Integer, Integer> numbers = new HashMap<>();
        boolean spokenBefore = false;
        int lastSpoken = -1;
        for (int i = 1; i <= 30000001; i++) {
            if (i <= startingNumbers.length) {
                lastSpoken = startingNumbers[i - 1];
                numbers.put(lastSpoken, i);
                spokenBefore = false;
                continue;
            }
            if (!spokenBefore) {
                if (numbers.containsKey(0)) {
                    spokenBefore = true;
                } else {
                    numbers.put(0, i);
                }
                lastSpoken = 0;
            } else {
                int difference = i - 1 - numbers.get(lastSpoken);
                if (numbers.containsKey(difference)) {
                    spokenBefore = true;
                } else {
                    numbers.put(difference, i);
                    spokenBefore = false;
                }
                numbers.replace(lastSpoken, i - 1);
                lastSpoken = difference;
            }
        }

        for (int key : numbers.keySet()) {
            if (numbers.get(key) == 30000000) {
                System.out.println(key + ", took " + t.nextMillis() + " ms");
                break;
            }
        }
    }
}
