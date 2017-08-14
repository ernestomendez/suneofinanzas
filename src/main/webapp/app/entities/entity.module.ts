import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { SuneofinanzasConceptosDePagoDxesoftModule } from './conceptos-de-pago/conceptos-de-pago-dxesoft.module';
import { SuneofinanzasMontosDxesoftModule } from './montos/montos-dxesoft.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        SuneofinanzasConceptosDePagoDxesoftModule,
        SuneofinanzasMontosDxesoftModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuneofinanzasEntityModule {}
