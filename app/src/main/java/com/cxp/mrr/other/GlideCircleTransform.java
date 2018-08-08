package com.cxp.mrr.other;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

import java.security.MessageDigest;

/**
 * 文 件 名: GlideCircleTransform
 * 创 建 人: CXP
 * 创建日期: 2018-05-21 6:27
 * 描    述: Glide加载圆形图片
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class GlideCircleTransform extends BitmapTransformation {


    @Override
    public String toString() {
        return "CropCircleTransformation()";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof GlideCircleTransform;
    }

    @Override
    public int hashCode() {
        return getClass().getName().hashCode();
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update((getClass().getName()).getBytes(CHARSET));
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return TransformationUtils.circleCrop(pool, toTransform, outWidth, outHeight);
    }
}
