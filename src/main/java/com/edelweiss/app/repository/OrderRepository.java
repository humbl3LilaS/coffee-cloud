package com.edelweiss.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.edelweiss.app.domain.CoffeeOrder;

public interface OrderRepository extends CrudRepository<CoffeeOrder, Long> {
    
}
