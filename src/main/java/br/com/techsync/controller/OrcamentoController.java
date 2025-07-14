package br.com.techsync.controller;

import br.com.techsync.models.Orcamento;
import br.com.techsync.models.Servicos;
import br.com.techsync.service.OrcamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orcamentos")
public class OrcamentoController {

    private final OrcamentoService service;

    public OrcamentoController(OrcamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Orcamento> criar(@RequestBody Orcamento orcamento) {
        Orcamento novoOrcamento = service.criarOrcamento(orcamento);
        return ResponseEntity.ok(novoOrcamento);
    }

    @GetMapping
    public List<Orcamento> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orcamento> buscar(@PathVariable int id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orcamento> atualizar(@PathVariable int id, @RequestBody Orcamento dadosAtualizados) {
        Orcamento orcamentoAtualizado = service.atualizarOrcamento(id, dadosAtualizados);

        if (orcamentoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(orcamentoAtualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        boolean deletado = service.deletar(id);

        if (deletado) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}