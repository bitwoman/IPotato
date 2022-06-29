package com.example.ipotato.classes;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipotato.R;
import com.example.ipotato.dao.PedidoDAO;
import com.example.ipotato.models.Pedido;
import com.example.ipotato.models.Produto;

import java.util.Locale;

public class Carrinho extends Fragment implements View.OnClickListener{

    //Atributos
    RadioGroup radioGroupFormaPagamento;
    RadioButton buttonFormaPgtoDinheiro, buttonFormaPgtoCredito, buttonFormaPgtoDebito;
    EditText editTextEnderecoEntrega;
    TextView textViewTotalPedido;
    Button buttonFecharPedido;

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

        radioGroupFormaPagamento = view.findViewById(R.id.idRadioGroupFormaPagamento);
        buttonFormaPgtoDinheiro = view.findViewById(R.id.idButtonFormaPgtoDinheiro);
        buttonFormaPgtoCredito = view.findViewById(R.id.idButtonFormaPgtoCredito);
        buttonFormaPgtoDebito = view.findViewById(R.id.idButtonFormaPgtoDebito);

        editTextEnderecoEntrega = view.findViewById(R.id.idEditTextEnderecoEntrega);

        textViewTotalPedido = view.findViewById(R.id.idTxtViewValorTotalPedido);

        buttonFecharPedido = view.findViewById(R.id.idButtonFecharPedido);
        buttonFecharPedido.setOnClickListener(this);
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