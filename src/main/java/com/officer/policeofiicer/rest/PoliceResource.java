package com.officer.policeofiicer.rest;

import com.officer.policeofiicer.domain.Police;
import com.officer.policeofiicer.rest.errors.BadRequestAlertException;
import com.officer.policeofiicer.rest.util.HeaderUtil;
import com.officer.policeofiicer.rest.util.PaginationUtil;
import com.officer.policeofiicer.rest.util.ResponseUtil;
import com.officer.policeofiicer.service.PoliceService;
import com.officer.policeofiicer.service.dto.PoliceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Police}.
 */
@RestController
@RequestMapping("/api")
public class PoliceResource {

    private final Logger log = LoggerFactory.getLogger(PoliceResource.class);

    private static final String ENTITY_NAME = "police";

    @Value("${arsham.clientApp.name}")
    private String applicationName;

    private final PoliceService policeService;

    public PoliceResource(PoliceService policeService) {
        this.policeService = policeService;
    }

    /**
     * {@code POST  /police} : Create a new police.
     *
     * @param policeDTO the policeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new policeDTO, or with status {@code 400 (Bad Request)} if the police has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/police")
    public ResponseEntity<PoliceDTO> createPolice(@RequestBody PoliceDTO policeDTO) throws URISyntaxException {
        log.debug("REST request to save Police : {}", policeDTO);
        if (policeDTO.getId() != null) {
            throw new BadRequestAlertException("A new police cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PoliceDTO result = policeService.save(policeDTO);
        return ResponseEntity.created(new URI("/api/police/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /police} : Updates an existing police.
     *
     * @param policeDTO the policeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated policeDTO,
     * or with status {@code 400 (Bad Request)} if the policeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the policeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/police")
    public ResponseEntity<PoliceDTO> updatePolice(@RequestBody PoliceDTO policeDTO) throws URISyntaxException {
        log.debug("REST request to update Police : {}", policeDTO);
        if (policeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PoliceDTO result = policeService.save(policeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, policeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /police} : get all the police.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of police in body.
     */
    @GetMapping("/police")
    public ResponseEntity<List<PoliceDTO>> getAllPolice(Pageable pageable) {
        log.debug("REST request to get a page of Police");
        Page<PoliceDTO> page = policeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /police/:id} : get the "id" police.
     *
     * @param id the id of the policeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the policeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/police/{id}")
    public ResponseEntity<PoliceDTO> getPolice(@PathVariable Long id) {
        log.debug("REST request to get Police : {}", id);
        Optional<PoliceDTO> policeDTO = policeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(policeDTO);
    }

    /**
     * {@code DELETE  /police/:id} : delete the "id" police.
     *
     * @param id the id of the policeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/police/{id}")
    public ResponseEntity<Void> deletePolice(@PathVariable Long id) {
        log.debug("REST request to delete Police : {}", id);
        policeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
