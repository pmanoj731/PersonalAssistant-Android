package com.aurora.personalassistant;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMSActivity extends Activity {
	public Button sendSMS;
	public EditText msgTxt;
	public EditText numTxt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_sms);
		sendSMS = (Button) findViewById(R.id.sendSMS);
		sendSMS.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(android.content.Intent.ACTION_VIEW);
				i.putExtra("address", "5556; 5558");
				// i.putExtra("address", "5556; 5558; 5560");
				// here i can send message to emulator 5556,5558,5560
				// you can change in real device
				i.putExtra("sms_body", "Hello my friends!");
				i.setType("vnd.android-dir/mms-sms");
				startActivity(i);
			}
		});
	}

	protected void sendMsg(String theNumber, String myMsg) {
		String SENT = "Message Sent";
		String DELIVERED = "Message Delivered";

		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(
				SENT), 0);
		PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
				new Intent(DELIVERED), 0);

		registerReceiver(new BroadcastReceiver() {
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(SMSActivity.this, "SMS sent",
							Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(getBaseContext(), "Generic Failure",
							Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(getBaseContext(), "No service",
							Toast.LENGTH_LONG).show();
				}
			}
		}, new IntentFilter(SENT));

		registerReceiver(new BroadcastReceiver() {
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), "SMS Delivered",
							Toast.LENGTH_LONG).show();
					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(getBaseContext(), "SMS not delivered",
							Toast.LENGTH_LONG).show();
					break;
				}
			}
		}, new IntentFilter(DELIVERED));

		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(theNumber, null, myMsg, sentPI, deliveredPI);
	}

	// //

	// ///

	// private void registerReceiver(BroadcastReceiver broadcastReceiver,
	// IntentFilter intentFilter) {
	// TODO Auto-generated method stub

	// }


}
