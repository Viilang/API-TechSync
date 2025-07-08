// src/main/java/br/com/techsync/dto/LoginRequest.java
package br.com.techsync.dto;

public class LoginRequest {
    private String email;
    private String senha;

    // Construtor padrão (necessário para deserialização do JSON)
    public LoginRequest() {
    }

    // Construtor com todos os campos
    public LoginRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}