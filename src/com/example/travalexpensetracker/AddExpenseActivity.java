package com.example.travalexpensetracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddExpenseActivity extends Activity {

	private EditText name;
	private EditText date;
    private EditText category;
    private EditText spending;
    private EditText currency;
    private EditText description;
    private Long mRowId;
    
    private DatabaseAdapter DbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_expense);
		
		DbHelper = new DatabaseAdapter(this);
        DbHelper.open();

        setContentView(R.layout.add_expense);
        setTitle(R.string.title_activity_add_expense);

        name = (EditText) findViewById(R.id.expensename);
        date = (EditText) findViewById(R.id.expensedate);
        category = (EditText) findViewById(R.id.expensecategory);
        spending = (EditText) findViewById(R.id.expenseamount);
        currency = (EditText) findViewById(R.id.expensecurrency);
        description = (EditText) findViewById(R.id.expensedescription);

        Button addexpense = (Button) findViewById(R.id.addexpense);
        
        mRowId = (savedInstanceState == null) ? null :
            (Long) savedInstanceState.getSerializable(DatabaseAdapter.KEY_ROWID);
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(DatabaseAdapter.KEY_ROWID)
									: null;
		}

		populateFields();
		
		addexpense.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
                //status.setText("in progress");
                updateExpense();
            	Intent intent = new Intent(AddExpenseActivity.this, ExpenseListActivity.class);
            	startActivity(intent);
            }
        });
	}
	
	private void populateFields() {
        if (mRowId != null) {
            Cursor expense = DbHelper.fetchNote(mRowId);
            startManagingCursor(expense);
            name.setText(expense.getString(
            		expense.getColumnIndexOrThrow(DatabaseAdapter.KEY_EXPENSENAME)));
            date.setText(expense.getString(
                    expense.getColumnIndexOrThrow(DatabaseAdapter.KEY_CAT)));
            category.setText(expense.getString(
                    expense.getColumnIndexOrThrow(DatabaseAdapter.KEY_EXPENSEDATE)));
            spending.setText(expense.getString(
                    expense.getColumnIndexOrThrow(DatabaseAdapter.KEY_SPENDING)));
            currency.setText(expense.getString(
                    expense.getColumnIndexOrThrow(DatabaseAdapter.KEY_CURRENCY)));
            description.setText(expense.getString(
                    expense.getColumnIndexOrThrow(DatabaseAdapter.KEY_EXPENSEDESCRIPION)));
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_expense, menu);
		return true;
	}
	
	private void updateExpense() {
		 String ename = name.getText().toString();   
		 String edate = date.getText().toString();
		 String ecat = category.getText().toString();
		 String espending = spending.getText().toString();
		 String ecurrency = currency.getText().toString();
		 String edescription = description.getText().toString();
		  
	      
	     if (mRowId == null) {
	        long id = DbHelper.createExpense(ename, edate, ecat, espending, ecurrency, edescription);
	         if (id > 0) {
	             mRowId = id;
	         }
	     } else {
	         DbHelper.updateExpense(mRowId, ename, edate, ecat, espending, ecurrency, edescription);
	     }
	    }
	
	public void addExpense(View v){
    	Toast.makeText(this, "Expense Item added", Toast.LENGTH_SHORT).show();
    }

}
