package day.one;

import AOCUtil.*;

public class Main {
    public static void main(String[] args) {
        int[] input = getInputData();

        System.out.println(solve(input));
    }

    public static int solve(int[] input) {
        for (int j : input) {
            for (int k : input) {
                for (int l : input) {
                    if (j + k + l == 2020) {
                        return j * k * l;
                    }
                }
            }
        }
        return -1;
    }

    public static int[] getInputData() {
        String inputData;

        inputData = FileReader.readFile("one/input.txt");

        String[] inputStringArray = inputData.split("\n");
        int[] inputArray = new int[inputStringArray.length];
        for (int i = 0; i < inputStringArray.length; i++) inputArray[i] = Integer.parseInt(inputStringArray[i]);

        return inputArray;
    }
}
