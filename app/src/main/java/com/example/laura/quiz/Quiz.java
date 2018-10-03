package com.example.laura.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.List;

public class Quiz extends AppCompatActivity {

    Button respuesta1, respuesta2, respuesta3, respuesta4;
    TextView puntuacion, pregunta;
    ImageView imagenPregunta;
    private int miPuntuacion;
    private int numPregunta;
    private int totalPreguntas;

    private Pregunta pregActual;

    private Toast acierto;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        miPuntuacion = 0;
        numPregunta = -1;
        totalPreguntas = Opciones.getNumPreg();

        //toast de acierto personalizado
        acierto = new Toast(this);
        View toast_layout = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.lytLayout));
        acierto.setView(toast_layout);
        acierto.setDuration(Toast.LENGTH_SHORT);

        //inicializamos las que seran las preguntas del juego
        Preguntas.startPreguntas();

        //comenzamos la partida
        SiguientePregunta();

    }

    public void SiguientePregunta(){ //cambiamos de pregunta

        numPregunta++;

        if(numPregunta <= totalPreguntas -1 ) {

            //si el numero actual de pregunta no supera el maximo, la mostramos
            pregActual = Preguntas.GetPregunta(numPregunta);
            render();

        }else{ //si hemos alcanzado el numero maximo de preguntas, terminamos el juego (pantalla de puntuacion)

            Intent in = new Intent(this, PantallaPuntuacion.class);
            in.putExtra("puntuacionFinal", String.valueOf(miPuntuacion)); //pasamos la puntuacion a la pantalla final
            startActivity(in);
            finish();

        }

    }

    public void render(){ //mostramos la pregunta y las respuestas en funcion del tipo

        setContentView(pregActual.getLayout()); //Cada tipo de pregunta tiene definido su propio layout

        //encontramos los 4 botones de respuestas en la interfaz
        respuesta1 = (Button) findViewById(R.id.respuesta1);
        respuesta2 = (Button) findViewById(R.id.respuesta2);
        respuesta3 = (Button) findViewById(R.id.respuesta3);
        respuesta4 = (Button) findViewById(R.id.respuesta4);

        ButtonsConfig(); //configuramos el onClick de los botones

        //encontramos el espacio de la pregunta en la interfaz
        pregunta = (TextView) findViewById(R.id.pregunta);

        //encontramos el espacio de la puntuacion en la interfaz
        puntuacion = (TextView) findViewById(R.id.puntuacion);
        puntuacion.setText("Puntuación: " + miPuntuacion);

        //renderizamos la pantalla en funcion del tipo de pregunta
        List<Group> renderers = pregActual.render();

        for (Group g: renderers) {

            switch (g.getTipo()) {
                case "textview": //pregunta de tipo texto

                    TextView view = (TextView) findViewById(g.getId());
                    view.setText(g.getValue());

                    break;

                case "button":

                    if (g.getId() == R.id.respuesta1) { //primera opcion de respuesta

                        if(g.getIdImg() != -1) { // si el id es distinto de -1, la respuesta es de tipo imagen

                            respuesta1.setBackgroundResource(g.getIdImg());
                            respuesta1.setTextSize(0); //escondemos el texto

                        }else

                            respuesta1.setTextSize(14);

                        respuesta1.setText(g.getValue());

                    } else if (g.getId() == R.id.respuesta2) { //Segunda opcion de respuesta

                        if(g.getIdImg() != -1) {

                            respuesta2.setBackgroundResource(g.getIdImg());
                            respuesta2.setTextSize(0);

                        }else

                            respuesta2.setTextSize(14);

                        respuesta2.setText(g.getValue());

                    } else if (g.getId() == R.id.respuesta3) { //tercera opcion de respuesta

                        if(g.getIdImg() != -1) {

                            respuesta3.setBackgroundResource(g.getIdImg());
                            respuesta3.setTextSize(0);

                        }else

                            respuesta3.setTextSize(14);

                        respuesta3.setText(g.getValue());

                    } else if (g.getId() == R.id.respuesta4) { //cuarta opcion de respuesta

                        if(g.getIdImg() != -1) {

                            respuesta4.setBackgroundResource(g.getIdImg());
                            respuesta4.setTextSize(0);

                        }else

                            respuesta4.setTextSize(14);

                        respuesta4.setText(g.getValue());

                    }

                    break;

                case "imgview": //pregunta de tipo imagen

                    ImageView img = (ImageView) findViewById(g.getId());
                    img.setImageResource(g.getIdImg());

                    break;

                case "video": //pregunta de tipo imagen

                    VideoView video = (VideoView) findViewById(g.getId());
                    String path = "android.resource://" + getPackageName() + "/" + g.getIdImg();
                    video.setVideoPath(path);
                    video.start();

                    break;

            }
        }

    }

    public void ButtonsConfig(){

        respuesta1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(pregActual.getRespuestaCorrecta(respuesta1.getText().toString())){

                    miPuntuacion += 3; //sumamos 3 ptos por acierto
                    acierto.show(); //mostramos el toast personalizado
                    SiguientePregunta(); //cambiamos de pregunta

                }else {

                    GameOver(); //ha fallado

                }
            }

        });

        respuesta2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(pregActual.getRespuestaCorrecta(respuesta2.getText().toString())){

                    miPuntuacion += 3;
                    acierto.show();
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

                    miPuntuacion += 3;
                    acierto.show();
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

                    miPuntuacion += 3;
                    acierto.show();
                    SiguientePregunta();

                }else {

                    GameOver();

                }
            }

        });
    }


    public void GameOver(){

        //mostramos alert con opcion de reiniciar la partida o de continuar (se restan 2 ptos)
        final AlertDialog.Builder alerta = new AlertDialog.Builder(Quiz.this);
        alerta
                .setMessage("¡Has fallado!Puedes volver a empezar o continuar hasta el final.")
                .setCancelable(false)
                .setPositiveButton("Continuar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                miPuntuacion = miPuntuacion - 2 < 0? 0 : (miPuntuacion - 2); //controlamos que la puntuacion no sea negativa
                                SiguientePregunta(); //continuamos la partida actual
                            }
                        }
                )
                .setNegativeButton("Reintentar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                startActivity(new Intent(getApplicationContext(), Quiz.class)); //reiniciamos la partida
                                finish();
                            }
                        }
                );

        AlertDialog dialogo = alerta.create();
        dialogo.show();
    }

}