package com.in28minutes.microservices.currency_exchange_service.controller;

import com.in28minutes.microservices.currency_exchange_service.bean.CurrencyExchange;
import com.in28minutes.microservices.currency_exchange_service.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    // Creating logger
    private final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private CurrencyExchangeRepository exchangeRepository;

    @Autowired
    private Environment environment;    // From : org.springframework.core.env

    @GetMapping("/from/{currency_from}/to/{currency_to}")     // URL ==> /from/USD/to/INR
    public CurrencyExchange getCurrencyExchange(
            @PathVariable String currency_from,
            @PathVariable String currency_to) {


        System.out.println("Hello Controller!");
        logger.info("Hello India");
        logger.info("getCurrencyExchange called with {} to {}", currency_from, currency_to);

        CurrencyExchange currencyExchange = exchangeRepository
                .findCurrencyExchangeByFromAndTo(currency_from, currency_to);

        if (currencyExchange == null) {
            throw new RuntimeException("Data Not Found!");
        }

        // Extracting the port of the instance which responding,
        // Multiple instances of CurrencyExchange may be running
        String instancePort = environment.getProperty("local.server.port");

        currencyExchange.setEnvironment("Response from : " + instancePort);
        return currencyExchange;
    }
}
