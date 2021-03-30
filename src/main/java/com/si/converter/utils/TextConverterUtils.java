package com.si.converter.utils;

public class TextConverterUtils {

    public static String convertUnitNameToSI(String unitName) {
        boolean removeExternalParentheses = false;
        if (unitName.charAt(0) != '(' && unitName.charAt(unitName.length()-1) != ')') {
            unitName = "(" + unitName + ')';
            removeExternalParentheses = true;
        }

        String sIUnitName = unitName
                .replaceAll("/"," / ")
                .replaceAll("\\("," ( ")
                .replaceAll("\\)"," ) ")
                .replaceAll("\\*"," * ")
                .replaceAll("( minute | min )", "s")
                .replaceAll("( hour | h )", "s")
                .replaceAll("( day | d )", "s")
                .replaceAll("( degree | °)", "rad")
                .replaceAll("( arcminute | ' )", "rad")
                .replaceAll("( arcsecond | \" )", "rad")
                .replaceAll("( hectare | ha )", "m²")
                .replaceAll("( litre | L )", "m³")
                .replaceAll("( tonne | t )", "kg")
                .replaceAll(" ", "");

        if(removeExternalParentheses) {
            StringBuilder aux = new StringBuilder(sIUnitName);
            aux.deleteCharAt(0);
            aux.deleteCharAt(aux.length()-1);
            sIUnitName = aux.toString();
        }
        return sIUnitName;
    }

    public static String convertUnitNameInMathematicalExpression(String unitName) {
        boolean removeExternalParentheses = false;
        if (unitName.charAt(0) != '(' && unitName.charAt(unitName.length()-1) != ')') {
            unitName = "(" + unitName + ')';
            removeExternalParentheses = true;
        }
        String mathematicalExpression = unitName
                .replaceAll("/"," / ")
                .replaceAll("\\("," ( ")
                .replaceAll("\\)"," ) ")
                .replaceAll("\\*"," * ")
                .replaceAll("( minute | min )", "60")
                .replaceAll("( hour | h )", "3600")
                .replaceAll("( day | d )", "86400")
                .replaceAll("( degree | ° )", "("+Math.PI+"/180)")
                .replaceAll("( arcminute | ' )", "("+Math.PI+"/10800)")
                .replaceAll("( arcsecond | \" )", "("+Math.PI+"/648000)")
                .replaceAll("( hectare | ha )", ""+Math.pow(10000,2))
                .replaceAll("( litre | L )", ""+Math.pow(.001, 3))
                .replaceAll("( tonne | t )", "1000")
                .replaceAll(" ", "");

        if(removeExternalParentheses) {
            StringBuilder aux = new StringBuilder(mathematicalExpression);
            aux.deleteCharAt(0);
            aux.deleteCharAt(aux.length()-1);
            mathematicalExpression = aux.toString();
        }
        return mathematicalExpression;
    }
}
