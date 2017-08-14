package mx.com.dxesoft.suneofinanzas.repository;

import mx.com.dxesoft.suneofinanzas.domain.Montos;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Montos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MontosRepository extends JpaRepository<Montos,Long> {
    
}
