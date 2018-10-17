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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Database(entities = {QuestionEntity.class, PointEntity.class}, version = 7)
public abstract class DataBase extends RoomDatabase {

    public abstract QuestionDao questionDao();
    public abstract PointsDao pointsDao();

    private static volatile DataBase INSTANCE;

    private static Context context;

    static DataBase getDataBase(final Context c){

        context = c;

        if(INSTANCE == null){

            synchronized (DataBase.class){

                if(INSTANCE == null){

                    INSTANCE = Room.databaseBuilder(c,
                            DataBase.class, "quiz_Database")
                            .fallbackToDestructiveMigration()
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

        public String readTextFile(InputStream inputStream) {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte buf[] = new byte[1024];
            int len;
            try {
                while ((len = inputStream.read(buf)) != -1) {
                    outputStream.write(buf, 0, len);
                }
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {

                System.out.println("Error: " + e.getMessage());

            }
            return outputStream.toString();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            //qDao.deleteAll();
            //int questionsCountDB = qDao.getQuestionsCount();

            try {

                InputStream is = context.getResources().openRawResource(
                        context.getResources().getIdentifier("update",
                                "raw", context.getPackageName()));

                String archivo = readTextFile(is);
                JSONObject json = new JSONObject(archivo);

                if(!json.getBoolean("update"))
                    return null;

                ///////////////////////////////////////////////////////////////////////////////////
                //HACER QUE UPDATE PASE A FALSE
                qDao.deleteAll();

                is = context.getResources().openRawResource(
                        context.getResources().getIdentifier("questions",
                                "raw", context.getPackageName()));

                archivo = readTextFile(is);

                json = new JSONObject(archivo);

                QuestionEntity newQuestion;
                JSONObject question;
                JSONArray questions =  json.getJSONArray("questions_easy");

                for (int i = 0; i < questions.length(); i++) {

                    question = questions.getJSONObject(i);
                    newQuestion = new QuestionEntity(question.getString("tipo"), "easy", question.getJSONObject("question").toString());
                    qDao.insert(newQuestion);

                }

                questions = json.getJSONArray("questions_medium");

                for (int i = 0; i < questions.length(); i++) {

                    question = questions.getJSONObject(i);
                    newQuestion = new QuestionEntity(question.getString("tipo"), "medium", question.getJSONObject("question").toString());
                    qDao.insert(newQuestion);

                }

                questions = json.getJSONArray("questions_hard");

                for (int i = 0; i < questions.length(); i++) {

                    question = questions.getJSONObject(i);
                    newQuestion = new QuestionEntity(question.getString("tipo"), "hard", question.getJSONObject("question").toString());
                    qDao.insert(newQuestion);

                }

            } catch (JSONException e) {
                System.out.println("Error on doInBackground: " + e.getMessage());
            }

            return null;
        }

    }

}