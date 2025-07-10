package br.com.techsync.service.cliente;

import br.com.techsync.models.cliente.Cliente;
import br.com.techsync.models.cliente.Responsavel;
import br.com.techsync.repository.cliente.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Serviço de gerenciamento de clientes.
 */
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    /**
     * Cria uma instância do serviço de clientes com a dependência do repositório de clientes.
     *
     * @param clienteRepository Repositório de clientes.
     */
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Busca todos os clientes existentes no sistema.
     *
     * @return Lista de clientes.
     */
    public List<Cliente> findAll() {
        // Chamada ao repositório para buscar todos os clientes
        return clienteRepository.findAll();
    }

    /**
     * Busca um cliente pelo seu ID.
     *
     * @param id Identificador do cliente (não pode ser nulo).
     * @return Cliente encontrado ou null se não existir.
     */
    public Cliente findById(Integer id) {
        // Verifica se o ID é válido
        if (id == null) {
            return null;
        }

        // Tenta encontrar o cliente pelo ID no repositório
        Optional<Cliente> cliente = clienteRepository.findById(id);

        // Retorna o cliente encontrado ou null se não existir
        return cliente.orElse(null);
    }

    /**
     * Cadastra um novo cliente.
     *
     * @param cliente Cliente a ser cadastrado (não pode ser nulo).
     * @return Cliente salvo no sistema ou null se não for possível.
     */
    public Cliente save(Cliente cliente) {
        // Verifica se o cliente é válido
        if (cliente == null) {
            return null;
        }

        // Atualiza os responsáveis do cliente com a referência ao cliente
        for (Responsavel r : cliente.getResponsaveis()) {
            r.setCliente(cliente);
        }

        // Tenta salvar o cliente no repositório
        Cliente clienteSalvo;

        try {
            clienteSalvo = clienteRepository.save(cliente);
        } catch (Exception e) {
            // Lança a exceção original se ocorrer um erro ao salvar o cliente
            throw e;
        }

        // Retorna o cliente salvo ou null se não for possível
        return clienteSalvo;
    }

    /**
     * Atualiza os dados de um cliente existente.
     *
     * @param cliente Cliente a ser atualizado (não pode ser nulo).
     * @return Cliente atualizado no sistema ou null se não for possível.
     */
    public Cliente update(Cliente cliente) {
        // Verifica se o cliente é válido
        if (cliente == null) {
            return null;
        }

        // Verifica se o ID do cliente é válido (não pode ser nulo)
        if (cliente.getId() == null) {
            return null;
        }

        // Atualiza os responsáveis do cliente com a referência ao cliente
        for (Responsavel r : cliente.getResponsaveis()) {
            r.setCliente(cliente);
        }

        // Tenta salvar o cliente atualizado no repositório
        return clienteRepository.save(cliente);
    }

    /**
     * Exclui um cliente do sistema.
     *
     * @param id Identificador do cliente a ser excluído (não pode ser nulo).
     * @return true se o cliente foi excluído com sucesso, false caso contrário.
     */
    public boolean delete(Integer id) {
        // Verifica se o ID é válido
        if (id == null) {
            return false;
        }

        // Tenta encontrar o cliente pelo ID no repositório
        Optional<Cliente> cliente = clienteRepository.findById(id);

        // Verifica se o cliente existe
        if (cliente.isEmpty()) {
            return false; // Cliente não encontrado, nada a fazer
        }

        // Tenta excluir o cliente do repositório
        clienteRepository.deleteById(id);

        // Retorna true se o cliente foi excluído com sucesso
        return true;
    }
}
