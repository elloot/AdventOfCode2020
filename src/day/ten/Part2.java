package day.ten;

import AOCUtil.FileReader;
import AOCUtil.Timer;

import java.util.Arrays;

public class Part2 {
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
    }
}
