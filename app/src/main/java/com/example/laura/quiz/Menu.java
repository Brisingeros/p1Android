package com.example.laura.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    Button inicio, opciones, salir, hall, select_perfil, anonimo;
    TextView titulo, userName;
    ImageView userImage;
    UserEntity jugador = null;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        DataBase db = DataBase.getDataBase(getApplicationContext());

        settings = getSharedPreferences("optionsPreferences", 0);

        Opciones.setDifficulty(settings.getString("dificultad", "easy"));

        Opciones.setNumPreg(settings.getInt("numPreg", 10));

        titulo = (TextView)  findViewById(R.id.titulo);
        userImage = (ImageView) findViewById(R.id.userImage);
        userName = (TextView) findViewById(R.id.userName);
        inicio = (Button) findViewById(R.id.play);
        opciones = (Button) findViewById(R.id.options);
        salir = (Button) findViewById(R.id.exit);
        hall = (Button) findViewById(R.id.hall);
        select_perfil = (Button) findViewById(R.id.seleccion_perfil);
        anonimo = (Button) findViewById(R.id.partida_anonima);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            jugador = (UserEntity) bundle.getSerializable("jugador");
            if(jugador != null)
                mostrarMenu();
        }

        hall.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), Hall.class);
                i.putExtra("jugador", jugador);
                startActivity(i);
                finish();

            }
        });

        select_perfil.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Users_list.class));
                finish();

            }
        });

        anonimo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                jugador = new UserEntity();
                mostrarMenu();

            }
        });

        inicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), Quiz.class);
                i.putExtra("jugador",jugador);
                startActivity(i);
                finish();

            }
        });

        opciones.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), Options.class);
                i.putExtra("jugador", jugador);
                startActivity(i);
                finish();

            }
        });

        salir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                salir();

            }
        });

    }

    private void mostrarMenu(){

        userName.setText(jugador.getNombre());
        userName.setVisibility(View.VISIBLE);
        setImage(jugador.getPath_foto());
        userImage.setVisibility(View.VISIBLE);
        select_perfil.setVisibility(View.INVISIBLE);
        anonimo.setVisibility(View.INVISIBLE);
        titulo.setVisibility(View.INVISIBLE);

        inicio.setVisibility(View.VISIBLE);
        opciones.setVisibility(View.VISIBLE);
        hall.setVisibility(View.VISIBLE);
        salir.setVisibility(View.VISIBLE);

    }

    private void setImage(String path){

        int img = getApplicationContext().getResources().getIdentifier(path, "drawable", getApplicationContext().getPackageName());

        System.out.println("path: " + img);

        if(img == 0){

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            this.userImage.setImageBitmap(BitmapFactory.decodeFile(path, options));

        }else{

            this.userImage.setImageResource(img);
        }
    }
    private void cambiarPerfil(){

        select_perfil.setVisibility(View.VISIBLE);
        anonimo.setVisibility(View.VISIBLE);
        titulo.setVisibility(View.VISIBLE);

        userName.setVisibility(View.INVISIBLE);
        userImage.setVisibility(View.INVISIBLE);
        inicio.setVisibility(View.INVISIBLE);
        opciones.setVisibility(View.INVISIBLE);
        hall.setVisibility(View.INVISIBLE);
        salir.setVisibility(View.INVISIBLE);

    }

    private void salir(){

        final AlertDialog.Builder alerta = new AlertDialog.Builder(Menu.this);
        alerta
                .setMessage("¿Quieres salir de la aplicación o cambiar de perfil?")
                .setCancelable(false)
                .setPositiveButton("Cambiar de perfil",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();
                                cambiarPerfil();

                            }
                        }
                )
                .setNegativeButton("Salir de la aplicación",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }
                        }
                );

        AlertDialog dialogo = alerta.create();
        dialogo.show();
    }
}
