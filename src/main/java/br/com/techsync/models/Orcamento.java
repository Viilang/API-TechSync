package br.com.techsync.models;

import br.com.techsync.models.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "T_TS_ORCAMENTOS")
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Servicos> servicos = new ArrayList<>();

    private double valor;

    // Construtores
    public Orcamento() {}

    public Orcamento(Cliente cliente, double valor) {
        this.cliente = cliente;
        this.valor = valor;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<Servicos> getServicos() { return servicos; }
    public void setServicos(List<Servicos> servicos) { this.servicos = servicos; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    // Regras de Negócio
    public void addServico(Servicos servico) {
        servicos.add(servico);
        servico.setOrcamento(this);
        calcularValorTotal();
    }

    public void removerServico(Servicos servico) {
        servicos.remove(servico);
        servico.setOrcamento(null);
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        this.valor = servicos.stream()
                .mapToDouble(s -> s.getValor() * s.getQuantidade())
                .sum();
    }

    public short exportPDF() {
        // Implementar lógica de exportação PDF
        return 1; // Sucesso
    }

    public void fechar(boolean decisao) {
        // Implementar lógica de fechamento do orçamento
    }

    public boolean buscarDados() {
        return this.id > 0;
    }
}
