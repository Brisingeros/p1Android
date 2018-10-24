package com.example.laura.quiz;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(UserEntity... points);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("DELETE FROM user_table WHERE userName = :name")
    void deleteUser(String name);

    @Query("UPDATE user_table SET foto = :newPath WHERE id = :id")
    void updateUser(String newPath, int id);

    @Query("UPDATE user_table SET puntuacion_max = :ptos,ult_part = :ult_partida,num_partidas = num_partidas+1 WHERE id = :id")
    void updatePartida(int ptos, String ult_partida,int id);

    @Query("SELECT * FROM user_table")
    LiveData<List<UserEntity>> getUsers();
    /*@Query("SELECT * FROM user_table WHERE difficulty = :diffSelected")
    LiveData<List<QuestionEntity>> getQuestionsByDiff(String diffSelected);*/

}
