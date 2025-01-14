package com.in28minutes.microservices.currency_exchange_service.bean;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "currency_exchange")
public class CurrencyExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "currency_from")
    private String from;

    @Column(name = "currency_to")
    private String to;

    // BigDecimal allows for high-precision arithmetic calculation where accuracy is critical
    @Column(name = "conversion_multiple")
    private BigDecimal conversionMultiple;

    // If we have multiple instances of CurrencyExchangeService running in parallel,
    // the environment variable will be used to determine which instance is providing the response back.
    @Column(name = "environment")
    private String environment;

    // For JPA/HIBERNATE
    public CurrencyExchange() {
    }

    public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }
}
