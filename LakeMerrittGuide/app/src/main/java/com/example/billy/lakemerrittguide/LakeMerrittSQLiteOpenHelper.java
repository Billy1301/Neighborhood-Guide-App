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
    public static final String COL_LAKE_MERRITT_DESCRIPTION = "DESCRIPTION";
    public static final String COL_LAKE_MERRITT_MAININFO_LOGOIMAGE = "INFOLOGOIMAGE";
    public static final String COL_LAKE_MERRITT_INFOIMAGEONE = "IMAGEONE";
    public static final String COL_LAKE_MERRITT_INFOIMAGETWO = "IMAGETWO";


    public static final String COL_ID = "_id";
    public static final String COL_CATEGORY_LIST = "CATEGORY_LIST";

    public static final String[] COL_LAKE_MERRITT_COLUMNS = {COL_ID,COL_CATEGORY_LIST, COL_LAKE_MERRITT_PLACE_NAME, COL_LAKE_MERRITT_ADDRESS,
            COL_LAKE_MERRITT_PHONE, COL_LAKE_MERRITT_RATINGS, COL_LAKE_MERRITT_PRICE, COL_LAKE_MERRITT_TYPE, COL_LAKE_MERRITT_DESCRIPTION, COL_LAKE_MERRITT_MAININFO_LOGOIMAGE, COL_LAKE_MERRITT_INFOIMAGEONE, COL_LAKE_MERRITT_INFOIMAGETWO};

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
                    COL_LAKE_MERRITT_DESCRIPTION + " TEXT, " +
                    COL_LAKE_MERRITT_MAININFO_LOGOIMAGE + " INTEGER, " +
                    COL_LAKE_MERRITT_INFOIMAGEONE + " INTEGER, " +
                    COL_LAKE_MERRITT_INFOIMAGETWO + " INTEGER)";


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


    public void listInsert(String categoryList, String placeName, String address, String phoneNumber, String ratings, String price, String type, String description, int infoMainLogo, int infoImageOne, int infoImageTwo){
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
        values.put(COL_LAKE_MERRITT_MAININFO_LOGOIMAGE, infoMainLogo);
        values.put(COL_LAKE_MERRITT_INFOIMAGEONE, infoImageOne);
        values.put(COL_LAKE_MERRITT_INFOIMAGETWO, infoImageTwo);


        db.insert(COL_LAKE_MERRITT_TABLE_NAME, null, values);
        db.close();
    }


    /**
     * need to fix search
     *
     */
    public Cursor searchLakeMerrittList(String query){
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME,
                COL_LAKE_MERRITT_COLUMNS,
                COL_LAKE_MERRITT_PLACE_NAME + " LIKE ? OR " + COL_LAKE_MERRITT_TYPE + " LIKE ?",
                new String[]{"%" + query + "%", "%" + query + "%"},
                null,
                null,
                null,
                null);
        return cursor;
    }


    public Cursor getLakeMerrittLists(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME, // a. table
                COL_LAKE_MERRITT_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, //COL_LAKE_MERRITT_TYPE, // e. group by
                null, // f. having
                null, //COL_LAKE_MERRITT_PLACE_NAME, // g. order by
                null); // h. limit
        return cursor;
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

    public ThingsToDo createObjects(int _id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME,
                COL_LAKE_MERRITT_COLUMNS,
                COL_ID + " = ?",
                new String[]{String.valueOf(_id)},
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        String name = cursor.getString(cursor.getColumnIndex(COL_LAKE_MERRITT_PLACE_NAME));
        String type = cursor.getString(cursor.getColumnIndex(COL_LAKE_MERRITT_TYPE));
        String location = cursor.getString(cursor.getColumnIndex(COL_LAKE_MERRITT_ADDRESS));
        String description = cursor.getString(cursor.getColumnIndex(COL_LAKE_MERRITT_DESCRIPTION));
        String price = cursor.getString(cursor.getColumnIndex(COL_LAKE_MERRITT_PRICE));
        String phoneNumber = cursor.getString(cursor.getColumnIndex(COL_LAKE_MERRITT_PHONE));
        String rating = cursor.getString(cursor.getColumnIndex(COL_LAKE_MERRITT_RATINGS));
        int mainLogo = cursor.getInt(cursor.getColumnIndex(COL_LAKE_MERRITT_MAININFO_LOGOIMAGE));
        int imageOne = cursor.getInt(cursor.getColumnIndex(COL_LAKE_MERRITT_INFOIMAGEONE));
        int imageTwo = cursor.getInt(cursor.getColumnIndex(COL_LAKE_MERRITT_INFOIMAGETWO));

        return new ThingsToDo(name, location, rating, phoneNumber,type, price, description, mainLogo, imageOne, imageTwo);

    }

    public Cursor getRestaurantList(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(COL_LAKE_MERRITT_TABLE_NAME,
                COL_LAKE_MERRITT_COLUMNS,
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
                COL_LAKE_MERRITT_COLUMNS,
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
                COL_LAKE_MERRITT_COLUMNS,
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
                COL_LAKE_MERRITT_COLUMNS,
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
                COL_LAKE_MERRITT_COLUMNS,
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
                COL_LAKE_MERRITT_COLUMNS,
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
                COL_LAKE_MERRITT_COLUMNS, // b. column names
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }

}
