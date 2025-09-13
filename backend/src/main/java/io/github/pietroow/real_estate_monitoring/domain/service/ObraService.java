package io.github.pietroow.real_estate_monitoring.domain.service;

import io.github.pietroow.real_estate_monitoring.domain.repository.ObraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObraService {

    private ObraRepository obraRepository;

}