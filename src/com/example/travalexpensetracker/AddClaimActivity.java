package com.example.travalexpensetracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class AddClaimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_claim);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_claim, menu);
		return true;
	}
	
	public void addClaim(View v){
    	Toast.makeText(this, "Claim Item added", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(AddClaimActivity.this, ClaimListActivity.class);
    	startActivity(intent);
    }

}
