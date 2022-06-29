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
import android.widget.ListView;
import android.widget.Toast;

import com.example.ipotato.R;
import com.example.ipotato.adapters.ProdutoAdapter;
import com.example.ipotato.dao.ProdutoDAO;
import com.example.ipotato.models.Produto;

import java.io.Serializable;
import java.util.List;

public class Cardapio extends Fragment {

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
        return inflater.inflate(R.layout.fragment_cardapio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getContext(), "Estou no cardápio", Toast.LENGTH_SHORT).show();
        super.onViewCreated(view, savedInstanceState);

        tabelaProduto = new ProdutoDAO(getContext());

        listViewProdutos = view.findViewById(R.id.idListaContainerDeProdutos);
        listaDeProdutos = tabelaProduto.getAllProducts();

        ProdutoAdapter adaptador = new ProdutoAdapter(getContext(), listaDeProdutos); //ele vai puxar os produtos por conta do método estático.
        listViewProdutos.setAdapter(adaptador);
    }
}