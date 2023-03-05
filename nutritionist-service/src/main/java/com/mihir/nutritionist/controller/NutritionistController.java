package com.mihir.nutritionist.controller;

import com.mihir.nutritionist.model.ResponseFlattened;
import com.mihir.nutritionist.service.INutritionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("nutritionist")
@CrossOrigin
public class NutritionistController {

    @Autowired
    INutritionistService nutritionistService;

    @GetMapping("/parser")
    public ResponseEntity<ResponseFlattened> getFoodInfo(
            @RequestParam(required = true) final String app_id,
            @RequestParam(required = true) final String app_key,
            @RequestParam(required = true) final String query
    ) {
        ResponseFlattened response = nutritionistService.fetchParsedFoodInfo(app_id, app_key, query);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/autocomplete")
    public ArrayList<String> getAutocompleteNames(
            @RequestParam(required = true) final String app_id,
            @RequestParam(required = true) final String app_key,
            @RequestParam(required = true) final String query
    ) {
        return nutritionistService.fetchAutocompleteFoodNames(app_id, app_key, query);
    }

}
