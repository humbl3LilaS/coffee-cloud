package com.edelweiss.repository;

import org.springframework.data.repository.CrudRepository;

import com.edelweiss.app.domain.CoffeeOrder;

public interface OrderRepository extends CrudRepository<CoffeeOrder, Long> {
    
}
