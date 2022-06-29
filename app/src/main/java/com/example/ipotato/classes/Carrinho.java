package com.example.ipotato.classes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipotato.R;
import com.example.ipotato.adapters.PedidoAdapter;
import com.example.ipotato.dao.PedidoDAO;
import com.example.ipotato.models.Pedido;
import com.example.ipotato.models.Pedidos;
import com.example.ipotato.models.Produto;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class Carrinho extends Fragment implements View.OnClickListener{

    //Atributos
    RadioGroup radioGroupFormaPagamento;
    RadioButton buttonFormaPgtoDinheiro, buttonFormaPgtoCredito, buttonFormaPgtoDebito;
    EditText editTextEnderecoEntrega;
    TextView textViewTotalPedido;
    Button buttonFecharPedido;
    List<Produto> listaDeBatatas;

    //Instâncias
    Pedido pedidoParaCadastrar;
    PedidoDAO tabelaPedido;

    //Construtor
    public Carrinho() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carrinho, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabelaPedido = new PedidoDAO(getContext());

        Intent it = new Intent(getContext(), Carrinho.class);
        listaDeBatatas = (List<Produto>) it.getSerializableExtra("PedidosProdutos");

        final PedidoAdapter adaptador = new PedidoAdapter(getContext(), listaDeBatatas);
        final ListView listaDePedidos = (ListView) view.findViewById(R.id.idListViewContainerItensPageCarrinho); //idListViewPedidoFinal
        listaDePedidos.setAdapter(adaptador);

        radioGroupFormaPagamento = view.findViewById(R.id.idRadioGroupFormaPagamento);
        buttonFormaPgtoDinheiro = view.findViewById(R.id.idButtonFormaPgtoDinheiro);
        buttonFormaPgtoCredito = view.findViewById(R.id.idButtonFormaPgtoCredito);
        buttonFormaPgtoDebito = view.findViewById(R.id.idButtonFormaPgtoDebito);

        editTextEnderecoEntrega = view.findViewById(R.id.idEditTextEnderecoEntrega);

        textViewTotalPedido = view.findViewById(R.id.idTxtViewValorTotalPedido);

        buttonFecharPedido = view.findViewById(R.id.idButtonFecharPedido);
        buttonFecharPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Pedidos pedidos = new Pedidos(adaptador);
//                Iterator<Produto> iterator = pedidos.produtosNoPedido.iterator();
//
//                while (iterator.hasNext()){
//                    if(iterator.next().getQuantidadeProduto() == 0){
//                        iterator.remove();
//                    }
//                }

//                Intent it = new Intent(view.getContext(), PedidoFinal.class);
//                it.putExtra("pedido", (Serializable) pedidos);
//                startActivity(it);
//                listaDePedidos.setAdapter(adaptador);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    //Validação do radioButton
    public Boolean validandoCampos(){

        String metodoPagamento;
        String enderecoDeEntrega = editTextEnderecoEntrega.getText().toString().trim();


        switch (radioGroupFormaPagamento.getCheckedRadioButtonId()){
            case R.id.idButtonFormaPgtoDinheiro:
                metodoPagamento = buttonFormaPgtoDinheiro.getText().toString().trim();
                return true;
            case R.id.idButtonFormaPgtoCredito:
                metodoPagamento = buttonFormaPgtoCredito.getText().toString().trim();
                return true;
            case R.id.idButtonFormaPgtoDebito:
                metodoPagamento = buttonFormaPgtoDebito.getText().toString().trim();
                return true;
            default:
                return false;
        }

        /*if(metodoPagamento.isEmpty()){
            Toast.makeText(getContext(), "Selecione uma forma de pagamento", Toast.LENGTH_SHORT).show();
        }else{
            if(enderecoDeEntrega.isEmpty()){
                Toast.makeText(getContext(), "Preencha o endereço de entrega", Toast.LENGTH_SHORT).show();
            }else{
                //Instância da classe Produto para a inserção dos dados do produto para, posterior, ser inserida no banco de dados - na tabela de produto.
//                pedidoParaCadastrar = new Pedido(0, produto, int quantidade, metodoPagamento, enderecoDeEntrega);
            }
        }*/
    }
}