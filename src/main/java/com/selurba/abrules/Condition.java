package com.selurba.abrules;

@FunctionalInterface
public interface Condition {
    boolean evaluate(Facts facts);
}
