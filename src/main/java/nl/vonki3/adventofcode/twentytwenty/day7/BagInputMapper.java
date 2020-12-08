package nl.vonki3.adventofcode.twentytwenty.day7;

public class BagInputMapper implements nl.vonki3.adventofcode.twentytwenty.util.MapInputInterface<Bag> {

    @Override
    public Bag map(final String inputPart) {
        final String[] input = inputPart.split("bags contain");
        final Bag bag = new Bag(input[0].trim());
        final String[] content = input[1].split(",");
        for (final String s : content) {
            if (!s.contains("no other bags")) {
                final String[] split = s.startsWith(" ") ? s.substring(1).split(" ") : s.split(" ");
                bag.addContent(new Bag(split[1] + " " + split[2]), Integer.valueOf(split[0]));
            }
        }
        return bag;
    }
}
