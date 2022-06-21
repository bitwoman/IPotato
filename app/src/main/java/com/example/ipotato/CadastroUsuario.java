package com.example.ipotato;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroUsuario extends Fragment implements View.OnClickListener{

    //Atributos
    NavController navController;
    EditText editTextNomeCompleto, editTextEmail, editTextNomeDeUsuario, editTextSenha, editTextConfirmarSenha;
    Button buttonRegistrar;

    //Construtor
    public CadastroUsuario() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadastro_usuario, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        editTextNomeCompleto = view.findViewById(R.id.idEditTextNomeCadastro);
        editTextEmail = view.findViewById(R.id.idEditTextEmailCadastro);
        editTextNomeDeUsuario = view.findViewById(R.id.idEditTextUsuarioCadastro);
        editTextSenha = view.findViewById(R.id.idEditTextSenhaCadastro);
        editTextConfirmarSenha = view.findViewById(R.id.idEditTextConfirmarSenhaCadastro);

        buttonRegistrar = view.findViewById(R.id.idButtonRegistrarCadastro);
        buttonRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        /*if(editTextNomeCompleto.getText().toString().isEmpty() || editTextEmail.getText().toString().isEmpty() ||
           editTextNomeDeUsuario.getText().toString().isEmpty() || editTextSenha.getText().toString().isEmpty() ||
           editTextConfirmarSenha.getText().toString().isEmpty()){

            editTextNomeCompleto.setHint("Campo obrigatório!");
            editTextNomeCompleto.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));

            editTextEmail.setHint("Campo obrigatório!");
            editTextEmail.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));

            editTextNomeDeUsuario.setHint("Campo obrigatório!");
            editTextNomeDeUsuario.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));

            editTextSenha.setHint("Campo obrigatório!");
            editTextSenha.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));

            editTextConfirmarSenha.setHint("Campo obrigatório!");
            editTextConfirmarSenha.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));
        } else {
            String nome = editTextNomeCompleto.getText().toString().trim();

            Pattern validacaoNome = Pattern.compile("[A-Za-z_ ]+");
            Matcher matchNome = validacaoNome.matcher(nome);
            boolean respostaValicaoNome = matchNome.matches();

            Pattern patternEmail = Patterns.EMAIL_ADDRESS;
            boolean validacaoEmail = patternEmail.matcher(editTextEmail.getText().toString().trim()).matches();

            String senha = editTextSenha.getText().toString().trim();
            String senhaConfirmada = editTextConfirmarSenha.getText().toString().trim();

//            Pattern validacaoSenha = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$");
//            Matcher matchSenha = validacaoSenha.matcher(senha);
//            boolean respostaValicaoSenha = matchSenha.matches();

//            Pattern validacaoSenhaConfirmada = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$");
//            Matcher matchSenhaConfirmada = validacaoSenhaConfirmada.matcher(senhaConfirmada);
//            boolean respostaValicaoSenhaConfirmada = matchSenhaConfirmada.matches();
            
            if(respostaValicaoNome == true){
                if(validacaoEmail == true){
                    if(respostaValicaoSenha == true){
                        if(respostaValicaoSenhaConfirmada == true){
                            if (senha.equals(senhaConfirmada)){
                                navController.navigate(R.id.action_cadastroUsuario_to_iniciarPedido3);
                            }else{
                                Toast.makeText(getContext(), "As senhas são diferentes!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getContext(), "Senha fora de padrão!", Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        Toast.makeText(getContext(), "Senha fora de padrão!", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(getContext(), "E-MAIL INCORRETO!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getContext(), "Nome fora do padrão!", Toast.LENGTH_SHORT).show();
            }
        }*/
    }
}