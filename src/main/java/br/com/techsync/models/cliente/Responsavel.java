package br.com.techsync.models.cliente;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
/**
 * Entidade responsável por representar a tabela de Responsáveis.
 */
@Entity
@Table(name = "T_TS_RESPONSAVEL")
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nome do responsável.
     */
    @Column(nullable = false)
    private String nome;

    /**
     * E-mail do responsável.
     */
    @Column(nullable = false)
    private String email;

    /**
     * Telefone do responsável.
     */
    @Column(nullable = false)
    private String telefone;

    /**
     * Função exercida pelo responsável.
     */
    @Column(nullable = false)
    private String funcao;

    /**
     * Cliente que possui este responsável.
     */
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonBackReference
    private Cliente cliente;

    // Constructor
    public Responsavel() {}

    // Getters e Setters

    /**
     * Retorna a função exercida pelo responsável.
     *
     * @return Função do responsável
     */
    public String getFuncao() {
        return funcao;
    }

    /**
     * Define a função exercida pelo responsável.
     *
     * @param funcao Nova função do responsável
     */
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    /**
     * Retorna o telefone do responsável.
     *
     * @return Telefone do responsável
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do responsável.
     *
     * @param telefone Novo telefone do responsável
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Retorna o e-mail do responsável.
     *
     * @return E-mail do responsável
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do responsável.
     *
     * @param email Novo e-mail do responsável
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna o nome do responsável.
     *
     * @return Nome do responsável
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do responsável.
     *
     * @param nome Novo nome do responsável
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o ID do responsável.
     *
     * @return ID do responsável
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o ID do responsável.
     *
     * @param id Novo ID do responsável
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o cliente que possui este responsável.
     *
     * @return Cliente que possui este responsável
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente que possui este responsável.
     *
     * @param cliente Novo cliente que possui este responsável
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
