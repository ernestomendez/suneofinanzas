import {adeudosAlumnosRoute} from './adeudos-alumnos-dxesoft.route';
import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {SuneofinanzasSharedModule} from '../../shared/shared.module';
import {RouterModule} from '@angular/router';
import {AdeudosAlumnosDxesoftComponent} from './adeudos-alumnos-dxesoft.component';
import {AdeudosAlumnosDxesoftService} from './adeudos-alumnos-dxesoft.service';

const ENTITY_STATES = [
    ...adeudosAlumnosRoute
];

@NgModule({
    imports: [
        SuneofinanzasSharedModule,
        RouterModule.forRoot(ENTITY_STATES, {useHash: true})
    ],
    declarations: [
        // AdeudosAlumnosDxesoft,
        AdeudosAlumnosDxesoftComponent
    ],
    entryComponents: [
        AdeudosAlumnosDxesoftComponent
    ],
    providers: [
        AdeudosAlumnosDxesoftService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuneofinanzasAdeudosAlumnosDxesoftModule {}
