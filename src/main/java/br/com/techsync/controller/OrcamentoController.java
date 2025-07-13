package br.com.techsync.controller;

import br.com.techsync.models.Orcamento;
import br.com.techsync.models.Servicos;
import br.com.techsync.service.OrcamentoService;
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
    public Orcamento criar(@RequestBody Orcamento orcamento) {
        double valorTotal = 0.0;

        for (Servicos s : orcamento.getServicos()) {
            s.setOrcamento(orcamento); // vincula ao orçamento
            valorTotal += s.getValor() * s.getQuantidade(); // soma ao total
        }

        orcamento.setValor(valorTotal); // define o valor total automaticamente
        return service.salvar(orcamento);
    }

    @GetMapping
    public List<Orcamento> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Orcamento buscar(@PathVariable int id) {
        return service.buscarPorId(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Orcamento atualizar(@PathVariable int id, @RequestBody Orcamento dadosAtualizados) {
        return service.buscarPorId(id).map(orcamentoExistente -> {
            // Atualiza os dados básicos
            orcamentoExistente.setValor(dadosAtualizados.getValor());
            orcamentoExistente.setCliente(dadosAtualizados.getCliente());

            // Atualiza os serviços
            orcamentoExistente.getServicos().clear();
            for (Servicos s : dadosAtualizados.getServicos()) {
                s.setOrcamento(orcamentoExistente); // vínculo reverso
                orcamentoExistente.getServicos().add(s);
            }

            return service.salvar(orcamentoExistente);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {
        service.deletar(id);
    }
}