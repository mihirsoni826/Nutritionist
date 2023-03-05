package com.mihir.nutritionist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodInfo {

    @JsonProperty(value = "text")
    private String text;

    @JsonProperty(value = "parsed")
    private ArrayList<Parsed> parsed;

}
