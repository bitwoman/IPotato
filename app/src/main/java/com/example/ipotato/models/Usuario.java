package com.example.ipotato.models;

import android.util.Log;

public class Usuario {

    private long id;
    private String nome, email, username, pass;

    public Usuario(long id, String nome, String email, String username, String pass) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.pass = pass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
