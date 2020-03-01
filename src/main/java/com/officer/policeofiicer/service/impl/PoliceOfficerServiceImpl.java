package com.officer.policeofiicer.service.impl;

import com.officer.policeofiicer.domain.PoliceOfficer;
import com.officer.policeofiicer.domain.StolenBiker;
import com.officer.policeofiicer.domain.StolenStatus;
import com.officer.policeofiicer.domain.enumeration.PoliceOfficerStatus;
import com.officer.policeofiicer.repository.PoliceOfficerRepository;
import com.officer.policeofiicer.repository.StolenBikerRepository;
import com.officer.policeofiicer.service.PoliceOfficerCashService;
import com.officer.policeofiicer.service.PoliceOfficerService;
import com.officer.policeofiicer.service.StolenBikerCashService;
import com.officer.policeofiicer.service.mapper.PoliceOfficerMapper;
import com.police.officer.dto.PoliceOfficerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PoliceOfficer}.
 */
@Service
@Transactional
public class PoliceOfficerServiceImpl implements PoliceOfficerService {

    private final Logger log = LoggerFactory.getLogger(PoliceOfficerServiceImpl.class);

    private final PoliceOfficerRepository policeOfficerRepository;

    private final PoliceOfficerMapper policeOfficerMapper;

    @Autowired
    private PoliceOfficerCashService policeOfficerCashService;

    @Autowired
    private StolenBikerRepository stolenBikerRepository;

    @Autowired
    private StolenBikerCashService stolenBikerCashService;

    public PoliceOfficerServiceImpl(PoliceOfficerRepository policeOfficerRepository, PoliceOfficerMapper policeOfficerMapper) {
        this.policeOfficerRepository = policeOfficerRepository;
        this.policeOfficerMapper = policeOfficerMapper;
    }

    /**
     * Save a policeOfficer.
     *
     * @param policeOfficerDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    @Transactional
    public PoliceOfficerDTO save(PoliceOfficerDTO policeOfficerDTO) {
        log.debug("Request to save PoliceOfficer : {}", policeOfficerDTO);
        PoliceOfficer policeOfficer = policeOfficerMapper.toEntity(policeOfficerDTO);
        StolenBiker freeStolenBiker = stolenBikerCashService.getFreeStolenBiker();
        if (freeStolenBiker!=null){
            freeStolenBiker.setStolenStatus(StolenStatus.IN_PROGRESS);
            stolenBikerRepository.save(freeStolenBiker);
            policeOfficer.setPoliceOfficer(freeStolenBiker);
            policeOfficer.setStatus(PoliceOfficerStatus.ENGAGE);
            policeOfficer = policeOfficerRepository.save(policeOfficer);
        }
        else{
            policeOfficer.setStatus(PoliceOfficerStatus.RELEASE);
            policeOfficer = policeOfficerRepository.save(policeOfficer);
            policeOfficerCashService.addFreePolice(policeOfficer);
        }
        return policeOfficerMapper.toDto(policeOfficer);
    }

    /**
     * Get all the policeOfficers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PoliceOfficerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PoliceOfficers");
        return policeOfficerRepository.findAll(pageable)
            .map(policeOfficerMapper::toDto);
    }

    /**
     * Get one policeOfficer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PoliceOfficerDTO> findOne(Long id) {
        log.debug("Request to get PoliceOfficer : {}", id);
        return policeOfficerRepository.findById(id)
            .map(policeOfficerMapper::toDto);
    }

    /**
     * Delete the policeOfficer by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PoliceOfficer : {}", id);
        policeOfficerRepository.deleteById(id);
    }
}
