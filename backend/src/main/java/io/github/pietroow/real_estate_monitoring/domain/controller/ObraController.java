package io.github.pietroow.real_estate_monitoring.domain.controller;

import io.github.pietroow.real_estate_monitoring.domain.repository.ObraRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/obras")
public class ObraController {

    private ObraRepository obraRepository;

    public ObraController(ObraRepository obraRepository) {
        this.obraRepository = obraRepository;
    }

}
