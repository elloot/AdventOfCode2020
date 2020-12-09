package day.eight;

import java.util.ArrayList;
import java.util.HashSet;

public class Part2 extends Part1 {
    public boolean executedCorrectly;

    public static void main(String[] args) {
        Part2 p2 = new Part2();

        int[] jmpIndexes = p2.indexesOf("jmp");
        int[] nopIndexes = p2.indexesOf("nop");

        // replace an index of jmp with nop
        // and see if boot code executes correctly
        for (int jmpIndex : jmpIndexes) {
            p2.instructions[jmpIndex] = "nop" + p2.instructions[jmpIndex].substring(3);
            try {
                p2.executeOpAt(0);
            } catch (IndexOutOfBoundsException e) {
                p2.executedCorrectly = false;
            }
            if (p2.executedCorrectly) {
                System.out.println(p2.executedCorrectly);
                System.out.println(p2.acc);
                break;
            }
            // reset instructions, acc and visitedIndexes for next iteration
            p2.instructions[jmpIndex] = "jmp" + p2.instructions[jmpIndex].substring(3);
            p2.visitedIndexes = new HashSet<>();
            p2.acc = 0;
        }

        p2.executedCorrectly = false;

        for (int nopIndex : nopIndexes) {
            p2.instructions[nopIndex] = "jmp" + p2.instructions[nopIndex].substring(3);
            try {
                p2.executeOpAt(0);
            } catch (IndexOutOfBoundsException e) {
                p2.executedCorrectly = false;
            }
            if (p2.executedCorrectly) {
                System.out.println(p2.executedCorrectly);
                System.out.println(p2.acc);
                break;
            }
            // reset instructions, acc and visitedIndexes for next iteration
            p2.instructions[nopIndex] = "nop" + p2.instructions[nopIndex].substring(3);
            p2.visitedIndexes = new HashSet<>();
            p2.acc = 0;
        }
    }

    public int[] indexesOf(String op) {
        ArrayList<Integer> indexes = new ArrayList<>(100);
        for (int i = 0; i < instructions.length; i++) {
            if (instructions[i].contains(op)) {
                indexes.add(i);
            }
        }
        return convertIntegers(indexes);
    }

    private int[] convertIntegers(ArrayList<Integer> integers) {
        int[] intArray = new int[integers.size()];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = integers.get(i);
        }

        return intArray;
    }

    public void executeOpAt(int index) throws IndexOutOfBoundsException {
        if (!visitedIndexes.add(index)) {
            executedCorrectly = false;
            return;
        } else {
            visitedIndexes.add(index);
        }
        String op = instructions[index].substring(0, 3);
        int arg = Integer.parseInt(instructions[index].substring(4));
        switch (op) {
            case "acc":
                acc += arg;
                if (index + 1 >= instructions.length) {
                    executedCorrectly = true;
                    return;
                }
                executeOpAt(index + 1);
                break;
            case "jmp":
                if (index + arg >= instructions.length) {
                    executedCorrectly = true;
                    return;
                }
                executeOpAt(index + arg);
                break;
            case "nop":
                if (index + 1 >= instructions.length) {
                    executedCorrectly = true;
                    return;
                }
                executeOpAt(index + 1);
                break;
        }
    }
}
