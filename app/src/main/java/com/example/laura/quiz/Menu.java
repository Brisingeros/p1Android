package com.example.laura.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button inicio, opciones, salir;

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        settings = getSharedPreferences("optionsPreferences", 0);

        Opciones.setImagenimagen(settings.getBoolean("imagenimagen", false));
        Opciones.setImagentexto(settings.getBoolean("imagentexto", false));
        Opciones.setTextoimagen(settings.getBoolean("textoimagen", false));
        Opciones.setNumPreg(settings.getInt("numPreg", 10));

        inicio = (Button) findViewById(R.id.play);
        opciones = (Button) findViewById(R.id.options);
        salir = (Button) findViewById(R.id.exit);
        inicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Quiz.class));
                finish();

            }
        });

        opciones.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Options.class));
                finish();

            }
        });

        salir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

    }
}
