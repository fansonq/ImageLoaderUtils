package com.fansonq.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * @author Created by：Fanson
 * Created Time: 2018/10/19 14:27
 * Describe：加载图片框架的策略接口
 */
public interface BaseImageLoaderStrategy{

    /**
     * 设置图片加载的配置参数
     *
     * @param config 配置参数
     */
    void setLoaderConfig(ImageLoaderConfig config);

    /**
     * 默认方式加载图片
     *
     * @param context   上下文
     * @param imageView View控件
     * @param imgUrl    图片Url
     */
    void loadImage(Context context, ImageView imageView, Object imgUrl);

    /**
     * 从drawable中异步加载本地图片
     *
     * @param context   上下文
     * @param imageId   资源ID
     * @param imageView View控件
     */
    void displayFromDrawable(Context context, int imageId, ImageView imageView);

    /**
     * 从内存卡中异步加载本地图片
     *
     * @param uri       路径
     * @param imageView View控件
     */
    void displayFromSDCard(String uri, ImageView imageView);

    /**
     * 加载圆角图片
     *
     * @param context   上下文
     * @param imageView View控件
     * @param imgUrl    图片Url
     */
    void loadCircleImage(Context context, ImageView imageView, String imgUrl);

    /**
     * 加载Gif
     *
     * @param context   上下文
     * @param imageView View控件
     * @param imgUrl    图片Url
     */
    void loadGifImage(Context context, ImageView imageView, Object imgUrl);

    /**
     * 加载圆角图片
     *
     * @param context   上下文
     * @param imageView View控件
     * @param imgUrl    图片Url
     */
    void loadCornerImage(Context context, ImageView imageView, String imgUrl, int radius);

    /**
     * 清除内存
     */
    void clearMemory(Context context);

}