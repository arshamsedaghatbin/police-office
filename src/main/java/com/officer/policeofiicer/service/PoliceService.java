package com.officer.policeofiicer.service;

import com.officer.policeofiicer.domain.Police;
import com.police.officer.dto.PoliceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Police}.
 */
public interface PoliceService {

    /**
     * Save a police.
     *
     * @param policeDTO the entity to save.
     * @return the persisted entity.
     */
    PoliceDTO save(PoliceDTO policeDTO);

    /**
     * Get all the police.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PoliceDTO> findAll(Pageable pageable);

    /**
     * Get the "id" police.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PoliceDTO> findOne(Long id);

    /**
     * Delete the "id" police.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
