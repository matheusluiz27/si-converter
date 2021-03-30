package com.si.converter.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.MathContext;

@Data
@Setter(value = AccessLevel.NONE)
public class SIConversionResponseModel {

    @JsonProperty("unit_name")
    private String unitName;

    @JsonProperty("multiplication_factor")
    private BigDecimal multiplicationFactor;

    public SIConversionResponseModel(String unitName, String multiplicationFactor) {
        this.unitName = unitName;
        this.multiplicationFactor = new BigDecimal(multiplicationFactor, new MathContext(14));
    }

    public SIConversionResponseModel(String unitName, BigDecimal multiplicationFactor) {
        this.unitName = unitName;
        this.multiplicationFactor =  multiplicationFactor.round(new MathContext(14));
    }
}
