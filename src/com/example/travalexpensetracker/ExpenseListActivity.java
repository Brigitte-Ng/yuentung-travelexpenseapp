package com.example.travalexpensetracker;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ExpenseListActivity extends ListActivity {

	private DatabaseAdapter DbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_expense);
		
		DbHelper = new DatabaseAdapter(this);
		DbHelper.open();
		fillData();
		registerForContextMenu(getListView());
		Button addNewExpense = (Button) findViewById(R.id.addexpense);
		//allows users to jump to add_claim.xml
		addNewExpense.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View view){
				Intent intent = new Intent(ExpenseListActivity.this, AddExpenseActivity.class);
		    	startActivity(intent);
			}
		});
	}
	
	private void fillData()
	{

		Cursor travelCursor = DbHelper.fetchAllExpenses();
		startManagingCursor(travelCursor);

		// Create an array to specify the fields we want to display in the list (Name only)
		String[] from = new String[] { DatabaseAdapter.KEY_EXPENSENAME, DatabaseAdapter.KEY_EXPENSEDATE};

		// and an array of the fields we want to bind those fields to (in this
		// case just text1)
		int[] to = new int[] { R.id.exfirstrow, R.id.exsecondrow};

		// Now create a simple cursor adapter and set it to display
		SimpleCursorAdapter claims = new SimpleCursorAdapter(this,R.layout.view_expense, travelCursor, from, to);
		setListAdapter(claims);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_list, menu);
		return true;
	}
	
	//allows users to jump to add_expense.xml
	public void addNewExpense(View v){
    	//Toast.makeText(this, "Add New Expense", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ExpenseListActivity.this, AddExpenseActivity.class);
    	startActivity(intent);
    }
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{

		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this, AddExpenseActivity.class);
		i.putExtra(DatabaseAdapter.KEY_ROWID, id);
		startActivity(i);
	}
	
	/**protected ArrayList<Expense> expenseList;
	
	public ExpenseListActivity(){
		expenseList = new ArrayList<Expense>();
	}

	public Collection<Expense> getExpense() {
		// TODO Auto-generated method stub
		return expenseList;
	}

	public void addClaim(Expense Expense) {
		// TODO Auto-generated method stub
		expenseList.add(Expense);
	}

	public void removeClaim(int index) {
		// TODO Auto-generated method stub
		expenseList.remove(index);
	}

	public Expense chooseExpense(int index) {
		// TODO Auto-generated method stub
		return expenseList.get(index);
	}**/

}
