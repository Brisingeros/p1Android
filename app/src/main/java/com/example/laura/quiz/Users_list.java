package com.example.laura.quiz;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Users_list extends AppCompatActivity {

    ArrayList<UserEntity> usuarios;
    ArrayList<String> nombres;
    UserEntity editado = null;

    RecyclerView listaRenderizable;
    TextView info_borrar;
    Button nuevoPerfil, salir, terminar_borrado, borrar, editar;
    UsersAdapter adaptador;
    Boolean borrando = false;
    Boolean editando = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_item);

        this.info_borrar = (TextView) findViewById(R.id.mens_eliminar);
        this.nuevoPerfil = (Button) findViewById(R.id.nuevo_perfil);
        this.borrar = (Button) findViewById(R.id.borrar_perfil);
        this.editar = (Button) findViewById(R.id.editar_perfil);
        this.terminar_borrado = (Button) findViewById(R.id.salir_borrar);
        this.salir = (Button) findViewById(R.id.salir_perfiles);
        this.listaRenderizable = (RecyclerView) findViewById(R.id.listUsers);
        this.listaRenderizable.setLayoutManager(new LinearLayoutManager(this));

        this.usuarios = new ArrayList<>();
        this.nombres = new ArrayList<>();


        //AÑADIR USUARIOS A LA LISTA RENDERIZABLE
        getUsers();

        this.borrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                borrando = true;
                salir.setVisibility(View.INVISIBLE);
                editar.setVisibility(View.INVISIBLE);
                borrar.setVisibility(View.INVISIBLE);
                nuevoPerfil.setVisibility(View.INVISIBLE);
                info_borrar.setText("Pulsa en el perfil a eliminar");
                info_borrar.setVisibility(View.VISIBLE);
                terminar_borrado.setVisibility(View.VISIBLE);

            }
        });

        this.editar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                editando = true;
                editar.setVisibility(View.INVISIBLE);
                salir.setVisibility(View.INVISIBLE);
                borrar.setVisibility(View.INVISIBLE);
                nuevoPerfil.setVisibility(View.INVISIBLE);
                info_borrar.setText("Pulsa en el perfil a editar");
                info_borrar.setVisibility(View.VISIBLE);
                terminar_borrado.setVisibility(View.VISIBLE);

            }
        });

        this.terminar_borrado.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                borrando = false;
                editando = false;
                salir.setVisibility(View.VISIBLE);
                borrar.setVisibility(View.VISIBLE);
                editar.setVisibility(View.VISIBLE);
                nuevoPerfil.setVisibility(View.VISIBLE);
                info_borrar.setVisibility(View.INVISIBLE);
                terminar_borrado.setVisibility(View.INVISIBLE);

            }
        });

        this.nuevoPerfil.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent in = new Intent(getApplicationContext(), Nuevo_perfil.class);
                in.putExtra("usuarios", nombres);
                startActivity(in);
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

    private void setAdaptador(){

        adaptador = new UsersAdapter(getApplicationContext(),usuarios);
        listaRenderizable.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                final UserEntity seleccion = usuarios.get(listaRenderizable.getChildAdapterPosition(v));

                if(borrando){

                    eliminarUsuario(seleccion);

                }else if (editando){

                    Intent in = new Intent(getApplicationContext(), Nuevo_perfil.class);
                    in.putExtra("usuario_editado", seleccion);
                    startActivity(in);
                    finish();


                }else{

                    //LOGIN
                    UserEntity usuario = seleccion == null?new UserEntity():seleccion;
                    Intent in = new Intent(getApplicationContext(), Menu.class);
                    in.putExtra("jugador", usuario);
                    startActivity(in);
                    finish();

                }

                System.out.println(seleccion.getNombre());

            }
        });


    }
    private void getUsers(){

        final LiveData u = DataBase.getDataBase(getApplicationContext()).UserDao().getUsers();
        Observer userDao = new Observer() {

            @Override
            public void onChanged(@Nullable Object o) {

                usuarios = (ArrayList<UserEntity>) u.getValue();
                nombres = new ArrayList<>();

                for(UserEntity u : usuarios){

                    nombres.add(u.getNombre());

                }

                setAdaptador();

            }
        };

        u.observe(this, userDao);

    }

    private void eliminarUsuario(final UserEntity user){

        final AlertDialog.Builder alerta = new AlertDialog.Builder(Users_list.this);
        alerta
                .setMessage("¿Seguro que quieres borrar el perfil de " + user.getNombre() + "?")
                .setCancelable(false)
                .setPositiveButton("Confirmar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                usuarios.remove(user);
                                setAdaptador();
                                AsyncTask.execute(new Runnable() {

                                    @Override
                                    public void run() {

                                        DataBase.getDataBase(getApplicationContext()).UserDao().deleteUser(user.getNombre());

                                    }
                                });
                                dialogInterface.dismiss();

                            }
                        }
                )
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();

                            }
                        }
                );

        AlertDialog dialogo = alerta.create();
        dialogo.show();
    }
}
