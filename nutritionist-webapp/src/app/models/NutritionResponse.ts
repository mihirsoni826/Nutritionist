export interface NutritionResponse {
    userQuery: string;
    detectedFoodItem: string;
    calories: number;
    protein: number;
    fat: number;
    carbs: number;
    fibre: number;
    imageUrl: string;
    quantity: number;
    measureLabel: string;
    measuredWeight: string;
    qualifiersLabel: string;
    error: boolean;
    errorMsg: string;
}