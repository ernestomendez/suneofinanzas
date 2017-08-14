package mx.com.dxesoft.suneofinanzas.service.impl;

import mx.com.dxesoft.suneofinanzas.service.MontosService;
import mx.com.dxesoft.suneofinanzas.domain.Montos;
import mx.com.dxesoft.suneofinanzas.repository.MontosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Montos.
 */
@Service
@Transactional
public class MontosServiceImpl implements MontosService{

    private final Logger log = LoggerFactory.getLogger(MontosServiceImpl.class);

    private final MontosRepository montosRepository;

    public MontosServiceImpl(MontosRepository montosRepository) {
        this.montosRepository = montosRepository;
    }

    /**
     * Save a montos.
     *
     * @param montos the entity to save
     * @return the persisted entity
     */
    @Override
    public Montos save(Montos montos) {
        log.debug("Request to save Montos : {}", montos);
        return montosRepository.save(montos);
    }

    /**
     *  Get all the montos.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Montos> findAll(Pageable pageable) {
        log.debug("Request to get all Montos");
        return montosRepository.findAll(pageable);
    }

    /**
     *  Get one montos by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Montos findOne(Long id) {
        log.debug("Request to get Montos : {}", id);
        return montosRepository.findOne(id);
    }

    /**
     *  Delete the  montos by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Montos : {}", id);
        montosRepository.delete(id);
    }
}
