package com.company.toyrobotsimulator.rule;

public class RuleResult {
    private final Decision decision;
    private final String output;

    public RuleResult(Decision decision, String output) {
        this.decision = decision;
        this.output = output;
    }

    public Decision getDecision() {
        return decision;
    }

    public String getOutput() {
        return output;
    }
}
