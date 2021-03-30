package com.si.converter.controllers;

import com.si.converter.models.SIConversionResponseModel;
import com.si.converter.services.SIConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/units/si")
public class SIConverterController {

    @Autowired
    private SIConverterService siConverterService;

    @GetMapping
    public ResponseEntity convertUnits(@RequestParam("units") String units) throws Exception {
        SIConversionResponseModel siConversionResponseModel = this.siConverterService.convertUnits(units);
        return ResponseEntity.ok(siConversionResponseModel);
    }
}
