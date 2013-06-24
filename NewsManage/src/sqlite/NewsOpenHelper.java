package sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NewsOpenHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION=1;
	private static final String DATABASE_FILE_NAME="News.db";
	public static final String DATABASE_TABLE_NAME="News";
	private static final String DATABASE_TABLE_CREATE="create table "+DATABASE_TABLE_NAME+
			"(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"Title varchar(64)," +
			"NewsDateTime datetime," +
			"UsersName varchar(64)," +
			"Context varchar(255)," +
			"ImgFile blob" +
			")";
	
	public NewsOpenHelper(Context context) {
		super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try{
			db.execSQL(DATABASE_TABLE_CREATE);
		}catch(Exception e){
            e.printStackTrace();
        }
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		Log.i("NewsManage", "UpGrade!");
	}

}
