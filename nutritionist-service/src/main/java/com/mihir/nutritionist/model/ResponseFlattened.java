package com.mihir.nutritionist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseFlattened {

    public ResponseFlattened() {
        this.error = false;
        this.errorMsg = "";
    }

    private String text;
    private String foodLabel;
    private float calories;
    private float protein;
    private float fat;
    private float carbs;
    private float fibre;
    private String imageUrl;
    private int quantity;
    private String measureLabel;
    private String weight;
    private String qualifiersLabel;
    private boolean error;
    private String errorMsg;

}
