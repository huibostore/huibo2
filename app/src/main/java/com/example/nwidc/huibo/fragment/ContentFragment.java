package com.example.nwidc.huibo.fragment;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nwidc.huibo.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by janiszhang on 2016/6/6.
 */

public class ContentFragment extends Fragment {

    private View viewContent;
    private int mType = 0;
    private String mTitle;


    public void setType(int mType) {
        this.mType = mType;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //布局文件中只有一个居中的TextView
        viewContent = inflater.inflate(R.layout.fragment_homecontent, container, false);
//        TextView textView = (TextView) viewContent.findViewById(R.id.tv_content);
//        textView.setText(this.mTitle);

        Banner banner = (Banner) viewContent.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new ContentFragment.GlideImageLoaders());

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");

        //设置图片集合
        banner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

//        viewContent.findViewById(R.id.huibo_snap).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                getFragmentManager()
//                    .beginTransaction()
//                    .addToBackStack(null)  //将当前fragment加入到返回栈中
//                    .replace(R.id.container, new TestFragment2()).commit();
//            }
//        });
//        viewContent.findViewById(R.id.home_rediu).setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                //new HomeMenuFragment().onClickTakes();
//                //((HomeMenuFragment)(ContentFragment.this.getParentFragment())).changeLayout();
//                TextView textView = (TextView) viewContent.findViewById(R.id.testx);
//               textView.setText("haha");
//            }
//
//        });

        return viewContent;
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
            ImageView simpleDraweeView = new ImageView(context);
            return simpleDraweeView;
        }
    }

//    public void onClicktuangou (View v) {
//       //new HomeMenuFragment().onClickTakes(viewContent);
//        TextView textView = (TextView) viewContent.findViewById(R.id.testx);
//        textView.setText("haha");
//    }

}

