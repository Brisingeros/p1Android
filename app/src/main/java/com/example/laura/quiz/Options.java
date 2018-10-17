package com.example.laura.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class Options extends AppCompatActivity {

    //opciones del juego

    Spinner spinner;
    Integer[] items = {5,10,15}; //opciones de numero de preguntas con las que jugar

    RadioGroup dificultad; //opciones de tipos de preguntas-respuestas con las que jugar

    Button atras, guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        spinner = (Spinner) findViewById(R.id.numPreg);
        dificultad = (RadioGroup) findViewById(R.id.difficultyRadio);

        atras = (Button) findViewById(R.id.exit);
        guardar = (Button) findViewById(R.id.save);

        atras.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Menu.class)); //salimos al menu sin guardar los cambios que se hayan producido
                finish();

            }

        });

        guardar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) { //guardamos los cambios y salimos al menu

                RadioButton pulsado = (RadioButton) findViewById(dificultad.getCheckedRadioButtonId());

                Opciones.setNumPreg((Integer) spinner.getSelectedItem());
                Opciones.setDifficulty(pulsado.getText().toString().toLowerCase());

                SharedPreferences settings = getSharedPreferences("optionsPreferences",0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putString("dificultad", Opciones.getDifficulty());

                editor.putInt("numPreg", Opciones.getNumPreg());

                editor.commit();

                startActivity(new Intent(getApplicationContext(), Menu.class)); //salir al menu
                finish();

            }

        });

        int posDef = 1;
        int index = 0;
        int numPreg = Opciones.getNumPreg();

        //buscamos la opcion del numero de preguntas que guardo el usuario la ultima vez
        while(index < items.length && items[index] != numPreg){
            index ++;
        }

        if(index < items.length){ //si lo ha encontrado (en principio es siempre true) lo definimos como opcion marcada en el spinner
            posDef = index;
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                R.layout.support_simple_spinner_dropdown_item, items);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setSelection(posDef);

        //configuramos los checkbox a su estado de ultimo guardado
        int idChecked = getResources().getIdentifier(Opciones.getDifficulty(), "id", getPackageName());
        dificultad.check(idChecked);
    }
}
