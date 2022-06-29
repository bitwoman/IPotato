package com.example.ipotato.classes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ipotato.R;
import com.example.ipotato.dao.UsuarioDAO;
import com.example.ipotato.models.Usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroUsuario extends Fragment implements View.OnClickListener{

    //Atributos
    NavController navController;
    EditText editTextNomeCompleto, editTextEmail, editTextNomeDeUsuario, editTextSenha, editTextConfirmarSenha;
    Button buttonRegistrar;
    UsuarioDAO tabelaUsuario;
    Usuario usuarioParaCadastrar;

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

        tabelaUsuario = new UsuarioDAO(getContext());

        navController = Navigation.findNavController(view);

        editTextNomeCompleto = view.findViewById(R.id.idEditTextNomeCadastro);
        editTextEmail = view.findViewById(R.id.idEditTextEmailCadastro);
        editTextNomeDeUsuario = view.findViewById(R.id.idEditTextUsuarioCadastro);
        editTextSenha = view.findViewById(R.id.idEditTextSenhaCadastro);
        editTextConfirmarSenha = view.findViewById(R.id.idEditTextConfirmarSenhaCadastro);

        buttonRegistrar = view.findViewById(R.id.idButtonRegistrarCadastro);
        buttonRegistrar.setOnClickListener(this);
    }

    //Método do evento de click de botões padrões na tela (cadastrar usuario)
    @Override
    public void onClick(View view) {
        if(this.validarCampos() == true) {
            tabelaUsuario.inserir(usuarioParaCadastrar);
            navController.navigate(R.id.action_cadastroUsuario_to_iniciarPedido3);
        }else{
            Toast.makeText(getContext(), "CADASTRO NÃO REALIZADO!", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean validarCampos() {
        String nome = editTextNomeCompleto.getText().toString().trim();
        String emailUsuario = editTextEmail.getText().toString().trim();
        String nomeUsuario = editTextNomeDeUsuario.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();
        String senhaConfirmada = editTextConfirmarSenha.getText().toString().trim();


        if (nome.isEmpty() || emailUsuario.isEmpty() || nomeUsuario.isEmpty() || senha.isEmpty() || senhaConfirmada.isEmpty()) {
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
            return false;
        }
        // Se os campos NÃO ESTIVEREM vazios!
        else {
            Pattern patternEmail = Patterns.EMAIL_ADDRESS;
            Pattern validacaoNome = Pattern.compile("[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+");

            Pattern validacaoSenha = Pattern.compile("^" +
                    "(?=.*[0-9])" +         //tem que conter número
                    "(?=.*[a-z])" +         //tem que letra minúscula
                    "(?=.*[A-Z])" +         //tem que conter letra maiúscula
                    "(?=.*[@#$%^!&+=])" +    //tem que ter caracter especial
                    "(?=\\S+$)" +           //não pode ter espaço
                    ".{8,}" +               //tem que ter pelo menos 8 caracteres
                    "$");

            Pattern validacaoSenhaConfirmada = Pattern.compile("^" +
                    "(?=.*[0-9])" +         //tem que conter número
                    "(?=.*[a-z])" +         //tem que letra minúscula
                    "(?=.*[A-Z])" +         //tem que conter letra maiúscula
                    "(?=.*[@#$%^!&+=])" +    //tem que ter caracter especial
                    "(?=\\S+$)" +           //não pode ter espaço
                    ".{8,}" +               //tem que ter pelo menos 8 caracteres
                    "$");

            //Instancia da classe Matcher para validar se o valor da variável é compatível com o padrão decretado no regex
            Matcher matchSenha = validacaoSenha.matcher(senha);
            Matcher matchNome = validacaoNome.matcher(nome);
            Matcher matchSenhaConfirmada = validacaoSenhaConfirmada.matcher(senhaConfirmada);

            //Resultado em booleano da validação do regex nos campos
            boolean respostaValicaoNome = matchNome.matches();
            boolean validacaoEmail = patternEmail.matcher(editTextEmail.getText().toString().trim()).matches();
            boolean respostaValicaoSenha = matchSenha.matches();
            boolean respostaValicaoSenhaConfirmada = matchSenhaConfirmada.matches();
            boolean validaNomeUsuarioExistente = tabelaUsuario.validarUsuario(nomeUsuario);

            //Instância da classe Usuario para a inserção dos dados do usuário para, posterior, ser inserida no banco de dados - na tabela de usuario.
            usuarioParaCadastrar = new Usuario(0, nome, emailUsuario, nomeUsuario, senha);

            //Verifica se as validações estão corretas e retornam verdadeiro para que o processo de cadastro prossiga
            if (respostaValicaoNome == true) {
                if (validacaoEmail == true) {
                    if (validaNomeUsuarioExistente == false) {
                        if (respostaValicaoSenha == true) {
                            if (respostaValicaoSenhaConfirmada == true) {
                                if (senha.equals(senhaConfirmada)) {
                                    usuarioParaCadastrar = new Usuario(0, nome, emailUsuario, nomeUsuario, senha);
                                    return true;
                                } else {
                                    Toast.makeText(getContext(), "As senhas são diferentes!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getContext(), "Senha fora de padrão!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Senha fora de padrão!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Nome de usuário já existe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "E-MAIL INCORRETO!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Nome fora do padrão!", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }
}