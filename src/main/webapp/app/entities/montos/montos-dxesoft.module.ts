import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuneofinanzasSharedModule } from '../../shared';
import {
    MontosDxesoftService,
    MontosDxesoftPopupService,
    MontosDxesoftComponent,
    MontosDxesoftDetailComponent,
    MontosDxesoftDialogComponent,
    MontosDxesoftPopupComponent,
    MontosDxesoftDeletePopupComponent,
    MontosDxesoftDeleteDialogComponent,
    montosRoute,
    montosPopupRoute,
    MontosDxesoftResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...montosRoute,
    ...montosPopupRoute,
];

@NgModule({
    imports: [
        SuneofinanzasSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        MontosDxesoftComponent,
        MontosDxesoftDetailComponent,
        MontosDxesoftDialogComponent,
        MontosDxesoftDeleteDialogComponent,
        MontosDxesoftPopupComponent,
        MontosDxesoftDeletePopupComponent,
    ],
    entryComponents: [
        MontosDxesoftComponent,
        MontosDxesoftDialogComponent,
        MontosDxesoftPopupComponent,
        MontosDxesoftDeleteDialogComponent,
        MontosDxesoftDeletePopupComponent,
    ],
    providers: [
        MontosDxesoftService,
        MontosDxesoftPopupService,
        MontosDxesoftResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuneofinanzasMontosDxesoftModule {}
