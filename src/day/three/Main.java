package day.three;
import AOCUtil.*;

import javax.lang.model.type.ArrayType;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String inputString = FileReader.readFile("three/input.txt");
        String[] inputStringArray = inputString.split("\n");
        int width = inputStringArray[0].length();
        Integer[] treeMap = new Integer[inputStringArray.length * width];

        // generate array with 1 for tree and 0 for no tree
        for (int i = 0; i < inputStringArray.length; i++) {
            String currentLine = inputStringArray[i];
            for (int j = 0; j < width; j++) {
                char currentChar = currentLine.charAt(j);
                treeMap[xyToIndex(j, i, width)] = currentChar == '.' ? 0 : 1;
            }
        }

        int stepX = 3;
        int stepY = 1;
        int x = 0, y = 0, numTrees = 0;

        while (y + stepY < inputStringArray.length) {
            x += stepX;
            y += stepY;
            if (x >= width) x = x - width;

            StringBuilder sb = new StringBuilder(inputStringArray[y]);
            if (treeMap[xyToIndex(x, y, width)] == 1) {
                numTrees++;
                sb.setCharAt(x, 'X');
                inputStringArray[y] = sb.toString();
            } else {
                sb.setCharAt(x, 'O');
                inputStringArray[y] = sb.toString();
            }
        }

        printInputStringArray(inputStringArray);
        System.out.println(numTrees);
    }

    public static void printInputStringArray(String[] a) {
        for (String s : a) {
            System.out.println(s);
        }
    }

    public static int xyToIndex(int x, int y, int width) {
        return y * width + x;
    }
}
