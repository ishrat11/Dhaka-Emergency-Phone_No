package com.example.dhakaemergencyphone_no;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class listitme4 extends Activity

{

	CustomAdapter custom;

	int[] image = { R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon, R.drawable.phone_icon,R.drawable.phone_icon };
	String[] title = { "24 Hours Support", "Helpline", "Helpline", "Helpline",
			"Helpline" };

	String[] police_no = { "01745774487", "01755556644", "01755556645",
			"01733219005", "01733219030" };

	ArrayList<RowItem> rowitems;
	ListView lv;
	EditText etx;
	int textlength = 0;

	final Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setTitle("Women Support");
		setContentView(R.layout.list_item1);

		rowitems = new ArrayList<RowItem>();
		for (int i = 0; i < title.length; i++) {
			RowItem item = new RowItem(image[i], title[i], police_no[i]);
			rowitems.add(item);
		}
		// etx = (EditText) findViewById(R.id.inputSearch);

		PhoneCallListener phoneListener = new PhoneCallListener();
		TelephonyManager telephonyManager = (TelephonyManager) this
			.getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);
		lv = (ListView) findViewById(R.id.listView1);

		final CustomAdapter custom = new CustomAdapter(this, rowitems);
		lv.setAdapter(custom);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v,
					final int position, long id) {

				AlertDialog.Builder alert = new AlertDialog.Builder(
						listitme4.this);
				final CharSequence[] items = { "Call", "Send via SMS" };
				alert.setTitle("Choose Option"); // Set Alert dialog title here
				alert.setItems(items, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int item) {

						if (items[item].equals("Call")) {
							
							onCall();
						}

						if (items[item].equals("Send via SMS")) {
							
							onSms();
						}

					}

					private void onSms() {
						Intent sendIntent = new Intent(Intent.ACTION_VIEW);
						sendIntent.setData(Uri.parse("sms:"));
						sendIntent.putExtra("sms_body", title[position] + '\n'  + police_no[position]);
						startActivity(sendIntent);

					}

					public void onCall() {
						
						
						Intent intent = new Intent(Intent.ACTION_CALL, Uri
								.parse("tel:" + police_no[position]));
						startActivity(intent);
					//	finish();
						

					}
				});

				AlertDialog alert1 = alert.create();
				alert1.show();
				alert1.setCanceledOnTouchOutside(true);

			}
		});

		

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		// hide keyboard :
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

	}

public class PhoneCallListener extends PhoneStateListener{

	private boolean isPhoneCalling = false;
	String LOG_TAG = "LOGGING 123";
	 
	@Override
	public void onCallStateChanged(int state, String incomingNumber) {

		if (TelephonyManager.CALL_STATE_RINGING == state) {
			// phone ringing
			Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
		}

		if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
			// active
			Log.i(LOG_TAG, "OFFHOOK");

			isPhoneCalling = true;
		}

		if (TelephonyManager.CALL_STATE_IDLE == state) {
			// run when class initial and phone call ended, 
			// need detect flag from CALL_STATE_OFFHOOK
			Log.i(LOG_TAG, "IDLE");

			if (isPhoneCalling) {

				Log.i(LOG_TAG, "restart app");

				// restart app
				Intent i = getBaseContext().getPackageManager()
					.getLaunchIntentForPackage(
						getBaseContext().getPackageName());
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				

				isPhoneCalling = false;
			}

		}
	}

}
}
