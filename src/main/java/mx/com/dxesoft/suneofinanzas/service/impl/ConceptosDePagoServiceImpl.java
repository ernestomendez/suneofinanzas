package mx.com.dxesoft.suneofinanzas.service.impl;

import mx.com.dxesoft.suneofinanzas.service.ConceptosDePagoService;
import mx.com.dxesoft.suneofinanzas.domain.ConceptosDePago;
import mx.com.dxesoft.suneofinanzas.repository.ConceptosDePagoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing ConceptosDePago.
 */
@Service
@Transactional
public class ConceptosDePagoServiceImpl implements ConceptosDePagoService{

    private final Logger log = LoggerFactory.getLogger(ConceptosDePagoServiceImpl.class);

    private final ConceptosDePagoRepository conceptosDePagoRepository;

    public ConceptosDePagoServiceImpl(ConceptosDePagoRepository conceptosDePagoRepository) {
        this.conceptosDePagoRepository = conceptosDePagoRepository;
    }

    /**
     * Save a conceptosDePago.
     *
     * @param conceptosDePago the entity to save
     * @return the persisted entity
     */
    @Override
    public ConceptosDePago save(ConceptosDePago conceptosDePago) {
        log.debug("Request to save ConceptosDePago : {}", conceptosDePago);
        return conceptosDePagoRepository.save(conceptosDePago);
    }

    /**
     *  Get all the conceptosDePagos.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConceptosDePago> findAll(Pageable pageable) {
        log.debug("Request to get all ConceptosDePagos");
        return conceptosDePagoRepository.findAll(pageable);
    }

    /**
     *  Get one conceptosDePago by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ConceptosDePago findOne(Long id) {
        log.debug("Request to get ConceptosDePago : {}", id);
        return conceptosDePagoRepository.findOne(id);
    }

    /**
     *  Delete the  conceptosDePago by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConceptosDePago : {}", id);
        conceptosDePagoRepository.delete(id);
    }
}
