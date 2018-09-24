package com.example.laura.quiz;

import java.util.ArrayList;
import java.util.List;

public class PreguntaImagenTexto extends Pregunta{

    private int rutaImg;

    public  PreguntaImagenTexto(String pregunta, String[] respuesta, String s, int ruta){
        super(pregunta, respuesta, s);

        rutaImg = ruta;

        layout = R.layout.activity_quiz_imagentexto;
    }

    @Override
    public boolean getRespuestaCorrecta(String respuesta) {
        return respuesta.equals(respuestaCorrecta);
    }

    @Override
    public List<Group> render() {
        List<Group> renderers = new ArrayList<>();

        renderers.add(new Group(R.id.pregunta, pregunta, "textview"));

        renderers.add(new Group(R.id.respuesta1, respuestas[0], "button"));
        renderers.add(new Group(R.id.respuesta2, respuestas[1], "button"));
        renderers.add(new Group(R.id.respuesta3, respuestas[2], "button"));
        renderers.add(new Group(R.id.respuesta4, respuestas[3], "button"));

        renderers.add(new Group(R.id.imagenPreg, "","imgview", rutaImg));

        return renderers;
    }
}
