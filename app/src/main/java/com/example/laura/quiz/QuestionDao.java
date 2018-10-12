package com.example.laura.quiz;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert
    void insert(QuestionEntity question);

    @Query("DELETE FROM questions_table")
    void deleteAll();

    @Query("SELECT * FROM questions_table WHERE tipo IN (:tipos)") // WHERE type IN (:tipos)
    LiveData<List<QuestionEntity>> getQuestionsByType(List<String> tipos); //List<String> tipos

    @Query("SELECT COUNT(id) FROM questions_table")
    int getQuestionsCount();

}
