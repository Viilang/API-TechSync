package br.com.techsync.service.cliente;

import br.com.techsync.models.cliente.Cliente;
import br.com.techsync.models.cliente.Responsavel;
import br.com.techsync.repository.cliente.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Buscar
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    // Buscar 1
    public Cliente findById(Integer id) {

        if (id == null) {
            return null;
        }

        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.orElse(null);
    }

    // Criar

    public Cliente save(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        if (cliente.getId() == null) {
            return null;
        }

        for (Responsavel r : cliente.getResponsaveis()) {
            r.setCliente(cliente);
        }

        Cliente clienteSalvo;

        try {
            clienteSalvo = clienteRepository.save(cliente);
        } catch (Exception e) {
            throw e;
        }

        return clienteSalvo;
    }

    // Atualizar
    public Cliente update(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        if (cliente.getId() == null) {
            return null;
        }

        for (Responsavel r : cliente.getResponsaveis()) {
            r.setCliente(cliente);
        }

        return clienteRepository.save(cliente);
    }

    // Deletar
    public boolean delete(Integer id) {
        if (id == null) {
            return false;
        }

        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            return false;
        }

        clienteRepository.deleteById(id);

        return true;
    }
}
