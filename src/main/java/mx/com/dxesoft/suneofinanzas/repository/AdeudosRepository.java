package mx.com.dxesoft.suneofinanzas.repository;

import mx.com.dxesoft.suneofinanzas.domain.Adeudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * suneofinanzas, mx.com.dxesoft.suneofinanzas.repository . AdeudosRepository
 * Created by ernesto on 20/08/17.
 */
@Repository
public interface AdeudosRepository extends JpaRepository<Adeudo, Long> {

    /**
     * Selects all the activo or pago_parcial adeudos from a given alumno.
     *
     * @param curp alumnos curp to search.
     * @return list of adeudos.
     */
    @Query( value = "SELECT * FROM adeudos a WHERE a.alumno_curp = :curp AND (a.status = 'ACTIVO' OR a.status = 'PAGO_PARCIAL')",
            nativeQuery = true)
    List<Adeudo> findByAlumnoCurpAndStatus(@Param("curp") String curp);
}
