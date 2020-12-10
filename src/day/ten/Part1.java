package day.ten;

import AOCUtil.*;
import AOCUtil.Timer;

import java.util.*;

public class Part1 {
    public static void main(String[] args) {
        Timer t = new Timer();
        String[] input = FileReader.readFile("ten/input.txt").split("\n");
        t.start();
        int[] adapters = new int[input.length + 1];
        adapters[0] = 0;
        for (int i = 1; i <= input.length; i++) {
            adapters[i] = Integer.parseInt(input[i - 1]);
        }
        Arrays.sort(adapters);

        Map<Integer, Integer> differences = new HashMap<>();
        differences.put(1, 0);
        differences.put(2, 0);
        differences.put(3, 0);

        for (int i = 0; i < adapters.length; i++) {
            int currentAdapter = adapters[i];
            if (i + 1 == adapters.length) {
                // add the difference between the device and the adapters
                differences.replace(3, differences.get(3) + 1);
                break;
            }
            int nextAdapter = adapters[i + 1];
            int difference = nextAdapter - currentAdapter;
            if (difference > 0 && difference <= 3) {
                differences.get(difference);
                differences.replace(difference, differences.get(difference) + 1);
            } else {
                System.out.println("Something went wrong");
                break;
            }
        }

        System.out.println(differences.get(1) * differences.get(3) + ", took " + t.nextMillis() + " ms");
    }
}
