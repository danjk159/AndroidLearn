package sqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import sqlite.UsersOpenHelper;
import sqlite.beans.User;


public class UsersDao {
	UsersOpenHelper usersOpenHelper;
	private SQLiteDatabase db;
	public UsersDao(Context context) {
		usersOpenHelper = new UsersOpenHelper(context);
		usersOpenHelper.onCreate(usersOpenHelper.getWritableDatabase());
	}

	public Cursor find(User u) {
		db = usersOpenHelper.getWritableDatabase();
		//String sql="insert into Users values (?,?)";
		//db.execSQL(sql,new Object[]{u.getUserName(),u.getPassword()});
		//ContentValues initialValues = new ContentValues();
		//initialValues.put("UserName", u.getUserName());
		//initialValues.put("Password", u.getPassword());
		return db.query(usersOpenHelper.DATABASE_TABLE_NAME,null, "UserName=? and Password=?", new  String[]{ u.getUserName(),u.getPassword()}, null, null, null);
	}
	public Cursor findAll() {
		db = usersOpenHelper.getWritableDatabase();
		return db.query(usersOpenHelper.DATABASE_TABLE_NAME,new String[]{"rowid AS _id","UserName","Password"}, null, null, null, null,null);
		//Cursor cursor=db.query(usersOpenHelper.DATABASE_TABLE_NAME,null,null, null, null, null,null);
		//return cursor;
	}
	
	
	public long add(User u) {
		db = usersOpenHelper.getWritableDatabase();
		//String sql="insert into Users values (?,?)";
		//db.execSQL(sql,new Object[]{u.getUserName(),u.getPassword()});
		ContentValues initialValues = new ContentValues();
		initialValues.put("UserName", u.getUserName());
		initialValues.put("Password", u.getPassword());
		return db.insert(usersOpenHelper.DATABASE_TABLE_NAME, null, initialValues);
	}

	public void update(User u) {
		// TODO Auto-generated method stub
		db = usersOpenHelper.getWritableDatabase();
		ContentValues initialValues = new ContentValues();
		initialValues.put("Password", u.getPassword());
		String whereClause = "UserName=?";
		String[] whereArgs = {u.getUserName()};
		db.update(usersOpenHelper.DATABASE_TABLE_NAME,initialValues,whereClause,whereArgs);
	}
}
