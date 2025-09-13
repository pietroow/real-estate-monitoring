package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.repository.ObraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObraService {

    private ObraRepository obraRepository;

}