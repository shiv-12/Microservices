package com.in28minutes.microservices.currency_exchange_service.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    @GetMapping("/sample-api")
    // if error comes, it will call the same url total 5 times
    @Retry(name = "sample-api-retry", fallbackMethod = "hardCodedMethod")

    // For rate limiting (At max 2 calls, in 10 sec)
    @RateLimiter(name = "default")
    public String sampleApi() {
        System.out.println("sample-api Called!");
        return new RestTemplate().getForEntity(
                        "http://localhost:8080/some-dummy-url", // Invalid URL
                        String.class)
                .getBody();
    }

    public String hardCodedMethod(Exception exception) {
        return "HARD-CODED-RESPONSE";
    }
}
