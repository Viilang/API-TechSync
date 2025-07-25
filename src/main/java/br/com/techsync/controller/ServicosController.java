package br.com.techsync.controller;

import br.com.techsync.models.Orcamento;
import br.com.techsync.models.Servicos;
import br.com.techsync.repository.ServicosRepository;
import br.com.techsync.service.OrcamentoService;
import br.com.techsync.service.ServicosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicosController {

    private final ServicosService service;
    private final OrcamentoService orcamentoService;
    private final ServicosRepository servicosRepository;

    public ServicosController(ServicosService service, OrcamentoService orcamentoService, ServicosRepository servicosRepository ) {
        this.service = service;
        this.orcamentoService = orcamentoService;
        this.servicosRepository = servicosRepository;
    }

    @PostMapping
    public Servicos criar(@RequestBody Servicos servico) {
        // Pega o orçamento do serviço
        Orcamento orcamento = servico.getOrcamento();

        // Garante que o orçamento está atualizado do banco
        Orcamento orcamentoCompleto = orcamentoService.buscarPorId(orcamento.getId())
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado"));

        // Associa o serviço ao orçamento
        servico.setOrcamento(orcamentoCompleto);
        orcamentoCompleto.getServicos().add(servico);

        // Calcula o novo valor total
        double adicional = servico.getValor() * servico.getQuantidade();
        orcamentoCompleto.setValor(orcamentoCompleto.getValor() + adicional);

        // Salva tudo
        orcamentoService.salvar(orcamentoCompleto);

        return servicosRepository.save(servico); // ou service.salvar(servico)
    }

    @GetMapping
    public List<Servicos> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Servicos buscar(@PathVariable int id) {
        return service.buscarPorId(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {
        service.deletar(id);
    }
}