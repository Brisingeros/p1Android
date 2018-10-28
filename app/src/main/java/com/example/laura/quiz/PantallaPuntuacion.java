package com.example.laura.quiz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Date;
import java.time.Instant;

public class PantallaPuntuacion extends AppCompatActivity {

    //pantalla final del juego

    Button reintentar, salir;
    TextView puntuacion, tiempo;
    DataBase db;
    UserEntity jugador = null;

    String puntos, totalSec = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_submitpuntuacion);
        Bundle bundle = getIntent().getExtras();
        puntos = bundle.getString("puntuacionFinal");
        totalSec = bundle.getString("totalTime");
        jugador = (UserEntity) bundle.getSerializable("jugador");
        db = DataBase.getDataBase(getApplicationContext());

        reintentar = (Button) findViewById(R.id.reintentar2);
        salir = (Button) findViewById(R.id.salir2);
        puntuacion = (TextView) findViewById(R.id.puntos);
        tiempo = (TextView) findViewById(R.id.tiempofinal);

        puntuacion.setText(puntos);
        tiempo.setText("Tiempo: " + totalSec);

        salir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), Menu.class);
                i.putExtra("jugador", jugador);
                startActivity(i); //salimos al menu principal
                finish();

            }

        });

        reintentar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), Quiz.class);
                i.putExtra("jugador", jugador);
                startActivity(i); //reiniciamos partida
                finish();

            }

        });

    }

    @Override
    protected void onStop(){
        super.onStop();

        java.util.Date d = new java.util.Date();
        CharSequence s = DateFormat.format("MMMM d, yyyy ", d.getTime());
        jugador.setUlt_partida(s.toString());
        if(Integer.valueOf(puntos) > jugador.getPunt_max()){

            jugador.setPunt_max(Integer.valueOf(puntos));

        }
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {

                db.pointsDao().insert(new PointEntity(Opciones.getDifficulty(), jugador.getNombre(), Integer.parseInt(puntos)));

            }

        });

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {

                db.UserDao().updatePartida(jugador.getPunt_max(),jugador.getUlt_partida(),jugador.getId());

            }

        });

    }
}
