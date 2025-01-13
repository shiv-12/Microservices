package com.in28minutes.microservices.currency_exchange_service.controller;

import com.in28minutes.microservices.currency_exchange_service.bean.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;    // From : org.springframework.core.env

    @GetMapping("/from/{from}/to/{to}")     // URL ==> /from/USD/to/INR
    public CurrencyExchange getCurrencyExchange(@PathVariable String from, @PathVariable String to) {

        CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));

        // Extracting the port of the instance which responding,
        // Multiple instances of CurrencyExchange may be running
        String instancePort = environment.getProperty("local.server.port");

        currencyExchange.setEnvironment(instancePort);
        return currencyExchange;
    }
}
