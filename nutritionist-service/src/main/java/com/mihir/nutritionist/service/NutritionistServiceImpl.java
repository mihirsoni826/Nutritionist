package com.mihir.nutritionist.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mihir.nutritionist.model.FoodInfo;
import com.mihir.nutritionist.model.ResponseFlattened;
import com.mihir.nutritionist.utils.Constants;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

@Service
public class NutritionistServiceImpl implements INutritionistService {

    private ResponseFlattened response;

    @Override
    public ResponseFlattened fetchParsedFoodInfo(String app_id, String app_key, String query) {

        this.response = new ResponseFlattened();

        URI uri =
                UriComponentsBuilder
                        .fromUriString(Constants.PARSER_BASE_URL)
                        .queryParam("app_id", app_id)
                        .queryParam("app_key", app_key)
                        .queryParam("ingr", query)
                        .build()
                        .toUri();

        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(uri, String.class);
        FoodInfo foodInfo = null;
        try {
            foodInfo = new ObjectMapper().readValue(json, FoodInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println(foodInfo);

        this.response = flattenResponseMapping(foodInfo);

        return this.response;
    }

    @Override
    public ArrayList<String> fetchAutocompleteFoodNames(String app_id, String app_key, String query) {
        URI uri =
                UriComponentsBuilder
                        .fromUriString(Constants.AUTOCOMPLETE_BASE_URL)
                        .queryParam("app_id", app_id)
                        .queryParam("app_key", app_key)
                        .queryParam("q", query)
                        .build()
                        .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ArrayList<String> suggestions = restTemplate.getForObject(uri, ArrayList.class);

        return suggestions;
    }

    @Override
    public ResponseFlattened flattenResponseMapping(FoodInfo foodInfo) {

        // Check if user query was parsed by the API or not
        if(foodInfo.getParsed().size() == 0) {
            this.response.setError(true);
            this.response.setErrorMsg("Nutritional Info does not exist for '" + foodInfo.getText() + "'");

            return this.response;
        } else {
            // Check if quantity was given by the user, if not - default to 1
            if(foodInfo.getParsed().get(0).getQty() == 0)
                foodInfo.getParsed().get(0).setQty(1);

            // Check if measure object exists, if not return without it
             if(foodInfo.getParsed().get(0).getMeasure() == null)
                 return parsedResponseWithoutMeasure(foodInfo);

            // Check if qualifiers exists in the API response
            if(foodInfo.getParsed().get(0).getMeasure().getQualified() != null)
                parseWithQualifiers(foodInfo);
        }

        populateResponseFromFoodInfo(foodInfo);

        return response;
    }

    private void populateResponseFromFoodInfo(FoodInfo foodInfo) {
        this.response.setText(foodInfo.getText());
        this.response.setFoodLabel(foodInfo.getParsed().get(0).getFood().getFoodName());
        this.response.setCalories(foodInfo.getParsed().get(0).getFood().getNutrients().getCalories());
        this.response.setProtein(foodInfo.getParsed().get(0).getFood().getNutrients().getProtein());
        this.response.setFat(foodInfo.getParsed().get(0).getFood().getNutrients().getFat());
        this.response.setCarbs(foodInfo.getParsed().get(0).getFood().getNutrients().getCarbs());
        this.response.setFibre(foodInfo.getParsed().get(0).getFood().getNutrients().getFibre());
        this.response.setImageUrl(foodInfo.getParsed().get(0).getFood().getImageUrl());
        this.response.setQuantity(foodInfo.getParsed().get(0).getQty());
        this.response.setMeasureLabel(foodInfo.getParsed().get(0).getMeasure().getLabel());
        this.response.setWeight(foodInfo.getParsed().get(0).getMeasure().getWeight());
    }

    private void parseWithQualifiers(FoodInfo foodInfo) {
        this.response.setQualifiersLabel(foodInfo.getParsed().get(0).getMeasure().getQualified().get(0).getQualifiers().get(0).getSizeLabel());
    }

    /**
     * Helper method to flatter the FoodInfo object if measure key is null in the API response
     * @param foodInfo
     * @return
     */
    public ResponseFlattened parsedResponseWithoutMeasure(FoodInfo foodInfo) {
        this.response = ResponseFlattened.builder()
                .text(foodInfo.getText())
                .foodLabel(foodInfo.getParsed().get(0).getFood().getFoodName())
                .calories(foodInfo.getParsed().get(0).getFood().getNutrients().getCalories())
                .protein(foodInfo.getParsed().get(0).getFood().getNutrients().getProtein())
                .fat(foodInfo.getParsed().get(0).getFood().getNutrients().getFat())
                .carbs(foodInfo.getParsed().get(0).getFood().getNutrients().getCarbs())
                .fibre(foodInfo.getParsed().get(0).getFood().getNutrients().getFibre())
                .imageUrl(foodInfo.getParsed().get(0).getFood().getImageUrl())
                .quantity(foodInfo.getParsed().get(0).getQty())
                .build();

        return this.response;
    }
}
