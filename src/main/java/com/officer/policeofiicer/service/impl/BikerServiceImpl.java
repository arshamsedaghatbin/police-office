package com.officer.policeofiicer.service.impl;

import com.officer.policeofiicer.domain.Biker;
import com.officer.policeofiicer.repository.BikerRepository;
import com.officer.policeofiicer.service.BikerService;
import com.officer.policeofiicer.service.dto.BikerDTO;
import com.officer.policeofiicer.service.mapper.BikerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Biker}.
 */
@Service
@Transactional
public class BikerServiceImpl implements BikerService {

    private final Logger log = LoggerFactory.getLogger(BikerServiceImpl.class);
    @Autowired
    private  BikerRepository bikerRepository;

    @Autowired
    private  BikerMapper bikerMapper;

//    public BikerServiceImpl(BikerRepository bikerRepository, BikerMapper bikerMapper) {
//        this.bikerRepository = bikerRepository;
//        this.bikerMapper = bikerMapper;
//    }

    /**
     * Save a biker.
     *
     * @param bikerDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BikerDTO save(BikerDTO bikerDTO) {
        log.debug("Request to save Biker : {}", bikerDTO);
        Biker biker = bikerMapper.toEntity(bikerDTO);
        biker = bikerRepository.save(biker);
        return bikerMapper.toDto(biker);
    }

    /**
     * Get all the bikers.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<BikerDTO> findAll() {
        log.debug("Request to get all Bikers");
        return bikerRepository.findAll().stream()
            .map(bikerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one biker by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BikerDTO> findOne(Long id) {
        log.debug("Request to get Biker : {}", id);
        return bikerRepository.findById(id)
            .map(bikerMapper::toDto);
    }

    /**
     * Delete the biker by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Biker : {}", id);
        bikerRepository.deleteById(id);
    }
}
