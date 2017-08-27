import {BaseEntity} from '../../shared/model/base-entity';
import {ConceptosDePagoDxesoft} from '../../entities/conceptos-de-pago/conceptos-de-pago-dxesoft.model';
import {MontosAdeudoDxesoft} from '../montos-adeudo/montos-adeudo-dxesoft.model';
import {AlumnosDxesoft} from '../alumnos/alumnos-dxesoft.model';

export class AdeudosDxesoft implements BaseEntity {
    constructor(
        public id?: number,
        public conceptosDePago?: ConceptosDePagoDxesoft,
        public conceptoName?: string,
        public description?: string,
        public status?: string,
        public montos?: MontosAdeudoDxesoft,
        public alumno?: AlumnosDxesoft
    ) {

    }
}
