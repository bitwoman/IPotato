package com.example.ipotato.models;

import android.graphics.Bitmap;

public class produto {

    //Atributos
    private long id;
    private Bitmap imagem;
    private String nome;
    private double preco, desconto;

    //Construtor
    public produto(long id, Bitmap imagem, String nome, double preco, double desconto) {
        this.id = id;
        this.imagem = imagem;
        this.nome = nome;
        this.preco = preco;
        this.desconto = desconto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
}
