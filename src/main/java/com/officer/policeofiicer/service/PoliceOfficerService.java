package com.officer.policeofiicer.service;

import com.officer.policeofiicer.domain.PoliceOfficer;
import com.officer.policeofiicer.service.dto.PoliceOfficerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link PoliceOfficer}.
 */
public interface PoliceOfficerService {

    /**
     * Save a policeOfficer.
     *
     * @param policeOfficerDTO the entity to save.
     * @return the persisted entity.
     */
    PoliceOfficerDTO save(PoliceOfficerDTO policeOfficerDTO);

    /**
     * Get all the policeOfficers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PoliceOfficerDTO> findAll(Pageable pageable);

    /**
     * Get the "id" policeOfficer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PoliceOfficerDTO> findOne(Long id);

    /**
     * Delete the "id" policeOfficer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
