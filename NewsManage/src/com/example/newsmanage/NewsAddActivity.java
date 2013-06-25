package com.example.newsmanage;

import java.sql.Date;
import java.text.SimpleDateFormat;

import sqlite.beans.News;
import sqlite.beans.User;
import sqlite.dao.NewsDAO;
import sqlite.dao.UsersDao;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


//新闻新增activity
public class NewsAddActivity extends Activity {
	/*
	 * 不知道这是啥，就知道有些操作是需要Context类传入，那时是使用类名.this进行传入，
	 * 在本类中即"NewsAddActivity.this",为方便起见使用这个。
	 * */
	Context mContext;
	//界面恐惧类，需要对应xml界面文件使用
	TextView tvUserName;
	EditText etTitle;
	EditText etContext;
	Button btnAddNews;
	Button btnCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//将本activity加入AppManager类中进行程序activity控制
		AppManager.getAppManager().addActivity(this);
		//设置mContext为本activity
		mContext = this;
		//设置对应的xml文件
		setContentView(R.layout.activity_news_add);
		
		tvUserName = (TextView) findViewById(R.id.tvNewsAddUserName);
		//设置对应的按钮，这需要在setContentView中先设置再设置这个，
		etTitle = (EditText) findViewById(R.id.etNewsAddTitle);
		etContext = (EditText) findViewById(R.id.etNewsAddContext);
		btnAddNews = (Button) findViewById(R.id.btnNewsAddAdd);
		btnCancel = (Button) findViewById(R.id.btnNewsAddCancel);
		//设置xml中tvUserName的值，
		tvUserName.setText("作者:"+MyPreference.getInstance(mContext).getLoginName());
		//设置监听器
		btnAddNews.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				NewsDAO newsDAO = new NewsDAO(mContext);
				String title = etTitle.getText().toString();
				String context = etContext.getText().toString();
				String userName = tvUserName.getText().toString();
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
				String strDateTime = formatter.format(curDate);

				// try{
				News u = new News();
				u.setUsersName(userName);
				u.setContetx(context);
				u.setTitle(title);
				u.setNewsDateTime(strDateTime);
				//newsDAO的add刚发会返回新增数据的_id的的值
				long rowid = newsDAO.add(u);
				Toast.makeText(mContext, rowid + "保存成功", Toast.LENGTH_LONG)
						.show();
				//跳转，设置完mContext=this后可以直接用mContent代替NewsAddActivity.this
				Intent intent = new Intent();
				intent.setClass(mContext, NewsListActivity.class);
				startActivity(intent);
			}
		});
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				Intent intent = new Intent();
				intent.setClass(mContext, NewsListActivity.class);
				startActivity(intent);
			}
		});

	}
	//同MainActivity.java
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	//同MainActivity.java
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.Leave:
			AppManager.getAppManager().AppExit(this);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
