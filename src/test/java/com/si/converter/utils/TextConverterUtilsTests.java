package com.si.converter.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class TextConverterUtilsTests {

    @ParameterizedTest
    @MethodSource("provideStringsForUnitNameSIConversion")
    @DisplayName("Convert unit names to SI")
    void convertUnitNameTestToSI(String input, String expected) {
        Assertions.assertEquals(expected, TextConverterUtils.convertUnitNameToSI(input));
    }

    @ParameterizedTest
    @MethodSource("provideStringsForUnitNameMathematicalExpressionConverter")
    @DisplayName("Convert the unit name with to corresponding mathematical expression")
    void convertUnitNameToMathematicalExpression(String input, String expected) {
        Assertions.assertEquals(expected, TextConverterUtils.convertUnitNameInMathematicalExpression(input));
    }

    private static Stream<Arguments> provideStringsForUnitNameSIConversion() {
        return Stream.of(
                Arguments.of("minute", "s"),
                Arguments.of("d", "s"),
                Arguments.of("(degree/minute)", "(rad/s)"),
                Arguments.of("litre/h", "m³/s"),
                Arguments.of("ha*°", "m²*rad"),
                Arguments.of("arcminute", "rad"),
                Arguments.of("\"", "rad"),
                Arguments.of("t", "kg")
        );
    }

    private static Stream<Arguments> provideStringsForUnitNameMathematicalExpressionConverter() {
        return Stream.of(
                Arguments.of("minute", "60"),
                Arguments.of("d", "86400"),
                Arguments.of("(degree/minute)", "(("+Math.PI + "/180)/60)"),
                Arguments.of("litre/h", Math.pow(.001, 3)+"/3600"),
                Arguments.of("ha*°", Math.pow(10000, 2)+"*("+Math.PI +"/180)"),
                Arguments.of("arcminute", "("+Math.PI +"/10800)"),
                Arguments.of("\"", "("+Math.PI +"/648000)"),
                Arguments.of("t", "1000")
        );
    }
}
