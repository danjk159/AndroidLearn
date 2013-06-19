package com.example.newsmanage;


import sqlite.beans.User;
import sqlite.dao.UsersDao;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btnLogout=null;
	Button btnLogin=null;
	Button btnRegister=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnLogout = (Button)findViewById(R.id.btnLogout);
		btnLogin=(Button)findViewById(R.id.btnLogin);
		btnRegister=(Button)findViewById(R.id.btnRegister);
		
		btnLogout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				MainActivity.this.finish();
			}			
		});
		
		btnLogin.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				UsersDao usersDao=new UsersDao(MainActivity.this);
				String userName=((EditText)findViewById(R.id.EtName)).getText().toString();
				String password=((EditText)findViewById(R.id.EtPassword)).getText().toString();
				User user=new User(userName,password);
				Cursor cursor=usersDao.find(user);
//				Toast.makeText(MainActivity.this, "搜索到"+cursor.getCount()+"用户", Toast.LENGTH_LONG).show();
				int UserCount=cursor.getCount();
				if(UserCount==1){
					MyPreference.getInstance(MainActivity.this).SetLoginName(userName);
					Intent intent=new Intent();
					intent.setClass(MainActivity.this, NewsListActivity.class);
					startActivity(intent);
				}else if(UserCount==0){
					Toast.makeText(MainActivity.this, "用户名或密码错误！", Toast.LENGTH_LONG).show();
					Toast.makeText(MainActivity.this, "用户名或密码错误！", Toast.LENGTH_LONG).show();
					}else if(UserCount>1){
					Toast.makeText(MainActivity.this, "系统逻辑btnLogin错误！", Toast.LENGTH_LONG).show();
				}
				
			}			
		});
		
		btnRegister.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				UsersDao usersDao=new UsersDao(MainActivity.this);
				String userName=((EditText)findViewById(R.id.EtName)).getText().toString();
				String password=((EditText)findViewById(R.id.EtPassword)).getText().toString();
				//try{
				User user=new User(userName,password);
				long rowid=usersDao.add(user);
				Toast.makeText(MainActivity.this, rowid+"保存成功", Toast.LENGTH_LONG).show();
				//}catch(Exception e){
					
				//}
			}			
		});
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
	

}
