package sqlite.dao;

import android.content.ContentValues;
import android.content.Context;
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

	public long add(User u) {
		db = usersOpenHelper.getWritableDatabase();
		//String sql="insert into Users values (?,?)";
		//db.execSQL(sql,new Object[]{u.getUserName(),u.getPassword()});
		ContentValues initialValues = new ContentValues();
		initialValues.put("UserName", u.getUserName());
		initialValues.put("Password", u.getPassword());
		return db.insert(usersOpenHelper.DATABASE_TABLE_NAME, null, initialValues);
	}
}
