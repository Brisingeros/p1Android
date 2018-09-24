package com.example.laura.quiz;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PreguntaTextoTexto extends Pregunta {

    public PreguntaTextoTexto(String pregunta, String[] respuesta, String s) {
        super(pregunta, respuesta, s);

        layout = R.layout.activity_quiz_textotexto;

    }

    @Override
    public boolean getRespuestaCorrecta(String respuesta) {
        return respuesta.equals(respuestaCorrecta);
    }

    @Override
    public List<Group> render(){
        List<Group> renderers = new ArrayList<>();

        renderers.add(new Group(R.id.pregunta, pregunta, "textview"));

        renderers.add(new Group(R.id.respuesta1, respuestas[0], "button"));
        renderers.add(new Group(R.id.respuesta2, respuestas[1], "button"));
        renderers.add(new Group(R.id.respuesta3, respuestas[2], "button"));
        renderers.add(new Group(R.id.respuesta4, respuestas[3], "button"));

        return renderers;
    }

}
