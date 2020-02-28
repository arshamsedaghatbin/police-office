package com.officer.policeofiicer.rest;

import com.officer.policeofiicer.domain.PoliceOfficer;
import com.officer.policeofiicer.rest.errors.BadRequestAlertException;
import com.officer.policeofiicer.rest.util.HeaderUtil;
import com.officer.policeofiicer.rest.util.PaginationUtil;
import com.officer.policeofiicer.rest.util.ResponseUtil;
import com.officer.policeofiicer.service.PoliceOfficerService;
import com.officer.policeofiicer.service.dto.PoliceOfficerDTO;
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
 * REST controller for managing {@link PoliceOfficer}.
 */
@RestController
@RequestMapping("/api")
public class PoliceOfficerResource {

    private final Logger log = LoggerFactory.getLogger(PoliceOfficerResource.class);

    private static final String ENTITY_NAME = "policeOfficer";

    @Value("${arsham.clientApp.name}")
    private String applicationName;

    private final PoliceOfficerService policeOfficerService;

    public PoliceOfficerResource(PoliceOfficerService policeOfficerService) {
        this.policeOfficerService = policeOfficerService;
    }

    /**
     * {@code POST  /police-officers} : Create a new policeOfficer.
     *
     * @param policeOfficerDTO the policeOfficerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new policeOfficerDTO, or with status {@code 400 (Bad Request)} if the policeOfficer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/police-officers")
    public ResponseEntity<PoliceOfficerDTO> createPoliceOfficer(@RequestBody PoliceOfficerDTO policeOfficerDTO) throws URISyntaxException {
        log.debug("REST request to save PoliceOfficer : {}", policeOfficerDTO);
        if (policeOfficerDTO.getId() != null) {
            throw new BadRequestAlertException("A new policeOfficer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PoliceOfficerDTO result = policeOfficerService.save(policeOfficerDTO);
        return ResponseEntity.created(new URI("/api/police-officers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /police-officers} : Updates an existing policeOfficer.
     *
     * @param policeOfficerDTO the policeOfficerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated policeOfficerDTO,
     * or with status {@code 400 (Bad Request)} if the policeOfficerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the policeOfficerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/police-officers")
    public ResponseEntity<PoliceOfficerDTO> updatePoliceOfficer(@RequestBody PoliceOfficerDTO policeOfficerDTO) throws URISyntaxException {
        log.debug("REST request to update PoliceOfficer : {}", policeOfficerDTO);
        if (policeOfficerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PoliceOfficerDTO result = policeOfficerService.save(policeOfficerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, policeOfficerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /police-officers} : get all the policeOfficers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of policeOfficers in body.
     */
    @GetMapping("/police-officers")
    public ResponseEntity<List<PoliceOfficerDTO>> getAllPoliceOfficers(Pageable pageable) {
        log.debug("REST request to get a page of PoliceOfficers");
        Page<PoliceOfficerDTO> page = policeOfficerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /police-officers/:id} : get the "id" policeOfficer.
     *
     * @param id the id of the policeOfficerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the policeOfficerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/police-officers/{id}")
    public ResponseEntity<PoliceOfficerDTO> getPoliceOfficer(@PathVariable Long id) {
        log.debug("REST request to get PoliceOfficer : {}", id);
        Optional<PoliceOfficerDTO> policeOfficerDTO = policeOfficerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(policeOfficerDTO);
    }

    /**
     * {@code DELETE  /police-officers/:id} : delete the "id" policeOfficer.
     *
     * @param id the id of the policeOfficerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/police-officers/{id}")
    public ResponseEntity<Void> deletePoliceOfficer(@PathVariable Long id) {
        log.debug("REST request to delete PoliceOfficer : {}", id);
        policeOfficerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
