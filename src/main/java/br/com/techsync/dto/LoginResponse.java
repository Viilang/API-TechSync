// src/main/java/br/com/techsync/dto/LoginResponse.java
package br.com.techsync.dto;

import br.com.techsync.models.Usuario; // Importe a classe Usuario

public class LoginResponse {
    private String token;
    private Usuario user; // O objeto Usuario autenticado

    // Construtor
    public LoginResponse(String token, Usuario user) {
        this.token = token;
        this.user = user;
    }

    // Getters e Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}