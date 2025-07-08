package br.com.techsync.service.cliente;

import br.com.techsync.models.cliente.Endereco;
import br.com.techsync.repository.cliente.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(final EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    // Buscar
    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    // Buscar 1
    public Endereco findById(Integer id) {

        if (id == null) {
            return null;
        }

        Optional<Endereco> endereco = enderecoRepository.findById(id);

        return endereco.orElse(null);
    }

    // Criar

    public Endereco save(Endereco endereco) {
        if (endereco == null) {
            return null;
        }

        if (endereco.getId() == null) {
            return null;
        }

        return enderecoRepository.save(endereco);
    }

    // Atualizar
    public Endereco update(Endereco endereco) {
        if (endereco == null) {
            return null;
        }

        if (endereco.getId() == null) {
            return null;
        }

        return enderecoRepository.save(endereco);
    }

    // Deletar
    public boolean delete(Integer id) {
        if (id == null) {
            return false;
        }

        Optional<Endereco> endereco = enderecoRepository.findById(id);

        if (endereco.isEmpty()) {
            return false;
        }

        enderecoRepository.deleteById(id);
        return true;
    }
}
