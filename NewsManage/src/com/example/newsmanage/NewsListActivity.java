package com.example.newsmanage;

import sqlite.dao.NewsDAO;
import sqlite.dao.UsersDao;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class NewsListActivity extends Activity {
	private ListView newsList = null;
	// private Button btnUserEdit = null;
	private Context mContext;
	private NewsDAO newsDao ;
	private Cursor cursor;
	private ListAdapter newsAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		AppManager.getAppManager().addActivity(this);
		// setContentView(R.layout.news_list);
		// newsList=(ListView) findViewById(R.id.listNewsList);
		newsList = new ListView(this);
		newsDao = new NewsDAO(mContext);
		// UsersDao usersDao = new UsersDao(NewsListActivity.this);
		cursor = newsDao.findAll();
		startManagingCursor(cursor);
		// Toast.makeText(NewsListActivity.this,cursor.getCount()+"" ,
		// Toast.LENGTH_LONG).show();

		newsAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cursor,
				new String[] { "Title" }, new int[] { android.R.id.text1 });
		newsList.setAdapter(newsAdapter);
		
		setContentView(newsList);
		
		
//		EditText tvSearch=new EditText(mContext);
//		tvSearch.setHint("搜索");
//		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(  
//		        FrameLayout.LayoutParams.FILL_PARENT,  
//		        FrameLayout.LayoutParams.WRAP_CONTENT);  
//		addContentView(tvSearch, params);
		
//		tvSearch.addTextChangedListener(new TextWatcher() {
//
//			@Override
//			public void afterTextChanged(Editable arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void beforeTextChanged(CharSequence arg0, int arg1,
//					int arg2, int arg3) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
//					int arg3) {
//				// TODO Auto-generated method stub
//				cursor = newsDao.findByTitle(arg0.toString());
//				newsList.invalidate();
//				//newsList.findViewWithTag(arg0);
//			}
//		
//		});
		
		
		
		newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				cursor.moveToPosition(position);
				String str = cursor.getString(0);
				//Toast.makeText(NewsListActivity.this,str+"被点击了",Toast.LENGTH_LONG).show();
				Intent intent=new Intent(mContext, NewsCardActivity.class);
				intent.putExtra("rowid", str);
				//Toast.makeText(NewsListActivity.this,intent.getStringExtra("rowid")+"被点击了",Toast.LENGTH_LONG).show();
				startActivity(intent);
			}
		});
	}
	//同MainActivity.java
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_list, menu);
		return true;
	}
	//同MainActivity.java
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch (item.getItemId()) {
		case R.id.UserEdit:
			intent.setClass(NewsListActivity.this, UserEditActivity.class);
			startActivity(intent);
			break;
		case R.id.NewsAdd:
			intent.setClass(NewsListActivity.this, NewsAddActivity.class);
			startActivity(intent);
			break;
		case R.id.Leave:
			AppManager.getAppManager().AppExit(this);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
