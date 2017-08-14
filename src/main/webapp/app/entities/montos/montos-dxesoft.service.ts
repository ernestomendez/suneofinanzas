import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { MontosDxesoft } from './montos-dxesoft.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class MontosDxesoftService {

    private resourceUrl = 'api/montos';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(montos: MontosDxesoft): Observable<MontosDxesoft> {
        const copy = this.convert(montos);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(montos: MontosDxesoft): Observable<MontosDxesoft> {
        const copy = this.convert(montos);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<MontosDxesoft> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.startDate = this.dateUtils
            .convertLocalDateFromServer(entity.startDate);
        entity.endDate = this.dateUtils
            .convertLocalDateFromServer(entity.endDate);
    }

    private convert(montos: MontosDxesoft): MontosDxesoft {
        const copy: MontosDxesoft = Object.assign({}, montos);
        copy.startDate = this.dateUtils
            .convertLocalDateToServer(montos.startDate);
        copy.endDate = this.dateUtils
            .convertLocalDateToServer(montos.endDate);
        return copy;
    }
}
