package day.eight;

import AOCUtil.*;

import java.util.HashSet;
import java.util.Set;

public class Part1 {
    public Set<Integer> visitedIndexes = new HashSet<>();
    public String[] instructions = FileReader.readFile("eight/input.txt").split("\n");
    public int acc = 0;

    public static void main(String[] args) {
        Part1 p1 = new Part1();
        p1.executeOpAt(0);
    }

    private void executeOpAt(int index) {
        if (!visitedIndexes.add(index)) {
            System.out.println(acc);
            return;
        } else {
            visitedIndexes.add(index);
        }
        String op = instructions[index].substring(0, 3);
        int arg = Integer.parseInt(instructions[index].substring(4));
        if (op.equals("acc")) {
            acc += arg;
            executeOpAt(index + 1);
        } else if (op.equals("jmp")) {
            executeOpAt(index + arg);
        } else if (op.equals("nop")) {
            executeOpAt(index + 1);
        }
    }
}
