package com.mihir.nutritionist.service;

import com.mihir.nutritionist.model.FoodInfo;
import com.mihir.nutritionist.model.ResponseFlattened;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface INutritionistService {
    public ResponseFlattened fetchParsedFoodInfo(String app_id, String app_key, String query);
    public ArrayList<String> fetchAutocompleteFoodNames(String app_id, String app_key, String query);
    public ResponseFlattened flattenResponseMapping(FoodInfo foodInfo);
}
