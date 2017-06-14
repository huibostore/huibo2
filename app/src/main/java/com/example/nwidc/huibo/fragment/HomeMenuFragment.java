package com.example.nwidc.huibo.fragment;
import android.support.annotation.Nullable;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.Adapter.HomeFragmentAdapter;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeMenuFragment extends Fragment {

    //private String content;
    private TabLayout tab_essence;
    private ViewPager vp_essence;
    private List<Fragment> fragments;
    private View view;



    @Nullable
    @Override


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home,container,false);
        initConentView(view);
        initData();



        return view;
    }
    public void initConentView(View view) {
        this.tab_essence = (TabLayout) view.findViewById(R.id.tab_essence);
        this.vp_essence = (ViewPager) view.findViewById(R.id.vp_essence);
    }

    public void initData() {
        //获取标签数据
        String[] titles = new String[]{"推荐@dream@0", "精选@dream@1", "热卖@dream@2", "易购@dream@3","淘宝@dream@3","本地@dream@3","团购@dream@3",};
        fragments= new ArrayList<Fragment>();
        fragments.add(new ContentFragment());
        fragments.add(new TestFragment3());
        fragments.add(new TestFragment3());
        fragments.add(new TestFragment3());
        fragments.add(new TestFragment3());
        fragments.add(new TestFragment3());
        fragments.add(new TestFragment3());

        //创建一个viewpager的adapter
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getChildFragmentManager(), Arrays.asList(titles),fragments);
        this.vp_essence.setAdapter(adapter);

        //将TabLayout和ViewPager关联起来
        this.tab_essence.setupWithViewPager(this.vp_essence);
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



//    public void onClickTakes(View viewContent ) {
//
//        TextView textView = (TextView) viewContent.findViewById(R.id.testx);
//        textView.setText("haha");
//
//    }

}
