package com.fansonq.imageloader.universal;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.fansonq.imageloader.BaseImageLoaderStrategy;
import com.fansonq.imageloader.ImageLoaderConfig;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * @author Created by：Fanson
 * Created Time: 2018/10/19 14:45
 * Describe：UniversalLoader的策略实现类
 */
public class UniversalLoaderStrategy implements BaseImageLoaderStrategy {

    /**
     * 磁盘缓存大小
     */
    private static int MAX_DISK_CACHE = 1024 * 1024 * 50;
    /**
     * 内存大小
     */
    private static int MAX_MEMORY_CACHE = 1024 * 1024 * 10;
    /**
     * 圆角半径
     */
    public static final int CORNER_RADIUS = 20;
    /**
     * UniversalLoader实例
     */
    private volatile static ImageLoader sImageLoader;
    /**
     * 参数配置
     */
    private ImageLoaderConfig mImageLoaderConfig;

    private CircleBitmapDisplayer mCircleBitmapDisplayer;
    private RoundedBitmapDisplayer mRoundedBitmapDisplayer;

    /**
     * 获取实例
     * @return ImageLoader实例
     */
    public static ImageLoader getImageLoader() {
        if (sImageLoader == null) {
            synchronized (UniversalLoaderStrategy.class) {
                sImageLoader = ImageLoader.getInstance();
            }
        }
        return sImageLoader;
    }

    /**
     * 配置参数
     * @param context 上下文
     */
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder build = new ImageLoaderConfiguration.Builder(context);
        build.tasksProcessingOrder(QueueProcessingType.LIFO);
        build.diskCacheSize(MAX_DISK_CACHE);
        build.memoryCacheSize(MAX_MEMORY_CACHE);
        build.memoryCache(new LruMemoryCache(MAX_MEMORY_CACHE));
        getImageLoader().init(build.build());
    }

    /**
     * 设置图片放缩类型为模式EXACTLY，用于图片详情页的缩放
     *
     * @return
     */
    public static DisplayImageOptions getOption4ExactlyType() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
    }

    /**
     * 自定义Option，加载本地图片（无内存和硬盘的缓存）
     *
     * @return
     */
    public static DisplayImageOptions getOptionCustom() {
        return new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
    }

    /**
     * 加载头像专用Options，默认加载中、失败和空url为 ic_loading_small
     *
     * @return
     */
    public static DisplayImageOptions getOptions4Header(int errorResource,int loadingResource) {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageForEmptyUri(errorResource)
                .showImageOnFail(errorResource)
                .showImageOnLoading(loadingResource)
                .build();
    }

    /**
     * 加载图片列表专用，加载前会重置View
     *
     * @return
     */
    public static DisplayImageOptions getOptions4PictureList(int errorResource,int loadingResource) {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)
                .showImageOnLoading(loadingResource)
                .showImageForEmptyUri(loadingResource)
                .showImageOnFail(loadingResource)
                .build();
    }

    /**
     * @param bitmapDisplayer normal 或圆形、圆角 bitmapDisplayer
     *
     * @return
     */
    private DisplayImageOptions getCircleOption( BitmapDisplayer bitmapDisplayer) {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(bitmapDisplayer)
                .build();
    }

    public UniversalLoaderStrategy(Context context) {
        UniversalLoaderStrategy.initImageLoader(context);
    }


    @Override
    public void setLoaderConfig(ImageLoaderConfig config) {
        mImageLoaderConfig = config;
    }

    @Override
    public void loadImage( Context context, ImageView view, Object imgUrl) {
        sImageLoader.displayImage((String) imgUrl, view, getOptions4Header(mImageLoaderConfig.getErrorPicRes(),mImageLoaderConfig.getPlacePicRes()));
    }

    @Override
    public void displayFromDrawable(Context context,int imageId, ImageView imageView) {
        sImageLoader.displayImage("drawable://" + imageId, imageView, getOptionCustom());
    }

    @Override
    public void displayFromSDCard(String uri, ImageView imageView) {
        sImageLoader.displayImage("file://" + uri, imageView, getOptionCustom());
    }

    @Override
    public void loadCircleImage(Context context, ImageView imageView, String imgUrl) {
        mCircleBitmapDisplayer = new CircleBitmapDisplayer();
        sImageLoader.displayImage(imgUrl,imageView,getCircleOption(mCircleBitmapDisplayer));
    }

    @Override
    public void loadGifImage(Context context, ImageView imageView, Object imgUrl) {

    }

    @Override
    public void loadCornerImage(Context context, ImageView imageView, String imgUrl, int radius) {
        //避免使用RoundedBitmapDisplayer，会创建新的ARGB_8888格式的Bitmap对象
        mRoundedBitmapDisplayer = new RoundedBitmapDisplayer(CORNER_RADIUS);
        sImageLoader.displayImage(imgUrl,imageView,getCircleOption(mRoundedBitmapDisplayer));
    }

    @Override
    public void clearMemory(Context context) {
        sImageLoader.clearMemoryCache();
    }


}
