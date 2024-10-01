package com.edelweiss.app.repository;

import org.springframework.data.repository.CrudRepository;
import com.edelweiss.app.domain.Ingredient;

public interface IngredientRepository 
         extends CrudRepository<Ingredient, String> {

}