package com.example.laura.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Quiz extends AppCompatActivity {

    Button respuesta1, respuesta2, respuesta3, respuesta4;
    TextView pregunta, puntuacion;

    //private Preguntas preguntas = new Preguntas();
    private int miPuntuacion = 0;
    private int numPregunta = 0;

    private int totalPreguntas = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //A quitar y dejar en menú
        Preguntas.startPreguntas(false);
        //Preguntas.desordenar();

        respuesta1 = (Button) findViewById(R.id.respuesta1);
        respuesta2 = (Button) findViewById(R.id.respuesta2);
        respuesta3 = (Button) findViewById(R.id.respuesta3);
        respuesta4 = (Button) findViewById(R.id.respuesta4);

        pregunta = (TextView) findViewById(R.id.pregunta);
        puntuacion = (TextView) findViewById(R.id.puntuacion);

        puntuacion.setText("Puntuación: " + miPuntuacion);

        respuesta1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(Preguntas.isRespuestaCorrecta(numPregunta, respuesta1.getText().toString())){

                    miPuntuacion++;
                    puntuacion.setText("Puntuación: " + miPuntuacion);
                    numPregunta++;
                    SiguientePregunta(numPregunta);

                }else {

                    GameOver();

                }
            }

        });

        respuesta2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(Preguntas.isRespuestaCorrecta(numPregunta, respuesta2.getText().toString())){

                    miPuntuacion++;
                    puntuacion.setText("Puntuación: " + miPuntuacion);
                    numPregunta++;
                    SiguientePregunta(numPregunta);

                }else {

                    GameOver();

                }

            }

        });

        respuesta3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(Preguntas.isRespuestaCorrecta(numPregunta, respuesta3.getText().toString())){

                    miPuntuacion++;
                    puntuacion.setText("Puntuación: " + miPuntuacion);
                    numPregunta++;
                    SiguientePregunta(numPregunta);

                }else {

                    GameOver();

                }

            }

        });

        respuesta4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(Preguntas.isRespuestaCorrecta(numPregunta, respuesta4.getText().toString())){

                    miPuntuacion++;
                    puntuacion.setText("Puntuación: " + miPuntuacion);
                    numPregunta++;
                    SiguientePregunta(numPregunta);

                }else {

                    GameOver();

                }

            }

        });

        SiguientePregunta(numPregunta);
    }

    public void SiguientePregunta(int id){

        pregunta.setText(Preguntas.GetPregunta(id));
        respuesta1.setText(Preguntas.GetRespuesta(id,0));
        respuesta2.setText(Preguntas.GetRespuesta(id,1));
        respuesta3.setText(Preguntas.GetRespuesta(id,2));
        respuesta4.setText(Preguntas.GetRespuesta(id,3));

    }

    public void GameOver(){

        final AlertDialog.Builder alerta = new AlertDialog.Builder(Quiz.this);
        alerta
                .setMessage("¡Has fallado!Puedes volver a empezar o continuar hasta el final.")
                .setCancelable(false)
                .setPositiveButton("Continuar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                miPuntuacion-=2;
                                puntuacion.setText("Puntuación: " + miPuntuacion);
                                numPregunta++;
                                SiguientePregunta(numPregunta);
                            }
                        }
                )
                .setNegativeButton("Reintentar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(), Quiz.class));
                            }
                        }
                );

        AlertDialog dialogo = alerta.create();
        dialogo.show();
    }
}
