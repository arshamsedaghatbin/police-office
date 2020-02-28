package com.officer.policeofiicer.repository;

import com.officer.policeofiicer.domain.PoliceOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PoliceOfficer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PoliceOfficerRepository extends JpaRepository<PoliceOfficer, Long> {

}
