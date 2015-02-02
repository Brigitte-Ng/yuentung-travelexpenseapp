package com.example.travalexpensetracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewStub;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //allows users to jump to list_claim.xml
    public void seeClaim(View v){
    	//Toast.makeText(this, "See Claim", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainActivity.this, ClaimListActivity.class);
    	startActivity(intent);
    }
    
  //allows users to jump to list_expense.xml
    public void seeExpense(View v){
    	//Toast.makeText(this, "See Expense", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainActivity.this, ExpenseListActivity.class);
    	startActivity(intent);
    }
    
}
