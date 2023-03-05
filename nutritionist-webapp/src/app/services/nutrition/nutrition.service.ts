import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { Constants } from 'src/app/utils/constants';
import { Observable, throwError } from 'rxjs';
import { NutritionResponse } from 'src/app/models/NutritionResponse';
import { catchError, map } from 'rxjs/internal/operators';

@Injectable({
  providedIn: 'root',
})
export class NutritionService {
  constructor(private http: HttpClient) {}

  getNutritionalInfoFromApi(query: string): Observable<any> {
    const params = new HttpParams()
      .set('app_id', Constants.APP_ID)
      .set('app_key', Constants.APP_KEY)
      .set('query', query);

    const url =
      'http://localhost:8081/nutritionist/parser?' + params.toString();

    return this.http.get(url).pipe(
      map((response: any) => {
        const nutritionInfoResponse: NutritionResponse = {
          userQuery: response.text,
          detectedFoodItem: response.foodLabel,
          calories: response.calories,
          protein: response.protein,
          fat: response.fat,
          carbs: response.carbs,
          fibre: response.fibre,
          imageUrl: response.imageUrl,
          quantity: response.quantity,
          measureLabel: response.measureLabel,
          measuredWeight: response.weight,
          qualifiersLabel: response.qualifiersLabel,
          error: response.error,
          errorMsg: response.errorMsg,
        };
        return nutritionInfoResponse;
      }),
      catchError(this.handleError)
    );
  }

  getFoodNamesSuggestions(query: string): Observable<any> {
    const params = new HttpParams()
      .set('app_id', Constants.APP_ID)
      .set('app_key', Constants.APP_KEY)
      .set('query', query);

    const url =
      'http://localhost:8081/nutritionist/autocomplete?' + params.toString();

    return this.http.get(url).pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse): any {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` + `body was: ${error.error}`
      );
    }
    return throwError('Something bad happened; please try again later.');
  }
}
