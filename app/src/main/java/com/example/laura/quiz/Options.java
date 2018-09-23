package com.example.laura.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

public class Options extends AppCompatActivity {

    Spinner spinner;

    CheckBox textoimagen, imagentexto, imagenimagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Integer[] items = {5,10,15};

        spinner = (Spinner) findViewById(R.id.numPreg);
        textoimagen = (CheckBox) findViewById(R.id.textoimagen);
        imagentexto = (CheckBox) findViewById(R.id.imagentexto);
        imagenimagen = (CheckBox) findViewById(R.id.imagenimagen);


        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                R.layout.support_simple_spinner_dropdown_item, items);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        textoimagen.setChecked(Opciones.isTextoimagen());
        imagentexto.setChecked(Opciones.isImagentexto());
        imagenimagen.setChecked(Opciones.isImagenimagen());
    }

    @Override
    protected void onStop() {
        super.onStop();

        Opciones.setNumPreg((Integer) spinner.getSelectedItem());
        Opciones.setTextoimagen(textoimagen.isChecked());
        Opciones.setImagentexto(imagentexto.isChecked());
        Opciones.setImagenimagen(imagenimagen.isChecked());
    }
}
