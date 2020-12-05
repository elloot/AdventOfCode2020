package day.five;

import AOCUtil.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String inputString = FileReader.readFile("five/input.txt");
        String[] inputArray = inputString.split("\n");

        int[] rows = new int[128];
        for (int i = 0; i < 128; i++) {
            rows[i] = i;
        }

        int[] column = new int[8];
        for (int i = 0; i < 8; i++) {
            column[i] = i;
        }

        Node<int[], String> rootRow = buildTree(rows);
        Node<int[], String> rootColumn = buildTree(column);

        int[] seatIDS = new int[inputArray.length];

        for (int i = 0; i < inputArray.length; i++) {
            String currentPassRow = inputArray[i].substring(0, 7);
            String currentPassColumn = inputArray[i].substring(7);
            Node<int[], String> currentRowNode = rootRow;
            Node<int[], String> currentColumnNode = rootColumn;
            for (int j = 0; j < currentPassRow.length(); j++) {
                char currentSection = currentPassRow.charAt(j);
                switch (currentSection) {
                    case 'F':
                        currentRowNode = currentRowNode.getChildren().get(0);
                        break;
                    case 'B':
                        currentRowNode = currentRowNode.getChildren().get(1);
                        break;
                }
            }

            for (int j = 0; j < currentPassColumn.length(); j++) {
                char currentSection = currentPassColumn.charAt(j);
                switch (currentSection) {
                    case 'L':
                        currentColumnNode = currentColumnNode.getChildren().get(0);
                        break;
                    case 'R':
                        currentColumnNode = currentColumnNode.getChildren().get(1);
                        break;
                }
            }

            int rowNumber = currentRowNode.getData()[0];
            int columnNumber = currentColumnNode.getData()[0];
            seatIDS[i] = rowNumber * 8 + columnNumber;
        }

        Arrays.sort(seatIDS);

        for (int i = 0; i < seatIDS.length; i++) {
            int seatID = seatIDS[i];
            if ((i + 1) < seatIDS.length && seatIDS[i + 1] - seatID > 1) {
                System.out.println(seatID + 1);
            }
        }
    }

    private static Node<int[], String> buildTree(int[] spaces) {
        Node<int[], String> root = new Node<>(spaces, "root");

        Node<int[], String> fChild = new Node<>(Arrays.copyOfRange(root.getData(), 0, (int) (Math.floor((root.getData().length - 1) / 2.0)) + 1), "F");
        Node<int[], String> bChild = new Node<>(Arrays.copyOfRange(root.getData(), (int) Math.ceil((root.getData().length - 1) / 2.0), root.getData().length), "B");

        buildF(root.addChild(fChild));
        buildB(fChild);

        buildF(bChild);
        buildB(root.addChild(bChild));

        return root;
    }

    private static void buildF(Node<int[], String> node) {
        if (node.getData().length != 1) {
            // F
            Node<int[], String> fNode = new Node<>(Arrays.copyOfRange(node.getData(), 0, (int) (Math.floor((node.getData().length - 1) / 2.0) + 1)), "F");
            node.addChild(fNode);

            buildF(fNode);
            buildB(fNode);
        }
    }

    private static void buildB(Node<int[], String> node) {
        if (node.getData().length != 1) {
            // B
            Node<int[], String> bNode = new Node<>(Arrays.copyOfRange(node.getData(), (int) Math.ceil((node.getData().length - 1) / 2.0), node.getData().length), "B");
            node.addChild(bNode);

            buildF(bNode);
            buildB(bNode);
        }
    }
}
