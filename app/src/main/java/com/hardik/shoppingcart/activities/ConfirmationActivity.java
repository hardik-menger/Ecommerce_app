package com.hardik.shoppingcart.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.hardik.shoppingcart.R;
import com.hardik.shoppingcart.adapters.CartListConfirmAdapter;
import com.hardik.shoppingcart.models.cart.Cart;
import com.hardik.shoppingcart.models.users.User;
import com.hardik.shoppingcart.presenters.ShoppingCartPresenter;
import com.hardik.shoppingcart.views.ShoppingCartInteraction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmationActivity extends AppCompatActivity implements ShoppingCartInteraction.ShoppingCart {

    private static final String BUNDLE_USERS = "bundle_users";
    private static final String EXTRA_USERS = "users";

    private Activity activity;
    private ShoppingCartPresenter shoppingCartPresenter;
    private Bundle bundleParams;
    private User user;
    private CartListConfirmAdapter adapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.text_confirm_name)
    TextView textConfirmName;

    @BindView(R.id.text_confirm_email)
    TextView textConfirmEmail;

    @BindView(R.id.text_confirm_mobile)
    TextView textConfirmMobile;

    @BindView(R.id.text_confirm_adress)
    TextView textConfirmAddress;

    @BindView(R.id.text_confirm_card_type)
    TextView textConfirmCardType;

    @BindView(R.id.text_confirm_card_number)
    TextView textConfirmCardNumber;

    @BindView(R.id.recycler_cart_list)
    RecyclerView recyclerCartList;

    @BindView(R.id.text_toolbar_title)
    TextView textToolbarTitle;


    /**
     * Provide intent with flags to start this Activity
     * @param context
     * @param user
     */
    public static void getStartIntentWithFlags(Context context, User user) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_USERS, user);

        Intent intent = new Intent(context, ConfirmationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(BUNDLE_USERS, bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_confirmation);
        ButterKnife.bind(this);

        activity = this;
        shoppingCartPresenter = new ShoppingCartPresenter(activity, this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
        textToolbarTitle.setText(getString(R.string.activity_user_confirm));

        initializeUserDetails();
        shoppingCartPresenter.getCartItemList();
    }

    private void initializeUserDetails() {
        bundleParams = getIntent().getExtras();
        user = bundleParams.getBundle(BUNDLE_USERS).getParcelable(EXTRA_USERS);

        textConfirmName.setText(user.getName());
        textConfirmEmail.setText(user.getEmail());
        textConfirmMobile.setText(user.getMobile());
        textConfirmAddress.setText(user.getAddress());
        textConfirmCardType.setText(user.getCardType());
        textConfirmCardNumber.setText(user.getCreditCardNumber());
    }

    @OnClick(R.id.button_submit)
    public void submitOrder(View view) {
        OrderSuccessActivity.getStartIntent(activity);
    }


    @Override
    public void showCartList(List<Cart> cartList) {
        adapter = new CartListConfirmAdapter(activity, cartList);

        recyclerCartList.setHasFixedSize(true);
        recyclerCartList.setLayoutManager(new LinearLayoutManager(activity));
        recyclerCartList.setAdapter(adapter);
    }

    @Override
    public void showNoCartItem() {

    }

    @Override
    public void showCartSummary(float totalAmount, int totalItems) {

    }
}
