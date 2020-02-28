package com.officer.policeofiicer.service;

import com.officer.policeofiicer.domain.StolenBiker;
import com.officer.policeofiicer.service.dto.StolenBikerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link StolenBiker}.
 */
public interface StolenBikerService {

    /**
     * Save a stolenBiker.
     *
     * @param stolenBikerDTO the entity to save.
     * @return the persisted entity.
     */
    StolenBikerDTO save(StolenBikerDTO stolenBikerDTO);

    /**
     * Get all the stolenBikers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StolenBikerDTO> findAll(Pageable pageable);

    /**
     * Get the "id" stolenBiker.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StolenBikerDTO> findOne(Long id);

    /**
     * Delete the "id" stolenBiker.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

     List<StolenBikerDTO> searchByProperties(StolenBikerDTO stolenBiker);
}
