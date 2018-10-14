package com.example.laura.quiz;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Preguntas {

    public static List<QuestionEntity> preguntasSeleccionadas = new ArrayList<>();
    private static Context context;


    public static void startPreguntas(List<QuestionEntity> questionDaos, Context con) { //le pasamos el contexto desde el menu y lo pasamos a cada constructor junto al json

        preguntasSeleccionadas = questionDaos;
        context = con;

        desordenar();

    }

    public static List<String> getTipos(){ //le pasamos el contexto desde el menu y lo pasamos a cada constructor junto al json

        List<String> tipos = new ArrayList<>();
        tipos.add("textotexto");

        if(Opciones.isImagenimagen())
            tipos.add("imagenimagen");
        if(Opciones.isImagentexto())
            tipos.add("imagentexto");
        if(Opciones.isTextoimagen())
            tipos.add("textoimagen");
        if(Opciones.isVideotexto())
            tipos.add("videotexto");

        return tipos;

    }

    private static void desordenar(){

        Collections.shuffle(preguntasSeleccionadas);

    }

    public static Pregunta GetPregunta(int id) throws JSONException{

        QuestionEntity q = preguntasSeleccionadas.get(id);
        JSONObject data = new JSONObject(q.getData());
        Pregunta pActual = null;

        System.out.println("TIPO: " + q.getType());
        if(q.getType().equals("textotexto")) {

            pActual = new PreguntaTextoTexto(context, data.getString("pregunta"), GenerarArray(data.getJSONArray("respuestas")) , data.getString("respuesta_correcta"));
        }

        if(q.getType().equals("textoimagen") && Opciones.isTextoimagen()) {
            pActual = new PreguntaTextoImagen(context, data.getString("pregunta"), GenerarArray(data.getJSONArray("respuestas")), data.getString("respuesta_correcta"), GenerarArray(data.getJSONObject("renderizable").getJSONArray("img_resp")));
        }

        if(q.getType().equals("imagentexto") && Opciones.isImagentexto()) {
            pActual = new PreguntaImagenTexto(context, data.getString("pregunta"), GenerarArray(data.getJSONArray("respuestas")), data.getString("respuesta_correcta"), data.getJSONObject("renderizable").getString("img_preg"));
        }

        if(q.getType().equals("imagenimagen") && Opciones.isImagenimagen()) {
            pActual = new PreguntaImagenImagen(context, data.getString("pregunta"), data.getJSONObject("renderizable").getString("img_preg"), GenerarArray(data.getJSONArray("respuestas")), data.getString("respuesta_correcta"), GenerarArray(data.getJSONObject("renderizable").getJSONArray("img_resp")));
        }

        if(q.getType().equals("videotexto") && Opciones.isVideotexto()) {
            pActual = new PreguntaVideoTexto(context, data.getString("pregunta"), GenerarArray(data.getJSONArray("respuestas")),  data.getString("respuesta_correcta"), data.getJSONObject("renderizable").getString("video_preg"));
        }

        System.out.println(pActual);

        return pActual;

    }

    private static String[] GenerarArray(JSONArray array){

        String[] ret = new String[array.length()];

        for(int i = 0; i < array.length(); i++){

            try {

                ret[i] = array.getString(i);

            } catch (JSONException e) {

                e.printStackTrace();

            }
        }

        return ret;
    }

}
