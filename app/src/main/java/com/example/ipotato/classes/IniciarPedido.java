package com.example.ipotato.classes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.AppBarConfigurationKt;

import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ipotato.R;
import com.google.android.material.navigation.NavigationView;

public class IniciarPedido extends Fragment implements View.OnClickListener {

    //Atributos
    NavController navController;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button buttonIniciarPedido;
    AppBarConfiguration mAppBarConfiguration;

    //Construtor
    public IniciarPedido() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_iniciar_pedido, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Instância do Navigation Controller, é responsável pelo gerenciamento das tramitações entre os fragmentos
        navController = Navigation.findNavController(view);

//        drawerLayout = view.findViewById(R.id.drawer_layout);
//        navigationView = view.findViewById(R.id.nav_drawer);
//        navigationView.setNavigationItemSelectedListener(this);
//
        buttonIniciarPedido = view.findViewById(R.id.idButtonIniciarPedido);
        buttonIniciarPedido.setOnClickListener(this);

//        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.cardapio).build();
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//               getParentFragment().getActivity(),
//               drawerLayout,
//               R.string.open,
//               R.string.close
//        );
//
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
    }

    //Evento de clique "padrão" da página de Iniciar Pedido
    @Override
    public void onClick(View view) {
//        navController.navigate(R.id.action_iniciarPedido3_to_cardapio);
        navController.navigate(R.id.action_iniciarPedido3_to_cadastroProduto);
    }

    //Evento de clique dos botões presentes no menu do Navigation Drawer
    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.idButtonNovoPedido:
                navController.navigate(R.id.action_iniciarPedido3_to_cardapio);
                break;
            case R.id.idButtonHistorico:
                Toast.makeText(getContext(), "Teste filha da puta", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idButtonGerenciarGardapio:
                Toast.makeText(getContext(), "Teste filha da puta", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idButtonSair:
                Toast.makeText(getContext(), "Teste filha da puta", Toast.LENGTH_SHORT).show();
//                finish();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }*/

//    Recriei o método de finish, pois o mesmo não é aceito no switch dentro do onNavigationItemSelected
    public void finish(){
        finish();
    }
//
//    Método responsável pela ação de fechar o menu caso o usuário aperte o botão de "voltar" no Android - ao invés de fechar o App ou voltar para alguma outra aba.
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            onBackPressed();
        }
    }
}