package com.example.laura.quiz;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Hall extends AppCompatActivity {

    List<TextView> rankings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hall);
        DataBase db = DataBase.getDataBase(getApplicationContext());

        rankings = new ArrayList<>();
        int id;

        for (int i = 1; i < 11; i++){
            id = getResources().getIdentifier(("pregunta" + i), "id", getPackageName());
            rankings.add((TextView) findViewById(id));
        }

        final LiveData<List<PointEntity>> top = db.pointsDao().getAllPoints();
        Observer quesDao = new Observer() {

            @Override
            public void onChanged(@Nullable Object o) {

                render((List<PointEntity>) o);

            }
        };
    }

    private void render(List<PointEntity> puntuaciones){
        PointEntity actual = null;

        for(int i = 0; i < puntuaciones.size(); i++){
            actual = puntuaciones.get(i);
            rankings.get(i).setText((i+1) + ") " + actual.getUserName() + " - " + actual.getPoints());
        }

    }
}
