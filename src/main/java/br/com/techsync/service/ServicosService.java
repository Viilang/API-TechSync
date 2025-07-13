package br.com.techsync.service;

import br.com.techsync.models.Servicos;
import br.com.techsync.repository.ServicosRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicosService {
    private final ServicosRepository repository;

    public ServicosService(ServicosRepository repository) {
        this.repository = repository;
    }

    public Servicos salvar(Servicos servico) {
        return repository.save(servico);
    }

    public List<Servicos> listarTodos() {
        return repository.findAll();
    }

    public Optional<Servicos> buscarPorId(int id) {
        return repository.findById(id);
    }

    public void deletar(int id) {
        repository.deleteById(id);
    }
}