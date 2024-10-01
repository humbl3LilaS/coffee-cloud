package com.edelweiss.app.controller;

import com.edelweiss.app.domain.CoffeeOrder;
import com.edelweiss.repository.OrderRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("coffeeOrder")
public class OrderController
{
    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }


    @GetMapping("/current")
    public String current()
    {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid CoffeeOrder coffeeOrder, Errors errors, SessionStatus sessionStatus)
    {
        if (errors.hasErrors())
        {
            return "orderForm";
        }

        orderRepository.save(coffeeOrder);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
