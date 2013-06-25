package com.example.newsmanage;

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
import android.widget.TextView;
import android.widget.Toast;

public class NewsCardActivity extends Activity {
	//rowid，点击新闻列表跳转过来时会赋值与rowid，因为sqlite自增长是从0开始，所以赋值-1错检测
	int rowid=-1;
	//同MainActivity.java
	Context mContext;
	//同MainActivity.java
	TextView tvTitle;
	TextView tvUserName;
	TextView tvDateTime;
	TextView tvContext;
	Button btnEdit;
	Button btnCancel;
	Button btnDelete;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext=this;
		//同MainActivity.java
		setContentView(R.layout.activity_news_card);
		AppManager.getAppManager().addActivity(this);
		//将xml和java中的控件类联系的方法
		connectContentView();
		try{
		//获取从上一个activity的intent里面赋给rowid的值，因为可能有空值，所以try catch一下
		rowid=Integer.parseInt(getIntent().getStringExtra("rowid")); 
		}catch(Exception e){
			Toast.makeText(mContext,"NewsCardActivity出错:"+e.getMessage(),Toast.LENGTH_LONG).show();
		}
		//如果成功传值获取rowid所需进行的擦做
		if(rowid!=-1){
			//填充数据。
			fillData();
			//设置监听器
			setButtonOnClickListener();
		}
	}

	private void connectContentView() {
		// TODO Auto-generated method stub
		tvTitle=(TextView)findViewById(R.id.tvNewsCardTitle);
		tvUserName=(TextView)findViewById(R.id.tvNewsCardUserName);
		tvDateTime=(TextView)findViewById(R.id.tvNewsCardDateTime);
		tvContext=(TextView)findViewById(R.id.tvNewsCardContext);
		btnEdit=(Button)findViewById(R.id.btnNewsCardEdit);
		btnCancel=(Button)findViewById(R.id.btnNewsCardCancel);
		btnDelete=(Button)findViewById(R.id.btnNewsCardDelete);
		}

	private void fillData() {
		// TODO Auto-generated method stub
		NewsDAO newsDAO=new NewsDAO(mContext);
		News u=newsDAO.findBy_id(rowid);
		tvTitle.setText("标题:"+u.getTitle());
		tvUserName.setText("作者:"+u.getUsersName());
		tvDateTime.setText("发布时间:"+u.getNewsDateTime());
		tvContext.setText("内容:"+u.getContetx());		
		
	}
	
	private void setButtonOnClickListener() {
		// TODO Auto-generated method stub
		btnDelete.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				NewsDAO newsDAO=new NewsDAO(mContext);
				newsDAO.delete(rowid);
				Toast.makeText(mContext,"删除成功",Toast.LENGTH_LONG).show();
				Intent intent=new Intent(mContext,NewsListActivity.class);
				startActivity(intent);
			}
			
		});
		
		btnCancel.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(mContext,NewsListActivity.class);
				startActivity(intent);
			}
			
		});
		btnEdit.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(mContext,NewsEditActivity.class);
				intent.putExtra("rowid", rowid+"");
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
