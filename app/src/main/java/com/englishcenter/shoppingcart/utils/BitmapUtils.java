package com.englishcenter.shoppingcart.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by jb on 04/09/2016.
 */
public class BitmapUtils {

    /**
     * Load Image with low memory usage
     * @param resId
     * @return bitmap image
     */
    public static Bitmap convertToBitmap(Context context, int resId) {
        return BitmapFactory.decodeResource(context.getResources(), resId);
    }

}
