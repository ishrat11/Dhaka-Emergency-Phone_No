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

public class listitme1 extends Activity{

	CustomAdapter custom;
	EditText etx;
	 int textlength = 0;
	int[] image = { R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon,
			R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon,
			R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon,R.drawable.phone_icon};
	String[] title = { "Dhaka Medical College Hospital","Salimullah Medical College(7319002-6)","Shahid Suhrawardi Hospital","Shishu Hospital",
			"National Heart Institute(9122560-72)","Holy Family Hospital","Heart Hospital","Dhaka Eye Hospital","Medinova Medical Service Ltd.",
			"Monowara General Hospital","Rafa Ambulance Service","Red Crescent Ambulance","South Asian Hospital","Day-Night Ambulance Service",
			"BARDEM(9661551-60)","BSMMU(9661551-60)","Ad-Din Hospital","Al-Markazul Islami Ambulance Service","Green Ambulance Service"};

	String[] police_no = {"028626812","027319002","029130800","029113512","029122560","029113512","029801874","028014476","028113721",
			"028318135","029110663","029358799","028616565"," 029123073","029661551"," 029661551","029362929"," 029127867","029334121"};

	ArrayList<RowItem> rowitems;
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setTitle("Dhaka Ambulance Service");
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
						listitme1.this);
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
