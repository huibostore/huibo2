package com.example.nwidc.huibo;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Goods_infoActivity extends AppCompatActivity implements ObservableScrollView.OnObservableScrollViewListener {


    private ObservableScrollView mObservableScrollView;
    private ImageView mTextView;
    private LinearLayout mHeaderContent;

    private int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置透明状态栏
        StatusbarUtils.enableTranslucentStatusbar(this);
        setContentView(R.layout.activity_goods);

        //按钮返回
        final TextView GoBack2 = (TextView) findViewById(R.id.textView);
        GoBack2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });



        //初始化控件
        mObservableScrollView = (ObservableScrollView) findViewById(R.id.sv_main_content);
        mTextView = (ImageView) findViewById(R.id.imageView);
        mHeaderContent = (LinearLayout) findViewById(R.id.ll_header_content);

        //获取标题栏高度
        ViewTreeObserver viewTreeObserver = mTextView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                mTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mHeight = mTextView.getHeight() - mHeaderContent.getHeight();//这里取的高度应该为图片的高度-标题栏
                //注册滑动监听
                mObservableScrollView.setOnObservableScrollViewListener(Goods_infoActivity.this);
            }
        });
    }


    /**
     * 获取ObservableScrollView的滑动数据
     *
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    public void onObservableScrollViewListener(int l, int t, int oldl, int oldt) {
        if (t <= 0) {
            //顶部图处于最顶部，标题栏透明
            mHeaderContent.setBackgroundColor(Color.argb(0, 255, 255, 255));
        } else if (t > 0 && t < mHeight) {
            //滑动过程中，渐变
            float scale = (float) t / mHeight;//算出滑动距离比例
            float alpha = (255 * scale);//得到透明度
            mHeaderContent.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
        } else {
            //过顶部图区域，标题栏定色
            mHeaderContent.setBackgroundColor(Color.argb(255, 255, 255, 255));
        }
    }

    public void showPopFormBottom(View view) {
        TakePhotoPopWin takePhotoPopWin = new TakePhotoPopWin(this, onClickListener);
        //showAtLocation(View parent, int gravity, int x, int y)
        takePhotoPopWin.showAtLocation(findViewById(R.id.main_view), Gravity.BOTTOM, 0, 0);
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_take_photo:
                    System.out.println("btn_take_photo");
                    break;
                case R.id.btn_pick_photo:
                    System.out.println("btn_pick_photo");
                    break;
            }
        }

    };


}
