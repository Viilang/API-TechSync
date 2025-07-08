package br.com.techsync.service.cliente;

import br.com.techsync.models.cliente.Responsavel;
import br.com.techsync.repository.cliente.ResponsavelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsavelService {
    private final ResponsavelRepository responsavelRepository;

    public ResponsavelService(final ResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }

    // Buscar
    public List<Responsavel> findAll() {
        return responsavelRepository.findAll();
    }

    // Buscar 1
    public Responsavel findById(Integer id) {

        if (id == null) {
            return null;
        }

        Optional<Responsavel> responsavel = responsavelRepository.findById(id);

        return responsavel.orElse(null);
    }

    // Criar

    public Responsavel save(Responsavel responsavel) {
        if (responsavel == null) {
            return null;
        }

        if (responsavel.getId() == null) {
            return null;
        }

        return responsavelRepository.save(responsavel);
    }

    // Atualizar
    public Responsavel update(Responsavel responsavel) {
        if (responsavel == null) {
            return null;
        }

        if (responsavel.getId() == null) {
            return null;
        }

        return responsavelRepository.save(responsavel);
    }

    // Deletar
    public boolean delete(Integer id) {
        if (id == null) {
            return false;
        }

        Optional<Responsavel> responsavel = responsavelRepository.findById(id);

        if (responsavel.isEmpty()) {
            return false;
        }

        responsavelRepository.deleteById(id);
        return true;
    }
}
