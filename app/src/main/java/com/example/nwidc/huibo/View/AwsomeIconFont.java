package com.example.nwidc.huibo.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/2/7 0007.
 */
public class AwsomeIconFont extends TextView {

    protected static Typeface stp;

    protected void init(Context context){
        if(stp == null){
            stp = Typeface.createFromAsset(context.getAssets(),"iconfont.ttf");
        }
        this.setTypeface(stp);
    }

    public AwsomeIconFont(Context context) {
        super(context);
        init(context);
    }

    public AwsomeIconFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AwsomeIconFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AwsomeIconFont(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
}
