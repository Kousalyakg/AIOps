package com.psquare.obs.service;

import com.psquare.obs.models.Vaccination;
import com.psquare.obs.repository.VaccinationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationService implements IVaccinationService{

    @Autowired
    private VaccinationRepo vaccinationRepo;

    @Override
    public Vaccination saveVaccinationDtl(Vaccination vaccination) {
          Vaccination vac =  vaccinationRepo.save(vaccination);
        return vac;
    }
}
