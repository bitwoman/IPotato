package com.example.ipotato.models;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Produto {

    //Atributos
    private long id;
    private Bitmap imagem;
    private String nome;
    private double preco, desconto;

    //Construtor
    public Produto(long id, String nome, double preco, double desconto) {
        this.id = id;
//        this.imagem = imagem;
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

    //Método personalizado
    public static ArrayList<Produto> getProdutos(){
        ArrayList<Produto> batatas = new ArrayList<Produto>();

        batatas.add(new Produto(1, "Batata 1", 9.90, 1.0));
        batatas.add(new Produto(2, "Batata 2", 9.90, 1.0));
        batatas.add(new Produto(3, "Batata 3", 9.90, 1.0));
        batatas.add(new Produto(4, "Batata 4", 9.90, 1.0));

        return batatas;
    }

}
