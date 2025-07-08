package br.com.techsync.controller.cliente;

import br.com.techsync.models.cliente.Cliente;
import br.com.techsync.service.cliente.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Criar um Cliente
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {

        Cliente novoCliente = clienteService.save(cliente);
        return ResponseEntity.ok(novoCliente);
    }

    // Editar um cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteEditado = clienteService.update(cliente);
        return ResponseEntity.ok(clienteEditado);
    }

    // Listar Empresa
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarCliente(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    // Listar Empresas
    @GetMapping()
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    // Excluir Empresas
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deletarCliente(@PathVariable Integer id) {
        return clienteService.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
