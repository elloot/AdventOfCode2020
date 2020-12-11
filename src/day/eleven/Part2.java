package day.eleven;

import AOCUtil.FileReader;
import AOCUtil.Timer;

import java.util.Arrays;

public class Part2 {
    public static void main(String[] args) {
        String[] input = FileReader.readFile("eleven/input.txt").split("\n");
        char[][] seats = new char[input.length][input[0].length()];
        char[][] futureSeats = new char[seats.length][seats[0].length];
        Timer t = new Timer();
        t.start();

        for (int i = 0; i < input.length; i++) {
            seats[i] = input[i].toCharArray();
            futureSeats[i] = input[i].toCharArray();
        }

        do {
            seats = copyOf(futureSeats);
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    int adjacentOccupied = numAdjacentOccupied(i, j, seats);
                    if (seats[i][j] == 'L') {
                        if (adjacentOccupied == 0) {
                            futureSeats[i][j] = '#';
                        }
                    } else if (seats[i][j] == '#') {
                        if (adjacentOccupied >= 5) {
                            futureSeats[i][j] = 'L';
                        }
                    }
                }
            }
        } while (!Arrays.deepEquals(seats, futureSeats));

        System.out.println(countOccupied(seats) + ", took " + t.nextMillis() + " ms");
    }

    private static int countOccupied(char[][] seats) {
        int numOccupied = 0;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == '#') {
                    numOccupied++;
                }
            }
        }
        return numOccupied;
    }

    private static char[][] copyOf(char[][] a) {
        char[][] copy = new char[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                copy[i][j] = a[i][j];
            }
        }
        return copy;
    }

    private static int numAdjacentOccupied(int y, int x, char[][] seats) {
        int numAdjacentOccupied = 0;

        for (int i = 0; i < 8; i++) {
            int xStep = 0;
            int yStep = 0;
            do {
                switch (i) {
                    // check up
                    case 0:
                        yStep--;
                        break;
                    // check diagonally up right
                    case 1:
                        yStep--;
                        xStep++;
                        break;
                    // check right
                    case 2:
                        xStep++;
                        break;
                    // check diagonally down right
                    case 3:
                        yStep++;
                        xStep++;
                        break;
                    // check down
                    case 4:
                        yStep++;
                        break;
                    // check diagonally down left
                    case 5:
                        yStep++;
                        xStep--;
                        break;
                    // check left
                    case 6:
                        xStep--;
                        break;
                    // check diagonally up left
                    case 7:
                        yStep--;
                        xStep--;
                        break;
                }
                if (!((y + yStep >= 0 && y + yStep < seats.length) && (x + xStep >= 0 && x + xStep < seats[y + yStep].length))) {
                    break;
                }
                if (seats[y + yStep][x + xStep] == '#') {
                    numAdjacentOccupied++;
                }
            } while (seats[y + yStep][x + xStep] == '.');
        }
        return numAdjacentOccupied;
    }
}
