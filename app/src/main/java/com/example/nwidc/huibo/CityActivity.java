package com.example.nwidc.huibo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class CityActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);


    }

//    public void showPopFormBottom(View view) {
//        TakePhotoPopWin takePhotoPopWin = new TakePhotoPopWin(this, onClickListener);
////        设置Popupwindow显示位置（从底部弹出）
//        takePhotoPopWin.showAtLocation(findViewById(R.id.main_view), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
//        params = getWindow().getAttributes();
//        //当弹出Popupwindow时，背景变半透明
//        params.alpha=0.7f;
//        getWindow().setAttributes(params);
//        //设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复1f
//        takePhotoPopWin.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                params = getWindow().getAttributes();
//                params.alpha=1f;
//                getWindow().setAttributes(params);
//            }
//        });
//
////        takePhotoPopWin.lis
    //}


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
