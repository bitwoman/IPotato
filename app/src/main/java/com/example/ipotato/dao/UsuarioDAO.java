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

    public final static String NOME_BANCO = "db1";
    public final static int VERSAO_BANCO = 1;

    public UsuarioDAO(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE usuario (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "email TEXT, " +
                "username TEXT, " +
                "pass TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(sqLiteDatabase);
    }

    public void inserir(Usuario u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", u.getNome());
        values.put("email", u.getEmail());
        values.put("username", u.getUsername());
        values.put("pass", u.getPass());
        db.insert("usuario", null, values);
//        db.close();
    }

    public List<Usuario> getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                "usuario", new String[] {"*"}, null,
                null,
                null,
                null,
                null
        );
        List<Usuario> listU = new ArrayList<Usuario>();
        if (c.moveToFirst()) {
            do {
                long id = c.getLong(c.getColumnIndexOrThrow("_id"));
                String nome = c.getString(c.getColumnIndexOrThrow("nome"));
                String email = c.getString(c.getColumnIndexOrThrow("email"));
                String username = c.getString(c.getColumnIndexOrThrow("username"));
                String pass = c.getString(c.getColumnIndexOrThrow("pass"));
                Usuario u = new Usuario(
                    id, nome, email, username, pass
                );
                listU.add(u);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return listU;
    }

    public void deletar(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("usuario", "_id = ?", new String[]{String.valueOf(id)});
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
}
