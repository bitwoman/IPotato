package com.example.ipotato.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ipotato.R;
import com.example.ipotato.dao.ProdutoDAO;
import com.example.ipotato.models.Produto;
import java.util.ArrayList;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ViewHolder>{

    //Atributos
    private Context context;
    private ArrayList<Produto> dataset;
    private OnRecyclerViewItemCliclListener onRecyclerViewItemCliclListener;
    private Button buttonRemoverQtdeItem, buttonAdicionarQtdeItem, buttonEditarProduto, buttonExcluirProduto;
    ProdutoDAO tabelaProduto;
    Produto produto;

    //Construtor
    public ProdutoAdapter(Context context, ArrayList<Produto> dataset) {
        this.context = context;
        this.dataset = dataset;
    }

    public void setOnRecyclerViewItemCliclListener(OnRecyclerViewItemCliclListener onRecyclerViewItemCliclListener) {
        this.onRecyclerViewItemCliclListener = onRecyclerViewItemCliclListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View linha = LayoutInflater.from(context).inflate(R.layout.fragment_item_batata, parent,false);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvNomeProdutoVH;
        private TextView tvPrecoProdutoVH;
        private ImageView ivImagemProdutoVH;
        NavController navController;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tabelaProduto = new ProdutoDAO(context);

            tvNomeProdutoVH = itemView.findViewById(R.id.idTxtViewNomeProdutoPromo);
            tvPrecoProdutoVH = itemView.findViewById(R.id.idTxtViewPrecoProdutoPromo);

            ivImagemProdutoVH = itemView.findViewById(R.id.idImagemProdutoPromo);

            buttonRemoverQtdeItem = itemView.findViewById(R.id.idButtonRemoverQtdeItem);
            buttonAdicionarQtdeItem = itemView.findViewById(R.id.idButtonAdicionarQtdeItem);
            buttonEditarProduto = itemView.findViewById(R.id.idButtonEditarProduto);
            buttonExcluirProduto = itemView.findViewById(R.id.idButtonExcluirProduto);

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
            if (onRecyclerViewItemCliclListener != null){
                onRecyclerViewItemCliclListener.onRecyclerViewItemClick(dataset.get(this.getAdapterPosition()));
            }

            switch (view.getId()){
                case R.id.idButtonEditarProduto:
                    break;
                case R.id.idButtonExcluirProduto:
                    //Pra setar os atributos do produto específico, de acordo com o seu id
//                    produto = dataset.get(i);

                    //Instanciando o objeto da caixa de alerta
                    AlertDialog.Builder alertDelete = new AlertDialog.Builder(context);

                    //Criando título da janela e a mensagem que será exibida para o usuário
                    alertDelete.setTitle(R.string.title_alert_dialog);
                    alertDelete.setMessage(R.string.alert_dialog_warning);

                    //Inflando a caixa de alerta junto aos botões respectivos de confirmar e cancelar

                    //Confirmar
                    alertDelete.setPositiveButton(R.string.alert_dialog_confirmar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                            tabelaProduto.deletar(produto);

//                            dataset.remove(contato);
//                            notifyDataSetChanged();
                        }
                    });

                    //Cancelar
                    alertDelete.setNegativeButton(R.string.alert_dialog_cancelar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    alertDelete.show();




                    break;
                case R.id.idButtonAdicionarQtdeItem:
                    break;
                case R.id.idButtonRemoverQtdeItem:
                    break;
            }
        }
    }

    public interface OnRecyclerViewItemCliclListener {
        void onRecyclerViewItemClick(Produto p);
    }
}