package com.example.dhakaemergencyphone_no;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

public class listitme extends Activity {

	CustomAdapter custom;

	int[] image = { R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon,
			R.drawable.phone_icon, R.drawable.phone_icon };
	String[] title = { "Adabar", "Airport", "Badda ", "Cantonment", "Demra",
			"Dhanmondi(8631941-2)", "Gulshan", "Hajaribag ", "Kafrul",
			"Kamrangirchar", "Khilgaon", "Khilkhet", "Kotwali ", "Lalbagh ",
			"Mirpur", "Mohammodpur", "Motijheel   ", "New Market", "Pallabi",
			"Paltan", "Ramna ", "Shah Ali", "Shympur", "Sobujbag ",
			"Sutrapur  ", "Tejgaon", "Turag", "Uttara" };

	String[] police_no = { "028153435", " 028951281", " 029882626 ",
			"  028829267 ", " 7516244", "028631941", "   029880234",
			" 029669900", "029871771", "027320323 ", "027219090", "028919364",
			"027116255", "027316300", "029001001", "029119960", " 029571000",
			"028631942", "028015122", " 029360802", "029350468 ",
			"  028060555", "027410691", "027219988", "027116233", " 029119444",
			" 028914664", " 028914126" };

	ArrayList<RowItem> rowitems;
	ListView lv;
	EditText etx;
	int textlength = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setTitle("Dhaka Metropolitan Police");
		setContentView(R.layout.list_item);

		rowitems = new ArrayList<RowItem>();
		for (int i = 0; i < title.length; i++) {
			RowItem item = new RowItem(image[i], title[i], police_no[i]);
			rowitems.add(item);
		}
		etx = (EditText) findViewById(R.id.inputSearch);
		
		lv = (ListView) findViewById(R.id.listView1);

		final CustomAdapter custom = new CustomAdapter(this, rowitems);
		lv.setAdapter(custom);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v,
					final int position, long id) {

				AlertDialog.Builder alert = new AlertDialog.Builder(
						listitme.this);
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
						sendIntent.setData(Uri.parse("sms:"));
						sendIntent.putExtra("sms_body", title[position] + '\n'  + police_no[position]);
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
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// listitme.this.
				// ((CustomAdapter) listitme.this.custom.getFilter()).filter(s);
				// (CustomAdapter)listitme.this.
				// listitme.this.custom.getFilter().filter(s);

				String text = etx.getText().toString()
						.toLowerCase(Locale.getDefault());
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
				// String text =
				// etx.getText().toString().toLowerCase(Locale.getDefault());
				// custom.filter(text);
			}
		});


		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		//hide keyboard :
		 getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



	}

}
