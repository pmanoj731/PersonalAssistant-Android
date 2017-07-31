package com.aurora.personalassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class PrincipleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principle, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			startActivity(new Intent(PrincipleActivity.this,ConvertActivity.class));
			break;
		case R.id.button2:
			startActivity(new Intent(PrincipleActivity.this,SMSActivity.class));
			break;
		case R.id.button3:
			startActivity(new Intent(PrincipleActivity.this,ProfileActivity.class));
            break;

		default:
			break;
		}
	}

}
