package com.englishcenter.shoppingcart.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.englishcenter.shoppingcart.R;
import com.englishcenter.shoppingcart.Shopping;
import com.englishcenter.shoppingcart.adapters.ShoppingCartListAdapter;
import com.englishcenter.shoppingcart.callbacks.OptionDialogCallback;
import com.englishcenter.shoppingcart.dialogs.CustomDialog;
import com.englishcenter.shoppingcart.models.cart.Cart;
import com.englishcenter.shoppingcart.presenters.ShoppingCartPresenter;
import com.englishcenter.shoppingcart.views.ShoppingCartInteraction;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hardik Menger
 */
public class ShoppingCartActivity extends AppCompatActivity implements
        ShoppingCartInteraction.ShoppingCart, ShoppingCartInteraction.RemoveItemCart {

    private static final String TAG = ShoppingCartListAdapter.class.getSimpleName();

    private Activity activity;
    private ShoppingCartListAdapter adapter;
    private ShoppingCartPresenter shoppingCartPresenter;
    private boolean isCartEmpty = true;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_cart_list)
    RecyclerView recyclerCartList;

    @BindView(R.id.text_total_items)
    TextView textTotalItems;

    @BindView(R.id.text_total_amount)
    TextView textTotalAmount;

    @BindView(R.id.linear_empty_cart)
    LinearLayout linearEmptyCart;

    @BindView(R.id.text_toolbar_title)
    TextView textToolbarTitle;

    @Inject
    CustomDialog customDialog;

    /**
     * Provide intent to start this activity
     * @param context
     */
    public static void getStartIntent(Context context) {
        Intent intent = new Intent(context, ShoppingCartActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_shopping_cart);
        ButterKnife.bind(this);

        activity = this;

        ((Shopping) activity.getApplicationContext()).getApplicationComponent().inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });
        textToolbarTitle.setText(getString(R.string.activity_cart_title));

        shoppingCartPresenter = new ShoppingCartPresenter(activity, this);
        shoppingCartPresenter.getCartItemList();
    }

    @Override
    public void showCartList(List<Cart> cartList) {
        isCartEmpty = false;

        adapter = new ShoppingCartListAdapter(activity, cartList, this);

        recyclerCartList.setHasFixedSize(true);
        recyclerCartList.setLayoutManager(new LinearLayoutManager(activity));
        recyclerCartList.setAdapter(adapter);
    }

    @Override
    public void showNoCartItem() {
        isCartEmpty = true;

        recyclerCartList.setVisibility(View.GONE);
        linearEmptyCart.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCartSummary(float totalAmount, int totalItems) {
        textTotalAmount.setText("₹ " + Float.toString(totalAmount));
        textTotalItems.setText(Integer.toString(totalItems));
    }

    @Override
    public void removeItem(int cartId) {
        customDialog.showOptionDialog(activity,
                activity.getString(R.string.dialog_remove_cart_title),
                activity.getString(R.string.dialog_remove_cart_message),
                activity.getString(R.string.button_remove_to_cart),
                activity.getString(R.string.button_cancel));
        customDialog.setOptionDialogCallback(new OptionDialogCallback() {
            @Override
            public void positiveButtonClick() {
                shoppingCartPresenter.removeCartItem(cartId);
            }

            @Override
            public void negativeButtonClick() {

            }
        });
    }

    @OnClick(R.id.button_checkout)
    public void checkout() {
        if(!isCartEmpty) {
            CheckoutActivity.getStartIntent(activity);
        } else {
            customDialog.showBasicDialog(activity,
                    getString(R.string.dialog_cart_empty_title),
                    getString(R.string.dialog_cart_empty_message));
        }
    }
}
