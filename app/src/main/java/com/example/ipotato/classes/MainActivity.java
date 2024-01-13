package com.example.ipotato.classes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ipotato.R;
import com.example.ipotato.adapters.ProdutoAdapter;
import com.example.ipotato.models.Produto;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLocker {

    //Atributos
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    private ListView listViewContainerFragment;
//    List<Produto> ListaGameNoCarrinho = new ArrayList<Game>();
//    TextView quantidadeNoCarrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        listViewContainerFragment = findViewById(R.id.idFragmentContainerView);
//        Ele vai puxar a lista de produtos por conta do método estático criado na classe Produto
//        ProdutoAdapter adaptador = new ProdutoAdapter(this, Produto.getProdutos());
//        listViewContainerFragment.setAdapter(adaptador);

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
                getSupportFragmentManager().beginTransaction().replace(R.id.idFragmentContainerView, IniciarPedido.class, null).commit();
                break;
            case R.id.idButtonHistorico:
                getSupportFragmentManager().beginTransaction().replace(R.id.idFragmentContainerView, HistoricoDePedidos.class, null).commit();
                break;
            case R.id.idButtonGerenciarGardapio:
                getSupportFragmentManager().beginTransaction().replace(R.id.idFragmentContainerView, GerenciarCardapio.class, null).commit();
                break;
            case R.id.idButtonSair:
                getSupportFragmentManager().beginTransaction().replace(R.id.idFragmentContainerView, Login.class, null).commit();
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
        frag.replace(R.id.idFragmentContainerView, fragment);
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