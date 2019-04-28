package com.hardik.shoppingcart.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbaseSettings extends SQLiteOpenHelper {

    public static final String DB_NAME = "shopping.db";
    private static final int DB_VERSION = 1;

    /* Product's Table */
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_PRODUCT_RAW_ID = "product_raw_id";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_PRODUCT_PRICE = "product_price";
    public static final String COLUMN_PRODUCT_BRAND = "product_brand";

    /* Cart's Table */
    public static final String TABLE_CART = "cart";
    public static final String COLUMN_CART_ID = "cart_id";
    public static final String COLUMN_CART_PRODUCT = "cart_product";
    public static final String COLUMN_CART_PRODUCT_PRICE = "cart_product_price";

    private SQLiteDatabase db;

    public DbaseSettings(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCTS + " ("
                        + COLUMN_PRODUCT_ID + " integer primary key autoincrement, "
                        + COLUMN_PRODUCT_RAW_ID + " integer, "
                        + COLUMN_PRODUCT_NAME + " text, "
                        + COLUMN_PRODUCT_BRAND + " text, "
                        + COLUMN_PRODUCT_PRICE + " real)"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_CART + " ("
                        + COLUMN_CART_ID + " integer primary key autoincrement, "
                        + COLUMN_CART_PRODUCT + " text, "
                        + COLUMN_CART_PRODUCT_PRICE + " real)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }

    public void open() {
        db = this.getWritableDatabase();
    }

    public void close() {
        db.close();
    }
}
