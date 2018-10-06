package com.example.laura.quiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert
    void insert(QuestionEntity question);

    @Query("SELECT * FROM questions_table WHERE type IN (:tipos)")
    List<QuestionEntity> getQuestionsByType(List<String> tipos);

    @Query("SELECT COUNT(id) FROM questions_table")
    int getQuestionsCount();

}
