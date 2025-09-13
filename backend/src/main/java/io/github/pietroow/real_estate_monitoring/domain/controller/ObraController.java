package io.github.pietroow.real_estate_monitoring.domain.controller;

import io.github.pietroow.real_estate_monitoring.domain.repository.ObraRepository;
import io.github.pietroow.real_estate_monitoring.domain.service.ObraService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/obras")
@RequiredArgsConstructor
public class ObraController {

    private ObraService obraService;

}