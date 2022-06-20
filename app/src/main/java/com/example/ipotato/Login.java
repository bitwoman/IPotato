package com.example.ipotato;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Html;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Fragment implements View.OnClickListener{

    //Atributos
    NavController navController;
    Button buttonLogin, buttonRegistrar;
    TextView buttonPopularBanco;
    EditText editTextNomeUsuario, editTextSenhaUsuario;

    //construtor
    public Login() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        editTextNomeUsuario = view.findViewById(R.id.idEditTextNomeUsuario);
        editTextSenhaUsuario = view.findViewById(R.id.idEditTextSenhaUsuario);

        buttonLogin = view.findViewById(R.id.idButtonLogin);
        buttonLogin.setOnClickListener(this);

        buttonRegistrar = view.findViewById(R.id.idButtonRegistrar);
        buttonRegistrar.setOnClickListener(this);

        buttonPopularBanco = view.findViewById(R.id.idButtonPopularBanco);
        buttonPopularBanco.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.idButtonLogin:
                if(editTextNomeUsuario.getText().toString().isEmpty() || editTextSenhaUsuario.getText().toString().isEmpty()){
                    editTextNomeUsuario.setHint("Campo obrigatório!");
                    editTextNomeUsuario.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));

                    editTextSenhaUsuario.setHint("Campo obrigatório!");
                    editTextSenhaUsuario.setHintTextColor(this.getResources().getColor(R.color.vermelho_hint_edittext_verificacao));
                } else{
                    navController.navigate(R.id.action_login_to_iniciarPedido3);
                }
                break;

            case R.id.idButtonRegistrar:
                navController.navigate(R.id.action_login_to_cadastroUsuario);
                break;

            case R.id.idButtonPopularBanco:
                break;
        }
    }
}