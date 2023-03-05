import { Injectable } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NutritionResponse } from 'src/app/models/NutritionResponse';

@Injectable({
  providedIn: 'root'
})
export class ModalControlService {

  constructor(
    private modalService: NgbModal
  ) { }

  openModal(component: any, response: NutritionResponse, suggestions: string[]): void {
    let modalRef = this.modalService.open(component, {
      animation: true,
      centered: true,
    });

    modalRef.componentInstance.modalContent = response;
    modalRef.componentInstance.suggestions = suggestions;
  }

  closeModal(modal: NgbActiveModal): void {
    modal.close();
  }
}
