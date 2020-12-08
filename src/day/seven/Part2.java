package day.seven;

import AOCUtil.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public String bagRules = FileReader.readFile("seven/input.txt");
    public static void main(String[] args) {
        Part2 p2 = new Part2();
        System.out.println(p2.howManyBagsContained("shiny gold"));
    }

    private int howManyBagsContained(String thisBag) {
        ArrayList<Integer> bagsContainedd = new ArrayList<>();
        // find the rules for the current bag
        Pattern pattern = Pattern.compile("(^" + thisBag + "[^.]*\\.)", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(bagRules);
        matcher.find();
        String thisBagsRules = matcher.group(1);

        // splits the contained bags in this bags
        // includes how many of the bag there is
        String[] bagsContainedInThisBag = thisBagsRules.replaceFirst("(.*contain )", "").split("(, |\\.+[^a-z0-9 ]*)");

        for (String bag : bagsContainedInThisBag) {
            String bagName = bag.substring(2);
            if (Character.isDigit(bag.charAt(0))) {
                int howManyOfThisBag = Integer.parseInt(bag.substring(0, 1));
                bagsContainedd.add(howManyOfThisBag + howManyOfThisBag * howManyBagsContained(bagName));
            } else {
                bagsContainedd.add(0);
            }
        }

        int totalBagsContained = 0;

        for (Integer containedBags : bagsContainedd) {
            totalBagsContained += containedBags;
        }

        return totalBagsContained;
    }

//    String[] currentContainedBags = rootBagRule.replaceFirst("(.*contain )", "").split("(, |\\.+[^a-z0-9 ]*)");
//    Pattern pattern = Pattern.compile("(^" + bagRule.substring(2) + "[^.]*\\.)", Pattern.MULTILINE);
//    Matcher matcher = pattern.matcher(bagRules);
//    if (matcher.find()) currentBagRule = matcher.group(1);

}
