package com.example.ipotato.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ipotato.R;
import com.example.ipotato.models.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends SQLiteOpenHelper {

    //Nome do banco e versão
    public final static String NOME_BANCO = "ipotato";
    public final static int VERSAO_BANCO = 1;

    //Construtor
    public ProdutoDAO(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    //On create para executar o SQL que vai criar as tabelas do banco e as colunas.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE produto(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "descricao TEXT, " +
                "imagem BLOB, " +
                "preco REAL, " +
                "desconto REAL);");
    }

    //função de atualização do banco caso já exista uma tabela com o nome duplicado
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS produto");
        onCreate(sqLiteDatabase);
    }

    //Função de inserção de registros no banco
    public void inserir(Produto p){

        //criando variável do tipo SQLiteDatabase e chamando o método getWritableDatabase(), funciona para alterações e não somente leitura
        SQLiteDatabase db = this.getWritableDatabase();

        // criando uma instância de ContentValues que é responsável por fazer o get dos campos, no caso inserir os valores nas
        // respectivas colunas da tabela no banco
        ContentValues insere_valor = new ContentValues();
        insere_valor.put("nome", p.getNome());
        insere_valor.put("descricao", p.getDescricao());
        insere_valor.put("preco", p.getPreco());
        insere_valor.put("desconto", p.getDesconto());

        //Chamando minha variavel db e passando a função de inserção no banco, colocando o nome da tabela e passando os valores
        // coletados através do insere_valores
        db.insert("Produto", null, insere_valor);

//        db.close();
    }

    //Função para atualizar registros no banco
    public void atualizar(String id, Produto p){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content = new ContentValues();
//        content.put("_id", id);
        //nome das minhas colunas / get da minha classe Produto
        content.put("nome", p.getNome());
        content.put("descricao", p.getDescricao());
        content.put("preco", p.getPreco());
        content.put("desconto", p.getDesconto());

                                                     //id = vai ser o id que eu to passando por parametro, no caso o id do usuario que
                                                    //esta atualizando
        db.update("Produto", content,"_id=?", new String[]{id});

        //encerrando a conexão com o banco
        db.close();
    }

    //Função de deleção de registros no banco
    public void deletar(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        //Chamando a função de delete, passando a tabela que eu vou mexer e o id do usuário que eu quero deletar
        db.delete("Produto", "_id=?", new String[]{id});

        db.close();
    }

    //Função de listagem de registros no banco
    public List<Produto> getAllProducts(){
        SQLiteDatabase db = this.getReadableDatabase();

        //Criando um cursor que vai executar uma Query no meu banco, nessa Query eu passo como parametro a
        // tabela a qual vou mexer e os campos que eu quero exibir na listagem
        Cursor cursor = db.query("Produto", new String[] {"_id","nome", "descricao","preco", "desconto"},
                null,
                null,
                null,
                null,
                null);

        //Se meu cursor acessar o primeiro registro:
        List<Produto> listP = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                //o getColumnIndexOrThrow está pedindo para eu puxar o valor ou abrir uma exceção na coluna chamada "_id"
                long id = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao"));
                double preco = cursor.getDouble(cursor.getColumnIndexOrThrow("preco"));
                double desconto = cursor.getDouble(cursor.getColumnIndexOrThrow("desconto"));

                Produto p = new Produto(id, R.drawable.batata_assada, nome, descricao, preco, desconto);
                listP.add(p);

//                Log.i("Registro: ", id + "" + nome + "" + preco);
            //Enquanto meu cursor se mover para o registro seguinte:
            } while (cursor.moveToNext());
            //Fecho o cursor e o banco
            cursor.close();
        }
//        db.close();
        return listP;
    }

    public void popularBD(){
        Produto pr = new Produto(
                0,
                R.drawable.batata_assada,
                "Ana",
                "Vendendo a Anaju",
                100,
                10
        );
        this.inserir(pr);
    }


    public List<Produto> produtosMockados() {
        List<Produto> listProduto = new ArrayList<Produto>();
        listProduto.add(new Produto(0, R.drawable.batata_assada,"sashimi", "Lorem ipsum", 10.50, 0));
        listProduto.add(new Produto(0, R.drawable.batata_assada,"sashimi", "Lorem ipsum", 10.50, 0));
        listProduto.add(new Produto(0, R.drawable.batata_assada,"sashimi", "Lorem ipsum", 10.50, 0));
        listProduto.add(new Produto(0, R.drawable.batata_assada,"sashimi", "Lorem ipsum", 10.50, 0));
        listProduto.add(new Produto(0, R.drawable.batata_assada,"sashimi", "Lorem ipsum", 10.50, 0));
        listProduto.add(new Produto(0, R.drawable.batata_assada,"sashimi", "Lorem ipsum", 10.50, 0));
        listProduto.add(new Produto(0, R.drawable.batata_assada,"sashimi", "Lorem ipsum", 10.50, 0));
        listProduto.add(new Produto(0, R.drawable.batata_assada,"sashimi", "Lorem ipsum", 10.50, 0));
        listProduto.add(new Produto(0, R.drawable.batata_assada,"sashimi", "Lorem ipsum", 10.50, 0));
        listProduto.add(new Produto(0, R.drawable.batata_assada,"sashimi", "Lorem ipsum", 10.50, 0));
        listProduto.add(new Produto(0, R.drawable.batata_assada,"sashimi", "Lorem ipsum", 10.50, 0));
        return listProduto;
    }
}
