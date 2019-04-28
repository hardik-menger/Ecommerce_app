package com.hardik.shoppingcart.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hardik.shoppingcart.R;
import com.hardik.shoppingcart.Shopping;
import com.hardik.shoppingcart.database.ProductsController;
import com.hardik.shoppingcart.models.products.ProductsRaw;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;

/**
 * Created by Hardik Menger
 */
public class SplashActivity  extends AppCompatActivity {

    private Gson gson;

    @Inject
    ProductsController productsController;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_splash);

        ((Shopping) getApplicationContext()).getApplicationComponent().inject(this);

        this.gson = new Gson();

        initializeProductData();
    }

    /**
     * Initialize the default product list
     */
    public void initializeProductData() {
        StringBuilder line = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    this.getAssets().open("products.json")
            ));

            String test;
            while((test = reader.readLine()) != null) {
                line.append(test);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProductsRaw rawResponse = gson.fromJson(String.valueOf(line), ProductsRaw.class);

        productsController.saveProductsList(rawResponse.getResponseProducts());

        MainActivity.getStartIntent(this);
        finish();
    }

}
