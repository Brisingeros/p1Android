package com.example.laura.quiz;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Database(entities = {QuestionEntity.class,PointEntity.class}, version = 1)
public abstract class DataBase extends RoomDatabase {

    public abstract QuestionDao questionDao();
    public abstract PointsDao pointsDao();

    private static volatile DataBase INSTANCE;

    static DataBase getDataBase(final Context c){

        if(INSTANCE == null){

            synchronized (DataBase.class){

                if(INSTANCE == null){

                    INSTANCE = Room.databaseBuilder(c,
                            DataBase.class, "quiz_Database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;

    }

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

            int questionsCountDB = qDao.getQuestionsCount();
            String text = "";
            String line;

            try {

                FileReader archivo = new FileReader(new File("../.././res/raw/questions.json"));
                BufferedReader br = new BufferedReader(archivo);

                while((line = br.readLine()) != null){
                    text += line;
                }

                JSONObject json = new JSONObject(text);
                JSONArray questions = (JSONArray) json.get("questions");

                QuestionEntity newQuestion;
                JSONObject question;

                for (int i = questionsCountDB; i < questions.length(); i++) { //si hay mas preguntas en el json que en la DB, se añaden a ésta

                    question = questions.getJSONObject(i);
                    newQuestion = new QuestionEntity(i, question.get("tipo").toString(), question.getJSONObject("question").toString());
                    qDao.insert(newQuestion);

                }

            } catch (java.io.IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

    }

}