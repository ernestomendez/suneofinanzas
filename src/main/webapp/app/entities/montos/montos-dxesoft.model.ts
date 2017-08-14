import { BaseEntity } from './../../shared';
import {ConceptosDePagoDxesoft} from '../conceptos-de-pago/conceptos-de-pago-dxesoft.model';
import {BidxichiMoneyDxesoft} from '../bidxichi-money/bidxichi-money-dxesoft.model';

export class MontosDxesoft implements BaseEntity {
    constructor(
        public id?: number,
        public amount?: BidxichiMoneyDxesoft,
        public startDate?: any,
        public endDate?: any,
        public ciclo?: string,
        public active?: boolean,
        public tax?: BidxichiMoneyDxesoft,
        public concepto?: ConceptosDePagoDxesoft,
    ) {
        this.active = true;
        this.amount = new BidxichiMoneyDxesoft();
        this.tax = new BidxichiMoneyDxesoft();
    }
}
