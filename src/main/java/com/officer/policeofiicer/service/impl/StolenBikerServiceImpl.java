package com.officer.policeofiicer.service.impl;

import com.officer.policeofiicer.domain.StolenBiker;
import com.officer.policeofiicer.repository.StolenBikerRepository;
import com.officer.policeofiicer.service.StolenBikerService;
import com.officer.policeofiicer.service.dto.StolenBikerDTO;
import com.officer.policeofiicer.service.mapper.StolenBikerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link StolenBiker}.
 */
@Service
@Transactional
public class StolenBikerServiceImpl implements StolenBikerService {

    private final Logger log = LoggerFactory.getLogger(StolenBikerServiceImpl.class);

    private final StolenBikerRepository stolenBikerRepository;

    private final StolenBikerMapper stolenBikerMapper;

    public StolenBikerServiceImpl(StolenBikerRepository stolenBikerRepository, StolenBikerMapper stolenBikerMapper) {
        this.stolenBikerRepository = stolenBikerRepository;
        this.stolenBikerMapper = stolenBikerMapper;
    }

    /**
     * Save a stolenBiker.
     *
     * @param stolenBikerDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public StolenBikerDTO save(StolenBikerDTO stolenBikerDTO) {
        log.debug("Request to save StolenBiker : {}", stolenBikerDTO);
        StolenBiker stolenBiker = stolenBikerMapper.toEntity(stolenBikerDTO);
        stolenBiker = stolenBikerRepository.save(stolenBiker);
        return stolenBikerMapper.toDto(stolenBiker);
    }

    /**
     * Get all the stolenBikers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StolenBikerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StolenBikers");
        return stolenBikerRepository.findAll(pageable)
            .map(stolenBikerMapper::toDto);
    }

    /**
     * Get one stolenBiker by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StolenBikerDTO> findOne(Long id) {
        log.debug("Request to get StolenBiker : {}", id);
        return stolenBikerRepository.findById(id)
            .map(stolenBikerMapper::toDto);
    }

    /**
     * Delete the stolenBiker by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StolenBiker : {}", id);
        stolenBikerRepository.deleteById(id);
    }
}
