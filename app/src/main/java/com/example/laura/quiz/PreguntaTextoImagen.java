package com.example.laura.quiz;

import java.util.ArrayList;
import java.util.List;



public class PreguntaTextoImagen extends Pregunta {


    public PreguntaTextoImagen(String pregunta, String[] respuesta, String s, int[] rutas) {

        super(pregunta, respuesta, s, rutas);

        layout = R.layout.activity_quiz_textoimagen;
    }

    @Override
    public boolean getRespuestaCorrecta(String respuesta) {
        return respuesta.equals(respuestaCorrecta);
    }

    @Override
    public List<Group> render() {

        List<Group> renderers = new ArrayList<>();

        renderers.add(new Group(R.id.pregunta, pregunta, "textview"));

        renderers.add(new Group(R.id.respuesta1, respuestas[0], "button", imgPreg[0]));
        renderers.add(new Group(R.id.respuesta2, respuestas[1], "button", imgPreg[1]));
        renderers.add(new Group(R.id.respuesta3, respuestas[2], "button", imgPreg[2]));
        renderers.add(new Group(R.id.respuesta4, respuestas[3], "button", imgPreg[3]));

        return renderers;

    }

}
