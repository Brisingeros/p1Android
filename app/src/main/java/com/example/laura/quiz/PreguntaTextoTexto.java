package com.example.laura.quiz;

public class PreguntaTextoTexto extends Pregunta {
    public PreguntaTextoTexto(String pregunta, String[] respuesta, String s, String ruta) {
        super(pregunta, respuesta, s, ruta);
    }

    @Override
    public boolean getRespuestaCorrecta(String respuesta) {
        return respuesta.equals(respuestaCorrecta);
    }

}
