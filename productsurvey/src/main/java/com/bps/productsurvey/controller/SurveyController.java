package com.bps.productsurvey.controller;

import com.bps.productsurvey.model.Survey;
import com.bps.productsurvey.model.SurveyModus;
import com.bps.productsurvey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SurveyController {

    @Autowired
    private SurveyService service;

    @PostMapping("/surveys")
    @ResponseStatus(value= HttpStatus.CREATED)
    public void create(@RequestBody Survey survey){
        service.create(survey);
    }

    @GetMapping("/surveys/summary-modus")
    public List<SurveyModus> summaryModus(){
        return service.summaryModus();
    }
}
