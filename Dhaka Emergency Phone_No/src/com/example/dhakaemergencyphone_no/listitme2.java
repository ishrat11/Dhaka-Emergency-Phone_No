package com.example.dhakaemergencyphone_no;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class listitme2 extends Activity {

	int[] image = { R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon,
			R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon
			};
	String[] title = {"Mirpur-10 (Circle)","Mirpur Training Complex","Mohammadpur","Postogola","Sadarghat ",
			"Tejgaon","Tongi","Khilgaon","Head Quarter"};

	String[] police_no = {"029001055","029001189","029112078 ","027410771","027119759","029898187","029801070",
			"027218329 "," 029556667 "};

	ArrayList<RowItem> rowitems;
	ListView lv;
	EditText etx;
	 int textlength = 0;
	 
	 CustomAdapter custom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setTitle("Dhaka Fire services");
		setContentView(R.layout.list_item);
		

		rowitems = new ArrayList<RowItem>();
		for (int i = 0; i < title.length; i++) {
			RowItem item = new RowItem(image[i], title[i], police_no[i]);
			rowitems.add(item);
		}
		etx=(EditText)findViewById(R.id.inputSearch);
		lv = (ListView) findViewById(R.id.listView1);
		
		final CustomAdapter custom=new CustomAdapter(this, rowitems);
		lv.setAdapter(custom);

		//lv.setAdapter(new CustomAdapter(this, rowitems));

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, final int position,
					long id) {
				
				
				AlertDialog.Builder alert = new AlertDialog.Builder(
						listitme2.this);
				final CharSequence[] items = { "Call", "Send via SMS" };
				alert.setTitle("Choose Option"); // Set Alert dialog title here
				alert.setItems(items, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int item) {

						if (items[item].equals("Call")) {
							// invoke call functionality
							onCall();
						}

						if (items[item].equals("Send via SMS")) {
							// invoke sms functionality
							onSms();
						}

					}

					private void onSms() {
						Intent sendIntent = new Intent(Intent.ACTION_VIEW);
						sendIntent.setData(Uri.parse("sms:"
								));
						sendIntent.putExtra("sms_body",title[position] + '\n'  + police_no[position] ); 
						startActivity(sendIntent);

					}

					public void onCall() {
						Intent intent = new Intent(Intent.ACTION_CALL, Uri
								.parse("tel:" + police_no[position]));
						startActivity(intent);

					}
				});
				
				AlertDialog alert1 = alert.create();
				alert1.show();
				alert1.setCanceledOnTouchOutside(true);
			}
		});

	etx.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//listitme.this.
			//	((CustomAdapter) listitme.this.custom.getFilter()).filter(s);
				//(CustomAdapter)listitme.this.
				//listitme.this.custom.getFilter().filter(s);
				
				String text = etx.getText().toString().toLowerCase(Locale.getDefault());
                custom.filter(text);
				
			
				
				
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				//String text = etx.getText().toString().toLowerCase(Locale.getDefault());
               // custom.filter(text);
			}
		});
		
	getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
	//hide keyboard :
	 getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

	}
}
