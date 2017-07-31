package com.aurora.personalassistant;


import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ConvertActivity extends SherlockActivity
{

    private static final String URL = "http://openexchangerates.org/api/latest.json?app_id=f2381be937714114b86274f5ea360b7d";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        

        Button btnDo = (Button)findViewById(R.id.buttonDo);
    	final EditText  usdValue = (EditText) findViewById(R.id.editTextUSD);
		final TextView gbpValue = (TextView) findViewById(R.id.textViewGBP);
		final TextView eurValue = (TextView) findViewById(R.id.textViewEURO);
		final TextView inrValue = (TextView) findViewById(R.id.textViewINR);
        
       btnDo.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			
			 if (!   usdValue.getText().toString().equals("") ) {   
				AsyncHttpClient client = new AsyncHttpClient();
				client.get(URL, new AsyncHttpResponseHandler() {

					@Override
					public void onFailure(Throwable arg0, String arg1) {
						// TODO Auto-generated method stub
						super.onFailure(arg0, arg1);
								}

					@Override
					public void onFinish() {
						// TODO Auto-generated method stub
						super.onFinish();
					}

					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
					}

					@Override
					public void onSuccess(String response) {
						Log.i("Currencyconvertor", "HTTP Sucess");
						// TODO Auto-generated method stub

						try {
							JSONObject jsonObj = new JSONObject(response);
							JSONObject ratesObject = jsonObj
									.getJSONObject("rates");

							Double gbpRate = ratesObject.getDouble("GBP");
							Double eurRate = ratesObject.getDouble("EUR");
							Double inrRate = ratesObject.getDouble("INR");

							Log.i("Currencyconvertor", "GBP:" + gbpRate);
							Log.i("Currencyconvertor", "EUR:" + eurRate);
							Log.i("Currencyconvertor", "INR:" + inrRate);


						 
								Double  usds = Double.valueOf( usdValue.getText().toString()); 
								Double gbps = usds * gbpRate;
								Double euros =  usds * eurRate;
								Double inrs =  usds * inrRate;
								
								inrValue.setText("INR:" + String.valueOf(inrs));
								gbpValue.setText("GBP:" + String.valueOf(gbps));
								eurValue.setText("EURO:" + String.valueOf(euros));
								
								
							} 

						 catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				});
				
			}      
			 else {
					Toast.makeText(getApplicationContext(),
							"please enter a USD value ",  
							Toast.LENGTH_LONG).show();
				}
		     
		}
       });
       
  
       
	}}
    
   