import { BaseEntity } from './../../shared';

export class ConceptosDePagoDxesoft implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public creationDate?: any,
        public disabledDate?: any,
        public taxpercentaje?: number,
        public active?: boolean,
        public lineaCapturaRequired?: boolean
    ) {
        this.active = true;
        this.lineaCapturaRequired = true;
    }
}
