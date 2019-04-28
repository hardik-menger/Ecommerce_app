package com.hardik.shoppingcart.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hardik.shoppingcart.R;
import com.hardik.shoppingcart.models.cart.Cart;
import com.hardik.shoppingcart.utils.BitmapUtils;
import com.hardik.shoppingcart.views.ShoppingCartInteraction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hardik Menger
 */
public class ShoppingCartListAdapter extends RecyclerView.Adapter<ShoppingCartListAdapter.CartListViewHolder> {

    private Context mContext;
    private List<Cart> cartList;
    private ShoppingCartInteraction.RemoveItemCart mView;
    int i=0;

    private ImageView img;
    public ShoppingCartListAdapter(Context context, List<Cart> cartList, ShoppingCartInteraction.RemoveItemCart view) {
        this.mContext = context;
        this.cartList = cartList;
        this.mView = view;
    }

    @Override
    public CartListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false);

        CartListViewHolder cartListViewHolder = new CartListViewHolder(view);

        return cartListViewHolder;
    }

    @Override
    public void onBindViewHolder(CartListViewHolder holder, int position) {
        Cart item = cartList.get(position);

        holder.textProductName.setText(item.getProductName());
        holder.textProductPrice.setText("₹ " + Float.toString(item.getProductPrice()));
        holder.textProductBrand.setVisibility(View.INVISIBLE);
        setImages(holder);
        holder.iconAction.setImageBitmap(BitmapUtils.convertToBitmap(mContext, R.drawable.ic_remove_shopping_cart_black));
        holder.iconAction.setTag(Integer.toString(item.getCartId()));
    }
    public void setImages(CartListViewHolder holder){
        int arr[]={R.drawable.iphone,R.drawable.samsung,R.drawable.samsung2,R.drawable.oppo};
        if(i==4)i=0;else holder.imageView.setImageResource(arr[i]);
        i++;
    }
    @Override
    public int getItemCount() {
        return !cartList.isEmpty() ? cartList.size() : 0;
    }

    class CartListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_product_name)
        TextView textProductName;

        @BindView(R.id.text_product_brand)
        TextView textProductBrand;

        @BindView(R.id.text_product_price)
        TextView textProductPrice;

        @BindView(R.id.icon_action)
        ImageView iconAction;

        @BindView(R.id.product_img)
        ImageView imageView;

        public CartListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.icon_action)
        public void removeFromCart(View view) {
            mView.removeItem(Integer.parseInt(view.getTag().toString()));
        }
    }
}
