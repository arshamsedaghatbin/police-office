package com.officer.policeofiicer.rest;

import com.officer.policeofiicer.domain.Biker;
import com.officer.policeofiicer.rest.errors.BadRequestAlertException;
import com.officer.policeofiicer.rest.util.HeaderUtil;
import com.officer.policeofiicer.rest.util.ResponseUtil;
import com.officer.policeofiicer.service.BikerService;
import com.police.officer.client.BikerClient;
import com.police.officer.dto.BikerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Biker}.
 */
@RestController
public class BikerResource implements BikerClient {

    private final Logger log = LoggerFactory.getLogger(BikerResource.class);

    private static final String ENTITY_NAME = "biker";

    @Value("${arsham.clientApp.name}")
    private String applicationName;

    private final BikerService bikerService;

    public BikerResource(BikerService bikerService) {
        this.bikerService = bikerService;
    }

    /**
     * {@code POST  /bikers} : Create a new biker.
     *
     * @param bikerDTO the bikerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bikerDTO, or with status {@code 400 (Bad Request)} if the biker has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    public ResponseEntity<BikerDTO> createBiker(@RequestBody BikerDTO bikerDTO) throws URISyntaxException {
        log.debug("REST request to save Biker : {}", bikerDTO);
        if (bikerDTO.getId() != null) {
            throw new BadRequestAlertException("A new biker cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BikerDTO result = bikerService.save(bikerDTO);
        return ResponseEntity.created(new URI("/api/bikers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bikers} : Updates an existing biker.
     *
     * @param bikerDTO the bikerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bikerDTO,
     * or with status {@code 400 (Bad Request)} if the bikerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bikerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    public ResponseEntity<BikerDTO> updateBiker(@RequestBody BikerDTO bikerDTO) throws URISyntaxException {
        log.debug("REST request to update Biker : {}", bikerDTO);
        if (bikerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BikerDTO result = bikerService.save(bikerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bikerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bikers} : get all the bikers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bikers in body.
     */
    public List<BikerDTO> getAllBikers() {
        log.debug("REST request to get all Bikers");
        return bikerService.findAll();
    }

    /**
     * {@code GET  /bikers/:id} : get the "id" biker.
     *
     * @param id the id of the bikerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bikerDTO, or with status {@code 404 (Not Found)}.
     */
    public ResponseEntity<BikerDTO> getBiker(@PathVariable Long id) {
        log.debug("REST request to get Biker : {}", id);
        Optional<BikerDTO> bikerDTO = bikerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bikerDTO);
    }

    /**
     * {@code DELETE  /bikers/:id} : delete the "id" biker.
     *
     * @param id the id of the bikerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    public ResponseEntity<Void> deleteBiker(@PathVariable Long id) {
        log.debug("REST request to delete Biker : {}", id);
        bikerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
