package com.edelweiss.app.controller;

import com.edelweiss.app.domain.CoffeeOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/current")
    public String current()
    {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(CoffeeOrder coffeeOrder, SessionStatus sessionStatus)
    {
        log.info("Order submitted {}", coffeeOrder);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
