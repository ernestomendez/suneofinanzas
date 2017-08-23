package mx.com.dxesoft.suneofinanzas.web.rest;

import mx.com.dxesoft.suneofinanzas.datatypes.Alumno;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * suneofinanzas, mx.com.dxesoft.suneofinanzas.web.rest . AlumnosResource
 * Created by ernesto on 22/08/17.
 */
@RestController
@RequestMapping("/api")
public class AlumnosResource {

    private final Logger log = LoggerFactory.getLogger(AlumnosResource.class);

    @GetMapping("/alumno/{curp}")
    public ResponseEntity<Alumno> getAlumnoByCurp(@PathVariable String curp) {
        log.debug("Request to get an Alumno by curp");

        Assert.hasText("can not be empty string or null", curp);

        final String uri = "http://localhost:8090/utm/students/curp/{curp}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("curp", curp);

        RestTemplate restTemplate = new RestTemplate();
        Alumno alumno = restTemplate.getForObject(uri, Alumno.class, params);

        log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.debug(alumno.toString());
        log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        return ResponseEntity.status(HttpStatus.OK).body(alumno);
    }
}
