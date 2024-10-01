package com.edelweiss.app.controller;


import com.edelweiss.app.domain.Coffee;
import com.edelweiss.app.domain.CoffeeOrder;
import com.edelweiss.app.domain.Ingredient;
import com.edelweiss.app.domain.Ingredient.Type;
import com.edelweiss.app.repository.IngredientRepository;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/design")
@SessionAttributes("coffeeOrder")
public class DesignCoffeeController
{

    private final IngredientRepository ingredientRepository;

    public DesignCoffeeController(IngredientRepository ingredientRepository)
    {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientToModel(Model model)
    {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        Type[] types = Type.values();
        for (Type type : types)
        {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
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

        return "redirect:/orders/current";
    }
    

    private Iterable<Ingredient> filterByType(
            Iterable<Ingredient> ingredients, Type type)
    {

        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }
}