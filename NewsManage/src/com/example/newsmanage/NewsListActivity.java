package com.example.newsmanage;

import sqlite.dao.NewsDAO;
import sqlite.dao.UsersDao;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class NewsListActivity extends Activity {
	private ListView newsList = null;
	// private Button btnUserEdit = null;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		// setContentView(R.layout.news_list);
		// newsList=(ListView) findViewById(R.id.listNewsList);
		final ListView newsList = new ListView(this);
		NewsDAO newsDao = new NewsDAO(mContext);
		// UsersDao usersDao = new UsersDao(NewsListActivity.this);
		final Cursor cursor = newsDao.findAll();
		startManagingCursor(cursor);
		// Toast.makeText(NewsListActivity.this,cursor.getCount()+"" ,
		// Toast.LENGTH_LONG).show();

		ListAdapter newsAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cursor,
				new String[] { "Title" }, new int[] { android.R.id.text1 });
		newsList.setAdapter(newsAdapter);
		setContentView(newsList);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_list, menu);
		return true;
	}

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
		}
		return super.onOptionsItemSelected(item);
	}

}
