package com.hardik.shoppingcart;

import com.hardik.shoppingcart.activities.OrderSuccessActivity;
import com.hardik.shoppingcart.activities.ShoppingCartActivity;
import com.hardik.shoppingcart.activities.SplashActivity;
import com.hardik.shoppingcart.fragments.ProductListFragment;
import com.hardik.shoppingcart.presenters.CartPresenter;
import com.hardik.shoppingcart.presenters.MainActivityProductPresenter;
import com.hardik.shoppingcart.presenters.ShoppingCartPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Hardik Menger
 */
@Singleton
@Component(modules = {ShoppingModule.class})
public interface ShoppingComponent {

    // Activities
    void inject(SplashActivity activity);
    void inject(ShoppingCartActivity activity);
    void inject(OrderSuccessActivity activity);

    // Fragments
    void inject(ProductListFragment fragment);

    // Presenter
    void inject(MainActivityProductPresenter presenter);
    void inject(CartPresenter presenter);
    void inject(ShoppingCartPresenter presenter);
}
