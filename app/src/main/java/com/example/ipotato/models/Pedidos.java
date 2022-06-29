package com.example.ipotato.models;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ipotato.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pedidos extends Fragment implements Serializable {

    //Atributos
    public List<Produto> produtosNoPedido = new ArrayList<>();
    public List<Pedido> atributosPedido = new ArrayList<>();

    //Construtor
   public Pedidos(List<Produto> produtosNoPedido) {
       this.produtosNoPedido = produtosNoPedido;
   }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pedidos, container, false);
    }

    ///Métodos personalizados
    //Método responsável por retornar o valor total da compra feita pelo usuário
    public double valorTotalPedido(){
        double total = 0;

        for (Produto produto: produtosNoPedido) {
            total += produto.getPreco() * produto.getQuantidadeProduto();
        }
        return total;
    }

    //Método responsável por retornar a quantidade total de itens pedidos
    public int quantidadeTotalPedido(){
        int quantidade = 0;

        for (Produto produto: produtosNoPedido) {
            quantidade += produto.getQuantidadeProduto();
        }
        return quantidade;
    }

    public String retornaMetodoPgto(){
       String metodoPgto = "";

        for (Pedido pedido: atributosPedido) {
            metodoPgto = pedido.getMetodoPag();
        }
        return metodoPgto;
    }

    public String retornaEndereco(){
        String endereco = "";

        for (Pedido pedido: atributosPedido) {
            endereco = pedido.getEndereco();
        }
        return endereco;
    }
}