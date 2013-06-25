package com.example.newsmanage;

import sqlite.beans.User;
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
import android.widget.TextView;
import android.widget.Toast;

public class UserEditActivity extends Activity {
	TextView tvUserName = null;
	Button btnChangePwd = null;
	Button btnCancel = null;
	Context mContext=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext=this;
		AppManager.getAppManager().addActivity(this);
		setContentView(R.layout.activity_user_edit);
		tvUserName = (TextView) findViewById(R.id.tvUserEditName);
		tvUserName.setText(MyPreference.getInstance(mContext)
				.getLoginName());
		btnChangePwd = (Button) findViewById(R.id.btnUserEditChangePwd);
		btnChangePwd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				User user = new User();
				user.setUserName(MyPreference
						.getInstance(mContext).getLoginName());
				user.setPassword(((TextView) findViewById(R.id.EtUserEditPassword))
						.getText().toString());
				UsersDao userDao = new UsersDao(mContext);
				userDao.update(user);
				Toast.makeText(UserEditActivity.this, "修改成功", Toast.LENGTH_LONG)
						.show();
				Intent intent = new Intent();
				intent.setClass(mContext, NewsListActivity.class);
				startActivity(intent);
			}
		});

		btnCancel = (Button) findViewById(R.id.btnUserEditCancel);
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
