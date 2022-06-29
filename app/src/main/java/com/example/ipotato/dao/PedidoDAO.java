package com.example.ipotato.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ipotato.models.Pedido;
import com.example.ipotato.models.Produto;

import java.util.ArrayList;
import java.util.List;

public class PedidoDAO extends SQLiteOpenHelper {

    //Nome do banco e versão
    public final static String NOME_BANCO = "ipotato";
    public final static int VERSAO_BANCO = 1;

    //Contrutor
    public PedidoDAO(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    //On create para executar o SQL que vai criar as tabelas do banco e as colunas.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //"execSQL" = executar código SQL que for colocado
        sqLiteDatabase.execSQL("CREATE TABLE pedido(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "quantidade REAL, " +
                "metodoPag TEXT," +
                "endereco TEXT, " +
                "FOREIGN KEY (produto_fk) REFERENCES produto(_id))");
    }

    //função de atualização do banco caso já exista uma tabela com o nome duplicado
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Comando SQL para dropar a tabela caso ela já exista
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pedido");
        onCreate(sqLiteDatabase);
    }

    //Função de inserção de registros no banco
    public void inserir(Pedido p){

        //criando variável do tipo SQLiteDatabase e chamando o método getWritableDatabase(), funciona para alterações e não somente leitura
        SQLiteDatabase db = this.getWritableDatabase();

        // criando uma instância de ContentValues que é responsável por fazer o get dos campos, no caso inserir os valores nas
        // respectivas colunas da tabela no banco
        ContentValues insere_valor = new ContentValues();
        insere_valor.put("quantidade", p.getQuantidade());
        insere_valor.put("metodoPag", p.getMetodoPag());
        insere_valor.put("endereco", p.getEndereco());
        insere_valor.put("fk_produto", p.getProduto().getId());

        //Chamando minha variavel db e passando a função de inserção no banco, colocando o nome da tabela e passando os valores
        // coletados através do insere_valores
        db.insert("pedido", null, insere_valor);

        //encerrando a conexão com o banco
//        db.close();
    }

    //Função para atualizar registros no banco
    public void atualizar(String id, Pedido p){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content = new ContentValues();
//        content.put("_id", id);
        //nome das minhas colunas / get da minha classe Pedido
        content.put("quantidade", p.getQuantidade());
        content.put("metodoPag", p.getMetodoPag());
        content.put("endereco", p.getEndereco());
        content.put("fk_produto", p.getProduto().getId());

                                                    //id = vai ser o id que eu to passando por parametro, no caso o id do usuario que
                                                    //esta atualizando
        db.update("pedido", content,"_id=?", new String[]{id});

        db.close();
    }

    //Função de deleção de registros no banco
    public void deletar(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        //Chamando a função de delete, passando a tabela que eu vou mexer e o id do usuário que eu quero deletar
        db.delete("pedido", "_id=?", new String[]{id});

        db.close();
    }

    //Função de listagem de registros no banco
    public List<Pedido> getAllOrders(){
        SQLiteDatabase db = this.getReadableDatabase();

        //Criando um cursor que vai executar uma Query no meu banco, nessa Query eu passo como parametro a
        // tabela a qual vou mexer e os campos que eu quero exibir na listagem
        Cursor cursor = db.query("pedido", new String[] {"_id","quantidade"},
                null,
                null,
                null,
                null,
                null);

        //Se meu cursor acessar o primeiro registro:
        List<Pedido> listP = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                //o getColumnIndexOrThrow está pedindo para eu puxar o valor ou abrir uma exceção na coluna chamada "_id"
                long id = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
                int produto = cursor.getInt(cursor.getColumnIndexOrThrow("produto"));
                int quantidade = cursor.getInt(cursor.getColumnIndexOrThrow("quantidade"));
                String metodoPag = cursor.getString(cursor.getColumnIndexOrThrow("metodoPag"));
                String endereco = cursor.getString(cursor.getColumnIndexOrThrow("endereco"));

//                SQLiteDatabase db2 = this.getWritableDatabase();
//                db2.execSQL("SELECT _id, produto, quantidade, metodoPag, endereco " +
//                        "FROM pedido AS ped" +
//                        "INNER JOIN produto AS prod" +
//                        "ON ped.id = prod.id");

//                Pedido p = new Pedido(
//                        id, produto, quantidade, metodoPag, endereco
//                );
//                listP.add(p);
//                Log.i("Registro: ", id + "" + produto + "" + quantidade + "" + metodoPag + "" + endereco);
            //Enquanto meu cursor se mover para o registro seguinte:
            } while (cursor.moveToNext());
        }
        //Fecho o cursor e o banco
        cursor.close();
        db.close();
        return listP;
    }

    public void popularBD(){
        Produto pr = new Produto(
                0,
                "Ana",
                "Vendendo a Anaju",
                100,
                10
        );
        Pedido p = new Pedido(
                0,
                pr,
                10,
                "Cartao",
                "ruaFodase"
        );
        this.inserir(p);
    }
}
