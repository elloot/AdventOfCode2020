package day.three;
import AOCUtil.*;

import javax.lang.model.type.ArrayType;
import java.lang.reflect.Array;
import java.math.BigInteger;
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

        int stepX = 1;
        int stepY = 1;
        int[] numTrees = new int[5];

        for (int i = 0; i < 5; i++) {
            numTrees[i] = getEncounteredTrees(treeMap, stepX, stepY, width, inputStringArray.length);

            if (i != 3) {
                stepX += 2;
            } else {
                stepX = 1;
                stepY += 1;
            }
        }

        long prodTrees = (long) numTrees[0] * numTrees[1] * numTrees[2] * numTrees[3] * numTrees[4];
        System.out.println(prodTrees);
    }
    
    public static int getEncounteredTrees(Integer[] treeMap, int stepX, int stepY, int width, int height) {
        int x = 0, y = 0, numTrees = 0;
        while (y + stepY < height) {
            x += stepX;
            y += stepY;
            if (x >= width) x = x - width;

            if (treeMap[xyToIndex(x, y, width)] == 1) {
                numTrees++;
            }
        }
        
        return numTrees;
    }

    public static int xyToIndex(int x, int y, int width) {
        return y * width + x;
    }
}
