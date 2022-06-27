package com.example.ipotato.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ipotato.R;
import com.example.ipotato.models.Produto;
import java.util.ArrayList;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Produto> dataset;
    private OnRecyclerViewItemCliclListener onRecyclerViewItemClickListener;

    public ProdutoAdapter(Context context, ArrayList<Produto> dataset) {
        this.context = context;
        this.dataset = dataset;
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemCliclListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View linha = LayoutInflater.from(context).inflate(R.layout.fragment_item_promo, parent, false);
        return new ViewHolder(linha);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produto produto = dataset.get(position);

        holder.getTvNomeProdutoVH().setText(produto.getNome());
        holder.getTvPrecoProdutoVH().setText(String.format("R$%.2f", produto.getPreco()));
//        holder.getIvImagemProdutoVH().setImageResource(produto.getImagem());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvNomeProdutoVH;
        private TextView tvPrecoProdutoVH;
        private ImageView ivImagemProdutoVH;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNomeProdutoVH = itemView.findViewById(R.id.tvNomeProduto);
            tvPrecoProdutoVH = itemView.findViewById(R.id.tvPrecoProduto);
            ivImagemProdutoVH = itemView.findViewById(R.id.ivImagemProduto);
            itemView.setOnClickListener(this);
        }

        public TextView getTvNomeProdutoVH() {
            return tvNomeProdutoVH;
        }

        public void setTvNomeProdutoVH(TextView tvNomeProdutoVH) {
            this.tvNomeProdutoVH = tvNomeProdutoVH;
        }

        public TextView getTvPrecoProdutoVH() {
            return tvPrecoProdutoVH;
        }

        public void setTvPrecoProdutoVH(TextView tvPrecoProdutoVH) {
            this.tvPrecoProdutoVH = tvPrecoProdutoVH;
        }

        public ImageView getIvImagemProdutoVH() {
            return ivImagemProdutoVH;
        }

        public void setIvImagemProdutoVH(ImageView ivImagemProdutoVH) {
            this.ivImagemProdutoVH = ivImagemProdutoVH;
        }

        @Override
        public void onClick(View view) {
            if (onRecyclerViewItemClickListener != null){
                onRecyclerViewItemClickListener.onRecyclerViewItemClick(dataset.get(this.getAdapterPosition()));
            }
        }
    }

        public interface OnRecyclerViewItemCliclListener {
            void onRecyclerViewItemClick(Produto p);
        }
}