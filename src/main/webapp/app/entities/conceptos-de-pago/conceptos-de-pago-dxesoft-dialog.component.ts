import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ConceptosDePagoDxesoft } from './conceptos-de-pago-dxesoft.model';
import { ConceptosDePagoDxesoftPopupService } from './conceptos-de-pago-dxesoft-popup.service';
import { ConceptosDePagoDxesoftService } from './conceptos-de-pago-dxesoft.service';

@Component({
    selector: 'jhi-conceptos-de-pago-dxesoft-dialog',
    templateUrl: './conceptos-de-pago-dxesoft-dialog.component.html'
})
export class ConceptosDePagoDxesoftDialogComponent implements OnInit {

    conceptosDePago: ConceptosDePagoDxesoft;
    isSaving: boolean;
    creationDateDp: any;
    disabledDateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private conceptosDePagoService: ConceptosDePagoDxesoftService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.conceptosDePago.id !== undefined) {
            this.subscribeToSaveResponse(
                this.conceptosDePagoService.update(this.conceptosDePago));
        } else {
            this.subscribeToSaveResponse(
                this.conceptosDePagoService.create(this.conceptosDePago));
        }
    }

    private subscribeToSaveResponse(result: Observable<ConceptosDePagoDxesoft>) {
        result.subscribe((res: ConceptosDePagoDxesoft) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: ConceptosDePagoDxesoft) {
        this.eventManager.broadcast({ name: 'conceptosDePagoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-conceptos-de-pago-dxesoft-popup',
    template: ''
})
export class ConceptosDePagoDxesoftPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private conceptosDePagoPopupService: ConceptosDePagoDxesoftPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.conceptosDePagoPopupService
                    .open(ConceptosDePagoDxesoftDialogComponent as Component, params['id']);
            } else {
                this.conceptosDePagoPopupService
                    .open(ConceptosDePagoDxesoftDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
