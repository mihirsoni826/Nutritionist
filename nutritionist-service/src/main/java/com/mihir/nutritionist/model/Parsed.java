package com.mihir.nutritionist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Parsed {
    @JsonProperty(value = "food")
    private Food food;

    @JsonProperty(value = "quantity")
    private int qty;

    @JsonProperty(value = "measure")
    private Measure measure;
}
