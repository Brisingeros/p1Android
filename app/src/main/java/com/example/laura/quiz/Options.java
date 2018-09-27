package com.example.laura.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class Options extends AppCompatActivity {

    Spinner spinner;
    Integer[] items = {5,10,15};

    CheckBox textoimagen, imagentexto, imagenimagen;

    Button atras, guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        spinner = (Spinner) findViewById(R.id.numPreg);
        textoimagen = (CheckBox) findViewById(R.id.textoimagen);
        imagentexto = (CheckBox) findViewById(R.id.imagentexto);
        imagenimagen = (CheckBox) findViewById(R.id.imagenimagen);

        atras = (Button) findViewById(R.id.exit);
        guardar = (Button) findViewById(R.id.save);

        atras.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Menu.class));
                finish();

            }

        });

        guardar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Opciones.setNumPreg((Integer) spinner.getSelectedItem());
                Opciones.setTextoimagen(textoimagen.isChecked());
                Opciones.setImagentexto(imagentexto.isChecked());
                Opciones.setImagenimagen(imagenimagen.isChecked());

                SharedPreferences settings = getSharedPreferences("optionsPreferences",0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putBoolean("imagenimagen", Opciones.isImagenimagen());
                editor.putBoolean("imagentexto", Opciones.isImagentexto());
                editor.putBoolean("textoimagen", Opciones.isTextoimagen());
                editor.putInt("numPreg", Opciones.getNumPreg());

                editor.commit();

                startActivity(new Intent(getApplicationContext(), Menu.class));
                finish();

            }

        });

        int posDef = 1;
        int index = 0;
        int numPreg = Opciones.getNumPreg();

        while(index < items.length && items[index] != numPreg){
            index ++;
        }

        if(index < items.length){
            posDef = index;
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                R.layout.support_simple_spinner_dropdown_item, items);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setSelection(posDef);

        textoimagen.setChecked(Opciones.isTextoimagen());
        imagentexto.setChecked(Opciones.isImagentexto());
        imagenimagen.setChecked(Opciones.isImagenimagen());
    }
}
