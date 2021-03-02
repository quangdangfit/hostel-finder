package com.example.quangdang.hostelfinder.Class;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "hostelManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_HOSTEL = "Hostel";

    private static final String KEY_HOSTEL_ID = "hostelID";
    private static final String KEY_HOSTEL_ADD = "hostelAddress";
    private static final String KEY_HOSTEL_AREA = "hostelArea";
    private static final String KEY_HOSTEL_PRICE = "hostelPrice";
    private static final String KEY_HOSTEL_INTRO = "hostelIntro";
    private static final String KEY_HOSTEL_STAR = "hostelStarNum";
    private static final String KEY_HOSTEL_PHONE = "hostelPhoneNum";
    private static final String KEY_HOSTEL_LAT = "hostelLatitude";
    private static final String KEY_HOSTEL_LNG = "hostelLongitude";
    private static final String KEY_HOSTEL_IMG = "hostelImgList";
    private static final String KEY_HOSTEL_CITY = "hostelCity";
    private static final String KEY_HOSTEL_DIST = "hostelDIST";


        public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME + ".sqlite", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Hostel
        db.execSQL("create table if not exists " + TABLE_HOSTEL + " ( "
                + KEY_HOSTEL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + KEY_HOSTEL_ADD + " VARCHAR, " + KEY_HOSTEL_AREA + " FLOAT, "
                + KEY_HOSTEL_PRICE + " INTEGER, " + KEY_HOSTEL_INTRO + " TEXT, "
                + KEY_HOSTEL_STAR + " INTEGER, " + KEY_HOSTEL_PHONE + " VARCHAR, "
                + KEY_HOSTEL_LAT + " FLOAT, " + KEY_HOSTEL_LNG + " FLOAT, "
                + KEY_HOSTEL_IMG + " VARCHAR, " + KEY_HOSTEL_CITY + " VARCHAR, "
                + KEY_HOSTEL_DIST + " VARCHAR);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_hostel_table = String.format("DROP TABLE IF EXISTS %s", TABLE_HOSTEL);
        db.execSQL(drop_hostel_table);
        onCreate(db);
    }

    public void addHostel(Hostel hostel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HOSTEL_ADD, hostel.getAddress());
        values.put(KEY_HOSTEL_AREA, hostel.getArea());
        values.put(KEY_HOSTEL_PRICE, hostel.getPrice());
        values.put(KEY_HOSTEL_INTRO, hostel.getIntro());
        values.put(KEY_HOSTEL_STAR, hostel.getStarNum());
        values.put(KEY_HOSTEL_PHONE, hostel.getPhoneNum());
        values.put(KEY_HOSTEL_LAT, hostel.getLatitude());
        values.put(KEY_HOSTEL_LNG, hostel.getLongitude());
        values.put(KEY_HOSTEL_IMG, hostel.getImage());
        values.put(KEY_HOSTEL_CITY, hostel.getCityName());
        values.put(KEY_HOSTEL_DIST, hostel.getDistName());

        db.insert(TABLE_HOSTEL, null, values);
        db.close();
    }

    public ArrayList<Hostel> getAllHostel() {
        ArrayList<Hostel> hostelArrayList = new ArrayList<>();
        String selectQuery = "SELECT " + "*" + " FROM " + TABLE_HOSTEL;


        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Hostel hostel = new Hostel(cursor.getInt(0), cursor.getString(1),
                    cursor.getFloat(2), cursor.getInt(3), cursor.getString(4),
                    cursor.getInt(5), cursor.getString(6), cursor.getFloat(7),
                    cursor.getFloat(8), cursor.getInt(9), cursor.getString(10),
                    cursor.getString(11)
            );
            hostelArrayList.add(hostel);
            cursor.moveToNext();
        }
        return hostelArrayList;
    }

    public ArrayList<Hostel> getAllHostelInCity(String cityName) {
        ArrayList<Hostel> hostelArrayList = new ArrayList<>();
        String selectQuery = "SELECT " + "*" + " FROM " + TABLE_HOSTEL + " WHERE " + KEY_HOSTEL_CITY
                + " LIKE " + cityName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Hostel hostel = new Hostel(cursor.getInt(0), cursor.getString(1),
                    cursor.getFloat(2), cursor.getInt(3), cursor.getString(4),
                    cursor.getInt(5), cursor.getString(6), cursor.getFloat(7),
                    cursor.getFloat(8), cursor.getInt(9), cursor.getString(10),
                    cursor.getString(11)
            );
            hostelArrayList.add(hostel);
            cursor.moveToNext();
        }
        return hostelArrayList;
    }

    public ArrayList<Hostel> getAllHostelInDist(String cityName, String distName) {
        ArrayList<Hostel> hostelArrayList = new ArrayList<>();
        if (distName.equals("Tất cả")){
            return getAllHostelInCity(cityName);
        }else {
            String selectQuery = "SELECT " + "*" + " FROM " + TABLE_HOSTEL + " WHERE " + KEY_HOSTEL_CITY
                    + " LIKE " + cityName + " AND " + KEY_HOSTEL_DIST + " LIKE " + distName;

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Hostel hostel = new Hostel(cursor.getInt(0), cursor.getString(1),
                        cursor.getFloat(2), cursor.getInt(3), cursor.getString(4),
                        cursor.getInt(5), cursor.getString(6), cursor.getFloat(7),
                        cursor.getFloat(8), cursor.getInt(9), cursor.getString(10),
                        cursor.getString(11)
                );
                hostelArrayList.add(hostel);
                cursor.moveToNext();
            }
            return hostelArrayList;
        }
    }

    public void deleteHostel(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HOSTEL, KEY_HOSTEL_ID + " = ?", new String[] {Integer.toString(id)});
        db.close();
    }
}