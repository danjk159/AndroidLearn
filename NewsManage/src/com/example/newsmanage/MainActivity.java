package com.example.newsmanage;


import sqlite.beans.User;
import sqlite.dao.UsersDao;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btnLogout=null;
	Button btnLogin=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnLogout = (Button)findViewById(R.id.btnLogout);
		btnLogin=(Button)findViewById(R.id.btnLogin);
		
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
				//try{
				User user=new User(userName,password);
				long rowid=usersDao.add(user);
				Toast.makeText(MainActivity.this, rowid+"保存成功", Toast.LENGTH_LONG).show();
				//}catch(Exception e){
					
				//}
			}			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
