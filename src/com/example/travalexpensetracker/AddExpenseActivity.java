package com.example.travalexpensetracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class AddExpenseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_expense);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_expense, menu);
		return true;
	}
	
	public void addExpense(View v){
    	Toast.makeText(this, "Expense Item added", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(AddExpenseActivity.this, ExpenseListActivity.class);
    	startActivity(intent);
    }

}
