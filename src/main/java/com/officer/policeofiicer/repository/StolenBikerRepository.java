package com.officer.policeofiicer.repository;

import com.officer.policeofiicer.domain.StolenBiker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StolenBiker entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StolenBikerRepository extends JpaRepository<StolenBiker, Long> {

}
