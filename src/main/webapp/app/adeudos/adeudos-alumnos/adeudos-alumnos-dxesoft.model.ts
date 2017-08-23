import { BaseEntity } from './../../shared';

export class AdeudosAlumnosDxesoft implements BaseEntity {
    constructor(
        public id?: number,
        public concepto?: BaseEntity,
        public conceptoName?: string,
        public description?: string,


    ){}
}
