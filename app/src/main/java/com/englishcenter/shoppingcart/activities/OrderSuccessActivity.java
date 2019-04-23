package com.englishcenter.shoppingcart.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.englishcenter.shoppingcart.R;
import com.englishcenter.shoppingcart.Shopping;
import com.englishcenter.shoppingcart.database.CartController;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jb on 04/09/2016.
 */
public class OrderSuccessActivity extends AppCompatActivity {

    private Activity activity;

    @Inject
    CartController cartController;

    /**
     * Provide intent to start this activity
     * @param context
     */
    public static void getStartIntent(Context context) {
        Intent intent = new Intent(context, OrderSuccessActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_order_success);
        ButterKnife.bind(this);

        activity = this;

        ((Shopping) activity.getApplicationContext()).getApplicationComponent().inject(this);
    }

    @OnClick(R.id.button_home)
    public void goBackToHome(View view) {
        cartController.removeAllItemFromCart();
        MainActivity.getStartIntent(activity);
        finish();
    }

}
