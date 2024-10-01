package com.edelweiss.app;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import com.edelweiss.app.domain.Ingredient;
import com.edelweiss.app.domain.Ingredient.Type;
import com.edelweiss.app.repository.IngredientRepository;

@SpringBootApplication
public class CoffeeCloudApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(CoffeeCloudApplication.class, args);
    }

    @Bean
    @Transactional
    ApplicationRunner dataLoder(IngredientRepository repo) {
        return args -> {
            repo.deleteAll();
            repo.save(new Ingredient("WMILK", "Whole Milk", Type.MILK));
            repo.save(new Ingredient("LFMILK", "Low-fat Milk", Type.MILK));
            repo.save(new Ingredient("LTFMILK", "Lactose-free Milk", Type.MILK));
            repo.save(new Ingredient("VSYUP", "Vanilla Syrups", Type.SYRUPS));
            repo.save(new Ingredient("CSYUP", "Caramel Syrups", Type.SYRUPS));
            repo.save(new Ingredient("PSYUP", "Peach Syrups", Type.SYRUPS));
            repo.save(new Ingredient("CHOCRE", "Chocolate Cream", Type.CREAM));
        };
    }

}
