package com.englishcenter.shoppingcart.views;

import com.englishcenter.shoppingcart.models.products.Products;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jb on 02/09/2016.
 */
public interface MainActivityInteraction {

    void showProducts(List<ArrayList<Products>> productsList, List<String> titles);

}
