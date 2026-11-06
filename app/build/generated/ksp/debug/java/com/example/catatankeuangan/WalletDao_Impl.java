package com.example.catatankeuangan;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WalletDao_Impl implements WalletDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Wallet> __insertionAdapterOfWallet;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<Wallet> __deletionAdapterOfWallet;

  private final EntityDeletionOrUpdateAdapter<Wallet> __updateAdapterOfWallet;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public WalletDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWallet = new EntityInsertionAdapter<Wallet>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `wallets` (`id`,`name`,`type`,`balance`,`color`,`icon`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Wallet entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        final String _tmp = __converters.fromWalletType(entity.getType());
        statement.bindString(3, _tmp);
        statement.bindDouble(4, entity.getBalance());
        statement.bindLong(5, entity.getColor());
        statement.bindString(6, entity.getIcon());
      }
    };
    this.__deletionAdapterOfWallet = new EntityDeletionOrUpdateAdapter<Wallet>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `wallets` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Wallet entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfWallet = new EntityDeletionOrUpdateAdapter<Wallet>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `wallets` SET `id` = ?,`name` = ?,`type` = ?,`balance` = ?,`color` = ?,`icon` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Wallet entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        final String _tmp = __converters.fromWalletType(entity.getType());
        statement.bindString(3, _tmp);
        statement.bindDouble(4, entity.getBalance());
        statement.bindLong(5, entity.getColor());
        statement.bindString(6, entity.getIcon());
        statement.bindLong(7, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM wallets";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final Wallet wallet, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWallet.insert(wallet);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final Wallet wallet, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfWallet.handle(wallet);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Wallet wallet, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfWallet.handle(wallet);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public List<Wallet> getAll() {
    final String _sql = "SELECT * FROM wallets ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfBalance = CursorUtil.getColumnIndexOrThrow(_cursor, "balance");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "icon");
      final List<Wallet> _result = new ArrayList<Wallet>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Wallet _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final WalletType _tmpType;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfType);
        _tmpType = __converters.toWalletType(_tmp);
        final double _tmpBalance;
        _tmpBalance = _cursor.getDouble(_cursorIndexOfBalance);
        final int _tmpColor;
        _tmpColor = _cursor.getInt(_cursorIndexOfColor);
        final String _tmpIcon;
        _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
        _item = new Wallet(_tmpId,_tmpName,_tmpType,_tmpBalance,_tmpColor,_tmpIcon);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Flow<List<Wallet>> getAllFlow() {
    final String _sql = "SELECT * FROM wallets ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"wallets"}, new Callable<List<Wallet>>() {
      @Override
      @NonNull
      public List<Wallet> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfBalance = CursorUtil.getColumnIndexOrThrow(_cursor, "balance");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "icon");
          final List<Wallet> _result = new ArrayList<Wallet>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Wallet _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final WalletType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toWalletType(_tmp);
            final double _tmpBalance;
            _tmpBalance = _cursor.getDouble(_cursorIndexOfBalance);
            final int _tmpColor;
            _tmpColor = _cursor.getInt(_cursorIndexOfColor);
            final String _tmpIcon;
            _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
            _item = new Wallet(_tmpId,_tmpName,_tmpType,_tmpBalance,_tmpColor,_tmpIcon);
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

  @Override
  public Wallet getById(final int id) {
    final String _sql = "SELECT * FROM wallets WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfBalance = CursorUtil.getColumnIndexOrThrow(_cursor, "balance");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "icon");
      final Wallet _result;
      if (_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final WalletType _tmpType;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfType);
        _tmpType = __converters.toWalletType(_tmp);
        final double _tmpBalance;
        _tmpBalance = _cursor.getDouble(_cursorIndexOfBalance);
        final int _tmpColor;
        _tmpColor = _cursor.getInt(_cursorIndexOfColor);
        final String _tmpIcon;
        _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
        _result = new Wallet(_tmpId,_tmpName,_tmpType,_tmpBalance,_tmpColor,_tmpIcon);
      } else {
        _result = null;
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
}
