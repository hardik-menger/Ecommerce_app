package com.englishcenter.shoppingcart.models.cart;

/**
 * Created by Hardik Menger
 */
public class Cart {

    int cartId;
    String productName;
    float productPrice;

    public Cart() {}

    //region Setters
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
    //endregion

    //region Getters
    public String getProductName() {
        return productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public int getCartId() {
        return cartId;
    }
    //endregion
}
