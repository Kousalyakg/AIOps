package com.psquare.obs.repository;

import com.psquare.obs.models.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationRepo extends JpaRepository<Vaccination, Long> {
}
