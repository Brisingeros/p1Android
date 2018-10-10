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

@Database(entities = {QuestionEntity.class,PointEntity.class}, version = 1)
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
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }else{


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
            //doInBackground();

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

            qDao.deleteAll();
            int questionsCountDB = qDao.getQuestionsCount();
            String text = "";
            String line;

            try {

                InputStream is = context.getResources().openRawResource(
                        context.getResources().getIdentifier("questions",
                                "raw", context.getPackageName()));

                String archivo = readTextFile(is);

                System.out.println("fichero: " + archivo);

                JSONObject json = new JSONObject(archivo);
                JSONArray questions = (JSONArray) json.get("questions");

                QuestionEntity newQuestion;
                JSONObject question;

                for (int i = questionsCountDB; i < questions.length(); i++) { //si hay mas preguntas en el json que en la DB, se añaden a ésta

                    question = questions.getJSONObject(i);
                    newQuestion = new QuestionEntity(i, question.get("tipo").toString(), question.getJSONObject("question").toString());
                    qDao.insert(newQuestion);

                }

            } catch (JSONException e) {
                System.out.println("Error: " + e.getMessage());
            }

            return null;
        }

    }

}