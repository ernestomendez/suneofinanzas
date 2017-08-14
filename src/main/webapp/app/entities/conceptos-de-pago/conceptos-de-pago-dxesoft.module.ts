import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuneofinanzasSharedModule } from '../../shared';
import {
    ConceptosDePagoDxesoftService,
    ConceptosDePagoDxesoftPopupService,
    ConceptosDePagoDxesoftComponent,
    ConceptosDePagoDxesoftDetailComponent,
    ConceptosDePagoDxesoftDialogComponent,
    ConceptosDePagoDxesoftPopupComponent,
    ConceptosDePagoDxesoftDeletePopupComponent,
    ConceptosDePagoDxesoftDeleteDialogComponent,
    conceptosDePagoRoute,
    conceptosDePagoPopupRoute,
    ConceptosDePagoDxesoftResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...conceptosDePagoRoute,
    ...conceptosDePagoPopupRoute,
];

@NgModule({
    imports: [
        SuneofinanzasSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ConceptosDePagoDxesoftComponent,
        ConceptosDePagoDxesoftDetailComponent,
        ConceptosDePagoDxesoftDialogComponent,
        ConceptosDePagoDxesoftDeleteDialogComponent,
        ConceptosDePagoDxesoftPopupComponent,
        ConceptosDePagoDxesoftDeletePopupComponent,
    ],
    entryComponents: [
        ConceptosDePagoDxesoftComponent,
        ConceptosDePagoDxesoftDialogComponent,
        ConceptosDePagoDxesoftPopupComponent,
        ConceptosDePagoDxesoftDeleteDialogComponent,
        ConceptosDePagoDxesoftDeletePopupComponent,
    ],
    providers: [
        ConceptosDePagoDxesoftService,
        ConceptosDePagoDxesoftPopupService,
        ConceptosDePagoDxesoftResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuneofinanzasConceptosDePagoDxesoftModule {}
