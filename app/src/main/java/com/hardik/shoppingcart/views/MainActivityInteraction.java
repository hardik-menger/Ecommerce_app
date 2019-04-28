package com.hardik.shoppingcart.views;

import com.hardik.shoppingcart.models.products.Products;

import java.util.ArrayList;
import java.util.List;


public interface MainActivityInteraction {

    void showProducts(List<ArrayList<Products>> productsList, List<String> titles);

}
