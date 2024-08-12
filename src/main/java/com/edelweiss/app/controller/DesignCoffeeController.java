package com.edelweiss.app.controller;

import com.edelweiss.app.domain.Coffee;
import com.edelweiss.app.domain.CoffeeOrder;
import com.edelweiss.app.domain.Ingredient;
import com.edelweiss.app.domain.Ingredient.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("coffeeOrder")
public class DesignCoffeeController
{

    @ModelAttribute
    public void addIngredientToModel(Model model)
    {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("S_MILK", "Stream Milk", Type.MILK),
                new Ingredient("LFAT_MILK", "Low Fat Milk", Type.MILK),
                new Ingredient("LACT_FMILK", "Lactose free milk", Type.MILK),
                new Ingredient("VSYP", "Vanilla Syrups", Type.SYRUPS),
                new Ingredient("HSYP", "Hazelnut Syrups", Type.SYRUPS),
                new Ingredient("CSYP", "Caramel Syrups", Type.SYRUPS),
                new Ingredient("PSYP", "Peach Syrups", Type.SYRUPS),
                new Ingredient("COCA", "Coca cream", Type.CREAM),
                new Ingredient("Choco", "Chocolate cream", Type.CREAM)
        );

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

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type)
    {
        return ingredients.stream().filter(x -> x.getType().equals(type)).toList();
    }
}
