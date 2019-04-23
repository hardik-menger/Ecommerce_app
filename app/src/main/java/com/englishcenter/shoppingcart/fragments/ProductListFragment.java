package com.englishcenter.shoppingcart.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.englishcenter.shoppingcart.R;
import com.englishcenter.shoppingcart.Shopping;
import com.englishcenter.shoppingcart.activities.MainActivity;
import com.englishcenter.shoppingcart.adapters.ProductListAdapter;
import com.englishcenter.shoppingcart.callbacks.OptionDialogCallback;
import com.englishcenter.shoppingcart.dialogs.CustomDialog;
import com.englishcenter.shoppingcart.models.cart.Cart;
import com.englishcenter.shoppingcart.models.products.Products;
import com.englishcenter.shoppingcart.presenters.CartPresenter;
import com.englishcenter.shoppingcart.views.ShoppingCartInteraction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jb on 02/09/2016.
 */
public class ProductListFragment extends Fragment implements ShoppingCartInteraction.AddProduct {

    private static final String TAG = ProductListFragment.class.getSimpleName();
    private static final String EXTRA_PRODUCTS = "products";

    private Activity activity;
    private Bundle bundleArgs;
    private List<Products> productList;
    private CartPresenter cartViewPresenter;

    @BindView(R.id.recycler_products)
    RecyclerView recyclerProducts;

    @Inject
    CustomDialog customDialog;

    /**
     * Provide instance of this fragment
     * @param products
     * @return
     */
    public static ProductListFragment getFragmentInstance(ArrayList<Products> products) {
        ProductListFragment productListFragment = new ProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(EXTRA_PRODUCTS, products);
        productListFragment.setArguments(bundle);

        return productListFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(activity).inflate(R.layout.fragment_products_list, container, false);
        ButterKnife.bind(this, view);

        cartViewPresenter = new CartPresenter(activity, this);

        bundleArgs = getArguments();
        productList = bundleArgs.getParcelableArrayList(EXTRA_PRODUCTS);

        recyclerProducts.setHasFixedSize(true);
        recyclerProducts.setLayoutManager(new LinearLayoutManager(activity));
        recyclerProducts.setAdapter(new ProductListAdapter(activity, productList, this));

        ((Shopping) activity.getApplicationContext()).getApplicationComponent().inject(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity) {
            activity = (MainActivity) context;
        } else {
            throw new IllegalStateException("No activity attached");
        }
    }

    @Override
    public void showOptionDialog(Cart item) {
        customDialog.showOptionDialog(activity,
                activity.getString(R.string.dialog_add_cart_title),
                activity.getString(R.string.dialog_add_cart_message, item.getProductName()),
                activity.getString(R.string.button_add_to_cart),
                activity.getString(R.string.button_cancel));

        customDialog.setOptionDialogCallback(new OptionDialogCallback() {
            @Override
            public void positiveButtonClick() {
                cartViewPresenter.addItemToCart(item);
            }

            @Override
            public void negativeButtonClick() {
            }
        });
    }

    @Override
    public void showAddCartSucess() {
        customDialog.showBasicDialog(activity,
                activity.getString(R.string.success),
                activity.getString(R.string.dialog_add_cart_succes));
    }
}
