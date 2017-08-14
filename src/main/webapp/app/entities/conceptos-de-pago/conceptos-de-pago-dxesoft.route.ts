import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ConceptosDePagoDxesoftComponent } from './conceptos-de-pago-dxesoft.component';
import { ConceptosDePagoDxesoftDetailComponent } from './conceptos-de-pago-dxesoft-detail.component';
import { ConceptosDePagoDxesoftPopupComponent } from './conceptos-de-pago-dxesoft-dialog.component';
import { ConceptosDePagoDxesoftDeletePopupComponent } from './conceptos-de-pago-dxesoft-delete-dialog.component';

@Injectable()
export class ConceptosDePagoDxesoftResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const conceptosDePagoRoute: Routes = [
    {
        path: 'conceptos-de-pago-dxesoft',
        component: ConceptosDePagoDxesoftComponent,
        resolve: {
            'pagingParams': ConceptosDePagoDxesoftResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'suneofinanzasApp.conceptosDePago.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'conceptos-de-pago-dxesoft/:id',
        component: ConceptosDePagoDxesoftDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'suneofinanzasApp.conceptosDePago.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const conceptosDePagoPopupRoute: Routes = [
    {
        path: 'conceptos-de-pago-dxesoft-new',
        component: ConceptosDePagoDxesoftPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'suneofinanzasApp.conceptosDePago.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'conceptos-de-pago-dxesoft/:id/edit',
        component: ConceptosDePagoDxesoftPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'suneofinanzasApp.conceptosDePago.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'conceptos-de-pago-dxesoft/:id/delete',
        component: ConceptosDePagoDxesoftDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'suneofinanzasApp.conceptosDePago.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
