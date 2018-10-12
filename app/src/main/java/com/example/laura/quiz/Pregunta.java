package com.example.laura.quiz;

import java.util.List;

public abstract class Pregunta {

    //clase padre de las preguntas

    //PASAMOS EL CONTEXTO A ESTA CLASE
    protected String pregunta;
    protected String[] respuestas;
    protected String respuestaCorrecta;

    protected  int layout;

    public Pregunta(String preg, String[] resp, String correct){

        this.pregunta = preg;
        this.respuestas = new String[resp.length];

        for(int i = 0; i < resp.length; i++){
            this.respuestas[i] = resp[i];
        }

        this.respuestaCorrecta = correct;

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
