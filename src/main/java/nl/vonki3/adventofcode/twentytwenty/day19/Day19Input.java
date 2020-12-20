package nl.vonki3.adventofcode.twentytwenty.day19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String resultingRules = "(" + expandRule(tmpRedirectRules, parseRule(rule), 0, 0) + ")";
//        System.out.println("Expanded to : " + resultingRules);
        rules.add(resultingRules);

        System.out.println("Ready parsing");
        System.out.println("----------");

    }

    private int getRuleNr(final String line) {
        return Integer.parseInt(line.substring(0, line.indexOf(":")));
    }

    private boolean isMessageRule(final String line) {
        return line.contains("\"");
    }

    protected String parseRule(final String line) {
        String rule = line;
        if (line.contains(":")) {
            rule = rule.substring(rule.indexOf(":") + 2);
        }
        if (isMessageRule(rule)) {
            rule = rule.replace("\"", "");
        }

        return rule;
    }

    private String expandRule(final Map<Integer, String> tmpRedirectRules, final String ruleRefs, final Integer curRuleNr, final int depth) {
        String useRule = ruleRefs;
        String tabs = "";
        for (int i = 0; i < depth; i++) {
            tabs += "\t";
        }
//        System.out.println(tabs + "Rule " + ruleRefs);
        if (!ruleRefs.matches(".*\\d.*")) {
            return ruleRefs;
        }

        final StringBuilder sb = new StringBuilder();
        if (hasReferenceToSelf(curRuleNr, ruleRefs)) {
//            System.out.println(ruleRefs + " vs " + redirectRule);
//            System.out.println("found rule to self: " + curRuleNr + " " + redirectRule);
            if (curRuleNr == 8) {
                sb.append("(" + expandRule(tmpRedirectRules, "42", curRuleNr, depth + 1) + ")*");
            }
            if (curRuleNr == 11) {
                sb.append(expandRule(tmpRedirectRules, "42", curRuleNr, depth + 1)
                        + "(" + expandRule(tmpRedirectRules, "42 31", curRuleNr, depth + 1) + ")*"
                + expandRule(tmpRedirectRules, "31", curRuleNr, depth + 1));
            }
        }
//        System.out.println("Using rule " + useRule);
        else if (useRule.contains("|")) {
            final String[] tmpRules = useRule.split("\\|");
            sb.append("(");
            sb.append(expandRule(tmpRedirectRules, tmpRules[0], curRuleNr, depth + 1));
            sb.append("|");
            sb.append(expandRule(tmpRedirectRules, tmpRules[1], curRuleNr, depth + 1));
            sb.append(")");
        } else {
            final String[] useRuleArray = useRule.split(" ");
            for (final String ruleRef : useRuleArray) {

                if (ruleRef.startsWith("\"")) {
                    sb.append(ruleRef, 1, ruleRef.length() - 2);
                    continue;
                }
                if (StringUtils.isNotBlank(ruleRef)) {
                    final String ruleToExpand = parseRule(tmpRedirectRules.get(Integer.parseInt(ruleRef)));
                    sb.append(expandRule(tmpRedirectRules, ruleToExpand, Integer.parseInt(ruleRef), depth + 1));
                }
            }
        }

//        System.out.println(tabs + "expanded to " + sb.toString());
        return sb.toString();
    }

    private boolean hasReferenceToSelf(final Integer curRule, final String ruleRefs) {
//        System.out.println("Checking "+ ruleRefs + " with rule nr " + curRule);
        final String[] ruleRefsArray = ruleRefs.split(" ");
        for (final String s : ruleRefsArray) {
            if (!"|".equals(s) && s.length() > 0 && Integer.parseInt(s) == curRule.intValue()) {
                return true;
            }
        }
        return false;
    }

}
