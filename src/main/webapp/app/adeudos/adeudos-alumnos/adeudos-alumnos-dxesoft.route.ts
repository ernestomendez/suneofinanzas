import {Routes} from '@angular/router';
import {AdeudosAlumnosDxesoft} from './adeudos-alumnos-dxesoft.model';
import {UserRouteAccessService} from '../../shared/auth/user-route-access-service';

export const adeudosAlumnosRoute: Routes = [
    {
        path: 'adeudos-alumnos-dxesoft',
        component: AdeudosAlumnosDxesoft,
        // resolve: {},
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'suneofinanzasApp.adeudosAlumno.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
