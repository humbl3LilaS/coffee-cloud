package com.edelweiss.app.domain;

import java.util.ArrayList;
import java.util.List;

public class CoffeeOrder
{
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    private List<Coffee> coffees = new ArrayList<>();

    public void addCoffee(Coffee coffee)
    {
        coffees.add(coffee);
    }
}
