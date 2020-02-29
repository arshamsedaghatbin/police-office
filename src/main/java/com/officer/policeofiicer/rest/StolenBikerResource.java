package com.officer.policeofiicer.rest;

import com.officer.policeofiicer.domain.StolenBiker;
import com.officer.policeofiicer.rest.errors.BadRequestAlertException;
import com.officer.policeofiicer.rest.util.HeaderUtil;
import com.officer.policeofiicer.rest.util.PaginationUtil;
import com.officer.policeofiicer.rest.util.ResponseUtil;
import com.officer.policeofiicer.service.StolenBikerService;
import com.police.officer.dto.StolenBikerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.police.officer.client.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link StolenBiker}.
 */
@RestController
public class StolenBikerResource implements StolenBikerClient{

    private final Logger log = LoggerFactory.getLogger(StolenBikerResource.class);

    private static final String ENTITY_NAME = "stolenBiker";

    @Value("${arsham.clientApp.name}")
    private String applicationName;

    private final StolenBikerService stolenBikerService;

    public StolenBikerResource(StolenBikerService stolenBikerService) {
        this.stolenBikerService = stolenBikerService;
    }

    /**
     * {@code POST  /stolen-bikers} : Create a new stolenBiker.
     *
     * @param stolenBikerDTO the stolenBikerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stolenBikerDTO, or with status {@code 400 (Bad Request)} if the stolenBiker has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    public ResponseEntity<StolenBikerDTO> createStolenBiker(@RequestBody StolenBikerDTO stolenBikerDTO) throws URISyntaxException {
        log.debug("REST request to save StolenBiker : {}", stolenBikerDTO);
        if (stolenBikerDTO.getId() != null) {
            throw new BadRequestAlertException("A new stolenBiker cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StolenBikerDTO result = stolenBikerService.save(stolenBikerDTO);
        return ResponseEntity.created(new URI("/api/stolen-bikers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stolen-bikers} : Updates an existing stolenBiker.
     *
     * @param stolenBikerDTO the stolenBikerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stolenBikerDTO,
     * or with status {@code 400 (Bad Request)} if the stolenBikerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stolenBikerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    public ResponseEntity<StolenBikerDTO> updateStolenBiker(@RequestBody StolenBikerDTO stolenBikerDTO) throws URISyntaxException {
        log.debug("REST request to update StolenBiker : {}", stolenBikerDTO);
        if (stolenBikerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StolenBikerDTO result = stolenBikerService.save(stolenBikerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stolenBikerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /stolen-bikers} : get all the stolenBikers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stolenBikers in body.
     */
    public ResponseEntity<List<StolenBikerDTO>> getAllStolenBikers(Pageable pageable) {
        log.debug("REST request to get a page of StolenBikers");
        Page<StolenBikerDTO> page = stolenBikerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    public ResponseEntity<List<StolenBikerDTO>> searchStolenBiker(@RequestBody StolenBikerDTO stolenBikerDTO) {
        log.debug("REST request to get a page of StolenBikers");
        List<StolenBikerDTO> stolenBikerDTOS = stolenBikerService.searchByProperties(stolenBikerDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME,"1")).body(stolenBikerDTOS);
    }

    /**
     * {@code GET  /stolen-bikers/:id} : get the "id" stolenBiker.
     *
     * @param id the id of the stolenBikerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stolenBikerDTO, or with status {@code 404 (Not Found)}.
     */
    public ResponseEntity<StolenBikerDTO> getStolenBiker(@PathVariable Long id) {
        log.debug("REST request to get StolenBiker : {}", id);
        Optional<StolenBikerDTO> stolenBikerDTO = stolenBikerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stolenBikerDTO);
    }

    /**
     * {@code DELETE  /stolen-bikers/:id} : delete the "id" stolenBiker.
     *
     * @param id the id of the stolenBikerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    public ResponseEntity<Void> deleteStolenBiker(@PathVariable Long id) {
        log.debug("REST request to delete StolenBiker : {}", id);
        stolenBikerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
