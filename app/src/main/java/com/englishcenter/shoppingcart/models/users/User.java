package com.englishcenter.shoppingcart.models.users;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jb on 04/09/2016.
 */
public class User implements Parcelable {

    private static final String EXTRA_USER_NAME = "user_name";
    private static final String EXTRA_USER_EMAIL = "user_email";
    private static final String EXTRA_USER_MOBILE = "user_mobile";
    private static final String EXTRA_USER_ADDRESS = "user_address";
    private static final String EXTRA_USER_CARD_TYPE = "user_card_type";
    private static final String EXTRA_USER_CARD_NUMBER = "user_card_number";
    private static final String EXTRA_USER_CARD_PIN = "user_card_pin";

    String name;
    String email;
    String mobile;
    String address;
    String cardType;
    String creditCardNumber;
    String creditCardPin;

    public User() {}

    protected User(Parcel in) {
        Bundle bundle = in.readBundle(getClass().getClassLoader());
        name = bundle.getString(EXTRA_USER_NAME, "");
        email = bundle.getString(EXTRA_USER_EMAIL, "");
        mobile = bundle.getString(EXTRA_USER_MOBILE, "");
        address = bundle.getString(EXTRA_USER_ADDRESS, "");
        cardType = bundle.getString(EXTRA_USER_CARD_TYPE, "");
        creditCardNumber = bundle.getString(EXTRA_USER_CARD_NUMBER, "");
        creditCardPin = bundle.getString(EXTRA_USER_CARD_PIN, "");
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_USER_NAME, name);
        bundle.putString(EXTRA_USER_EMAIL, email);
        bundle.putString(EXTRA_USER_MOBILE, mobile);
        bundle.putString(EXTRA_USER_ADDRESS, address);
        bundle.putString(EXTRA_USER_CARD_TYPE, cardType);
        bundle.putString(EXTRA_USER_CARD_NUMBER, creditCardNumber);
        bundle.putString(EXTRA_USER_CARD_PIN, creditCardPin);
        parcel.writeBundle(bundle);
    }

    //region Setters
    public void setAddress(String address) {
        this.address = address;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setCreditCardPin(String creditCardPin) {
        this.creditCardPin = creditCardPin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

    //region Getters
    public String getAddress() {
        return address;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCreditCardPin() {
        return creditCardPin;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }
    //endregion
}
