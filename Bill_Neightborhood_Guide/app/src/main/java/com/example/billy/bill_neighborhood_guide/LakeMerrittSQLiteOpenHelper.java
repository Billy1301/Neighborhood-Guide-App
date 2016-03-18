package com.example.billy.bill_neighborhood_guide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by drewmahrt on 12/29/15.
 */
public class LakeMerrittSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = LakeMerrittSQLiteOpenHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 1;  // watch out how you name.. no space
    public static final String DATABASE_NAME = "LAKE_MERRITT_DB";
    public static final String LAKE_MERRITT_TABLE_NAME = "LAKE_MERRITT_LIST";
    public static final String LAKE_MERRITT_PLACE_NAME = "PLACE_NAME";
    public static final String LAKE_MERRITT_ADDRESS = "ADDRESS";
    public static final String LAKE_MERRITT_PHONE = "PHONE_NUMBER";
    public static final String LAKE_MERRITT_RATINGS = "RATINGS";
    public static final String LAKE_MERRITT_PRICE = "PRICE";
    public static final String LAKE_MERRITT_TYPE = "TYPE";
    public static final String LAKE_MERRITT_DESCRIPTION = "DESCRIPTION"; // remember to set up R.String.info!!!!!


    public static final String COL_ID = "_id";
    public static final String COL_CATEGORY_LIST = "CATEGORY_LIST";

    public static final String[] LAKE_MERRITT_COLUMNS = {COL_ID,COL_CATEGORY_LIST, LAKE_MERRITT_PLACE_NAME, LAKE_MERRITT_ADDRESS,
                                    LAKE_MERRITT_PHONE, LAKE_MERRITT_RATINGS, LAKE_MERRITT_PRICE, LAKE_MERRITT_TYPE};

    private static final String CREATE_LAKE_MERRITT_LIST_TABLE =
            "CREATE TABLE " + LAKE_MERRITT_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY, " +
                    COL_CATEGORY_LIST + " TEXT, " + LAKE_MERRITT_PLACE_NAME + " TEXT, "
                    + LAKE_MERRITT_ADDRESS + " TEXT, " + LAKE_MERRITT_PHONE + " TEXT, " + LAKE_MERRITT_RATINGS
                    + " TEXT, " + LAKE_MERRITT_PRICE + " TEXT, " + LAKE_MERRITT_TYPE + " TEXT )";


    private static LakeMerrittSQLiteOpenHelper instance;

    public static LakeMerrittSQLiteOpenHelper getInstance(Context context){
        if(instance == null){
            instance = new LakeMerrittSQLiteOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    private LakeMerrittSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LAKE_MERRITT_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LAKE_MERRITT_TABLE_NAME);
        this.onCreate(db);
    }


    public void listInsert(int id, String categoryList, String placeName, String address, String phoneNumber, String ratings, String price, String type){

        SQLiteDatabase db = getWritableDatabase();

        // create a new content value to store values
        ContentValues values = new ContentValues();
        values.put(COL_ID, id);
        values.put(LAKE_MERRITT_RATINGS, ratings);
        values.put(LAKE_MERRITT_ADDRESS, address);
        values.put(LAKE_MERRITT_TYPE, type);
        values.put(COL_CATEGORY_LIST, categoryList);
        values.put(LAKE_MERRITT_PRICE, price);
        values.put(LAKE_MERRITT_PLACE_NAME, placeName);
        values.put(LAKE_MERRITT_PHONE, phoneNumber);


        db.insert(LAKE_MERRITT_TABLE_NAME, null, values);
//        db.close();
    }

    public Cursor searchLakeMerrittList(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        // to search through 2 columns
        Cursor cursor = db.query(true, LAKE_MERRITT_TABLE_NAME,
                new String[]{COL_CATEGORY_LIST, LAKE_MERRITT_PLACE_NAME, LAKE_MERRITT_TYPE},
                COL_CATEGORY_LIST + " LIKE" + "'%" + query + "%' OR " + LAKE_MERRITT_PLACE_NAME +
                        " LIKE" + "'%" + query + "%' OR " + LAKE_MERRITT_TYPE + " LIKE" + "'%" + query + "%'",
                null,
                null,
                null,
                null,
                null,
                null);
        return cursor;
    }


    public Cursor getLakeMerrittLists(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(LAKE_MERRITT_TABLE_NAME, // a. table
                LAKE_MERRITT_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }

    public String getDescriptionById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(LAKE_MERRITT_TABLE_NAME,
                LAKE_MERRITT_COLUMNS,
                COL_ID+" = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_ID));
        } else {
            return "No Description Found";
        }
    }

    public String getLakeMerrittByCategoryList(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(LAKE_MERRITT_TABLE_NAME,
                        LAKE_MERRITT_COLUMNS,
                        COL_CATEGORY_LIST+ " = ?",
                        null,
                        null,
                        null,
                        null,
                        null);
        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_CATEGORY_LIST));
        } else {
            return "No Category Found";
        }
    }

    public String getLakeMerrittByPlaceName(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(LAKE_MERRITT_TABLE_NAME,
                LAKE_MERRITT_COLUMNS,
                LAKE_MERRITT_PLACE_NAME+ " = ?",
                null,
                null,
                null,
                null,
                null);
        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(LAKE_MERRITT_PLACE_NAME));
        } else {
            return "No Place Found";
        }

    }


    public Cursor getRestaurantList(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(LAKE_MERRITT_TABLE_NAME,
                LAKE_MERRITT_COLUMNS,
                COL_CATEGORY_LIST+ " = ?",
                new String[]{"Restaurants"},
                null,
                null,
                null,
                null);
            return cursor;

    }

    public Cursor getActivitiesList(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(LAKE_MERRITT_TABLE_NAME,
                LAKE_MERRITT_COLUMNS,
                COL_CATEGORY_LIST+ " = ?",
                new String[]{"Activities"},
                null,
                null,
                null,
                null);
        return cursor;

    }

}
