package com.hardik.shoppingcart.views;

import com.hardik.shoppingcart.models.cart.Cart;

import java.util.List;

/**
 * Created by Hardik Menger
 */
public interface ShoppingCartInteraction {

    interface AddProduct {

        void showOptionDialog(Cart item);

        void showAddCartSucess();
    }

    interface ShoppingCart {

        void showCartList(List<Cart> cartList);

        void showNoCartItem();

        void showCartSummary(float totalAmount, int totalItems);
    }

    interface RemoveItemCart {

        void removeItem(int cartId);

    }
}
