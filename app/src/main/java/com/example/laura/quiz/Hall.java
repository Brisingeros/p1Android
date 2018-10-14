package com.example.laura.quiz;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Hall extends AppCompatActivity {

    List<TextView> rankings;
    Button salir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hall);
        DataBase db = DataBase.getDataBase(getApplicationContext());

        rankings = new ArrayList<>();
        salir = (Button) findViewById(R.id.salirHall);

        salir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Menu.class)); //iniciamos una nueva partida
                finish();

            }

        });

        int id;

        for (int i = 1; i < 11; i++){

            id = getResources().getIdentifier(("puntos" + i), "id", getPackageName());
            rankings.add((TextView) findViewById(id));

        }

        final LiveData<List<PointEntity>> top = db.pointsDao().getAllPoints();
        Observer quesDao = new Observer() {

            @Override
            public void onChanged(@Nullable Object o) {

                System.out.println(top.getValue());
                render(top.getValue());

            }
        };

        top.observe(this, quesDao);
    }

    private void render(List<PointEntity> puntuaciones){
        PointEntity actual = null;

        for(int i = 0; i < puntuaciones.size(); i++){
            actual = puntuaciones.get(i);
            rankings.get(i).setText((i+1) + ") " + actual.getUserName() + " - " + actual.getPoints());
        }

    }
}
