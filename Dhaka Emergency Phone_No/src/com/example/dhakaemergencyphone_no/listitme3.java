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

public class listitme3 extends Activity{

	

	int[] image = { R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon
			
			};
	String[] title = {"Dhaka Medical College(Shandhani )","Dhaka Dental College Branch(Shandhani )","Bangladesh Medical College(Shandhani )",
			"Red Crescent Blood Bank ","Shandhani International Eye Bank","Sir Salimullah College Blood Bank","Islami Bank Hospital Blood Bank",
			"Quantum Foundation"};

	String[] police_no = { "029668690"," 029002035","029124619"," 029116563","029124353","027319123","028317090","029351969"};

	ArrayList<RowItem> rowitems;
	ListView lv;
	EditText etx;
	 int textlength = 0;
	 
	 CustomAdapter custom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setTitle("Dhaka Blood Bank ");
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
						listitme3.this);
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
				
			}
		});
		
	getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
	//hide keyboard :
	 getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

	}
}
