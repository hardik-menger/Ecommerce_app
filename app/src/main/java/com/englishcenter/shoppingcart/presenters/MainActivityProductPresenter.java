package com.englishcenter.shoppingcart.presenters;

import android.content.Context;

import com.englishcenter.shoppingcart.Shopping;
import com.englishcenter.shoppingcart.database.ProductsController;
import com.englishcenter.shoppingcart.models.products.Products;
import com.englishcenter.shoppingcart.views.MainActivityInteraction;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

/**
 * Created by jb on 02/09/2016.
 */
public class MainActivityProductPresenter {

    private static final String TAG = MainActivityProductPresenter.class.getSimpleName();

    private static final String KEY_SAMSUNG = "samsung";
    private static final String KEY_SONY = "sony";
    private static final String KEY_APPLE = "apple";
    private static final String KEY_CHERRY = "cherry";
    private static final String KEY_MYPHONE = "myphone";


    private Context mContext;
    private MainActivityInteraction mView;
    private Gson gson;

    @Inject
    ProductsController productsController;

    public MainActivityProductPresenter(Context context, MainActivityInteraction view) {
        this.mContext = context;
        this.mView = view;

        this.gson = new Gson();

        ((Shopping) context.getApplicationContext()).getApplicationComponent().inject(this);
    }


    public void getProducts() {
        List<ArrayList<Products>> fileteredProduct = new ArrayList<>();
        List<String> titles = new ArrayList<>();

        // Get All products
        List<Products> allProducts = productsController.getAllProducts();

        // Get all brands
        for(Products product : allProducts) {
            titles.add(product.getProductBrand());
        }

        // Filter brands so that no duplicate will occur
        Set<String> uniqueTitles = StreamSupport.stream(titles)
                .sorted((brandA, brandB) -> brandB.compareToIgnoreCase(brandA))
                .collect(Collectors.toSet());

        titles = new ArrayList<>(uniqueTitles);

        fileteredProduct = getFilteredProduct(titles);

        mView.showProducts(fileteredProduct, titles);
    }

    /**
     * Get product by brands from database
     * @param brands
     * @return
     */
    private List<ArrayList<Products>> getFilteredProduct(List<String> brands) {
        List<ArrayList<Products>> filterdProducts = new ArrayList<>();

        for(String brand : brands) {
            filterdProducts.add(productsController.getProductByBrand(brand));
        }

        return filterdProducts;
    }
}
