package com.edelweiss.app.convertor;


import com.edelweiss.app.repository.ingredient.IngredientRepository;
import com.edelweiss.app.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class IngredientByIdConverter implements Converter<String, Ingredient>
{
    private IngredientRepository ingredientRepo;

    public IngredientByIdConverter(IngredientRepository ingredientRepo)
    {
        this.ingredientRepo = ingredientRepo;
    }


    @Override
    public Ingredient convert(String id)
    {
        return ingredientRepo.findById(id).orElse(null);
    }

}
