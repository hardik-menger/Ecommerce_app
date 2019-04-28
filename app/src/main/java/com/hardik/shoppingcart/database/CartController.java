package com.hardik.shoppingcart.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hardik.shoppingcart.models.cart.Cart;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Hardik Menger
 */
public class CartController {

    private static final String TAG = CartController.class.getSimpleName();

    private DbaseSettings dbaseSettings;
    private Context mContext;
    private SQLiteDatabase db;
    private Gson gson;

    @Inject
    public CartController(Context context, Gson gson) {
        this.mContext = context;
        this.gson = gson;

        this.dbaseSettings = new DbaseSettings(context);
    }

    /**
     * Save cart item to database
     * @param item
     */
    public void saveToCart(Cart item) {
        db = dbaseSettings.getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbaseSettings.COLUMN_CART_PRODUCT, item.getProductName());
            contentValues.put(dbaseSettings.COLUMN_CART_PRODUCT_PRICE, item.getProductPrice());

            db.insert(dbaseSettings.TABLE_CART, null, contentValues);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    /**
     * Get all cart items from database
     * @return
     */
    public List<Cart> getCartItems() {
        db = dbaseSettings.getReadableDatabase();

        List<Cart> cartIems = new ArrayList<>();

        String[] columns = new String[] {
                dbaseSettings.COLUMN_CART_ID,
                dbaseSettings.COLUMN_CART_PRODUCT,
                dbaseSettings.COLUMN_CART_PRODUCT_PRICE
        };

        Cursor cursor = db.query(dbaseSettings.TABLE_CART, columns, null, null, null, null, dbaseSettings.COLUMN_CART_ID + " DESC");

        if(cursor != null) {
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    Cart item = new Cart();
                    item.setCartId(cursor.getInt(cursor.getColumnIndex(dbaseSettings.COLUMN_CART_ID)));
                    item.setProductName(cursor.getString(cursor.getColumnIndex(dbaseSettings.COLUMN_CART_PRODUCT)));
                    item.setProductPrice(cursor.getFloat(cursor.getColumnIndex(dbaseSettings.COLUMN_CART_PRODUCT_PRICE)));

                    cartIems.add(item);
                } while(cursor.moveToNext());

                return cartIems;
            }
        }

        return null;

    }

    /**
     * Remove item from cart
     * @param cartId
     */
    public void removeItemFromCart(int cartId) {
        db = dbaseSettings.getWritableDatabase();
        db.delete(dbaseSettings.TABLE_CART,
                dbaseSettings.COLUMN_CART_ID + "=?",
                new String[]{Integer.toString(cartId)});
        db.close();
    }

    /**
     * Remove all items from the cart
     */
    public void removeAllItemFromCart() {
        db = dbaseSettings.getWritableDatabase();
        db.delete(dbaseSettings.TABLE_CART, null, null);
        db.close();
    }
}
