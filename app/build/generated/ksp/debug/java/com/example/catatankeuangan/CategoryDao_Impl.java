package com.example.catatankeuangan;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CategoryDao_Impl implements CategoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Category> __insertionAdapterOfCategory;

  public CategoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCategory = new EntityInsertionAdapter<Category>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `categories` (`name`,`iconRes`,`limit`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Category entity) {
        statement.bindString(1, entity.getName());
        statement.bindLong(2, entity.getIconRes());
        if (entity.getLimit() == null) {
          statement.bindNull(3);
        } else {
          statement.bindDouble(3, entity.getLimit());
        }
      }
    };
  }

  @Override
  public void insert(final Category cat) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCategory.insert(cat);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Category> getAll() {
    final String _sql = "SELECT * FROM categories";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIconRes = CursorUtil.getColumnIndexOrThrow(_cursor, "iconRes");
      final int _cursorIndexOfLimit = CursorUtil.getColumnIndexOrThrow(_cursor, "limit");
      final List<Category> _result = new ArrayList<Category>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Category _item;
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final int _tmpIconRes;
        _tmpIconRes = _cursor.getInt(_cursorIndexOfIconRes);
        final Double _tmpLimit;
        if (_cursor.isNull(_cursorIndexOfLimit)) {
          _tmpLimit = null;
        } else {
          _tmpLimit = _cursor.getDouble(_cursorIndexOfLimit);
        }
        _item = new Category(_tmpName,_tmpIconRes,_tmpLimit);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Flow<List<Category>> getAllFlow() {
    final String _sql = "SELECT * FROM categories";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"categories"}, new Callable<List<Category>>() {
      @Override
      @NonNull
      public List<Category> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfIconRes = CursorUtil.getColumnIndexOrThrow(_cursor, "iconRes");
          final int _cursorIndexOfLimit = CursorUtil.getColumnIndexOrThrow(_cursor, "limit");
          final List<Category> _result = new ArrayList<Category>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Category _item;
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final int _tmpIconRes;
            _tmpIconRes = _cursor.getInt(_cursorIndexOfIconRes);
            final Double _tmpLimit;
            if (_cursor.isNull(_cursorIndexOfLimit)) {
              _tmpLimit = null;
            } else {
              _tmpLimit = _cursor.getDouble(_cursorIndexOfLimit);
            }
            _item = new Category(_tmpName,_tmpIconRes,_tmpLimit);
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
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
