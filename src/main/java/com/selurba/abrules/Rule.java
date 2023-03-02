package com.selurba.abrules;

@FunctionalInterface
public interface Rule {
    void perform(Facts facts);
}

