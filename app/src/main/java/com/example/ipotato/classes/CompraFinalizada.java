package com.example.ipotato.classes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ipotato.R;
import com.example.ipotato.adapters.ProdutoAdapter;
import com.example.ipotato.models.Pedidos;

import org.w3c.dom.Text;

import java.io.Serializable;

public class CompraFinalizada extends Fragment {

    //Atributos
    TextView txtViewTotalCompra, txtViewQtdeTotalProdutos, txtViewFormaPgto, txtViewEnderecoEntrega;

    ///Construtor
    public CompraFinalizada() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compra_finalizada, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent it = new Intent(view.getContext(), CompraFinalizada.class);
        Pedidos pedidos = (Pedidos) it.getSerializableExtra("pedido");
        final ProdutoAdapter adaptador = new ProdutoAdapter(getContext(), pedidos.produtosNoPedido);

        ListView listView = (ListView) view.findViewById(R.id.idListViewContainerItensComprados);
        listView.setAdapter(adaptador);

        txtViewTotalCompra = view.findViewById(R.id.idTxtViewValorTotalPedidoFinal);
        txtViewQtdeTotalProdutos = view.findViewById(R.id.idTxtViewQtdeProdutos);
        txtViewFormaPgto = view.findViewById(R.id.idTxtRetornoFormaPgto);
        txtViewEnderecoEntrega = view.findViewById(R.id.idTxtViewRetornoEnderecoEntrega);

        txtViewTotalCompra.setText("R$ " + String.format("%.2f", pedidos.valorTotalPedido()).replace(".",","));
        txtViewQtdeTotalProdutos.setText(String.valueOf(pedidos.quantidadeTotalPedido()));
        txtViewFormaPgto.setText(pedidos.retornaMetodoPgto());
        txtViewEnderecoEntrega.setText(pedidos.retornaEndereco());
    }
}