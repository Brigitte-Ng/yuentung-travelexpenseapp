package com.example.travalexpensetracker;

import java.util.ArrayList;
import java.util.Collection;

import android.app.ListActivity;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;

public class ClaimListActivity extends ListActivity{

	private DatabaseAdapter DbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_claim);
		
		DbHelper = new DatabaseAdapter(this);
		DbHelper.open();
		fillData();
		registerForContextMenu(getListView());
		//Button addClaim = (Button) findViewById(R.id.addclaim);
		Button addNewClaim = (Button) findViewById(R.id.addclaim);
		/**addClaim.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View view){
				setResult(RESULT_OK);
				DbHelper.clearHistory();
				fillData();
			}
		});**/
		//allows users to jump to add_claim.xml
		addNewClaim.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View view){
				Intent intent = new Intent(ClaimListActivity.this, AddClaimActivity.class);
		    	startActivity(intent);
			}
		});
	}
	
	private void fillData()
	{

		Cursor travelCursor = DbHelper.fetchAllClaims();
		startManagingCursor(travelCursor);

		// Create an array to specify the fields we want to display in the list (Name only)
		String[] from = new String[] { DatabaseAdapter.KEY_EXPENSENAME, DatabaseAdapter.KEY_EXPENSEDATE };

		// and an array of the fields we want to bind those fields to (in this
		// case just text1)
		int[] to = new int[] { R.id.firstrow, R.id.secondrow};

		// Now create a simple cursor adapter and set it to display
		SimpleCursorAdapter claims = new SimpleCursorAdapter(this,R.layout.view_claim, travelCursor, from, to);
		setListAdapter(claims);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claim_list, menu);
		return true;
	}
	
	/**protected ArrayList<Claim> claimList;
	
	public ClaimListActivity(){
		claimList = new ArrayList<Claim>();
	}

	public Collection<Claim> getClaims() {
		// TODO Auto-generated method stub
		return claimList;
	}

	public void addClaim(Claim claim) {
		// TODO Auto-generated method stub
		claimList.add(claim);
	}

	public void removeClaim(int index) {
		// TODO Auto-generated method stub
		claimList.remove(index);
	}

	public Claim chooseClaim(int index) {
		// TODO Auto-generated method stub
		return claimList.get(index);
	}**/

}
