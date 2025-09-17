package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.model.Obra;
import io.github.pietroow.real_estate_monitoring.repository.ObraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObraService {

    private final ObraRepository obraRepository;

    public Obra salvar(Obra obra) {
        return obraRepository.save(obra);
    }

    public List<Obra> listarTodasObras(){
        return obraRepository.findAll();
    }

}
