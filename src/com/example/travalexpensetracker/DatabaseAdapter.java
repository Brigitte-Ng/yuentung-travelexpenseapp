package com.example.travalexpensetracker;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseAdapter {
	
	ArrayList<String> body = new ArrayList<String>();

	//create all the variables for SQL usage
	public static final String KEY_ROWID = "_id";
	public static final String KEY_EXPENSENAME = "expense_name";
	public static final String KEY_CAT = "category";
	public static final String KEY_EXPENSEDATE = "expense_date";
	public static final String KEY_SPENDING = "spending";
	public static final String KEY_CURRENCY = "currency";
	public static final String KEY_EXPENSEDESCRIPION = "expense_description";
	
	public static final String KEY_CLAIMNAME = "claim_name";
	public static final String KEY_CSTARTDATE = "cstartdate";
	public static final String KEY_CENDDATE = "cenddate";
	public static final String KEY_CSTATUS = "cstatus";
	public static final String KEY_CLAIMDESCRIPTION = "claim_description";
	private static final String TAG = "DatabaseAdapter";
	
	private DatabaseHelper DbHelper;
	private SQLiteDatabase Db;
	
	//SQL create statement
	private static final String DATABASE_CREATE = "create table travel(_id integer primary key autoincrement," +
			"expense_name char(30)," + "category char(30)," + "expense_date date," + "spending numeric," + "currency char(3)," + "expense_description char(50)," + 
			"claim_name char(30)," + "cstartdate date," + "cenddate date," + "cstatus char(30)," + "claim_description char(50));";

	private static final String DATABASE_NAME = "data";
	private static final String DATABASE_TABLE = "travel";
	private static final int DATABASE_VERSION = 2;
	private final Context ctx;

	private static class DatabaseHelper extends SQLiteOpenHelper{

		DatabaseHelper(Context context)
		{

			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db){
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS travel");
			onCreate(db);
		}
	}
	
	//constructor
	public DatabaseAdapter(Context ctx){

		this.ctx = ctx;
	}
	
	/**
	 * Open the notes database. If it cannot be opened, try to create a new
	 * instance of the database. If it cannot be created, throw an exception to
	 * signal the failure
	 * 
	 * @return this (self reference, allowing this to be chained in an
	 *         initialization call)
	 * @throws SQLException
	 *             if the database could be neither opened or created
	 */
	public DatabaseAdapter open() throws SQLException	{

		DbHelper = new DatabaseHelper(ctx);
		Db = DbHelper.getWritableDatabase();
		return this;
	}

	public void clearHistory()	{

		Db.execSQL("DELETE FROM " + DATABASE_TABLE);
	}

	public void close(){

		DbHelper.close();
	}
	
	/**
	 * Create a new note using the title and body provided. If the note is
	 * successfully created return the new rowId for that note, otherwise return
	 * a -1 to indicate failure.
	 * 
	 */
	public long createClaim(String claim_name, String cstartdate,String cenddate, String cstatus,String claim_description)
	{

		ContentValues initialValues = new ContentValues();
		
		initialValues.put(KEY_CLAIMNAME, claim_name);
		initialValues.put(KEY_CSTARTDATE, cstartdate);
		initialValues.put(KEY_CENDDATE, cenddate);
		initialValues.put(KEY_CSTATUS, cstatus);
		initialValues.put(KEY_CLAIMDESCRIPTION, claim_description);
		
		return Db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	public long createExpense(String expense_name,String category, String expense_date,String spending,
			String currency, String expense_description)
	{

		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_EXPENSENAME, expense_name);
		initialValues.put(KEY_CAT, category);
		initialValues.put(KEY_EXPENSEDATE, expense_date);
		initialValues.put(KEY_SPENDING, spending);
		initialValues.put(KEY_CURRENCY, currency);
		initialValues.put(KEY_EXPENSEDESCRIPION, expense_description);
		
		return Db.insert(DATABASE_TABLE, null, initialValues);
	}

	/**
	 * Delete the note with the given rowId
	 * 
	 * @param rowId
	 *            id of note to delete
	 * @return true if deleted, false otherwise
	 */
	public boolean deleterow(long rowId)
	{

		return Db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}
	
	/**
	 * Return a Cursor over the list of all notes in the database
	 * 
	 * @return Cursor over all notes
	 */

	public Cursor fetchAllClaims()
	{

		return Db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_EXPENSENAME, KEY_CAT, KEY_EXPENSEDATE,
				KEY_SPENDING, KEY_CURRENCY, KEY_EXPENSEDESCRIPION, KEY_CLAIMNAME, KEY_CSTARTDATE, KEY_CENDDATE, KEY_CSTATUS, KEY_CLAIMDESCRIPTION},
				null, null, null, null, null);
	}

	public ArrayList<String> fetchclaims(){

		Cursor c = Db.rawQuery("SELECT claim_name, cstartdate, cenddate, cstatus, claim_description FROM " + DATABASE_TABLE + " where expense_date = NULL", null);

		if (c.moveToFirst())
		{

			for (int i = 0; i < c.getCount(); i++)
			{

				body.add(c.getString(2));
				c.moveToNext();
			}
		}
		//c.close();
		return body;
	}

	/**
	 * Return a Cursor positioned at the note that matches the given rowId
	 * 
	 * @param rowId
	 *            id of note to retrieve
	 * @return Cursor positioned to matching note, if found
	 * @throws SQLException
	 *             if note could not be found/retrieved
	 */
	public Cursor fetchNote(long rowId) throws SQLException
	{

		Cursor mCursor =

		Db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_EXPENSENAME, KEY_CAT, KEY_EXPENSEDATE,
				KEY_SPENDING, KEY_CURRENCY, KEY_EXPENSEDESCRIPION, KEY_CSTARTDATE, KEY_CENDDATE, KEY_CSTATUS, KEY_CLAIMDESCRIPTION}, 
				KEY_ROWID + "=" + rowId, null, null, null, null,null);

		if (mCursor != null)
		{
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	/**
	 * Update the note using the details provided. The note to be updated is
	 * specified using the rowId, and it is altered to use the title and body
	 * values passed in
	 * 
	 * @param rowId
	 *            id of note to update
	 * @param title
	 *            value to set note title to
	 * @param body
	 *            value to set note body to
	 * @return true if the note was successfully updated, false otherwise
	 */
	public boolean updateClaim(long rowId, String claim_name, String cstartdate,String cenddate, String cstatus,String claim_description)
	{

		ContentValues args = new ContentValues();
		
		args.put(KEY_CLAIMNAME, claim_name);
		args.put(KEY_CSTARTDATE, cstartdate);
		args.put(KEY_CENDDATE, cenddate);
		args.put(KEY_CSTATUS, cstatus);
		args.put(KEY_CLAIMDESCRIPTION, claim_description);

		// SELECT name, length(name) FROM COMPANY
		return Db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}
	
	public boolean updateExpense(long rowId, String name,String category, String expense_date,String spending,
			String currency, String expense_description)
	{

		ContentValues args = new ContentValues();
		args.put(KEY_EXPENSENAME, name);
		args.put(KEY_CAT, category);
		args.put(KEY_EXPENSEDATE, expense_date);
		args.put(KEY_SPENDING, spending);
		args.put(KEY_CURRENCY, currency);
		args.put(KEY_EXPENSEDESCRIPION, expense_description);

		// SELECT name, length(name) FROM COMPANY
		return Db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}

}
