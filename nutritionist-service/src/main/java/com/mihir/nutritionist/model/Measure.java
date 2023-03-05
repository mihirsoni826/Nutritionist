package com.mihir.nutritionist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Measure {

    @JsonProperty(value = "label")
    private String label;

    @JsonProperty(value = "weight")
    private String weight;

    @JsonProperty(value = "qualified")
    private ArrayList<Qualified> qualified;
}
