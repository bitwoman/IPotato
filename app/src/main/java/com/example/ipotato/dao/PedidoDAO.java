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

public class PedidoDAO extends SQLiteOpenHelper {

    public final static String NOME_BANCO = "bd1";
    public final static int VERSAO_BANCO = 1;

    public PedidoDAO(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE pedido(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "quantidade REAL, " +
                "metodoPag TEXT," +
                "endereco TEXT, " +
                "fk_produto INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pedido");
        onCreate(sqLiteDatabase);
    }

    public void inserir(Pedido p){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues insere_valor = new ContentValues();
        insere_valor.put("quantidade", p.getQuantidade());
        insere_valor.put("metodoPag", p.getMetodoPag());
        insere_valor.put("endereco", p.getEndereco());
        insere_valor.put("fk_produto", p.getProduto().getId());

        db.insert("pedido", null, insere_valor);

//        db.close();
    }

    public void atualizar(String id, Pedido p){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content = new ContentValues();
//        content.put("_id", id);
        //nome das minhas colunas / get da minha classe Pedido
        content.put("quantidade", p.getQuantidade());
        content.put("metodoPag", p.getMetodoPag());
        content.put("endereco", p.getEndereco());
        content.put("fk_produto", p.getProduto().getId());

        //id = vai ser o id que eu to passando por parametro, no caso o id do usuario que esta atualizando
        db.update("pedido", content,"_id=?", new String[]{id});

        db.close();
    }

    public void deletar(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("pedido", "_id=?", new String[]{id});

        db.close();
    }

    public void listar(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("pedido", new String[] {"_id","quantidade"}, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do {
                //o getColumnIndexOrThrow está pedindo para eu puxar o valor ou abrir uma exceção na coluna chamada "_id"
                long id = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
                int produto = cursor.getInt(cursor.getColumnIndexOrThrow("produto"));
                int quantidade = cursor.getInt(cursor.getColumnIndexOrThrow("quantidade"));
                String metodoPag = cursor.getString(cursor.getColumnIndexOrThrow("metodoPag"));
                String endereco = cursor.getString(cursor.getColumnIndexOrThrow("endereco"));

                Log.i("Registro: ", id + "" + produto + "" + quantidade + "" + metodoPag + "" + endereco);
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
        Pedido p = new Pedido(
                0,
                pr,
                10,
                "Cartao",
                "rua fodase"
        );
        this.inserir(p);
    }
}
