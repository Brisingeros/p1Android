package com.example.laura.quiz;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;



public class PreguntaTextoImagen extends Pregunta {

    //pregunta de tipo texto con respuestas de tipo imagen

    //menuContext.getResources().getIdentifier("seymour_skinner_", "drawable", menuContext.getPackageName())
    private int[] imgPreg;

    public PreguntaTextoImagen(Context c, String pregunta, String[] respuesta, String s, String[] rutasImg) { //recibe el contexto, se lo pasa a super, parsea el json y pasa lo que corresponda a super

        super(pregunta, respuesta, s);

        layout = R.layout.activity_quiz_textoimagen;

        this.imgPreg = new int[rutasImg.length];
        for(int i = 0; i < rutasImg.length; i++){


            this.imgPreg[i] = c.getResources().getIdentifier(rutasImg[i], "drawable", c.getPackageName());

        }

        //this.imgPreg = rutasImg; //guarda las imagenes de las respuestas

    }

    @Override
    public boolean getRespuestaCorrecta(String respuesta) {
        return respuesta.equals(respuestaCorrecta);
    }

    @Override
    public List<Group> render() {

        List<Group> renderers = new ArrayList<>();

        renderers.add(new Group(R.id.pregunta, pregunta, "textview"));

        renderers.add(new Group(R.id.respuesta1, respuestas[0], "button", imgPreg[0]));
        renderers.add(new Group(R.id.respuesta2, respuestas[1], "button", imgPreg[1]));
        renderers.add(new Group(R.id.respuesta3, respuestas[2], "button", imgPreg[2]));
        renderers.add(new Group(R.id.respuesta4, respuestas[3], "button", imgPreg[3]));

        return renderers;

    }

}
