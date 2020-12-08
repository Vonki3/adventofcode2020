package nl.vonki3.adventofcode.twentytwenty.day7;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Bag {
    private final String name;
    private Map<Bag, Integer> content = null;
    private boolean traversed = false;

    public void addContent(final Bag bag, final Integer nr) {
        if (content == null) {
            content = new HashMap<>();
        }

        this.content.put(bag, nr);
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Bag))
            return false;

        return ((Bag) other).getName().equals(this.getName());
    }

    public void setTraversed() {
        this.traversed = true;
    }
}
