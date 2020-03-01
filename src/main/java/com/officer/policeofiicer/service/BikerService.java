package com.officer.policeofiicer.service;

import com.officer.policeofiicer.domain.Biker;
import com.police.officer.dto.BikerDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Biker}.
 */
public interface BikerService {

    /**
     * Save a biker.
     *
     * @param bikerDTO the entity to save.
     * @return the persisted entity.
     */
    BikerDTO save(BikerDTO bikerDTO);

    /**
     * Get all the bikers.
     *
     * @return the list of entities.
     */
    List<BikerDTO> findAll();

    /**
     * Get the "id" biker.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BikerDTO> findOne(Long id);

    /**
     * Delete the "id" biker.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
