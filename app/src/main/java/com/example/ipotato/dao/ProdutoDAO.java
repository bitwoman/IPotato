package com.example.ipotato.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ipotato.models.Produto;

public class ProdutoDAO extends SQLiteOpenHelper {

    public final static String NOME_BANCO = "bd1";
    public final static int VERSAO_BANCO = 1;

    public ProdutoDAO(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE produto(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "imagem BLOB, " +
                "preco REAL, " +
                "desconto REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS produto");
        onCreate(sqLiteDatabase);
    }

    public void inserir(Produto p){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues insere_valor = new ContentValues();
        insere_valor.put("nome", p.getNome());
        insere_valor.put("preco", p.getPreco());
        insere_valor.put("desconto", p.getDesconto());

        db.insert("Produto", null, insere_valor);

//        db.close();
    }

    public void atualizar(String id, Produto p){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content = new ContentValues();
//        content.put("_id", id);
        //nome das minhas colunas / get da minha classe Produto
        content.put("nome", p.getNome());
        content.put("preco", p.getPreco());
        content.put("desconto", p.getDesconto());

                                                     //id = vai ser o id que eu to passando por parametro, no caso o id do usuario que esta atualizando
        db.update("Produto", content,"_id=?", new String[]{id});

        db.close();
    }

    public void deletar(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("Produto", "_id=?", new String[]{id});

        db.close();
    }

    public void listar(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("Produto", new String[] {"_id","nome"}, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do {
                //o getColumnIndexOrThrow está pedindo para eu puxar o valor ou abrir uma exceção na coluna chamada "_id"
                long id = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
                double preco = cursor.getDouble(cursor.getColumnIndexOrThrow("preco"));
                Log.i("Registro: ", id + "" + nome + "" + preco);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }

    public void popularBD(){
        Produto pr = new Produto(
                0,
                "Ana",
                100,
                10
        );
        this.inserir(pr);
    }
}
