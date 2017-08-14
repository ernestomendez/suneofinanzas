import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ConceptosDePagoDxesoft } from './conceptos-de-pago-dxesoft.model';
import { ConceptosDePagoDxesoftPopupService } from './conceptos-de-pago-dxesoft-popup.service';
import { ConceptosDePagoDxesoftService } from './conceptos-de-pago-dxesoft.service';

@Component({
    selector: 'jhi-conceptos-de-pago-dxesoft-delete-dialog',
    templateUrl: './conceptos-de-pago-dxesoft-delete-dialog.component.html'
})
export class ConceptosDePagoDxesoftDeleteDialogComponent {

    conceptosDePago: ConceptosDePagoDxesoft;

    constructor(
        private conceptosDePagoService: ConceptosDePagoDxesoftService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.conceptosDePagoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'conceptosDePagoListModification',
                content: 'Deleted an conceptosDePago'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-conceptos-de-pago-dxesoft-delete-popup',
    template: ''
})
export class ConceptosDePagoDxesoftDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private conceptosDePagoPopupService: ConceptosDePagoDxesoftPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.conceptosDePagoPopupService
                .open(ConceptosDePagoDxesoftDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
