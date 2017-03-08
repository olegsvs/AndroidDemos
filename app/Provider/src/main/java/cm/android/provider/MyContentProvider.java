package cm.android.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.sql.SQLException;

/**
 * 定义实际的Content Provider类
 * 用于从Sqlite数据库中添加和读取记录
 * @author miaowei
 *
 */
public class MyContentProvider extends ContentProvider {

	private SQLiteDatabase sqlDB;
	private DatabaseHelper dbHelper;
	
	private static final String TABLE_NAME = "user";
	private static final String TAG = "MyContentProvider";
	
	
	@Override
	public boolean onCreate() {
		dbHelper = new DatabaseHelper(getContext());
		
		return (dbHelper == null) ? false : true;
	}


	@Override
	public String getType(Uri uri) {
		return null;
	}


	/**
	 * 从sqlite中读取记录
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		qb.setTables(TABLE_NAME);
		
		Cursor cursor = qb.query(db , projection, selection,null, null,null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(),uri);
		
		return cursor;
	}
	/**
	 * Sqlite数据库中添加记录
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		sqlDB = dbHelper.getWritableDatabase();
		long rowId = sqlDB.insert(TABLE_NAME, "", values);
		if (rowId > 0) {
			
			Uri  rowUri = ContentUris.appendId(MyUsers.User.CONTENT_URL.buildUpon(), rowId).build();
			
			getContext().getContentResolver().notifyChange(rowUri, null);
		
			return rowUri;
		}
		
		try {
			throw new SQLException("Failed to insert row into "+uri);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

	/**
	 * 创建数据库和数据表
	 * @author miaowei
	 *
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper{

		
		private static final String DATABASE_NAME = "user.db";
		private static final int DATABASE_VERSION = 1;
		
		
		
		public DatabaseHelper(Context context) {
			
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			//创建用于存储数据的表
	        db.execSQL("Create table " + TABLE_NAME + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, USER_NAME TEXT);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
			db.execSQL("drop table if exists "+TABLE_NAME);
			onCreate(db);
		}
		
		
	}

	@Nullable
	@Override
	public Bundle call(String method, String arg, Bundle extras) {
		return super.call(method, arg, extras);
	}
}
