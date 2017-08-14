package mx.com.dxesoft.suneofinanzas.service;

import mx.com.dxesoft.suneofinanzas.domain.Montos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Montos.
 */
public interface MontosService {

    /**
     * Save a montos.
     *
     * @param montos the entity to save
     * @return the persisted entity
     */
    Montos save(Montos montos);

    /**
     *  Get all the montos.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Montos> findAll(Pageable pageable);

    /**
     *  Get the "id" montos.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Montos findOne(Long id);

    /**
     *  Delete the "id" montos.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
