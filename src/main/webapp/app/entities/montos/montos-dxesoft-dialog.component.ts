import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { MontosDxesoft } from './montos-dxesoft.model';
import { MontosDxesoftPopupService } from './montos-dxesoft-popup.service';
import { MontosDxesoftService } from './montos-dxesoft.service';
import { ConceptosDePagoDxesoft, ConceptosDePagoDxesoftService } from '../conceptos-de-pago';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-montos-dxesoft-dialog',
    templateUrl: './montos-dxesoft-dialog.component.html'
})
export class MontosDxesoftDialogComponent implements OnInit {

    montos: MontosDxesoft;
    isSaving: boolean;

    conceptosdepagos: ConceptosDePagoDxesoft[];
    startDateDp: any;
    endDateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private montosService: MontosDxesoftService,
        private conceptosDePagoService: ConceptosDePagoDxesoftService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.conceptosDePagoService.query()
            .subscribe((res: ResponseWrapper) => { this.conceptosdepagos = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        console.log(this.montos);
        this.isSaving = true;
        if (this.montos.id !== undefined) {
            this.subscribeToSaveResponse(
                this.montosService.update(this.montos));
        } else {
            this.subscribeToSaveResponse(
                this.montosService.create(this.montos));
        }
    }

    private subscribeToSaveResponse(result: Observable<MontosDxesoft>) {
        result.subscribe((res: MontosDxesoft) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: MontosDxesoft) {
        this.eventManager.broadcast({ name: 'montosListModification', content: 'OK'});
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

    trackConceptosDePagoById(index: number, item: ConceptosDePagoDxesoft) {
        return item.id;
    }

    calculateTaxes(value) {
        let taxpercentage: number = this.montos.concepto.taxpercentaje;
        taxpercentage = taxpercentage / 100;
        const tax: number = Number(this.montos.amount.value) * taxpercentage;
        this.montos.tax.value = tax;
    }
}

@Component({
    selector: 'jhi-montos-dxesoft-popup',
    template: ''
})
export class MontosDxesoftPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private montosPopupService: MontosDxesoftPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.montosPopupService
                    .open(MontosDxesoftDialogComponent as Component, params['id']);
            } else {
                this.montosPopupService
                    .open(MontosDxesoftDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
