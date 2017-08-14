import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { MontosDxesoftComponent } from './montos-dxesoft.component';
import { MontosDxesoftDetailComponent } from './montos-dxesoft-detail.component';
import { MontosDxesoftPopupComponent } from './montos-dxesoft-dialog.component';
import { MontosDxesoftDeletePopupComponent } from './montos-dxesoft-delete-dialog.component';

@Injectable()
export class MontosDxesoftResolvePagingParams implements Resolve<any> {

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

export const montosRoute: Routes = [
    {
        path: 'montos-dxesoft',
        component: MontosDxesoftComponent,
        resolve: {
            'pagingParams': MontosDxesoftResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'suneofinanzasApp.montos.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'montos-dxesoft/:id',
        component: MontosDxesoftDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'suneofinanzasApp.montos.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const montosPopupRoute: Routes = [
    {
        path: 'montos-dxesoft-new',
        component: MontosDxesoftPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'suneofinanzasApp.montos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'montos-dxesoft/:id/edit',
        component: MontosDxesoftPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'suneofinanzasApp.montos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'montos-dxesoft/:id/delete',
        component: MontosDxesoftDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'suneofinanzasApp.montos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
