package com.edelweiss.app.repository.order;

import com.edelweiss.app.domain.CoffeeOrder;

public interface OrderRepository
{
    public CoffeeOrder save(CoffeeOrder order);
}
