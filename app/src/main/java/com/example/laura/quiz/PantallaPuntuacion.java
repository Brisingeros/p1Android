package com.example.laura.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PantallaPuntuacion extends AppCompatActivity {

    Button reintentar, salir;
    TextView puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pantallapuntuacion);
        Bundle bundle = getIntent().getExtras();
        String puntos = bundle.getString("puntuacionFinal");

        reintentar = (Button) findViewById(R.id.reintentar);
        salir = (Button) findViewById(R.id.salir);
        puntuacion = (TextView) findViewById(R.id.puntos);

        puntuacion.setText(puntos);

        salir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Quiz.class));

            }

        });

        reintentar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Quiz.class)); //SALIR AL MENU

            }

        });

    }
}
