package com.englishcenter.shoppingcart.models.products;

import com.englishcenter.shoppingcart.models.products.Products;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Hardik Menger
 */
public class ProductsRaw {

    private static final String KEY_RESPONSE_STATUS = "status";
    @SerializedName(KEY_RESPONSE_STATUS)
    int responseStatus;

    private static final String KEY_RESPONSE_MESSAGE = "message";
    @SerializedName(KEY_RESPONSE_MESSAGE)
    String responseMessage;

    private static final String KEY_RESPONSE_PRODUCTS = "products";
    @SerializedName(KEY_RESPONSE_PRODUCTS)
    List<Products> responseProducts;

    //region Setters
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setResponseProducts(List<Products> responseProducts) {
        this.responseProducts = responseProducts;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }
    //endregion

    //region Getters
    public String getResponseMessage() {
        return responseMessage;
    }

    public List<Products> getResponseProducts() {
        return responseProducts;
    }

    public int getResponseStatus() {
        return responseStatus;
    }
    //endregion
}
