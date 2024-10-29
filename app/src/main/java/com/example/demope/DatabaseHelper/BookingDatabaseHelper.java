package com.example.demope.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.demope.Model.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "booking.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BOOKING = "Booking";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CUSTOMER_NAME = "customerName";
    private static final String COLUMN_SERVICE_NAME = "serviceName";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_TOTAL_VALUE = "totalValue";

    public BookingDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_BOOKING + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CUSTOMER_NAME + " TEXT, "
                + COLUMN_SERVICE_NAME + " TEXT, "
                + COLUMN_PRICE + " REAL, "
                + COLUMN_QUANTITY + " INTEGER, "
                + COLUMN_TOTAL_VALUE + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKING);
        onCreate(db);
    }

    public void insertBooking(Booking booking) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Use transactions for better performance and atomicity
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_CUSTOMER_NAME, booking.getCustomerName());
            values.put(COLUMN_SERVICE_NAME, booking.getServiceName());
            values.put(COLUMN_PRICE, booking.getPrice());
            values.put(COLUMN_QUANTITY, booking.getQuantity());
            values.put(COLUMN_TOTAL_VALUE, booking.getTotalValue());

            db.insertOrThrow(TABLE_BOOKING, null, values);
            db.setTransactionSuccessful();  // Commit the transaction
        } catch (Exception e) {
            e.printStackTrace();  // Log the error in case of failure
        } finally {
            db.endTransaction();
        }

        db.close();
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookingList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOKING, null);

            if (cursor.moveToFirst()) {
                do {
                    Booking booking = new Booking();
                    booking.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                    booking.setCustomerName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_NAME)));
                    booking.setServiceName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SERVICE_NAME)));
                    booking.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));
                    booking.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)));
                    booking.setTotalValue(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_VALUE)));
                    bookingList.add(booking);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();  // Always close cursor
            }
            db.close();  // Always close the database
        }

        return bookingList;
    }

    public Booking getBookingById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Booking booking = null;
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_BOOKING, null, COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                booking = new Booking();
                booking.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                booking.setCustomerName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_NAME)));
                booking.setServiceName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SERVICE_NAME)));
                booking.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));
                booking.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)));
                booking.setTotalValue(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_VALUE)));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return booking;
    }
}
