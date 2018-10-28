package com.example.laura.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
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

    String puntos, totalSec = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_submitpuntuacion);
        Bundle bundle = getIntent().getExtras();
        puntos = bundle.getString("puntuacionFinal");
        totalSec = bundle.getString("totalTime");
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
                startActivity(i); //salimos al menu principal
                finish();

            }

        });

        reintentar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), Quiz.class);
                startActivity(i); //reiniciamos partida
                finish();

            }

        });

    }

    @Override
    protected void onStop(){
        super.onStop();

        java.util.Date d = new java.util.Date();
        final CharSequence s = DateFormat.format("MMMM d, yyyy ", d.getTime());

        SharedPreferences setting = getSharedPreferences("optionsPreferences", 0);

        final String nombreJugador = setting.getString("user_name", "Anónimo");
        final int id = setting.getInt("id", -2);

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {

                db.pointsDao().insert(new PointEntity(Opciones.getDifficulty(), nombreJugador, Integer.parseInt(puntos)));

            }

        });

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {

                db.UserDao().updatePartida(Integer.parseInt(puntos),s.toString(),id);

            }

        });

    }
}
