package com.fansonq.imageloaderutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.fansonq.imageloader.ImageLoaderUtils;
import com.fansonq.imageloader.universal.UniversalLoaderStrategy;

public class MainActivity extends AppCompatActivity {

    private Button mBtnGlide, mBtnUniversal;
    private Button mBtnClear;
    private ImageView mIvPhoto;
    private String picUrl = "http://guolin.tech/book.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mBtnClear = findViewById(R.id.btn_clear);
        mBtnGlide = findViewById(R.id.btn_glide);
        mBtnUniversal = findViewById(R.id.btn_universal);
        mIvPhoto = findViewById(R.id.iv_test);
    }

    private void initData() {
        initImageLoaderUtils();
    }

    /**
     * 初始化监听事件
     */
    private void initListener() {
        mBtnGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageLoaderUtils.getInstance().loadImage(MainActivity.this, mIvPhoto, picUrl);
            }
        });

        mBtnUniversal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置不同框架策略
                ImageLoaderUtils.getInstance().setImageLoaderStrategy(new UniversalLoaderStrategy(MainActivity.this));
                ImageLoaderUtils.getInstance().loadImage(MainActivity.this, mIvPhoto, picUrl);
            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIvPhoto.setImageDrawable(null);
            }
        });
    }

    /**
     * 初始化图片框架，可以放Application初始化
     */
    private void initImageLoaderUtils() {
        ImageLoaderUtils.init();

        //设置参数配置
//        ImageLoaderUtils.getInstance().setImageLoaderConfig(new ImageLoaderConfig.Builder().placePicRes(R.mipmap.ic_launcher_round).build());

    }


}
