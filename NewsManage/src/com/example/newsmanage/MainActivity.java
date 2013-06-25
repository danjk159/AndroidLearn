package com.example.newsmanage;


import sqlite.beans.User;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//登录界面的activity，这是最烂的写法，稍微好点的见NewsCardActivity.java
public class MainActivity extends Activity {
	//按钮类，需要对应xml界面文件使用
	Button btnLogout=null;
	Button btnLogin=null;
	Button btnRegister=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置对应的xml文件
		setContentView(R.layout.activity_main);
		//将本activity加入AppManager类中进行程序activity控制
		AppManager.getAppManager().addActivity(this);
		//设置对应的按钮，这需要在setContentView中先设置再设置这个，
		//并且这些按钮都是指的是同一个xml文件
		btnLogout = (Button)findViewById(R.id.btnLogout);
		btnLogin=(Button)findViewById(R.id.btnLogin);
		btnRegister=(Button)findViewById(R.id.btnRegister);
		//设置按钮的监听器
		btnLogout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				//关闭本activity，其它不关闭
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
					//使用MyPreference类存储登录的用户名
					MyPreference.getInstance(MainActivity.this).SetLoginName(userName);
					//跳转方法intent.
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
	
	//创建时设置menu菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//设置对应的xml文件
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	//设置menu点击的效果
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		//点击对应文字时的操作
		case R.id.Leave:
			//调用AppManager类方法关闭所有activity
			AppManager.getAppManager().AppExit(this);
			break;
		}
		return super.onOptionsItemSelected(item);
	}


}
