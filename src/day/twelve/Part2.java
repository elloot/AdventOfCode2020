package day.twelve;

import AOCUtil.FileReader;
import AOCUtil.Timer;

public class Part2 {
    public static void main(String[] args) {
        String[] instructions = FileReader.readFile("twelve/input.txt").split("\n");
        Timer t = new Timer();
        t.start();
        double x = 0;
        double y = 0;
        double waypointX = 10;
        double waypointY = 1;
        double waypointAngle, hypotenuse;

        for (String instruction : instructions) {
            char action = instruction.charAt(0);
            int value = Integer.parseInt(instruction.substring(1));
            if (action == 'L' || action == 'R') {
                waypointAngle = Math.toDegrees(Math.atan(waypointY/waypointX));
                hypotenuse = waypointY / Math.sin(Math.atan(waypointY/waypointX));
                if (action == 'L') {
                    waypointAngle += value;
                } else {
                    waypointAngle -= value;
                }
                waypointX = Math.cos(Math.toRadians(waypointAngle)) * hypotenuse;
                waypointY = Math.sin(Math.toRadians(waypointAngle)) * hypotenuse;
            } else {
                switch (action) {
                    case 'N':
                        waypointY += value;
                        break;
                    case 'S':
                        waypointY -= value;
                        break;
                    case 'E':
                        waypointX += value;
                        break;
                    case 'W':
                        waypointX -= value;
                        break;
                    case 'F':
                        x += waypointX * value;
                        y += waypointY * value;
                        break;
                }
            }
        }
        double manhattanDistance = Math.abs(x) + Math.abs(y);
        manhattanDistance = Math.round(manhattanDistance);
        System.out.println(manhattanDistance + ", took " + t.nextMillis() + " ms");
    }
}
