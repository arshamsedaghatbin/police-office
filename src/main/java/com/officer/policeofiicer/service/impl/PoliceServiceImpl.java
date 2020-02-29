package com.officer.policeofiicer.service.impl;

import com.officer.policeofiicer.domain.Police;
import com.officer.policeofiicer.repository.PoliceRepository;
import com.officer.policeofiicer.service.PoliceService;
import com.officer.policeofiicer.service.mapper.PoliceMapper;
import com.police.officer.dto.PoliceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Police}.
 */
@Service
@Transactional
public class PoliceServiceImpl implements PoliceService {

    private final Logger log = LoggerFactory.getLogger(PoliceServiceImpl.class);

    private final PoliceRepository policeRepository;

    private final PoliceMapper policeMapper;

    public PoliceServiceImpl(PoliceRepository policeRepository, PoliceMapper policeMapper) {
        this.policeRepository = policeRepository;
        this.policeMapper = policeMapper;
    }

    /**
     * Save a police.
     *
     * @param policeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PoliceDTO save(PoliceDTO policeDTO) {
        log.debug("Request to save Police : {}", policeDTO);
        Police police = policeMapper.toEntity(policeDTO);
        police = policeRepository.save(police);
        return policeMapper.toDto(police);
    }

    /**
     * Get all the police.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PoliceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Police");
        return policeRepository.findAll(pageable)
            .map(policeMapper::toDto);
    }

    /**
     * Get one police by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PoliceDTO> findOne(Long id) {
        log.debug("Request to get Police : {}", id);
        return policeRepository.findById(id)
            .map(policeMapper::toDto);
    }

    /**
     * Delete the police by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Police : {}", id);
        policeRepository.deleteById(id);
    }
}
