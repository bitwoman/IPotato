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
import android.widget.Toast;

import com.example.ipotato.R;
import com.example.ipotato.adapters.ProdutoAdapter;
import com.example.ipotato.models.Produto;

public class Cardapio extends Fragment implements ProdutoAdapter.OnRecyclerViewItemCliclListener {

    //Atributos
    private RecyclerView listaProdutosEmPromo;

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
        super.onViewCreated(view, savedInstanceState);

        listaProdutosEmPromo = view.findViewById(R.id.idRecyclerProdutosPromo);
        listaProdutosEmPromo.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        ProdutoAdapter adapter = new ProdutoAdapter(getContext(), Produto.getProdutos());

        listaProdutosEmPromo.setAdapter(adapter);
    }

    @Override
    public void onRecyclerViewItemClick(Produto p) {
        Toast.makeText(getContext(), "Clicou em " + p.getNome(), Toast.LENGTH_SHORT).show();
    }
}