package sqlite.dao;

import sqlite.NewsOpenHelper;
import sqlite.beans.News;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NewsDAO {
	private NewsOpenHelper newsOpenHelper;
	private SQLiteDatabase db;
	public NewsDAO(Context context) {
		newsOpenHelper = new NewsOpenHelper(context);
		newsOpenHelper.onCreate(newsOpenHelper.getWritableDatabase());
	}

	public Cursor findByTitle(String title) {
		db = newsOpenHelper.getWritableDatabase();
		return db.query(newsOpenHelper.DATABASE_TABLE_NAME,null, "Title=? ", new  String[]{title}, null, null, null);
	}
	public Cursor findAll() {
		db = newsOpenHelper.getWritableDatabase();
		return db.query(newsOpenHelper.DATABASE_TABLE_NAME,null, null, null, null, null,null);
		//Cursor cursor=db.query(newsOpenHelper.DATABASE_TABLE_NAME,null,null, null, null, null,null);
		//return cursor;
	}
	
	
	public long add(News u) {
		db = newsOpenHelper.getWritableDatabase();
		//String sql="insert into Users values (?,?)";
		//db.execSQL(sql,new Object[]{u.getUserName(),u.getPassword()});
		ContentValues initialValues = new ContentValues();
		initialValues.put("Title", u.getTitle());
		initialValues.put("UsersName", u.getUsersName());
		initialValues.put("NewsDateTime", u.getNewsDateTime());
		initialValues.put("Context", u.getContetx());
		initialValues.put("ImgFile", u.getImgFile());
		return db.insert(newsOpenHelper.DATABASE_TABLE_NAME, null, initialValues);
	}

	public void update(News u) {
		// TODO Auto-generated method stub
		db = newsOpenHelper.getWritableDatabase();
		ContentValues initialValues = new ContentValues();
		initialValues.put("Title", u.getTitle());
		initialValues.put("NewsDateTime", u.getNewsDateTime());
		initialValues.put("Context", u.getContetx());
		initialValues.put("ImgFile", u.getImgFile());
		String whereClause = "_id=?";
		String[] whereArgs = {u.get_id()+""};
		db.update(newsOpenHelper.DATABASE_TABLE_NAME,initialValues,whereClause,whereArgs);
	}

	public News findBy_id(int rowid) {
		// TODO Auto-generated method stub
		db = newsOpenHelper.getWritableDatabase();
		Cursor cursor=db.query(newsOpenHelper.DATABASE_TABLE_NAME,null, "_id=? ", new  String[]{rowid+""}, null, null, null);
		cursor.moveToFirst();
		News n=new News();
		n.setTitle(cursor.getString(1));
		n.setUsersName(cursor.getString(3));
		n.setNewsDateTime(cursor.getString(2));
		n.setContetx(cursor.getString(4));
		n.setImgFile(cursor.getBlob(5));
		return n;
	}

	public void delete(int rowid) {
		// TODO Auto-generated method stub
		db = newsOpenHelper.getWritableDatabase();
		db.delete(newsOpenHelper.DATABASE_TABLE_NAME,"_id=?",new String[]{String.valueOf(rowid)});
	}
}
