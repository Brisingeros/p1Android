package com.example.laura.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button inicio, opciones, salir, hall;

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        DataBase db = DataBase.getDataBase(getApplicationContext());

        settings = getSharedPreferences("optionsPreferences", 0);

        Opciones.setDifficulty(settings.getString("dificultad", "easy"));

        Opciones.setNumPreg(settings.getInt("numPreg", 10));

        inicio = (Button) findViewById(R.id.play);
        opciones = (Button) findViewById(R.id.options);
        salir = (Button) findViewById(R.id.exit);
        hall = (Button) findViewById(R.id.hall);

        hall.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Hall.class));
                finish();

            }
        });

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
