package com.example.laura.quiz;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Database(entities = {QuestionEntity.class,PointEntity.class}, version = 1)
public abstract class DataBase extends RoomDatabase {

    public abstract QuestionDao questionDao();
    public abstract PointsDao pointsDao();

    private static volatile DataBase INSTANCE;

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
        };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final QuestionDao qDao;

        PopulateDbAsync(DataBase db) {

            qDao = db.questionDao();

        }

        @Override
        protected Void doInBackground(final Void... params) {

            int index = qDao.getQuestionsCount();
            try {

                FileReader archivo = new FileReader(new File("../questions.json"));
                BufferedReader br = new BufferedReader(archivo);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            JSONObject preg =
            /*mDao.deleteAll();
            Word word = new Word("Una");
            mDao.insert(word);
            word = new Word("Dola");
            mDao.insert(word);
            word = new Word("Tela");
            mDao.insert(word);
            word = new Word("Catola");
            mDao.insert(word);*/
            return null;
        }
    }

    static DataBase getDataBase(final Context c){

        if(INSTANCE == null){

            synchronized (DataBase.class){

                if(INSTANCE == null){

                    INSTANCE = Room.databaseBuilder(c.getApplicationContext(),
                           DataBase.class, "quiz_Database")
                           .addCallback(sRoomDatabaseCallback)
                           .build();
                }
            }
        }

        return INSTANCE;

    }

}
