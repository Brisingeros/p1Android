package com.example.laura.quiz;

public class Preguntas {

    private String[] preguntas = {

        "¿Cuánto quiere Laura a Kevin?"




    };

    private String[][] respuestas = {

            {"Mucho", "Poco", "Nada", "Hasta el infinito"},
            {},
            {},
            {},
            {}

    };

    private String[] respuestaCorrecta = {

            "Hasta el infinito"



    };

    public String GetPregunta(int id){

        return this.preguntas[id];

    }

    public String GetRespuesta(int idPregunta, int idRespuesta){

        return this.respuestas[idPregunta][idRespuesta];

    }

    public String getRespuestaCorrecta(int idPregunta) {

        return this.respuestaCorrecta[idPregunta];

    }
}
