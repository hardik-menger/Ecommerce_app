package com.englishcenter.shoppingcart.presenters;

import android.content.Context;

import com.englishcenter.shoppingcart.Shopping;
import com.englishcenter.shoppingcart.database.CartController;
import com.englishcenter.shoppingcart.models.cart.Cart;
import com.englishcenter.shoppingcart.views.ShoppingCartInteraction;

import javax.inject.Inject;

/**
 * Created by Hardik Menger
 */
public class CartPresenter {

    private Context mContext;
    private ShoppingCartInteraction.AddProduct mView;

    @Inject
    CartController cartController;

    public CartPresenter(Context context, ShoppingCartInteraction.AddProduct view) {
        this.mContext = context;
        this.mView = view;

        ((Shopping) context.getApplicationContext()).getApplicationComponent().inject(this);
    }

    /**
     * Add item to cart table
     * @param item
     */
    public void addItemToCart(Cart item) {
        cartController.saveToCart(item);
        mView.showAddCartSucess();
    }

}
