package com.example.ipotato.classes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ipotato.R;
import com.example.ipotato.adapters.ProdutoAdapter;
import com.example.ipotato.dao.ProdutoDAO;
import com.example.ipotato.models.Produto;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;

public class Cardapio extends Fragment {

    private ProdutoAdapter adap;
    private RecyclerView rcView;

    //Atributos
    ListView listViewProdutos;
    List<Produto> listaDeProdutos;
    ProdutoDAO tabelaProduto;

    //Construtor
    public Cardapio() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_batata, container, false);

        tabelaProduto = new ProdutoDAO(getContext());

        listViewProdutos = (ListView) view.findViewById(R.id.idListaContainerDeProdutos);
        listaDeProdutos = tabelaProduto.produtosMockados();
        ProdutoAdapter adap = new ProdutoAdapter(getContext(), listaDeProdutos);

        listViewProdutos.setAdapter(adap);
//
        return view;
//        return inflater.inflate(R.layout.fragment_cardapio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getContext(), "Estou no cardápio", Toast.LENGTH_SHORT).show();
        super.onViewCreated(view, savedInstanceState);
//        tabelaProduto = new ProdutoDAO(getContext());
//
//        listViewProdutos = (ListView) view.findViewById(R.id.idListaContainerDeProdutos);
//        listaDeProdutos = tabelaProduto.produtosMockados();
//        ProdutoAdapter adap = new ProdutoAdapter(getContext(), listaDeProdutos);
//
//        listViewProdutos.setAdapter(adap);
    }
}