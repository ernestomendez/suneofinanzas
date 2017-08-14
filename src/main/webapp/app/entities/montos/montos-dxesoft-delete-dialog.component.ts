import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MontosDxesoft } from './montos-dxesoft.model';
import { MontosDxesoftPopupService } from './montos-dxesoft-popup.service';
import { MontosDxesoftService } from './montos-dxesoft.service';

@Component({
    selector: 'jhi-montos-dxesoft-delete-dialog',
    templateUrl: './montos-dxesoft-delete-dialog.component.html'
})
export class MontosDxesoftDeleteDialogComponent {

    montos: MontosDxesoft;

    constructor(
        private montosService: MontosDxesoftService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.montosService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'montosListModification',
                content: 'Deleted an montos'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-montos-dxesoft-delete-popup',
    template: ''
})
export class MontosDxesoftDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private montosPopupService: MontosDxesoftPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.montosPopupService
                .open(MontosDxesoftDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
