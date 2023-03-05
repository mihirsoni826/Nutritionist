package com.mihir.nutritionist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Food {

    @JsonProperty(value = "label")
    private String foodName;

    @JsonProperty(value = "nutrients")
    private Nutrients nutrients;

    @JsonProperty(value = "image")
    private String imageUrl;

}
