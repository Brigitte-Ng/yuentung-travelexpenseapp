package com.example.travalexpensetracker;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Expense extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_expense);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_expense, menu);
        return true;
    }
    
    protected String expenseName;
    
    public String getName() {
		// TODO Auto-generated method stub
		return this.expenseName;
	}
}
