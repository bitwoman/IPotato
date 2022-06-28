package com.example.ipotato.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ipotato.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends SQLiteOpenHelper {

    //Nome do banco e versão
    public final static String NOME_BANCO = "db1";
    public final static int VERSAO_BANCO = 1;

    //Construtor
    public UsuarioDAO(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    //On create para executar o SQL que vai criar as tabelas do banco e as colunas.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE usuario (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "email TEXT, " +
                "username TEXT, " +
                "pass TEXT);");
    }

    //função de atualização do banco caso já exista uma tabela com o nome duplicado
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(sqLiteDatabase);
    }

    //Função de inserção de registros no banco
    public void inserir(Usuario u) {

        //criando variável do tipo SQLiteDatabase e chamando o método getWritableDatabase(), funciona para alterações e não somente leitura
        SQLiteDatabase db = this.getWritableDatabase();

        // criando uma instância de ContentValues que é responsável por fazer o get dos campos, no caso inserir os valores nas
        // respectivas colunas da tabela no banco
        ContentValues values = new ContentValues();
        values.put("nome", u.getNome());
        values.put("email", u.getEmail());
        values.put("username", u.getUsername());
        values.put("pass", u.getPass());

        //Chamando minha variavel db e passando a função de inserção no banco, colocando o nome da tabela e passando os valores
        // coletados através do insere_valores
        db.insert("usuario", null, values);
        Log.i("DADOS", "Usuário inserido");
//        db.close();
    }

    //Função para atualizar registros no banco
    public void atualizar(String id, Usuario u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //nome das minhas colunas / get da minha classe Pedido
        values.put("nome", u.getNome());
        values.put("email", u.getEmail());
        values.put("username", u.getUsername());
        values.put("pass", u.getPass());
                                                    //id = vai ser o id que eu to passando por parametro, no caso o id do usuario que
                                                    //esta atualizando
        db.update("usuario", values, "id = ?", new String[]{id});
        Log.i("DADOS", "Usuário atualizado");

        db.close();
    }

    public List<Usuario> getAllUsers() {

        //Função de listagem de registros no banco
        SQLiteDatabase db = this.getReadableDatabase();
        //Criando um cursor que vai executar uma Query no meu banco, nessa Query eu passo como parametro a
        // tabela a qual vou mexer e os campos que eu quero exibir na listagem
        Cursor c = db.query(
                "usuario", new String[] {"*"}, null,
                null,
                null,
                null,
                null
        );
        //Se meu cursor acessar o primeiro registro:
        List<Usuario> listU = new ArrayList<Usuario>();
        if (c.moveToFirst()) {
            do {
                //o getColumnIndexOrThrow está pedindo para eu puxar o valor ou abrir uma exceção na coluna chamada "_id"
                long id = c.getLong(c.getColumnIndexOrThrow("_id"));
                String nome = c.getString(c.getColumnIndexOrThrow("nome"));
                String email = c.getString(c.getColumnIndexOrThrow("email"));
                String username = c.getString(c.getColumnIndexOrThrow("username"));
                String pass = c.getString(c.getColumnIndexOrThrow("pass"));
                Usuario u = new Usuario(
                    id, nome, email, username, pass
                );
                listU.add(u);
            //Enquanto meu cursor se mover para o registro seguinte:
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return listU;
    }

    //Função de deleção de registros no banco
    public void deletar(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        //Chamando a função de delete, passando a tabela que eu vou mexer e o id do usuário que eu quero deletar
        db.delete("usuario", "_id = ?", new String[]{String.valueOf(id)});
        Log.i("DADOS", "Usuário deletado");

        db.close();
    }

    public void popularTabela() {
        Usuario u = new Usuario(
            0,
            "Augusto",
            "teste@email.com",
            "augustoUser",
            "12345678"
        );
        this.inserir(u);
        Log.i("inserir", "Inseri um usuário");
    }


    //VALIDAÇÃO DE LOGIN DO USUÁRIO

    //Validação de existência do nome de usuário
    public Boolean validarUsuario(String nomeUsuario){
        SQLiteDatabase db = this.getReadableDatabase();

        //LIKE THIS > "SELECT * FROM usuario WHERE username = nomeUsuario";
        String tabela = "usuario", where = "username = ?";
        String[] colunas = {"username"};
        String[] argumentos = {nomeUsuario};

        Cursor cursor = db.query(tabela, colunas, where, argumentos, null, null, null, null);

        if(cursor.getCount() > 0){
            Log.i("VALIDACAO", "Usuário existe no banco de dados");
            return true;
        }else{
            Log.i("VALIDACAO", "Usuário não existe no banco de dados");
            return false;
        }
    }

    //Validação de existência da senha do usuário
    public Boolean validarLogin(String nomeUsuario, String senhaUsuario){
        SQLiteDatabase db = this.getReadableDatabase();

        //LIKE THIS > "SELECT * FROM usuario WHERE username = nomeUsuario";
        String tabela = "usuario", where = "username = ? and pass = ?";
        String[] colunas = {"username", "pass"};
        String[] argumentos = {nomeUsuario, senhaUsuario};

        Cursor cursor = db.query(tabela, colunas, where, argumentos, null, null, null, null);

        if(cursor.getCount() > 0){
            Log.i("VALIDACAO", "Login inválido");
            return true;
        }else{
            Log.i("VALIDACAO", "Login inválido");
            return false;
        }
    }

}
