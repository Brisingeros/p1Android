package com.example.laura.quiz;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import android.support.annotation.NonNull;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfUserEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUser;

  private final SharedSQLiteStatement __preparedStmtOfUpdateUser;

  private final SharedSQLiteStatement __preparedStmtOfUpdatePartida;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserEntity = new EntityInsertionAdapter<UserEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `user_table`(`id`,`userName`,`puntuacion_max`,`num_partidas`,`ult_part`,`foto`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getNombre() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNombre());
        }
        stmt.bindLong(3, value.getPunt_max());
        stmt.bindLong(4, value.getNum_partidas());
        if (value.getUlt_partida() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getUlt_partida());
        }
        if (value.getPath_foto() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getPath_foto());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM user_table";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteUser = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM user_table WHERE userName = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateUser = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE user_table SET foto = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdatePartida = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE user_table SET puntuacion_max = CASE WHEN ? > puntuacion_max THEN ? ELSE puntuacion_max END,ult_part = ?,num_partidas = num_partidas+1 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(UserEntity... points) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserEntity.insert(points);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public void deleteUser(String name) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUser.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (name == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, name);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteUser.release(_stmt);
    }
  }

  @Override
  public void updateUser(String newPath, int id) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateUser.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (newPath == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, newPath);
      }
      _argIndex = 2;
      _stmt.bindLong(_argIndex, id);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateUser.release(_stmt);
    }
  }

  @Override
  public void updatePartida(int ptos, String ult_partida, int id) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdatePartida.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, ptos);
      _argIndex = 2;
      _stmt.bindLong(_argIndex, ptos);
      _argIndex = 3;
      if (ult_partida == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, ult_partida);
      }
      _argIndex = 4;
      _stmt.bindLong(_argIndex, id);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdatePartida.release(_stmt);
    }
  }

  @Override
  public LiveData<List<UserEntity>> getUsers() {
    final String _sql = "SELECT * FROM user_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<UserEntity>>() {
      private Observer _observer;

      @Override
      protected List<UserEntity> compute() {
        if (_observer == null) {
          _observer = new Observer("user_table") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfNombre = _cursor.getColumnIndexOrThrow("userName");
          final int _cursorIndexOfPuntMax = _cursor.getColumnIndexOrThrow("puntuacion_max");
          final int _cursorIndexOfNumPartidas = _cursor.getColumnIndexOrThrow("num_partidas");
          final int _cursorIndexOfUltPartida = _cursor.getColumnIndexOrThrow("ult_part");
          final int _cursorIndexOfPathFoto = _cursor.getColumnIndexOrThrow("foto");
          final List<UserEntity> _result = new ArrayList<UserEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final UserEntity _item;
            _item = new UserEntity();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            _item.setNombre(_tmpNombre);
            final int _tmpPunt_max;
            _tmpPunt_max = _cursor.getInt(_cursorIndexOfPuntMax);
            _item.setPunt_max(_tmpPunt_max);
            final int _tmpNum_partidas;
            _tmpNum_partidas = _cursor.getInt(_cursorIndexOfNumPartidas);
            _item.setNum_partidas(_tmpNum_partidas);
            final String _tmpUlt_partida;
            _tmpUlt_partida = _cursor.getString(_cursorIndexOfUltPartida);
            _item.setUlt_partida(_tmpUlt_partida);
            final String _tmpPath_foto;
            _tmpPath_foto = _cursor.getString(_cursorIndexOfPathFoto);
            _item.setPath_foto(_tmpPath_foto);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<UserEntity> getUserById(int id) {
    final String _sql = "SELECT * FROM user_table WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return new ComputableLiveData<UserEntity>() {
      private Observer _observer;

      @Override
      protected UserEntity compute() {
        if (_observer == null) {
          _observer = new Observer("user_table") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfNombre = _cursor.getColumnIndexOrThrow("userName");
          final int _cursorIndexOfPuntMax = _cursor.getColumnIndexOrThrow("puntuacion_max");
          final int _cursorIndexOfNumPartidas = _cursor.getColumnIndexOrThrow("num_partidas");
          final int _cursorIndexOfUltPartida = _cursor.getColumnIndexOrThrow("ult_part");
          final int _cursorIndexOfPathFoto = _cursor.getColumnIndexOrThrow("foto");
          final UserEntity _result;
          if(_cursor.moveToFirst()) {
            _result = new UserEntity();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _result.setId(_tmpId);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            _result.setNombre(_tmpNombre);
            final int _tmpPunt_max;
            _tmpPunt_max = _cursor.getInt(_cursorIndexOfPuntMax);
            _result.setPunt_max(_tmpPunt_max);
            final int _tmpNum_partidas;
            _tmpNum_partidas = _cursor.getInt(_cursorIndexOfNumPartidas);
            _result.setNum_partidas(_tmpNum_partidas);
            final String _tmpUlt_partida;
            _tmpUlt_partida = _cursor.getString(_cursorIndexOfUltPartida);
            _result.setUlt_partida(_tmpUlt_partida);
            final String _tmpPath_foto;
            _tmpPath_foto = _cursor.getString(_cursorIndexOfPathFoto);
            _result.setPath_foto(_tmpPath_foto);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
