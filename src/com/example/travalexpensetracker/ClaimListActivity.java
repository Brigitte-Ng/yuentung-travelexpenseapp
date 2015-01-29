package com.example.travalexpensetracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class ClaimListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_claim);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claim_list, menu);
		return true;
	}
	
	public void addNewClaim(View v){
    	//Toast.makeText(this, "Add New Claim", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ClaimListActivity.this, AddClaimActivity.class);
    	startActivity(intent);
    }

}
