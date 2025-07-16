package br.com.techsync.controller;

import br.com.techsync.models.Servicos;
import br.com.techsync.service.ServicosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicosController {

    private final ServicosService servicosService;

    public ServicosController(ServicosService servicosService) {
        this.servicosService = servicosService;
    }

    @PostMapping
    public ResponseEntity<Servicos> criar(@RequestBody Servicos servico) {
        Servicos criado = servicosService.criarServico(servico);
        return ResponseEntity.ok(criado);
    }

    @GetMapping
    public List<Servicos> listar() {
        return servicosService.listarTodos();
    }

    @GetMapping("/{id}")
    public Servicos buscar(@PathVariable int id) {
        return servicosService.buscarPorId(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicos> atualizar(@PathVariable int id, @RequestBody Servicos novosDados) {
        return servicosService.atualizarServico(id, novosDados)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        boolean removido = servicosService.removerServico(id);
        return removido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}