package com.example.laura.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Preguntas {

    private static String[] preguntas = {
        "¿Quién disparó al señor Burns?",
        "¿Cuál es el nombre completo de Homer?",
        "¿De qué están hechas la barritas energéticas que toma Homer?",
        "¿Quiénes salvan de la quiebra a la compañía de Rasca y Pica?",
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
        "¿Quién es Bart Simpson?",
        "¿Cómo se llama este personaje?",
        "¿Cómo se llama este personaje?",
        "¿Cómo se llama este personaje?",
        "¿Quién es el padre de este personaje?",
        "¿A quién ama este personaje?",
        "¿Quién es el jefe de este personaje?",
        "¿Cuál es el mensaje secreto?",
        "¿Cuál es su prenda favorita?",
        "¿Qué es lo que no encuentra Homer?",
        "¿De qué pueblo vienen los Noruegos?"
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
        {"imagen1", "imagen2", "imagen3", "imagen4"},
        {"Skinner", "Apu", "Milhouse", "Homer"},
        {"Bob", "Fausto el portugués", "Simon", "Krusty"},
        {"Sherri", "Terri", "Lisa", "Marge"},
        {"imagen1", "imagen2", "imagen3", "imagen4"},
        {"imagen1", "imagen2", "imagen3", "imagen4"},
        {"imagen1", "imagen2", "imagen3", "imagen4"},
        {"Sadam es bueno","P al cuadrado es Illuminati","Alistate en la marina","Yo soy el Homer malo"},
        {"Jersey caparazón","Chaleco de gorila","Mocasines","Calzón de bisón"},
        {"Cuchara","Cuchillo","Tenedor","Bart"},
        {"Mónaco","Shelbyville","Springfield","Ogdenville"}
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
        "imagen2",
        "Milhouse",
        "Fausto el portugués",
        "Terri",
        "imagen1",
        "imagen3",
        "imagen2",
        "Alistate en la marina",
        "Chaleco de gorila",
        "Cuchara",
        "Ogdenville"

    };

    private static String[] tipoPregunta = {
        "textotexto",
        "textotexto",
        "textotexto",
        "textotexto",
        "textotexto",
        "textotexto",
        "textotexto",
        "textotexto",
        "textotexto",
        "textotexto",
        "textotexto",
        "textotexto",
        "textotexto",
        "textotexto",
        "textotexto",
        "textotexto",
        "textotexto",
        "textoimagen",
        "textoimagen",
        "textoimagen",
        "imagentexto",
        "imagentexto",
        "imagentexto",
        "imagenimagen",
        "imagenimagen",
        "imagenimagen",
        "videotexto",
        "videotexto",
        "videotexto",
        "videotexto"
    };

    private static int[] videoPreguntas = {
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            R.raw.alistate,
            R.raw.mocasines_saltarines,
            R.raw.taca,
            R.raw.achundarnar

    };
    private static int[] imgPreguntas = {
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            -1,
            R.drawable.milhouse,
            R.drawable.fausto,
            R.drawable.terri,
            R.drawable.bart,
            R.drawable.milhouse,
            R.drawable.seymour_skinner_,
            -1,
            -1,
            -1,
            -1

    };
    private static int[][] rutasImg = {
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {-1},
            {R.drawable.joseph_quimby,R.drawable.seymour_skinner_,R.drawable.mr_muntz,R.drawable.bill_gates},
            {R.drawable.toni_el_gordo, R.drawable.wiggum, R.drawable.troy_mcclure, R.drawable.sii},
            {R.drawable.milhouse, R.drawable.bart, R.drawable.nelson, R.drawable.simon},
            {-1},
            {-1},
            {-1},
            {R.drawable.homer, R.drawable.bill_gates, R.drawable.mr_muntz, R.drawable.joseph_quimby},
            {R.drawable.terri, R.drawable.gatubela, R.drawable.lisa, R.drawable.fausto},
            {R.drawable.gatubela, R.drawable.chalmers, R.drawable.bart, R.drawable.fausto},
            {-1},
            {-1},
            {-1},
            {-1}
    };

    private static List<Pregunta> quizPreguntas;


    public static void startPreguntas(){ //le pasamos el contexto desde el menu y lo pasamos a cada constructor junto al json

        //llamamos a la bdd y recuperamos todas las preguntas segun el tipo que hayan marcado los usuarios
        //creamos un obj por cada pregunta que tiene tipo y subobjeto(question)
        //hacemos lista con esos objetos y desordenamos
        //cuando se llame a siguientePregunta() se coge el obj que toque, se crea la Pregunta y se devuelve
        quizPreguntas = new ArrayList<>();

        //cogemos las preguntas que se mostraran en la aplicacion en funcion de las opciones seleccionadas por el usuario
        for(int i = 0; i < preguntas.length; i++){

            if(tipoPregunta[i] == "textotexto") {
                quizPreguntas.add(new PreguntaTextoTexto(preguntas[i], respuestas[i], respuestaCorrecta[i]));
            }

            if(tipoPregunta[i] == "textoimagen" && Opciones.isTextoimagen()) {
                quizPreguntas.add(new PreguntaTextoImagen(preguntas[i], respuestas[i], respuestaCorrecta[i], rutasImg[i]));
            }

            if(tipoPregunta[i] == "imagentexto" && Opciones.isImagentexto()) {
                quizPreguntas.add(new PreguntaImagenTexto(preguntas[i], respuestas[i], respuestaCorrecta[i], imgPreguntas[i]));
            }

            if(tipoPregunta[i] == "imagenimagen" && Opciones.isImagenimagen()) {
               quizPreguntas.add(new PreguntaImagenImagen(preguntas[i], imgPreguntas[i], respuestas[i], respuestaCorrecta[i], rutasImg[i]));
            }

            if(tipoPregunta[i] == "videotexto" && Opciones.isVideotexto()) {
                quizPreguntas.add(new PreguntaVideoTexto(preguntas[i], respuestas[i], respuestaCorrecta[i], videoPreguntas[i]));
            }
        }

        desordenar(); //desordenamos las preguntas para que sea menos monotono el juego

    }

    private static void desordenar(){

        Collections.shuffle(quizPreguntas);

    }

    public static Pregunta GetPregunta(int id){

        return quizPreguntas.get(id);

    }

}
