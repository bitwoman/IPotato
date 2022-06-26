package com.example.ipotato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ipotato.dao.PedidoDAO;
import com.example.ipotato.dao.ProdutoDAO;
import com.example.ipotato.dao.UsuarioDAO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProdutoDAO pDAO = new ProdutoDAO(this);
        pDAO.popularBD();

        PedidoDAO peDAO = new PedidoDAO(this);
        peDAO.popularBD();

        UsuarioDAO uDao = new UsuarioDAO(this);
        uDao.popularTabela();

    }
}