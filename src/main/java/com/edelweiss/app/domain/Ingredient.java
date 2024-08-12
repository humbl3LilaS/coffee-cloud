package com.edelweiss.app.domain;

import lombok.Data;

@Data
public class Ingredient
{
    private final String id;
    private final String name;
    private final Type type;

    public enum Type
    {
        MILK, SYRUPS, CREAM
    }
}
