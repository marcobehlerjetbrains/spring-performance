package com.jetbrains.springperformance;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

@RestController
public class TaxController {

    SecureRandom random = new SecureRandom();

    public record TaxRate(String name, Double rate) {}

    @GetMapping("/")
    public TaxRate taxRate() {
        return new TaxRate("German VAT",  random.nextDouble());
    }

}
