package com.selurba.abrules;

@FunctionalInterface
public interface Action {
    void execute(Facts facts);
}
