package com.example.laura.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Quiz extends AppCompatActivity {

    Button respuesta1, respuesta2, respuesta3, respuesta4;
    TextView puntuacion, pregunta;
    ImageView imagenPregunta;
    private int miPuntuacion = 0;
    private int numPregunta = -1;
    private int totalPreguntas = 15;

    private Pregunta pregActual;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Preguntas.startPreguntas();

        SiguientePregunta();

    }

    public void SiguientePregunta(){

        numPregunta++;

        if(numPregunta <= totalPreguntas-1) {

            pregActual = Preguntas.GetPregunta(numPregunta);
            render();

        }else{

            Intent in = new Intent(this, PantallaPuntuacion.class);
            in.putExtra("puntuacionFinal", String.valueOf(miPuntuacion));
            startActivity(in);

        }

    }

    public void render(){

        setContentView(pregActual.getLayout());

        respuesta1 = (Button) findViewById(R.id.respuesta1);
        respuesta2 = (Button) findViewById(R.id.respuesta2);
        respuesta3 = (Button) findViewById(R.id.respuesta3);
        respuesta4 = (Button) findViewById(R.id.respuesta4);

        ButtonsConfig();

        pregunta = (TextView) findViewById(R.id.pregunta);

        puntuacion = (TextView) findViewById(R.id.puntuacion);

        puntuacion.setText("Puntuación: " + (miPuntuacion < 0? 0: miPuntuacion));

        List<Group> renderers = pregActual.render();

        for (Group g: renderers) {

            switch (g.getTipo()) {
                case "textview":

                    TextView view = (TextView) findViewById(g.getId());
                    view.setText(g.getValue());

                    break;

                case "button":

                    if (g.getId() == R.id.respuesta1) {


                        if(g.getIdImg() != -1) {

                            respuesta1.setBackgroundResource(g.getIdImg());
                            respuesta1.setTextSize(0);

                        }else

                            respuesta1.setTextSize(14);

                        respuesta1.setText(g.getValue());

                    } else if (g.getId() == R.id.respuesta2) {

                        if(g.getIdImg() != -1) {

                            respuesta2.setBackgroundResource(g.getIdImg());
                            respuesta2.setTextSize(0);

                        }else

                            respuesta2.setTextSize(14);

                        respuesta2.setText(g.getValue());

                    } else if (g.getId() == R.id.respuesta3) {

                        if(g.getIdImg() != -1) {

                            respuesta3.setBackgroundResource(g.getIdImg());
                            respuesta3.setTextSize(0);

                        }else

                            respuesta3.setTextSize(14);

                        respuesta3.setText(g.getValue());

                    } else if (g.getId() == R.id.respuesta4) {

                        if(g.getIdImg() != -1) {

                            respuesta4.setBackgroundResource(g.getIdImg());
                            respuesta4.setTextSize(0);

                        }else

                            respuesta4.setTextSize(14);

                        respuesta4.setText(g.getValue());

                    }

                    break;

                case "imgview":

                    //imagenPregunta.setImageDrawable(g.getId());

                    break;
            }
        }

    }

    public void ButtonsConfig(){

        respuesta1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(pregActual.getRespuestaCorrecta(respuesta1.getText().toString())){

                    miPuntuacion++;
                    SiguientePregunta();

                }else {

                    GameOver();

                }
            }

        });

        respuesta2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(pregActual.getRespuestaCorrecta(respuesta2.getText().toString())){

                    miPuntuacion++;
                    SiguientePregunta();

                }else {

                    GameOver();

                }
            }

        });

        respuesta3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(pregActual.getRespuestaCorrecta(respuesta3.getText().toString())){

                    miPuntuacion++;
                    SiguientePregunta();

                }else {

                    GameOver();

                }
            }

        });

        respuesta4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(pregActual.getRespuestaCorrecta(respuesta4.getText().toString())){

                    miPuntuacion++;
                    SiguientePregunta();

                }else {

                    GameOver();

                }
            }

        });
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
                                SiguientePregunta();
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