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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewsAddActivity extends Activity {
	Context mContext;
	TextView tvUserName;
	EditText etTitle;
	EditText etContext;
	Button btnAddNews;
	Button btnCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_news_add);
		tvUserName = (TextView) findViewById(R.id.tvNewsEditUserName);
		tvUserName.setText("作者:"+MyPreference.getInstance(mContext).getLoginName());

		etTitle = (EditText) findViewById(R.id.etNewsEditTitle);
		etContext = (EditText) findViewById(R.id.etNewsEditContext);
		btnAddNews = (Button) findViewById(R.id.btnNewsAddAdd);
		btnCancel = (Button) findViewById(R.id.btnNewsAddCancel);
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
				long rowid = newsDAO.add(u);
				Toast.makeText(mContext, rowid + "保存成功", Toast.LENGTH_LONG)
						.show();
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

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.news_add, menu);
//		return true;
//	}

}
