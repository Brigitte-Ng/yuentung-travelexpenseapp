package com.example.travalexpensetracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddClaimActivity extends Activity {
	
	private EditText name;
	private EditText startdate;
    private EditText enddate;
    private EditText status;
    private EditText description;
    private Long mRowId;
    
    private DatabaseAdapter DbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_claim);
        DbHelper = new DatabaseAdapter(this);
        DbHelper.open();

        setContentView(R.layout.add_claim);
        setTitle(R.string.title_activity_add_claim);

        name = (EditText) findViewById(R.id.claimname);
        startdate = (EditText) findViewById(R.id.claimstartdate);
        enddate = (EditText) findViewById(R.id.claimenddate);
        status = (EditText) findViewById(R.id.claimstatus);
        description = (EditText) findViewById(R.id.claimdescription);

        Button save = (Button) findViewById(R.id.saveclaim);
        Button submit = (Button) findViewById(R.id.submitclaim);
        
        status.setText("in progress");

        mRowId = (savedInstanceState == null) ? null :
            (Long) savedInstanceState.getSerializable(DatabaseAdapter.KEY_ROWID);
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(DatabaseAdapter.KEY_ROWID)
									: null;
		}

		populateFields();
		
		save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
                //status.setText("in progress");
                updateClaim();
            	Intent intent = new Intent(AddClaimActivity.this, ClaimListActivity.class);
            	startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
                status.setText("submitted");
                updateClaim();
            	Intent intent = new Intent(AddClaimActivity.this, ClaimListActivity.class);
            	startActivity(intent);
            }
        });
        //updateClaim();
	}
	
	private void populateFields() {
        if (mRowId != null) {
            Cursor claim = DbHelper.fetchNote(mRowId);
            startManagingCursor(claim);
            name.setText(claim.getString(
            		claim.getColumnIndexOrThrow(DatabaseAdapter.KEY_CLAIMNAME)));
            startdate.setText(claim.getString(
                    claim.getColumnIndexOrThrow(DatabaseAdapter.KEY_CSTARTDATE)));
            enddate.setText(claim.getString(
                    claim.getColumnIndexOrThrow(DatabaseAdapter.KEY_CENDDATE)));
            status.setText(claim.getString(
                    claim.getColumnIndexOrThrow(DatabaseAdapter.KEY_CSTATUS)));
            description.setText(claim.getString(
                    claim.getColumnIndexOrThrow(DatabaseAdapter.KEY_CLAIMDESCRIPTION)));
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_claim, menu);
		return true;
	}
	
	 private void updateClaim() {
		 String cname = name.getText().toString();   
		 String cstartdate = startdate.getText().toString();
		 String cenddate = enddate.getText().toString();
		 String cstatus = status.getText().toString();
		 String cdescription = description.getText().toString();
		  
	      
	     if (mRowId == null) {
	        long id = DbHelper.createClaim(cname, cstartdate,cenddate, cstatus, cdescription);
	         if (id > 0) {
	             mRowId = id;
	         }
	     } else {
	         DbHelper.updateClaim(mRowId, cname, cstartdate,cenddate, cstatus, cdescription);
	     }
	    }
	
	//for user to add an item
	public void addClaim(View v){
    	Toast.makeText(this, "Claim Item added", Toast.LENGTH_SHORT).show();
    }
	public void save(View v){
    	Toast.makeText(this, "Claim Item added", Toast.LENGTH_SHORT).show();
    }

}
