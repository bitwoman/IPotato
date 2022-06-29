package com.example.ipotato.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.ipotato.R;
import com.example.ipotato.models.Produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PedidoAdapter extends BaseAdapter implements NumberPicker.OnValueChangeListener, Serializable {

    //Atributos
    private Context context;
    public List<Produto> produto = new ArrayList<>();

    //Construtor
    public PedidoAdapter(Context ct, List<Produto> dt) {
        this.context = ct;
        this.produto = dt;
    }

    //Retorna o tamanho de lista
    @Override
    public int getCount() {
        return produto.size();
    }

    //Pegar o item (produto) da lista daquela linha. objeto na posicao i
    @Override
    public Object getItem(int i) {
        return produto.get(i);
    }

    //Pegar o id desse item (produto) da lista
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View linhaPedidoAdaptador = inflater.inflate(R.layout.fragment_pedidos, viewGroup, false);

//        ImageView imagemProduto = (ImageView) linhaPedidoAdaptador.findViewById(R.id.idImageViewItemBatataPedidos);
        TextView txtNomeProduto = (TextView) linhaPedidoAdaptador.findViewById(R.id.idTxtNomeProdutoPedidos);
        TextView txtPrecoProduto = (TextView) linhaPedidoAdaptador.findViewById(R.id.idTxtPrecoPedidos);
        final NumberPicker quantidadeProduto = (NumberPicker) linhaPedidoAdaptador.findViewById(R.id.idNPQtdeProdutoNoCarrinhoPedidos);

        final Produto itemProduto = produto.get(i);

//        imagemProduto.setImageResource(itemProduto.getImagem());
        txtNomeProduto.setText(itemProduto.getNome());
        txtPrecoProduto.setText("R$ " + String.format("%.2f", itemProduto.getPreco()).replace(".", ","));

        quantidadeProduto.setMaxValue(10);
        quantidadeProduto.setMinValue(0);
        quantidadeProduto.setValue(itemProduto.getQuantidadeProduto());

        quantidadeProduto.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int valuePicker = quantidadeProduto.getValue();

                itemProduto.setQuantidadeProduto(quantidadeProduto.getValue());
            }
        });
        return linhaPedidoAdaptador;
    }

    //Instância para o funcionamento do valor do NumberPicker, como o onclicklistener
    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {

    }
}
