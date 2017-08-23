package mx.com.dxesoft.suneofinanzas.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.ApiParam;
import mx.com.dxesoft.suneofinanzas.datatypes.Alumno;
import mx.com.dxesoft.suneofinanzas.domain.Adeudo;
import mx.com.dxesoft.suneofinanzas.service.AdeudosService;
import mx.com.dxesoft.suneofinanzas.service.dto.AdeudoDto;
import mx.com.dxesoft.suneofinanzas.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * suneofinanzas, mx.com.dxesoft.suneofinanzas.web.rest . AdeudosResource
 * Created by ernesto on 19/08/17.
 */
@RestController
@RequestMapping("/api")
public class AdeudosResource {

    private final Logger log = LoggerFactory.getLogger(AdeudosResource.class);

    private static final String ENTITY_NAME = "Adeudo";

    private final AdeudosService adeudosService;

    public AdeudosResource(AdeudosService adeudosService) {
        this.adeudosService = adeudosService;
    }

    @GetMapping("/adeudos")
    @Timed
    public ResponseEntity<List<Adeudo>> getAllAdeudos(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Adeudo");
        Page<Adeudo> page = adeudosService.findAll(pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/adeudos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * Gets all adeudos from the given Alumno information.
     *
     * @param alumnoCurp alumnos curp.
     * @param alumnoName alumnos name.
     * @param alumnoMatricula alumnos matricula.
     * @return adeudos information.
     */
    @GetMapping("/adeudos/alumno")
    @Timed
    public ResponseEntity<AdeudoDto> getAdeudosAlumno(@RequestParam String alumnoCurp,
                                                      @RequestParam String alumnoName,
                                                      @RequestParam String alumnoMatricula) {
        log.debug("REST request to get all active adeudos from alumno");

        Alumno alumno = new Alumno(alumnoCurp, alumnoName, alumnoMatricula);

        AdeudoDto adeudoDto = adeudosService.findAdeudosByAlumno(alumno);

        return ResponseEntity.status(HttpStatus.OK).body(adeudoDto);
    }

}
