package com.officer.policeofiicer.repository;

import com.officer.policeofiicer.domain.Biker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Biker entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BikerRepository extends JpaRepository<Biker, Long> {

}
