package com.example.laura.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Users_list extends AppCompatActivity {

    ArrayList<User> usuarios;
    RecyclerView listaRenderizable;
    Button nuevoPerfil, salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_item);

        this.nuevoPerfil = (Button) findViewById(R.id.nuevo_perfil);
        this.salir = (Button) findViewById(R.id.salir_perfiles);
        this.listaRenderizable = (RecyclerView) findViewById(R.id.listUsers);
        this.listaRenderizable.setLayoutManager(new LinearLayoutManager(this));

        this.usuarios = new ArrayList<>();

        this.usuarios.add(new User("Laura","120","17/10/2018","2","bart"));
        this.usuarios.add(new User("Kevin","120","12/10/2018","4","fausto"));

        UsersAdapter adaptador = new UsersAdapter(getApplicationContext(),this.usuarios);
        this.listaRenderizable.setAdapter(adaptador);


        this.nuevoPerfil.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Nuevo_perfil.class));
                finish();

            }
        });

        this.salir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Menu.class));
                finish();

            }
        });
    }
}
