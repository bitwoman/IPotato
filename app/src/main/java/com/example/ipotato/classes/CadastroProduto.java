package com.example.ipotato.classes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipotato.R;
import com.example.ipotato.dao.ProdutoDAO;
import com.example.ipotato.dao.UsuarioDAO;
import com.example.ipotato.models.Produto;
import com.example.ipotato.models.Usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroProduto extends Fragment implements View.OnClickListener{

    //Atributos
    EditText editTextNomeProdutoCadastro, editTextDescricaoProdutoCadastro, editTextPrecoProdutoCadastro, editTextDescontoProdutoCadastro;
    ImageView buttonCadastrarImagemProduto;
    Button buttonCadastrarProduto;
    ProdutoDAO tabelaProduto;
    Produto produtoParaCadastrar;
    NavController navController;

    //Construtor
    public CadastroProduto() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadastro_produto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabelaProduto = new ProdutoDAO(getContext());

        navController = Navigation.findNavController(view);

        editTextNomeProdutoCadastro = view.findViewById(R.id.idEditTextNomeProdutoCadastro);
        editTextDescricaoProdutoCadastro = view.findViewById(R.id.idEditTextDescricaoProdutoCadastro);
        editTextPrecoProdutoCadastro = view.findViewById(R.id.idEditTextPrecoProdutoCadastro);
        editTextDescontoProdutoCadastro = view.findViewById(R.id.idEditTextDescontoProdutoCadastro);

        buttonCadastrarImagemProduto = view.findViewById(R.id.idButtonCadastrarImagemProdutoCadastro);
        buttonCadastrarProduto = view.findViewById(R.id.idButtonCadastrarProduto);

        buttonCadastrarImagemProduto.setOnClickListener(this);
        buttonCadastrarProduto.setOnClickListener(this);
    }

    //Método do evento de click de botões padrões na tela (cadastrar produto)
    @Override
    public void onClick(View view) {
        if(validarCadastroProduto() == true) {
            tabelaProduto.inserir(produtoParaCadastrar);
            navController.navigate(R.id.action_cadastroProduto_to_cardapio);
        }else{
            Toast.makeText(getContext(), "CADASTRO NÃO REALIZADO!", Toast.LENGTH_SHORT).show();
        }
    }

    //Método personalizado para validar o cadastro do produto
    public Boolean validarCadastroProduto(){

        String nomeProduto = editTextNomeProdutoCadastro.getText().toString().trim();
        String descricaoProduto = editTextDescricaoProdutoCadastro.getText().toString().trim();
        String precoProduto = editTextPrecoProdutoCadastro.getText().toString().trim();
        String descontoProduto = editTextDescontoProdutoCadastro.getText().toString().trim();
//            int imagemProduto = ;

        if (nomeProduto.isEmpty() || descricaoProduto.isEmpty() || precoProduto.isEmpty() || descontoProduto.isEmpty()){
            editTextNomeProdutoCadastro.setHint("Campo obrigatório!");
            editTextNomeProdutoCadastro.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));

            editTextDescricaoProdutoCadastro.setHint("Campo obrigatório!");
            editTextDescricaoProdutoCadastro.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));

            editTextPrecoProdutoCadastro.setHint("Campo obrigatório!");
            editTextPrecoProdutoCadastro.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));
        } else {
            //Validação regex para o campo conter apenas número, vírgula ou ponto (pelo atributo ser do tipo double, e sem espaço)
            Pattern validacaoPrecoDesconto = Pattern.compile("^[0-9]*\\.?[0-9]*");

            //Instancia da classe Matcher para validar se o valor da variável é compatível com o padrão decretado no regex
            Matcher matchPreco = validacaoPrecoDesconto.matcher(precoProduto);
            Matcher matchDesconto = validacaoPrecoDesconto.matcher(descontoProduto);

            //Resultado em booleano da validação do regex nos campos
            boolean respostaValicaoPreco = matchPreco.matches();
            boolean respostaValicaoDesconto = matchDesconto.matches();

            double precoProdutoDouble = Double.parseDouble (precoProduto);
            double descontoProdutoDouble = Double.parseDouble (descontoProduto);

            //Instância da classe Produto para a inserção dos dados do produto para, posterior, ser inserida no banco de dados - na tabela de produto.
            produtoParaCadastrar = new Produto(0, nomeProduto,descricaoProduto, precoProdutoDouble, descontoProdutoDouble);

            //Verifica se as validações estão corretas e retornam verdadeiro para que o processo de cadastro prossiga
            if(respostaValicaoPreco == true){
                if(respostaValicaoDesconto == true){
                    return true;
                }else {
                    Toast.makeText(getContext(), "Preço inválido", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(getContext(), "Preço inválido", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return false;
    }
}