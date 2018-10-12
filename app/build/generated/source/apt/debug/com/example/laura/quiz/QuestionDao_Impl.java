package com.example.laura.quiz;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.arch.persistence.room.util.StringUtil;
import android.database.Cursor;
import android.support.annotation.NonNull;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class QuestionDao_Impl implements QuestionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfQuestionEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public QuestionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuestionEntity = new EntityInsertionAdapter<QuestionEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `questions_table`(`id`,`tipo`,`data`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, QuestionEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getType() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getType());
        }
        if (value.getData() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getData());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM questions_table";
        return _query;
      }
    };
  }

  @Override
  public void insert(QuestionEntity question) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfQuestionEntity.insert(question);
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
  public LiveData<List<QuestionEntity>> getQuestionsByType(List<String> tipos) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM questions_table WHERE tipo IN (");
    final int _inputSize = tipos.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : tipos) {
      if (_item == null) {
        _statement.bindNull(_argIndex);
      } else {
        _statement.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    return new ComputableLiveData<List<QuestionEntity>>() {
      private Observer _observer;

      @Override
      protected List<QuestionEntity> compute() {
        if (_observer == null) {
          _observer = new Observer("questions_table") {
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
          final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("tipo");
          final int _cursorIndexOfData = _cursor.getColumnIndexOrThrow("data");
          final List<QuestionEntity> _result = new ArrayList<QuestionEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final QuestionEntity _item_1;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final String _tmpData;
            _tmpData = _cursor.getString(_cursorIndexOfData);
            _item_1 = new QuestionEntity(_tmpId,_tmpType,_tmpData);
            _result.add(_item_1);
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
  public int getQuestionsCount() {
    final String _sql = "SELECT COUNT(id) FROM questions_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
