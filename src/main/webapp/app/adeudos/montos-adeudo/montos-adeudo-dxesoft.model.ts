import {BidxichiMoneyDxesoft} from "../../entities/bidxichi-money/bidxichi-money-dxesoft.model";

export class MontosAdeudoDxesoft {
    constructor(
        public amount?: BidxichiMoneyDxesoft,
        public taxes?: BidxichiMoneyDxesoft
    ){
        this.amount = new BidxichiMoneyDxesoft();
        this.taxes = new BidxichiMoneyDxesoft();
    }
}
