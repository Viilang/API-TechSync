package br.com.techsync.controller.cliente;

import br.com.techsync.models.cliente.Cliente;
import br.com.techsync.service.cliente.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável por gerenciar os clientes.
 */
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    /**
     * Construtor do controller que recebe a instância do serviço de cliente.
     *
     * @param clienteService Instância do serviço de cliente.
     */
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Método para criar um novo cliente.
     *
     * @param cliente Objeto da entidade Cliente a ser criado.
     * @return Resposta HTTP com o cliente criado.
     */
    @PostMapping
    public ResponseEntity<?> criarCliente(@RequestBody Cliente cliente) {
        try {
            if (cliente == null) {
                throw new NullPointerException("Objeto cliente não pode ser nulo.");
            }
            return ResponseEntity.ok(clienteService.save(cliente));
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ocorreu um erro interno");
        }
    }

    /**
     * Método para editar um cliente existente.
     *
     * @param id      ID do cliente a ser editado
     * @param cliente Objeto da entidade Cliente a ser editado.
     * @return Resposta HTTP com o cliente editado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        try {
            if (id == null || id <= 0) {
                throw new NullPointerException("ID do cliente deve ser um número inteiro.");
            }
            if (cliente == null) {
                throw new NullPointerException("Objeto cliente não pode ser nulo.");
            }
            cliente.setId(id);
            return ResponseEntity.ok(clienteService.update(cliente));
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ocorreu um erro interno");
        }
    }

    /**
     * Método para listar um cliente por ID.
     *
     * @param id ID do cliente a ser buscado.
     * @return Resposta HTTP com o cliente encontrado, caso exista.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> listarCliente(@PathVariable Integer id) {
        try {
            if (id == null || id <= 0) {
                throw new NullPointerException("ID do cliente deve ser um número inteiro.");
            }
            return ResponseEntity.ok(clienteService.findById(id));
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ocorreu um erro interno");
        }
    }

    /**
     * Método para listar todos os clientes.
     *
     * @return Resposta HTTP com lista de clientes encontrados.
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    /**
     * Método para excluir um cliente por ID.
     *
     * @param id ID do cliente a ser excluído.
     * @return Resposta HTTP indicando se o cliente foi excluído com sucesso ou não encontrado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Integer id) {
        try {
            if (id == null || id <= 0) {
                throw new NullPointerException("ID do cliente deve ser um número inteiro.");
            }
            return clienteService.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ocorreu um erro interno");

        }

    }
}
