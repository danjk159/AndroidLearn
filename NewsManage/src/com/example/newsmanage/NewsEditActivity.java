package com.example.newsmanage;

import java.sql.Date;
import java.text.SimpleDateFormat;

import sqlite.beans.News;
import sqlite.dao.NewsDAO;
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

public class NewsEditActivity extends Activity {
	Context mContext;
	int rowid;
	TextView tvUserName;
	EditText etTitle;
	EditText etContext;
	Button btnNewsEditEdit;
	Button btnCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_news_edit);
		connectContentView();
		AppManager.getAppManager().addActivity(this);
		try {
			rowid = Integer.parseInt(getIntent().getStringExtra("rowid"));
		} catch (Exception e) {
			Toast.makeText(mContext, "NewsCardActivity出错:" + e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
		if(rowid!=-1){
			fillData();
			setButtonOnClickListener();
		}
	}

	private void connectContentView() {
		// TODO Auto-generated method stub
		etTitle = (EditText) findViewById(R.id.etNewsEditTitle);
		tvUserName = (TextView) findViewById(R.id.tvNewsEditUserName);
		etContext = (EditText) findViewById(R.id.etNewsEditContext);
		btnNewsEditEdit = (Button) findViewById(R.id.btnNewsEditEdit);
		btnCancel = (Button) findViewById(R.id.btnNewsEditCancel);
	}
	
	private void fillData() {
		// TODO Auto-generated method stub
		NewsDAO newsDAO=new NewsDAO(mContext);
		News u=newsDAO.findBy_id(rowid);
		etTitle.setText(u.getTitle());
		tvUserName.setText("作者:"+u.getUsersName());
		etContext.setText(u.getContetx());		
	}

	private void setButtonOnClickListener() {
		// TODO Auto-generated method stub
		btnCancel.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(mContext,NewsListActivity.class);
				startActivity(intent);
			}
			
		});
		btnNewsEditEdit.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
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
				u.set_id(rowid);
				u.setUsersName(userName);
				u.setContetx(context);
				u.setTitle(title);
				u.setNewsDateTime(strDateTime);
				newsDAO.update(u);
				Toast.makeText(mContext,"修改成功", Toast.LENGTH_LONG)
						.show();
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
