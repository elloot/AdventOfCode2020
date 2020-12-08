package day.seven;

import AOCUtil.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Main {
    public int numBags = 0;
    public Set<Bag> bagsContainingShinyGold = new HashSet<>();

    public Main() {

    }

    public static void main(String[] args) {
        String ogRegex = "([0-9][^,.]*)";
        String inputString = FileReader.readFile("seven/input.txt");
        String[] inputLines = inputString.split("\n");

        Timer timer = new Timer();
        timer.start();

        Main main = new Main();
        main.howManyBagsContain(new Bag("shiny gold", false), inputLines);
        System.out.println(main.bagsContainingShinyGold.size());
        System.out.println(timer.nextMillis());
    }

    public void howManyBagsContain(Bag bag, String[] inputLines) {
        ArrayList<String> directlyContaining = new ArrayList<>();
        for (String line : inputLines) {
            // remove the "x bags contain" part from the first bag
            String[] currentContainedBags = line.replaceFirst("(.*contain )", "").split("([,|.]+[^a-z0-9 ]*)");
            boolean containsBagDirectly = false;
            for (String currentContainedBag : currentContainedBags) {
                if (currentContainedBag.contains(bag.name)) {
                    containsBagDirectly = true;
                    break;
                }
            }
            if (containsBagDirectly) {
                directlyContaining.add(line.substring(0, line.indexOf("bags") - 1));
                bagsContainingShinyGold.add(new Bag(line.substring(0, line.indexOf("bags") - 1), true));
            }
        }
        if (directlyContaining.size() != 0) {
            for (String bagContaining : directlyContaining) {
                howManyBagsContain(new Bag(bagContaining, true), inputLines);
            }
        }
    }
}
