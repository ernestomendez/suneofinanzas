package mx.com.dxesoft.suneofinanzas.web.rest;

import com.codahale.metrics.annotation.Timed;
import mx.com.dxesoft.suneofinanzas.domain.ConceptosDePago;
import mx.com.dxesoft.suneofinanzas.service.ConceptosDePagoService;
import mx.com.dxesoft.suneofinanzas.web.rest.util.HeaderUtil;
import mx.com.dxesoft.suneofinanzas.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ConceptosDePago.
 */
@RestController
@RequestMapping("/api")
public class ConceptosDePagoResource {

    private final Logger log = LoggerFactory.getLogger(ConceptosDePagoResource.class);

    private static final String ENTITY_NAME = "conceptosDePago";

    private final ConceptosDePagoService conceptosDePagoService;

    public ConceptosDePagoResource(ConceptosDePagoService conceptosDePagoService) {
        this.conceptosDePagoService = conceptosDePagoService;
    }

    /**
     * POST  /conceptos-de-pagos : Create a new conceptosDePago.
     *
     * @param conceptosDePago the conceptosDePago to create
     * @return the ResponseEntity with status 201 (Created) and with body the new conceptosDePago, or with status 400 (Bad Request) if the conceptosDePago has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/conceptos-de-pagos")
    @Timed
    public ResponseEntity<ConceptosDePago> createConceptosDePago(@Valid @RequestBody ConceptosDePago conceptosDePago) throws URISyntaxException {
        log.debug("REST request to save ConceptosDePago : {}", conceptosDePago);
        if (conceptosDePago.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new conceptosDePago cannot already have an ID")).body(null);
        }
        ConceptosDePago result = conceptosDePagoService.save(conceptosDePago);
        return ResponseEntity.created(new URI("/api/conceptos-de-pagos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /conceptos-de-pagos : Updates an existing conceptosDePago.
     *
     * @param conceptosDePago the conceptosDePago to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated conceptosDePago,
     * or with status 400 (Bad Request) if the conceptosDePago is not valid,
     * or with status 500 (Internal Server Error) if the conceptosDePago couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/conceptos-de-pagos")
    @Timed
    public ResponseEntity<ConceptosDePago> updateConceptosDePago(@Valid @RequestBody ConceptosDePago conceptosDePago) throws URISyntaxException {
        log.debug("REST request to update ConceptosDePago : {}", conceptosDePago);
        if (conceptosDePago.getId() == null) {
            return createConceptosDePago(conceptosDePago);
        }
        ConceptosDePago result = conceptosDePagoService.save(conceptosDePago);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, conceptosDePago.getId().toString()))
            .body(result);
    }

    /**
     * GET  /conceptos-de-pagos : get all the conceptosDePagos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of conceptosDePagos in body
     */
    @GetMapping("/conceptos-de-pagos")
    @Timed
    public ResponseEntity<List<ConceptosDePago>> getAllConceptosDePagos(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of ConceptosDePagos");
        Page<ConceptosDePago> page = conceptosDePagoService.findAll(pageable);

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        page.getContent().forEach(System.out::println);
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/conceptos-de-pagos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /conceptos-de-pagos/:id : get the "id" conceptosDePago.
     *
     * @param id the id of the conceptosDePago to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the conceptosDePago, or with status 404 (Not Found)
     */
    @GetMapping("/conceptos-de-pagos/{id}")
    @Timed
    public ResponseEntity<ConceptosDePago> getConceptosDePago(@PathVariable Long id) {
        log.debug("REST request to get ConceptosDePago : {}", id);
        ConceptosDePago conceptosDePago = conceptosDePagoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(conceptosDePago));
    }

    /**
     * DELETE  /conceptos-de-pagos/:id : delete the "id" conceptosDePago.
     *
     * @param id the id of the conceptosDePago to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/conceptos-de-pagos/{id}")
    @Timed
    public ResponseEntity<Void> deleteConceptosDePago(@PathVariable Long id) {
        log.debug("REST request to delete ConceptosDePago : {}", id);
        conceptosDePagoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
