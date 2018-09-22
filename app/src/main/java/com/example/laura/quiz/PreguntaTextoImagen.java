package com.example.laura.quiz;

import java.util.List;

public class PreguntaTextoImagen extends Pregunta {
    public PreguntaTextoImagen(String pregunta, String[] respuesta, String s, String ruta) {
        super(pregunta, respuesta, s, ruta);
    }

    @Override
    public boolean getRespuestaCorrecta(String respuesta) {
        return false;
    }

    @Override
    public List<Group> render() {
        return null;
    }

}
