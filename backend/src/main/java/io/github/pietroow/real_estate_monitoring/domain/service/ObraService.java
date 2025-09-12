package io.github.pietroow.real_estate_monitoring.domain.service;

import io.github.pietroow.real_estate_monitoring.domain.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObraService {

    private ObraRepository obraRepository;

    public ObraService(ObraRepository obraRepository) {
        this.obraRepository = obraRepository;
    }


}
