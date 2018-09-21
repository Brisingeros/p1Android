package com.example.laura.quiz;

public class PreguntaImagen extends Pregunta {
    public PreguntaImagen(String pregunta, String[] respuesta, String s) {
        super(pregunta, respuesta, s);
    }

    @Override
    public boolean getRespuestaCorrecta(String respuesta) {
        return false;
    }

}
