package com.example.billy.lakemerrittguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Billy on 3/18/16.
 */
public class LakeMerrittSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = LakeMerrittSQLiteOpenHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LAKE_MERRITT_DB";
    public static final String COL_LAKE_MERRITT_TABLE_NAME = "LAKE_MERRITT_LIST";

    public static final String COL_LAKE_MERRITT_PLACE_NAME = "PLACE_NAME";
    public static final String COL_LAKE_MERRITT_ADDRESS = "ADDRESS";
    public static final String COL_LAKE_MERRITT_PHONE = "PHONE_NUMBER";
    public static final String COL_LAKE_MERRITT_RATINGS = "RATINGS";
    public static final String COL_LAKE_MERRITT_PRICE = "PRICE";
    public static final String COL_LAKE_MERRITT_TYPE = "TYPE";
    public static final String COL_LAKE_MERRITT_DESCRIPTION = "DESCRIPTION"; // remember to set up R.String.info!!!!!


    public static final String COL_ID = "_id";
    public static final String COL_CATEGORY_LIST = "CATEGORY_LIST";

    public static final String[] LAKE_MERRITT_COLUMNS = {COL_ID,COL_CATEGORY_LIST, COL_LAKE_MERRITT_PLACE_NAME, COL_LAKE_MERRITT_ADDRESS,
            COL_LAKE_MERRITT_PHONE, COL_LAKE_MERRITT_RATINGS, COL_LAKE_MERRITT_PRICE, COL_LAKE_MERRITT_TYPE, COL_LAKE_MERRITT_DESCRIPTION};

    private static final String CREATE_LAKE_MERRITT_LIST_TABLE =
            "CREATE TABLE " + COL_LAKE_MERRITT_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY, " +
                    COL_CATEGORY_LIST + " TEXT, " +
                    COL_LAKE_MERRITT_PLACE_NAME + " TEXT, " +
                    COL_LAKE_MERRITT_ADDRESS + " TEXT, " +
                    COL_LAKE_MERRITT_PHONE + " TEXT, " +
                    COL_LAKE_MERRITT_RATINGS + " TEXT, " +
                    COL_LAKE_MERRITT_PRICE + " TEXT, " +
                    COL_LAKE_MERRITT_TYPE + " TEXT, " +
                    COL_LAKE_MERRITT_DESCRIPTION + " TEXT)";


    public static final String DROP_LAKE_MERRITT_TABLE = "DROP TABLE IF EXISTS " + COL_LAKE_MERRITT_TABLE_NAME;


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
        db.execSQL(DROP_LAKE_MERRITT_TABLE);
        this.onCreate(db);
    }


    public void listInsert(String categoryList, String placeName, String address, String phoneNumber, String ratings, String price, String type, String description){
        SQLiteDatabase db = getWritableDatabase();
        // create a new content value to store values
        ContentValues values = new ContentValues();
        //values.put(COL_ID, id);
        values.put(COL_LAKE_MERRITT_RATINGS, ratings);
        values.put(COL_LAKE_MERRITT_ADDRESS, address);
        values.put(COL_LAKE_MERRITT_TYPE, type);
        values.put(COL_CATEGORY_LIST, categoryList);
        values.put(COL_LAKE_MERRITT_PRICE, price);
        values.put(COL_LAKE_MERRITT_PLACE_NAME, placeName);
        values.put(COL_LAKE_MERRITT_PHONE, phoneNumber);
        values.put(COL_LAKE_MERRITT_DESCRIPTION, description);


        db.insert(COL_LAKE_MERRITT_TABLE_NAME, null, values);
        db.close();
    }

    public Cursor searchLakeMerrittList(String query){
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(true, COL_LAKE_MERRITT_TABLE_NAME,
                new String[]{COL_LAKE_MERRITT_PRICE, COL_LAKE_MERRITT_PLACE_NAME, COL_LAKE_MERRITT_TYPE},
                COL_LAKE_MERRITT_PRICE + " LIKE" + "'%" + query + "%' OR " + COL_LAKE_MERRITT_PLACE_NAME +
                        " LIKE" + "'%" + query + "%' OR " + COL_LAKE_MERRITT_TYPE + " LIKE" + "'%" + query + "%'",
                new String[]{"%" + query + "%"},
                null,
                null,
                null,
                null);
        return cursor;
    }


    public Cursor getLakeMerrittLists(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME, // a. table
                LAKE_MERRITT_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, //COL_LAKE_MERRITT_TYPE, // e. group by
                null, // f. having
                null, //COL_LAKE_MERRITT_PLACE_NAME, // g. order by
                null); // h. limit
        return cursor;
    }

    public String getCategoryListById(int _id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME,
                new String[] {COL_CATEGORY_LIST},
                COL_ID+ " = ?",
                new String[]{String.valueOf(_id)},
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

    public String getPlaceNameById(int _id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME,
                new String[]{COL_LAKE_MERRITT_PLACE_NAME},
                COL_ID + " = ?",
                new String[]{String.valueOf(_id)},
                null,
                null,
                null,
                null);
        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_LAKE_MERRITT_PLACE_NAME));
        } else {
            return "No Place Found";
        }

    }

    public String getAddressById(int _id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME,
                new String[]{COL_LAKE_MERRITT_ADDRESS},
                COL_ID + " = ?",
                new String[]{String.valueOf(_id)},
                null,
                null,
                null,
                null);
        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_LAKE_MERRITT_PLACE_NAME));
        } else {
            return "No Location Found";
        }

    }

    public String getPriceById(int _id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME,
                new String[]{COL_LAKE_MERRITT_PRICE},
                COL_ID + " = ?",
                new String[]{String.valueOf(_id)},
                null,
                null,
                null,
                null);
        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_LAKE_MERRITT_PLACE_NAME));
        } else {
            return "Nothing found";
        }

    }
    public String getPhoneById(int _id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME,
                new String[]{COL_LAKE_MERRITT_PHONE},
                COL_ID + " = ?",
                new String[]{String.valueOf(_id)},
                null,
                null,
                null,
                null);
        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_LAKE_MERRITT_PHONE));
        } else {
            return "Nothing found";
        }

    }


    public String getTypeById(int _id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME,
                new String[]{COL_LAKE_MERRITT_TYPE},
                COL_ID + " = ?",
                new String[]{String.valueOf(_id)},
                null,
                null,
                null,
                null);
        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_LAKE_MERRITT_PLACE_NAME));
        } else {
            return "Nothing found";
        }

    }

    public int getDescriptionById(int _id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME,
                new String[]{COL_LAKE_MERRITT_DESCRIPTION},
                COL_ID + " = ?",
                new String[]{String.valueOf(_id)},
                null,
                null,
                null,
                null);
        if(cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndex(COL_LAKE_MERRITT_DESCRIPTION));
        } else {
            return -22;
        }

    }

    public Cursor getRestaurantList(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME,
                LAKE_MERRITT_COLUMNS,
                COL_CATEGORY_LIST+ " = ?",
                new String[]{"Restaurants"},
                null,
                null,
                null,
                null);
        return cursor;

    }

    // this is for testing filter only... use if work, delete if not
    public Cursor testingGetKoreanList(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME,
                LAKE_MERRITT_COLUMNS,
                COL_LAKE_MERRITT_TYPE+ " = ?",
                new String[]{"Korean"},
                null,
                null,
                null,
                null);
        return cursor;

    }

    public Cursor testingGetSportsList(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME,
                LAKE_MERRITT_COLUMNS,
                COL_LAKE_MERRITT_TYPE+ " = ?",
                new String[]{"Sports"},
                null,
                null,
                null,
                null);
        return cursor;

    }

    public Cursor getParksList(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME,
                LAKE_MERRITT_COLUMNS,
                COL_LAKE_MERRITT_TYPE+ " = ?",
                new String[]{"Parks"},
                null,
                null,
                null,
                null);
        return cursor;

    }

    // this is for testing filter only... use if work, delete if not
    public Cursor testingSortByPriceRange(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME,
                LAKE_MERRITT_COLUMNS,
                COL_LAKE_MERRITT_PRICE+ " LIKE '%$'",
                null,  //new String[]{"% $ %"},
                null,
                null,
                COL_LAKE_MERRITT_PRICE + " ASC",
                null);
        return cursor;

    }

    /**
     *
     * can use same logic to create filter!
     */
    public Cursor getActivitiesList(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME,
                LAKE_MERRITT_COLUMNS,
                COL_CATEGORY_LIST+ " = ?",
                new String[]{"Activities"},
                null,
                null,
                null,
                null);
        return cursor;

    }

    public Cursor getItem(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME, // a. table
                LAKE_MERRITT_COLUMNS, // b. column names
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }

}
