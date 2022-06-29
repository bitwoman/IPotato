package com.example.ipotato.models;

import android.graphics.Bitmap;

import com.example.ipotato.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Produto implements Serializable {

    //Atributos
    private long id;
    private int imagem;
    private String nome, descricao;
    private double preco, desconto;
    public int quantidadeProduto = 0;

    //Construtor
    public Produto(long id, int imagem, String nome, String descricao, double preco, double desconto) {
        this.id = id;
        this.imagem = imagem;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.desconto = desconto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    //Método personalizado
    public static ArrayList<Produto> getProdutos(){
        ArrayList<Produto> batatas = new ArrayList<Produto>();

        batatas.add(new Produto(1, R.drawable.batata_assada,"Batata 1", "Batata 1", 9.90, 1.0));
        batatas.add(new Produto(2, R.drawable.batata_assada,"Batata 2", "Batata 2",9.90, 1.0));
        batatas.add(new Produto(3, R.drawable.batata_assada,"Batata 3", "Batata 3",9.90, 1.0));
        batatas.add(new Produto(4, R.drawable.batata_assada,"Batata 4", "Batata 4",9.90, 1.0));

        return batatas;
    }

}
