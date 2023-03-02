package com.selurba.abrules;

public class RuleBuilder {

    private Condition condition;
    private Action action;

    private RuleBuilder(Condition condition) {
        this.condition = condition;
    }

    public static RuleBuilder when(final Condition condition) {
        return new RuleBuilder(condition);
    }

    public Rule then(final Action action) {
        return new DefaultRule(condition, action);
    }
}
