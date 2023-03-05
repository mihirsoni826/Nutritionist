import { HttpClient } from '@angular/common/http';
import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NutritionResponse } from 'src/app/models/NutritionResponse';
import { NutritionService } from 'src/app/services/nutrition/nutrition.service';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss'],
})
export class ModalComponent implements OnInit {
  @Input() modalContent: NutritionResponse;
  @Input() suggestions: string[];

  constructor(
    private http: HttpClient,
    private nutritionService: NutritionService,
    private modalService: NgbModal,
    private activeModal: NgbActiveModal,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    if (this.modalContent.measureLabel == null)
      this.modalContent.measureLabel = 'whole';

    if (this.modalContent.qualifiersLabel == null)
      this.modalContent.qualifiersLabel = 'medium';
  }
}
