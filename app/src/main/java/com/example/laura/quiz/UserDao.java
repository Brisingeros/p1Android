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

    @Query("UPDATE user_table SET foto = :newPath WHERE userName = :name")
    void updateUserByName(String newPath, String name);

    @Query("UPDATE user_table SET puntuacion_max = CASE WHEN :ptos > puntuacion_max THEN :ptos ELSE puntuacion_max END,ult_part = :ult_partida,num_partidas = num_partidas+1 WHERE userName = :name")
    void updatePartidaByName(int ptos, String ult_partida,String name);

    @Query("SELECT * FROM user_table")
    LiveData<List<UserEntity>> getUsers();

    @Query("SELECT * FROM user_table WHERE userName = :name")
    LiveData<UserEntity> getUserByName(String name);

}
