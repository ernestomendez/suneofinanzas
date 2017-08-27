import {Injectable} from '@angular/core';
import {JhiDateUtils} from 'ng-jhipster';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs';
import {AdeudosAlumnosDxesoft} from './adeudos-alumnos-dxesoft.model';

@Injectable()
export class AdeudosAlumnosDxesoftService {

    private resourceUrl = 'api/adeudos/alumno';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    find(curp?: string): Observable<AdeudosAlumnosDxesoft> {
        return this.http.get(`${this.resourceUrl}/${curp}`).map((res: Response) => {
            const jsonResponse = res.json();
            console.log('#################################################################');
            console.log(jsonResponse);
            console.log('#################################################################');
            this.convertItemFromServer(jsonResponse);
            console.log('$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$');
            console.log(jsonResponse);
            console.log('$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$');
            return jsonResponse;
        });
    }

    private convertItemFromServer(entity: any) {
        entity.creationDate = this.dateUtils
            .convertLocalDateFromServer(entity.creationDate);
        entity.disabledDate = this.dateUtils
            .convertLocalDateFromServer(entity.disabledDate);
    }

}
