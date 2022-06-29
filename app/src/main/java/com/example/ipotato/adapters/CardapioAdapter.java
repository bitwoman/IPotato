package com.example.ipotato.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ipotato.R;
import com.example.ipotato.models.Produto;

import java.util.ArrayList;
import java.util.List;

public class CardapioAdapter extends RecyclerView.Adapter<CardapioAdapter.ViewHolder> {

    private List<Produto> lista;
    private Context context;

    public CardapioAdapter (Context c, List<Produto> l) {
        context = c;
        lista = l;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_cardapio, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgProduto.setImageResource(R.drawable.batata_assada);
        holder.nomeProduto.setText(lista.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nomeProduto;
        private ImageView imgProduto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeProduto = (TextView) itemView.findViewById(R.id.idTxtNomeProdutoPageGerenciar);
            imgProduto = (ImageView) itemView.findViewById(R.id.idImageViewProduto);

        }
    }

}
