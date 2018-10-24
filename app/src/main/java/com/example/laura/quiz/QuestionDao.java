package com.example.laura.quiz;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert
    void insert(QuestionEntity... points);

    @Query("DELETE FROM question_table")
    void deleteAll();

    @Query("SELECT * FROM question_table WHERE difficulty = :diffSelected")
    LiveData<List<QuestionEntity>> getQuestionsByDiff(String diffSelected);

}
