package com.mihir.nutritionist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nutrients {

    @JsonProperty(value = "ENERC_KCAL")
    private float calories;

    @JsonProperty(value = "PROCNT")
    private float protein;

    @JsonProperty(value = "FAT")
    private float fat;

    @JsonProperty(value = "CHOCDF")
    private float carbs;

    @JsonProperty(value = "FIBTG")
    private float fibre;

}
