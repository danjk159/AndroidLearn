package com.example.newsmanage;

import sqlite.dao.UsersDao;
import android.os.Bundle;
import android.app.Activity;
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
	//private Button btnUserEdit = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.news_list);
		// newsList=(ListView) findViewById(R.id.listNewsList);
		ListView newsList = new ListView(this);
		UsersDao usersDao = new UsersDao(NewsListActivity.this);
		Cursor cursor = usersDao.findAll();
		startManagingCursor(cursor);
		// Toast.makeText(NewsListActivity.this,cursor.getCount()+"" , Toast.LENGTH_LONG).show();

		ListAdapter newsAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cursor,
				new String[] { "UserName" }, new int[] { android.R.id.text1 });
		newsList.setAdapter(newsAdapter);
		setContentView(newsList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.UserEdit:
			//Toast.makeText(NewsListActivity.this,MyPreference.getInstance(NewsListActivity.this).getLoginName(), Toast.LENGTH_LONG).show();
			Intent intent = new Intent();
			intent.setClass(NewsListActivity.this, UserEditActivity.class);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
