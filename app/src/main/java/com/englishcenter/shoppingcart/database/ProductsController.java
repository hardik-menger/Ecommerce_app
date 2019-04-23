package com.englishcenter.shoppingcart.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.englishcenter.shoppingcart.models.products.Products;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Hardik Menger
 */
public class ProductsController {

    private static final String TAG = ProductsController.class.getSimpleName();

    private DbaseSettings dbaseSettings;
    private Context mContext;
    private SQLiteDatabase db;
    private Gson gson;

    @Inject
    public ProductsController(Context context, Gson gson) {
        this.mContext = context;
        this.gson = gson;

        this.dbaseSettings = new DbaseSettings(context);
    }

    /**
     * Save all products
     * @param products
     */
    public void saveProductsList(List<Products> products) {
        db = dbaseSettings.getWritableDatabase();

        if(!products.isEmpty()) {
            clearTable(dbaseSettings.TABLE_PRODUCTS);
        }

        db.beginTransaction();
        try {
            for(Products product : products) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(dbaseSettings.COLUMN_PRODUCT_RAW_ID, product.getProductId());
                contentValues.put(dbaseSettings.COLUMN_PRODUCT_NAME, product.getProductName());
                contentValues.put(dbaseSettings.COLUMN_PRODUCT_BRAND, product.getProductBrand());
                contentValues.put(dbaseSettings.COLUMN_PRODUCT_PRICE, product.getProductPrice());

                db.insert(dbaseSettings.TABLE_PRODUCTS, null, contentValues);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    /**
     * Get all products from database
     * @return
     */
    public List<Products> getAllProducts() {
        db = dbaseSettings.getReadableDatabase();

        List<Products> productList = new ArrayList<>();

        String[] columns = new String[] {
                dbaseSettings.COLUMN_PRODUCT_RAW_ID,
                dbaseSettings.COLUMN_PRODUCT_NAME,
                dbaseSettings.COLUMN_PRODUCT_BRAND,
                dbaseSettings.COLUMN_PRODUCT_PRICE
        };
        Cursor cursor = db.query(
                dbaseSettings.TABLE_PRODUCTS, columns, null, null, null, null, null);

        if(cursor != null) {
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    Products product = new Products();
                    product.setProductId(cursor.getInt(cursor.getColumnIndex(dbaseSettings.COLUMN_PRODUCT_RAW_ID)));
                    product.setProductName(cursor.getString(cursor.getColumnIndex(dbaseSettings.COLUMN_PRODUCT_NAME)));
                    product.setProductBrand(cursor.getString(cursor.getColumnIndex(dbaseSettings.COLUMN_PRODUCT_BRAND)));
                    product.setProductPrice(cursor.getFloat(cursor.getColumnIndex(dbaseSettings.COLUMN_PRODUCT_PRICE)));

                    productList.add(product);
                } while(cursor.moveToNext());

                return productList;
            }
        }

        return null;
    }

    /**
     * Get product by category from database
     * @param brand
     * @return
     */
    public ArrayList<Products> getProductByBrand(String brand) {
        db = dbaseSettings.getReadableDatabase();

        ArrayList<Products> productList = new ArrayList<>();

        String[] columns = new String[] {
                dbaseSettings.COLUMN_PRODUCT_RAW_ID,
                dbaseSettings.COLUMN_PRODUCT_NAME,
                dbaseSettings.COLUMN_PRODUCT_BRAND,
                dbaseSettings.COLUMN_PRODUCT_PRICE
        };
        String selection = dbaseSettings.COLUMN_PRODUCT_BRAND + " = '" + brand + "'";
        Cursor cursor = db.query(dbaseSettings.TABLE_PRODUCTS, columns, selection, null, null, null, null);

        if(cursor != null) {
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    Products product = new Products();
                    product.setProductId(cursor.getInt(cursor.getColumnIndex(dbaseSettings.COLUMN_PRODUCT_RAW_ID)));
                    product.setProductName(cursor.getString(cursor.getColumnIndex(dbaseSettings.COLUMN_PRODUCT_NAME)));
                    product.setProductBrand(cursor.getString(cursor.getColumnIndex(dbaseSettings.COLUMN_PRODUCT_BRAND)));
                    product.setProductPrice(cursor.getFloat(cursor.getColumnIndex(dbaseSettings.COLUMN_PRODUCT_PRICE)));

                    productList.add(product);
                } while(cursor.moveToNext());

                return productList;
            }
        }

        return null;
    }

    /**
     * Delete all data in a table
     * @param tableName
     */
    private void clearTable(String tableName) {
        db = dbaseSettings.getWritableDatabase();
        db.delete(tableName, null, null);
    }
}
