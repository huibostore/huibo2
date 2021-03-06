package com.example.nwidc.huibo.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nwidc.huibo.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2017/5/23.
 */

public class PurchaseFragment extends Fragment {
    private View view;

    FragmentTransaction fragmentTransaction;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.activity_purchase, null);

        TextView tv = (TextView) view.findViewById(R.id.btn_back);
        tv .setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        Banner banner = (Banner) view.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new PurchaseFragment.GlideImageLoaders());

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");

        //设置图片集合
        banner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        return view;
    }


    private class GlideImageLoaders extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */


            //Glide 加载图片简单用法
            //Glide.with(context).load(path).into(imageView);

            //Picasso 加载图片简单用法
            // Picasso.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
            // Uri uri = Uri.parse((String) path);
            //imageView.setImageURI(uri);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.banner);
            imageView.setImageBitmap(bitmap);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
        @Override
        public ImageView createImageView(Context context) {
            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
            ImageView simpleDraweeView=new ImageView(context);
            return simpleDraweeView;
        }
    }



}
