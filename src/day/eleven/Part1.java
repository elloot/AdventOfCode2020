package day.eleven;

import AOCUtil.FileReader;
import AOCUtil.Timer;

import java.util.Arrays;

public class Part1 {
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
                        if (adjacentOccupied >= 4) {
                            futureSeats[i][j] = 'L';
                        }
                    }
                }
            }
        } while (!Arrays.deepEquals(seats, futureSeats));

        System.out.println(countOccupied(seats) + ", took " + t.nextMillis() + " ms");
    }

    private static int countOccupied (char[][] seats) {
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
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((y + i >= 0 && y + i < seats.length) && (x + j >= 0 && x + j < seats[y + i].length)) {
                    if (seats[y + i][x + j] == '#' && !(i == 0 && j == 0)) {
                        numAdjacentOccupied++;
                    }
                }
            }
        }
        return numAdjacentOccupied;
    }
}
