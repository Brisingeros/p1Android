package com.example.laura.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Preguntas {

    private static String[] preguntas = {
        "¿Quién disparó al señor Burns?",
        "¿Cuál es el nombre completo de Homer?",
        "¿De qué están hechas la barritas energéticas que toma Homer?",
        "¿Quiénes salvan de la quiebra a la Rasca y Pica?",
        "¿De qué estado norteamericano es el disfraz que le hace Homer a Lisa?",
        "¿Cuántos dedos tienen los Simpsons?",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    };

    private static String[][] respuestas = {

        {"Homer", "Tito Puente", "Maggie", "Lisa"},
        {"Homer Josh Simpson", "Homer Guasón Simpson", "Homer Jeff Simpson", "Homer Jay Simpson"},
        {"Pipas y periódicos chinos", "Chicles de menta", "Trocitos de fruta y libros viejos", "Chocolate y espaguettis"},
        {"Bart y Lisa", "Lester y Eliza", "Krusty y Bob", "Ralph y Vomitron"},
        {"California", "Alabama", "Nevada", "Florida"},
        {"2", "3", "4", "5"},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {}
    };

    private static String[] respuestaCorrecta = {

        "Maggie",
        "Homer Jay Simpson",
        "Pipas y periódicos chinos",
        "Lester y Eliza",
        "Florida",
        "4",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    };

    private static List<Integer> ordenPreguntas;

    public static void startPreguntas(int numPreg){

        ordenPreguntas = new ArrayList<>();

        for(int i = 0; i < numPreg; i++){
            ordenPreguntas.add(i+1);
        }

        Collections.shuffle(ordenPreguntas);
    }

    public static String GetPregunta(int id){

        return preguntas[ordenPreguntas.get(id)];

    }

    public static String GetRespuesta(int idPregunta, int idRespuesta){

        String[] respuestaAct = respuestas[ordenPreguntas.get(idPregunta)];

        return respuestaAct[idRespuesta];

    }

    public static String getRespuestaCorrecta(int idPregunta) {

        return respuestaCorrecta[ordenPreguntas.get(idPregunta)];

    }
}
