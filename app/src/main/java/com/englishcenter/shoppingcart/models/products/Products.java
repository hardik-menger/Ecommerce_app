package com.englishcenter.shoppingcart.models.products;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jb on 02/09/2016.
 */
public class Products implements Parcelable {

    private static final String EXTRA_PRODUCT_ID = "product_id";
    private static final String EXTRA_PRODUCT_NAME = "product_name";
    private static final String EXTRA_PRODUCT_BRAND = "product_brand";
    private static final String EXTRA_PRODUCT_PRICE = "product_price";

    private static final String PRODUCT_ID = "id";
    @SerializedName(PRODUCT_ID)
    int productId;

    private static final String PRODUCT_NAME = "name";
    @SerializedName(PRODUCT_NAME)
    String productName;

    private static final String PRODUCT_BRAND = "brand";
    @SerializedName(PRODUCT_BRAND)
    String productBrand;

    private static final String PRODUCT_PRICE = "price";
    @SerializedName(PRODUCT_PRICE)
    float productPrice;

    public Products() {}

    protected Products(Parcel in) {
        Bundle bundle = in.readBundle(getClass().getClassLoader());
        productId = bundle.getInt(EXTRA_PRODUCT_ID, 0);
        productName = bundle.getString(EXTRA_PRODUCT_NAME, "");
        productBrand = bundle.getString(EXTRA_PRODUCT_BRAND, "");
        productPrice = bundle.getFloat(EXTRA_PRODUCT_PRICE, 0);
    }

    public static final Creator<Products> CREATOR = new Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_PRODUCT_ID, productId);
        bundle.putString(EXTRA_PRODUCT_NAME, productName);
        bundle.putString(EXTRA_PRODUCT_BRAND, productName);
        bundle.putFloat(EXTRA_PRODUCT_PRICE, productPrice);
        parcel.writeBundle(bundle);
    }

    //region Setters
    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }
    //endregion

    //region Getters
    public String getProductBrand() {
        return productBrand;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public float getProductPrice() {
        return productPrice;
    }
    //endregion
}
