package br.com.techsync.service;

import br.com.techsync.models.Orcamento;
import br.com.techsync.repository.OrcamentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrcamentoService {
    private final OrcamentoRepository repository;

    public OrcamentoService(OrcamentoRepository repository) {
        this.repository = repository;
    }

    public Orcamento salvar(Orcamento orcamento) {
        return repository.save(orcamento);
    }

    public List<Orcamento> listarTodos() {
        return repository.findAll();
    }

    public Optional<Orcamento> buscarPorId(int id) {
        return repository.findById(id);
    }

    public void deletar(int id) {
        repository.deleteById(id);
    }
}