package com.example.ipotato.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ipotato.R;
import com.example.ipotato.classes.GerenciarCardapio;
import com.example.ipotato.dao.ProdutoDAO;
import com.example.ipotato.models.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoAdapter extends BaseAdapter implements View.OnClickListener {

    //Atributos
    private Context context;
    private List<Produto> dataset;
    private Button buttonRemoverQtdeItem, buttonAdicionarQtdeItem, buttonEditarProduto, buttonExcluirProduto;
    TextView textViewNomeProdutoPromo, textViewPrecoProdutoPromo;
    ImageView imageViewProdutoPromo;
    ProdutoDAO tabelaProduto;
    Produto produto;

    //Construtor
    public ProdutoAdapter(Context ct, List<Produto> produtos) {
        this.context = ct;
        this.dataset = produtos;
    }

    //Retorna o tamanho de lista
    @Override
    public int getCount() {
        return dataset.size();
    }

    //Pegar o item (produto) da lista daquela linha. objeto na posicao i
    @Override
    public Object getItem(int i) {
        return dataset.get(i);
    }

    //Pegar o id desse item (produto) da lista
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //retorna a linha construída. método principal. linha feita
        View linhaProduto = LayoutInflater.from(context).inflate(R.layout.fragment_item_batata, viewGroup, false); //3 parametros no inflate(layout, pai/viewgroup, falso -> não adc tudo de uma vez, porque temos que popular)

        //inflar o layout xml, acessar os campos e colocar os valores,
        //Acesar imgView, e txt's

        //Inflando os tópicos do produto
        textViewNomeProdutoPromo = view.findViewById(R.id.idTxtNomeProduto);
        textViewPrecoProdutoPromo = view.findViewById(R.id.idTxtPreco);

//        imageViewProdutoPromo = view.findViewById(R.id.idImagemProdutoPromo);

        buttonRemoverQtdeItem = view.findViewById(R.id.idButtonRemoverQtdeItem);
        buttonAdicionarQtdeItem = view.findViewById(R.id.idButtonAdicionarQtdeItem);
//        buttonEditarProduto = view.findViewById(R.id.idButtonEditarProduto);
//        buttonExcluirProduto = view.findViewById(R.id.idButtonExcluirProduto);

        //Pra setar os atributos do produto específico, de acordo com o seu id
        produto = dataset.get(i);

        //Setar os atributos
        textViewNomeProdutoPromo.setText(produto.getNome());
        textViewPrecoProdutoPromo.setText((String.valueOf(produto.getPreco())));
//        imageViewProdutoPromo.setImageBitmap(produto.getImagem());

        //O adapter da lista -> Pega os dados de uma forma para serem exibidos de outro.
        return linhaProduto;
    }

    //Método de evento de click
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.idButtonEditarProduto:
                Intent it = new Intent(context, GerenciarCardapio.class);
                it.putExtra("produto", String.valueOf(produto.getId()));
                context.startActivity(it);
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
                        tabelaProduto.deletar(String.valueOf(produto.getId()));

                        dataset.remove(produto);
                        notifyDataSetChanged();
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