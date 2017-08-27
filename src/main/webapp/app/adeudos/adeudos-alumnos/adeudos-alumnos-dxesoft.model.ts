import {AlumnosDxesoft} from '../alumnos/alumnos-dxesoft.model';
import {BidxichiMoneyDxesoft} from '../../entities/bidxichi-money/bidxichi-money-dxesoft.model';
import {AdeudosDxesoft} from '../adeudos/adeudos-dxesoft.model';

export class AdeudosAlumnosDxesoft {
    constructor(
        public alumno?: AlumnosDxesoft,
        public saldo?: BidxichiMoneyDxesoft,
        public adeudos?: AdeudosDxesoft[]
    ) {

    }
}
