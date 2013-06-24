package com.example.newsmanage;

import sqlite.beans.News;
import sqlite.dao.NewsDAO;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NewsCardActivity extends Activity {
	int rowid=-1;
	Context mContext;
	
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
		setContentView(R.layout.activity_news_card);
		
		connectContentView();
		try{
		rowid=Integer.parseInt(getIntent().getStringExtra("rowid")); 
		}catch(Exception e){
			Toast.makeText(mContext,"NewsCardActivity出错:"+e.getMessage(),Toast.LENGTH_LONG).show();
		}
		
		if(rowid!=-1){
			fillData();
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
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.news_card, menu);
//		return true;
//	}

}
