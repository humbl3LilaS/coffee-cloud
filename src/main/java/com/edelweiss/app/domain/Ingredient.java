package com.edelweiss.app.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient
{
    private  String id;
    private  String name;
    private  Type type;

    public enum Type
    {
        MILK, SYRUPS, CREAM
    }
}
