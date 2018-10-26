package com.example.laura.quiz;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONException;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz extends AppCompatActivity {

    Button respuesta1, respuesta2, respuesta3, respuesta4;
    TextView puntuacion, pregunta, pregActText, aciertos_fallos;
    Chronometer cronometro;

    private int miPuntuacion;
    private int numPregunta;
    private int totalPreguntas;
    private int aciertos;
    private int fallos;
    private int segundos;
    private long initTime;
    private UserEntity jugador = null;
    private Pregunta pregActual;
    private Toast acierto;
    private Toast fallo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        DataBase db = DataBase.getDataBase(getApplicationContext());
        miPuntuacion = 0;
        numPregunta = -1;
        aciertos = 0;
        fallos = 0;
        segundos = 0;
        totalPreguntas = Opciones.getNumPreg();

        //toast de acierto personalizado
        acierto = new Toast(this);
        View toast_layout = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.lytLayout));
        acierto.setView(toast_layout);
        acierto.setDuration(Toast.LENGTH_SHORT);

        //toast de fallo personalizado
        fallo = new Toast(this);
        View toast_fallo = getLayoutInflater().inflate(R.layout.toast_fallo, (ViewGroup) findViewById(R.id.lytLayout));
        fallo.setView(toast_fallo);
        fallo.setDuration(Toast.LENGTH_SHORT);

        Bundle bundle = getIntent().getExtras();
        this.jugador = (UserEntity) bundle.getSerializable("jugador");

        //inicializamos las que seran las preguntas del juego

        String diffSelected = Opciones.getDifficulty();
        final LiveData pregun = db.questionDao().getQuestionsByDiff(diffSelected);
        Observer quesDao = new Observer() {

            @Override
            public void onChanged(@Nullable Object o) {

                //Durante la primera ejecución de la aplicación en toda su vida en una nueva memoria, es probable que no todas las preguntas se hayan subido a la BDD.
                //Comprobamos esto para evitar un error en el número de preguntas al jugar al quiz.
                if(((List)pregun.getValue()).size() > totalPreguntas){
                    pregun.removeObserver(this);
                    Preguntas.startPreguntas((List<QuestionEntity>) pregun.getValue(), getApplicationContext());
                    initTime = SystemClock.elapsedRealtime();
                    SiguientePregunta();
                }
            }
        };

        pregun.observe(this, quesDao);


        //comenzamos la partida


    }

    public void SiguientePregunta() { //cambiamos de pregunta

        numPregunta++;

        if(numPregunta <= totalPreguntas -1 ) {

            //si el numero actual de pregunta no supera el maximo, la mostramos
            try {
                pregActual = Preguntas.GetPregunta(numPregunta);
                render();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }else{ //si hemos alcanzado el numero maximo de preguntas, terminamos el juego (pantalla de puntuacion)

            System.out.println(segundos);
            miPuntuacion -= (segundos/30);

            Intent in = new Intent(this, PantallaPuntuacion.class);
            in.putExtra("puntuacionFinal", String.valueOf(miPuntuacion)); //pasamos la puntuacion a la pantalla final
            in.putExtra("jugador", this.jugador);
            in.putExtra("totalTime", this.cronometro.getText());
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

        pregActText = (TextView) findViewById(R.id.pregActText);
        pregActText.setText("Pregunta: " + (numPregunta+1) + "/" + totalPreguntas);

        aciertos_fallos = (TextView) findViewById(R.id.aciertos_fallos);
        aciertos_fallos.setText("Aciertos/Fallos: " + aciertos + "/" + fallos);

        cronometro = (Chronometer) findViewById(R.id.cronom);
        cronometro.setBase(initTime);
        cronometro.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                segundos++;
            }
        });
        cronometro.start();

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
                    aciertos++;
                    acierto.show(); //mostramos el toast personalizado
                    SiguientePregunta(); //cambiamos de pregunta

                }else {

                    fallos++;
                    fallo.show(); //mostramos el toast personalizado
                    SiguientePregunta(); //cambiamos de pregunta

                }
            }

        });

        respuesta2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(pregActual.getRespuestaCorrecta(respuesta2.getText().toString())){

                    miPuntuacion += 3;
                    aciertos++;
                    acierto.show();
                    SiguientePregunta();

                }else {

                    fallos++;
                    fallo.show();
                    SiguientePregunta();

                }
            }

        });

        respuesta3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(pregActual.getRespuestaCorrecta(respuesta3.getText().toString())){

                    miPuntuacion += 3;
                    aciertos++;
                    acierto.show();
                    SiguientePregunta();

                }else {

                    fallos++;
                    fallo.show();
                    SiguientePregunta();

                }
            }

        });

        respuesta4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(pregActual.getRespuestaCorrecta(respuesta4.getText().toString())){

                    miPuntuacion += 3;
                    aciertos++;
                    acierto.show();
                    SiguientePregunta();

                }else {

                    fallos++;
                    fallo.show();
                    SiguientePregunta();

                }
            }

        });
    }
}