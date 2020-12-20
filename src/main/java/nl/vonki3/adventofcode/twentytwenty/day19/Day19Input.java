package nl.vonki3.adventofcode.twentytwenty.day19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class Day19Input {
    final List<String> rules = new ArrayList<>();
    final List<String> messages = new ArrayList<>();

    public Day19Input(final List<String> input) {
        System.out.println("----------");
        System.out.println("Parsing");
        System.out.println("----------");

        final Map<Integer, String> tmpRedirectRules = new HashMap<>();
        boolean parsingRules = true;
        for (final String line : input) {
            if (line.length() == 0) {
                parsingRules = false;
                continue;
            }

            if (parsingRules) {
                final int ruleNr = getRuleNr(line);
                tmpRedirectRules.put(ruleNr, parseRule(line));
            } else {
                messages.add(line);
            }
        }
        System.out.println("----------");
        System.out.println(tmpRedirectRules.values());
        System.out.println("----------");

        final String rule = tmpRedirectRules.get(0);
//        System.out.println("Rule: " + rule);
        String resultingRules = "(" + expandRule(tmpRedirectRules, parseRule(rule), 0) + ")";
//        System.out.println("Expanded to : " + resultingRules);
        rules.add(resultingRules);

        System.out.println("Ready parsing");
        System.out.println("----------");

    }

    private int getRuleNr(final String line) {
        return Integer.parseInt(line.substring(0,line.indexOf(":")));
    }

    private boolean isMessageRule(final String line) {
        return line.contains("\"");
    }

    protected String parseRule(final String line) {
        String rule = line;
        if (line.contains(":")) {
            rule = rule.substring(rule.indexOf(":")+2);
        }
        if (isMessageRule(rule)) {
            rule = rule.replace("\"", "");
        }

        return rule;
    }

    private String expandRule(final Map<Integer, String> tmpRedirectRules, final String ruleRefs, final int depth) {
        String tabs = "";
        for (int i = 0; i < depth; i++) {
            tabs += "\t";
        }
//        System.out.println(tabs + "Rule " + ruleRefs);
        if (!ruleRefs.matches(".*\\d.*")) {
            return ruleRefs;
        }

        final StringBuilder sb = new StringBuilder();
//        if (hasReferenceToSelf(rulerefs)) {
//
//        }
        if (ruleRefs.contains("|")) {
            final String[] tmpRules = ruleRefs.split("\\|");
            sb.append("(");
            sb.append(expandRule(tmpRedirectRules, tmpRules[0], depth + 1));
            sb.append("|");
            sb.append(expandRule(tmpRedirectRules, tmpRules[1], depth + 1));
            sb.append(")");
        } else {


            final String[] ruleRefsArray = ruleRefs.split(" ");
            for (final String ruleRef : ruleRefsArray) {

                if (ruleRef.startsWith("\"")) {
                    sb.append(ruleRef.substring(1, ruleRef.length() - 2));
                    continue;
                }
                if (StringUtils.isNotBlank(ruleRef)) {
                    final String ruleToExpand = parseRule(tmpRedirectRules.get(Integer.parseInt(ruleRef)));
                    sb.append(expandRule(tmpRedirectRules, ruleToExpand, depth + 1));
                }
            }
        }

//        System.out.println(tabs + "expanded to " + sb.toString());
        return sb.toString();
    }

}
