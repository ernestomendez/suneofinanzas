import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { ConceptosDePagoDxesoft } from './conceptos-de-pago-dxesoft.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ConceptosDePagoDxesoftService {

    private resourceUrl = 'api/conceptos-de-pagos';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(conceptosDePago: ConceptosDePagoDxesoft): Observable<ConceptosDePagoDxesoft> {
        const copy = this.convert(conceptosDePago);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(conceptosDePago: ConceptosDePagoDxesoft): Observable<ConceptosDePagoDxesoft> {
        const copy = this.convert(conceptosDePago);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<ConceptosDePagoDxesoft> {
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
        entity.creationDate = this.dateUtils
            .convertLocalDateFromServer(entity.creationDate);
        entity.disabledDate = this.dateUtils
            .convertLocalDateFromServer(entity.disabledDate);
    }

    private convert(conceptosDePago: ConceptosDePagoDxesoft): ConceptosDePagoDxesoft {
        const copy: ConceptosDePagoDxesoft = Object.assign({}, conceptosDePago);
        copy.creationDate = this.dateUtils
            .convertLocalDateToServer(conceptosDePago.creationDate);
        copy.disabledDate = this.dateUtils
            .convertLocalDateToServer(conceptosDePago.disabledDate);
        return copy;
    }
}