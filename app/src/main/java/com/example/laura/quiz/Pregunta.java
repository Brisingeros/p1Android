package com.example.laura.quiz;

public abstract class Pregunta {

    protected String pregunta;
    protected String[] respuestas;
    protected String respuestaCorrecta;

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

    public abstract boolean getRespuestaCorrecta(String respuesta);
}
