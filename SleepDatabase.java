package com.example.sibideiveegan.sleepanalyser1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sibideiveegan on 7/4/16.
 */

/**
 * Created by sibideiveegan on 22/3/16.
 */
public class SleepDatabase extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "SleepAnalyser";
    public static String TABLE_NAME = "SleepTimeRecorder";
    public static String COL = "id";                //0
    public static String COL_01 = "UserId";         //1
    public static String COL_1 = "Date";            //2
    public static String COL_2 = "SleepTimeHour";   //3
    public static String COL_21 = "SleepTimeMin";   //4
    public static String COL_22 = "SleepTimeSec";   //5

    public static String TABLE_NAME1 = "WakeUpTimeRecorder";
    public static String COL_3 = "id1";             //0
    public static String COL_31 = "UserId";         //1
    public static String COL_4 = "Date";            //2
    public static String COL_5 = "WakeUpTimeHour";  //3
    public static String COL_51 = "WakeUpTimeMin";  //4
    public static String COL_52 = "WakeUpTimeSec";  //5
    /*public static String TABLE_NAME2 = "Summary";
    public static String COL_6 = "id2";
    public static String COL_7 = "Date";
    public static String COL_7 = "AvgSleepTime";
    public static String COL_8 = "AvgWakeTime";
    public static String COL_9 = "AvgSleepDuration";*/

    SQLiteDatabase db;

    public SleepDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_NAME + "(id integer primary key autoincrement,UserId text , Date text,SleepTimeHour text," +
                "SleepTimeMin text ,SleepTimeSec text )");
        db.execSQL("create table if not exists " + TABLE_NAME1 + "(id1 integer primary key autoincrement,UserId text , Date text,WakeUpTimeHour text," +
                "WakeUpTimeMin text ,WakeUpTimeSec text )");
        //db.execSQL("create table if not exists " + TABLE_NAME2 + "(id2 integer primary key autoincrement," +
         //       "AvgSleepTime text,AvgWakeTime text,AvgSleepDuration)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertdata(String Table, String Date,String UserId, String TimeHour,String TimeMin,String TimeSec) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        long res = -1;
        if (Table.equals("SleepTimeRecorder") ) {
            cv.put(COL_01,UserId);
            cv.put(COL_1, Date);
            cv.put(COL_2, TimeHour);
            cv.put(COL_21,TimeMin);
            cv.put(COL_22,TimeSec);
            res = db.insert(TABLE_NAME, null, cv);
            Log.d("Inserted","data");
        } else if (Table.equals("WakeUpTimeRecorder")) {
            cv.put(COL_31,UserId);
            cv.put(COL_4, Date);
            cv.put(COL_5, TimeHour);
            cv.put(COL_51,TimeMin);
            cv.put(COL_52,TimeSec);
            res = db.insert(TABLE_NAME1, null, cv);
        }
        if (res == -1)
            return false;
        else
            return true;
    }

    public Cursor getall(String Table) {
        db = this.getWritableDatabase();
        Cursor r=null;
        if (Table.equals("SleepTimeRecorder")) {
         r = db.rawQuery("select * from " + TABLE_NAME, null);
        } else if (Table.equals("WakeUpTimeRecorder")) {
           r = db.rawQuery("select * from " + TABLE_NAME1, null);
        }
        db.rawQuery("commit", null);
        return r;
    }
    public Cursor getavg() {
        //Cursor r = db.rawQuery("select * from " + TABLE_NAME + " join " + TABLE_NAME1, null);
        //SQLiteDatabase _DB = fDatabaseHelper.getReadableDatabase();
       // private final String MY_QUERY = "SELECT * FROM table_a a INNER JOIN table_b b ON a.id=b.other_id WHERE b.property_id=?";
        //char[] propertyId;
       // Cursor r = db.rawQuery(MY_QUERY, new String[]{String.valueOf(propertyId)});
        String MY_QUERY = "SELECT * FROM TABLE_NAME JOIN TABLE_NAME1 ON TABLE_NAME.id=TABLE_NAME1.id1 WHERE TABLE_NAME.UserId =TABLE_NAME1.UserId";
        Cursor r = db.rawQuery(MY_QUERY, null);
        //Toast.makeText(getApplicationContext,"Getting Avg",Toast.LENGTH_LONG).show();
        Log.d("insde avg", "working");
        return  r;
    }
}
