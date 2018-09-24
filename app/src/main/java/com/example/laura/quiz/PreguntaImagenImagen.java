package com.example.laura.quiz;

import java.util.ArrayList;
import java.util.List;

public class PreguntaImagenImagen extends Pregunta{

    private int imgPregunta;
    private int[] imgResp;
    public PreguntaImagenImagen(String preguntaTexto, int preguntaImagen,String[] respuesta, String s, int[] rutas) {

        super(preguntaTexto, respuesta, s);

        layout = R.layout.activity_quiz_imagenimagen;
        imgPregunta = preguntaImagen;
        imgResp = rutas;

    }

    @Override
    public boolean getRespuestaCorrecta(String respuesta) {
        return respuesta.equals(respuestaCorrecta);
    }

    @Override
    public List<Group> render(){

        List<Group> renderers = new ArrayList<>();

        renderers.add(new Group(R.id.pregunta, pregunta, "textview"));
        renderers.add(new Group(R.id.imagenPreg, imgPregunta, "imgview"));
        renderers.add(new Group(R.id.respuesta1, respuestas[0], "button", imgResp[0]));
        renderers.add(new Group(R.id.respuesta2, respuestas[1], "button", imgResp[1]));
        renderers.add(new Group(R.id.respuesta3, respuestas[2], "button", imgResp[2]));
        renderers.add(new Group(R.id.respuesta4, respuestas[3], "button", imgResp[3]));

        return renderers;

    }
}
