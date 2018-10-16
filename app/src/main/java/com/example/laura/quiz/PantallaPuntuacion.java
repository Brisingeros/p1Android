package com.example.laura.quiz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PantallaPuntuacion extends AppCompatActivity {

    //pantalla final del juego

    Button reintentar, salir;
    TextView puntuacion;
    EditText nombre;
    DataBase db;

    String puntos = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_submitpuntuacion);
        Bundle bundle = getIntent().getExtras();
        puntos = bundle.getString("puntuacionFinal");

        db = DataBase.getDataBase(getApplicationContext());

        reintentar = (Button) findViewById(R.id.reintentar2);
        salir = (Button) findViewById(R.id.salir2);
        puntuacion = (TextView) findViewById(R.id.puntos);
        nombre = (EditText) findViewById(R.id.nombrePlayer);

        puntuacion.setText(puntos);

        salir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Menu.class)); //salimos al menu principal
                finish();

            }

        });

        reintentar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Quiz.class)); //iniciamos una nueva partida
                finish();

            }

        });

    }

    @Override
    protected void onStop(){
        super.onStop();

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {

                System.out.println("Se esta insertando");
                db.pointsDao().insert(new PointEntity(nombre.getText().toString(), Integer.parseInt(puntos)));

            }

        });

    }
}
