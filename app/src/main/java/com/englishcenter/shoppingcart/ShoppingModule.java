package com.englishcenter.shoppingcart;

import android.app.Application;

import com.englishcenter.shoppingcart.database.CartController;
import com.englishcenter.shoppingcart.database.ProductsController;
import com.englishcenter.shoppingcart.dialogs.CustomDialog;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hardik Menger
 */
@Module
public class ShoppingModule {

    private final Application application;

    public ShoppingModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    public ProductsController providesProductsController() {
        return new ProductsController(application.getApplicationContext(), new Gson());
    }

    @Provides
    @Singleton
    public CartController providesCartController() {
        return new CartController(application.getApplicationContext(), new Gson());
    }

    @Provides
    @Singleton
    public CustomDialog providesCustomDialog() {
        return new CustomDialog();
    }
}
