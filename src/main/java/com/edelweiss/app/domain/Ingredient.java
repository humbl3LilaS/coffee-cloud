package com.edelweiss.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Table
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient
{
    @Id
    private String id;
    
    private  String name;
    private  Type type;

    public enum Type
    {
        MILK, SYRUPS, CREAM
    }
}
