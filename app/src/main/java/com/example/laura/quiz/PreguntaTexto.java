package com.example.laura.quiz;

public class PreguntaTexto extends Pregunta {
    public PreguntaTexto(String pregunta, String[] respuesta, String s) {
        super(pregunta, respuesta, s);
    }

    @Override
    public boolean getRespuestaCorrecta(String respuesta) {
        return respuesta.equals(respuestaCorrecta);
    }

}
