package br.com.techsync.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "T_TS_SERVICOS")
public class Servicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String descricao;
    private double valor;
    private int quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orcamento_id")
    @JsonBackReference
    private Orcamento orcamento;

    // Construtores
    public Servicos() {}

    public Servicos(String descricao, double valor, int quantidade) {
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public Orcamento getOrcamento() { return orcamento; }
    public void setOrcamento(Orcamento orcamento) { this.orcamento = orcamento; }

    // Regras de Négocio
    public void setProperties(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public boolean buscarDados() {
        return this.id > 0;
    }

    public void salvar() {
        // Lógica será implementada no service
    }

    public void listarTodos() {
        // Lógica será implementada no service
    }
}