package mx.com.dxesoft.suneofinanzas.service;

import mx.com.dxesoft.suneofinanzas.datatypes.Alumno;
import mx.com.dxesoft.suneofinanzas.domain.Adeudo;
import mx.com.dxesoft.suneofinanzas.service.dto.AdeudoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * suneofinanzas, mx.com.dxesoft.suneofinanzas.service . AdeudosService
 * Created by ernesto on 20/08/17.
 */
public interface AdeudosService {

    Page<Adeudo> findAll(Pageable pageable);

    /**
     * Finds all the actives adeudos for a given alumno.
     *
     * @param alumno alumno to find.
     * @return adeudos for the alumno.
     */
    AdeudoDto findAdeudosByAlumno(Alumno alumno);
}
