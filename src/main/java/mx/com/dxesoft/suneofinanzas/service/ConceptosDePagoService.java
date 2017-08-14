package mx.com.dxesoft.suneofinanzas.service;

import mx.com.dxesoft.suneofinanzas.domain.ConceptosDePago;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing ConceptosDePago.
 */
public interface ConceptosDePagoService {

    /**
     * Save a conceptosDePago.
     *
     * @param conceptosDePago the entity to save
     * @return the persisted entity
     */
    ConceptosDePago save(ConceptosDePago conceptosDePago);

    /**
     *  Get all the conceptosDePagos.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ConceptosDePago> findAll(Pageable pageable);

    /**
     *  Get the "id" conceptosDePago.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ConceptosDePago findOne(Long id);

    /**
     *  Delete the "id" conceptosDePago.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
