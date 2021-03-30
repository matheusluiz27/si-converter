package com.si.converter.services;

import com.si.converter.models.SIConversionResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.stream.Stream;

@SpringBootTest
public class SIConverterServiceTests {

    @Autowired
    private SIConverterService siConverterService;

    @ParameterizedTest
    @MethodSource("provideUnitsForSIConversion")
    @DisplayName("Test multiplication factor value")
    void getUnitMultiplicationFactorTest(String input, String expected) throws Exception {
        SIConversionResponseModel sIConversionResponseModel = this.siConverterService.convertUnits(input);
        Assertions.assertEquals(new BigDecimal(expected, new MathContext(14)), sIConversionResponseModel.getMultiplicationFactor());
    }

    @ParameterizedTest
    @MethodSource("provideUnitsNameForSIConversion")
    @DisplayName("Test unit name value")
    void getUnitUnitNameTest(String input, String expected) throws Exception {
        SIConversionResponseModel sIConversionResponseModel = this.siConverterService.convertUnits(input);
        Assertions.assertEquals(expected, sIConversionResponseModel.getUnitName());
    }

    private static Stream<Arguments> provideUnitsForSIConversion() {
        return Stream.of(
                Arguments.of("d", "86400"),
                Arguments.of("(degree/minute)", "0.000290888208666"),
                Arguments.of("ha*°", "1745329.2519943"),
                Arguments.of("L", "1.0E-9")
        );
    }

    private static Stream<Arguments> provideUnitsNameForSIConversion() {
        return Stream.of(
                Arguments.of("d", "s"),
                Arguments.of("(degree/minute)", "(rad/s)"),
                Arguments.of("ha*°", "m²*rad"),
                Arguments.of("L", "m³")
        );
    }
}
