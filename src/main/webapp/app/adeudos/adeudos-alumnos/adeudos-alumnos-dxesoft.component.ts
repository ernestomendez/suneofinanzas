import {Component} from '@angular/core';
import {AdeudosAlumnosDxesoft} from './adeudos-alumnos-dxesoft.model';
import {AdeudosAlumnosDxesoftService} from './adeudos-alumnos-dxesoft.service';

@Component({
    selector: 'jhi-adeudos-alumnos-dxesoft',   // el prefijo jhi es por una preconfiguracion de jhipster
    templateUrl: './adeudos-alumnos-dxesoft.component.html'
})
export class AdeudosAlumnosDxesoftComponent {

    adeudosAlumnos: AdeudosAlumnosDxesoft;
    // private subscription: Subscription;
    // private eventSubscriber: Subscription;

    constructor(
        // private eventManager: JhiEventManager  //Por el momento no se va a utilizar el event manager.
        private adeudosAlumnosService: AdeudosAlumnosDxesoftService,
        // private route: ActivatedRoute

    ) {
        console.log('@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ aqui estuve yo ########################33');
    }

    load(curp) {
        this.adeudosAlumnosService.find(curp).subscribe((adeudos) => {
            this.adeudosAlumnos = adeudos;
        });
    }
    previousState() {
        window.history.back();
    }
}
