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
        "¿Cuál es el precio de Maggie?",
        "¿Cómo se llama el perro de la familia?",
        "¿Qué edad tiene Bart?",
        "¿Cómo se llama la madre de Homer?",
        "¿Cuál es el verdadero nombre de Snake?",
        "¿Quién no tiene ningún hijo?",
        "¿Cuál de estos famosos nunca ha aparecido en la serie?",
        "¿A qué se dedicaba Moe antes de tener la taberna?",
        "¿Cómo se llama el cóctel que inventa Homer?",
        "¿Cuál de los siguientes cantantes no ha aparecido en la serie?",
        "¿Cuál es el nombre de la cocinera del colegio?",
        "¿Quién es Joe Quimby?",
        "¿Quién es Troy Mclure?",
        "¿Quién es Bart Simpson?"
    };

    private static String[][] respuestas = {

        {"Homer", "Tito Puente", "Maggie", "Lisa"},
        {"Homer Josh Simpson", "Homer Guasón Simpson", "Homer Jeff Simpson", "Homer Jay Simpson"},
        {"Pipas y periódicos chinos", "Chicles de menta", "Trocitos de fruta y libros viejos", "Chocolate y espaguettis"},
        {"Bart y Lisa", "Lester y Eliza", "Krusty y Bob", "Ralph y Vomitron"},
        {"California", "Alabama", "Nevada", "Florida"},
        {"2", "3", "4", "5"},
        {"240.34", "1347.65", "847.63", "76.98"},
        {"Ayudante de Santa", "Ayudante de Satán", "Ayudante de Jesús", "Ayudante de Deadpool"},
        {"11", "12", "13", "10"},
        {"Mona", "Mino", "Eleine", "Jude"},
        {"Krustofsky", "Hans Moleman", "Joe Quimby", "Chester Turley"},
        {"Patty", "Selma", "Apu", "Ned Flanders"},
        {"Ronaldo Nazario", "Cristiano Ronaldo", "Andrés Iniesta", "Leo Messi"},
        {"Modelo", "Boxeador", "Tenista", "Actor"},
        {"El flameado de Moe", "El flameado de Homer", "El flameado de Peter", "El flameado de Apu"},
        {"Madonna", "Katy Perry", "Lady Gaga", "Britney Spears"},
        {"Anna", "Doris", "Boris", "Trudis"},
        {"imagen1", "imagen2", "imagen3", "imagen4"},
        {"imagen1", "imagen2", "imagen3", "imagen4"},
        {"imagen1", "imagen2", "imagen3", "imagen4"}
    };

    private static String[] respuestaCorrecta = {

        "Maggie",
        "Homer Jay Simpson",
        "Pipas y periódicos chinos",
        "Lester y Eliza",
        "Florida",
        "4",
        "847.63",
        "Ayudante de Santa",
        "10",
        "Mona",
        "Chester Turley",
        "Patty",
        "Andrés Iniesta",
        "Boxeador",
        "El flameado de Moe",
        "Madonna",
        "Doris",
        "imagen1",
        "imagen3",
        "imagen2"
    };

    private static String[] tipoPregunta = {
        "texto",
        "texto",
        "texto",
        "texto",
        "texto",
        "texto",
        "texto",
        "texto",
        "texto",
        "texto",
        "texto",
        "texto",
        "texto",
        "texto",
        "texto",
        "texto",
        "texto",
        "imagen",
        "imagen",
        "imagen"
    };

    private static List<Pregunta> quizPreguntas;

    public static void startPreguntas(boolean img){

        quizPreguntas = new ArrayList<>();

        for(int i = 0; i < preguntas.length; i++){
            if(tipoPregunta[i] == "texto") {
                quizPreguntas.add(new PreguntaTexto(preguntas[i], respuestas[i], respuestaCorrecta[i]));
            } else if(tipoPregunta[i] == "imagen" && img) {
                quizPreguntas.add(new PreguntaImagen(preguntas[i], respuestas[i], respuestaCorrecta[i]));
            }
        }

        desordenar();
    }

    private static void desordenar(){
        Collections.shuffle(quizPreguntas);
    }

    public static String GetPregunta(int id){

        return quizPreguntas.get(id).getPregunta();

    }

    public static String GetRespuesta(int idPregunta, int idRespuesta){


        return quizPreguntas.get(idPregunta).getRespuesta(idRespuesta);

    }

    public static boolean isRespuestaCorrecta(int idPregunta, String respuesta) {

        //return quizPreguntas.get(idPregunta).getRespuestaCorrecta();
        return quizPreguntas.get(idPregunta).getRespuestaCorrecta(respuesta);

    }
}
