package com.example.laura.quiz;

import java.util.List;

public abstract class Pregunta {

    protected String pregunta;
    protected String[] respuestas;
    protected String respuestaCorrecta;

    protected String imgPreg;

    protected  int layout;

    public Pregunta(String preg, String[] resp, String correct, String rutaImg){
        this.pregunta = preg;
        this.respuestas = new String[resp.length];

        for(int i = 0; i < resp.length; i++){
            this.respuestas[i] = resp[i];
        }

        this.respuestaCorrecta = correct;
        this.imgPreg = rutaImg;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String[] getRespuestas() {
        return respuestas;
    }

    public String getRespuesta(int i) {
        return respuestas[i];
    }

    public int getLayout() {
        return layout;
    }

    public abstract boolean getRespuestaCorrecta(String respuesta);

    public abstract List<Group> render();
}
