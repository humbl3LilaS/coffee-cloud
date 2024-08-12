package com.edelweiss.app.convertor;


import com.edelweiss.app.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient>
{
    private Map<String, Ingredient> ingredientsMap = new HashMap<>();

    public IngredientByIdConverter()
    {
        ingredientsMap.put("S_MILK", new Ingredient("S_MILK", "Stream Milk", Ingredient.Type.MILK));
        ingredientsMap.put("LFAT_MILK", new Ingredient("LFAT_MILK", "Low Fat Milk", Ingredient.Type.MILK));
        ingredientsMap.put("LACT_FMILK", new Ingredient("LACT_FMILK", "Lactose free milk", Ingredient.Type.MILK));
        ingredientsMap.put("VSYP", new Ingredient("VSYP", "Vanilla Syrups", Ingredient.Type.SYRUPS));
        ingredientsMap.put("HSYP", new Ingredient("HSYP", "Hazelnut Syrups", Ingredient.Type.SYRUPS));
        ingredientsMap.put("CSYP", new Ingredient("CSYP", "Caramel Syrups", Ingredient.Type.SYRUPS));
        ingredientsMap.put("PSYP", new Ingredient("PSYP", "Peach Syrups", Ingredient.Type.SYRUPS));
        ingredientsMap.put("COCA", new Ingredient("COCA", "Coca cream", Ingredient.Type.CREAM));
        ingredientsMap.put("CHOCO", new Ingredient("CHOCO", "Chocolate cream", Ingredient.Type.CREAM));
    }

    @Override
    public Ingredient convert(String id)
    {
        return ingredientsMap.get(id);
    }

}
