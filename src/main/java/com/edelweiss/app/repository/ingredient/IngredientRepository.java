package com.edelweiss.app.repository.ingredient;

import com.edelweiss.app.domain.Ingredient;

import java.util.Optional;

public interface IngredientRepository
{
    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
