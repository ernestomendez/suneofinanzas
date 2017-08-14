import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { MontosDxesoft } from './montos-dxesoft.model';
import { MontosDxesoftService } from './montos-dxesoft.service';

@Injectable()
export class MontosDxesoftPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private montosService: MontosDxesoftService

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
                this.montosService.find(id).subscribe((montos) => {
                    if (montos.startDate) {
                        montos.startDate = {
                            year: montos.startDate.getFullYear(),
                            month: montos.startDate.getMonth() + 1,
                            day: montos.startDate.getDate()
                        };
                    }
                    if (montos.endDate) {
                        montos.endDate = {
                            year: montos.endDate.getFullYear(),
                            month: montos.endDate.getMonth() + 1,
                            day: montos.endDate.getDate()
                        };
                    }
                    this.ngbModalRef = this.montosModalRef(component, montos);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.montosModalRef(component, new MontosDxesoft());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    montosModalRef(component: Component, montos: MontosDxesoft): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.montos = montos;
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
