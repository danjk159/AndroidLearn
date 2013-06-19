package com.example.newsmanage;

import sqlite.beans.User;
import sqlite.dao.UsersDao;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserEditActivity extends Activity {
	TextView tvUserName=null;
	Button btnChangePwd=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_edit);
		tvUserName=(TextView)findViewById(R.id.tvUserEditName);
		tvUserName.setText(MyPreference.getInstance(UserEditActivity.this).getLoginName());
		btnChangePwd=(Button)findViewById(R.id.btnUserEditChangePwd);
		btnChangePwd.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				User user=new User();
				user.setUserName(MyPreference.getInstance(UserEditActivity.this).getLoginName());
				user.setPassword(((TextView)findViewById(R.id.EtUserEditPassword)).getText().toString());
				UsersDao userDao=new UsersDao(UserEditActivity.this);
				userDao.update(user);
				Toast.makeText(UserEditActivity.this,"修改成功" , Toast.LENGTH_LONG).show();
				Intent intent=new Intent();
				intent.setClass(UserEditActivity.this, MainActivity.class);
				startActivity(intent);
			}			
		});
	}


}
