package day.seven;

import java.util.Objects;

public class Bag {
    public String name;
    public boolean containsShinyGold;

    public Bag(String name, boolean containsShinyGold) {
        this.name = name;
        this.containsShinyGold = containsShinyGold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bag bag = (Bag) o;
        return name.equals(bag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
