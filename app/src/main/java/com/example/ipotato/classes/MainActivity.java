package com.example.ipotato.classes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ipotato.R;
import com.example.ipotato.dao.PedidoDAO;
import com.example.ipotato.dao.ProdutoDAO;
import com.example.ipotato.dao.UsuarioDAO;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLocker {

    //Atributos
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView = findViewById(R.id.navi_view);
        navigationView.setNavigationItemSelectedListener(this);

        liberarMenu(false);
//        ProdutoDAO pDAO = new ProdutoDAO(this);
//        pDAO.popularBD();
//
//        PedidoDAO peDAO = new PedidoDAO(this);
//        peDAO.popularBD();
//
//        UsuarioDAO uDao = new UsuarioDAO(this);
//        uDao.popularTabela();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.idButtonNovoPedido:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, IniciarPedido.class, null).commit();
                break;
            case R.id.idButtonHistorico:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, HistoricoDePedidos.class, null).commit();
                break;
            case R.id.idButtonGerenciarGardapio:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, Cardapio.class, null).commit();
                break;
            case R.id.idButtonSair:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, Login.class, null).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    private void showFragments(Fragment fragment) {

        FragmentTransaction frag = getSupportFragmentManager().beginTransaction();
        frag.replace(R.id.fragmentContainerView, fragment);
        frag.commit();

    }

    public void liberarMenu(boolean liberar) {
        if (liberar == true) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            getSupportActionBar().show();
            return;
        }
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        getSupportActionBar().hide();
        return;
    }
}