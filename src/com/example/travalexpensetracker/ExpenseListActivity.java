package com.example.travalexpensetracker;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ExpenseListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_expense);
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
	
	protected ArrayList<Expense> expenseList;
	
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
	}

}
