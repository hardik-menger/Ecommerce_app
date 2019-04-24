package com.englishcenter.shoppingcart.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.englishcenter.shoppingcart.R;
import com.englishcenter.shoppingcart.models.cart.Cart;
import com.englishcenter.shoppingcart.models.products.Products;
import com.englishcenter.shoppingcart.views.ShoppingCartInteraction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hardik Menger
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {

    private static final String TAG = ProductListAdapter.class.getSimpleName();

    private Context mContext;
    private List<Products> productsList;
    private ShoppingCartInteraction.AddProduct mView;
    private ImageView img;
    public ProductListAdapter(Context context, List<Products> productsList, ShoppingCartInteraction.AddProduct view) {
        this.mContext = context;
        this.productsList = productsList;
        this.mView = view;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Products product = productsList.get(position);

        holder.textProductName.setText(product.getProductName());
        holder.textProductBrand.setText(product.getProductBrand().toUpperCase());
        holder.textProductPrice.setText("₹ " + Float.toString(product.getProductPrice()));
        holder.imageView.setImageResource(R.drawable.iphone);
        holder.relativeProductContainer.setTag(product.getProductPrice());

    }

    @Override
    public int getItemCount() {
        return !productsList.isEmpty() ? productsList.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.relative_product_container)
        RelativeLayout relativeProductContainer;

        @BindView(R.id.text_product_name)
        TextView textProductName;

        @BindView(R.id.text_product_brand)
        TextView textProductBrand;

        @BindView(R.id.text_product_price)
        TextView textProductPrice;

        @BindView(R.id.product_img)
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.relative_product_container)
        public void addProduct(View view) {
            Cart item = new Cart();
            item.setProductName(textProductName.getText().toString());
            item.setProductPrice(Float.parseFloat(view.getTag().toString()));

            mView.showOptionDialog(item);
        }
    }
}
