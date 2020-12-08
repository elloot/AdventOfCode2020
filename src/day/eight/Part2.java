package day.eight;

public class Part2 extends Part1 {
    public static void main(String[] args) {
        Part2 p2 = new Part2();

    }

    private boolean isCorrectConfig() {
        try {
            executeOpAt(0);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
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
        switch (op) {
            case "acc":
                acc += arg;
                if (index + 1 >= instructions.length) {
                    System.out.println(acc);
                    return;
                }
                executeOpAt(index + 1);
                break;
            case "jmp":
                if (index + arg >= instructions.length) {
                    System.out.println(acc);
                    return;
                }
                executeOpAt(index + arg);
                break;
            case "nop":
                if (index + 1 >= instructions.length) {
                    System.out.println(acc);
                    return;
                }
                executeOpAt(index + 1);
                break;
        }
    }
}
