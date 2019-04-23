package com.englishcenter.shoppingcart;

import com.englishcenter.shoppingcart.activities.OrderSuccessActivity;
import com.englishcenter.shoppingcart.activities.ShoppingCartActivity;
import com.englishcenter.shoppingcart.activities.SplashActivity;
import com.englishcenter.shoppingcart.fragments.ProductListFragment;
import com.englishcenter.shoppingcart.presenters.CartPresenter;
import com.englishcenter.shoppingcart.presenters.MainActivityProductPresenter;
import com.englishcenter.shoppingcart.presenters.ShoppingCartPresenter;

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
