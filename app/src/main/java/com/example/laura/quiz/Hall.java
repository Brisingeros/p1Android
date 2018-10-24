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

    List<TextView> rankingsHard, rankingsMedium, rankingsEasy;
    Button salir;
    UserEntity jugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hall);
        DataBase db = DataBase.getDataBase(getApplicationContext());

        salir = (Button) findViewById(R.id.salirHall);

        Bundle bundle = getIntent().getExtras();
        jugador = (UserEntity) bundle.getSerializable("jugador");

        salir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), Menu.class);
                i.putExtra("jugador", jugador);
                startActivity(i);
                finish();

            }

        });

        //////////////////////////////////////////////////////////////
        //HARD
        rankingsHard = new ArrayList<>();
        int id;
        for (int i = 1; i < 5; i++){

            id = getResources().getIdentifier(("puntos" + i + "hard"), "id", getPackageName());
            rankingsHard.add((TextView) findViewById(id));

        }

        final LiveData<List<PointEntity>> topHard = db.pointsDao().getAllPoints("hard");
        Observer pointDaoHard = new Observer() {

            @Override
            public void onChanged(@Nullable Object o) {
                render(topHard.getValue(), rankingsHard);
            }
        };

        topHard.observe(this, pointDaoHard);

        //////////////////////////////////////////////////////////////
        //MEDIUM
        rankingsMedium = new ArrayList<>();

        for (int i = 1; i < 5; i++){

            id = getResources().getIdentifier(("puntos" + i + "medium"), "id", getPackageName());
            rankingsMedium.add((TextView) findViewById(id));

        }

        final LiveData<List<PointEntity>> topMedium = db.pointsDao().getAllPoints("medium");
        Observer pointDaoMedium = new Observer() {

            @Override
            public void onChanged(@Nullable Object o) {
                render(topMedium.getValue(), rankingsMedium);
            }
        };

        topMedium.observe(this, pointDaoMedium);

        //////////////////////////////////////////////////////////////
        //EASY
        rankingsEasy = new ArrayList<>();

        for (int i = 1; i < 5; i++){

            id = getResources().getIdentifier(("puntos" + i + "easy"), "id", getPackageName());
            rankingsEasy.add((TextView) findViewById(id));

        }

        final LiveData<List<PointEntity>> topEasy = db.pointsDao().getAllPoints("easy");
        Observer pointDaoEasy = new Observer() {

            @Override
            public void onChanged(@Nullable Object o) {
                render(topEasy.getValue(), rankingsEasy);
            }
        };

        topEasy.observe(this, pointDaoEasy);
    }

    private void render(List<PointEntity> puntuaciones, List<TextView> ran){
        PointEntity actual = null;

        for(int i = 0; i < puntuaciones.size(); i++){
            actual = puntuaciones.get(i);
            ran.get(i).setText((i+1) + ") " + actual.getUserName() + " - " + actual.getPoints());
        }

    }
}
