import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { MontosDxesoft } from './montos-dxesoft.model';
import { MontosDxesoftService } from './montos-dxesoft.service';

@Component({
    selector: 'jhi-montos-dxesoft-detail',
    templateUrl: './montos-dxesoft-detail.component.html'
})
export class MontosDxesoftDetailComponent implements OnInit, OnDestroy {

    montos: MontosDxesoft;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private montosService: MontosDxesoftService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMontos();
    }

    load(id) {
        this.montosService.find(id).subscribe((montos) => {
            this.montos = montos;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMontos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'montosListModification',
            (response) => this.load(this.montos.id)
        );
    }
}
