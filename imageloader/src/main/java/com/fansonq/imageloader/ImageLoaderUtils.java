package com.fansonq.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.fansonq.imageloader.glide.GlideLoaderStrategy;

/**
 * @author Created by：Fanson
 * Created Time: 2018/10/19 14:32
 * Describe：图片加载框架的代理类
 */
public class ImageLoaderUtils {

    private static BaseImageLoaderStrategy sImageLoaderStrategy;
    private volatile static ImageLoaderUtils mInstance;

    /**
     * 默认参数配置
     */
    private static ImageLoaderConfig mDefaultConfig = new ImageLoaderConfig.Builder()
            .setMaxDiskCache(1024 * 1024 * 50)
            .setMaxMemoryCache(1024 * 1024 * 10)
            .errorPicRes(R.mipmap.default_image)
            .placePicRes(R.mipmap.default_image)
            .build();

    /**
     * 初始化
     */
    public static void init() {
        //默认使用Glide框架
        sImageLoaderStrategy = new GlideLoaderStrategy();
        sImageLoaderStrategy.setLoaderConfig(mDefaultConfig);
    }

    /**
     * 获取实例
     *
     * @return ImageLoaderUtils实例
     */
    public static ImageLoaderUtils getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoaderUtils.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderUtils();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    /**
     * 设置图片框架策略
     *
     * @param strategy 图片框架策略
     */
    public void setImageLoaderStrategy(BaseImageLoaderStrategy strategy) {
        sImageLoaderStrategy = strategy;
        //设置默认参数，防止用户没有设置
        setImageLoaderConfig(mDefaultConfig);
    }

    /**
     * 设置框架的配置
     *
     * @param config 配置
     * @return BaseImageLoaderStrategy
     */
    public BaseImageLoaderStrategy setImageLoaderConfig(ImageLoaderConfig config) {
        sImageLoaderStrategy.setLoaderConfig(config);
        return sImageLoaderStrategy;
    }

    /**
     * 常规加载图片
     *
     * @param context   上下文
     * @param imageView View控件
     * @param imgUrl    图片Url
     */
    public BaseImageLoaderStrategy loadImage(Context context, ImageView imageView, Object imgUrl) {
        sImageLoaderStrategy.loadImage(context, imageView, imgUrl);
        return sImageLoaderStrategy;
    }

    /**
     * 加载本地的Drawable图片
     *
     * @param context   上下文
     * @param imageID   图片资源ID
     * @param imageView View控件
     */
    public BaseImageLoaderStrategy displayFromDrawable(Context context, int imageID, ImageView imageView) {
        sImageLoaderStrategy.displayFromDrawable(context, imageID, imageView);
        return sImageLoaderStrategy;
    }

    /**
     * 加载本地的Drawable图片
     *
     * @param uri       图片资源路径
     * @param imageView View控件
     */
    public BaseImageLoaderStrategy displayFromSD(String uri, ImageView imageView) {
        sImageLoaderStrategy.displayFromSDCard(uri, imageView);
        return sImageLoaderStrategy;
    }

    /**
     * 加载圆形图片
     *
     * @param context   上下文
     * @param imageView View控件
     * @param imgUrl    图片Url
     */
    public BaseImageLoaderStrategy loadCircleImage(Context context, ImageView imageView, String imgUrl) {
        sImageLoaderStrategy.loadCircleImage(context, imageView, imgUrl);
        return sImageLoaderStrategy;
    }

    /**
     * 加载圆角图片
     *
     * @param context   上下文
     * @param imageView View控件
     * @param imgUrl    图片Url
     * @param radius    圆角半径
     */
    public BaseImageLoaderStrategy loadCornerImage(Context context, ImageView imageView, String imgUrl, int radius) {
        sImageLoaderStrategy.loadCornerImage(context, imageView, imgUrl, radius);
        return sImageLoaderStrategy;
    }

    /**
     * 加载Gif图片
     *
     * @param context   上下文
     * @param imageView View控件
     * @param imgUrl    图片Url
     */
    public BaseImageLoaderStrategy loadGifImage(Context context, ImageView imageView, Object imgUrl) {
        sImageLoaderStrategy.loadGifImage(context, imageView, imgUrl);
        return sImageLoaderStrategy;
    }

    /**
     * 清除内存
     *
     * @param context 上下文
     */
    public BaseImageLoaderStrategy clearMemory(Context context) {
        sImageLoaderStrategy.clearMemory(context);
        return sImageLoaderStrategy;
    }


}

