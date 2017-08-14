package mx.com.dxesoft.suneofinanzas.repository;

import mx.com.dxesoft.suneofinanzas.domain.ConceptosDePago;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ConceptosDePago entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConceptosDePagoRepository extends JpaRepository<ConceptosDePago,Long> {
    
}
