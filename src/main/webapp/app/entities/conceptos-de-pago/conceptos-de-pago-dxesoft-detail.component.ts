import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { ConceptosDePagoDxesoft } from './conceptos-de-pago-dxesoft.model';
import { ConceptosDePagoDxesoftService } from './conceptos-de-pago-dxesoft.service';

@Component({
    selector: 'jhi-conceptos-de-pago-dxesoft-detail',
    templateUrl: './conceptos-de-pago-dxesoft-detail.component.html'
})
export class ConceptosDePagoDxesoftDetailComponent implements OnInit, OnDestroy {

    conceptosDePago: ConceptosDePagoDxesoft;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private conceptosDePagoService: ConceptosDePagoDxesoftService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConceptosDePagos();
    }

    load(id) {
        this.conceptosDePagoService.find(id).subscribe((conceptosDePago) => {
            this.conceptosDePago = conceptosDePago;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConceptosDePagos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'conceptosDePagoListModification',
            (response) => this.load(this.conceptosDePago.id)
        );
    }
}
