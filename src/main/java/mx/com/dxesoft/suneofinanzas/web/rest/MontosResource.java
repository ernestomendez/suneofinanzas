package mx.com.dxesoft.suneofinanzas.web.rest;

import com.codahale.metrics.annotation.Timed;
import mx.com.dxesoft.suneofinanzas.domain.Montos;
import mx.com.dxesoft.suneofinanzas.service.MontosService;
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
 * REST controller for managing Montos.
 */
@RestController
@RequestMapping("/api")
public class MontosResource {

    private final Logger log = LoggerFactory.getLogger(MontosResource.class);

    private static final String ENTITY_NAME = "montos";

    private final MontosService montosService;

    public MontosResource(MontosService montosService) {
        this.montosService = montosService;
    }

    /**
     * POST  /montos : Create a new montos.
     *
     * @param montos the montos to create
     * @return the ResponseEntity with status 201 (Created) and with body the new montos, or with status 400 (Bad Request) if the montos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/montos")
    @Timed
    public ResponseEntity<Montos> createMontos(@Valid @RequestBody Montos montos) throws URISyntaxException {
        log.debug("REST request to save Montos : {}", montos);
        if (montos.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new montos cannot already have an ID")).body(null);
        }
        Montos result = montosService.save(montos);
        return ResponseEntity.created(new URI("/api/montos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /montos : Updates an existing montos.
     *
     * @param montos the montos to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated montos,
     * or with status 400 (Bad Request) if the montos is not valid,
     * or with status 500 (Internal Server Error) if the montos couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/montos")
    @Timed
    public ResponseEntity<Montos> updateMontos(@Valid @RequestBody Montos montos) throws URISyntaxException {
        log.debug("REST request to update Montos : {}", montos);
        if (montos.getId() == null) {
            return createMontos(montos);
        }
        Montos result = montosService.save(montos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, montos.getId().toString()))
            .body(result);
    }

    /**
     * GET  /montos : get all the montos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of montos in body
     */
    @GetMapping("/montos")
    @Timed
    public ResponseEntity<List<Montos>> getAllMontos(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Montos");
        Page<Montos> page = montosService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/montos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /montos/:id : get the "id" montos.
     *
     * @param id the id of the montos to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the montos, or with status 404 (Not Found)
     */
    @GetMapping("/montos/{id}")
    @Timed
    public ResponseEntity<Montos> getMontos(@PathVariable Long id) {
        log.debug("REST request to get Montos : {}", id);
        Montos montos = montosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(montos));
    }

    /**
     * DELETE  /montos/:id : delete the "id" montos.
     *
     * @param id the id of the montos to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/montos/{id}")
    @Timed
    public ResponseEntity<Void> deleteMontos(@PathVariable Long id) {
        log.debug("REST request to delete Montos : {}", id);
        montosService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
