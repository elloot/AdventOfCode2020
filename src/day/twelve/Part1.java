package day.twelve;

import AOCUtil.*;

import java.util.ArrayList;

public class Part1 {
    public static void main(String[] args) {
        String[] instructions = FileReader.readFile("twelve/input.txt").split("\n");
        Timer t = new Timer();
        t.start();
        CircularList<Character> directions = new CircularList<>(new Character[]{'N', 'E', 'S', 'W'});
        int facing = 1;
        int x = 0;
        int y = 0;

        for (String instruction : instructions) {
            char action = instruction.charAt(0);
            int value = Integer.parseInt(instruction.substring(1));
            switch (action) {
                case 'N':
                    y += value;
                    break;
                case 'S':
                    y -= value;
                    break;
                case 'E':
                    x += value;
                    break;
                case 'W':
                    x -= value;
                    break;
                case 'L':
                    value /= 90;
                    facing -= value;
                    break;
                case 'R':
                    value /= 90;
                    facing += value;
                    break;
                case 'F':
                    char facingDirection = directions.get(facing);
                    switch (facingDirection) {
                        case 'N':
                            y += value;
                            break;
                        case 'S':
                            y -= value;
                            break;
                        case 'E':
                            x += value;
                            break;
                        case 'W':
                            x -= value;
                            break;
                    }
                    break;
            }
        }

        int manhattanDistance = Math.abs(x) + Math.abs(y);
        System.out.println(manhattanDistance + ", took " + t.nextMillis() + " ms");
    }

    public static class CircularList<E> extends ArrayList<E> {
        public CircularList (E[] es) {
            for (E e : es) {
                super.add(e);
            }
        }
        @Override
        public E get(int index) {
            while (index < 0) {
                index += super.size();
            }
            return super.get(index % size());
        }
    }
}
