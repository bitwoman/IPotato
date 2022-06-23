package com.example.ipotato.models;

public class Pedido {

    //Atributos
    private long id;
    private Produto produto;
    private int quantidade;
    private String metodoPag, endereco;

    //Construtor
    public Pedido(long id, Produto produto, int quantidade, String metodoPag, String endereco) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.metodoPag = metodoPag;
        this.endereco = endereco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getMetodoPag() {
        return metodoPag;
    }

    public void setMetodoPag(String metodoPag) {
        this.metodoPag = metodoPag;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
