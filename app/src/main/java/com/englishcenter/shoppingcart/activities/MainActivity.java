package com.englishcenter.shoppingcart.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.englishcenter.shoppingcart.R;
import com.englishcenter.shoppingcart.adapters.ProductBrandAdapter;
import com.englishcenter.shoppingcart.models.products.Products;
import com.englishcenter.shoppingcart.presenters.MainActivityProductPresenter;
import com.englishcenter.shoppingcart.views.MainActivityInteraction;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityInteraction {

    private Activity activity;
    private ProductBrandAdapter adapter;
    private MainActivityProductPresenter viewPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tab_products)
    TabLayout tabLayout;

    @BindView(R.id.pager_list)
    ViewPager pagerList;

    /**
     * Provide intent to start this activity
     * @param context
     */
    public static void getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * Privide intent to start this activity as new Task
     * @param context
     */
    public static void getStartIntentNewTask(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        activity = this;
        viewPresenter = new MainActivityProductPresenter(activity, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPresenter.getProducts();
    }

    @Override
    public void showProducts(List<ArrayList<Products>> productsList, List<String> titles) {
        adapter = new ProductBrandAdapter(getSupportFragmentManager(), titles, productsList);
        pagerList.setAdapter(adapter);
        pagerList.setOffscreenPageLimit(titles.size());

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(pagerList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.cart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_item_cart:
                ShoppingCartActivity.getStartIntent(activity);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
