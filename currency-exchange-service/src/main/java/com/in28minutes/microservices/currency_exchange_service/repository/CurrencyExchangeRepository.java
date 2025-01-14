package com.in28minutes.microservices.currency_exchange_service.repository;

import com.in28minutes.microservices.currency_exchange_service.bean.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    // <EntityClass, PrimaryKeyDataType>

    // Here from and to should be matched with entity class property (data field)
    public CurrencyExchange findCurrencyExchangeByFromAndTo(String from, String to);
}
