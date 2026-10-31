package com.example.catatankeuangan;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.IllegalArgumentException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class RecurringDao_Impl implements RecurringDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RecurringEntry> __insertionAdapterOfRecurringEntry;

  public RecurringDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRecurringEntry = new EntityInsertionAdapter<RecurringEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `recurring_entries` (`id`,`description`,`amount`,`type`,`dayOfMonth`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RecurringEntry entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getDescription());
        statement.bindDouble(3, entity.getAmount());
        statement.bindString(4, __Type_enumToString(entity.getType()));
        statement.bindLong(5, entity.getDayOfMonth());
      }
    };
  }

  @Override
  public void insert(final RecurringEntry entry) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRecurringEntry.insert(entry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<RecurringEntry> getAll() {
    final String _sql = "SELECT * FROM recurring_entries";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfDayOfMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "dayOfMonth");
      final List<RecurringEntry> _result = new ArrayList<RecurringEntry>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final RecurringEntry _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        final double _tmpAmount;
        _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        final Transaction.Type _tmpType;
        _tmpType = __Type_stringToEnum(_cursor.getString(_cursorIndexOfType));
        final int _tmpDayOfMonth;
        _tmpDayOfMonth = _cursor.getInt(_cursorIndexOfDayOfMonth);
        _item = new RecurringEntry(_tmpId,_tmpDescription,_tmpAmount,_tmpType,_tmpDayOfMonth);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __Type_enumToString(@NonNull final Transaction.Type _value) {
    switch (_value) {
      case INCOME: return "INCOME";
      case EXPENSE: return "EXPENSE";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private Transaction.Type __Type_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "INCOME": return Transaction.Type.INCOME;
      case "EXPENSE": return Transaction.Type.EXPENSE;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
