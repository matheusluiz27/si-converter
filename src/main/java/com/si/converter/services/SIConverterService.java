package com.si.converter.services;

import com.si.converter.exceptions.InvalidUnitsException;
import com.si.converter.models.SIConversionResponseModel;
import com.si.converter.utils.TextConverterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

@Service
public class SIConverterService {
    Logger logger = LoggerFactory.getLogger(SIConverterService.class);

    public SIConversionResponseModel convertUnits(String units) throws Exception {
        this.logger.info("starting unit conversion: ", units);
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("groovy");
        String multiplicationFactor = "";
        String sIUnits = TextConverterUtils.convertUnitNameToSI(units);
        String mathematicalExpression = TextConverterUtils.convertUnitNameInMathematicalExpression(units);
        try {
            multiplicationFactor  = scriptEngine.eval(mathematicalExpression).toString();
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            throw new InvalidUnitsException();
        }

        return new SIConversionResponseModel(sIUnits, multiplicationFactor);
    }
}
