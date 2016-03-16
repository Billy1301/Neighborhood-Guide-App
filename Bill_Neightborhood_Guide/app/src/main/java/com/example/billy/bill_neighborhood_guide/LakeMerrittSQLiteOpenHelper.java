package com.example.billy.bill_neighborhood_guide;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by drewmahrt on 12/29/15.
 */
public class LakeMerrittSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = LakeMerrittSQLiteOpenHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 9;
    public static final String DATABASE_NAME = "LAKE_MERRITT_DB";
    public static final String LAKE_MERRITT_LIST_TABLE_NAME = "LAKE_MERRITT_LIST";
    public static final String LAKE_MERRITT_PLACE_NAME = "PLACE NAME";
    public static final String LAKE_MERRITT_LOCATION = "LOCATION";
    public static final String LAKE_MERRITT_PHONE = "PHONE NUMBER";
    public static final String LAKE_MERRITT_RATINGS = "RATINGS";
    public static final String LAKE_MERRITT_PRICE = "PRICE";
    public static final String LAKE_MERRITT_TYPE = "TYPE";


    public static final String COL_ID = "_id";
    public static final String COL_CATEGORY_LIST = "CATEGORY LIST";

    public static final String[] LAKE_MERRITT_COLUMNS = {COL_ID,COL_CATEGORY_LIST, LAKE_MERRITT_PLACE_NAME, LAKE_MERRITT_LOCATION,
                                    LAKE_MERRITT_PHONE, LAKE_MERRITT_RATINGS, LAKE_MERRITT_PRICE, LAKE_MERRITT_TYPE};

    private static final String CREATE_LAKE_MERRITT_LIST_TABLE =
            "CREATE TABLE " + LAKE_MERRITT_LIST_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_CATEGORY_LIST + " TEXT, " + LAKE_MERRITT_PLACE_NAME + " TEXT, "
                    + LAKE_MERRITT_LOCATION + " TEXT, " + LAKE_MERRITT_PHONE + " TEXT, " + LAKE_MERRITT_RATINGS
                    + " TEXT, " + LAKE_MERRITT_PRICE + " TEXT, " + LAKE_MERRITT_TYPE + " TEXT )";


    private static LakeMerrittSQLiteOpenHelper instance;

    public static LakeMerrittSQLiteOpenHelper getInstance(Context context){
        if(instance == null){
            instance = new LakeMerrittSQLiteOpenHelper(context);
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
        db.execSQL("DROP TABLE IF EXISTS " + LAKE_MERRITT_LIST_TABLE_NAME);
        this.onCreate(db);
    }

    public Cursor getIconList(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(LAKE_MERRITT_LIST_TABLE_NAME, // a. table
                LAKE_MERRITT_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }
}
