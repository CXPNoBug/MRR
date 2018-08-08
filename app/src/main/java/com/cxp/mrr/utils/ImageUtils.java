package com.cxp.mrr.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * 文 件 名: ImageUtils
 * 创 建 人: CXP
 * 创建日期: 2018-05-31 14:26
 * 描    述: 图片工具类
 * <p>
 * Glide特点
 * 使用简单
 * 可配置度高，自适应程度高
 * 支持常见图片格式 Jpg png gif webp
 * 支持多种数据源  网络、本地、资源、Assets 等
 * 高效缓存策略    支持Memory和Disk图片缓存 默认Bitmap格式采用RGB_565内存使用至少减少一半
 * 生命周期集成   根据Activity/Fragment生命周期自动管理请求
 * 高效处理Bitmap  使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力
 * 这里默认支持Context，Glide支持Context,Activity,Fragment，FragmentActivity
 *
 * PS:不要再非主线程里面使用Glide加载图片，如果真的使用了，请把context参数换成getApplicationContext
 *
 */
public class ImageUtils {


    //默认加载
    public static void loadImage(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).into(mImageView);
    }

    //加载指定大小
    public static void loadImageSize(Context mContext, String path, int width, int height, ImageView mImageView) {
        RequestOptions options = new RequestOptions()
                .override(width, height);
        Glide.with(mContext).load(path).apply(options).into(mImageView);
    }

    //填充
    public static void loadImageCrop(Context mContext, String path, ImageView mImageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop();
        Glide.with(mContext).load(path).apply(options).into(mImageView);
    }

    //设置加载中以及加载失败图片
    public static void loadImageLoding(Context mContext, String path, ImageView mImageView, int lodingImage, int errorImageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(lodingImage)
                .error(errorImageView);
        Glide.with(mContext).load(path).apply(options).into(mImageView);
    }

    //设置加载中以及加载失败图片并且指定大小
    public static void loadImageLodingSize(Context mContext, String path, int width, int height, ImageView mImageView, int lodingImage, int errorImageView) {
        RequestOptions options = new RequestOptions()
                .override(width, height)
                .placeholder(lodingImage)
                .error(errorImageView);
        Glide.with(mContext).load(path).apply(options).into(mImageView);
    }

    //清除图片缓存
    public static void loadImageClearCache(Context mContext, String path, ImageView mImageView) {
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(mContext).load(path).apply(options).into(mImageView);
    }

    /**
     * 策略解说：
     * <p>
     * all:缓存源资源和转换后的资源
     * <p>
     * none:不作任何磁盘缓存
     * <p>
     * source:缓存源资源
     * <p>
     * result：缓存转换后的资源
     */

    //设置缓存策略
    public static void loadImageDiskCache(Context mContext, String path, ImageView mImageView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext).load(path).apply(options).into(mImageView);
    }

    /**
     * 会先加载缩略图
     */

    //设置缩略图支持
    public static void loadImageThumbnail(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).thumbnail(0.1f).into(mImageView);
    }

    //设置动态GIF加载方式
    public static void loadImageDynamicGif(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).asGif().load(path).into(mImageView);
    }

    //设置静态GIF加载方式(gif第一帧)
    public static void loadImageStaticGif(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).asBitmap().load(path).into(mImageView);
    }

    //清理磁盘缓存
    public static void GuideClearDiskCache(Context mContext) {
        //理磁盘缓存 需要在子线程中执行
        Glide.get(mContext).clearDiskCache();
    }

    //清理内存缓存
    public static void GuideClearMemory(Context mContext) {
        //清理内存缓存  可以在UI主线程中进行
        Glide.get(mContext).clearMemory();
    }
}
