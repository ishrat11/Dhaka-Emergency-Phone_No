package com.example.dhakaemergencyphone_no;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends Activity {

	
	Button btn1,btn2,btn3,btn4,btn5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1=(Button)findViewById(R.id.button1);
		btn2=(Button)findViewById(R.id.button2);
		btn3=(Button)findViewById(R.id.button3);
		btn4=(Button)findViewById(R.id.button4);
		btn5=(Button)findViewById(R.id.button5);
		//btn1.getBackground().setAlpha(100);

		
	}
	
	public void Police(View v){
		Intent intent=new Intent(this, listitme.class);
		startActivity(intent);
		btn1.getBackground().setAlpha(200);
		
	}

	
	public void Ambulance(View v){
		Intent intent=new Intent(this, listitme1.class);
		startActivity(intent);
		btn2.getBackground().setAlpha(200);
	}
	
	
	public void Fire(View v){
		Intent intent=new Intent(this, listitme2.class);
		startActivity(intent);
		btn3.getBackground().setAlpha(200);
	}
	
	public void Blood(View v){
		Intent intent=new Intent(this, listitme3.class);
		startActivity(intent);
		btn4.getBackground().setAlpha(200);
	}
	
	
	public void Women(View v){
		Intent intent=new Intent(this, listitme4.class);
		startActivity(intent);
		btn5.getBackground().setAlpha(200);
		
	}
}

