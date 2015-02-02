package com.example.travalexpensetracker;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Claim extends Activity {

	protected String claimName;
	
	public Claim(String claimName) {
		// TODO Auto-generated constructor stub
		this.claimName = claimName;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_claim);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_claim, menu);
		return true;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.claimName;
	}
	

}
