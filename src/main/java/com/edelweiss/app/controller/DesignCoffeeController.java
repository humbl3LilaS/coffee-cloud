package com.edelweiss.app.controller;

import com.edelweiss.app.data.repository.ingredient.IngredientRepository;
import com.edelweiss.app.domain.Coffee;
import com.edelweiss.app.domain.CoffeeOrder;
import com.edelweiss.app.domain.Ingredient;
import com.edelweiss.app.domain.Ingredient.Type;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("coffeeOrder")
public class DesignCoffeeController
{
    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignCoffeeController(IngredientRepository ingredientRepo)
    {
        this.ingredientRepo = ingredientRepo;
    }


    @ModelAttribute
    public void addIngredientToModel(Model model)
    {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type : types)
        {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }


    @ModelAttribute(name = "coffeeOrder")
    public CoffeeOrder order()
    {
        return new CoffeeOrder();
    }

    @ModelAttribute(name = "coffee")
    public Coffee coffee()
    {
        return new Coffee();
    }


    @GetMapping
    public String showDesignForm()
    {
        return "design";
    }

    @PostMapping
    public String processCoffee(@Valid Coffee coffee, Errors errors, @ModelAttribute CoffeeOrder coffeeOrder)
    {

        if (errors.hasErrors())
        {
            return "design";
        }

        coffeeOrder.addCoffee(coffee);
        log.info("Processing coffee: {}", coffee);

        return "redirect:/orders/current";
    }


    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type)
    {
        return StreamSupport.stream(ingredients.spliterator(), false).filter(x -> x.getType().equals(type)).toList();
    }
}
