import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit, ChangeDetectorRef } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NutritionResponse } from 'src/app/models/NutritionResponse';
import { ModalControlService } from 'src/app/services/modal-control/modal-control.service';
import { NutritionService } from 'src/app/services/nutrition/nutrition.service';
import { DashboardComponent } from '../dashboard/dashboard.component';


@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.scss'],
})
export class ErrorComponent implements OnInit {
  @Input() modalContent: NutritionResponse;
  @Input() suggestions: string[];

  constructor(
    private http: HttpClient,
    private nutritionService: NutritionService,
    private modalControlService: ModalControlService,
    private activeModal: NgbActiveModal,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {}

  callParsedInfo(event): any {
    new DashboardComponent(
      this.http,
      this.nutritionService,
      this.modalControlService,
      this.cdr
    ).getParsedInfo(null, true, event.target.textContent);

    this.modalControlService.closeModal(this.activeModal);
  }

}
