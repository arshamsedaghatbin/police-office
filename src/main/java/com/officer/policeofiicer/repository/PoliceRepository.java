package com.officer.policeofiicer.repository;

import com.officer.policeofiicer.domain.Police;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Police entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PoliceRepository extends JpaRepository<Police, Long> {

}
