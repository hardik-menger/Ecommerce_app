package com.englishcenter.shoppingcart.presenters;

import android.content.Context;

import com.englishcenter.shoppingcart.Shopping;
import com.englishcenter.shoppingcart.database.CartController;
import com.englishcenter.shoppingcart.models.cart.Cart;
import com.englishcenter.shoppingcart.views.ShoppingCartInteraction;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Hardik Menger
 */
public class ShoppingCartPresenter {

    private Context mContext;
    private ShoppingCartInteraction.ShoppingCart mView;

    @Inject
    CartController cartController;

    public ShoppingCartPresenter(Context context, ShoppingCartInteraction.ShoppingCart view) {
        this.mContext = context;
        this.mView = view;

        ((Shopping) context.getApplicationContext()).getApplicationComponent().inject(this);
    }

    /**
     * Get all cart items from database
     */
    public void getCartItemList() {
        List<Cart> cartList = cartController.getCartItems();

        if(cartList != null) {
            mView.showCartList(cartList);
            mView.showCartSummary(getTotalAmount(cartList), cartList.size());
        }
        else {
            mView.showNoCartItem();
            mView.showCartSummary(0, 0);
        }
    }

    /**
     * Removing selected item from the cart
     * @param cartId
     */
    public void removeCartItem(int cartId) {
        cartController.removeItemFromCart(cartId);
        getCartItemList();
    }

    /**
     * Calculate total amount of items in cart
     * @param cartList
     * @return
     */
    private float getTotalAmount(List<Cart> cartList) {
        float totalAmount = 0;

        for(Cart item : cartList) {
            totalAmount += item.getProductPrice();
        }

        return totalAmount;
    }
}
