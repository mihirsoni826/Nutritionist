package com.mihir.nutritionist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Qualified {

    @JsonProperty(value = "qualifiers")
    private ArrayList<Qualifiers> qualifiers;

    @JsonProperty(value = "weight")
    private float weight;

}
