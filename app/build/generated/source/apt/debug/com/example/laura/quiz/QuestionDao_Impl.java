package com.example.laura.quiz;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.util.StringUtil;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao_Impl implements QuestionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfQuestionEntity;

  public QuestionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuestionEntity = new EntityInsertionAdapter<QuestionEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `questions_table`(`id`,`type`,`data`) VALUES (?,?,?)";
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
  public List<QuestionEntity> getQuestionsByType(List<String> tipos) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM questions_table WHERE type IN (");
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
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
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
      _statement.release();
    }
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
