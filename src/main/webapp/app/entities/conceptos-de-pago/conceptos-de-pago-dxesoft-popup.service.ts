import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ConceptosDePagoDxesoft } from './conceptos-de-pago-dxesoft.model';
import { ConceptosDePagoDxesoftService } from './conceptos-de-pago-dxesoft.service';

@Injectable()
export class ConceptosDePagoDxesoftPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private conceptosDePagoService: ConceptosDePagoDxesoftService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.conceptosDePagoService.find(id).subscribe((conceptosDePago) => {
                    if (conceptosDePago.creationDate) {
                        conceptosDePago.creationDate = {
                            year: conceptosDePago.creationDate.getFullYear(),
                            month: conceptosDePago.creationDate.getMonth() + 1,
                            day: conceptosDePago.creationDate.getDate()
                        };
                    }
                    if (conceptosDePago.disabledDate) {
                        conceptosDePago.disabledDate = {
                            year: conceptosDePago.disabledDate.getFullYear(),
                            month: conceptosDePago.disabledDate.getMonth() + 1,
                            day: conceptosDePago.disabledDate.getDate()
                        };
                    }
                    this.ngbModalRef = this.conceptosDePagoModalRef(component, conceptosDePago);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.conceptosDePagoModalRef(component, new ConceptosDePagoDxesoft());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    conceptosDePagoModalRef(component: Component, conceptosDePago: ConceptosDePagoDxesoft): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.conceptosDePago = conceptosDePago;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
