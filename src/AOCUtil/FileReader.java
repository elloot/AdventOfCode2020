package AOCUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public static String readFile(String filePath) {
        String fileName = "./src/day/" + filePath;
        Scanner s = null;

        try {
            s = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder input = new StringBuilder();

        while (s.hasNextLine()) {
            input.append(s.nextLine()).append("\n");
        }

        return input.toString();
    }
}

