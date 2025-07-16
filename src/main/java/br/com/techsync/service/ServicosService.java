package br.com.techsync.service;

import br.com.techsync.models.Orcamento;
import br.com.techsync.models.Servicos;
import br.com.techsync.repository.ServicosRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicosService {

    private final ServicosRepository servicosRepository;
    private final OrcamentoService orcamentoService;

    public ServicosService(ServicosRepository servicosRepository, OrcamentoService orcamentoService) {
        this.servicosRepository = servicosRepository;
        this.orcamentoService = orcamentoService;
    }

    public Servicos criarServico(Servicos servico) {
        Integer orcamentoId = servico.getOrcamento().getId();

        Orcamento orcamentoPersistido = orcamentoService.buscarPorId(orcamentoId)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado"));

        servico.setOrcamento(orcamentoPersistido);

        Servicos salvo = servicosRepository.save(servico);

        orcamentoPersistido.calcularValorTotal();
        orcamentoService.salvar(orcamentoPersistido);

        return salvo;
    }

    public List<Servicos> listarTodos() {
        return servicosRepository.findAll();
    }

    public Optional<Servicos> buscarPorId(int id) {
        return servicosRepository.findById(id);
    }

    public Optional<Servicos> atualizarServico(int id, Servicos novosDados) {
        return servicosRepository.findById(id).map(servico -> {
            servico.setDescricao(novosDados.getDescricao());
            servico.setValor(novosDados.getValor());
            servico.setQuantidade(novosDados.getQuantidade());

            Orcamento orcamento = servico.getOrcamento();
            orcamento.calcularValorTotal();
            orcamentoService.salvar(orcamento);

            return servicosRepository.save(servico);
        });
    }

    public boolean removerServico(int id) {
        Optional<Servicos> servicoOptional = servicosRepository.findById(id);

        if (servicoOptional.isPresent()) {
            Servicos servico = servicoOptional.get();
            Orcamento orcamento = servico.getOrcamento();

            orcamento.getServicos().remove(servico);

            orcamento.calcularValorTotal();
            orcamentoService.salvar(orcamento);

            servicosRepository.deleteById(id);
            return true;
        }

        return false;
    }
}