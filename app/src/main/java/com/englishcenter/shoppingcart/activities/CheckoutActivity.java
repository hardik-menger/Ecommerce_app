package com.englishcenter.shoppingcart.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.englishcenter.shoppingcart.R;
import com.englishcenter.shoppingcart.models.users.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by jb on 04/09/2016.
 */
public class CheckoutActivity extends AppCompatActivity {

    private Activity activity;
    private String cardType = "";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.text_input_name)
    TextInputLayout textInputName;

    @BindView(R.id.text_input_email)
    TextInputLayout textInputEmail;

    @BindView(R.id.text_input_mobile)
    TextInputLayout textInputMobile;

    @BindView(R.id.text_input_address)
    TextInputLayout textInputAddress;

    @BindView(R.id.text_input_card_number)
    TextInputLayout textInputCardNumber;

    @BindView(R.id.text_input_card_pin)
    TextInputLayout textInputCardPin;

    @BindView(R.id.text_toolbar_title)
    TextView textToolbarTitle;

    /**
     * Provide intent to start this activity
     * @param context
     */
    public static void getStartIntent(Context context) {
        Intent intent = new Intent(context, CheckoutActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);

        activity = this;

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
        textToolbarTitle.setText(getString(R.string.activity_user_info_title));
    }

    @OnCheckedChanged({R.id.radio_visa, R.id.radio_mastercard})
    public void selectCardType(RadioButton radioButton) {
        boolean checked = radioButton.isChecked();

        switch (radioButton.getId()) {
            case R.id.radio_visa:
                if(checked) cardType = getString(R.string.text_card_type_visa);
                break;
            case R.id.radio_mastercard:
                if(checked) cardType = getString(R.string.text_card_type_mastercard);
                break;
        }
    }

    @OnClick(R.id.button_confirm_details)
    public void confirmUserDetails() {
        String name = textInputName.getEditText().getText().toString();
        String email = textInputEmail.getEditText().getText().toString();
        String mobile = textInputMobile.getEditText().getText().toString();
        String address = textInputAddress.getEditText().getText().toString();
        String creditCardNumber = textInputCardNumber.getEditText().getText().toString();
        String creditCardPin = textInputCardPin.getEditText().getText().toString();
        String selectedCardType = cardType;

        if(TextUtils.isEmpty(name)) {
            textInputName.setError(getString(R.string.textfield_required));
            return;
        }
        else textInputName.setError(null);

        if(TextUtils.isEmpty(email)) {
            textInputEmail.setError(getString(R.string.textfield_required));
            return;
        }
        else textInputEmail.setError(null);

        if(TextUtils.isEmpty(mobile)) {
            textInputMobile.setError(getString(R.string.textfield_required));
            return;
        }
        else textInputMobile.setError(null);

        if(TextUtils.isEmpty(address)) {
            textInputAddress.setError(getString(R.string.textfield_required));
            return;
        }
        else textInputAddress.setError(null);

        if(TextUtils.isEmpty(creditCardNumber) || creditCardNumber.length() != 19) {
            textInputCardNumber.setError(getString(R.string.textfield_required));
            return;
        }
        else textInputCardNumber.setError(null);

        if(TextUtils.isEmpty(creditCardPin) || creditCardPin.length() != 3) {
            textInputCardPin.setError(getString(R.string.textfield_required));
            return;
        }
        else textInputCardPin.setError(null);

        if(selectedCardType.isEmpty()) {
            Toast.makeText(activity, activity.getString(R.string.text_required_card_type), Toast.LENGTH_SHORT).show();
            return;
        }


        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setAddress(address);
        user.setCardType(selectedCardType);
        user.setCreditCardNumber(creditCardNumber);
        user.setCreditCardPin(creditCardPin);

        ConfirmationActivity.getStartIntentWithFlags(activity, user);
    }

}
