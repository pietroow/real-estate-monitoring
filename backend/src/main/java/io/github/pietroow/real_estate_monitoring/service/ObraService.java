package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.model.Obra;
import io.github.pietroow.real_estate_monitoring.repository.ObraRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ObraService {

    private final ObraRepository obraRepository;

    public Obra salvar(Obra obra) {
        return obraRepository.save(obra);
    }

    public List<Obra> listarTodasObras() {
        return obraRepository.findAll();
    }

    public Obra buscarPorId(UUID id) {
        return obraRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deletarObra(UUID id) {
        obraRepository.deleteById(id);
    }

    public Obra atualizarObra(UUID id, Obra obraAtualizada) {

        Obra obraExistente = obraRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        obraExistente.setNomeObra(obraAtualizada.getNomeObra());
        obraExistente.setCodigoObra(obraAtualizada.getCodigoObra());
        obraExistente.setTipoObra(obraAtualizada.getTipoObra());
        obraExistente.setStatusObra(obraAtualizada.getStatusObra());
        obraExistente.setArt(obraAtualizada.getArt());
        obraExistente.setResponsávelTecnico(obraAtualizada.getResponsávelTecnico());
        obraExistente.setResponsavelDaObra(obraAtualizada.getResponsavelDaObra());
        obraExistente.setCeiCno(obraAtualizada.getCeiCno());
        obraExistente.setAreaTotal(obraAtualizada.getAreaTotal());
        obraExistente.setUnidade(obraAtualizada.getUnidade());
        obraExistente.setComentario(obraAtualizada.getComentario());
        obraExistente.setObraVisivelPara(obraAtualizada.getObraVisivelPara());
        obraExistente.setStatusParaLancamentos(obraAtualizada.isStatusParaLancamentos());
        obraExistente.setStatusParaFaturamentos(obraAtualizada.isStatusParaFaturamentos());
        obraExistente.setStatusParaCompras(obraAtualizada.isStatusParaCompras());

        return obraRepository.save(obraExistente);
    }
}
