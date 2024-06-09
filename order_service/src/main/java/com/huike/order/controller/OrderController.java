package com.huike.order.controller;

//import com.huike.order.command.OrderCommand;

import com.huike.order.command.OrderCommand;
import com.huike.order.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
    public Product findById(@PathVariable Long id) {
        return restTemplate.getForObject("http://service-product/product/" + id, Product.class);
    }

    @RequestMapping(value = "/buy2/{id}", method = RequestMethod.GET)
    public Product findById2(@PathVariable Long id) {
        return new OrderCommand(restTemplate, id).execute();
    }

}
