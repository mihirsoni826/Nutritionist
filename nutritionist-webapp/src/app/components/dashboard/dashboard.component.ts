import { HttpClient } from '@angular/common/http';
import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NutritionResponse } from 'src/app/models/NutritionResponse';
import { NutritionService } from 'src/app/services/nutrition/nutrition.service';
import { Modal } from 'bootstrap';
import { Observable, of } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalComponent } from '../modal/modal.component';
import { ErrorComponent } from '../error/error.component';
import { ModalControlService } from 'src/app/services/modal-control/modal-control.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  private userQuery: string;
  private suggestions: string[];
  nutritionalInfoResponse$: Observable<any>;
  loaderActive: boolean = false;
  error: boolean = false;

  @Input() modalContent: NutritionResponse;

  constructor(
    private http: HttpClient,
    private nutritionService: NutritionService,
    private modalControlService: ModalControlService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {}

  getParsedInfo(form: NgForm, linkCall: boolean, linkQuery: string): void {
    this.loaderActive = true;

    if (linkCall) this.userQuery = linkQuery.trim();
    else this.userQuery = form.value.query;

    this.getFoodSuggestions();

    this.nutritionService
      .getNutritionalInfoFromApi(this.userQuery)
      .subscribe((response: NutritionResponse) => {
        
        if (response.error === true) {
          this.modalControlService.openModal(
            ErrorComponent,
            response,
            this.suggestions
          );
          this.error = true;
        } 
        else {
          this.modalControlService.openModal(
            ModalComponent,
            response,
            this.suggestions
          );
        }

        this.loaderActive = false;
        this.cdr.detectChanges();
      });
  }

  getFoodSuggestions(): void {
    this.nutritionService
      .getFoodNamesSuggestions(this.userQuery)
      .subscribe((response: any) => {
        this.suggestions = response;
      });
  }
}
